package com.example.signupapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.signupapp.Fragments.NotesFragment;
import com.example.signupapp.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class FragmentShift extends AppCompatActivity {

    BottomNavigationView nav;
    NotesFragment notesFragment = new NotesFragment();
    ProfileFragment profileFragment = new ProfileFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_shift);

        nav = findViewById(R.id.nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentShift, notesFragment).commit();

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentShift, profileFragment).commit();
                        return true;
                    case R.id.notes:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentShift, notesFragment).commit();
                        return true;
                }
                return false;
            }
        });

        nav.setSelectedItemId(R.id.notes);
    }
}