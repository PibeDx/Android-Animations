package com.josecuentas.android_animations;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.view.View;
import android.widget.Button;

/*
* base in: http://www.uwanttolearn.com/android/constraint-layout-animations-dynamic-constraints-ui-java-hell/
* */

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButApply, mButReset;
    private Button mButton;
    private ConstraintLayout mConstraintLayout;
    private ConstraintSet mApplyConstraintSet = new ConstraintSet();
    private ConstraintSet mResetConstraintSet = new ConstraintSet();
    private View mView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);


        mConstraintLayout = (ConstraintLayout) findViewById(R.id.main);
        mButApply = (Button) findViewById(R.id.butApply);
        mButReset = (Button) findViewById(R.id.butReset);
        mView = findViewById(R.id.button);
        mButton = (Button) findViewById(R.id.button2);

        mResetConstraintSet.clone(mConstraintLayout);
        mApplyConstraintSet.clone(mConstraintLayout );

        mButApply.setOnClickListener(this);
        mButReset.setOnClickListener(this);

    }

    public void onApplyClick(View view) {
        TransitionManager.beginDelayedTransition(mConstraintLayout);
        mApplyConstraintSet.clear(R.id.button);
        mApplyConstraintSet.connect(R.id.button,ConstraintSet.TOP,R.id.main,ConstraintSet.TOP,8);
        /*mApplyConstraintSet.connect(R.id.button, ConstraintSet.RIGHT, R.id.main, ConstraintSet.RIGHT, 8);*/
        mApplyConstraintSet.centerHorizontally(R.id.button, R.id.main);

        /*//mApplyConstraintSet.connect(R.id.button,ConstraintSet.LEFT,R.id.main,ConstraintSet.LEFT,0);
        mApplyConstraintSet.connect(R.id.button,ConstraintSet.RIGHT,R.id.main,ConstraintSet.RIGHT,0);
        //mApplyConstraintSet.connect(R.id.button,ConstraintSet.TOP,R.id.main,ConstraintSet.TOP,0);
        mApplyConstraintSet.connect(R.id.button,ConstraintSet.BOTTOM,R.id.main,ConstraintSet.BOTTOM,0);*/

        mApplyConstraintSet.constrainWidth(R.id.button,ConstraintSet.WRAP_CONTENT);
        mApplyConstraintSet.constrainHeight(R.id.button,ConstraintSet.WRAP_CONTENT);

        //mApplyConstraintSet.setMargin(R.id.button, ConstraintSet.START, 500);
        mApplyConstraintSet.applyTo(mConstraintLayout);
    }

    public void onResetClick(View view) {
        TransitionManager.beginDelayedTransition(mConstraintLayout);
        mResetConstraintSet.applyTo(mConstraintLayout);
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butApply:
                onApplyClick(mView);
                break;
            case R.id.butReset:
                onResetClick(mView);
                break;
        }
    }
}