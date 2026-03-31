package com.interswitch.fraud_detector_sidecar.dto;

import java.time.Instant;

public class HealthResponse {

    private String status;
    private String service;
    private Instant timestamp;
    private String ip;
    private int port;

    public HealthResponse(String status, String service, Instant timestamp, String ip, int port) {
        this.status = status;
        this.service = service;
        this.timestamp = timestamp;
        this.ip = ip;
        this.port = port;
    }

    public String getStatus() {
        return status;
    }

    public String getService() {
        return service;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}