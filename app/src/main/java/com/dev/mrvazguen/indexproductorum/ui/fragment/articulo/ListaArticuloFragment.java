package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo;

import android.icu.util.MeasureUnit;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.data.model.Usuari;
import com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager.ArticuloManagerDB;
import com.dev.mrvazguen.indexproductorum.data.repository.iTaskNotification;
import com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter.ListaArticuloAdapter;
import com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter.SharedUserAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ListaArticuloFragment extends Fragment  implements   NavigationView.OnNavigationItemSelectedListener {
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
        setHasOptionsMenu(true);

        Toolbar toolbar = (Toolbar)this.getActivity().findViewById(R.id.toolbar);
        this.getActivity().setActionBar(toolbar);



        NavigationView mNavigationView = (NavigationView) this.getActivity().findViewById(R.id.navView);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }

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



    //Menu
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        return super.onOptionsItemSelected(item);
    }


    ///NavigationView
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.btnLogout){
            getActivity().finish();
            Log.d("close_app","closed");
            return  true;
        }
        Log.d("close_app","noDetected");
        return false;
    }

}