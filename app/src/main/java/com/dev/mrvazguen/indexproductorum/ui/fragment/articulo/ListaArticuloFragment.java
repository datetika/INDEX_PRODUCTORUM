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
import com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter.ListaArticuloAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListaArticuloFragment extends Fragment {
    ArrayList<Articulo>articulos;
    private RecyclerView recyclerView;
    private  ListaArticuloAdapter adapter;

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
        articulos.add(new Articulo("Empty_Date"));


        //If DB is empty
      //  if(articulos.size()==0)
        //  articulos.add(new Articulo("Empty_DATE"));

        //Cargar los datos de FirestoreArticulo
        iFirestoreNotification notification = new iFirestoreNotification() {
            @Override
            public void OnSuccess() {
                Log.e("ReadFirebase","Hemos leido con exito !!!!");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void OnFailure() {
                Log.e("ReadFirebase","No hemos podido leer los datos del DB !!!!");
            }
        };

        articuloManagerDB.read(articulos, notification);
        Log.e("ListaArticulosFragment","Array lista articulos size: " + articulos.size());



        // Add the following lines to create RecyclerView
        adapter= new ListaArticuloAdapter(articulos);
        recyclerView = view.findViewById(R.id.recyclerViewItemArticulos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

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