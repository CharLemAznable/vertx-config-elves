package com.github.charlemaznable.vertx.config;

import com.github.charlemaznable.vertx.common.DiamondStoneTest;
import com.github.charlemaznable.vertx.common.HazelcastClusterManagerTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import static com.github.charlemaznable.vertx.config.VertxClusterConfigElf.VERTX_CLUSTER_CONFIG_DIAMOND_GROUP_NAME;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.VERTX_OPTIONS_DIAMOND_GROUP_NAME;

@SuppressWarnings("HttpUrlsUsage")
public class DiamondHazelcastClusterManagerTest
        extends HazelcastClusterManagerTest implements DiamondStoneTest {

    @Test
    public void testDiamondHazelcastClusterManager() {
        MockDiamondServer.setUpMockServer();

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "hazelcast", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondHazelcastClusterManager");
        val hazelcast = parseDiamondStone("hazelcast");
        assertHazelcast(hazelcast);

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "hazelcast0", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondHazelcastClusterManager()");
        val hazelcast0 = parseDiamondStone("hazelcast0");
        assertHazelcast0(hazelcast0);

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "hazelcast1", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondHazelcastClusterManager(hazelcastXml)");
        MockDiamondServer.setConfigInfo(VERTX_CLUSTER_CONFIG_DIAMOND_GROUP_NAME, "hazelcastXml", """
                <?xml version="1.0" encoding="UTF-8"?>
                <hazelcast xmlns="http://www.hazelcast.com/schema/config"
                           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                           xsi:schemaLocation="http://www.hazelcast.com/schema/config
                           http://www.hazelcast.com/schema/config/hazelcast-config-4.0.xsd">
                    <network>
                        <port>6801</port>
                    </network>
                </hazelcast>
                """);
        val hazelcast1 = parseDiamondStone("hazelcast1");
        assertHazelcast1(hazelcast1);

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "hazelcast2", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondHazelcastClusterManager(VertxClusters, hazelcastYaml)");
        MockDiamondServer.setConfigInfo("VertxClusters", "hazelcastYaml", """
                hazelcast:
                  network:
                    port:
                      port: 7901
                """);
        val hazelcast2 = parseDiamondStone("hazelcast2");
        assertHazelcast2(hazelcast2);

        MockDiamondServer.tearDownMockServer();
    }

    @Test
    public void testDiamondHazelcastClusterManagerError() {
        MockDiamondServer.setUpMockServer();

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "hazelcastNone", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondHazelcastClusterManager(hazelcastNotExists)");
        val hazelcastNone = parseDiamondStone("hazelcastNone");
        assertHazelcastNone(hazelcastNone);

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "hazelcastError", "" +
                "clusterManager=@com.github.charlemaznable.vertx.config.DiamondHazelcastClusterManager(hazelcastXmlError)");
        MockDiamondServer.setConfigInfo(VERTX_CLUSTER_CONFIG_DIAMOND_GROUP_NAME, "hazelcastXmlError", """
                <?xml version="1.0" encoding="UTF-8"?>
                <hazelcast xmlns="http://www.hazelcast.com/schema/config"
                           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                           xsi:schemaLocation="http://www.hazelcast.com/schema/config
                           http://www.hazelcast.com/schema/config/hazelcast-config-4.0.xsd">
                    <network>
                        <port>6801
                    </network>
                </hazelcast>
                """);
        val hazelcastError = parseDiamondStone("hazelcastError");
        assertHazelcastError(hazelcastError);

        MockDiamondServer.tearDownMockServer();
    }
}
