package com.sid.msgateway;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/users/**")
                        .uri("lb://MSUSER"))
                .route(r -> r
                        .path("/products/**")
                        .uri("lb://MSPRODUCT"))
                .route(r -> r
                        .path("/orders/**")
                        .uri("lb://MSORDER"))
                .route(r -> r
                        .path("/payments/**")
                        .uri("lb://MSPAYMENT"))
                .route(r -> r
                        .path("/reviews/**")
                        .uri("lb://MSREVIEW"))
                .build();
    }
}
