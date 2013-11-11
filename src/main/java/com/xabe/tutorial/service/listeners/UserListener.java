package com.xabe.tutorial.service.listeners;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xabe.tutorial.model.user.User;
import com.xabe.tutorial.websocket.Notification;

public class UserListener {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
	
	@Inject
	private Notification notification;

	@PostConstruct
	public void postConstruct() {
		LOGGER.info("## ---------------------------------------------------------------------------->postConstruct: ");
	}

	@PreDestroy
	public void preDestroy() {
		LOGGER.info("## ---------------------------------------------------------------------------->preDestroy");
	}

	@PostLoad
	public void newUserLoad(User user) {
		LOGGER.info("## ---------------------------------------------------------------------------->User loaded: " + user.getName());
	}

	@PrePersist
	public void newUserAlertBefore(User user) {
		LOGGER.info("## ---------------------------------------------------------------------------->Ready to create new user: " + user.getName());
	}

	@PostPersist
	public void newUserAlertAfter(User user) {
		LOGGER.info("## ---------------------------------------------------------------------------->New user created: " + user.getName());
		try
		{
			if(notification != null)
			{
				notification.addUser("New user : "+user.getName());
			}
			else
			{
				LOGGER.info("Websocket notificacion es null");
			}
		}catch(IOException exception){
			LOGGER.error("Error al enviar la notificación");
		}
	}

	@PreUpdate
	public void updateUserAlertBefore(User user) {
		LOGGER.info("## ---------------------------------------------------------------------------->Ready to update user: " + user.getName());
	}

	@PostUpdate
	public void updateUserAlertAfter(User user) {
		LOGGER.info("## ---------------------------------------------------------------------------->User updated: " + user.getName());
		try
		{
			if(notification != null)
			{
				notification.addUser("Update user : "+user.getName());
			}
			else
			{
				LOGGER.info("Websocket notificacion es null");
			}
		}catch(IOException exception){
			LOGGER.error("Error al enviar la notificación");
		}
	}

	@PreRemove
	public void deleteUserAlertBefore(User user) {
		LOGGER.info("## ---------------------------------------------------------------------------->Ready to delete user: " + user.getName());
	}

	@PostRemove
	public void deleteUserAlertAfter(User user) {
		LOGGER.info("## ---------------------------------------------------------------------------->User deleted: " + user.getName());
		try
		{
			if(notification != null)
			{
				notification.addUser("Delete user : "+user.getName());
			}
			else
			{
				LOGGER.info("Websocket notificacion es null");
			}
		}catch(IOException exception){
			LOGGER.error("Error al enviar la notificación");
		}
	}

}
