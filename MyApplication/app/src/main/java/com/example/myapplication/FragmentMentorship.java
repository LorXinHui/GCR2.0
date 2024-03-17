package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMentorship#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMentorship extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMentorship() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMentorship.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMentorship newInstance(String param1, String param2) {
        FragmentMentorship fragment = new FragmentMentorship();
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
        View rootView = inflater.inflate(R.layout.fragment_mentorship, container, false);

        // Find RecyclerView in the inflated layout
        RecyclerView invitationView = rootView.findViewById(R.id.invitation_list);

        // Create and set layout manager (e.g., LinearLayoutManager)
        LinearLayoutManager invitationLayout = new LinearLayoutManager(getActivity());
        invitationView.setLayoutManager(invitationLayout);

        // Create and set adapter
        List<InvitationItem> invitationItemList = new ArrayList<>();
        // Add some invitation items to the list
        invitationItemList.add(new InvitationItem("Elena Rodriguez", "UX Designer"));
        invitationItemList.add(new InvitationItem("Malik Patel", "Data Scientist"));
        invitationItemList.add(new InvitationItem("Maxwell Lee", "Financial Analyst"));
        InvitationAdapter invitationAdapter = new InvitationAdapter(invitationItemList);
        invitationView.setAdapter(invitationAdapter);

        // Find RecyclerView in the inflated layout
        RecyclerView mentorshipView = rootView.findViewById(R.id.recommendation_list);

        // Create and set layout manager (e.g., LinearLayoutManager)
        LinearLayoutManager mentorshipLayout = new LinearLayoutManager(getActivity());
        mentorshipView.setLayoutManager(mentorshipLayout);

        // Create and set adapter
        List<InvitationItem> mentorshipList = new ArrayList<>();
        // Add some invitation items to the list
        mentorshipList.add(new InvitationItem("Oliver Bennett", "Chief Technology Officer (CTO)"));
        mentorshipList.add(new InvitationItem("Lucas Morales", "Operations Manager"));
        MentorshipAdapter mentorshipAdapter = new MentorshipAdapter(mentorshipList);
        mentorshipView.setAdapter(mentorshipAdapter);

        return rootView;
    }

}

class InvitationAdapter extends RecyclerView.Adapter<InvitationAdapter.InvitationViewHolder> {
    private List<InvitationItem> invitationItems;

    public InvitationAdapter(List<InvitationItem> invitationItems) {
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
        InvitationItem invitationItem = invitationItems.get(position);
        holder.nameTextView.setText(invitationItem.getName());
        holder.jobDescriptionTextView.setText(invitationItem.getJobDescription());
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

class MentorshipAdapter extends RecyclerView.Adapter<MentorshipAdapter.MentorshipViewHolder> {
    private List<InvitationItem> mentorshipItems;

    public MentorshipAdapter(List<InvitationItem> invitationItems) {
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
        InvitationItem invitationItem = mentorshipItems.get(position);
        holder.nameTextView.setText(invitationItem.getName());
        holder.jobDescriptionTextView.setText(invitationItem.getJobDescription());
        // Implement button click listeners if needed
        holder.mentorshipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update layout when button is clicked
                holder.mentorshipButton.setVisibility(View.GONE);
                holder.requestSentText.setVisibility(View.VISIBLE);
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