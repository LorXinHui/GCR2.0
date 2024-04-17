package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.fragment.FragementNewsDetails;
import com.example.myapplication.items.NewsItem;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsItem> newsItems;
    private FragmentManager fragmentManager;

    public NewsAdapter(FragmentManager fragmentManager, List<NewsItem> newsItems) {
        this.fragmentManager = fragmentManager;
        this.newsItems = newsItems;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsItem newsItem = newsItems.get(position);
        holder.newsTitle.setText(newsItem.getTitle());
        holder.newsCategory.setText(newsItem.getCategory());
        holder.newsContent.setText(newsItem.getContent());

        FragementNewsDetails fragementNewsDetails = new FragementNewsDetails();

        holder.seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == holder.seeMore && fragmentManager != null){
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.flFragment, fragementNewsDetails);
                    transaction.commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }



    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        TextView newsCategory;
        TextView newsContent;
        TextView seeMore;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.news_title);
            newsCategory = itemView.findViewById(R.id.news_category);
            newsContent = itemView.findViewById(R.id.news_content);
            seeMore = itemView.findViewById(R.id.see_more);
        }
    }
}
