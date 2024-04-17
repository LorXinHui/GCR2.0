package com.example.myapplication.items;

public class CourseItem {
    private String courseName;
    private String courseType;
    private int courseProgress;
    private String courseDesc;

    public CourseItem(String courseName, String courseType, int courseProgress, String courseDesc) {
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseProgress = courseProgress;
        this.courseDesc = courseDesc;
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

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }
}