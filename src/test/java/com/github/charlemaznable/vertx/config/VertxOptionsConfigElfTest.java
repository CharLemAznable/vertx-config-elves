package com.github.charlemaznable.vertx.config;

import com.github.charlemaznable.apollo.MockApolloServer;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import java.util.concurrent.TimeUnit;

import static com.github.charlemaznable.core.lang.Propertiess.parseStringToProperties;
import static com.github.charlemaznable.core.vertx.VertxElf.parsePropertiesToVertxOptions;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.VERTX_OPTIONS_DIAMOND_GROUP_NAME;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.getApolloProperty;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.getDiamondStone;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VertxOptionsConfigElfTest {

    @Test
    public void testVertxOptionsConfigElfInApollo() {
        MockApolloServer.setUpMockServer();

        val configProperty = getApolloProperty("default");
        assertConfigValue(configProperty);

        MockApolloServer.tearDownMockServer();
    }

    @Test
    public void testVertxOptionsConfigElfInDiamond() {
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_DIAMOND_GROUP_NAME, "DEFAULT", "" +
                "eventLoopPoolSize=2\n" +
                "maxEventLoopExecuteTime=5\n" +
                "haEnabled=true\n" +
                "haGroup=___DEFAULT___\n" +
                "maxEventLoopExecuteTimeUnit=SECONDS\n" +
                "blockedThreadCheckIntervalUnit=SECOND\n");

        val configStone = getDiamondStone("DEFAULT");
        assertConfigValue(configStone);

        MockDiamondServer.tearDownMockServer();
    }

    private void assertConfigValue(String configValue) {
        assertNotNull(configValue);
        val vertxOptions = parsePropertiesToVertxOptions(parseStringToProperties(configValue));
        assertEquals(2, vertxOptions.getEventLoopPoolSize());
        assertEquals(5, vertxOptions.getMaxEventLoopExecuteTime());
        assertTrue(vertxOptions.isHAEnabled());
        assertEquals("___DEFAULT___", vertxOptions.getHAGroup());
        assertEquals(TimeUnit.SECONDS, vertxOptions.getMaxEventLoopExecuteTimeUnit());
        assertNull(vertxOptions.getBlockedThreadCheckIntervalUnit()); // error config SECOND, should be SECONDS
        assertNull(vertxOptions.getClusterManager());
    }
}
