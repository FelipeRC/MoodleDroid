package com.felipetcc.moodledroid.util;

public interface Constantes {

	String IP_SERVIDOR = "192.168.0.11";
	
	String PORTA_SERVIDOR = "8080";
	
	String URL_VERIFICA_USUARIO = "http://" + IP_SERVIDOR + ":"
			+ PORTA_SERVIDOR + "/MoodleDroidServices/rest/listener/login/";
	
	String URL_VERIFICA_NOTICIAS = "http://" + IP_SERVIDOR + ":"
			+ PORTA_SERVIDOR + "/MoodleDroidServices/rest/listener/retornaNoticias/";
	
	String URL_CURSOS = "http://" + IP_SERVIDOR + ":"
			+ PORTA_SERVIDOR + "/MoodleDroidServices/rest/listener/verificarCursos/";
	
	String URL_VISUALIZA_PERFIL = "http://" + IP_SERVIDOR + ":"
			+ PORTA_SERVIDOR + "/MoodleDroidServices/rest/listener/consultarPerfil/";
	
}
