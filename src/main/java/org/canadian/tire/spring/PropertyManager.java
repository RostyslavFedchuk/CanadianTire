package org.canadian.tire.spring;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Component
@PropertySource({"classpath:application.properties"})
public class PropertyManager {

    @Value("${canadian.tire.base.ui.url}")
    private String canadianTireBaseUiUrl;
    @Value("${base.api.url}")
    private String baseApiUrl;
    @Value("${api.access.token}")
    private String apiAccessToken;
    @Value("${implicit.wait.timeout}")
    private Integer implicitWaitTimeout;
}