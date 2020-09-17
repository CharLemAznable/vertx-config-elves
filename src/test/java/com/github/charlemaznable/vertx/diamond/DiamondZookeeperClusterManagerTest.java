package com.github.charlemaznable.vertx.diamond;

import io.vertx.core.json.JsonObject;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.VERTX_CLUSTER_CONFIG_GROUP_NAME;
import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.VERTX_OPTIONS_GROUP_NAME;
import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.getVertxOptionsStone;
import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.parseStoneToVertxOptions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiamondZookeeperClusterManagerTest {

    private static final String DEFAULT_ZOOKEEPER_JSON = "" +
            "{\n" +
            "  \"zookeeperHosts\":\"127.0.0.1\",\n" +
            "  \"sessionTimeout\":20000,\n" +
            "  \"connectTimeout\":3000,\n" +
            "  \"rootPath\":\"io.vertx\",\n" +
            "  \"retry\": {\n" +
            "    \"initialSleepTime\":100,\n" +
            "    \"intervalTimes\":10000,\n" +
            "    \"maxTimes\":5\n" +
            "  }\n" +
            "}";
    private static final JsonObject DEFAULT_ZOOKEEPER_JSON_OBJECT = new JsonObject(DEFAULT_ZOOKEEPER_JSON);

    private static final String CUSTOM_ZOOKEEPER_JSON = "" +
            "{\n" +
            "  \"zookeeperHosts\":\"192.168.0.1\",\n" +
            "  \"sessionTimeout\":10000,\n" +
            "  \"connectTimeout\":2000,\n" +
            "  \"rootPath\":\"io.vertx.custom\",\n" +
            "  \"retry\": {\n" +
            "    \"initialSleepTime\":200,\n" +
            "    \"intervalTimes\":15000,\n" +
            "    \"maxTimes\":10\n" +
            "  }\n" +
            "}";
    private static final JsonObject CUSTOM_ZOOKEEPER_JSON_OBJECT = new JsonObject(CUSTOM_ZOOKEEPER_JSON);

    @Test
    public void testDiamondZookeeperClusterManager() {
        MockDiamondServer.setUpMockServer();

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "zookeeper", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondZookeeperClusterManager");
        val zookeeper = parseStoneToVertxOptions(getVertxOptionsStone("zookeeper"));
        val zookeeperClusterManager = zookeeper.getClusterManager();
        assertTrue(zookeeperClusterManager instanceof ZookeeperClusterManager);
        assertEquals(DEFAULT_ZOOKEEPER_JSON_OBJECT, ((ZookeeperClusterManager) zookeeperClusterManager).getConfig());

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "zookeeper0", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondZookeeperClusterManager()");
        val zookeeper0 = parseStoneToVertxOptions(getVertxOptionsStone("zookeeper0"));
        val zookeeper0ClusterManager = zookeeper0.getClusterManager();
        assertTrue(zookeeper0ClusterManager instanceof ZookeeperClusterManager);
        assertEquals(DEFAULT_ZOOKEEPER_JSON_OBJECT, ((ZookeeperClusterManager) zookeeper0ClusterManager).getConfig());

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "zookeeper1", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondZookeeperClusterManager(zookeeperJson)");
        MockDiamondServer.setConfigInfo(VERTX_CLUSTER_CONFIG_GROUP_NAME, "zookeeperJson", CUSTOM_ZOOKEEPER_JSON);
        val zookeeper1 = parseStoneToVertxOptions(getVertxOptionsStone("zookeeper1"));
        val zookeeper1ClusterManager = zookeeper1.getClusterManager();
        assertTrue(zookeeper1ClusterManager instanceof ZookeeperClusterManager);
        assertEquals(CUSTOM_ZOOKEEPER_JSON_OBJECT, ((ZookeeperClusterManager) zookeeper1ClusterManager).getConfig());

        MockDiamondServer.tearDownMockServer();
    }

    @Test
    public void testDiamondZookeeperClusterManagerError() {
        MockDiamondServer.setUpMockServer();

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "zookeeperNone", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondZookeeperClusterManager(zookeeperNotExists)");
        val zookeeperNone = parseStoneToVertxOptions(getVertxOptionsStone("zookeeperNone"));
        val zookeeperNoneClusterManager = zookeeperNone.getClusterManager();
        assertTrue(zookeeperNoneClusterManager instanceof ZookeeperClusterManager);
        assertEquals(DEFAULT_ZOOKEEPER_JSON_OBJECT, ((ZookeeperClusterManager) zookeeperNoneClusterManager).getConfig());

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "zookeeperError", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondZookeeperClusterManager(zookeeperJsonError)");
        MockDiamondServer.setConfigInfo(VERTX_CLUSTER_CONFIG_GROUP_NAME, "zookeeperJsonError", "" +
                "{\n" +
                "  \"zookeeperHosts\":\"127.0.0.1\",\n" +
                "  \"sessionTimeout\":20000,\n" +
                "  \"connectTimeout\":3000,\n" +
                "  \"rootPath\":\"io.vertx\",\n" +
                "  \"retry\": \n" +
                "    \"initialSleepTime\":100,\n" +
                "    \"intervalTimes\":10000,\n" +
                "    \"maxTimes\":5\n" +
                "}");
        val zookeeperError = parseStoneToVertxOptions(getVertxOptionsStone("zookeeperError"));
        val zookeeperErrorClusterManager = zookeeperError.getClusterManager();
        assertTrue(zookeeperErrorClusterManager instanceof ZookeeperClusterManager);
        assertEquals(DEFAULT_ZOOKEEPER_JSON_OBJECT, ((ZookeeperClusterManager) zookeeperErrorClusterManager).getConfig());

        MockDiamondServer.tearDownMockServer();
    }
}
