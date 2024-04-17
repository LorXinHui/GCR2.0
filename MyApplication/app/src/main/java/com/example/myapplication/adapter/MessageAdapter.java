package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.items.MessageItem;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<MessageItem> messageItems;

    public MessageAdapter(List<MessageItem> messageItems) {
        this.messageItems = messageItems;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.messsage_item, parent, false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        MessageItem messageItem = messageItems.get(position);
        holder.nameTextView.setText(messageItem.getMentor().getName());
        holder.jobDescriptionTextView.setText(messageItem.getMentor().getJobDescription());
        holder.emailTextView.setText(messageItem.getEmail());
        holder.contactTextView.setText(messageItem.getContact());
    }

    @Override
    public int getItemCount() {
        return messageItems.size();
    }



    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView jobDescriptionTextView;
        TextView emailTextView;
        TextView contactTextView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.mentor_name);
            jobDescriptionTextView = itemView.findViewById(R.id.mentor_job);
            emailTextView = itemView.findViewById(R.id.mentor_email);
            contactTextView = itemView.findViewById(R.id.mentor_contact);
        }
    }
}
