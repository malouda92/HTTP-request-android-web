package com.cnaps.ramanandafylovamialy.kotrana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cnaps.ramanandafylovamialy.mylibrary.OnTaskCompleted;
import com.cnaps.ramanandafylovamialy.mylibrary.ServiceHTTPPost;
import com.cnaps.ramanandafylovamialy.mylibrary.User;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText username = null;
    EditText password = null;
    Button btnValider = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.username = findViewById(R.id.username);
        this.password = findViewById(R.id.password);
        this.btnValider = findViewById(R.id.btn);

        this.btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    User user = new User(0, username.getText().toString(),password.getText().toString());
                    JSONObject jso = new JSONObject();
                    try{
                        jso.put("username", user.getUsername());
                        jso.put("password", user.getPassword());
                        ServiceHTTPPost serviceHTTPPost = new ServiceHTTPPost();
                        serviceHTTPPost.execute("http://192.168.88.229:8000/api/user/post/",jso.toString());
                        serviceHTTPPost.setListener(new OnTaskCompleted() {
                            @Override
                            public void onTaskCompleted(String response) {
                                Toast.makeText(getApplicationContext(),"" + response, Toast.LENGTH_LONG).show();
                            }
                        });
                    }catch (Exception e){
                        Log.e("Tag", "" + e.getMessage());
                    }
                }
            }
        });
    }
}
