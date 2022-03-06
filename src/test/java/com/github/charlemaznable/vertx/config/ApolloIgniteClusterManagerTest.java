package com.github.charlemaznable.vertx.config;

import com.github.charlemaznable.apollo.MockApolloServer;
import com.github.charlemaznable.vertx.common.IgniteClusterManagerTest;
import lombok.val;
import org.junit.jupiter.api.Test;

import static com.github.charlemaznable.core.vertx.VertxElf.parseStringToVertxOptions;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.getApolloProperty;

public class ApolloIgniteClusterManagerTest extends IgniteClusterManagerTest {

    @Test
    public void testApolloIgniteClusterManager() {
        MockApolloServer.setUpMockServer();

        val ignite = parseStringToVertxOptions(getApolloProperty("ignite"));
        assertIgnite(ignite);

        val ignite0 = parseStringToVertxOptions(getApolloProperty("ignite0"));
        assertIgnite0(ignite0);

        val ignite1 = parseStringToVertxOptions(getApolloProperty("ignite1"));
        assertIgnite1(ignite1);

        MockApolloServer.tearDownMockServer();
    }

    @Test
    public void testApolloIgniteClusterManagerError() {
        MockApolloServer.setUpMockServer();

        val igniteNone = parseStringToVertxOptions(getApolloProperty("igniteNone"));
        assertIgniteNone(igniteNone);

        val igniteError = parseStringToVertxOptions(getApolloProperty("igniteError"));
        assertIgniteError(igniteError);

        MockApolloServer.tearDownMockServer();
    }
}
