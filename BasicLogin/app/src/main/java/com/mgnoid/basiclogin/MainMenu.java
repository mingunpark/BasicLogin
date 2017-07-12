package com.mgnoid.basiclogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void userInfo(View v){
        Intent intent = new Intent(MainMenu.this, UserInfo.class);

        startActivity(intent);

    }
}
