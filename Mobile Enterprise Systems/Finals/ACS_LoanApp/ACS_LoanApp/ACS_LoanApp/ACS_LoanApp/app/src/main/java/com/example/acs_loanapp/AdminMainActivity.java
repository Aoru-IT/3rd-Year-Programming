package com.example.acs_loanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;

public class AdminMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PendingFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_pending);
        }

        View headerView = navigationView.getHeaderView(0);
        TextView navHeaderText = headerView.findViewById(R.id.nav_header_text);
        TextView navSubHeaderText = headerView.findViewById(R.id.nav_subheader_text);

        Intent intent = getIntent();
        String empID = intent.getStringExtra("ID");
        navHeaderText.setText(empID);
        navSubHeaderText.setText("");
        getSupportActionBar().setTitle("Welcome " + empID);


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_pending) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PendingFragment()).commit();
        } else if (item.getItemId() == R.id.nav_allrecords) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllRecordsFragment()).commit();
        } else if (item.getItemId() == R.id.nav_approved) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ApprovedFragment()).commit();
        } else if (item.getItemId() == R.id.nav_declined) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DeclinedFragment()).commit();
        } else if (item.getItemId() == R.id.nav_logout) {
            Intent logoutIntent = new Intent(AdminMainActivity.this, LoginActivity.class);
            startActivity(logoutIntent);
            finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}