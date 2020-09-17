package com.github.charlemaznable.vertx.diamond;

import io.vertx.spi.cluster.ignite.IgniteClusterManager;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.ignite.internal.IgnitionEx;
import org.apache.ignite.internal.util.typedef.F;
import org.n3r.diamond.client.cache.ParamsAppliable;

import java.io.ByteArrayInputStream;

import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.getVertxClusterConfigStoneByApplyParams;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.joor.Reflect.on;

@Slf4j
public class DiamondIgniteClusterManager extends IgniteClusterManager implements ParamsAppliable {

    @Override
    public void applyParams(String[] params) {
        val configStone = getVertxClusterConfigStoneByApplyParams(params);
        if (isBlank(configStone)) return; // none diamond or blank diamond or error params

        // read diamond stone as XML or YAML format
        val inputStream = new ByteArrayInputStream(configStone.getBytes(UTF_8));
        try {
            val cfg = F.first(IgnitionEx.loadConfigurations(inputStream).get1());
            on(this).set("cfg", cfg).call("setNodeID", cfg);
        } catch (Exception e) {
            log.error("Failed to set config", e);
        }
    }
}
