package com.example.demo.controller;

import com.example.demo.dto.AInterface;
import com.example.demo.service.AService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ARestController {
    private final AService aService;

    @PostMapping
    public ResponseEntity<Void> manage(@RequestBody AInterface aRequest){
        aService.manage(aRequest);
        return ResponseEntity.ok().build();
    }
}
