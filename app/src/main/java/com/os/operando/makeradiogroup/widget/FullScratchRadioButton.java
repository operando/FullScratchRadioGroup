package com.os.operando.makeradiogroup.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.CompoundButton;

public class FullScratchRadioButton extends CompoundButton {

    private OnCheckedChangeListener onCheckedChangeWidgetListener;

    public FullScratchRadioButton(Context context) {
        super(context);
    }

    public FullScratchRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScratchRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // onTouchEventのオーバーライドしないとtouch eventが飛ばない.Why??
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                performClick();
                break;
        }
        return true;
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        if (onCheckedChangeWidgetListener != null) {
            onCheckedChangeWidgetListener.onCheckedChanged(this, checked);
        }
    }

    @Override
    public void toggle() {
        if (!isChecked()) {
            super.toggle();
        }
    }

    /**
     * Register a callback to be invoked when the checked state of this button
     * changes. This callback is used for internal purpose only.
     *
     * @param listener the callback to call on checked state change
     */
    void setOnCheckedChangeWidgetListener(OnCheckedChangeListener listener) {
        onCheckedChangeWidgetListener = listener;
    }
}
