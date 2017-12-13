package com.example.dam.izvextra.Model.Pojo;


import android.os.Parcel;
import android.os.Parcelable;

public class Group implements Parcelable {

    private String grupo;
    private int id;

    public Group() {}

    public Group(String grupo, int id) {
        this.grupo = grupo;
        this.id = id;
    }

    protected Group(Parcel in) {
        grupo = in.readString();
        id = in.readInt();
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

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
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
        parcel.writeString(grupo);
        parcel.writeInt(id);
    }
}
