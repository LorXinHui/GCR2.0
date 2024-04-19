package com.example.myapplication.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.adapter.MessageAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.items.InvitationItem;
import com.example.myapplication.items.MessageItem;
import com.example.myapplication.R;
import com.example.myapplication.object.User;

import java.util.ArrayList;
import java.util.List;

public class FragmentMesssage extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_messsage);
        setTitle("Industrial Mentorship");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(FragmentMesssage.this);
        SharedPreferences sharedPreferences = getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("user_id", 0);

        // Find RecyclerView in the inflated layout
        RecyclerView messageView = findViewById(R.id.message);

        // Create and set layout manager (e.g., LinearLayoutManager)
        LinearLayoutManager messageLayout = new LinearLayoutManager(this);
        messageView.setLayoutManager(messageLayout);

        // Create and set adapter
        List<User> messageList = db.getInvite(userId, "TRUE");
        messageList.addAll(db.getMentor(userId, "TRUE"));
        // Add some invitation items to the list
        //messageList.add(new MessageItem(new InvitationItem("Elena Rodriguez", "UX Designer"), "elana.rodriquez@univiz.com", "+1 (916) 693-5829" ));
        //messageList.add(new MessageItem(new InvitationItem("Oliver Bennett", "Chief Technology Officer (CTO)"), "oliver.bennett@techxperts.com", "+65 3952 2507"));
        //messageList.add(new MessageItem(new InvitationItem("Lucas Morales", "Operations Manager"), "lucas.morales@iconet.com", "+60 12 6750 7326"));
        MessageAdapter messageAdapter = new MessageAdapter(messageList);
        messageView.setAdapter(messageAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

