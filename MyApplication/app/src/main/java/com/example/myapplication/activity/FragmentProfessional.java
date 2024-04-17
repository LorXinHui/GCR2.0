package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CourseDetailAdapter;
import com.example.myapplication.items.CourseItem;

import java.util.ArrayList;
import java.util.List;

public class FragmentProfessional extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_professional);

        RecyclerView courseView = findViewById(R.id.courses);

        LinearLayoutManager courseLayout = new LinearLayoutManager(this);
        courseView.setLayoutManager(courseLayout);

        List<CourseItem> courseList = new ArrayList<>();
        courseList.add(new CourseItem(
                "Entrepreneurship and Innovation",
                "Professional",
                30,
                "Explore the process of entrepreneurship and innovation, including idea generation, business model development, and market validation."
        ));
        courseList.add(new CourseItem(
                "Investment Management",
                "Casual",
                80,
                "Explore various investment strategies, portfolio management techniques, and risk assessment methods."
        ));

        CourseDetailAdapter courseDetailAdapter = new CourseDetailAdapter(FragmentProfessional.this, courseList);
        courseView.setAdapter(courseDetailAdapter);
    }
}