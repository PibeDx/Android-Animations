package com.josecuentas.android_animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Animation2Activity extends AppCompatActivity {

    private TextView mTextviewTitle;
    private TextView mTextviewFooter;
    private ProgressBar mProgressbar;
    private Button mButtonAnimation;
    private Button mButtonReset;
    private ViewGroup mRootView;

    private int mShorAnimationDuration;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation2);
        injectView();
        setup();
        events();

    }

    private void events() {
        mButtonAnimation.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                animation();
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                animationReset();
            }
        });
    }

    private void setup() {
        mShorAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);
        mRootView.setVisibility(View.GONE);
    }

    private void animation() {
        mRootView.setAlpha(0f);
        mRootView.setVisibility(View.VISIBLE);

        mRootView.animate()
                .alpha(1f)
                .setDuration(mShorAnimationDuration)
                .setListener(null);

        mProgressbar.animate()
                .alpha(0f)
                .setDuration(mShorAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override public void onAnimationEnd(Animator animation) {
                        mProgressbar.setVisibility(View.GONE);
                        mProgressbar.setAlpha(1f);
                    }
                });
    }

    private void animationReset() {
        mProgressbar.setAlpha(0f);
        mProgressbar.setVisibility(View.VISIBLE);

        mProgressbar.animate()
                .alpha(1f)
                .setDuration(mShorAnimationDuration)
                .setListener(null);

        mRootView.animate()
                .alpha(0f)
                .setDuration(mShorAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override public void onAnimationEnd(Animator animation) {
                        mRootView.setVisibility(View.GONE);
                        mRootView.setAlpha(1f);
                    }
                });
    }

    private void injectView() {
        mTextviewTitle = (TextView) findViewById(R.id.textview_title);
        mTextviewFooter = (TextView) findViewById(R.id.textview_footer);
        mProgressbar = (ProgressBar) findViewById(R.id.progressbar);
        mButtonAnimation = (Button) findViewById(R.id.button_animation);
        mButtonReset = (Button) findViewById(R.id.button_reset);
        mRootView = (ViewGroup) findViewById(R.id.content);
    }
}
