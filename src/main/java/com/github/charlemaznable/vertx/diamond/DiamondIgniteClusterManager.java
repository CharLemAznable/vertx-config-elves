package com.github.charlemaznable.vertx.diamond;

import io.vertx.core.json.JsonObject;
import io.vertx.spi.cluster.ignite.IgniteClusterManager;
import io.vertx.spi.cluster.ignite.IgniteOptions;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.n3r.diamond.client.cache.ParamsAppliable;

import static com.github.charlemaznable.core.lang.Str.isBlank;
import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.getVertxClusterConfigStoneByApplyParams;
import static org.joor.Reflect.on;

@Slf4j
public final class DiamondIgniteClusterManager extends IgniteClusterManager implements ParamsAppliable {

    @Override
    public void applyParams(String[] params) {
        val configStone = getVertxClusterConfigStoneByApplyParams(params);
        if (isBlank(configStone)) return; // none diamond or blank diamond or error params

        // read diamond stone
        try {
            on(this).set("extOptions", new IgniteOptions(new JsonObject(configStone)));
        } catch (Exception e) {
            log.error("Failed to set config", e);
        }
    }
}
