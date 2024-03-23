package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProfessional#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProfessional extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentProfessional() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentProfessional.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentProfessional newInstance(String param1, String param2) {
        FragmentProfessional fragment = new FragmentProfessional();
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
        View view = inflater.inflate(R.layout.fragment_professional, container, false);

        // Find the button in your layout
        Button details1 = view.findViewById(R.id.moreDet1);
        Button details2 = view.findViewById(R.id.moreDet2);

        // Set an onClickListener for the button
        details1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of the fragment you want to navigate to
                FragmentCourseDetails fragmentCourseDetails = new FragmentCourseDetails();

                // Get the FragmentManager
                FragmentManager fragmentManager = getParentFragmentManager();

                // Begin a transaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Replace the current fragment with the new fragment
                fragmentTransaction.replace(R.id.flFragment, fragmentCourseDetails);

                // Optional: Add the transaction to the back stack
                fragmentTransaction.addToBackStack(null);

                // Commit the transaction
                fragmentTransaction.commit();
            }
        });

        // Set an onClickListener for the button
        details2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of the fragment you want to navigate to
                FragmentCourseDetails fragmentCourseDetails = new FragmentCourseDetails();

                // Get the FragmentManager
                FragmentManager fragmentManager = getParentFragmentManager();

                // Begin a transaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Replace the current fragment with the new fragment
                fragmentTransaction.replace(R.id.flFragment, fragmentCourseDetails);

                // Optional: Add the transaction to the back stack
                fragmentTransaction.addToBackStack(null);

                // Commit the transaction
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}