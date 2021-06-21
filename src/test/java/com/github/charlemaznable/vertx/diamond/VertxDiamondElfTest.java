package com.github.charlemaznable.vertx.diamond;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import java.util.concurrent.TimeUnit;

import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.VERTX_OPTIONS_GROUP_NAME;
import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.getVertxOptionsStone;
import static com.github.charlemaznable.vertx.diamond.VertxDiamondElf.parseStoneToVertxOptions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VertxDiamondElfTest {

    @Test
    public void testVertxDiamondElf() {
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo(VERTX_OPTIONS_GROUP_NAME, "DEFAULT", "" +
                "eventLoopPoolSize=2\n" +
                "maxEventLoopExecuteTime=5\n" +
                "haEnabled=true\n" +
                "haGroup=___DEFAULT___\n" +
                "maxEventLoopExecuteTimeUnit=SECONDS\n" +
                "blockedThreadCheckIntervalUnit=SECOND\n");

        val configStone = getVertxOptionsStone("DEFAULT");
        assertNotNull(configStone);

        val vertxOptions = parseStoneToVertxOptions(configStone);
        assertEquals(2, vertxOptions.getEventLoopPoolSize());
        assertEquals(5, vertxOptions.getMaxEventLoopExecuteTime());
        assertTrue(vertxOptions.isHAEnabled());
        assertEquals("___DEFAULT___", vertxOptions.getHAGroup());
        assertEquals(TimeUnit.SECONDS, vertxOptions.getMaxEventLoopExecuteTimeUnit());
        assertNull(vertxOptions.getBlockedThreadCheckIntervalUnit()); // error config SECOND, should be SECONDS
        assertNull(vertxOptions.getClusterManager());

        MockDiamondServer.tearDownMockServer();
    }
}
