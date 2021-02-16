package com.dinusha.soft.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("/")
public class ResourceController {
    private static final HashMap<String, String> payload = new HashMap<>();

    @PostMapping("/v1/cpu")
    public void persistData(@RequestBody String cpu) {
        System.out.println(cpu);
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(cpu);
            payload.put((String) jsonObject.get("instance"), (String) jsonObject.get("cpu"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/v1/cpu")
    public Map<String, String> persistData() {
        return payload;
    }
}
