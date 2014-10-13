package services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import entities.Postagem;
import entities.Usuario;
import fachada.Fachada;

@Path("/listener")
public class ListenerServicePost {
	
	@GET
	@Path("teste")
	public String test() {
		return "teste"; 
	}

	@Path("/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Usuario fazLogin(@FormParam("login") String login,
			@FormParam("senha") String senha,
			@FormParam("flagEncriptacao") String flagEncriptacao,
			@Context HttpServletResponse servletResponse) throws IOException {
		Usuario usuario = Fachada.getInstance().retornaUsuario(login, senha,
				flagEncriptacao);

		return usuario;

	}

	@Path("/retornaNoticias")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ArrayList<Postagem> retornaNoticias(
			@FormParam("login") String login, @FormParam("senha") String senha,
			@FormParam("flagEncriptacao") String flagEncriptacao,
			@Context HttpServletResponse servletResponse) throws IOException {
		
		return Fachada.getInstance().retornaUltimasNoticias(login, senha,
				flagEncriptacao);

	}

}
