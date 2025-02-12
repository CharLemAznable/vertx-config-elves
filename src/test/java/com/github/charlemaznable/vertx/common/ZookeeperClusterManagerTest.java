package com.github.charlemaznable.vertx.common;

import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;
import lombok.val;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class ZookeeperClusterManagerTest {

    protected static final String DEFAULT_ZOOKEEPER_JSON = """
            {
              "zookeeperHosts":"127.0.0.1",
              "sessionTimeout":20000,
              "connectTimeout":3000,
              "rootPath":"io.vertx",
              "retry": {
                "initialSleepTime":100,
                "intervalTimes":10000,
                "maxTimes":5
              }
            }""";
    protected static final JsonObject DEFAULT_ZOOKEEPER_JSON_OBJECT = new JsonObject(DEFAULT_ZOOKEEPER_JSON);

    protected static final String CUSTOM_ZOOKEEPER_JSON = """
            {
              "zookeeperHosts":"192.168.0.1",
              "sessionTimeout":10000,
              "connectTimeout":2000,
              "rootPath":"io.vertx.custom",
              "retry": {
                "initialSleepTime":200,
                "intervalTimes":15000,
                "maxTimes":10
              }
            }""";
    protected static final JsonObject CUSTOM_ZOOKEEPER_JSON_OBJECT = new JsonObject(CUSTOM_ZOOKEEPER_JSON);

    protected void assertZookeeper(VertxOptions zookeeper) {
        val zookeeperClusterManager = zookeeper.getClusterManager();
        assertTrue(zookeeperClusterManager instanceof ZookeeperClusterManager);
        assertEquals(DEFAULT_ZOOKEEPER_JSON_OBJECT, ((ZookeeperClusterManager) zookeeperClusterManager).getConfig());
    }

    protected void assertZookeeper0(VertxOptions zookeeper0) {
        val zookeeper0ClusterManager = zookeeper0.getClusterManager();
        assertTrue(zookeeper0ClusterManager instanceof ZookeeperClusterManager);
        assertEquals(DEFAULT_ZOOKEEPER_JSON_OBJECT, ((ZookeeperClusterManager) zookeeper0ClusterManager).getConfig());
    }

    protected void assertZookeeper1(VertxOptions zookeeper1) {
        val zookeeper1ClusterManager = zookeeper1.getClusterManager();
        assertTrue(zookeeper1ClusterManager instanceof ZookeeperClusterManager);
        assertEquals(CUSTOM_ZOOKEEPER_JSON_OBJECT, ((ZookeeperClusterManager) zookeeper1ClusterManager).getConfig());
    }

    protected void assertZookeeperNone(VertxOptions zookeeperNone) {
        val zookeeperNoneClusterManager = zookeeperNone.getClusterManager();
        assertTrue(zookeeperNoneClusterManager instanceof ZookeeperClusterManager);
        assertEquals(DEFAULT_ZOOKEEPER_JSON_OBJECT, ((ZookeeperClusterManager) zookeeperNoneClusterManager).getConfig());
    }

    protected void assertZookeeperError(VertxOptions zookeeperError) {
        val zookeeperErrorClusterManager = zookeeperError.getClusterManager();
        assertTrue(zookeeperErrorClusterManager instanceof ZookeeperClusterManager);
        assertEquals(DEFAULT_ZOOKEEPER_JSON_OBJECT, ((ZookeeperClusterManager) zookeeperErrorClusterManager).getConfig());
    }
}
