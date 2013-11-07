package com.xabe.tutorial.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.ejb.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint(value = "/websocket")
@Singleton
public class Chat {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
	private Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());
	private Vector<String> historyChat = new Vector<String>();
	
    @OnOpen
    public void onOpen(Session userSession) throws IOException {
    	LOGGER.info("New request received. Id: " + userSession.getId());
    	userSessions.add(userSession);
    	showHistoryChat(userSession);
    }
    
    private void showHistoryChat(Session session) throws IOException{
    	for(String message : historyChat){
    		session.getBasicRemote().sendText(message);
    	}
    }
    
    @OnClose
    public void onClose(Session userSession) {
    	LOGGER.info("Connection closed. Id: " + userSession.getId());
    	userSessions.remove(userSession);
    }    

    @OnMessage
    public void onMessage(String message, Session user) throws IOException {
    	historyChat.add(message);
        for (Session session : userSessions) {
        	session.getBasicRemote().sendText(message);
        }
    }
    
    
    public Set<Session> getUserSessions() {
		return userSessions;
	}
}
