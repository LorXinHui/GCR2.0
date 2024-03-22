package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.items.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNews#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNews extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentNews() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCommunity.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNews newInstance(String param1, String param2) {
        FragmentNews fragment = new FragmentNews();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_community, container, false);
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.community_news, container, false);

        // Find RecyclerView in the inflated layout
        RecyclerView newsView = rootView.findViewById(R.id.news_list);

        // Create and set layout manager (e.g., LinearLayoutManager)
        LinearLayoutManager newsLayout = new LinearLayoutManager(getActivity());
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
        NewsAdapter newsAdapter = new NewsAdapter(newsItemList);
        newsView.setAdapter(newsAdapter);

        return rootView;
    }
}

class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsItem> newsItems;

    public NewsAdapter(List<NewsItem> newsItems) {
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
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }



    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        TextView newsCategory;
        TextView newsContent;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.news_title);
            newsCategory = itemView.findViewById(R.id.news_category);
            newsContent = itemView.findViewById(R.id.news_content);
        }
    }
}