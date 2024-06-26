package com.example.myapplication.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.items.InvitationItem;
import com.example.myapplication.object.User;

import java.util.List;

public class MentorshipAdapter extends RecyclerView.Adapter<MentorshipAdapter.MentorshipViewHolder> {
    private List<User> mentorshipItems;
    private Context context;
    DatabaseHelper db;

    public MentorshipAdapter(Context context, List<User> invitationItems) {
        this.context = context;
        this.mentorshipItems = invitationItems;
    }

    @NonNull
    @Override
    public MentorshipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mentorship_item, parent, false);
        return new MentorshipViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MentorshipViewHolder holder, int position) {
        User mentor = mentorshipItems.get(position);
        holder.nameTextView.setText(mentor.getUser_fname() + " " + mentor.getUser_lname());
        holder.jobDescriptionTextView.setText(mentor.getUser_position());

        db = new DatabaseHelper(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("user_id", 0);

        // Implement button click listeners if needed
        holder.mentorshipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update layout when button is clicked
                holder.mentorshipButton.setVisibility(View.GONE);
                holder.requestSentText.setVisibility(View.VISIBLE);

                db.updateStatus(userId, mentor.getUser_id(), "mentorship");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mentorshipItems.size();
    }



    public static class MentorshipViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView jobDescriptionTextView;
        Button mentorshipButton;
        TextView requestSentText;

        public MentorshipViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.mentor_name);
            jobDescriptionTextView = itemView.findViewById(R.id.mentor_job);
            mentorshipButton = itemView.findViewById(R.id.mentorButton);
            requestSentText = itemView.findViewById(R.id.requestSentText);
        }
    }
}
