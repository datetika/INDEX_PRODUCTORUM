package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager.ArticuloManagerDB;
import com.dev.mrvazguen.indexproductorum.data.repository.iFirestoreNotification;
import com.dev.mrvazguen.indexproductorum.data.repository.iTaskNotification;
import com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter.ListaArticuloAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListaArticuloFragment extends Fragment {
    ArrayList<Articulo>articulos;
    private RecyclerView recyclerView;
    private  ListaArticuloAdapter adapter;

    public  iTaskNotification<Articulo> iTaskNotification;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_lista_articulos, container, false);

        ArticuloManagerDB articuloManagerDB = new ArticuloManagerDB("Test/prueba");

        View view = inflater.inflate(R.layout.fragment_lista_articulos, container, false);




        articulos = new ArrayList<>();
        //Cargar los datos de FirestoreArticulo
        iTaskNotification = new iTaskNotification<Articulo>(){
            @Override
            public void OnSucces(List<Articulo> lista) {
                articulos= (ArrayList<Articulo>) lista;
                adapter= new ListaArticuloAdapter(articulos);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void OnFail(String msg) {
               Log.d("ArticuloManagerDB",msg);
                articulos.add(new Articulo("Empty_DATE"));
                adapter= new ListaArticuloAdapter(articulos);
                recyclerView.setAdapter(adapter);
            }
        };

        articuloManagerDB.read( iTaskNotification);
        Log.e("ListaArticulosFragment","Array lista articulos size: " + articulos.size());

        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewItemArticulos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        view.findViewById(R.id.floatingActionButtonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(
                        R.id.action_listaArticuloFragment_to_agregarArticuloFragment);
            }
        });


        return view;
    }


    ///region
    public void ListeningTripList(){

    }
    ///enregion
}