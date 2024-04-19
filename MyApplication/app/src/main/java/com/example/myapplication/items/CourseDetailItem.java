package com.example.myapplication.items;

public class CourseDetailItem {
    private int courseID;
    private int certID;
    private String courseName;
    private String courseDesc;
    public CourseDetailItem(int courseID, int certID, String courseName, String courseDesc){
        this.courseID = courseID;
        this.certID = certID;
        this.courseName = courseName;
        this.courseDesc = courseDesc;

    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCertID() {
        return certID;
    }

    public void setCertID(int certID) {
        this.certID = certID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }
}
