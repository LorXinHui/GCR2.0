package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.items.InvitationItem;
import com.example.myapplication.object.User;

import java.util.List;

public class InvitationAdapter extends RecyclerView.Adapter<InvitationAdapter.InvitationViewHolder> {
    private List<User> invitationItems;

    public InvitationAdapter(List<User> invitationItems) {
        this.invitationItems = invitationItems;
    }

    @NonNull
    @Override
    public InvitationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.invitation_item, parent, false);
        return new InvitationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InvitationViewHolder holder, int position) {
        User invitationItem = invitationItems.get(position);
        holder.nameTextView.setText(invitationItem.getUser_fname() + " " + invitationItem.getUser_lname());
        holder.jobDescriptionTextView.setText(invitationItem.getUser_position());
        // Implement button click listeners if needed
        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update layout when button is clicked
                holder.acceptButton.setVisibility(View.GONE);
                holder.rejectButton.setVisibility(View.GONE);
                holder.actionText.setVisibility(View.VISIBLE);
                holder.actionText.setText("Accept");
            }
        });

        holder.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update layout when button is clicked
                holder.acceptButton.setVisibility(View.GONE);
                holder.rejectButton.setVisibility(View.GONE);
                holder.actionText.setVisibility(View.VISIBLE);
                holder.actionText.setText("Reject");
            }
        });
    }

    @Override
    public int getItemCount() {
        return invitationItems.size();
    }

    public static class InvitationViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView jobDescriptionTextView;
        Button acceptButton;
        Button rejectButton;
        TextView actionText;

        public InvitationViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.invite_name);
            jobDescriptionTextView = itemView.findViewById(R.id.invite_job);
            acceptButton = itemView.findViewById(R.id.acceptButton);
            rejectButton = itemView.findViewById(R.id.rejectButton);
            actionText = itemView.findViewById(R.id.actionText);
        }
    }
}
