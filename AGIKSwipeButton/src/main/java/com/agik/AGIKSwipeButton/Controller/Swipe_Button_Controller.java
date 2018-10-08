package com.agik.AGIKSwipeButton.Controller;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.agik.AGIKSwipeButton.View.Swipe_Button_View;

/**
 * Created by Gratus on 02/10/18.
 */

public class Swipe_Button_Controller extends AppCompatSeekBar {

    private Drawable thumb;
    private OnSwipeCompleteListener listener_forward_reverse;
    private Swipe_Button_View swipe_button_view;

    public Swipe_Button_Controller(Context context) {
        super(context);
    }

    public Swipe_Button_Controller(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Swipe_Button_Controller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setThumb(Drawable thumb) {
        this.thumb = thumb;
        super.setThumb(thumb);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (thumb.getBounds().contains((int) event.getX(), (int) event.getY())) {
                // This fixes an issue where the parent view (e.g ScrollView) receives
                // touch events along with the SlideView
                getParent().requestDisallowInterceptTouchEvent(true);
                super.onTouchEvent(event);
            } else {
                return false;
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getProgress() > 85) {
                if(swipe_button_view.swipe_both_direction){
                    if (listener_forward_reverse != null) {
                        if (!swipe_button_view.swipe_reverse) {
                            swipe_button_view.swipe_reverse = true;
                            swipe_button_view.setreverse_180();
                            listener_forward_reverse.onSwipe_Forward(swipe_button_view);
                        }
                        else{
                            swipe_button_view.setreverse_0();
                            swipe_button_view.swipe_reverse = false;
                            listener_forward_reverse.onSwipe_Reverse(swipe_button_view);
                        }
                    }
                }
                else {
                    if (listener_forward_reverse != null) {
                        if (!swipe_button_view.swipe_reverse) {
                            listener_forward_reverse.onSwipe_Forward(swipe_button_view);
                        }
                        if (swipe_button_view.swipe_reverse) {
                            listener_forward_reverse.onSwipe_Reverse(swipe_button_view);
                        }
                    }
                }
            }
            getParent().requestDisallowInterceptTouchEvent(false);
            setProgress(0);
        }
        else
            super.onTouchEvent(event);

        return true;
    }

    public void setOnSwipeCompleteListener_forward_reverse(OnSwipeCompleteListener listener_forward_reverse, Swipe_Button_View swipe_button_view) {
        this.listener_forward_reverse = listener_forward_reverse;
        this.swipe_button_view = swipe_button_view;
    }

    @Override
    public Drawable getThumb() {
        // getThumb method was added in SDK 16 but our minSDK is 14
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return super.getThumb();
        } else {
            return thumb;
        }
    }
}