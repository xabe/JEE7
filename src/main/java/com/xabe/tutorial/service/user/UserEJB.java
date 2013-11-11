package com.xabe.tutorial.service.user;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.xabe.tutorial.model.user.User;
import com.xabe.tutorial.service.Service;

@Stateless
public class UserEJB extends Service<User>{
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="websocketUnit")
	private EntityManager entityManager;


	public void add(User t) {
		try
		{
		entityManager.persist(t);
		entityManager.flush();
		}catch(Exception e){
			LOGGER.error("Error al hacer la insercion : "+e.getMessage());
			e.printStackTrace();
		}
	}

	public void delete(User t) {
		t = entityManager.merge(t);
		entityManager.remove(t);
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		Query query = entityManager.createNamedQuery("User.findAll", User.class);
		return (List<User>) query.getResultList();
	}

	public User getUser(String name) {
		Query query = entityManager.createNamedQuery("User.findByName", User.class).setParameter("name", name);
        return  (User) query.getSingleResult();
	}
	
	@Override
	public User getId(Integer id) {
		Query query = entityManager.createQuery("Select p from User p where p.id = ?1").setParameter(1, id);
		return (User) query.getSingleResult();
	}

	public User update(User t) {
		User user = entityManager.merge(t);
		entityManager.flush();
		return user;
	}

}
