package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {

    List<Community> communities = new ArrayList<>();

    public CommunityAdapter(){
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

        public ViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            communityHead = itemView.findViewById(R.id.communityHeader);
            communityDesc = itemView.findViewById(R.id.communityDesc);


        }


    }

    public void setFilteredCommunities(List<Community> communities) {
        this.communities = communities;
        notifyDataSetChanged();
    }


}
