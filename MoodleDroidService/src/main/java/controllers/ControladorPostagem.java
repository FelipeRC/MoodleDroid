package controllers;

import java.util.ArrayList;

import dao.PostagemDAOFactory;
import entities.Postagem;

public class ControladorPostagem {

	public ControladorPostagem() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Postagem> retornaUltimasNoticias(){
		return PostagemDAOFactory.getInstance().getDAO()
		.retornaUltimasNoticias();
	}
	
}
