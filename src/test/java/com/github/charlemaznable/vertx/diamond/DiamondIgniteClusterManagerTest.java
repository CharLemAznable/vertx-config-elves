package com.github.charlemaznable.vertx.diamond;

import io.vertx.spi.cluster.ignite.IgniteClusterManager;
import io.vertx.spi.cluster.ignite.IgniteOptions;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.VERTX_CLUSTER_CONFIG_GROUP_NAME;
import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.VERTX_OPTIONS_GROUP_NAME;
import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.getVertxOptionsStone;
import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.parseStoneToVertxOptions;
import static org.joor.Reflect.on;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiamondIgniteClusterManagerTest {

    @Test
    public void testDiamondIgniteClusterManager() {
        MockDiamondServer.setUpMockServer();

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "ignite", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondIgniteClusterManager");
        val ignite = parseStoneToVertxOptions(getVertxOptionsStone("ignite"));
        val igniteClusterManager = ignite.getClusterManager();
        assertTrue(igniteClusterManager instanceof IgniteClusterManager);
        assertNull(on(igniteClusterManager).get("extOptions"));

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "ignite0", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondIgniteClusterManager()");
        val ignite0 = parseStoneToVertxOptions(getVertxOptionsStone("ignite0"));
        val ignite0ClusterManager = ignite0.getClusterManager();
        assertTrue(ignite0ClusterManager instanceof IgniteClusterManager);
        assertNull(on(ignite0ClusterManager).get("extOptions"));

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "ignite1", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondIgniteClusterManager(igniteJson)");
        MockDiamondServer.setConfigInfo(VERTX_CLUSTER_CONFIG_GROUP_NAME, "igniteJson", "" +
                "{\n" +
                "  \"localPort\":47101\n" +
                "}");
        val ignite1 = parseStoneToVertxOptions(getVertxOptionsStone("ignite1"));
        val ignite1ClusterManager = ignite1.getClusterManager();
        assertTrue(ignite1ClusterManager instanceof IgniteClusterManager);
        IgniteOptions extOptions = on(ignite1ClusterManager).get("extOptions");
        assertNotNull(extOptions);
        assertEquals(47101, extOptions.getLocalPort());

        MockDiamondServer.tearDownMockServer();
    }

    @Test
    public void testDiamondIgniteClusterManagerError() {
        MockDiamondServer.setUpMockServer();

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "igniteNone", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondIgniteClusterManager(igniteNotExists)");
        val igniteNone = parseStoneToVertxOptions(getVertxOptionsStone("igniteNone"));
        val igniteNoneClusterManager = igniteNone.getClusterManager();
        assertTrue(igniteNoneClusterManager instanceof IgniteClusterManager);
        assertNull(on(igniteNoneClusterManager).get("extOptions"));

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "igniteError", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondIgniteClusterManager(igniteJsonError)");
        MockDiamondServer.setConfigInfo(VERTX_CLUSTER_CONFIG_GROUP_NAME, "igniteJsonError", "" +
                "{\n" +
                "  \"localPort\":47101,\n" +
                "  \"discoveryOptions\":" +
                "}");
        val igniteError = parseStoneToVertxOptions(getVertxOptionsStone("igniteError"));
        val igniteErrorClusterManager = igniteError.getClusterManager();
        assertTrue(igniteErrorClusterManager instanceof IgniteClusterManager);
        assertNull(on(igniteErrorClusterManager).get("extOptions"));

        MockDiamondServer.tearDownMockServer();
    }
}
