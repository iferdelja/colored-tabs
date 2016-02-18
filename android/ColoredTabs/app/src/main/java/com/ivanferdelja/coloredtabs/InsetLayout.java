package com.ivanferdelja.coloredtabs;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowInsets;
import android.widget.RelativeLayout;

public class InsetLayout extends RelativeLayout {
    public InsetLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {

        Log.d("coloredtabs", "windowinsets " + insets.toString());
        setPadding(getPaddingLeft(), getPaddingTop() + insets.getSystemWindowInsetTop(),
                getPaddingRight(), getPaddingBottom());
        insets.consumeSystemWindowInsets();
        return super.onApplyWindowInsets(insets);
    }
}
