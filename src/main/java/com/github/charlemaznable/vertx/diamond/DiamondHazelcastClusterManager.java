package com.github.charlemaznable.vertx.diamond;

import com.hazelcast.config.ConfigBuilder;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.config.YamlConfigBuilder;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.n3r.diamond.client.cache.ParamsAppliable;

import java.io.ByteArrayInputStream;

import static com.github.charlemaznable.core.lang.Str.isBlank;
import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.getVertxClusterConfigStoneByApplyParams;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.StringUtils.startsWithIgnoreCase;

@Slf4j
public final class DiamondHazelcastClusterManager extends HazelcastClusterManager implements ParamsAppliable {

    @Override
    public void applyParams(String[] params) {
        val configStone = getVertxClusterConfigStoneByApplyParams(params);
        if (isBlank(configStone)) return; // none diamond or blank diamond or error params

        // read diamond stone as XML or YAML format
        val inputStream = new ByteArrayInputStream(configStone.getBytes(UTF_8));
        ConfigBuilder configBuilder = startsWithIgnoreCase(configStone, "<?xml ")
                ? new XmlConfigBuilder(inputStream) : new YamlConfigBuilder(inputStream);
        try {
            this.setConfig(configBuilder.build());
        } catch (Exception e) {
            log.error("Failed to set config", e);
        }
    }
}
