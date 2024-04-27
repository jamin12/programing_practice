package com.example.typeconverter.formatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.util.Locale;

class MyNumberFormatterTest {
	MyNumberFormatter formatter = new MyNumberFormatter();

	@Test
	void parse() throws ParseException {
		Number result = formatter.parse("1,000", Locale.KOREA);
		assertThat(result).isEqualTo(1000L); //Long 타입 주의
	}

	@Test
	void print() {
		String result = formatter.print(1000, Locale.KOREA);
		assertThat(result).isEqualTo("1,000");
	}
}