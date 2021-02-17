package com.example.animation2;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.ImageFormat;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.Image;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private int mSoundCollision = 1;
    private int mStreamId;
    View start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton start = (ImageButton) findViewById(R.id.start);
        ImageButton fire = (ImageButton) findViewById(R.id.fire);


        createNewSoundPool() ;
        mSoundPool.load(this, R.raw.beep, 1);

        Animation IB_rise  =  AnimationUtils.loadAnimation(this, R.anim.rocket_fly);
        ImageButton IB = (ImageButton) findViewById(R.id.imageButton);

        fire.setOnClickListener(onPlayButtonClickListener);
        start.setOnClickListener(onPlayButtonClickListener1);

        // Получим ссылку на солнце
        ImageView sunImageView = (ImageView) findViewById(R.id.sun);
        // Анимация для восхода солнца
        Animation sunRiseAnimation = AnimationUtils.loadAnimation(this, R.anim.sun_rise);
        // Подключаем анимацию к нужному View
        sunImageView.startAnimation(sunRiseAnimation);

        ImageView clockImageView = (ImageView) findViewById(R.id.clock);
// анимация для вращения часов
        Animation clockTurnAnimation = AnimationUtils.loadAnimation(this, R.anim.clock_turn);
        clockImageView.startAnimation(clockTurnAnimation);

        // получим ссылку на часовую стрелку
        ImageView hourImageView = (ImageView) findViewById(R.id.hour_hand);
// анимация для стрелки
        Animation hourTurnAnimation = AnimationUtils.loadAnimation(this, R.anim.hour_turn);
// присоединяем анимацию
        hourImageView.startAnimation(hourTurnAnimation);



        IB.startAnimation(IB_rise);


    }



    View.OnClickListener onPlayButtonClickListener1 = new View.OnClickListener (){
        @Override
        public void onClick(View v) {

        }
    };


    View.OnClickListener onPlayButtonClickListener = new View.OnClickListener (){
        @Override
        public void onClick(View v) {
            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            mStreamId = mSoundPool.play(mSoundCollision,1,1,1,0,1);
            //Toast.makeText(getApplicationContext(), "soundPool.play()", Toast.LENGTH_LONG).show();



        }
    };
    @TargetApi(Build.VERSION_CODES.LOLLIPOP) //вызов класса SoundPool.Builder
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    private class IB_rise {
    }
}
