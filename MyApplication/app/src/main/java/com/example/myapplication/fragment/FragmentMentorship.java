package com.example.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.activity.FragmentMesssage;
import com.example.myapplication.adapter.InvitationAdapter;
import com.example.myapplication.adapter.MentorshipAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.items.InvitationItem;
import com.example.myapplication.R;
import com.example.myapplication.object.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMentorship#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMentorship extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FloatingActionButton messageFab;
    DatabaseHelper db;

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

        db = new DatabaseHelper(getContext());
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("user_id", 0);

        // Find RecyclerView in the inflated layout
        RecyclerView invitationView = rootView.findViewById(R.id.invitation_list);

        // Create and set layout manager (e.g., LinearLayoutManager)
        LinearLayoutManager invitationLayout = new LinearLayoutManager(getActivity());
        invitationView.setLayoutManager(invitationLayout);

        // Create and set adapter
        List<User> invitationItemList = db.getInvite(userId, "FALSE");
        // Add some invitation items to the list
        //invitationItemList.add(new InvitationItem("Elena Rodriguez", "UX Designer"));
        //invitationItemList.add(new InvitationItem("Malik Patel", "Data Scientist"));
        InvitationAdapter invitationAdapter = new InvitationAdapter(getContext(), invitationItemList);
        invitationView.setAdapter(invitationAdapter);

        // Find RecyclerView in the inflated layout
        RecyclerView mentorshipView = rootView.findViewById(R.id.recommendation_list);

        // Create and set layout manager (e.g., LinearLayoutManager)
        LinearLayoutManager mentorshipLayout = new LinearLayoutManager(getActivity());
        mentorshipView.setLayoutManager(mentorshipLayout);

        // Create and set adapter
        List<User> mentorshipList = db.getMentor(userId, "FALSE");
        // Add some invitation items to the list
        //mentorshipList.add(new InvitationItem("Oliver Bennett", "Chief Technology Officer (CTO)"));
        //mentorshipList.add(new InvitationItem("Lucas Morales", "Operations Manager"));
        MentorshipAdapter mentorshipAdapter = new MentorshipAdapter(getContext(), mentorshipList);
        mentorshipView.setAdapter(mentorshipAdapter);

        messageFab = rootView.findViewById(R.id.message_fab);
        messageFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), FragmentMesssage.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    /*
    @Override
    public void onClick(View v) {
        if (v == messageFab){
            //FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            //transaction.replace(R.id.flFragment, fragmentMesssage);
            //transaction.commit();
            Intent intent= new Intent(, FragmentMesssage.class);
            startActivity(intent);
        }
    }
     */
}
