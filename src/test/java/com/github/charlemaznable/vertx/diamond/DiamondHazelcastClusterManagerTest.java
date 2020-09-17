package com.github.charlemaznable.vertx.diamond;

import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.VERTX_CLUSTER_CONFIG_GROUP_NAME;
import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.VERTX_OPTIONS_GROUP_NAME;
import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.getVertxOptionsStone;
import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.parseStoneToVertxOptions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiamondHazelcastClusterManagerTest {

    @Test
    public void testDiamondHazelcastClusterManager() {
        MockDiamondServer.setUpMockServer();

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "hazelcast", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondHazelcastClusterManager");
        val hazelcast = parseStoneToVertxOptions(getVertxOptionsStone("hazelcast"));
        val hazelcastClusterManager = hazelcast.getClusterManager();
        assertTrue(hazelcastClusterManager instanceof HazelcastClusterManager);
        assertNull(((HazelcastClusterManager) hazelcastClusterManager).getConfig());

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "hazelcast0", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondHazelcastClusterManager()");
        val hazelcast0 = parseStoneToVertxOptions(getVertxOptionsStone("hazelcast0"));
        val hazelcast0ClusterManager = hazelcast0.getClusterManager();
        assertTrue(hazelcast0ClusterManager instanceof HazelcastClusterManager);
        assertNull(((HazelcastClusterManager) hazelcast0ClusterManager).getConfig());

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "hazelcast1", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondHazelcastClusterManager(hazelcastXml)");
        MockDiamondServer.setConfigInfo(VERTX_CLUSTER_CONFIG_GROUP_NAME, "hazelcastXml", "" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<hazelcast xmlns=\"http://www.hazelcast.com/schema/config\"\n" +
                "           xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "           xsi:schemaLocation=\"http://www.hazelcast.com/schema/config\n" +
                "           http://www.hazelcast.com/schema/config/hazelcast-config-3.12.xsd\">\n" +
                "    <network>\n" +
                "        <port>6801</port>\n" +
                "    </network>\n" +
                "</hazelcast>\n");
        val hazelcast1 = parseStoneToVertxOptions(getVertxOptionsStone("hazelcast1"));
        val hazelcast1ClusterManager = hazelcast1.getClusterManager();
        assertTrue(hazelcast1ClusterManager instanceof HazelcastClusterManager);
        val configXml = ((HazelcastClusterManager) hazelcast1ClusterManager).getConfig();
        assertNotNull(configXml);
        assertEquals(6801, configXml.getNetworkConfig().getPort());

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "hazelcast2", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondHazelcastClusterManager(VertxClusters, hazelcastYaml)");
        MockDiamondServer.setConfigInfo("VertxClusters", "hazelcastYaml", "" +
                "hazelcast:\n" +
                "  network:\n" +
                "    port:\n" +
                "      port: 7901\n");
        val hazelcast2 = parseStoneToVertxOptions(getVertxOptionsStone("hazelcast2"));
        val hazelcast2ClusterManager = hazelcast2.getClusterManager();
        assertTrue(hazelcast2ClusterManager instanceof HazelcastClusterManager);
        val configYaml = ((HazelcastClusterManager) hazelcast2ClusterManager).getConfig();
        assertNotNull(configYaml);
        assertEquals(7901, configYaml.getNetworkConfig().getPort());

        MockDiamondServer.tearDownMockServer();
    }

    @Test
    public void testDiamondHazelcastClusterManagerError() {
        MockDiamondServer.setUpMockServer();

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "hazelcastNone", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondHazelcastClusterManager(hazelcastNotExists)");
        val hazelcastNone = parseStoneToVertxOptions(getVertxOptionsStone("hazelcastNone"));
        val hazelcastNoneClusterManager = hazelcastNone.getClusterManager();
        assertTrue(hazelcastNoneClusterManager instanceof HazelcastClusterManager);
        assertNull(((HazelcastClusterManager) hazelcastNoneClusterManager).getConfig());

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "hazelcastError", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondHazelcastClusterManager(hazelcastXmlError)");
        MockDiamondServer.setConfigInfo(VERTX_CLUSTER_CONFIG_GROUP_NAME, "hazelcastXmlError", "" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<hazelcast xmlns=\"http://www.hazelcast.com/schema/config\"\n" +
                "           xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "           xsi:schemaLocation=\"http://www.hazelcast.com/schema/config\n" +
                "           http://www.hazelcast.com/schema/config/hazelcast-config-3.12.xsd\">\n" +
                "    <network>\n" +
                "        <port>6801\n" +
                "    </network>\n" +
                "</hazelcast>\n");
        val hazelcastError = parseStoneToVertxOptions(getVertxOptionsStone("hazelcastError"));
        val hazelcastErrorClusterManager = hazelcastError.getClusterManager();
        assertTrue(hazelcastErrorClusterManager instanceof HazelcastClusterManager);
        assertNull(((HazelcastClusterManager) hazelcastErrorClusterManager).getConfig());

        MockDiamondServer.tearDownMockServer();
    }
}
