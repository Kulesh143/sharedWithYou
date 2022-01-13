package com.kulesh.sharedwithyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences=getSharedPreferences("com.kulesh.sharedwithyou",MODE_PRIVATE);
        SharedPreferences.OnSharedPreferenceChangeListener listener=new SharedPreferences.OnSharedPreferenceChangeListener(){
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                System.out.println(s);
            }
        };
        preferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void sendJSON(View view) {
        SharedPreferences preferences=getSharedPreferences("com.kulesh.sharedwithyou",MODE_PRIVATE);
        SharedPreferences.Editor e=preferences.edit();
        User u1=new User(1,"Kulesh","0773562890");
        User u2=new User(4,"Alex","0773465790");
        User u3=new User(5,"Sarah","0745622890");
        Gson gson=new Gson();
        String json1=gson.toJson(u1);
        String json2=gson.toJson(u2);
        String json3=gson.toJson(u3);
        e.putString("u1",json1);
        e.putString("u2",json2);
        e.putString("u3",json3);
        e.apply();
    }

    public void readJSon(View view) {
        SharedPreferences preferences=getSharedPreferences("com.kulesh.sharedwithyou",MODE_PRIVATE);
        String json=preferences.getString("u1","Not Found");
        String json2=preferences.getString("u2","Not Found");
        String json3=preferences.getString("u3","Not Found");
        Gson gson=new Gson();
        User user=gson.fromJson(json,User.class);
        User user2=gson.fromJson(json2,User.class);
        User user3=gson.fromJson(json3,User.class);
        Map<String ,?>data=preferences.getAll();
        Gson g=new Gson();
        for(Map.Entry<String,?>E:data.entrySet()){
            String key=E.getKey();
            String value=E.getValue().toString();
            User userQ=gson.fromJson(value,User.class);
            System.out.println(userQ.getId()+" "+userQ.getName()+" "+userQ.getMobile());
            TextView tv=findViewById(R.id.txt);
            tv.setText(userQ.getId()+" "+userQ.getName()+" "+userQ.getMobile());
        }

    }

    public void Remove(View view) {
        SharedPreferences preferences=getSharedPreferences("com.kulesh.sharedwithyou",MODE_PRIVATE);
        SharedPreferences.Editor e=preferences.edit();
        e.remove("u1");
        e.remove("u2");
        e.remove("u3");
        e.clear();
        e.apply();
        Toast.makeText(getApplicationContext() ,"Removed", Toast.LENGTH_SHORT).show();
    }
}