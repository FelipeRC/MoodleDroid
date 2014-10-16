package com.felipetcc.moodledroid.adapter;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.felipetcc.moodledroid.R;
import com.felipetcc.moodledroid.model.Noticia;

public class NoticiasAdapter extends BaseAdapter{

	private Context ctx;
	private List<Noticia> noticias;
	
	public NoticiasAdapter(Context ctx, List<Noticia> noticias) {
		this.ctx = ctx;
		this.noticias = noticias;
	}

	@Override
	public int getCount() {
		return noticias.size();
	}

	@Override
	public Object getItem(int index) {
		return noticias.get(index);
	}

	@Override
	public long getItemId(int index) {
		return noticias.get(index).getId();
	}

	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		ViewHolder holder;
		
		Noticia noticia = noticias.get(index);
		
		if(view == null){
			view = LayoutInflater.from(ctx).inflate(R.layout.noticias_item_layout, null);
			
			holder = new ViewHolder();
			holder.txtTituloNoticia = (TextView) view.findViewById(R.id.txtTituloNoticia);
			holder.txtAutorNoticia = (TextView) view.findViewById(R.id.txtAutorNoticia);
			holder.txtMsgNoticia = (TextView) view.findViewById(R.id.txtMsgNoticia);
			
			view.setTag(holder);
			
		}else{
			holder = (ViewHolder)view.getTag();
		}
		
		String titulo = noticia.getTitulo();
		String autor = noticia.getAutor();
		Spanned mensagem = Html.fromHtml(noticia.getMensagem());
		
		holder.txtTituloNoticia.setText(titulo);
		holder.txtAutorNoticia.setText(autor);
		holder.txtMsgNoticia.setText(mensagem);
		
		return view;
	}

	static class ViewHolder {
		   TextView txtTituloNoticia;
		   TextView txtAutorNoticia;
		   TextView txtMsgNoticia;
	}
	
}
