package com.github.charlemaznable.vertx.diamond;

import io.vertx.core.json.JsonObject;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.n3r.diamond.client.cache.ParamsAppliable;

import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.getVertxClusterConfigStoneByApplyParams;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Slf4j
public class DiamondZookeeperClusterManager extends ZookeeperClusterManager implements ParamsAppliable {

    @Override
    public void applyParams(String[] params) {
        val configStone = getVertxClusterConfigStoneByApplyParams(params);
        if (isBlank(configStone)) return; // none diamond or blank diamond or error params

        // read diamond stone
        try {
            this.setConfig(new JsonObject(configStone));
        } catch (Exception e) {
            log.error("Failed to set config", e);
        }
    }
}
