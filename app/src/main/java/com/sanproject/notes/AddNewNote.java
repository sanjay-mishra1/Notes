package com.sanproject.notes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNewNote extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDesc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
         editTextTitle=findViewById(R.id.note_title);
         editTextDesc=findViewById(R.id.note_desc);
        setTitle(R.string.new_note);
    }
    public void addNewNote(View view) {
        String title=editTextTitle.getText().toString().trim();
        if (title.isEmpty())
        {
            Toast.makeText(this,R.string.enter_title,Toast.LENGTH_SHORT).show();
            return;
        }
        String desc=editTextDesc.getText().toString().trim();
        if (desc.isEmpty())
        {   Toast.makeText(this, R.string.enter_note ,Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences sharedPreferences =getSharedPreferences("MY_NOTES",MODE_PRIVATE);
        if (sharedPreferences.contains(title))
        {   Toast.makeText(this, R.string.new_title,Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(title,desc);
        editor.apply();
        finish();
        Toast.makeText(this, "Note created successfully.",Toast.LENGTH_SHORT).show();
    }
}
