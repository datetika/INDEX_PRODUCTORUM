package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.model.Articulo;

import java.util.ArrayList;

public class ListaArticuloAdapter extends RecyclerView.Adapter<ListaArticuloAdapter.LlistarArticuloViewHolder> {
    private Articulo articulo;
    private ArrayList<Articulo>articulos;
    private  int nItems=0;

    /*
    public ListaArticuloAdapter(String  dato) {
        articulo = new Articulo(dato);
        nItems++;
    }
*/
   public  ListaArticuloAdapter(ArrayList<Articulo> articulos){
        this.articulos =articulos;
        nItems = articulos.size();
   }

    @NonNull
    @Override
    public LlistarArticuloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_articulo_item,parent,false);
        return new LlistarArticuloViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LlistarArticuloViewHolder holder, int position) {
        //holder.getView().setText( articulo.getNombre());
        holder.getView().setText(articulos.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return nItems;
    }


    public  class LlistarArticuloViewHolder extends  RecyclerView.ViewHolder {
        TextView title;
        public LlistarArticuloViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvNombre);
        }
        public TextView getView(){return title;}
    };
}
