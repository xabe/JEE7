package com.xabe.tutorial.ws.user;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.JSONP;

import com.xabe.tutorial.model.user.User;
import com.xabe.tutorial.service.user.UserEJB;
import com.xabe.tutorial.ws.BaseRest;

@Path("/ws")
@Stateless
public class UserWs extends BaseRest {

	@EJB
	private UserEJB userEJB;

	@PostConstruct
	public void init() {
		logger.info("Servicio rest de user");
		createUser("pepe");
	}
	
	private void createUser(String name){
		User user = new User();
		user.setName(name);		
		user.setUsername(name+new Date().getTime());
		userEJB.add(user);		
	}

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUser() {
		return userEJB.getAll();
	}
	
	@Path("/add")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAdd(@DefaultValue("jose") @QueryParam("name") String name) {
		createUser(name);
		return userEJB.getAll();
	}
	
	@Path("/update")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUpdate(@DefaultValue("pepe") @QueryParam("name") String name) {
		User user = userEJB.getUser(name);
		if(user != null)
		{
			user.setEmail("algo");
			userEJB.update(user);
		}
		return userEJB.getAll();
	}
	
	@Path("/delete")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getDelete(@DefaultValue("pepe") @QueryParam("name") String name) {
		User user = userEJB.getUser(name);
		if(user != null)
		{
			userEJB.delete(user);
		}
		return userEJB.getAll();
	}
	
	@GET
	@Path("/jsonp")
	@JSONP
	@Produces({"application/json", "application/javascript"})
	public List<User> getAllJsonp(){
		logger.info("Obtiene todos los registro de Prueba de jsonp");
		return userEJB.getAll();
	}

}
