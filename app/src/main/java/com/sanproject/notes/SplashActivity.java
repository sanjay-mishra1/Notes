package com.sanproject.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent=new Intent(this,LoginPinActivity.class);
        if(getSharedPreferences("MYDATA",MODE_PRIVATE).getString("PIN",null)==null)
        {   intent.putExtra("TYPE","NEW_PIN");
            startActivity(intent);
        }
        else {
            intent.putExtra("TYPE","ENTER_PIN");
            startActivity(intent);
        }
    }
}
