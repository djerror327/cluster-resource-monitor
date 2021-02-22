package com.dinusha.soft.controller;

import com.dinusha.soft.constant.WebsocketConstent;
import com.dinusha.soft.utill.JsonUtil;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.task.TaskSchedulerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController("/")
@EnableScheduling
@PropertySource("classpath:application.properties")
public class ResourceController {
    private static final Logger logger = Logger.getLogger(ResourceController.class);
    public static HashMap<String, String> payload = new HashMap<>();

    @Value("${delete.unused.tiles}")
    private String deleteTiles;

    @PostMapping("/v1/cpu")
    public void persistData(@RequestBody String cpu) {
        logger.debug("Post call : /v1/cpu : " + cpu);
//        JsonUtil.JSON_OBJECT.apply(cpu);
        JSONObject jsonObject = JsonUtil.JSON_OBJECT.apply(cpu);
        payload.put((String) jsonObject.get("instance"), (String) jsonObject.get("cpu"));
        payload.forEach((key, value) -> WebsocketConstent.cpuPayload.append(key).append(":").append(value));


    }

//    @GetMapping("/v1/cpu")
//    public Map<String, String> persistData() {
//        logger.debug("Get call : /v1/cpu : " + payload);
//        return payload;
//    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "${cron.tile.refresh}")
    public void cleanTile() {
        if (deleteTiles.equals("true")) {
            logger.debug("Deleting unused tiles");
            payload = new HashMap<>();
//            WebsocketConstent.cpuPayload = new StringBuilder();
        } else {
            logger.debug("Deleting unused tiles : off");
        }

    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler(TaskSchedulerBuilder builder) {
        return builder.build();
    }
}
