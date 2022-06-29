package com.dev.mrvazguen.indexproductorum.ui.fragment.articulo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.ui.fragment.articulo.adapter.ListaArticuloAdapter;

import java.util.ArrayList;

public class ListaArticuloFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_lista_articulos, container, false);


        View view = inflater.inflate(R.layout.fragment_lista_articulos, container, false);

        //Cargar los datos de Firestore
        ArrayList<Articulo>articulos = new ArrayList<>();
        articulos.add(new Articulo("Default_item_articulo"));

        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewItemArticulos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new ListaArticuloAdapter(articulos));

        view.findViewById(R.id.floatingActionButtonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(
                        R.id.action_listaArticuloFragment_to_agregarArticuloFragment);
            }
        });

        return view;
    }
}