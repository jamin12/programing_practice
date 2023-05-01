package com.example.demo.service;

import com.example.demo.dto.AInterface;
import com.example.demo.repository.ARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AService {
    private final ARepository aRepository;

    public void manage(AInterface aRequest){
        aRepository.save(aRequest.toA());
    }
}
