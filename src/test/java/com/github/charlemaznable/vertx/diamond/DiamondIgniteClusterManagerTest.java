package com.github.charlemaznable.vertx.diamond;

import io.vertx.spi.cluster.ignite.IgniteClusterManager;
import lombok.val;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import java.util.UUID;

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
        assertNull(on(igniteClusterManager).get("cfg"));

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "ignite0", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondIgniteClusterManager()");
        val ignite0 = parseStoneToVertxOptions(getVertxOptionsStone("ignite0"));
        val ignite0ClusterManager = ignite0.getClusterManager();
        assertTrue(ignite0ClusterManager instanceof IgniteClusterManager);
        assertNull(on(ignite0ClusterManager).get("cfg"));

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "ignite1", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondIgniteClusterManager(igniteXml)");
        MockDiamondServer.setConfigInfo(VERTX_CLUSTER_CONFIG_GROUP_NAME, "igniteXml", "" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xmlns:util=\"http://www.springframework.org/schema/util\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "                           http://www.springframework.org/schema/beans/spring-beans.xsd\n" +
                "                           http://www.springframework.org/schema/util\n" +
                "                           http://www.springframework.org/schema/util/spring-util.xsd\">\n" +
                "  <bean class=\"org.apache.ignite.configuration.IgniteConfiguration\">\n" +
                "    <property name=\"discoverySpi\">\n" +
                "      <bean class=\"org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi\">\n" +
                "        <property name=\"ipFinder\">\n" +
                "          <bean class=\"org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder\"/>\n" +
                "        </property>\n" +
                "      </bean>\n" +
                "    </property>\n" +
                "  </bean>\n" +
                "</beans>\n");
        val ignite1 = parseStoneToVertxOptions(getVertxOptionsStone("ignite1"));
        val ignite1ClusterManager = ignite1.getClusterManager();
        assertTrue(ignite1ClusterManager instanceof IgniteClusterManager);
        IgniteConfiguration cfg = on(ignite1ClusterManager).get("cfg");
        assertNotNull(cfg);
        val uuid = UUID.fromString(ignite1ClusterManager.getNodeID());
        //noinspection deprecation
        assertEquals(uuid, cfg.getNodeId());
        assertEquals("vertx.ignite.node." + uuid, cfg.getIgniteInstanceName());

        assertTrue(cfg.getDiscoverySpi() instanceof TcpDiscoverySpi);
        val discoverySpi = (TcpDiscoverySpi) cfg.getDiscoverySpi();
        assertTrue(discoverySpi.getIpFinder() instanceof TcpDiscoveryMulticastIpFinder);

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
        assertNull(on(igniteNoneClusterManager).get("cfg"));

        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "igniteError", "" +
                "clusterManager=@com.github.charlemaznable.vertx.diamond.DiamondIgniteClusterManager(igniteXmlError)");
        MockDiamondServer.setConfigInfo(VERTX_CLUSTER_CONFIG_GROUP_NAME, "igniteXmlError", "" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xmlns:util=\"http://www.springframework.org/schema/util\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "                           http://www.springframework.org/schema/beans/spring-beans.xsd\n" +
                "                           http://www.springframework.org/schema/util\n" +
                "                           http://www.springframework.org/schema/util/spring-util.xsd\">\n" +
                "  <bean class=\"org.apache.ignite.configuration.IgniteConfiguration\">\n" +
                "    <property name=\"discoverySpi\">\n" +
                "      <bean class=\"org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi\">\n" +
                "        <property name=\"ipFinder\">\n" +
                "          <bean class=\"org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder\"/>\n" +
                "      </bean>\n" +
                "    </property>\n" +
                "  </bean>\n" +
                "</beans>\n");
        val igniteError = parseStoneToVertxOptions(getVertxOptionsStone("igniteError"));
        val igniteErrorClusterManager = igniteError.getClusterManager();
        assertTrue(igniteErrorClusterManager instanceof IgniteClusterManager);
        assertNull(on(igniteErrorClusterManager).get("cfg"));

        MockDiamondServer.tearDownMockServer();
    }
}
