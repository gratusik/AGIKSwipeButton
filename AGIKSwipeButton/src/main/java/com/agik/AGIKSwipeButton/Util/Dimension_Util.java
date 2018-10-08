package com.agik.AGIKSwipeButton.Util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Gratus on 02/10/18.
 */

public class Dimension_Util {

    public static float spToPx(int sp, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                context.getResources().getDisplayMetrics());
    }
}
