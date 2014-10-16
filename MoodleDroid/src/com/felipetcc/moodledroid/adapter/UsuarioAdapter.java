package com.felipetcc.moodledroid.adapter;


import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.felipetcc.moodledroid.R;
import com.felipetcc.moodledroid.model.Usuario;

public class UsuarioAdapter extends BaseAdapter{
	
	private Context ctx;
	private List<Usuario> usuarios;

	public UsuarioAdapter(Context ctx, List<Usuario> usuariosSalvos) {
		this.ctx = ctx;
		this.usuarios = usuariosSalvos;
	}

	public int getCount() {
		return usuarios.size();
	}

	public Object getItem(int index) {
		return usuarios.get(index);
	}

	public long getItemId(int index) {
		return usuarios.get(index).getId();
	}

	public View getView(int index, View view, ViewGroup viewGroup) {
		ViewHolder holder;

		   Usuario u = usuarios.get(index);

		   if (view == null){
			   view = LayoutInflater.from(ctx).inflate(R.layout.item_login_salvo, null);
		 
		      holder = new ViewHolder();
		      holder.txtNome = (TextView) 
		    		  view.findViewById(R.id.txtNomeUsuario);
		      holder.txtLogin = (TextView) view.findViewById(R.id.txtLogin);
		      
		      
		      view.setTag(holder);
		   } else {
		      holder = (ViewHolder)view.getTag();
		   }
		   
		   String nome = u.getNome()+ " "+ u.getSobrenome();
		   String login = u.getLogin();
		   if(nome.length() > 12){
			   nome = nome.substring(0, 12);
		   }
		   if(login.length() > 12){
			   login = login.substring(0, 13);
		   }
		   
		   holder.txtNome.setText(nome);
		   holder.txtLogin.setText(login);
		  Log.i("TESTEEEEEEEEE", Integer.toString(usuarios.size()));
		   

		   return view;
	}

	static class ViewHolder {
		   TextView txtNome;
		   TextView txtLogin;
	}
	
}
