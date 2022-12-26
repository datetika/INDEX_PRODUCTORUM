package com.dev.mrvazguen.indexproductorum.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dev.mrvazguen.indexproductorum.R;
import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager.ArticuloManagerDB;
import com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager.UserManagerDB;
import com.dev.mrvazguen.indexproductorum.data.repository.iFirestoreNotification;
import com.dev.mrvazguen.indexproductorum.data.repository.iTaskNotification;
import com.dev.mrvazguen.indexproductorum.databinding.ActivityMainBinding;
import com.dev.mrvazguen.indexproductorum.ui.fragment.home.MainFragment;
import com.dev.mrvazguen.indexproductorum.utils.GlobarArgs;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Activity","Main Activity");
        //TODO: Comprobar si no existe el database crear nuevo y anadir los datos de usuarios
    }




}