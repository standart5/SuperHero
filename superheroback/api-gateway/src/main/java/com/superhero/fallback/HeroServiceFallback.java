package com.superhero.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class HeroServiceFallback implements FallbackProvider {

    private static final String DEFAULT_MESSAGE = "Heroes information is not available.";

    @Override
    public String getRoute() {
        return "hero-service";
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
