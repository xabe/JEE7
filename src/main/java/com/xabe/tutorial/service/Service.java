package com.xabe.tutorial.service;

import java.io.Serializable;
import java.util.List;

import com.xabe.tutorial.model.Model;

/**
 * Clase que tiene lo métodos genericos de los ejb
 * @author Chabir
 *
 */
public abstract class Service<T extends Model> implements Serializable{
	private static final long serialVersionUID = 1L;

	public abstract void add(T t);
	
	public abstract T update(T t);
	
	public abstract void delete(T t);
	
	public abstract List<T> getAll();
	
	public abstract T getId(Integer id);
	
}
