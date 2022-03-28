package com.example.rolling.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rolling.entity.Model;
import com.example.rolling.service.TestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class TestController {

	private final TestService testService;

	@GetMapping("port")
	public ResponseEntity<Integer> getPort() {
		return ResponseEntity.ok(testService.getPort());
	}

	@GetMapping("hcheck")
	public ResponseEntity<Long> healthCheck() {
		return ResponseEntity.ok(testService.getHealthCheck());
	}
}
