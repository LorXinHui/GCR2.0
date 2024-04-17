package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.FragmentCourseDetails;
import com.example.myapplication.items.CourseItem;

import java.util.List;

public class CourseDetailAdapter extends RecyclerView.Adapter<CourseDetailAdapter.CourseDetailViewHolder> {
    private List<CourseItem> courseItems;
    Context context;

    public CourseDetailAdapter(Context context, List<CourseItem> courseItems) {
        this.context = context;
        this.courseItems = courseItems;
    }

    @NonNull
    @Override
    public CourseDetailAdapter.CourseDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_detail_item, parent, false);
        return new CourseDetailAdapter.CourseDetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseDetailAdapter.CourseDetailViewHolder holder, int position) {
        CourseItem courseItem = courseItems.get(position);
        holder.nameTextView.setText(courseItem.getCourseName());
        holder.descTextView.setText(courseItem.getCourseDesc());
        holder.moreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FragmentCourseDetails.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseItems.size();
    }

    public static class CourseDetailViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descTextView;
        Button moreDetails;

        public CourseDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.course_title);
            descTextView = itemView.findViewById(R.id.course_desc);
            moreDetails = itemView.findViewById(R.id.more_det);
        }
    }
}
