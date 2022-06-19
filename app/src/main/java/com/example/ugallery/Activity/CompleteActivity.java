package com.example.ugallery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ugallery.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CompleteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        Toolbar toolbar  = findViewById(R.id.toolbar_com);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.actionbar_background);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompleteActivity.this, EmailActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}