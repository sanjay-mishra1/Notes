package com.sanproject.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadListView();
    }

    private void loadListView() {
        SharedPreferences sharedPreferences =getSharedPreferences("MY_NOTES",MODE_PRIVATE);

        ListView listView=findViewById(R.id.listview);
        final HashMap<String,Object> notes= (HashMap<String, Object>) sharedPreferences.getAll();
        Log.e("NoteHash","=>"+sharedPreferences.getAll());
        final Object[] data= sharedPreferences.getAll().keySet().toArray();
        final String[] notesData=new String[data.length];
        for(int i=0;i<data.length;i++)
            notesData[i]= (String) data[i];
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                notesData
        );
        Log.e("Note", Arrays.toString(data));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                if (notesData[position].startsWith("Website"))
                {
                    intent =new Intent(MainActivity.this,AddNewWebsite.class);
                    intent.putExtra("URL","SHOW");
                }
                else
                intent =new Intent(MainActivity.this,SeeNotesActivity.class);
                intent.putExtra("NOTE_TITLE",(notesData[position]));
                intent.putExtra("NOTE_DESC",(String)notes.get(data[position]));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadListView();
    }

    public void changePin(View view) {
        Intent intent =new Intent(MainActivity.this,LoginPinActivity.class);
        intent.putExtra("TYPE","NEW_PIN");
        startActivity(intent);
    }

    public void addNewNote(View view) {
        startActivity(new Intent(this,AddNewNote.class));
    }

    public void addNewWebsite(View view) {
        Intent intent= new Intent(this,AddNewWebsite.class);
        intent.putExtra("URL","NEW");
        startActivity(intent);
    }
}
