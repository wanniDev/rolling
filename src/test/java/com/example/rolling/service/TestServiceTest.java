package com.example.rolling.service;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestServiceTest {

	@Autowired
	TestService testService;

	@Test
	void getPort() {
		assertThat(testService.getPort()).isEqualTo(8080);
	}
}