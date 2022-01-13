package com.kulesh.sharedwithyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SharedPreferences preferences=getSharedPreferences("com.kulesh.sharedwithyou",MODE_PRIVATE);
        SharedPreferences.OnSharedPreferenceChangeListener listener=new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                System.out.println(s);
            }
        };
        preferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void send(View view) {
        SharedPreferences preferences=getSharedPreferences("com.kulesh.sharedwithyou",MODE_PRIVATE);
        SharedPreferences.Editor e=preferences.edit();
        User u1=new User(1,"Kulesh","0775683945");
        User u2=new User(2,"Alex Rider","0766726789");
        Gson gson=new Gson();
        String json1=gson.toJson(u1,User.class);
        String json2=gson.toJson(u2,User.class);
        e.putString("json1",json1);
        e.putString("json2",json2);
        e.apply();
    }

    public void receive(View view) {
        SharedPreferences preferences=getSharedPreferences("com.kulesh.sharedwithyou",MODE_PRIVATE);
        String json=preferences.getString("json1","NOT FOUND");
        String json2=preferences.getString("json2","NOT FOUND");
        Gson gson=new Gson();
        User u=gson.fromJson(json,User.class);
        User u2=gson.fromJson(json2,User.class);
        Map<String ,?>m=preferences.getAll();
        Gson g=new Gson();
        for(Map.Entry<String,?>e:m.entrySet()){
            String key=e.getKey();
            String value=e.getValue().toString();
            User ui=g.fromJson(value,User.class);
            TextView tv=findViewById(R.id.txt);
            tv.setText(ui.getId()+" "+ui.getName()+" "+ui.getMobile());
        }
    }

    public void remove(View view) {
        SharedPreferences preferences=getSharedPreferences("com.kulesh.sharedwithyou",MODE_PRIVATE);
        SharedPreferences.Editor e=preferences.edit();
        e.remove("json1");
        e.clear();
        e.apply();
        Toast.makeText(getApplicationContext(), "Removed!", Toast.LENGTH_SHORT).show();
    }
}