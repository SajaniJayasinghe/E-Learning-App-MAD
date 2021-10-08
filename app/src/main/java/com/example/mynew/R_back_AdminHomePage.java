package com.example.mynew;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;



public class R_back_AdminHomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView;
    MenuItem menuItem;

    RelativeLayout bookStore,Lnews,developmentTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radmin_home_page);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.textView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //homepage relative layout id
        bookStore = findViewById(R.id.L8);
        Lnews = findViewById(R.id.L2);
        developmentTeam = findViewById(R.id.L10);

        //setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //book store layout
        bookStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(R_back_AdminHomePage.this, S_backend_MainActivity.class));
            }
        });

        //Latest news and quiz
        Lnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(R_back_AdminHomePage.this, A_back_CategoryActivity.class));
            }
        });

        //development team
        developmentTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(R_back_AdminHomePage.this, DevelopmentTeam.class));
            }
        });
    }
    //navigation bar
    @Override
    public boolean onNavigationItemSelected(@NonNull  MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_addsubjects:
                Intent intent = new Intent(R_back_AdminHomePage.this, R_back_MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                Intent intent1 = new Intent(R_back_AdminHomePage.this, S_MainActivity2.class);
                startActivity(intent1);
                break;

            case R.id.nav_maths:
                Intent intent2 = new Intent(R_back_AdminHomePage.this, R_mathsguide.class);
                startActivity(intent2);
                break;
            case R.id.nav_bio:
                Intent intent3 = new Intent(R_back_AdminHomePage.this, R_phyguide.class);
                startActivity(intent3);
                break;
            case R.id.nav_phy:
                Intent intent4 = new Intent(R_back_AdminHomePage.this, R_bioguide.class);
                startActivity(intent4);
                break;
            case R.id.nav_chem:
                Intent intent5 = new Intent(R_back_AdminHomePage.this, R_chemguide.class);
                startActivity(intent5);
                break;
        }
        return true;
    }
}