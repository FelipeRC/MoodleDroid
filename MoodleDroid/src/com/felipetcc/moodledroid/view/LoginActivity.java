package com.felipetcc.moodledroid.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.felipetcc.moodledroid.R;
import com.felipetcc.moodledroid.adapter.UsuarioAdapter;
import com.felipetcc.moodledroid.db.DBHelper;
import com.felipetcc.moodledroid.db.RepositorioUsuario;
import com.felipetcc.moodledroid.handler.UsuarioHandler;
import com.felipetcc.moodledroid.model.Usuario;
import com.felipetcc.moodledroid.network.IRequestCallback;
import com.felipetcc.moodledroid.network.NetworkQueue;
import com.felipetcc.moodledroid.util.Constantes;

public class LoginActivity extends Activity {
	
	public static final String TAG = LoginActivity.class.getSimpleName();

	private EditText edtUsername;
	private EditText edtSenha;
	private CheckBox cbSalvarUsuario;
	private String username;
	private String senha;
	private List<Usuario> usuariosSalvos = null;
	private UsuarioAdapter usuarioAdapter;
	private ProgressDialog dialog;
	private String accessToken;
	
	private IRequestCallback<String> requestLoginCallback;
	private IRequestCallback<String> requestUserInfoCallback;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		Log.d(TAG, "Recuperando usuários salvos.");
		RepositorioUsuario repositorioUsuario = new RepositorioUsuario(
				new DBHelper(this));
		usuariosSalvos = repositorioUsuario.getUsuarios();

		
		if (usuariosSalvos != null && usuariosSalvos.size() > 0) {
			Log.d(TAG, "Carregando tela com lista de usuários salvos.");
			carregaTelaUsuariosSalvos();
			
		} else {
			Log.d(TAG, "Carregando tela para primeiro acesso.");
			carregaTelaAcessoPrincipal();
		}

		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);
		
		preparaRequestCallback();
	}

	/**
	 * Inicia a tela com uma lista de usuários salvos
	 */
	private void carregaTelaUsuariosSalvos() {
		setContentView(R.layout.usuarios_salvos_layout);
		ListView listLogin = (ListView) findViewById(R.id.usuarios_salvos_list);
		usuarioAdapter = new UsuarioAdapter(this, usuariosSalvos);
		listLogin.setAdapter(usuarioAdapter);
	}
	
	/**
	 * Inicia a tela com os campos username e passwords para serem preenchidos pelo usuário
	 */
	private void carregaTelaAcessoPrincipal() {
		setContentView(R.layout.login_layout);
		edtUsername = (EditText) findViewById(R.id.edtLogin);
		edtSenha = (EditText) findViewById(R.id.edtSenha);
		cbSalvarUsuario = (CheckBox) findViewById(R.id.cBLembrar);
	}
	
	/**
	 * inicializa os callbacks
	 */
	private void preparaRequestCallback(){
		
		requestLoginCallback = new IRequestCallback<String>() {

			@Override
			public void onRequestResponse(String response) {
				try {
					Log.d(TAG, "Convertendo o response para um JSONObject");
					JSONObject jsonObject = new JSONObject(response);
					Log.d(TAG, "Recuperando o token do json retornado.");
					accessToken = jsonObject.getString("token");
					Log.d(TAG, "Verificando se o json contem o token.");
					if(accessToken == null || accessToken.isEmpty()){
						dialog.hide();
						//TODO rever mensagem de erro
						Toast.makeText(getApplicationContext(), "Erro no login.", Toast.LENGTH_LONG).show();
					}else{
						Log.d(TAG, "Preparando a requisicao para buscar as informacoes do usuario.");
						Map<String, String> params = new HashMap<String, String>();
						params.put(Constantes.PARAM_TOKEN, accessToken);
						params.put(Constantes.PARAM_FUNCTION, Constantes.FUNCTION_GET_INFO_NAME);
						params.put(Constantes.PARAM_RESPONSE_FORMAT, Constantes.JSON_RESPONSE_FORMAT);
						Log.d(TAG, "Colocando requisicao na fila.");
						NetworkQueue.getInstance(getApplicationContext())
								.doStringRequestByPOST(Constantes.URL_REST_REQUEST, TAG, requestUserInfoCallback, params);
					}
				} catch (JSONException e) {
					Log.e(TAG, e.getMessage());
					dialog.hide();
				}
			}

			@Override
			public void onRequestError(Exception error) {
				Log.e(TAG, error.getMessage());
				dialog.hide();
			}
		};
		
		requestUserInfoCallback = new IRequestCallback<String>() {
			
			@Override
			public void onRequestResponse(String response) {
				try {
					Log.d(TAG, "Convertendo o response para um JSONObject");
					JSONObject result = new JSONObject(response);
					
					//TODO código parcial para chamar tela de home do sistema
					Intent intent = new Intent(LoginActivity.this,
							HomeActivity.class);
					Usuario usuario = new Usuario();
					try {
						usuario.setNome(result.getString("firstname"));
						usuario.setSobrenome(result.getString("lastname"));
						usuario.setSenha(senha);
						usuario.setLogin(username);
					} catch (JSONException e) {
						Log.e(TAG, e.getMessage());
					}
					intent.putExtra("usuario", usuario);

					startActivity(intent);
					
				} catch (JSONException e) {
					Log.e(TAG, e.getMessage());
				}
				dialog.hide();
			}
			
			@Override
			public void onRequestError(Exception error) {
				Log.e(TAG, error.getMessage());
				dialog.hide();
			}
		};
		
	}

	
	//-------------------LISTENERS-------------------
	
	public void clickAcessar(View v) {
		Log.d(TAG, "Recuperando valores dos campos da tela.");
		username = edtUsername.getText().toString();
		senha = edtSenha.getText().toString();

		Log.d(TAG, "Verificando se os campos estão preenchidos.");
		if (username == null || username.equals("") || senha == null
				|| senha.equals("")) {
			Toast.makeText(this, "Por favor, digite seus dados de acesso.",
					Toast.LENGTH_SHORT).show();
		} else {
			dialog = ProgressDialog.show(LoginActivity.this, "Aguarde",
					"Carregando usuário...");
			
			Log.d(TAG, "Preparando requisição para pegar o token e as informacoes do usuario.");
			Map<String, String> params = new HashMap<String, String>();
			params.put(Constantes.PARAM_SERVICE, Constantes.SERVICE_NAME);
			params.put(Constantes.PARAM_USERNAME, username);
			params.put(Constantes.PARAM_PASSWORD, senha);
			Log.d(TAG, "Colocando requisição na fila.");
			NetworkQueue.getInstance(getApplicationContext())
					.doStringRequestByPOST(Constantes.URL_GET_TOKEN, TAG, requestLoginCallback, params);
			
		}
	}
	
	
	//----------------------------------------------------------------------------------------
	//Código antigo daqui por diante
	public void btConfirmarPressed(View v) {

		LinearLayout linearLayout = (LinearLayout) v.getParent().getParent()
				.getParent();
		TextView textUserName = (TextView) linearLayout
				.findViewById(R.id.txtUserName);

		Usuario usuario = UsuarioHandler.pesquisarUsuarioSalvo(textUserName
				.getText().toString(), this);

		new DownloadJsonAsyncTask().execute(Constantes.URL_VERIFICA_USUARIO,
				usuario.getLogin(), usuario.getSenha(), "0", "1");

	}

	public void btdeletarPressed(View view) {

		LinearLayout linearLayout = (LinearLayout) view.getParent().getParent()
				.getParent();
		TextView textLogin = (TextView) linearLayout
				.findViewById(R.id.txtUserName);

		UsuarioHandler
				.removerUsuarioSalvo(textLogin.getText().toString(), this);

		onResume();
	}

	public void outroUsuario(View view) {

		setContentView(R.layout.login_layout_auxiliar);
		edtUsername = (EditText) findViewById(R.id.edtLogin);
		edtSenha = (EditText) findViewById(R.id.edtSenha);
		cbSalvarUsuario = (CheckBox) findViewById(R.id.cBLembrar);

	}

	

	public void clickSair(View v) {
		onBackPressed();
	}

	public void voltarPressed(View view) {
		carregaTelaUsuariosSalvos();
	}

	

	/*
	 * Classe interna Responsável por rodar em background e fazer o Download do
	 * arquivo Json
	 */
	class DownloadJsonAsyncTask extends AsyncTask<String, Void, JSONObject> {

		private ProgressDialog dialog;

		String flagSalvar = "";
		String login = "";
		String senha = "";

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(LoginActivity.this, "Aguarde",
					"Fazendo login...");
		}

		@Override
		protected JSONObject doInBackground(String... params) {

			login = params[1];
			senha = params[2];
			flagSalvar = params[3];
			String result = getRESTFileContent(params[0], login, senha,
					params[4]);

			Log.i("URL URL URL URL URL", params[0]);

			JSONObject jsonObject = null;

			if (result == null) {
				Log.e("LogandoIFMoodleDroid", "Falha ao acessar WS");
				return null;
			} else {
				Log.i("Result Retornado", result);
			}

			try {
				jsonObject = new JSONObject(result);

			} catch (Exception e) {
			}
			return jsonObject;
		}

		protected void onPostExecute(JSONObject result) {
			super.onPostExecute(result);
			dialog.dismiss();

			if (result != null) {

				Intent intent = new Intent(LoginActivity.this,
						HomeActivity.class);
				Usuario usuario = new Usuario();
				try {
					usuario.setNome(result.getString("nome"));
					usuario.setSobrenome(result.getString("sobrenome"));
					usuario.setSenha(result.getString("senha"));
					usuario.setLogin(result.getString("login"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				intent.putExtra("usuario", usuario);

				if (Integer.parseInt(flagSalvar) == 1) {
					Log.i("HORA DE SALVAR NO BANCO", "SALVAR");
					Usuario usuario2 = null;
					try {
						usuario2 = new Usuario(result.getString("login"),
								result.getString("senha"),
								result.getString("nome"),
								result.getString("sobrenome"));
						Log.i("SALVANDO NO BANCO", usuario2.getNome() + " "
								+ usuario2.getSobrenome());
					} catch (JSONException e) {
						e.printStackTrace();
					}
					RepositorioUsuario repositorioUsuario = new RepositorioUsuario(
							new DBHelper(LoginActivity.this));
					repositorioUsuario.inserir(usuario2);
				}

				startActivity(intent);
			} else {

				Toast.makeText(LoginActivity.this,
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

		public String getRESTFileContent(String url, String loginUsuario,
				String senhaUsuario, String flagUsuarioSalvo) {
			HttpClient httpclient = new DefaultHttpClient();

			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
			nameValuePairs.add(new BasicNameValuePair("login", loginUsuario));
			nameValuePairs.add(new BasicNameValuePair("senha", senhaUsuario));
			nameValuePairs.add(new BasicNameValuePair("flagDecriptacao",
					flagUsuarioSalvo));

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
