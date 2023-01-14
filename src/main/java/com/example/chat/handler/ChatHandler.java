package com.example.chat.handler;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> clients = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String payload = message.getPayload();
        log.info("payload : " + payload);


        for (WebSocketSession client : clients) {
            client.sendMessage(message);
        }
    }


    /*클라이언트 접속 시 호출*/
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        clients.add(session);
        log.info("클라이언트 접속 : " + session);

    }

    /*클라이언트 접속 해제 시 호출*/
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        clients.remove(session);
        log.info("클라이언트 접속 해제 : " + session);
    }
}
