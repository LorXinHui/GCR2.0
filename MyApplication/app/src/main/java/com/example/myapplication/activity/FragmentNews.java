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
import com.example.myapplication.items.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class FragmentNews extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_news);
        setTitle("Community");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find RecyclerView in the inflated layout
        RecyclerView newsView = findViewById(R.id.news_list);

        // Create and set layout manager (e.g., LinearLayoutManager)
        LinearLayoutManager newsLayout = new LinearLayoutManager(this);
        newsView.setLayoutManager(newsLayout);

        // Create and set adapter
        List<NewsItem> newsItemList = new ArrayList<>();
        // Add some invitation items to the list
        newsItemList.add(new NewsItem(
                "Navigating Market Volatility: Tips for Investors",
                "Guides & Tips",
                "In light of recent market fluctuations, it's essential for investors to remain calm and stay focused on their long-term financial goals. Here are some tips for navigating market volatility: ..."
        ));
        newsItemList.add(new NewsItem(
                "Understanding the Basics of Stock Market Investing",
                "Guides & Tips",
                "Investing in the stock market can be daunting for beginners, but understanding the basics can help you make informed decisions and build wealth over time. Firstly, it's essential to grasp the concept of stocks, which represent ownership in a company. When you buy shares of a company's stock, you become a partial owner and have the potential to benefit from the company's growth and profitability.... "
        ));
        newsItemList.add(new NewsItem(
                "What Are Your Favorite Personal Finance Apps and Tools?",
                "Discussions",
                "In today's digital age, there's no shortage of personal finance apps and tools designed to help you manage your money more effectively. From budgeting apps and expense trackers to investment platforms and credit score monitors, the options are endless. What are your favorite personal finance apps and tools.... "
        ));
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

