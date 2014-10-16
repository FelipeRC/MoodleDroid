package com.felipetcc.moodledroid.model;

import java.io.Serializable;

public class Noticia implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7753412526315334688L;
	
	private long id;
	private String titulo;
	private String autor;
	private String mensagem;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
