package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.data.model.Usuari;
import com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager.ArticuloManagerDB;
import com.dev.mrvazguen.indexproductorum.data.repository.iTaskNotification;
import com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter.ListaArticuloAdapter;
import com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter.SharedUserAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaArticuloFragment extends Fragment {
    ArrayList<Articulo>articulos;
    ArrayList<Usuari>usuaris;
    private RecyclerView recyclerViewArticles;
    private RecyclerView recyclerViewSharedUser;
    private  ListaArticuloAdapter adapterArticles;
    private SharedUserAdapter adapterSharedUser;

    public  iTaskNotification<Articulo> iTaskNotification;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_lista_articulos, container, false);

        ///region dades Article
        ArticuloManagerDB articuloManagerDB = new ArticuloManagerDB("Test/prueba");

        View viewListaArticle = inflater.inflate(R.layout.fragment_lista_articulos, container, false);

        articulos = new ArrayList<>();
        usuaris = new ArrayList<>();
        usuaris.add(new Usuari("test@gmail.com","Fulanito"));
        usuaris.add(new Usuari("hola@gmail.com","Menganito"));

        //Cargar los datos de FirestoreArticulo
        iTaskNotification = new iTaskNotification<Articulo>(){
            @Override
            public void OnSucces(List<Articulo> lista) {
                articulos= (ArrayList<Articulo>) lista;
                if(articulos.size()==0)
                    articulos.add(new Articulo("EMPTY_FIELD"));
                adapterArticles= new ListaArticuloAdapter(articulos);
                recyclerViewArticles.setAdapter(adapterArticles);
            }

            @Override
            public void OnFail(String msg) {
               Log.d("ArticuloManagerDB",msg);
                articulos.add(new Articulo("Empty_DATE"));
                adapterArticles= new ListaArticuloAdapter(articulos);
                recyclerViewArticles.setAdapter(adapterArticles);
            }
        };

        articuloManagerDB.readRealtimeListener(iTaskNotification);

        Log.e("ListaArticulosFragment","Array lista articulos size: " + articulos.size());

        // Add the following lines to create RecyclerView
        recyclerViewArticles = viewListaArticle.findViewById(R.id.recyclerViewItemArticulos);
        recyclerViewArticles.setHasFixedSize(true);
        recyclerViewArticles.setLayoutManager(new LinearLayoutManager(viewListaArticle.getContext()));


        //TODO recylerview UserSHared
        recyclerViewSharedUser = viewListaArticle.findViewById(R.id.recyclerViewSharedUsers);
        recyclerViewSharedUser.setHasFixedSize(true);
        recyclerViewSharedUser.setLayoutManager(new LinearLayoutManager(viewListaArticle.getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterSharedUser= new SharedUserAdapter(usuaris);//Set date
        recyclerViewSharedUser.setAdapter(adapterSharedUser);


        viewListaArticle.findViewById(R.id.floatingActionButtonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(
                        R.id.action_listaArticuloFragment_to_agregarArticuloFragment);
            }
        });


        viewListaArticle.findViewById(R.id.btnManageSharedUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(
                        R.id.action_listaArticuloFragment_to_sharedUserFragment);
            }
        });
         //endregion
        return viewListaArticle;
    }


    ///region
    public void ListeningTripList(){

    }
    ///enregion
}