package com.example.acs_loanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private static String empID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        View headerView = navigationView.getHeaderView(0);
        TextView navHeaderText = headerView.findViewById(R.id.nav_header_text);
        TextView navSubHeaderText = headerView.findViewById(R.id.nav_subheader_text);

        Intent intent = getIntent();
        empID = intent.getStringExtra("ID");
        String name = intent.getStringExtra("Name");

        navHeaderText.setText(empID);
        navSubHeaderText.setText(name);

        getSupportActionBar().setTitle("Welcome " + empID);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        int itemId = item.getItemId();

        if (itemId == R.id.nav_home) {
            selectedFragment = new HomeFragment();
        } else if (itemId == R.id.nav_emergency) {
            selectedFragment = new EmergencyFragment();
        } else if (itemId == R.id.nav_special) {
            selectedFragment = new SpecialFragment();
        } else if (itemId == R.id.nav_regular) {
            selectedFragment = new RegularFragment();
        } else if (itemId == R.id.nav_loan_details) {
            selectedFragment = new LoanDetailsFragment();
        } else if (itemId == R.id.nav_loan_status) {
            selectedFragment = new LoanStatusFragment();
        } else if (itemId == R.id.nav_logout) {
            Intent logoutIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(logoutIntent);
            finish();
            return true;
        } else {
            Toast.makeText(this, "Invalid option selected!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setCheckedItem(itemId);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public static String getEmployeeID() {
        return empID;
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
