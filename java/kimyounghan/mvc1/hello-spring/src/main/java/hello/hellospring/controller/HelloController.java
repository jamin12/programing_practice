package hello.hellospring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!!");
		return "hello";
	}

	@GetMapping("/hello-mvc")
	public String helloMvc(@RequestParam String name, Model model) {
		model.addAttribute("data", name);
		return "hello-template";
	}

	@GetMapping("hello-string")
	@ResponseBody
	public String helloString(@RequestParam("name") String name) {
		return "hello " + name;
	}

	@GetMapping("hello-api")
	@ResponseBody
	public ResponseEntity<Hello> helloApi(@RequestParam("name") String name) {
		return ResponseEntity.ok(new Hello(name));
	}

	record Hello(String name) {
	}
}
