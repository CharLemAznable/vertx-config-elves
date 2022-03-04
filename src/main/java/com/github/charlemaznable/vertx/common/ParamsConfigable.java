package com.github.charlemaznable.vertx.common;

import lombok.val;
import org.n3r.diamond.client.cache.ParamsAppliable;

import static org.apache.commons.lang3.StringUtils.isBlank;

public interface ParamsConfigable extends ParamsAppliable {

    String fetchConfigValue(String[] params);

    void applyConfigValue(String configValue);

    @Override
    default void applyParams(String[] params) {
        val configValue = fetchConfigValue(params);
        // none value or blank value or error params
        if (isBlank(configValue)) return;
        applyConfigValue(configValue);
    }
}
