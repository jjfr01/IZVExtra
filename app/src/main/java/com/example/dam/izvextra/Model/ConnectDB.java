package com.example.dam.izvextra.Model;


import android.content.Context;
import android.os.AsyncTask;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.View.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ConnectDB {

    private ArrayList<Excursion> excs;
    private ArrayList<Group> grps;
    private ArrayList<Teacher> tchs;

    public ConnectDB() {
    }

    public void getExcusionJson(final Context context) {

        AsyncTask<String, Void, String> taskGet = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... string) {


                return descargarDBJson("http://json-franor21.c9users.io:8080/Excursiones", "GET");
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                String json = s;

                JSONArray jsonarray = null;

                try {

                    jsonarray = new JSONArray(json);

                } catch (Exception e) {

                    e.printStackTrace();

                }

                excs = jsonArraytoArrayListExc(jsonarray);

                ((MainActivity) context).setArrayExc(excs);


                    getGroupJson(context);

            }
        };

        taskGet.execute();
    }

    public void getGroupJson(final Context context) {

        AsyncTask<String, Void, String> taskGet = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... string) {


                return descargarDBJson("http://json-franor21.c9users.io:8080/Grupos", "GET");
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                String json = s;

                JSONArray jsonarray = null;

                try {

                    jsonarray = new JSONArray(json);

                } catch (Exception e) {

                    e.printStackTrace();

                }

                grps = jsonArraytoArrayListGrp(jsonarray);

                ((MainActivity) context).setArrayGroups(grps);

                getTeacherJson(context);

            }
        };

        taskGet.execute();
    }

    public void getTeacherJson(final Context context) {

        AsyncTask<String, Void, String> taskGet = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... string) {


                return descargarDBJson("http://json-franor21.c9users.io:8080/Profesores", "GET");
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                String json = s;

                JSONArray jsonarray = null;

                try {

                    jsonarray = new JSONArray(json);

                } catch (Exception e) {

                    e.printStackTrace();

                }

                tchs = jsonArraytoArrayListTch(jsonarray);

                ((MainActivity) context).setArrayTeachers(tchs);

                ((MainActivity) context).defaultFragment();

            }
        };

        taskGet.execute();
    }

    private String descargarDBJson(String link, String method) {

        String result = "";

        try {

            URL url = new URL(link);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(method);

            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            conn.connect();

            InputStream is = conn.getInputStream();

            if (is != null) {

                result = readStream(is);

            }

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        return result;
    }

    private String readStream(InputStream is) {

        String aux = "";

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String datos = "";

            while ((datos = reader.readLine()) != null) {

                aux = aux + datos;
            }

            reader.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return aux;
    }

    private ArrayList<Excursion> jsonArraytoArrayListExc(JSONArray jsonarray) {

        ArrayList<Excursion> array = new ArrayList<>();

        for (int i = 0; i < jsonarray.length(); i++) {

            try {

                JSONObject newJsonobject = jsonarray.getJSONObject(i);

                Excursion newexc = new Excursion(newJsonobject.getString("description")
                        , newJsonobject.getString("place")
                        , newJsonobject.getString("date")
                        , newJsonobject.getString("hour")
                        , newJsonobject.getString("groups")
                        , newJsonobject.getString("teachers")
                        , Integer.parseInt(newJsonobject.getString("id")));

                array.add(newexc);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        return array;
    }

    private ArrayList<Group> jsonArraytoArrayListGrp(JSONArray jsonarray) {

        ArrayList<Group> array = new ArrayList<>();

        for (int i = 0; i < jsonarray.length(); i++) {

            try {

                JSONObject newJsonobject = jsonarray.getJSONObject(i);

                Group newexc = new Group(newJsonobject.getString("grupo")
                        , Integer.parseInt(newJsonobject.getString("id")));

                array.add(newexc);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        return array;
    }

    private ArrayList<Teacher> jsonArraytoArrayListTch(JSONArray jsonarray) {

        ArrayList<Teacher> array = new ArrayList<>();

        for (int i = 0; i < jsonarray.length(); i++) {

            try {

                JSONObject newJsonobject = jsonarray.getJSONObject(i);

                Teacher newexc = new Teacher(newJsonobject.getString("nombre")
                        , Integer.parseInt(newJsonobject.getString("id")));

                array.add(newexc);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        return array;
    }

}
