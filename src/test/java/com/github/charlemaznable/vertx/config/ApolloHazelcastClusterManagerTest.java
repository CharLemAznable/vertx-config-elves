package com.github.charlemaznable.vertx.config;

import com.github.charlemaznable.apollo.MockApolloServer;
import com.github.charlemaznable.vertx.common.HazelcastClusterManagerTest;
import lombok.val;
import org.junit.jupiter.api.Test;

import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.getApolloProperty;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.parseConfigValueToVertxOptions;

public class ApolloHazelcastClusterManagerTest extends HazelcastClusterManagerTest {

    @Test
    public void testApolloHazelcastClusterManager() {
        MockApolloServer.setUpMockServer();

        val hazelcast = parseConfigValueToVertxOptions(getApolloProperty("hazelcast"));
        assertHazelcast(hazelcast);

        val hazelcast0 = parseConfigValueToVertxOptions(getApolloProperty("hazelcast0"));
        assertHazelcast0(hazelcast0);

        val hazelcast1 = parseConfigValueToVertxOptions(getApolloProperty("hazelcast1"));
        assertHazelcast1(hazelcast1);

        val hazelcast2 = parseConfigValueToVertxOptions(getApolloProperty("hazelcast2"));
        assertHazelcast2(hazelcast2);

        MockApolloServer.tearDownMockServer();
    }

    @Test
    public void testApolloHazelcastClusterManagerError() {
        MockApolloServer.setUpMockServer();

        val hazelcastNone = parseConfigValueToVertxOptions(getApolloProperty("hazelcastNone"));
        assertHazelcastNone(hazelcastNone);

        val hazelcastError = parseConfigValueToVertxOptions(getApolloProperty("hazelcastError"));
        assertHazelcastError(hazelcastError);

        MockApolloServer.tearDownMockServer();
    }
}
