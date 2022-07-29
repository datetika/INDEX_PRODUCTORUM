package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.data.model.SharedUser;
import com.dev.mrvazguen.indexproductorum.data.model.Usuari;
import com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager.ArticuloManagerDB;
import com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager.UserManagerDB;
import com.dev.mrvazguen.indexproductorum.data.repository.iTaskNotification;
import com.dev.mrvazguen.indexproductorum.databinding.FragmentListaArticulosBinding;
import com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter.ListaArticuloAdapter;
import com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter.SharedUserAdapter;
import com.dev.mrvazguen.indexproductorum.utils.GlobarArgs;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class ListaArticuloFragment extends Fragment{
    private ArrayList<Articulo>articulos;
    private NavigationView navigationView;
    private View headerView;
    private TextView navUsername;
    private RecyclerView recyclerViewSharedUser;
    private  ListaArticuloAdapter adapterArticles;
    private SharedUserAdapter adapterSharedUser;
    public com.dev.mrvazguen.indexproductorum.data.repository.iTaskNotification<Articulo> iTaskNotification;

    private  FragmentListaArticulosBinding binding;
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

        View v = inflater.inflate(R.layout.fragment_lista_articulos, container, false);
        binding = FragmentListaArticulosBinding.bind(v);

        //inicializacion de variables usuaris, articulos
        articulos = new ArrayList<>();
        ArrayList<SharedUser>sharedUsers = new ArrayList<>();
        ArticuloManagerDB articuloManagerDB = new ArticuloManagerDB("Test/prueba");
        UserManagerDB userManagerDB = new UserManagerDB();


        ///region  //TODO Shared USER recylerview UserSHared


        //TODO add pager behavior (Recylerview UserSHared item  page indicator)
        //PagerSnapHelper snapHelper = new PagerSnapHelper();
        //snapHelper.attachToRecyclerView(recyclerViewSharedUser);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        binding.recViewItemSharedUser.setLayoutManager(layoutManager);

        ArrayList<SharedUser> items =  new ArrayList<>();
        items.add(new SharedUser("Default"));
        binding.recViewItemSharedUser.setAdapter(new SharedUserAdapter(items));


        ///endregion
        ///region //TODO RECYLERVIEW lista Articulos


        //Cargar los datos de FirestoreArticulo
        View viewListaArticle = inflater.inflate(R.layout.fragment_lista_articulos, container, false);
        LinearLayoutManager   linearLayoutManagerUser = new LinearLayoutManager(viewListaArticle.getContext(), LinearLayoutManager.HORIZONTAL, false);

        iTaskNotification = new iTaskNotification<Articulo>(){
            @Override
            public void OnSucces(List<Articulo> lista) {
                articulos= (ArrayList<Articulo>) lista;
                if(articulos.size()==0)
                    articulos.add(new Articulo("EMPTY_FIELD"));
                adapterArticles= new ListaArticuloAdapter(articulos);
                binding.recyclerViewItemArticulos.setAdapter(adapterArticles);
            }

            @Override
            public void OnFail(String msg) {
                articulos.add(new Articulo(msg));
                Log.d("ArticuloManagerDB",msg);
                adapterArticles= new ListaArticuloAdapter(articulos);
                binding.recyclerViewItemArticulos.setAdapter(adapterArticles);
            }
        };

        articuloManagerDB.readRealtimeListener(iTaskNotification);
        Log.e("ListaArticulosFragment","Array lista articulos size: " + articulos.size());




        //  Add recylerview Items shoping list
        binding.recyclerViewItemArticulos.setHasFixedSize(true);
        binding.recyclerViewItemArticulos.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));

        //TODO recylerview shoping list swipe to  delete item
       new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
           @Override
           public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
               return false;
           }

           @Override
           public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
               int position = viewHolder.getBindingAdapterPosition();

               if(articuloManagerDB.deleteDocument(GlobarArgs.DB_SHOPING+"/"+GlobarArgs.USER_ID+"/"+GlobarArgs.COLLECTION_SHOPING_LIST,articulos.get(position).getNombre()))
                    adapterArticles.deteleItem(viewHolder.getBindingAdapterPosition());
               Log.d("recylerviewArticules","onSwiped");

           }
       }).attachToRecyclerView(binding.recyclerViewItemArticulos);
       ///endregion




        ///region buttons listener
        binding.floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(
                        R.id.action_listaArticuloFragment_to_agregarArticuloFragment);
            }
        });


        binding.btnManageSharedUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(
                        R.id.action_listaArticuloFragment_to_sharedUserFragment);
            }
        });
        ///endregion

         //endregion

        ///region NavigationView

        navigationView = binding.getRoot().findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_close:
                        Log.d("NavigationView","close");
                        getActivity().finish();
                        FirebaseAuth.getInstance().signOut();
                        break;
                    case R.id.nav_shareUser:
                        Navigation.findNavController(binding.getRoot()).navigate(
                                R.id.action_listaArticuloFragment_to_sharedUserFragment);
                        break;

                    case  R.id.nav_Descuento:
                        Navigation.findNavController(binding.getRoot()).navigate(
                                R.id.action_listaArticuloFragment_to_descuentoProductoFragment);
                        break;
                }
                return false;
            }
        });

        //TODO  inicialize navigationView header and set Name of user
        headerView = navigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.tvNavigationNombre);
        navUsername.setText(GlobarArgs.NOM_USUARI_ACTUAL);
        ///endregion




        return binding.getRoot();
    }

    //Menu
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
       getActivity().getMenuInflater().inflate(R.menu.men_navegation_view, menu);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //UPDATE NAME OF USER
        navUsername.setText(GlobarArgs.NOM_USUARI_ACTUAL);

        switch (item.getItemId()) {
            case R.id.mnu_barra_lateralOpen:
                DrawerLayout drawerLayout=  ((DrawerLayout) (getActivity().findViewById(R.id.drawerLayutListaArticles)));
                ActionMenuItemView menuItem = (ActionMenuItemView) getActivity().findViewById(R.id.mnu_barra_lateralOpen);

                if(! drawerLayout.isOpen()){
                   drawerLayout .openDrawer(Gravity.LEFT);
                   menuItem.setIcon(ContextCompat.getDrawable(getContext(), android.R.drawable.arrow_up_float));
               }
               else{
                   drawerLayout.close();
                   menuItem.setIcon(ContextCompat.getDrawable(getContext(), android.R.drawable.arrow_down_float));
               }
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}