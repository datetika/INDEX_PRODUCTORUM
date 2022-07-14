package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.utils.GlobarArgs;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;

/*
@autor : PROYECTO FINAL ANDROID MOBILE DEVELOPER
 */

public class ListaArticuloAdapter extends RecyclerView.Adapter<ListaArticuloAdapter.LlistarArticuloViewHolder> {
    private Articulo articulo;
    private ArrayList <Articulo> articulos;
    private  int nItems=0;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    /*
    public ListaArticuloAdapter(String  dato) {
        articulo = new Articulo(dato);
        nItems++;
    }
   */
   public  ListaArticuloAdapter(ArrayList <Articulo> articulos){
        this.articulos =articulos;
        nItems = articulos.size();
   }

   //TODO recylerview  onclick show in new windows the result
    @NonNull
    @Override
    public LlistarArticuloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       ///region TODO Article onClick event show the detail of article
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_articulo_item,parent,false);
       view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.d("Item_recylerView_Articulo",((TextView)v.findViewById(R.id.tvNombre)).getText().toString());
               Bundle bundle = new Bundle();

               String posicion = (String) v.findViewById(R.id.tvNombre).getTag();
               String nombreArticulo = articulos.get(Integer.parseInt(posicion)).getNombre();  //((TextView)v.findViewById(R.id.tvNombre)).getText().toString();
               String descripcion = articulos.get(Integer.parseInt(posicion)).getDescripcion();
               double precio = articulos.get(Integer.parseInt(posicion)).getPrecio();
               String categoria="";
               for(String c : articulos.get(Integer.parseInt(posicion)).getCategoria()){
                   categoria+=c+" ";
               }

               bundle.putString(GlobarArgs.articuloEnum.nombre.toString(),nombreArticulo);
               bundle.putString(GlobarArgs.articuloEnum.descripcion.toString(),descripcion);
               bundle.putDouble(GlobarArgs.articuloEnum.precio.toString(),precio);
               bundle.putString(GlobarArgs.articuloEnum.categoria.toString(),categoria);

               Navigation.findNavController(view).navigate(R.id.mostrarArticuloFragment, bundle);

           }
       });
       ///endregion
        return new LlistarArticuloViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull LlistarArticuloViewHolder holder, int position) {
        //holder.getView().setText( articulo.getNombre());
            holder.getTextView().setText(articulos.get(position).getNombre());
            holder.getTextView().setTag(String.valueOf( position));//We put the position in the tag
    }

    @Override
    public int getItemCount() {
        return nItems;
    }

    public void deteleItem(int removedIndex) {
       //Firebase delete
         articulos.remove(removedIndex);
         this.notifyItemRemoved(removedIndex);
         nItems = articulos.size();//update size

    }

    public  class LlistarArticuloViewHolder extends  RecyclerView.ViewHolder {
        TextView title;

        public LlistarArticuloViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvNombre);
        }
        public TextView getTextView(){return title;}
    };
}
