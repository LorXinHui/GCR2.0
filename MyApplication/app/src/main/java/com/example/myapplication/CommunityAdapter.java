package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.fragment.FragmentNews;

import java.util.ArrayList;
import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {

    List<Community> communities = new ArrayList<>();
    FragmentManager fragmentManager;

    public CommunityAdapter(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    public void setData(List<Community> data){
        this.communities = data;
    }
    @NonNull
    @Override
    public CommunityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.communities, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String communityName = communities.get(position).getCommunityName();
        String communityDesc = communities.get(position).getCommunityDesc();
        holder.communityHead.setText(communityName);
        holder.communityDesc.setText(communityDesc);

        FragmentNews fragmentNews = new FragmentNews();

        holder.joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == holder.joinButton && fragmentManager != null){
                    Toast.makeText(v.getContext().getApplicationContext(), "Joined Community", Toast.LENGTH_SHORT).show();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.flFragment, fragmentNews);
                    transaction.commit();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return communities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // view components put into the card view
        public View itemView;
        public TextView communityHead;
        public TextView communityDesc;
        public Button joinButton;

        public ViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            communityHead = itemView.findViewById(R.id.communityHeader);
            communityDesc = itemView.findViewById(R.id.communityDesc);
            joinButton = itemView.findViewById(R.id.btnJoinCommunity);

        }


    }

    public void setFilteredCommunities(List<Community> communities) {
        this.communities = communities;
        notifyDataSetChanged();
    }


}
