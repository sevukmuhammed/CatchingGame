package com.example.kennyoyun;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class TryAgainActivity extends AppCompatActivity {
    TextView score2,score3;
    Button tryagain,scores2;
    Typeface t;
    Bundle bundle;
    String sc;
    int best=0;
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
        setContentView(R.layout.activity_try_again);

        tryagain = findViewById(R.id.tryagain);
        scores2 = findViewById(R.id.scores2);
        score2 = findViewById(R.id.score2);
        score3 = findViewById(R.id.score3);
        t = Typeface.createFromAsset(getAssets(),"font/bauhaus.ttf");
        score2.setTypeface(t);
        tryagain.setTypeface(t);
        scores2.setTypeface(t);
        score3.setTypeface(t);

        bundle = getIntent().getExtras();
        if(bundle != null)
        {
            sc = (String) bundle.get("score");
            best = (int) bundle.get("bestScore");
        }
        score3.setText(sc);
        score2.setText(best + "");
        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TryAgainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        scores2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(TryAgainActivity.this,ScoresActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed()
    {

        Intent intent = new Intent(TryAgainActivity.this, WelcomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
