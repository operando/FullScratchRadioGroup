package com.os.operando.makeradiogroup.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CompoundButton;

public class FullScratchRadioButton extends CompoundButton {

    public FullScratchRadioButton(Context context) {
        super(context);
    }

    public FullScratchRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScratchRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FullScratchRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
