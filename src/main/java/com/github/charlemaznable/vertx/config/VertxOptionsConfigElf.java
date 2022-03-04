package com.github.charlemaznable.vertx.config;

import com.ctrip.framework.apollo.ConfigService;
import com.google.common.primitives.Primitives;
import io.vertx.core.VertxOptions;
import lombok.NoArgsConstructor;
import lombok.val;
import org.n3r.diamond.client.Miner;
import org.n3r.eql.util.O;
import org.n3r.eql.util.O.ValueGettable;

import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.BooleanUtils.toBoolean;
import static org.apache.commons.lang3.math.NumberUtils.toInt;
import static org.apache.commons.lang3.math.NumberUtils.toLong;
import static org.n3r.diamond.client.impl.DiamondUtils.parseObject;
import static org.n3r.diamond.client.impl.DiamondUtils.parseStoneToProperties;

@NoArgsConstructor(access = PRIVATE)
public final class VertxOptionsConfigElf {

    public static final String VERTX_OPTIONS_APOLLO_NAMESPACE = "VertxOptions";
    public static final String VERTX_OPTIONS_DIAMOND_GROUP_NAME = "VertxOptions";

    public static String getApolloProperty(String propertyName) {
        return ConfigService.getConfig(VERTX_OPTIONS_APOLLO_NAMESPACE)
                .getProperty(propertyName, "");
    }

    public static String getDiamondStone(String dataId) {
        return new Miner().getStone(VERTX_OPTIONS_DIAMOND_GROUP_NAME, dataId);
    }

    public static VertxOptions parseConfigValueToVertxOptions(String value) {
        val vertxOptions = new VertxOptions();

        val properties = parseStoneToProperties(value);
        for (val prop : properties.entrySet()) {
            O.setValue(vertxOptions, Objects.toString(prop.getKey()), new ValueGettable() {
                @Override
                public Object getValue() {
                    return prop.getValue();
                }

                @SuppressWarnings("unchecked")
                @Override
                public Object getValue(Class<?> returnType) {
                    val value = Objects.toString(prop.getValue());
                    val rt = Primitives.unwrap(returnType);
                    if (rt == String.class) return value;
                    if (rt.isPrimitive()) return parsePrimitive(rt, value);

                    if (Enum.class.isAssignableFrom(returnType)) {
                        try {
                            return Enum.valueOf((Class<Enum>) returnType, value);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                    return parseObject(value, rt);
                }
            });
        }

        return vertxOptions;
    }

    private static Object parsePrimitive(Class<?> rt, String value) {
        if (rt == boolean.class) return toBoolean(value);
        if (rt == int.class) return toInt(value);
        if (rt == long.class) return toLong(value);
        return null;
    }
}
