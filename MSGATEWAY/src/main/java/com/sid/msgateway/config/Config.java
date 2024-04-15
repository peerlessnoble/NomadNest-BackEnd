package com.sid.msgateway.config;


import com.sid.msgateway.filter.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class Config {
    private final JwtAuthenticationFilter filter;
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/auth/**")
                        .uri("lb://SECURITY-MSERVICE"))
                .route(r -> r
                        .path("/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://MSUSER"))
                .route(r -> r
                        .path("/catalog/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://CATALOG-SERVICE"))
                .route(r -> r
                        .path("/orders/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://MSORDER"))
                        //.uri("lb://ORDER-SERVICE"))
                .route(r -> r
                        .path("/payments/**")
                        .uri("lb://MSPAYMENT"))
                .route(r -> r
                        .path("/reviews/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://MSREVIEW"))
                .build();
    }
}
