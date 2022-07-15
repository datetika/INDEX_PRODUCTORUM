package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.data.model.SharedUser;
import com.dev.mrvazguen.indexproductorum.data.model.Usuari;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class SharedUserAdapter extends RecyclerView.Adapter<SharedUserAdapter.SharedUserViewHolder>{
    private  ArrayList<SharedUser> usuaris;
    private  int nItems=0;
    public  SharedUserAdapter(ArrayList<SharedUser> usuaris){
        this.usuaris =usuaris;
        nItems = usuaris.size();
    }

    @NonNull
    @Override
    public SharedUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shared_user_item,parent,false);
        return new SharedUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SharedUserViewHolder holder, int itemPosition) {
          holder.getTextViewUserName().setText(usuaris.get(itemPosition).getNombre());
          holder.getTextViewUserPageIndicator().setText(String.valueOf(itemPosition+1)+"/"+String.valueOf(nItems));
    }


    @Override
    public int getItemCount() {
        return nItems;
    }

    public  class SharedUserViewHolder extends  RecyclerView.ViewHolder {
         TextView tvUserName;
         TextView tvPageIndicator ;
        public SharedUserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvNombreUsuari);
            tvPageIndicator = itemView.findViewById(R.id.tvUSERPageIndicator);
        }

        public TextView getTextViewUserName(){return tvUserName;}
        public TextView getTextViewUserPageIndicator(){return tvPageIndicator;}
    };
}
