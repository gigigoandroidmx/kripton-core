package com.gigigo.kbase.presentation.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

/**
 * @author Juan Godínez Vera - 6/30/2017.
 */
public class ComponentTextViewAnimator {

    private final Context context;

    private TextView view;

    public ComponentTextViewAnimator(Context context) {
        this.context = context;
    }

    public void setView(TextView view) {
        if(view == null) return;

        this.view = view;
        this.view.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(this.view) {

            @Override
            public boolean onDrawableClick() {
                hideView();
                return false;
            }
        });
    }

    public void hideView() {
        if(view == null) return;
        view.setVisibility(View.GONE);
        view.setAlpha(0.f);
    }

    public void showView(Throwable throwable) {
        if(view == null || throwable == null) return;

        view.setText(throwable.getMessage());

        final ObjectAnimator show = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, -100f, 0f);
        show.setInterpolator(new BounceInterpolator());
        show.setDuration(1000);
        show.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
                view.setAlpha(1.f);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.VISIBLE);
                view.setAlpha(1.f);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                view.setVisibility(View.GONE);
                view.setAlpha(0.f);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        show.start();
    }
}
