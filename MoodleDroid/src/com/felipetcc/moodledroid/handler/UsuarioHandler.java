package com.felipetcc.moodledroid.handler;

import android.content.Context;

import com.felipetcc.moodledroid.db.DBHelper;
import com.felipetcc.moodledroid.db.RepositorioUsuario;
import com.felipetcc.moodledroid.model.Usuario;

public class UsuarioHandler {

	
	
	public static void removerUsuarioSalvo(String login, Context context){
		
		RepositorioUsuario repositorioUsuario = new RepositorioUsuario(
				new DBHelper(context));	 
		repositorioUsuario.remover(login);
		
	}

	public static Usuario pesquisarUsuarioSalvo(String login, Context context) {

		RepositorioUsuario repositorioUsuario = new RepositorioUsuario(
				new DBHelper(context));	 
		
		return repositorioUsuario.pesquisar(login);
	}
	
	
}
