package com.felipetcc.moodledroid.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.felipetcc.moodledroid.R;
import com.felipetcc.moodledroid.model.Usuario;

public class PerfilActivity extends Activity {
	
	private Usuario usuario;
	private EditText nomeUsuario;
	private EditText sobreNomeUsuario;
	private EditText email;
	private EditText cidade;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setContentView(R.layout.perfil_layout);
		
		
		usuario = (Usuario) getIntent().getExtras().getSerializable("usuario");
		
		nomeUsuario = (EditText) findViewById(R.id.edtPerfilNome);
		sobreNomeUsuario = (EditText) findViewById(R.id.edtPerfilSobrenome);
		email = (EditText) findViewById(R.id.edtPerfilEmail);
		cidade = (EditText) findViewById(R.id.edtPerfilCidade);
		
		nomeUsuario.setText(usuario.getNome());
		sobreNomeUsuario.setText(usuario.getSobrenome());
		email.setText(usuario.getEmail());
		cidade.setText(usuario.getCidade());
		
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		
	}

}
