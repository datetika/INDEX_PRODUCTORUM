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
import com.dev.mrvazguen.indexproductorum.databinding.ActivityMainBinding;
import com.dev.mrvazguen.indexproductorum.ui.fragment.home.MainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Activity","Main Activity");


    }




}