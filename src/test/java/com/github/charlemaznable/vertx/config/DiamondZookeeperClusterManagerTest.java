package com.github.charlemaznable.vertx.config;

import com.github.charlemaznable.vertx.common.ZookeeperClusterManagerTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import static com.github.charlemaznable.core.vertx.VertxElf.parseStringToVertxOptions;
import static com.github.charlemaznable.vertx.config.VertxClusterConfigElf.VERTX_CLUSTER_CONFIG_DIAMOND_GROUP_NAME;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.VERTX_OPTIONS_DIAMOND_GROUP_NAME;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.getDiamondStone;

public class DiamondZookeeperClusterManagerTest extends ZookeeperClusterManagerTest {

    @Test
    public void testDiamondZookeeperClusterManager() {
        MockDiamondServer.setUpMockServer();

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "zookeeper", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondZookeeperClusterManager");
        val zookeeper = parseStringToVertxOptions(getDiamondStone("zookeeper"));
        assertZookeeper(zookeeper);

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "zookeeper0", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondZookeeperClusterManager()");
        val zookeeper0 = parseStringToVertxOptions(getDiamondStone("zookeeper0"));
        assertZookeeper0(zookeeper0);

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "zookeeper1", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondZookeeperClusterManager(zookeeperJson)");
        MockDiamondServer.setConfigInfo(VERTX_CLUSTER_CONFIG_DIAMOND_GROUP_NAME, "zookeeperJson", CUSTOM_ZOOKEEPER_JSON);
        val zookeeper1 = parseStringToVertxOptions(getDiamondStone("zookeeper1"));
        assertZookeeper1(zookeeper1);

        MockDiamondServer.tearDownMockServer();
    }

    @Test
    public void testDiamondZookeeperClusterManagerError() {
        MockDiamondServer.setUpMockServer();

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "zookeeperNone", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondZookeeperClusterManager(zookeeperNotExists)");
        val zookeeperNone = parseStringToVertxOptions(getDiamondStone("zookeeperNone"));
        assertZookeeperNone(zookeeperNone);

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "zookeeperError", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondZookeeperClusterManager(zookeeperJsonError)");
        MockDiamondServer.setConfigInfo(VERTX_CLUSTER_CONFIG_DIAMOND_GROUP_NAME, "zookeeperJsonError", "" +
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
        val zookeeperError = parseStringToVertxOptions(getDiamondStone("zookeeperError"));
        assertZookeeperError(zookeeperError);

        MockDiamondServer.tearDownMockServer();
    }
}
