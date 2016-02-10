package com.ivanferdelja.coloredtabs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            init(attrs);
        }
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            init(attrs);
        }
    }

    public CustomTextView(Context context) {
        super(context);
        if (!isInEditMode()) {
            init(null);
        }
    }

    protected void init(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, new int[]{R.attr.typeface});
        String typefaceName = array.getString(0);
        if (!TextUtils.isEmpty(typefaceName)) {
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), typefaceName + ".ttf"));
        }
        array.recycle();
    }
}
