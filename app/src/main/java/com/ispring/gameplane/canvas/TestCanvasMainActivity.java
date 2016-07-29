package com.ispring.gameplane.canvas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ispring.gameplane.R;


public class TestCanvasMainActivity extends Activity implements Button.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_canvas_activity_main);
    }

    @Override
    public void onClick(View v) {
        CustomView.DrawMode drawMode;
        switch (v.getId()){
            case R.id.btnDrawAxis:
                drawMode = CustomView.DrawMode.AXIS;
                break;
            case R.id.btnDrawARGB:
                drawMode = CustomView.DrawMode.ARGB;
                break;
            case R.id.btnDrawText:
                drawMode = CustomView.DrawMode.TEXT;
                break;
            case R.id.btnDrawPoint:
                drawMode = CustomView.DrawMode.POINT;
                break;
            case R.id.btnDrawLine:
                drawMode = CustomView.DrawMode.LINE;
                break;
            case R.id.btnDrawRect:
                drawMode = CustomView.DrawMode.RECT;
                break;
            case R.id.btnDrawCircle:
                drawMode = CustomView.DrawMode.CIRCLE;
                break;
            case R.id.btnDrawOval:
                drawMode = CustomView.DrawMode.OVAL;
                break;
            case R.id.btnDrawArc:
                drawMode = CustomView.DrawMode.ARC;
                break;
            case R.id.btnDrawPath:
                drawMode = CustomView.DrawMode.PATH;
                break;
            case R.id.btnDrawBitmap:
                drawMode = CustomView.DrawMode.BITMAP;
                break;
            default:
                drawMode = CustomView.DrawMode.UNKNOWN;
                break;
        }
        Intent intent = new Intent(this, CanvasActivity.class);
        intent.putExtra("drawMode", drawMode.value());
        startActivity(intent);
    }
}