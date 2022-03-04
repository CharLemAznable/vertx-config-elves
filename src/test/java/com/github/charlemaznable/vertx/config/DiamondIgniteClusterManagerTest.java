package com.github.charlemaznable.vertx.config;

import com.github.charlemaznable.vertx.common.IgniteClusterManagerTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import static com.github.charlemaznable.vertx.config.VertxClusterConfigElf.VERTX_CLUSTER_CONFIG_DIAMOND_GROUP_NAME;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.VERTX_OPTIONS_DIAMOND_GROUP_NAME;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.getDiamondStone;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.parseConfigValueToVertxOptions;

public class DiamondIgniteClusterManagerTest extends IgniteClusterManagerTest {

    @Test
    public void testDiamondIgniteClusterManager() {
        MockDiamondServer.setUpMockServer();

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "ignite", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondIgniteClusterManager");
        val ignite = parseConfigValueToVertxOptions(getDiamondStone("ignite"));
        assertIgnite(ignite);

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "ignite0", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondIgniteClusterManager()");
        val ignite0 = parseConfigValueToVertxOptions(getDiamondStone("ignite0"));
        assertIgnite0(ignite0);

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "ignite1", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondIgniteClusterManager(igniteJson)");
        MockDiamondServer.setConfigInfo(VERTX_CLUSTER_CONFIG_DIAMOND_GROUP_NAME, "igniteJson", "" +
                "{\n" +
                "  \"localPort\":47101\n" +
                "}");
        val ignite1 = parseConfigValueToVertxOptions(getDiamondStone("ignite1"));
        assertIgnite1(ignite1);

        MockDiamondServer.tearDownMockServer();
    }

    @Test
    public void testDiamondIgniteClusterManagerError() {
        MockDiamondServer.setUpMockServer();

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "igniteNone", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondIgniteClusterManager(igniteNotExists)");
        val igniteNone = parseConfigValueToVertxOptions(getDiamondStone("igniteNone"));
        assertIgniteNone(igniteNone);

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "igniteError", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondIgniteClusterManager(igniteJsonError)");
        MockDiamondServer.setConfigInfo(VERTX_CLUSTER_CONFIG_DIAMOND_GROUP_NAME, "igniteJsonError", "" +
                "{\n" +
                "  \"localPort\":47101,\n" +
                "  \"discoveryOptions\":" +
                "}");
        val igniteError = parseConfigValueToVertxOptions(getDiamondStone("igniteError"));
        assertIgniteError(igniteError);

        MockDiamondServer.tearDownMockServer();
    }
}
