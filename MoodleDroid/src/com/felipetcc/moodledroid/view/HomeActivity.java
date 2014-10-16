package com.felipetcc.moodledroid.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.felipetcc.moodledroid.R;
import com.felipetcc.moodledroid.model.Curso;
import com.felipetcc.moodledroid.model.Noticia;
import com.felipetcc.moodledroid.model.Usuario;
import com.felipetcc.moodledroid.util.Constantes;

public class HomeActivity extends Activity {

	private Usuario usuario;
	private TextView nomeUsuario;
	String url = "";
	
	private final String BOTAO_NOTICIAS = "1";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		setContentView(R.layout.home_layout);
		
		usuario = (Usuario) getIntent().getExtras().getSerializable("usuario");
		
		nomeUsuario = (TextView) findViewById(R.id.nomeUsuario);
		nomeUsuario.setText(usuario.getNome());
	}

	public void clickNoticias(View v){
		url = Constantes.URL_VERIFICA_NOTICIAS;
		new DownloadJsonAsyncTask().execute(url, usuario.getLogin(), usuario.getSenha(), "0", "1");
	}
	public void clickPerfil(View v){
		url = Constantes.URL_VISUALIZA_PERFIL;
		new DownloadJsonAsyncTask().execute(url, usuario.getLogin(), usuario.getSenha(), "0", "1");
	}
	public void clickCursos(View v){
		url = Constantes.URL_CURSOS;
		new DownloadJsonAsyncTask().execute(url, usuario.getLogin(), usuario.getSenha(), "0", "1");
	}
	class DownloadJsonAsyncTask extends AsyncTask<String, Void, JSONObject> {

		private ProgressDialog dialog;
		
		
		String flagSalvar = "";
		String login = "";
		String senha = "";
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if(url.equals(Constantes.URL_VERIFICA_NOTICIAS)){
				dialog = ProgressDialog.show(HomeActivity.this, "Aguarde",
					"Buscando Notícias Recentes...");
			}else if(url.equals(Constantes.URL_CURSOS)){
				dialog = ProgressDialog.show(HomeActivity.this, "Aguarde",
						"Buscando Cursos...");
			}else if(url.equals(Constantes.URL_VISUALIZA_PERFIL)){
				dialog = ProgressDialog.show(HomeActivity.this, "Aguarde",
						"Buscando Informações do perfil...");
			}
		}

		@Override
		protected JSONObject doInBackground(String... params) {

			login = params[1];
			senha = params[2];
			flagSalvar = params[3];

			String result = getRESTFileContent(url, login, senha, params[4]);
			

			Log.i("URL URL URL URL URL", params[0]);

			JSONObject jsonObject = null;

			if (result == null) {
				Log.e("HomeActivity", "Falha ao acessar WS");
				return null;
			} else {
				Log.i("Result Retornado", result);
			}

			try {
				jsonObject = new JSONObject(result);

			} catch (Exception e) {
				Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
			}
			return jsonObject;
		}

		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();

			
			if (result != null) {
				if(url.equals(Constantes.URL_VERIFICA_NOTICIAS)){
					Intent intent = new Intent(HomeActivity.this,
							NoticiasActivity.class);
					JSONArray arrayPostagens;
					ArrayList<Noticia> listNoticias = new ArrayList<Noticia>();
					Noticia noticiaRetornada;
					try {
						arrayPostagens = result.getJSONArray("postagem");
						
						for(int i = 0; i < arrayPostagens.length(); i++){
							JSONObject postagem = arrayPostagens.getJSONObject(i);
							noticiaRetornada = new Noticia();
							
							noticiaRetornada.setId(postagem.getLong("id"));
							noticiaRetornada.setTitulo(postagem.getString("titulo"));
							noticiaRetornada.setMensagem(postagem.getString("mensagem"));
							noticiaRetornada.setAutor(postagem.getString("autor"));
							
							listNoticias.add(noticiaRetornada);
						}
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					intent.putExtra("usuario", usuario);
					intent.putExtra("listNoticias", listNoticias);
					startActivity(intent);
				}else if(url.equals(Constantes.URL_CURSOS)){
					
					Intent intent = new Intent(HomeActivity.this,
							CursosActivity.class);
					JSONArray arrayCursos;
					ArrayList<Curso> listCursos = new ArrayList<Curso>();
					Curso cursoRetornado;
					
					try {
						arrayCursos = result.getJSONArray("curso");
						
						for(int i = 0; i < arrayCursos.length(); i++){
							JSONObject curso = arrayCursos.getJSONObject(i);
							cursoRetornado = new Curso();
							
							cursoRetornado.setId(curso.getLong("id"));
							cursoRetornado.setNome(curso.getString("nome"));
							
							listCursos.add(cursoRetornado);
						}
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					intent.putExtra("usuario", usuario);
					intent.putExtra("listCursos", listCursos);
					startActivity(intent);
					
				}else if(url.equals(Constantes.URL_VISUALIZA_PERFIL)){
					

					Intent intent = new Intent(HomeActivity.this,
							PerfilActivity.class);
					Usuario usuario = new Usuario();
					try {
						usuario.setNome(result.getString("nome"));
						usuario.setSobrenome(result.getString("sobrenome"));
						usuario.setEmail(result.getString("email"));
						usuario.setCidade(result.getString("city"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					intent.putExtra("usuario", usuario);

					startActivity(intent);
				}
			} else {

				Toast.makeText(HomeActivity.this,
						"Dados de acesso incorretos.", Toast.LENGTH_SHORT)
						.show();
			}
		}
		
		private String toString(InputStream is) throws IOException {

			byte[] bytes = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int lidos;
			while ((lidos = is.read(bytes)) > 0) {
				baos.write(bytes, 0, lidos);
			}
			return new String(baos.toByteArray());
		}
		
		public String getRESTFileContent(String url, String loginUsuario, String senhaUsuario, String flagUsuarioSalvo) {
			HttpClient httpclient = new DefaultHttpClient();
			
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
			nameValuePairs.add(new BasicNameValuePair("login", loginUsuario));
			nameValuePairs.add(new BasicNameValuePair("senha", senhaUsuario));
			nameValuePairs.add(new BasicNameValuePair("flagDecriptacao", flagUsuarioSalvo));
			
			if(url.equals(Constantes.URL_VISUALIZA_PERFIL)){
				nameValuePairs.add(new BasicNameValuePair("loginPerfil", loginUsuario));
			}

			try {
				httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httpPost);

				HttpEntity entity = response.getEntity();

				if (entity != null) {
					InputStream instream = entity.getContent();
					String result = toString(instream);

					instream.close();
					return result;
				}
			} catch (Exception e) {
				Log.e("IFMoodleDROID", "Falha ao acessar Web service", e);
			}
			return null;
		}
	}
}
