package com.example.myapplication.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.items.CourseDetailItem;

public class FragmentCourseDetails extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_course_details);
        setTitle("Courses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView courseName = findViewById(R.id.course_title);
        TextView courseDesc = findViewById(R.id.course_desc);

        SharedPreferences sharedPreferences = getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        String currCourse = sharedPreferences.getString("course_name", null);
        int userId = sharedPreferences.getInt("user_id", 0);

        db = new DatabaseHelper(FragmentCourseDetails.this);
        CourseDetailItem course = db.getCourse(currCourse);

        courseName.setText(course.getCourseName());
        courseDesc.setText(course.getCourseDesc());


        Button enrolButton = findViewById(R.id.enrolButton);
        // Inflate the layout for this fragment
        enrolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a toast message
                db.enrolCourse(userId, course);
                Toast.makeText(FragmentCourseDetails.this,"You are enrolled into the course!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                SharedPreferences sharedPreferences = getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("course_name");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}