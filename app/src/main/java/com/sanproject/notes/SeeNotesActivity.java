package com.sanproject.notes;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SeeNotesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_notes);
        TextView textViewTitle=findViewById(R.id.note_title);
        textViewTitle.setText(getIntent().getStringExtra("NOTE_TITLE"));
        TextView textViewNote=findViewById(R.id.note_desc);
        textViewNote.setText(getIntent().getStringExtra("NOTE_DESC"));
    }
}
