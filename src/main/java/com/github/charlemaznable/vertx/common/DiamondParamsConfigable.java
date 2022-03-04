package com.github.charlemaznable.vertx.common;

import lombok.val;

import static com.github.charlemaznable.vertx.config.VertxClusterConfigElf.fetchVertxClusterConfigApplyParams;
import static com.github.charlemaznable.vertx.config.VertxClusterConfigElf.getDiamondStone;
import static java.util.Objects.isNull;

public interface DiamondParamsConfigable extends ParamsConfigable {

    @Override
    default String fetchConfigValue(String[] params) {
        val applyParams = fetchVertxClusterConfigApplyParams(params);
        if (isNull(applyParams)) return null;
        return getDiamondStone(applyParams.getLeft(), applyParams.getRight());
    }
}
