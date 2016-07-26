package com.ispring.gameplane;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by qixinh on 16/7/26.
 */
public class CustomClickView extends View {
    //触摸事件相关的变量
    //一次单击事件由DOWN和UP两个事件合成，假设从down到up间隔小于200毫秒，我们就认为发生了一次单击事件
    private static final int singleClickDurationTime = 200;
    //一次双击事件由两个点击事件合成，两个单击事件之间小于300毫秒，我们就认为发生了一次双击事件
    private static final int doubleClickDurationTime = 300;
    private long lastSingleClickTime = -1;//上次发生单击的时刻
    private long touchDownTime = -1;//触点按下的时刻
    private long touchUpTime = -1;//触点弹起的时刻
    private float touchX = -1;//触点的x坐标
    private float touchY = -1;//触点的y坐标

    public CustomClickView(Context context) {
        super(context);
    }

    public CustomClickView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomClickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchType touchType=resovleTouchType(event);
        return super.onTouchEvent(event);
    }
//自定义双击或者是长按单击事件
    private TouchType resovleTouchType(MotionEvent event) {
        //合成我们想要的事件类型

        TouchType touchType = TouchType.SINGLE_CLICK;
        int action = event.getAction();
        touchX = event.getX();
        touchY = event.getY();
        if (action == MotionEvent.ACTION_MOVE) {
            long deltaTime = System.currentTimeMillis() - touchDownTime;
            if (deltaTime > singleClickDurationTime) {
                //触点移动
                touchType =TouchType.MOVE;
            }
        } else if (action == MotionEvent.ACTION_DOWN) {
            //触点按下
            touchDownTime = System.currentTimeMillis();
        } else if (action == MotionEvent.ACTION_UP) {
            //触点弹起
            touchUpTime = System.currentTimeMillis();
            //计算触点按下到触点弹起之间的时间差
            long downUpDurationTime = touchUpTime - touchDownTime;
            //如果此次触点按下和抬起之间的时间差小于一次单击事件指定的时间差，
            //那么我们就认为发生了一次单击
            if (downUpDurationTime <= singleClickDurationTime) {
                //计算这次单击距离上次单击的时间差
                long twoClickDurationTime = touchUpTime - lastSingleClickTime;

                if (twoClickDurationTime <= doubleClickDurationTime) {
                    //如果两次单击的时间差小于一次双击事件执行的时间差，
                    //那么我们就认为发生了一次双击事件
                    touchType = TouchType.DOUBLE_CLICK;
                    //重置变量
                    lastSingleClickTime = -1;
                    touchDownTime = -1;
                    touchUpTime = -1;
                } else {
                    //如果这次形成了单击事件，但是没有形成双击事件，那么我们暂不触发此次形成的单击事件
                    //我们应该在doubleClickDurationTime毫秒后看一下有没有再次形成第二个单击事件
                    //如果那时形成了第二个单击事件，那么我们就与此次的单击事件合成一次双击事件
                    //否则在doubleClickDurationTime毫秒后触发此次的单击事件
                    lastSingleClickTime = touchUpTime;
                    touchType=TouchType.SINGLE_CLICK;
                }
            }
        }
        return touchType;
    }


    enum TouchType {
        MOVE, SINGLE_CLICK, DOUBLE_CLICK
    }
}
