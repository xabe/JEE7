package com.xabe.tutorial.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que contiene los metodos comunes a los modelos
 * @author Chabir
 *
 */
@XmlRootElement
public abstract class Model implements Serializable{
	
    private static final long serialVersionUID = 1L;
}
