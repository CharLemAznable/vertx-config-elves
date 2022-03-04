package com.github.charlemaznable.vertx.config;

import com.github.charlemaznable.apollo.MockApolloServer;
import com.github.charlemaznable.vertx.common.ZookeeperClusterManagerTest;
import lombok.val;
import org.junit.jupiter.api.Test;

import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.getApolloProperty;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.parseConfigValueToVertxOptions;

public class ApolloZookeeperClusterManagerTest extends ZookeeperClusterManagerTest {

    @Test
    public void testApolloZookeeperClusterManager() {
        MockApolloServer.setUpMockServer();

        val zookeeper = parseConfigValueToVertxOptions(getApolloProperty("zookeeper"));
        assertZookeeper(zookeeper);

        val zookeeper0 = parseConfigValueToVertxOptions(getApolloProperty("zookeeper0"));
        assertZookeeper0(zookeeper0);

        val zookeeper1 = parseConfigValueToVertxOptions(getApolloProperty("zookeeper1"));
        assertZookeeper1(zookeeper1);

        MockApolloServer.tearDownMockServer();
    }

    @Test
    public void testApolloZookeeperClusterManagerError() {
        MockApolloServer.setUpMockServer();

        val zookeeperNone = parseConfigValueToVertxOptions(getApolloProperty("zookeeperNone"));
        assertZookeeperNone(zookeeperNone);

        val zookeeperError = parseConfigValueToVertxOptions(getApolloProperty("zookeeperError"));
        assertZookeeperError(zookeeperError);

        MockApolloServer.tearDownMockServer();
    }
}
