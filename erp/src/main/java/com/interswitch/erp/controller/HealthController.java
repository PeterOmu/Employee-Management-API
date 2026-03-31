package com.interswitch.erp.controller;

import com.interswitch.fraud_detector_sidecar.dto.HealthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.time.Instant;

@RestController
public class HealthController {

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${server.port}")
    private int port;

    @GetMapping("/api/health")
    public HealthResponse health() throws Exception {

        String ip = InetAddress.getLocalHost().getHostAddress();

        return new HealthResponse(
                "UP",
                serviceName,
                Instant.now(),
                ip,
                port
        );
    }
}