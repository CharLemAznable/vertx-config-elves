package com.github.charlemaznable.vertx.common;

import io.vertx.core.VertxOptions;

import static com.github.charlemaznable.core.lang.Propertiess.parseStringToProperties;
import static com.github.charlemaznable.core.vertx.VertxElf.parsePropertiesToVertxOptions;
import static com.github.charlemaznable.vertx.config.VertxOptionsConfigElf.getDiamondStone;

public interface DiamondStoneTest {

    default VertxOptions parseDiamondStone(String dataId) {
        return parsePropertiesToVertxOptions(
                parseStringToProperties(getDiamondStone(dataId)));
    }
}
