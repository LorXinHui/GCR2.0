package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Community;
import com.example.myapplication.CommunityAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCommunity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCommunity extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textView;

    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Community> communities = new ArrayList<>();

    private CommunityAdapter communityAdapter;

    public FragmentCommunity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCommunityNews.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCommunity newInstance(String param1, String param2) {
        FragmentCommunity fragment = new FragmentCommunity();
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
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);

        // textView = rootView.findViewById(R.id.text_community);
        // textView.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        recyclerView = view.findViewById(R.id.communityRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        communityAdapter = new CommunityAdapter(getActivity().getSupportFragmentManager());
        communityAdapter.setData(communities);
        recyclerView.setAdapter(communityAdapter);
        communityAdapter.notifyDataSetChanged();

        searchView = view.findViewById(R.id.search_community);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                              @Override
                                              public boolean onQueryTextSubmit(String query) {
                                                  return false;
                                              }

                                              @Override
                                              public boolean onQueryTextChange(String newText) {
                                                  filterList(newText);
                                                  return true;
                                              }
                                          }

        );

    }

    private void filterList(String text){
        List<Community> filteredCommunity = new ArrayList<>();
        for (Community c: communities){
            if (c.getCommunityName().toLowerCase().contains(text.toLowerCase())){
                filteredCommunity.add(c);
            }
        }
        if (filteredCommunity.isEmpty()){
            Toast noCommunity = Toast.makeText(getActivity(), "No community found", Toast.LENGTH_SHORT);
            noCommunity.show();

        }else{
            communityAdapter.setFilteredCommunities(filteredCommunity);
        }
    }


    private void dataInitialize(){
        communities = new ArrayList<>();
        Community c1 = new Community("FinanceTalk Hub",
                "FinanceTalk Hub is a vibrant community \n forum for professionals, enthusiasts, and \n learners in the financial industry");

        Community c2 = new Community("Fintech Frontiers Forum",
                "Dive into discussions on fintech startups, \n digital banking,  and other \n disruptive technologies shaping \n the future of finance.");

        communities.add(c1);
        communities.add(c2);
    }
}