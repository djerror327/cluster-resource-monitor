package com.dinusha.soft;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class AppStart {
    private static final Logger logger = Logger.getLogger(AppStart.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        logger.info("Stating Cluster Resource Monitoring APP...");
        SpringApplication.run(AppStart.class, args);


//        int a = 0;
//        for (; ; ) {
//            Thread.sleep(5000);
////            StringBuffer stringBuilder=WebsocketConstent.cpuPayload;
//
//            System.out.println(ResourceController.payload.toString());
//            System.out.println();
//
////            SocketTextHandler.webSocketSession.sendMessage(new TextMessage("{\"cpu\":\"" + a + "\"}"));
//        }
    }
}
