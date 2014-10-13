package fachada;

import java.util.ArrayList;

import controllers.ControladorPostagem;
import controllers.ControladorUsuario;
import entities.Postagem;
import entities.Usuario;

public class Fachada {

	private static Fachada fachada;
	private ControladorUsuario controladorUsuario;
	private ControladorPostagem controladorPostagem;
	
	public Fachada() {
		controladorUsuario = new ControladorUsuario();
		controladorPostagem = new ControladorPostagem();
	}
	
	
	public static Fachada getInstance(){
		
		if(fachada == null){
			fachada = new Fachada();
		}
		return fachada;
	}
	
	public Usuario retornaUsuario(String login, String senha,
			String flagEncriptacao) {
		return controladorUsuario.retornaUsuario(login, senha, flagEncriptacao);
	}
	
	public ArrayList<Postagem> retornaUltimasNoticias(String login, String senha,
			String flagEncriptacao){
		
		Usuario usuarioLogado = retornaUsuario(login, senha, flagEncriptacao);
		
		if(usuarioLogado!= null){
			return controladorPostagem.retornaUltimasNoticias();
		}
		
		return null;
	}
	
}
