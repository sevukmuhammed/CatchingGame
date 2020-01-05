package com.example.kennyoyun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

public class ScoresActivity extends AppCompatActivity {
    ListView scorelist;
    DataBase db;
    Typeface tf;
    TextView scores;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_scores);

        tf = Typeface.createFromAsset(getAssets(),"font/bauhaus.ttf");
        scorelist = findViewById(R.id.scorelist);
        scores = findViewById(R.id.scores);
        scores.setTypeface(tf);

        db = new DataBase(this);
        List<String> veriler = db.display();
        adapter = new ArrayAdapter<String>(ScoresActivity.this,R.layout.row,android.R.id.text1,veriler);
        scorelist.setAdapter(adapter);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(ScoresActivity.this,WelcomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}

