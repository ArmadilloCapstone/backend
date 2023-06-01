package com.example.dolbomi;

import com.example.dolbomi.domain.Message;
import com.example.dolbomi.service.MessageService;
import com.example.dolbomi.service.StudentStateService;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketHandler extends TextWebSocketHandler {

    private final ConcurrentHashMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    private final HashMap<String, String> idNameMap = new HashMap<>();

    private final MessageService messageService;
    public WebSocketHandler(MessageService messageService){
        this.messageService = messageService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        if (session == null) {
            session.close(CloseStatus.BAD_DATA);
            return;
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = null;
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

        JSONObject chat = new JSONObject(message.getPayload());


        if(chat.getString("type").equals("setting")){
            sessionMap.put(chat.getString("id"), session);
            idNameMap.put(chat.getString("id"), chat.getString("name"));
            session.sendMessage(new TextMessage(chat.getString("id") + "가 등록되었습니다."));
        }
        else if(chat.getString("type").equals("message")){
            System.out.println("HI");
            String userid = null;
            for (String key : sessionMap.keySet()) {
                if(session.equals(sessionMap.get(key))){
                    userid = key;
                }
            }
            System.out.println(message.getPayload());

            var targetSession = sessionMap.get(chat.getString("id"));
            System.out.println(chat.getString("id"));;
            System.out.println(chat.getString("name"));;
            System.out.println(chat.getString("text"));
            System.out.println(targetSession);
            if (targetSession == null || userid == null) {
                return;
            }
            Message newMessage = new Message();
            newMessage.setSender_id(userid);
            newMessage.setSender_name(idNameMap.get(userid));
            newMessage.setReceiver_id(chat.getString("id"));
            newMessage.setReceiver_name(chat.getString("name"));;
            newMessage.setText(chat.getString("text"));
            newMessage.setDate(new Timestamp(System.currentTimeMillis()));
            Message createdMessage = messageService.createMessage(newMessage);
            targetSession.sendMessage(new TextMessage(createdMessage.toString()));
        }
        else{
            System.out.println("Error");
        }


    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

}
