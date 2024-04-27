package com.example.typeconverter.convertor;

import org.springframework.core.convert.converter.Converter;

import com.example.typeconverter.type.IpPort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {
	@Override
	public IpPort convert(String source) {
		log.info("convert source={}", source);
		String[] split = source.split(":");
		return new IpPort(split[0], Integer.parseInt(split[1]));
	}
}
