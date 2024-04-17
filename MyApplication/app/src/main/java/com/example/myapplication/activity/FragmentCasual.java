package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;

public class FragmentCasual extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_casual);

        // Find the button in your layout
        ImageView casualImageView = findViewById(R.id.casualImageView);

        // Set an onClickListener for the button
        casualImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentCasual.this, FragmentQuiz.class);
                startActivity(intent);
            }
        });
    }
}