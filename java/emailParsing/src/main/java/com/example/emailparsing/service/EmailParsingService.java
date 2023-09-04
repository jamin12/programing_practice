package com.example.emailparsing.service;

import org.springframework.stereotype.Service;

import com.example.emailparsing.utils.MailGetter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailParsingService {
	private final MailGetter mailGetter;


	public void getEmail(){
		mailGetter.getMain();
	}
}
