package com.felipetcc.moodledroid.model;

import java.io.Serializable;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 8801145374343038929L;
	
	
	private long id;
	private String login;
	private String senha;
	private String nome;
	private String sobrenome;
	
	//---------------------------------------
	//Atributos carregados apenas para perfil
	private String email;
	private String cidade;
	
	public Usuario(long id, String login, String senha,String nome, String sobrenome) {
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}
	public Usuario(String login, String senha, String nome, String sobrenome){
		this(0, login, senha,nome,sobrenome);
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public long getId() {
		return id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Usuario(){
		
	}
	
}
