package com.example.typeconverter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.typeconverter.type.IpPort;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloController {
	@GetMapping("/hello-v1")
	public String helloV1(HttpServletRequest request) {
		String data = request.getParameter("data"); //문자 타입 조회
		int intValue = Integer.parseInt(data); //숫자 타입으로 변경
		log.info("intValue = {}", intValue);
		return "ok";
	}

	@GetMapping("/hello-v2")
	public String helloV2(@RequestParam Integer data) {
		log.info("data = {}", data);
		return "ok";
	}

	@GetMapping("/ip-port")
	public String ipPort(@RequestParam IpPort ipPort) {
		log.info("ipPort IP = {}", ipPort.getIp());
		log.info("ipPort PORT = {}", ipPort.getPort());
		return "ok";
	}
}