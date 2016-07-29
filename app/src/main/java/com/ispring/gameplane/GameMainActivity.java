package com.ispring.gameplane;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ispring.gameplane.game.GameConstans;


public class GameMainActivity extends Activity implements Button.OnClickListener {
private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= (Button) findViewById(R.id.setting);
        if(GameConstans.IS_NO_ENEMY==0){
            btn.setText("NO Enemy");

        }else{
            btn.setText("HAS Enemy");
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if(viewId == R.id.btnGame){
            startGame();
        }else if(viewId==R.id.setting){
            if(GameConstans.IS_NO_ENEMY==0){
                GameConstans.IS_NO_ENEMY=1;
                btn.setText("HAS Enemy");

            }else{
                GameConstans.IS_NO_ENEMY=0;
                btn.setText("NO Enemy");
            }
        }
    }

    public void startGame(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}