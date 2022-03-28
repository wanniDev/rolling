package com.example.rolling.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestService {

	@Value("${server.port}")
	private int port;

	public int getPort() {
		return this.port;
	}

	public Long getHealthCheck() {
		return System.currentTimeMillis();
	}
}
