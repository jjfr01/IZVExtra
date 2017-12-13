package com.example.dam.izvextra.Model.Pojo;


import android.os.Parcel;
import android.os.Parcelable;

public class Excursion implements Parcelable {


    private String description, place, date, hour, groups, teachers;
    private int id;

    public Excursion() {
    }

    public Excursion(String description, String place, String date, String hour, String groups, String teachers, int id) {
        this.description = description;
        this.place = place;
        this.date = date;
        this.hour = hour;
        this.groups = groups;
        this.teachers = teachers;
        this.id = id;
    }

    protected Excursion(Parcel in) {
        description = in.readString();
        place = in.readString();
        date = in.readString();
        hour = in.readString();
        groups = in.readString();
        teachers = in.readString();
        id = in.readInt();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeString(place);
        parcel.writeString(date);
        parcel.writeString(hour);
        parcel.writeString(groups);
        parcel.writeString(teachers);
        parcel.writeInt(id);
    }
}
