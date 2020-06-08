package com.cnaps.ramanandafylovamialy.mylibrary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class ServiceHTTP extends AsyncTask<String, Void, Integer> {

    protected Context context;
    protected String responseServer;
    protected ProgressDialog pb;
    public OnTaskCompleted listener;


    public ServiceHTTP(Context context){
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
        return null;
    }

    @Override
    protected void onPostExecute(Integer status){
        this.pb.hide();
        this.listener.onTaskCompleted(this.responseServer);
    }
}
