package com.agik.swipe_button.Controller;

import com.agik.swipe_button.View.Swipe_Button_View;

public interface OnSwipeCompleteListener {
    void onSwipe_Forward(Swipe_Button_View swipe_button_view);
    void onSwipe_Reverse(Swipe_Button_View swipe_button_view);
}
