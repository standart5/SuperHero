package com.superhero.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class MainServiceFallback implements FallbackProvider {

    private static final String DEFAULT_MESSAGE = "Service not available.";

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return new ClientResponse(HttpStatus.GATEWAY_TIMEOUT, DEFAULT_MESSAGE);
        } else {
            return new ClientResponse(HttpStatus.INTERNAL_SERVER_ERROR, DEFAULT_MESSAGE);
        }
    }
}
