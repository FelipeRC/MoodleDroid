package controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import security.Encrypter;
import services.ListenerServicePost;
import Exceptions.EncryptorException;
import dao.UsuarioDAOFactory;
import entities.Usuario;

public class ControladorUsuario {

	private static final String FLAG_ENCRIPT_DECRIPT_ON = "1";

	public ControladorUsuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario retornaUsuario(String login, String senha,
			String flagEncriptacao) {
		
		Usuario usuarioTemp = new Usuario();
		usuarioTemp.setLogin(login);

		if (flagEncriptacao.equals(FLAG_ENCRIPT_DECRIPT_ON)) {

			try {
				usuarioTemp.setSenha(Encrypter
						.decriptar(Encrypter.CHAVE, senha));
			} catch (EncryptorException ex) {
				Logger.getLogger(ListenerServicePost.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		} else {
			usuarioTemp.setSenha(senha);
		}
		Usuario usuario = UsuarioDAOFactory.getInstance().getDAO()
				.verifyUsuario(usuarioTemp);

		try {
			usuario.setSenha(Encrypter.encriptar(Encrypter.CHAVE, senha));
		} catch (EncryptorException ex) {
			Logger.getLogger(ListenerServicePost.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		return usuario;
	}

}
