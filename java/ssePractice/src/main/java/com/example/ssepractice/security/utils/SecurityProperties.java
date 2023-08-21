package com.example.ssepractice.security.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityProperties {
    @Value("${security.auth.excludedPaths}")
    private String excludedPaths;

    public String[] getExcludedPaths() {
        return excludedPaths.split(",");
    }
}