package com.example.demo.service;

import java.util.NoSuchElementException;

import com.example.demo.domain.A;
import com.example.demo.dto.AInterface;
import com.example.demo.dto.AResponse;
import com.example.demo.repository.ARepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AService {
	private final ARepository aRepository;

	public AResponse getById(Long id) {
		A a = aRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Not found"));
		return AResponse.getA(a);
	}

	public void manage(AInterface aRequest) {
		aRepository.save(aRequest.toA());
	}
}
