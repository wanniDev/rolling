package com.example.rolling.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rolling.entity.Model;

@RestController
@RequestMapping("api")
public class TestController {
	@Value("server.port")
	private int port;

	@GetMapping("port")
	public ResponseEntity<Integer> getPort() {
		return ResponseEntity.ok(port);
	}

	@GetMapping("hcheck")
	public ResponseEntity<Long> healthCheck() {
		return ResponseEntity.ok(System.currentTimeMillis());
	}
}
