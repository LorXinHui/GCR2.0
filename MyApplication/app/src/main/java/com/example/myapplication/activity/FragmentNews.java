package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.NewsAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.items.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class FragmentNews extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_news);
        setTitle("Community");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(FragmentNews.this);

        TextView header = findViewById(R.id.header);
        Intent intent = getIntent();
        header.setText(intent.getStringExtra("community"));

        // Find RecyclerView in the inflated layout
        RecyclerView newsView = findViewById(R.id.news_list);

        // Create and set layout manager (e.g., LinearLayoutManager)
        LinearLayoutManager newsLayout = new LinearLayoutManager(this);
        newsView.setLayoutManager(newsLayout);

        // Create and set adapter
        List<NewsItem> newsItemList = db.getPost(intent.getStringExtra("community"));
        NewsAdapter newsAdapter = new NewsAdapter(this, this.getSupportFragmentManager(), newsItemList);
        newsView.setAdapter(newsAdapter);

        View back = findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentNews.this, MainActivity.class);
                intent.putExtra("fragment", "community");
                startActivity(intent);
            }
        });
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

