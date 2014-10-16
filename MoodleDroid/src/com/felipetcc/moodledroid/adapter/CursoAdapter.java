package com.felipetcc.moodledroid.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.felipetcc.moodledroid.R;
import com.felipetcc.moodledroid.model.Curso;

public class CursoAdapter extends BaseAdapter{
	
	private Context ctx;
	private List<Curso> cursos;
	
	public CursoAdapter(Context ctx, List<Curso> cursos) {

		this.ctx = ctx;
		this.cursos = cursos;
	}

	@Override
	public int getCount() {
		return cursos.size();
	}

	@Override
	public Object getItem(int index) {
		return cursos.get(index);
	}

	@Override
	public long getItemId(int index) {
		return cursos.get(index).getId();
	}

	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		
		ViewHolder holder = null;
		
		Curso curso = cursos.get(index);
		
		if(view == null){
			view = LayoutInflater.from(ctx).inflate(R.layout.cursos_item_layout, null);
			
			holder = new ViewHolder();
			holder.txtNomeCurso = (TextView) view.findViewById(R.id.txtNomeCurso);
			
			view.setTag(holder);
		}else{
			holder = (ViewHolder)view.getTag();
		}
		
		String nomeCurso = curso.getNome();
		holder.txtNomeCurso.setText(nomeCurso);
		
		return view;
	}
	
	static class ViewHolder {
		   TextView txtNomeCurso;
	}

}
