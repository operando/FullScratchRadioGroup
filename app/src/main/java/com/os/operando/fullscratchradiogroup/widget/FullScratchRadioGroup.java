package com.os.operando.fullscratchradiogroup.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.os.operando.fullscratchradiogroup.R;

public class FullScratchRadioGroup extends LinearLayout {

    private int checkedId = -1;
    private boolean protectFromCheckedChange;
    private OnCheckedChangeListener onCheckedChangeListener;
    private CompoundButton.OnCheckedChangeListener childOnCheckedChangeListener;
    private PassThroughHierarchyChangeListener passThroughHierarchyChangeListener;

    public FullScratchRadioGroup(Context context) {
        super(context);
        setOrientation(VERTICAL);
        init();
    }

    public FullScratchRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(
                attrs, R.styleable.FullScratchRadioGroup, R.attr.fullScratchRadioButtonStyle, 0);

        final int checkedViewId = attributes.getResourceId(R.styleable.FullScratchRadioGroup_checkedButton, View.NO_ID);
        if (checkedViewId != View.NO_ID) {
            checkedId = checkedViewId;
        }

        final int orientation = attributes.getInt(R.styleable.FullScratchRadioGroup_orientation, VERTICAL);
        setOrientation(orientation);

        attributes.recycle();
        init();
    }

    private void init() {
        childOnCheckedChangeListener = new CheckedStateTracker();
        passThroughHierarchyChangeListener = new PassThroughHierarchyChangeListener();
        super.setOnHierarchyChangeListener(passThroughHierarchyChangeListener);
    }

    @Override
    public void setOnHierarchyChangeListener(OnHierarchyChangeListener listener) {
        passThroughHierarchyChangeListener.onHierarchyChangeListener = listener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (checkedId != -1) {
            protectFromCheckedChange = true;
            setCheckedStateForView(checkedId, true);
            protectFromCheckedChange = false;
            setCheckedId(checkedId);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof FullScratchRadioButton) {
            final FullScratchRadioButton button = (FullScratchRadioButton) child;
            if (button.isChecked()) {
                protectFromCheckedChange = true;
                if (checkedId != -1) {
                    setCheckedStateForView(checkedId, false);
                }
                protectFromCheckedChange = false;
                setCheckedId(button.getId());
            }
        }

        super.addView(child, index, params);
    }

    public void check(@IdRes int id) {
        if (id != -1 && id == checkedId) {
            return;
        }

        if (checkedId != -1) {
            setCheckedStateForView(checkedId, false);
        }

        if (id != -1) {
            setCheckedStateForView(checkedId, true);
        }

        setCheckedId(id);
    }

    private void setCheckedId(@IdRes int id) {
        checkedId = id;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(this, checkedId);
        }
    }

    private void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView != null && checkedView instanceof FullScratchRadioButton) {
            ((FullScratchRadioButton) checkedView).setChecked(checked);
        }
    }

    @IdRes
    public int getCheckedFullScratchRadioButtonId() {
        return checkedId;
    }

    public void clearCheck() {
        check(View.NO_ID);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(int width, int height, float weight) {
            super(width, height, weight);
        }

        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        @Override
        protected void setBaseAttributes(TypedArray a, int widthAttr, int heightAttr) {
            super.setBaseAttributes(a, widthAttr, heightAttr);

            if (a.hasValue(widthAttr)) {
                width = a.getLayoutDimension(widthAttr, "layout_width");
            } else {
                width = WRAP_CONTENT;
            }

            if (a.hasValue(heightAttr)) {
                height = a.getLayoutDimension(heightAttr, "layout_height");
            } else {
                height = WRAP_CONTENT;
            }
        }
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(FullScratchRadioGroup fullScratchRadioGroup, @IdRes int checkedId);
    }

    private class CheckedStateTracker implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (protectFromCheckedChange) {
                return;
            }

            protectFromCheckedChange = true;
            if (checkedId != -1) {
                setCheckedStateForView(checkedId, false);
            }
            protectFromCheckedChange = false;

            int id = compoundButton.getId();
            setCheckedId(id);
        }
    }

    private class PassThroughHierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {

        private ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener;

        @Override
        public void onChildViewAdded(View parent, View child) {
            if (parent == FullScratchRadioGroup.this && child instanceof FullScratchRadioButton) {
                int id = child.getId();
                if (id == View.NO_ID) {
                    // 後方互換のためにhashCodeで代用
                    // 最近の実装は以下のようにView.generateViewIdメソッドで生成してる
                    // View.generateViewIdはAPI Level 17から使える
                    // https://developer.android.com/reference/android/view/View.html#generateViewId()
                    // id = View.generateViewId();
                    id = child.hashCode();
                    child.setId(id);
                }
                ((FullScratchRadioButton) child).setOnCheckedChangeWidgetListener(
                        childOnCheckedChangeListener);
            }

            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(parent, child);
            }
        }

        @Override
        public void onChildViewRemoved(View parent, View child) {
            if (parent == FullScratchRadioGroup.this && child instanceof FullScratchRadioButton) {
                ((FullScratchRadioButton) child).setOnCheckedChangeWidgetListener(null);
            }

            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(parent, child);
            }
        }
    }
}