package com.dinusha.soft.websocket;

import com.dinusha.soft.controller.ResourceController;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class SocketTextHandler extends TextWebSocketHandler {
    public static WebSocketSession webSocketSession;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // The WebSocket has been closed
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // The WebSocket has been opened
        // I might save this session object so that I can send messages to it outside of this method

        // Let's send the first message
        session.sendMessage(new TextMessage("You are now connected to the server. This is the first message."));
        sendMessage(session);
//        webSocketSession=session;
//        int a = 0;
//        for (; ; ) {
//            Thread.sleep(1000);
//            a += 1;
//            session.sendMessage(new TextMessage("{\"cpu\":\"" + a + "\"}"));
//        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        // A message has been received
        System.out.println("Message received: " + textMessage.getPayload());
    }

    private void sendMessage(WebSocketSession session) {

        for (; ; ) {
//            StringBuilder cpuPayload = new StringBuilder();
//            WebsocketConstent.payloadMap.forEach((key, value) -> {
//                cpuPayload.append()
//            });
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                session.sendMessage(new TextMessage(new JSONObject(ResourceController.payload).toJSONString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
