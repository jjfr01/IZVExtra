package com.example.dam.izvextra.Presenter;


import android.content.Context;

import com.example.dam.izvextra.Model.ConnectDB;
import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.PrintPDF;

public class Contract {

    public Contract() {
    }

    public void getArrays(Context context) {

        ConnectDB connectDB = new ConnectDB();

        connectDB.getExcusionJson(context);
    }

    public void postExc(Context context, Excursion exc) {

        ConnectDB connectDB = new ConnectDB();

        connectDB.postJson(context, exc);

    }

    public void putExc(Context context, Excursion exc, int id) {

        ConnectDB connectDB = new ConnectDB();

        connectDB.putJson(context, exc, id);

    }

    public void deleteExc(Context context, int id) {

        ConnectDB connectDB = new ConnectDB();

        connectDB.deleteJson(context, id);

    }

    public void printPDF(Context context, Excursion exc){

        PrintPDF printPDF = new PrintPDF();

        printPDF.createPDFFile(context, exc);

    }


}
