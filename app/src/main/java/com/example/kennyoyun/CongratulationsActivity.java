package com.example.kennyoyun;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CongratulationsActivity extends AppCompatActivity {
    Typeface tf1;
    Button scores,play;
    TextView score;
    Bundle bundle;
    String sc;
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
        setContentView(R.layout.activity_congratulations);

        play = findViewById(R.id.play);
        scores = findViewById(R.id.scores);
        score = findViewById(R.id.score);
        tf1 = Typeface.createFromAsset(getAssets(),"font/bauhaus.ttf");
        play.setTypeface(tf1);
        scores.setTypeface(tf1);
        score.setTypeface(tf1);

        bundle = getIntent().getExtras();
        if(bundle != null)
        {
            sc = (String) bundle.get("score");
        }
        score.setText(sc);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CongratulationsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        scores.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CongratulationsActivity.this,ScoresActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(CongratulationsActivity.this, WelcomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
