package com.example.dam.izvextra.Presenter;


import android.content.Context;

import com.example.dam.izvextra.Model.ConnectDB;
import com.example.dam.izvextra.Model.Pojo.Excursion;

import java.util.ArrayList;

public class Contract {

    public Contract() {
    }

    public void getArrays(Context context){

        ConnectDB connectDB = new ConnectDB();

        connectDB.getExcusionJson(context);
    }


}
