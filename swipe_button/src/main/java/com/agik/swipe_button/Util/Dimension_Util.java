package com.agik.swipe_button.Util;

import android.content.Context;
import android.util.TypedValue;

public class Dimension_Util {

    public static float spToPx(int sp, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                context.getResources().getDisplayMetrics());
    }
}
