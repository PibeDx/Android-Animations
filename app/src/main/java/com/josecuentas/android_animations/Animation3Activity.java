package com.josecuentas.android_animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
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
    private View mVieKeyboardBar;

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

        mVieKeyboardBar.setOnTouchListener(new View.OnTouchListener() {
            @Override public boolean onTouch(View v, MotionEvent event) {
                //cantidad de movimiento en Y
                float touchY = event.getY();
                //posicion actual de footer en Y
                float keyboardY = mRlaFooter.getY();
                //altura del footer
                int keyboardHeight = mRlaFooter.getHeight();
                //altura del contenedor principal
                int contentHeight = mRlaContent.getHeight();
                //posicion inicial de keyboard en Y
                int keyboardInitY = contentHeight - keyboardHeight;
                //posicion nueva tras el movimiento en Y de keyboard
                float keyboardNewPositionY = keyboardY + touchY;

                //Si la position inicial en Y de keyboard es mayor igual
                //ha la nueva posicion, setteamos la posicion inicial
                if (keyboardInitY >= keyboardNewPositionY) {
                    //restart position keyboard
                    mRlaFooter.setY(keyboardInitY);
                } else {
                    mRlaFooter.setY(keyboardY + touchY);
                }
                switch (event.getAction()) {
                    //Al soltar el touch
                    case MotionEvent.ACTION_UP:
                        //Si se mueve el keyboard mas de la mitad para abajo y se suelta se oculta el teclado
                        if (keyboardY > contentHeight - (keyboardHeight/2)) {
                            //ocultar el teclado
                            hideKeyboard();
                        } else {
                            //mostrar el teclado
                            showKeyboard();
                        }
                        break;
                }
                return true;
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
        mVieKeyboardBar = findViewById(R.id.view_keyboard_bar);
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
