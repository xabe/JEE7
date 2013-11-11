package com.xabe.tutorial.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xabe.tutorial.model.Model;

/**
 * Clase que tiene lo métodos genericos de los ejb
 * @author Chabir
 *
 */
public abstract class Service<T extends Model> implements Serializable{
	private static final long serialVersionUID = 1L;
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

	public abstract void add(T t);
	
	public abstract T update(T t);
	
	public abstract void delete(T t);
	
	public abstract List<T> getAll();
	
	public abstract T getId(Integer id);
	
}
