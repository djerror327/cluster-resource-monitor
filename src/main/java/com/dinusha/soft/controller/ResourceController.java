package com.dinusha.soft.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class ResourceController {
    @PostMapping("/v1/cpu")
    public void persistData(@RequestBody String cpu) {
        System.out.println(cpu);
    }
}
