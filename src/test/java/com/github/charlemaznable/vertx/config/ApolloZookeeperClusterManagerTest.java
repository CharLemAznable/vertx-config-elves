package com.github.charlemaznable.vertx.config;

import com.github.charlemaznable.apollo.MockApolloServer;
import com.github.charlemaznable.vertx.common.ZookeeperClusterManagerTest;
import lombok.val;
import org.junit.jupiter.api.Test;

import static com.github.charlemaznable.core.vertx.VertxElf.parseStringToVertxOptions;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.getApolloProperty;

public class ApolloZookeeperClusterManagerTest extends ZookeeperClusterManagerTest {

    @Test
    public void testApolloZookeeperClusterManager() {
        MockApolloServer.setUpMockServer();

        val zookeeper = parseStringToVertxOptions(getApolloProperty("zookeeper"));
        assertZookeeper(zookeeper);

        val zookeeper0 = parseStringToVertxOptions(getApolloProperty("zookeeper0"));
        assertZookeeper0(zookeeper0);

        val zookeeper1 = parseStringToVertxOptions(getApolloProperty("zookeeper1"));
        assertZookeeper1(zookeeper1);

        MockApolloServer.tearDownMockServer();
    }

    @Test
    public void testApolloZookeeperClusterManagerError() {
        MockApolloServer.setUpMockServer();

        val zookeeperNone = parseStringToVertxOptions(getApolloProperty("zookeeperNone"));
        assertZookeeperNone(zookeeperNone);

        val zookeeperError = parseStringToVertxOptions(getApolloProperty("zookeeperError"));
        assertZookeeperError(zookeeperError);

        MockApolloServer.tearDownMockServer();
    }
}
