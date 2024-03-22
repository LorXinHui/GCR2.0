package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.fragment.FragmentCertification;
import com.example.myapplication.fragment.FragmentCommunity;
import com.example.myapplication.fragment.FragmentHome;
import com.example.myapplication.fragment.FragmentMentorship;
import com.example.myapplication.fragment.FragmentProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/*
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
 */

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setTitle("Hello");

        bottomNavigationView
                = findViewById(R.id.bottomNavigationView);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }
    FragmentHome fragmentHome = new FragmentHome();
    FragmentCommunity fragmentCommunity = new FragmentCommunity();
    FragmentMentorship fragmentMentorship = new FragmentMentorship();
    FragmentCertification fragmentCertification = new FragmentCertification();
    FragmentProfile fragmentProfile = new FragmentProfile();

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId(); // Get the item ID

        if (itemId == R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragmentHome).commit();
            setTitle("Home");
            return true;
        } else if (itemId == R.id.community) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragmentCommunity).commit();
            setTitle("Communities");
            return true;
        } else if (itemId == R.id.mentorship) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragmentMentorship).commit();
            setTitle("Industrial Mentorship");
            return true;
        } else if (itemId == R.id.certification) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragmentCertification).commit();
            setTitle("Courses");
            return true;
        } else if (itemId == R.id.profile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragmentProfile).commit();
            setTitle("Profile");
            return true;
        }
        return false;
    }
}
