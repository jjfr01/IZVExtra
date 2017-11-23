package com.example.dam.izvextra.Model.Pojo;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Excursion implements Parcelable {

    private ArrayList<Teacher> teachers;
    private ArrayList<Group> groups;
    private String description, place, hour;
    private Date date;

    public Excursion() {
    }

    public Excursion(ArrayList<Teacher> teachers, ArrayList<Group> groups, String description, String place, String hour, Date date) {
        this.teachers = teachers;
        this.groups = groups;
        this.description = description;
        this.place = place;
        this.hour = hour;
        this.date = date;
    }

    protected Excursion(Parcel in) {
        teachers = in.createTypedArrayList(Teacher.CREATOR);
        groups = in.createTypedArrayList(Group.CREATOR);
        description = in.readString();
        place = in.readString();
        hour = in.readString();
    }

    public static final Creator<Excursion> CREATOR = new Creator<Excursion>() {
        @Override
        public Excursion createFromParcel(Parcel in) {
            return new Excursion(in);
        }

        @Override
        public Excursion[] newArray(int size) {
            return new Excursion[size];
        }
    };

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(teachers);
        parcel.writeTypedList(groups);
        parcel.writeString(description);
        parcel.writeString(place);
        parcel.writeString(hour);
    }
}
