package com.sanproject.notes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNewWebsite extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private EditText editTextTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);
        editText=findViewById(R.id.editUrl);
        editTextTitle=findViewById(R.id.title);
        button=findViewById(R.id.button);
        WebView webView=findViewById(R.id.webview);
        if (!getIntent().getStringExtra("URL").equals("NEW")){
            button.setText("Delete");
            button.setVisibility(View.GONE);
            setTitle(getIntent().getStringExtra("NOTE_TITLE").replace("Website",""));
            editText.setEnabled(false);
            editText.setText(getIntent().getStringExtra("NOTE_DESC"));
            editTextTitle.setText(getIntent().getStringExtra("NOTE_TITLE"));
            editTextTitle.setVisibility(View.GONE);
            webView.loadUrl(getIntent().getStringExtra("NOTE_DESC"));
        }else{
            setTitle(R.string.new_website);
        }
    }

    public void webviewBt(View view) {
        SharedPreferences sharedPreferences=getSharedPreferences("MY_NOTES",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if (button.getText().toString().equalsIgnoreCase("delete")){
            editor.remove("Website "+editTextTitle.getText().toString().trim());
            editor.apply();
            finish();
            Toast.makeText(this, "Note created successfully.",Toast.LENGTH_SHORT).show();
        }else{
            if (!editText.getText().toString().trim().isEmpty()&& !editTextTitle.getText().toString().trim().isEmpty()){
                editor.putString("Website "+editTextTitle.getText().toString().trim(),editText.getText().toString().trim());
                editor.apply();
                finish();
                Toast.makeText(this, "Note created successfully.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
