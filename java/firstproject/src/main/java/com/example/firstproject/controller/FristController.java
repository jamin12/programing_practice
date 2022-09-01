package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FristController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "홍팍");
        return "greeting"; // templates.greetings.mustache -> 브라우저로 전송
    }

    @GetMapping(value = "/bye")
    public String goodBye(Model model) {
        model.addAttribute("username", "sdff");
        return "goodBye";
    }

}
