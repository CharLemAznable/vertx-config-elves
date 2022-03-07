package com.github.charlemaznable.vertx.config;

import com.github.charlemaznable.apollo.MockApolloServer;
import com.github.charlemaznable.vertx.common.ApolloPropertyTest;
import com.github.charlemaznable.vertx.common.HazelcastClusterManagerTest;
import lombok.val;
import org.junit.jupiter.api.Test;

public class ApolloHazelcastClusterManagerTest
        extends HazelcastClusterManagerTest implements ApolloPropertyTest {

    @Test
    public void testApolloHazelcastClusterManager() {
        MockApolloServer.setUpMockServer();

        val hazelcast = parseApolloProperty("hazelcast");
        assertHazelcast(hazelcast);

        val hazelcast0 = parseApolloProperty("hazelcast0");
        assertHazelcast0(hazelcast0);

        val hazelcast1 = parseApolloProperty("hazelcast1");
        assertHazelcast1(hazelcast1);

        val hazelcast2 = parseApolloProperty("hazelcast2");
        assertHazelcast2(hazelcast2);

        MockApolloServer.tearDownMockServer();
    }

    @Test
    public void testApolloHazelcastClusterManagerError() {
        MockApolloServer.setUpMockServer();

        val hazelcastNone = parseApolloProperty("hazelcastNone");
        assertHazelcastNone(hazelcastNone);

        val hazelcastError = parseApolloProperty("hazelcastError");
        assertHazelcastError(hazelcastError);

        MockApolloServer.tearDownMockServer();
    }
}
