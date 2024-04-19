package com.example.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.activity.FragmentSettings;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.object.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProfile extends Fragment {

    DatabaseHelper db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentProfile newInstance(String param1, String param2) {
        FragmentProfile fragment = new FragmentProfile();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Find the button in your layout
        ImageView settingsImageView = view.findViewById(R.id.settingsImageView);

        // Set an onClickListener for the button
        settingsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), FragmentSettings.class);
                startActivity(intent);
            }
        });

        //get current user
        db = new DatabaseHelper(getContext());
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        int user_id = sharedPreferences.getInt("user_id", 0);

        //update details based on the current user
        TextView userName = view.findViewById(R.id.username);
        EditText userStatus = view.findViewById(R.id.user_status);
        EditText userPos = view.findViewById(R.id.user_position);
        EditText userEmail = view.findViewById(R.id.user_email);
        EditText userContact = view.findViewById(R.id.user_contact);
        EditText userUni = view.findViewById(R.id.user_uni);
        EditText userMajor = view.findViewById(R.id.user_major);
        EditText userCert = view.findViewById(R.id.user_certificate);
        EditText userSkills = view.findViewById(R.id.user_skills);

        User user = db.getUser(username);
        if (user != null) {
            userName.setText(user.getUser_fname() + " " + user.getUser_lname());
            userStatus.setText(user.getUser_status());
            userPos.setText(user.getUser_position());
            userEmail.setText(user.getUser_email());
            userContact.setText(user.getUser_contact());
            userUni.setText(db.getUniversity(user_id));
            userMajor.setText(db.getMajor(user_id));

            ArrayList<String> userCerts = db.getCert(user_id);
            StringBuilder certStringBuilder = new StringBuilder();
            for (String cert : userCerts) {
                certStringBuilder.append(cert).append("\n"); // Append each certificate name followed by a newline
            }
            userCert.setText(certStringBuilder.toString());

            ArrayList<String> userSkillList = db.getSkill(userCerts);
            StringBuilder skillStringBuilder = new StringBuilder();
            for (String skill: userSkillList) {
                skillStringBuilder.append(skill).append("\n");
            }
            userSkills.setText(skillStringBuilder.toString());
        } else {
            // User not found or database operation failed
            Log.d("User", "User not found or database operation failed");
        }

        return view;

    }

}