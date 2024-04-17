package com.example.myapplication.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.adapter.MessageAdapter;
import com.example.myapplication.items.InvitationItem;
import com.example.myapplication.items.MessageItem;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMesssage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMesssage extends AppCompatActivity {

    /**
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMesssage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMesssage.

    // TODO: Rename and change types and number of parameters
    public static FragmentMesssage newInstance(String param1, String param2) {
        FragmentMesssage fragment = new FragmentMesssage();
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
        View view = inflater.inflate(R.layout.fragment_messsage, container, false);

        // Find RecyclerView in the inflated layout
        RecyclerView messageView = view.findViewById(R.id.message);

        // Create and set layout manager (e.g., LinearLayoutManager)
        LinearLayoutManager messageLayout = new LinearLayoutManager(getActivity());
        messageView.setLayoutManager(messageLayout);

        // Create and set adapter
        List<MessageItem> messageList = new ArrayList<>();
        // Add some invitation items to the list
        messageList.add(new MessageItem(new InvitationItem("Elena Rodriguez", "UX Designer"), "elana.rodriquez@univiz.com", "+1 (916) 693-5829" ));
        messageList.add(new MessageItem(new InvitationItem("Oliver Bennett", "Chief Technology Officer (CTO)"), "oliver.bennett@techxperts.com", "+65 3952 2507"));
        messageList.add(new MessageItem(new InvitationItem("Lucas Morales", "Operations Manager"), "lucas.morales@iconet.com", "+60 12 6750 7326"));
        MessageAdapter messageAdapter = new MessageAdapter(messageList);
        messageView.setAdapter(messageAdapter);

        return view;
    }
    */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_messsage);

        // Find RecyclerView in the inflated layout
        RecyclerView messageView = findViewById(R.id.message);

        // Create and set layout manager (e.g., LinearLayoutManager)
        LinearLayoutManager messageLayout = new LinearLayoutManager(this);
        messageView.setLayoutManager(messageLayout);

        // Create and set adapter
        List<MessageItem> messageList = new ArrayList<>();
        // Add some invitation items to the list
        messageList.add(new MessageItem(new InvitationItem("Elena Rodriguez", "UX Designer"), "elana.rodriquez@univiz.com", "+1 (916) 693-5829" ));
        messageList.add(new MessageItem(new InvitationItem("Oliver Bennett", "Chief Technology Officer (CTO)"), "oliver.bennett@techxperts.com", "+65 3952 2507"));
        messageList.add(new MessageItem(new InvitationItem("Lucas Morales", "Operations Manager"), "lucas.morales@iconet.com", "+60 12 6750 7326"));
        MessageAdapter messageAdapter = new MessageAdapter(messageList);
        messageView.setAdapter(messageAdapter);
    }
}

