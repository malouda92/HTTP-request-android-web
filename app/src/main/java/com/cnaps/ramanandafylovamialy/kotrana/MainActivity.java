package com.cnaps.ramanandafylovamialy.kotrana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cnaps.ramanandafylovamialy.mylibrary.OnTaskCompleted;
import com.cnaps.ramanandafylovamialy.mylibrary.ServiceHTTPPost;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText username = null;
    EditText password = null;
    Button btnValider = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.username = (EditText)findViewById(R.id.username);
        this.password = (EditText)findViewById(R.id.password);
        this.btnValider = (Button)findViewById(R.id.btn);

        this.btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jso = new JSONObject();
                try{
                    jso.put("username", username.getText().toString());
                    jso.put("password", password.getText().toString());
                    ServiceHTTPPost serviceHTTPPost = new ServiceHTTPPost(MainActivity.this);
                    serviceHTTPPost.execute("http://192.168.88.229:8000/api/user/post/",jso.toString());
                    serviceHTTPPost.listener = new OnTaskCompleted() {
                        @Override
                        public void onTaskCompleted(String response) {
                            Toast.makeText(getApplicationContext(),"" + response, Toast.LENGTH_LONG).show();
                        }
                    };
                }catch (Exception e){

                }
            }
        });
    }
}
