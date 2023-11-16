package com.example.onlinetestv3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class HomeScreen extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton imgBtn_menuIcon;
    ImageButton imgBtnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        imgBtn_menuIcon = findViewById(R.id.imgBtn_menuIcon);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        loadFragment(new HomeFragment());



        // Menu Onclick event
        imgBtn_menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // AppDrawer menu option onclick event
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home_screen:
                        loadFragment(new HomeFragment());
                        break;
                   case R.id.nav_c_prog:
                        loadFragment(new CTest(getApplicationContext()));
                        break;
                    case R.id.nav_cpp_prog:
                        loadFragment(new CppTest(getApplicationContext()));
                        break;
                    case R.id.nav_java_prog:
                        loadFragment(new JavaTest(getApplicationContext()));
                        break;
                    case R.id.nav_db_prog:
                        loadFragment(new DbmsTest(getApplicationContext()));
                        break;
                    case R.id.nav_about:
                        loadFragment(new AboutUs());
                        break;
                    case R.id.nav_contact:
                        loadFragment(new ContactUs());
                        break;
                    case R.id.nav_profile:
                        loadFragment(new ProfileFragment());
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.my_container,fragment);
        ft.commit();
    }
}