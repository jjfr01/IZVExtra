package com.example.dam.izvextra.Model.Pojo;


import android.os.Parcel;
import android.os.Parcelable;

public class Teacher implements Parcelable {

    private String nameTeacher;
    private int idTeacher;

    public Teacher() {}

    public Teacher(String nameTeacher, int idTeacher) {
        this.nameTeacher = nameTeacher;
        this.idTeacher = idTeacher;
    }

    protected Teacher(Parcel in) {
        nameTeacher = in.readString();
        idTeacher = in.readInt();
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameTeacher);
        parcel.writeInt(idTeacher);
    }
}
