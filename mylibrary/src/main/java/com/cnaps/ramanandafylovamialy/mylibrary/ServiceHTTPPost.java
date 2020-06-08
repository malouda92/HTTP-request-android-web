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

public class ServiceHTTPPost extends AsyncTask<String, Void, Integer> {

    private Context context;
    private ProgressDialog pb;
    private String responseServer;
    public OnTaskCompleted listener;

    public ServiceHTTPPost(){}

    public ServiceHTTPPost(Context context){
        this.context = context.getApplicationContext();
    }

    @Override
    protected void onPreExecute(){
        pb = new ProgressDialog(this.context);
        pb.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pb.setMessage("en cours");
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
        pb.hide();
        if(status == 201){
            this.listener.onTaskCompleted(this.responseServer);
        }
    }
}
