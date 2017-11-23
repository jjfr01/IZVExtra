package com.example.dam.izvextra.Model.Pojo;


import android.os.Parcel;
import android.os.Parcelable;

public class Group implements Parcelable {

    private String nameGroup;
    private int idGroup;

    public Group() {}

    public Group(String nameGroup, int idGroup) {
        this.nameGroup = nameGroup;
        this.idGroup = idGroup;
    }

    protected Group(Parcel in) {
        nameGroup = in.readString();
        idGroup = in.readInt();
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameGroup);
        parcel.writeInt(idGroup);
    }
}
