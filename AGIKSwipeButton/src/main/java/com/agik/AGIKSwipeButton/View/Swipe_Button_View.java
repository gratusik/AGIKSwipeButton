package com.agik.AGIKSwipeButton.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.agik.AGIKSwipeButton.Controller.OnSwipeCompleteListener;
import com.agik.AGIKSwipeButton.Controller.Swipe_Button_Controller;
import com.agik.AGIKSwipeButton.R;

import static com.agik.AGIKSwipeButton.Util.Dimension_Util.spToPx;
import static com.agik.AGIKSwipeButton.Util.Drawable_Color_Util.setDrawableColor;
import static com.agik.AGIKSwipeButton.Util.Drawable_Color_Util.setDrawableStroke;

/**
 * Created by Gratus on 02/10/18.
 */

public class Swipe_Button_View extends RelativeLayout implements SeekBar.OnSeekBarChangeListener {

    protected Swipe_Button_Controller swipe_button_controller;
    protected Drawable swipe_bg_drawable;
    protected Drawable thumb_bg_drawable;
    protected Drawable thumbImage;
    protected TextView swipeTextView;
    protected LayerDrawable swipeLayers;
    protected int strokeColor;
    public boolean swipe_reverse = false;
    public boolean swipe_both_direction = false;
    protected float swipeTextSize = spToPx(16, getContext());
    protected String swipeText;
    protected boolean animateSwipeText;
    protected int ThumbImageId,swipe_thumb_bg_color,swipe_bg_color,swipeTextColor;
    
    public Swipe_Button_View(Context context) {
        super(context);
        set_default(null, 0);
    }

    public Swipe_Button_View(Context context, AttributeSet attrs) {
        super(context, attrs);
        set_default(attrs, 0);
    }

    public Swipe_Button_View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        set_default(attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Swipe_Button_View(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        set_default(attrs, defStyleAttr);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (animateSwipeText) {
            swipeTextView.setAlpha(1 - (progress / 100f));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    
    void set_default(AttributeSet attrs, int defStyle) {
        inflate(getContext(), R.layout.sb_swipe_view, this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.sb_swipe_view_bg));
        } else {
            setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.sb_swipe_view_bg));
        }

        swipeTextView = findViewById(R.id.sb_text);
        swipe_button_controller = findViewById(R.id.sb_swipe);
        swipe_button_controller.setOnSeekBarChangeListener(this);
        swipe_bg_drawable = getBackground();
        swipeLayers = (LayerDrawable) swipe_button_controller.getThumb();
        thumb_bg_drawable = swipeLayers.findDrawableByLayerId(R.id.thumb_background);

        TypedArray attributes = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.SwipeButtonView,
                defStyle, defStyle);
        try {
            getAttributes(attributes);
        } finally {
            attributes.recycle();
        }
    }

    private void getAttributes(TypedArray attributes) {

        swipe_reverse = attributes.getBoolean(R.styleable.SwipeButtonView_sb_swipe_reverse, false);
        swipe_both_direction = attributes.getBoolean(R.styleable.SwipeButtonView_sb_swipe_both_direction, false);

        swipeText = attributes.getString(R.styleable.SwipeButtonView_sb_swipe_text);
        swipeTextColor = attributes.getColor(R.styleable.SwipeButtonView_sb_swipe_text_color, ContextCompat.getColor(getContext(), R.color.sb_text_color_default));
        animateSwipeText = attributes.getBoolean(R.styleable.SwipeButtonView_sb_swipe_animate_text, true);
        swipeTextSize = attributes.getDimension(R.styleable.SwipeButtonView_sb_swipe_text_size, swipeTextSize);
        swipeTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, swipeTextSize);
        setText(swipeText);
        setTextColor(swipeTextColor);

        ThumbImageId = attributes.getResourceId(R.styleable.SwipeButtonView_sb_thumb_image, R.drawable.ic_forward);
        setThumbImage(ContextCompat.getDrawable(getContext(), ThumbImageId));

        swipe_thumb_bg_color = attributes.getColor(R.styleable.SwipeButtonView_sb_thumb_bg_color, ContextCompat.
                getColor(getContext(), R.color.sb_thumb_button_color_default));
        setThumbBackgroundColor(swipe_thumb_bg_color);
        swipe_bg_color = attributes.getColor(R.styleable.SwipeButtonView_sb_swipe_bg_color, ContextCompat.
                getColor(getContext(), R.color.sb_swipe_bg_color_default));
        setSwipeBackgroundColor(swipe_bg_color);
        strokeColor = attributes.getColor(R.styleable.SwipeButtonView_sb_stroke_bg_color, ContextCompat.
                getColor(getContext(), R.color.sb_stroke_color_default));
        if (attributes.hasValue(R.styleable.SwipeButtonView_sb_stroke_bg_color)) {
            setDrawableStroke(swipe_bg_drawable, strokeColor);
        }

        if (swipe_reverse) {
            setreverse_180();
        }
    }

    public void setreverse_180(){
        swipe_button_controller.setRotation(180);
        set_text_position();
    }
    public void setreverse_0(){
        swipe_button_controller.setRotation(0);
        set_text_position();
    }

    private void set_text_position() {
        LayoutParams params = ((LayoutParams) swipeTextView.getLayoutParams());
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            params.addRule(RelativeLayout.ALIGN_PARENT_END, 0);
            params.addRule(RelativeLayout.ALIGN_PARENT_START);
        }
        swipeTextView.setLayoutParams(params);
    }

    public void setTextColor(@ColorInt int color) {
        swipeTextView.setTextColor(color);
    }

    public void setText(CharSequence text) {
        swipeTextView.setText(text);
    }

    public void setTextSize(int size) { swipeTextView.setTextSize(size); }

    public TextView getTextView() { return swipeTextView; }

    public void setThumbImage(Drawable image) {
        swipeLayers.setDrawableByLayerId(R.id.thumb_image, image);
    }

    public void setThumbBackgroundColor(int color) {
        setDrawableColor(thumb_bg_drawable, color);
    }

    public void setSwipeBackgroundColor(int color) {
        setDrawableColor(swipe_bg_drawable, color);
    }

    public Swipe_Button_Controller getSlider() {
        return swipe_button_controller;
    }

    public void setOnSwipeCompleteListener_forward_reverse(OnSwipeCompleteListener listener_forward_reverse) {
        swipe_button_controller.setOnSwipeCompleteListener_forward_reverse(listener_forward_reverse, this);
    }
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setEnabled(enabled);
        }
        swipeLayers.setDrawableByLayerId(R.id.thumb_image,ContextCompat.getDrawable(getContext(), ThumbImageId));
        setDrawableColor(thumb_bg_drawable, swipe_thumb_bg_color);
        setDrawableColor(swipe_bg_drawable, swipe_bg_color);
    }
}

