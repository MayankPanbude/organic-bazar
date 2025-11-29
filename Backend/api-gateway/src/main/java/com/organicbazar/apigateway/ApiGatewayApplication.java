package com.organicbazar.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("SERVER_PORT", dotenv.get("SERVER_PORT", "8080"));
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    // ✅ Global CORS handler for Gateway
//    @Bean
//    public WebFilter corsFilter() {
//        return (ServerWebExchange exchange, WebFilterChain chain) -> {
//            ServerHttpResponse response = exchange.getResponse();
//            HttpHeaders headers = response.getHeaders();
//            headers.add("Access-Control-Allow-Origin", "http://localhost:5173");
//            headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//            headers.add("Access-Control-Allow-Headers", "*");
//            headers.add("Access-Control-Allow-Credentials", "true");
//
//            if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
//                response.setStatusCode(HttpStatus.OK);
//                return response.setComplete();
//            }
//
//            return chain.filter(exchange);
//        };
//    }
}
