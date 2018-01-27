package com.hxh.sensor;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Vibrator_Activity extends AppCompatActivity
{
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_vibrator);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    //加震动权限
    //第一个参数，指代一个震动的频率数组。每两个为一组，每组的第一个为等待时间，第二个为震动时间。
    // 比如 [2000,500,100,400],会先等待2000毫秒，震动500毫秒，再等待100毫秒，震动400毫秒
    //第二个参数，repest指代从 第几个索引（第一个数组参数） 的位置开始循环震动。
    //vibrator.vibrate(new long[]{300,500},0);
    //会一直保持循环，我们需要用 vibrator.cancel()主动终止
    //设置为-1只震动一次

    public void one(View v)
    {
        vibrator.vibrate(new long[]{500, 100, 500, 300}, 0);
    }

    public void two(View v)
    {
        vibrator.vibrate(new long[]{500, 500, 500, 300, 500, 100}, -1);
    }

    public void stop(View v)
    {
        vibrator.cancel();
    }
}
