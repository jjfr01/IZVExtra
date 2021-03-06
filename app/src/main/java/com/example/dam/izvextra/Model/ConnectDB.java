package com.example.dam.izvextra.Model;


import android.content.Context;
import android.os.AsyncTask;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.View.EditActivity;
import com.example.dam.izvextra.View.MainActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

                ((MainActivity) context).defaultFragment();

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

            }
        };

        taskGet.execute();
    }

    public void postJson(final Context context, final Excursion exc) {

        AsyncTask<Void, Void, Void> taskAddJson = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                postAndPutJsonToDBJson("http://json-franor21.c9users.io:8080/Excursiones", "POST", exc);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                ((EditActivity)context).finish();

            }
        };

        taskAddJson.execute();

    }

    public void putJson(final Context context, final Excursion exc, final int id) {

        AsyncTask<Void, Void, Void> taskPut = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                postAndPutJsonToDBJson("http://json-franor21.c9users.io:8080/Excursiones/" + id, "PUT", exc);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                ((EditActivity) context).finish();

            }
        };

        taskPut.execute();

    }

    public void deleteJson(final Context context, final int id) {

        AsyncTask<Void, Void, Void> taskDelete = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                deleteJsonFromDBJson("http://json-franor21.c9users.io:8080/Excursiones/" + id, "DELETE", context);

                return null;
            }
        };

        taskDelete.execute();

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

        if (jsonarray != null) {

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
        }

        return array;
    }

    private ArrayList<Group> jsonArraytoArrayListGrp(JSONArray jsonarray) {

        ArrayList<Group> array = new ArrayList<>();

        if (jsonarray != null) {


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

        }

        return array;
    }

    private ArrayList<Teacher> jsonArraytoArrayListTch(JSONArray jsonarray) {

        ArrayList<Teacher> array = new ArrayList<>();

        if (jsonarray != null) {

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

        }

        return array;
    }

    private void postAndPutJsonToDBJson(String link, String method, Excursion exc) {

        Gson gson = new Gson();

        String json = gson.toJson(exc);

        try {

            URL url = new URL(link);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(method);

            conn.setDoOutput(true);

            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            conn.connect();


            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");//Para mandar los datos, en este caso, para mandar el json
            osw.write(json);
            osw.flush();//Poner para que todos los datos se mandan
            osw.close();

            conn.getInputStream();//Sin esto, sabe Dios, no funciona


        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

    private void deleteJsonFromDBJson(String link, String method, Context context) {

        try {

            URL url = new URL(link);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(method);

            conn.setDoOutput(true);

            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            conn.connect();

            conn.getInputStream();//Sin esto, sabe Dios, no funciona


        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

}
