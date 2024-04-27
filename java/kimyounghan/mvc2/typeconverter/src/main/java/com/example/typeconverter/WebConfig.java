package com.example.typeconverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.typeconverter.convertor.IntegerToStringConverter;
import com.example.typeconverter.convertor.IpPortToStringConverter;
import com.example.typeconverter.convertor.StringToIntegerConverter;
import com.example.typeconverter.convertor.StringToIpPortConverter;
import com.example.typeconverter.formatter.MyNumberFormatter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addFormatters(FormatterRegistry registry) {
		// registry.addConverter(new IntegerToStringConverter());
		// registry.addConverter(new StringToIntegerConverter());
		registry.addConverter(new IpPortToStringConverter());
		registry.addConverter(new StringToIpPortConverter());

		registry.addFormatter(new MyNumberFormatter());
	}
}
