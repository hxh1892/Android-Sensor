package com.hxh.sensor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main_Activity extends AppCompatActivity
{
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }

    public void cam(View v)
    {
        startActivity(new Intent(mContext, CAF_Activity.class));
    }

    public void vib(View v)
    {
        startActivity(new Intent(mContext, Vibrator_Activity.class));
    }

    public void other(View v)
    {
        startActivity(new Intent(mContext, SensorCheck_Activity.class));
    }
}
