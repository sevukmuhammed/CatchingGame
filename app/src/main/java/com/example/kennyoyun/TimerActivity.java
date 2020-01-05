package com.example.kennyoyun;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class TimerActivity extends AppCompatActivity {
    TextView text;
    Typeface t;
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
        setContentView(R.layout.activity_timer);

        text = findViewById(R.id.text);
        t = Typeface.createFromAsset(getAssets(),"font/bauhaus.ttf");
        text.setTypeface(t);

        new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int temp = (int)millisUntilFinished/1000;
                if(temp != 1 )
                {
                    text.setText((temp-1) + "");
                }
                else
                {
                    text.setText("Go!");
                    Intent intent = new Intent(TimerActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFinish(){}
        }.start();
    }
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(TimerActivity.this);
        builder.setTitle("Warning!");
        builder.setMessage("Are you sure you want to quit?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.kenny);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
