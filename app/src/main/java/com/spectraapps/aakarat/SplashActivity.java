package com.spectraapps.aakarat;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.spectraapps.aakarat.util.ListSharedPreference;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends Activity {

    private boolean isFirstRun;
    private Button button_ar,button_en;
    private ListSharedPreference.Set setSharedPreference;
    private ListSharedPreference.Get getSharedPreference;

    @BindView(R.id.splach_screen)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        ButterKnife.bind(SplashActivity.this);
        Picasso.get().load(R.drawable.logo).into(imageView);

        setSharedPreference = new ListSharedPreference.Set(SplashActivity.this.getApplicationContext());
        getSharedPreference = new ListSharedPreference.Get(SplashActivity.this.getApplicationContext());

        isFirstRun = getSharedPreference.getIsFirstRun();

        button_ar = findViewById(R.id.btn_ar);
        button_en = findViewById(R.id.btn_en);


        insertAnimation();
        initButtonClickListener();

    }
    private void initButtonClickListener() {

        button_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSharedPreference.setLanguage("en");
                setSharedPreference.setIsFirstRun(false);

                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        button_ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSharedPreference.setLanguage("ar");
                setSharedPreference.setIsFirstRun(false);

                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void insertAnimation() {

        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_anim);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(1500);

        ImageView splash = findViewById(R.id.splach_screen);
        splash.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (isFirstRun) {
                    button_ar.setVisibility(View.VISIBLE);
                    button_en.setVisibility(View.VISIBLE);
                } else {
                    button_ar.setVisibility(View.INVISIBLE);
                    button_en.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isFirstRun) {
                    YoYo.with(Techniques.Bounce).playOn(button_ar);
                    YoYo.with(Techniques.Bounce).playOn(button_en);
                } else {
                    if (getSharedPreference.getIsLogged()) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }//end insert anim


}
