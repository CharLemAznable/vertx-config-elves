package com.github.charlemaznable.vertx.config;

import com.github.charlemaznable.apollo.MockApolloServer;
import com.github.charlemaznable.vertx.common.HazelcastClusterManagerTest;
import lombok.val;
import org.junit.jupiter.api.Test;

import static com.github.charlemaznable.core.vertx.VertxElf.parseStringToVertxOptions;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.getApolloProperty;

public class ApolloHazelcastClusterManagerTest extends HazelcastClusterManagerTest {

    @Test
    public void testApolloHazelcastClusterManager() {
        MockApolloServer.setUpMockServer();

        val hazelcast = parseStringToVertxOptions(getApolloProperty("hazelcast"));
        assertHazelcast(hazelcast);

        val hazelcast0 = parseStringToVertxOptions(getApolloProperty("hazelcast0"));
        assertHazelcast0(hazelcast0);

        val hazelcast1 = parseStringToVertxOptions(getApolloProperty("hazelcast1"));
        assertHazelcast1(hazelcast1);

        val hazelcast2 = parseStringToVertxOptions(getApolloProperty("hazelcast2"));
        assertHazelcast2(hazelcast2);

        MockApolloServer.tearDownMockServer();
    }

    @Test
    public void testApolloHazelcastClusterManagerError() {
        MockApolloServer.setUpMockServer();

        val hazelcastNone = parseStringToVertxOptions(getApolloProperty("hazelcastNone"));
        assertHazelcastNone(hazelcastNone);

        val hazelcastError = parseStringToVertxOptions(getApolloProperty("hazelcastError"));
        assertHazelcastError(hazelcastError);

        MockApolloServer.tearDownMockServer();
    }
}
