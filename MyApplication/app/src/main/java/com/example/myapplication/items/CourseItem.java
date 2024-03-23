package com.example.myapplication.items;

public class CourseItem {
    private String courseName;
    private String courseType;
    private int courseProgress;

    public CourseItem(String courseName, String courseType, int courseProgress) {
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseProgress = courseProgress;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public int getCourseProgress() {
        return courseProgress;
    }

    public void setCourseProgress(int courseProgress) {
        this.courseProgress = courseProgress;
    }
}
