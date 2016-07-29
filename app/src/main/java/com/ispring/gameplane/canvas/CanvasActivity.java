package com.ispring.gameplane.canvas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.ispring.gameplane.R;


public class CanvasActivity extends Activity {

    private CustomView customView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        customView = (CustomView)findViewById(R.id.myView);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android);
        customView.setBitmap(bitmap);

        Intent intent = getIntent();
        if(intent != null){
            CustomView.DrawMode drawMode = CustomView.DrawMode.valueOf(intent.getIntExtra("drawMode", 0));
            customView.setDrawMode(drawMode);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(customView != null){
            customView.destroy();
            customView = null;
        }
    }
}
