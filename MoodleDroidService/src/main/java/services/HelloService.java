package services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/hello")
public class HelloService {
	
	final static String DOMAIN_URL = "http://ifmoodledroidtcc.zz.vc/moodle/";
	final static String USERNAME = "felipe";
	final static String PASSWORD = "Felipe.1";
	final static String FUNCTION_TOKEN =  "moodle_droid_service";
	final static String FUNCTION_INFO =  "core_webservice_get_site_info";
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {

		String response = "Erro";
		
		try {
			
			String requestTokenUrl = DOMAIN_URL + "login/token.php?username=" + USERNAME
					+ "&password=" + PASSWORD + "&service=" + FUNCTION_TOKEN;
			String tokenJSONString = doGet(requestTokenUrl);
			JSONObject tokenJSON = new JSONObject(tokenJSONString);
			String tokenString = tokenJSON.getString("token");
			
			String requestInfoURL = DOMAIN_URL + "webservice/rest/server.php?wstoken=" + tokenString
					+ "&wsfunction=" + FUNCTION_INFO;
			String infoJSONString = doGet(requestInfoURL); 
			
			response = infoJSONString;
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;

	}
	
	public String doPost(String urlString) {
		return urlString;
	}

	public static String doGet(String urlString) throws IOException {

		BufferedReader br = null;
		StringBuffer response = new StringBuffer(10000);
		try {

			URL url = new URL(urlString);

			HttpURLConnection connection = null;
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Request-Method", "GET");
			connection.setDoInput(true);
			connection.setDoOutput(false);

			connection.connect();

			br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String s = "";
			while (null != ((s = br.readLine()))) {
				response.append(s);
			}

			System.out.println("URL acessada: " + urlString);
			System.out.println();
			System.out.println(response);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return response.toString();
	}
}