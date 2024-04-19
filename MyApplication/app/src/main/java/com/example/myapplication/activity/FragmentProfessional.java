package com.example.myapplication.activity;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CourseDetailAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.items.CourseDetailItem;
import com.example.myapplication.items.CourseItem;

import java.util.ArrayList;
import java.util.List;

public class FragmentProfessional extends AppCompatActivity {
    private DatabaseHelper db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_professional);
        setTitle("Courses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        RecyclerView courseView = findViewById(R.id.courses);

        LinearLayoutManager courseLayout = new LinearLayoutManager(this);
        courseView.setLayoutManager(courseLayout);

        db = new DatabaseHelper(this);

        List<CourseDetailItem> courseList = db.getCourseDetail();

        CourseDetailAdapter courseDetailAdapter = new CourseDetailAdapter(FragmentProfessional.this, courseList);
        courseView.setAdapter(courseDetailAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}