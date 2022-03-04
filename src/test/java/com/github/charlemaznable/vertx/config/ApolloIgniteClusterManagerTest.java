package com.github.charlemaznable.vertx.config;

import com.github.charlemaznable.apollo.MockApolloServer;
import com.github.charlemaznable.vertx.common.IgniteClusterManagerTest;
import lombok.val;
import org.junit.jupiter.api.Test;

import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.getApolloProperty;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.parseConfigValueToVertxOptions;

public class ApolloIgniteClusterManagerTest extends IgniteClusterManagerTest {

    @Test
    public void testApolloIgniteClusterManager() {
        MockApolloServer.setUpMockServer();

        val ignite = parseConfigValueToVertxOptions(getApolloProperty("ignite"));
        assertIgnite(ignite);

        val ignite0 = parseConfigValueToVertxOptions(getApolloProperty("ignite0"));
        assertIgnite0(ignite0);

        val ignite1 = parseConfigValueToVertxOptions(getApolloProperty("ignite1"));
        assertIgnite1(ignite1);

        MockApolloServer.tearDownMockServer();
    }

    @Test
    public void testApolloIgniteClusterManagerError() {
        MockApolloServer.setUpMockServer();

        val igniteNone = parseConfigValueToVertxOptions(getApolloProperty("igniteNone"));
        assertIgniteNone(igniteNone);

        val igniteError = parseConfigValueToVertxOptions(getApolloProperty("igniteError"));
        assertIgniteError(igniteError);

        MockApolloServer.tearDownMockServer();
    }
}
