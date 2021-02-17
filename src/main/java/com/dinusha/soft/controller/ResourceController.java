package com.dinusha.soft.controller;

import com.dinusha.soft.utill.JsonUtil;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("/")
@EnableScheduling
@PropertySource("classpath:application.properties")
public class ResourceController {
    private static final Logger logger = Logger.getLogger(ResourceController.class);
    private static HashMap<String, String> payload = new HashMap<>();

    @Value("${delete.unused.tiles}")
    private String deleteTiles;

    @PostMapping("/v1/cpu")
    public void persistData(@RequestBody String cpu) {
        logger.debug("Post call : /v1/cpu : " + cpu);
        JsonUtil.JSON_OBJECT.apply(cpu);
        JSONObject jsonObject = JsonUtil.JSON_OBJECT.apply(cpu);
        payload.put((String) jsonObject.get("instance"), (String) jsonObject.get("cpu"));

    }

    @GetMapping("/v1/cpu")
    public Map<String, String> persistData() {
        logger.debug("Get call : /v1/cpu : " + payload);
        return payload;
    }

    @Scheduled(cron = "${cron.tile.refresh}")
    public void cleanTile() {
        if (deleteTiles.equals("true")) {
            logger.debug("Deleting unused tiles");
            payload = new HashMap<>();
        } else {
            logger.debug("Deleting unused tiles : off");
        }
    }
}
