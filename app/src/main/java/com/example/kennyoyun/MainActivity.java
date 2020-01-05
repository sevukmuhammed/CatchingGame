package com.example.kennyoyun;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DataBase db;
    TextView textView,textView2;
    ImageView imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    int score=0;
    Typeface t;
    MediaPlayer mediaPlayer;
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
        setContentView(R.layout.activity_main);

        db = new DataBase(this);

        t = Typeface.createFromAsset(getAssets(),"font/bauhaus.ttf");

        textView = findViewById(R.id.textView);
        textView.setTypeface(t);
        textView2 = findViewById(R.id.textView2);
        textView2.setTypeface(t);
        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);

        imageArray = new ImageView[]{imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};

        hideImages();
        new CountDownTimer(25000,1000){

            @Override
            public void onTick(long millisUntilFinished)
            {
                textView.setText("" + millisUntilFinished/1000);
            }
            @Override
            public void onFinish()
            {
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                String str = textView2.getText().toString();

                db.insertScore(str);
                boolean result = db.checkBigger(str);
                if(result == true)
                {
                    Intent intent = new Intent(MainActivity.this,CongratulationsActivity.class);
                    intent.putExtra("score",str);
                    startActivity(intent);
                }
                else
                {
                    int best = db.bestScore();
                    Intent intent = new Intent(MainActivity.this,TryAgainActivity.class);
                    intent.putExtra("score",str);
                    intent.putExtra("bestScore",best);
                    startActivity(intent);
                }
            }
        }.start();
    }
    public void countScore(View view)
    {
        buttonSound(view);
        score++;
        textView2.setText(score + "");
    }
    public void hideImages()
    {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                int i = (int)(Math.random()*9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
    public void buttonSound(View view)
    {
        mediaPlayer = MediaPlayer.create(this,R.raw.sound1);
        mediaPlayer.start();
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
