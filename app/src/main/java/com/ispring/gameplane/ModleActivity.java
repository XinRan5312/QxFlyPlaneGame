package com.ispring.gameplane;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by qixinh on 16/7/29.
 */
public class ModleActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modle);
        TextView modle = (TextView) findViewById(R.id.modle);
        TextView desnityTV = (TextView) findViewById(R.id.denity);
        TextView desnityDpi = (TextView) findViewById(R.id.denity_dpi);
        TextView widthTV = (TextView) findViewById(R.id.width);
        TextView heightTV = (TextView) findViewById(R.id.height);
        modle.setText(android.os.Build.MODEL);
        DisplayMetrics marty = getResources().getDisplayMetrics();
        float density=marty.density;
        int densityDPI=marty.densityDpi;
        int width=marty.widthPixels;
        int height=marty.heightPixels;
        desnityTV.setText(""+density);
        desnityDpi.setText(""+densityDPI);
        widthTV.setText(""+width);
        heightTV.setText(""+height);

    }
}
