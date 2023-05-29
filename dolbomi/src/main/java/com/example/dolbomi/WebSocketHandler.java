package com.example.dolbomi;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketHandler extends TextWebSocketHandler {

    private final ConcurrentHashMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        if (session == null) {
            session.close(CloseStatus.BAD_DATA);
            return;
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = "";
        for (String key : sessionMap.keySet()) {
            if(session.equals(sessionMap.get(key))){
                userId = key;
            }
        }
        if (userId != null) {
            sessionMap.remove(userId);
        }

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //setting_user_id
        String text = message.getPayload();
        if(text.length() > 16 && text.substring(0, 15).equals("setting_user_id")){
            String userId = text.substring(16, text.length());
            sessionMap.put(userId, session);
            session.sendMessage(new TextMessage(userId));
        }
        else{
            System.out.println("HI");
            String userId = "";
            for (String key : sessionMap.keySet()) {
                if(session.equals(sessionMap.get(key))){
                    userId = key;
                }
            }
            System.out.println(message.getPayload());
            JSONObject chat = new JSONObject(message.getPayload());

            var targetSession = sessionMap.get(chat.getString("id"));
            System.out.println(chat.getString("id"));;
            System.out.println(chat.getString("text"));
            System.out.println(targetSession);
            if (targetSession == null || userId == null) {
                return;
            }

            targetSession.sendMessage(new TextMessage("{ 'id':" + userId+ ",'text':'" + chat.getString("text") + "'}"));
        }

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

}
