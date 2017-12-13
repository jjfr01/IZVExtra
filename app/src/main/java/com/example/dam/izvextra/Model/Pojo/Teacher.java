package com.example.dam.izvextra.Model.Pojo;


import android.os.Parcel;
import android.os.Parcelable;

public class Teacher implements Parcelable {

    private String nombre;
    private int id;

    public Teacher() {}

    public Teacher(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    protected Teacher(Parcel in) {
        nombre = in.readString();
        id = in.readInt();
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        parcel.writeString(nombre);
        parcel.writeInt(id);
    }
}
