package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.items.CommunityItem;
import com.example.myapplication.adapter.CommunityAdapter;
import com.example.myapplication.R;
import com.example.myapplication.activity.FragmentMesssage;
import com.example.myapplication.adapter.CourseAdapter;
import com.example.myapplication.items.CourseItem;
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

        List<CommunityItem> communityList = new ArrayList<>();
        communityList.add(new CommunityItem("FinanceTalk Hub",
                "FinanceTalk Hub is a vibrant community \n forum for professionals, enthusiasts, and \n learners in the financial industry"));
        communityList.add(new CommunityItem("Fintech Frontiers Forum",
                "Dive into discussions on fintech startups, \n digital banking,  and other \n disruptive technologies shaping \n the future of finance."));
        CommunityAdapter communityAdapter = new CommunityAdapter(getActivity().getSupportFragmentManager());
        communityAdapter.setData(communityList);
        communityView.setAdapter(communityAdapter);

        RecyclerView courseView = rootView.findViewById(R.id.course_list);
        LinearLayoutManager courseManager = new LinearLayoutManager(getActivity());
        courseView.setLayoutManager(courseManager);

        List<CourseItem> courseList = new ArrayList<>();
        courseList.add(new CourseItem(
                "Entrepreneurship and Innovation",
                "Professional",
                30
        ));
        courseList.add(new CourseItem(
                "Investment Management",
                "Casual",
                80
        ));
        CourseAdapter courseAdapter = new CourseAdapter(courseList);
        courseView.setAdapter(courseAdapter);

        return rootView;
    }
}

