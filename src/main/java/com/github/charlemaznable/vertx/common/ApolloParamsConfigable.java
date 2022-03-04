package com.github.charlemaznable.vertx.common;

import lombok.val;

import static com.github.charlemaznable.vertx.config.VertxClusterConfigElf.fetchVertxClusterConfigApplyParams;
import static com.github.charlemaznable.vertx.config.VertxClusterConfigElf.getApolloProperty;
import static java.util.Objects.isNull;

public interface ApolloParamsConfigable extends ParamsConfigable {

    @Override
    default String fetchConfigValue(String[] params) {
        val applyParams = fetchVertxClusterConfigApplyParams(params);
        if (isNull(applyParams)) return null;
        return getApolloProperty(applyParams.getLeft(), applyParams.getRight());
    }
}
