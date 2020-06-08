package com.cnaps.ramanandafylovamialy.mylibrary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceHTTPPost extends ServiceHTTP {

    private String responseServer;

    public ServiceHTTPPost(){
        super();
    }

    public ServiceHTTPPost(Context context){
        super(context);
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(String... strings) {
        int responseCode = 0;
        try{
            URL url = new URL(strings[0]);
            HttpURLConnection connexion = (HttpURLConnection)url.openConnection();
            connexion.setRequestMethod("POST");
            connexion.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connexion.getOutputStream());

            outputStreamWriter.write(strings[1]);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            responseCode = connexion.getResponseCode();
            this.responseServer = connexion.getResponseMessage();

        }catch(Exception e){
            e.printStackTrace();
        }
        return responseCode;
    }

    @Override
    protected void onPostExecute(Integer status){
        super.onPostExecute(status);
        if(status == 201){
            this.listener.onTaskCompleted(this.responseServer);
        }
    }
}
