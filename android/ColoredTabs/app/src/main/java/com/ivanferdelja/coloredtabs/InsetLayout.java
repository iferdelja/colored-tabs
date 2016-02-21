package com.ivanferdelja.coloredtabs;


import android.content.Context;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.RelativeLayout;

public class InsetLayout extends RelativeLayout {

    private boolean consumed = false;

    public InsetLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        if (!consumed) {
            setPadding(getPaddingLeft(), getPaddingTop(),// + insets.getSystemWindowInsetTop(),
                    getPaddingRight(), getPaddingBottom());
            insets = insets.consumeSystemWindowInsets().consumeStableInsets();
            consumed = true;
        }
        return super.onApplyWindowInsets(insets);
    }
}
