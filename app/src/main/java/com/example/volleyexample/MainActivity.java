package com.example.volleyexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//JSON - data transfering format
//javascript object notation
public class MainActivity extends AppCompatActivity {

    private TextView txtMain;
    private Button btnGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGet=findViewById(R.id.btnGet);
        txtMain=findViewById(R.id.txtMain);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }
    private void getData(){
         final Gson gson = new Gson();

        //Requesting data from the website using String request
        String url = "https://jsonplaceholder.typicode.com/posts";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                txtMain.setText(response);
//                Post[] post = gson.fromJson(response, Post[].class);
//                for(Post p:post){
//                    txtMain.setText(txtMain.getText()+"\n"+p.getTitle());
//                }
                Post post=gson.fromJson(response, Post.class);
                txtMain.setText(post.getTitle());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>posts=new HashMap<String, String>();
                posts.put("id", "6");
                posts.put("userid", "102");
                posts.put("title", "New Post");
                posts.put("body", "the body of the new post");
                return posts;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
        queue.start();
    }
}