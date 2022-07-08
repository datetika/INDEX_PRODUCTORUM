package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.data.model.Usuari;
import com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager.ArticuloManagerDB;
import com.dev.mrvazguen.indexproductorum.data.repository.iTaskNotification;
import com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter.ListaArticuloAdapter;
import com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter.SharedUserAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ListaArticuloFragment extends Fragment   {
    ArrayList<Articulo>articulos;
    ArrayList<Usuari>usuaris;
    private RecyclerView recyclerViewArticles;
    private RecyclerView recyclerViewSharedUser;
    private  ListaArticuloAdapter adapterArticles;
    private SharedUserAdapter adapterSharedUser;
    LinearLayoutManager linearLayoutManagerUser;
    public  iTaskNotification<Articulo> iTaskNotification;
    NavigationView navigationView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


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
         linearLayoutManagerUser = new LinearLayoutManager(viewListaArticle.getContext(), LinearLayoutManager.HORIZONTAL, false);


        recyclerViewSharedUser = viewListaArticle.findViewById(R.id.recyclerViewSharedUsers);
        recyclerViewSharedUser.setHasFixedSize(true);
        recyclerViewSharedUser.setLayoutManager(linearLayoutManagerUser);
        adapterSharedUser= new SharedUserAdapter(usuaris);//Set date
        recyclerViewSharedUser.setAdapter(adapterSharedUser);

        //TODO add pager behavior (Recylerview UserSHared item  page indicator)
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewSharedUser);






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

        //NavegationView
        //TODO NavigatationView
        navigationView = getActivity().findViewById(R.id.navView);
        /*
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                switch (id){
                    case R.id.nav_close:
                        Log.d("NavigationView","Close");
                        break;
                }
                return false;
            }
        });

         */

        return viewListaArticle;
    }




    //Menu
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
       getActivity().getMenuInflater().inflate(R.menu.men_navegation_view, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_barra_lateralOption:

                DrawerLayout drawerLayout=  ((DrawerLayout) (getActivity().findViewById(R.id.drawerLayutListaArticles)));
               if(! drawerLayout.isOpen())
                   drawerLayout .openDrawer(Gravity.LEFT);
              else
                   drawerLayout.close();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /*
    //implements   NavigationView.OnNavigationItemSelectedListener
    ///NavigationView

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_close:
                Log.d("NavigationItemSelected","nav_close");
                break;
            case R.id.nav_shared_network:
                Log.d("NavigationItemSelected","nav_shared_network");
                break;
            case R.id.nav_shareUser:
                Log.d("NavigationItemSelected","nav_shareUser");
                break;

            case R.id.nav_spends:
                Log.d("NavigationItemSelected","nav_spends");
                break;
        }
        return false;
    }


     */
}