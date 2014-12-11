package com.felipetcc.moodledroid.util;

public interface Constantes {
	
	String DOMAIN_URL = "http://ifmoodledroidtcc.zz.vc/moodle/";
	
	String URL_GET_TOKEN = DOMAIN_URL + "login/token.php";
	
	String URL_REST_REQUEST = DOMAIN_URL + "webservice/rest/server.php";
	
	String SERVICE_NAME =  "moodle_droid_service";
	
	String FUNCTION_GET_INFO_NAME =  "core_webservice_get_site_info";
	
	String JSON_RESPONSE_FORMAT = "json";
	
	String PARAM_USERNAME = "username";
	
	String PARAM_PASSWORD = "password";
	
	String PARAM_SERVICE = "service";
	
	String PARAM_TOKEN = "wstoken";
	
	String PARAM_FUNCTION = "wsfunction";
	
	String PARAM_RESPONSE_FORMAT = "moodlewsrestformat";
	
	
	
	
	
	
	
	//------------------------------------------------------

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
