package com.josecuentas.android_animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.josecuentas.android_animations.animation.AnimationListenerAdapter;

public class Animation3Activity extends AppCompatActivity {

    private static final String TAG = "Animation3Activity";
    private RelativeLayout mRlaContent;
    private RelativeLayout mRlaFooter;
    private ImageView mIviKeyboard;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation3);
        injectView();
        setup();
        events();
    }
    private void events() {
        mRlaFooter.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Log.d(TAG, "onClick: ");
            }
        });

        findViewById(R.id.button_showkeyboard).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                showKeyboard();
            }
        });

        findViewById(R.id.button_hidekeyboard).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                hideKeyboard();
            }
        });
    }

    private void setup() {
        setupUI();
    }

    private void setupUI() {
        mRlaFooter.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override public void onGlobalLayout() {
                        // hide keyboard without animation
                        ObjectAnimator.ofFloat(mRlaFooter, "Y",  mRlaFooter.getBottom())
                                .setDuration(0)
                                .start();
                    }
                });
    }

    private void injectView() {
        mRlaContent = (RelativeLayout) findViewById(R.id.content);
        mRlaFooter = (RelativeLayout) findViewById(R.id.footer);
        mIviKeyboard = (ImageView) findViewById(R.id.imageview_keyboard);
    }

    private void showKeyboard() {
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(mRlaFooter, "Y",  mRlaFooter.getTop());
        objectAnimatorY.setDuration(300);
        objectAnimatorY.start();
    }

    private void hideKeyboard() {
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(mRlaFooter, "Y",  mRlaFooter.getBottom());
        objectAnimatorY.setDuration(300);
        objectAnimatorY.start();
    }
}
