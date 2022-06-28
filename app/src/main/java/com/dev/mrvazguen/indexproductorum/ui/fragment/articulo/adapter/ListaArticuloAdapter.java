package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.model.Articulo;

public class ListaArticuloAdapter extends RecyclerView.Adapter<ListaArticuloAdapter.LlistarArticuloViewHolder> {
    private Articulo articulo;
    private  int nItems=0;
    public ListaArticuloAdapter(String  dato) {
        articulo = new Articulo(dato);
        nItems++;
    }


    @NonNull
    @Override
    public LlistarArticuloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_articulo_item,parent,false);
        return new LlistarArticuloViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LlistarArticuloViewHolder holder, int position) {

        holder.getView().setText( articulo.getNombre());

    }

    @Override
    public int getItemCount() {
        return nItems;
    }


    public  class LlistarArticuloViewHolder extends  RecyclerView.ViewHolder {
        TextView title;
        public LlistarArticuloViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvListItem);
        }
        public TextView getView(){return title;}
    };
}
