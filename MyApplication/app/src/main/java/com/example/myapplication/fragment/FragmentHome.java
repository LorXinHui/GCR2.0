package com.example.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.items.CommunityItem;
import com.example.myapplication.adapter.CommunityAdapter;
import com.example.myapplication.R;
import com.example.myapplication.activity.FragmentMesssage;
import com.example.myapplication.adapter.CourseAdapter;
import com.example.myapplication.items.CourseItem;
import com.example.myapplication.object.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FloatingActionButton messageFab;

    private RecyclerView recyclerView;
    private List<CommunityItem> communities = new ArrayList<>();
    private CommunityAdapter communityAdapter;

    private DatabaseHelper db;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        db = new DatabaseHelper(getContext());

        //get current user from SharedPreference
        TextView userDisplay = rootView.findViewById(R.id.username);

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        User user = db.getUser(username);
        // Process user data
        if (user != null) {
            // User data retrieval successful
            userDisplay.setText(user.getUser_fname() + " " + user.getUser_lname());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("user_id", user.getUser_id());
            editor.apply();
        } else {
            // User not found or database operation failed
            Log.d("User", "User not found or database operation failed");
            userDisplay.setText("User");
        }

        messageFab = rootView.findViewById(R.id.message_fab);
        messageFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), FragmentMesssage.class);
                startActivity(intent);
            }
        });

        RecyclerView communityView = rootView.findViewById(R.id.community_list);
        LinearLayoutManager communityLayout = new LinearLayoutManager(getActivity());
        communityView.setLayoutManager(communityLayout);

        List<CommunityItem> communityJoined = db.getCommunityJoined(1000000);
        CommunityAdapter communityAdapter = new CommunityAdapter(rootView.getContext(), getActivity().getSupportFragmentManager());
        communityAdapter.setData(communityJoined);
        communityView.setAdapter(communityAdapter);
        // communityAdapter.notifyDataSetChanged();

        RecyclerView courseView = rootView.findViewById(R.id.course_list);
        LinearLayoutManager courseManager = new LinearLayoutManager(getActivity());
        courseView.setLayoutManager(courseManager);

        List<CourseItem> courseList = new ArrayList<>();
        courseList.add(new CourseItem(
                "Entrepreneurship and Innovation",
                "Professional",
                30,
                "Explore the process of entrepreneurship and innovation, including idea generation, business model development, and market validation."
        ));
        courseList.add(new CourseItem(
                "Investment Management",
                "Casual",
                80,
                "Explore various investment strategies, portfolio management techniques, and risk assessment methods."
        ));
        CourseAdapter courseAdapter = new CourseAdapter(courseList);
        courseView.setAdapter(courseAdapter);

        return rootView;
    }
}

