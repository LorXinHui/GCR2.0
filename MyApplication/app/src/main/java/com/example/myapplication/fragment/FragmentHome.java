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
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Community;
import com.example.myapplication.CommunityAdapter;
import com.example.myapplication.R;
import com.example.myapplication.items.CourseItem;
import com.example.myapplication.items.MessageItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FloatingActionButton messageFab;

    private RecyclerView recyclerView;
    private List<Community> communities = new ArrayList<>();
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
        messageFab.setOnClickListener(this);

        RecyclerView communityView = rootView.findViewById(R.id.community_list);
        LinearLayoutManager communityLayout = new LinearLayoutManager(getActivity());
        communityView.setLayoutManager(communityLayout);

        List<Community> communityList = new ArrayList<>();
        communityList.add(new Community("FinanceTalk Hub",
                "FinanceTalk Hub is a vibrant community \n forum for professionals, enthusiasts, and \n learners in the financial industry"));
        communityList.add(new Community("Fintech Frontiers Forum",
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

    FragmentMesssage fragmentMesssage = new FragmentMesssage();
    @Override
    public void onClick(View v) {
        if (v == messageFab){
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.flFragment, fragmentMesssage);
            transaction.commit();
        }
    }
}

class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private List<CourseItem> courseItems;

    public CourseAdapter(List<CourseItem> courseItems) {
        this.courseItems = courseItems;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        CourseItem courseItem = courseItems.get(position);
        holder.nameTextView.setText(courseItem.getCourseName());
        holder.categoryTextView.setText(courseItem.getCourseType());
        holder.progressTextView.setText(Integer.toString(courseItem.getCourseProgress()) + "%");
        holder.progressBar.setProgress(courseItem.getCourseProgress());
    }

    @Override
    public int getItemCount() {
        return courseItems.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView categoryTextView;
        TextView progressTextView;
        ProgressBar progressBar;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.course_title);
            categoryTextView = itemView.findViewById(R.id.course_category);
            progressTextView = itemView.findViewById(R.id.course_progress);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}