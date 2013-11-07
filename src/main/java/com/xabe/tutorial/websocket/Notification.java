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

@ServerEndpoint(value = "/notification")
@Singleton
public class Notification {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
	private Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());
	private Vector<String> historyNotification = new Vector<String>();
	
    @OnOpen
    public void onOpen(Session userSession) throws IOException {
    	LOGGER.info("New request received. Id: " + userSession.getId());
    	userSessions.add(userSession);
    	showHistoryNotification(userSession);
    }
    
    private void showHistoryNotification(Session session) throws IOException{
    	for(String message : historyNotification){
    		session.getBasicRemote().sendText(message);
    	}
    }
    
    public void addUser(String message) throws IOException{  		
    	historyNotification.add(message);
    	for(Session session : userSessions){
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
    	historyNotification.add(message);
        for (Session session : userSessions) {
        	session.getBasicRemote().sendText(message);
        }
    }
    
    
    public Set<Session> getUserSessions() {
		return userSessions;
	}
}
