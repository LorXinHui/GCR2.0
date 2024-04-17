package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.items.CourseItem;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
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
