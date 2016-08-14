package com.os.operando.fullscratchradiogroup;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.os.operando.fullscratchradiogroup.widget.FullScratchRadioGroup;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FullScratchRadioGroup radioGroup = (FullScratchRadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new FullScratchRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(FullScratchRadioGroup fullScratchRadioGroup, @IdRes int checkedId) {
                Log.d(TAG, "onCheckedChanged : checkedId = " + checkedId);
            }
        });
    }
}
