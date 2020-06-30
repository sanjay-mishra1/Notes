package com.sanproject.notes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginPinActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String type=getIntent().getStringExtra("TYPE");
        if (type.equals("NEW_PIN"))
        {
            setContentView(R.layout.new_pin_layout);
            newPin();
        }
        else {
            setContentView(R.layout.activity_pin);
            checkPin();
        }

    }

    private void checkPin() {
        SharedPreferences sharedPreferences=getSharedPreferences("MYDATA",MODE_PRIVATE);
        final String pin=sharedPreferences.getString("PIN",null);
        if (pin==null)
        {
            Toast.makeText(this,"Pin not found",Toast.LENGTH_SHORT).show();
        }
        final EditText editText=findViewById(R.id.pin);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pinEdit=editText.getText().toString().trim();
                if (pin.equals(pinEdit)){
                    startActivity(new Intent(LoginPinActivity.this,MainActivity.class));
                }else{
                    Toast.makeText(LoginPinActivity.this,"Pin not found.",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void newPin() {
        final SharedPreferences sharedPreferences=getSharedPreferences("MYDATA",MODE_PRIVATE);
        final EditText pinEdit=findViewById(R.id.pin);
        final EditText pinAgainEdit=findViewById(R.id.pinAgain);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin=pinEdit.getText().toString().trim();
                String pinAgain=pinAgainEdit.getText().toString().trim();
                if (pin.isEmpty())
                {
                    Toast.makeText(LoginPinActivity.this,"Please enter pin.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pinAgain.isEmpty())
                {
                    Toast.makeText(LoginPinActivity.this,"Please enter pin again.",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pin.equals(pinAgain)){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("PIN",pin);
                    editor.apply();
                    startActivity(new Intent(LoginPinActivity.this,MainActivity.class));
                }else{
                    Toast.makeText(LoginPinActivity.this,"Both pin should be same.",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
