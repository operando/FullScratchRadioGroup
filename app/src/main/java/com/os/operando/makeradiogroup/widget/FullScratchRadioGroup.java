package com.os.operando.makeradiogroup.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class FullScratchRadioGroup extends LinearLayout {

    private OnCheckedChangeListener onCheckedChangeListener;

    public FullScratchRadioGroup(Context context) {
        super(context);
    }

    public FullScratchRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScratchRadioGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FullScratchRadioGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }


    public interface OnCheckedChangeListener {
        void onCheckedChanged(FullScratchRadioGroup fullScratchRadioGroup, @IdRes int checkedId);
    }
}