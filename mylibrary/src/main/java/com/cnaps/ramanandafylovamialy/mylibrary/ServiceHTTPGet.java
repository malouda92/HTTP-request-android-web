package com.cnaps.ramanandafylovamialy.mylibrary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceHTTPGet extends AsyncTask<String, Void, Integer> {

    private Context context;
    private ProgressDialog pb;
    private String responseServer;
    private int responseCode;
    public OnTaskCompleted listener;

    public ServiceHTTPGet(){}

    public ServiceHTTPGet(Context context){
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

        try{
            URL url = new URL(strings[0]);
            HttpURLConnection connexion = (HttpURLConnection)url.openConnection();
            connexion.setRequestMethod("GET");
            this.responseCode = connexion.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            StringBuilder sb =new StringBuilder();
            String line;
            while((line = reader.readLine())!= null){
                sb.append(line);
            }
            this.responseServer = sb.toString();

        }catch(Exception e){
            e.printStackTrace();
        }
        return this.responseCode;
    }

    @Override
    protected void onPostExecute(Integer code){
        pb.hide();
        if(code == 200){
            this.listener.onTaskCompleted(this.responseServer);
        }
    }
}
