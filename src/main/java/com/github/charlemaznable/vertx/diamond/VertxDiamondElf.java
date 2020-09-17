package com.github.charlemaznable.vertx.diamond;

import com.google.common.primitives.Primitives;
import io.vertx.core.VertxOptions;
import lombok.val;
import org.n3r.diamond.client.Miner;
import org.n3r.eql.util.O;
import org.n3r.eql.util.O.ValueGettable;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;
import static org.apache.commons.lang3.StringUtils.equalsAnyIgnoreCase;
import static org.n3r.diamond.client.impl.DiamondUtils.parseObject;
import static org.n3r.diamond.client.impl.DiamondUtils.parseStoneToProperties;

public final class VertxDiamondElf {

    public static final String VERTX_OPTIONS_GROUP_NAME = "VertxOptions";

    public static final String VERTX_CLUSTER_CONFIG_GROUP_NAME = "VertxClusterConfig";

    public static String getVertxOptionsStone(String dataId) {
        return new Miner().getStone(VERTX_OPTIONS_GROUP_NAME, dataId);
    }

    public static String getVertxClusterConfigStone(String group, String dataId) {
        return new Miner().getStone(defaultIfBlank(group, VERTX_CLUSTER_CONFIG_GROUP_NAME), dataId);
    }

    public static VertxOptions parseStoneToVertxOptions(String stone) {
        val vertxOptions = new VertxOptions();

        val properties = parseStoneToProperties(stone);
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

    @SuppressWarnings("Duplicates")
    private static Object parsePrimitive(Class<?> rt, String value) {
        if (rt == boolean.class) return equalsAnyIgnoreCase(value, "yes", "true", "on", "y");
        if (rt == short.class) return Short.parseShort(value);
        if (rt == int.class) return Integer.parseInt(value);
        if (rt == long.class) return Long.parseLong(value);
        if (rt == float.class) return Float.parseFloat(value);
        if (rt == double.class) return Double.parseDouble(value);
        if (rt == byte.class) return Byte.parseByte(value);
        if (rt == char.class) return value.length() > 0 ? value.charAt(0) : '\0';
        return null;
    }

    private VertxDiamondElf() {}
}
