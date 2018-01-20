package com.hxh.sensor;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    Context mContext = this;

    public static final int INTENT_CODE_PERMISSION_REQUEST = 100;

    Vibrator vibrator;

    Camera camera;
    Camera.Parameters parameters;

    SurfaceView sv;
    SurfaceHolder surfaceHolder;

    boolean isHaveFlashLight = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
            {

            }
            else
            {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        INTENT_CODE_PERMISSION_REQUEST);
            }
        }
        else
        {

        }

        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
        {
            //有闪光灯
            isHaveFlashLight = true;

            initCamera();
        }
        else
        {
            //无闪光灯
            isHaveFlashLight = false;

            Toast.makeText(mContext, "你的机型不支持闪光灯", Toast.LENGTH_SHORT).show();
        }
    }

    private void initCamera()
    {
        camera = Camera.open();
        parameters = camera.getParameters();

        sv = (SurfaceView) findViewById(R.id.sv);
        surfaceHolder = sv.getHolder();

        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        surfaceHolder.addCallback(new SurfaceHolder.Callback()
        {
            public void surfaceCreated(SurfaceHolder holder)
            {
                try
                {
                    camera.setPreviewDisplay(surfaceHolder);
                }
                catch (Exception e) {camera.release();}

                camera.startPreview();
            }

            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
            {
            }

            public void surfaceDestroyed(SurfaceHolder holder)
            {
            }
        });
    }

    //加震动权限
    //第一个参数，指代一个震动的频率数组。每两个为一组，每组的第一个为等待时间，第二个为震动时间。
    // 比如 [2000,500,100,400],会先等待2000毫秒，震动500，再等待100，震动400
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

    public void on(View v)
    {
        if (isHaveFlashLight)
        {
            try
            {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
            }
            catch (Exception e) {e.printStackTrace();}
        }
    }

    public void off(View v)
    {
        if (isHaveFlashLight)
        {
            try
            {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameters);
            }
            catch (Exception e) {e.printStackTrace();}
        }
    }

    boolean isCanFlash = true;

    public void fon(View view)
    {
        if (isHaveFlashLight)
        {
            isCanFlash = true;

            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while (isCanFlash)
                    {
                        on(null);

                        try
                        {
                            Thread.sleep(300);
                        }
                        catch (InterruptedException e) {e.printStackTrace();}

                        off(null);

                        try
                        {
                            Thread.sleep(300);
                        }
                        catch (InterruptedException e) {e.printStackTrace();}
                    }
                }
            }).start();
        }
    }

    public void foff(View view)
    {
        isCanFlash = false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode == INTENT_CODE_PERMISSION_REQUEST)
        {
            boolean isPermissionOk = true;

            for (int index = 0; index < grantResults.length; index++)
            {
                if (grantResults[index] != PackageManager.PERMISSION_GRANTED)
                {
                    isPermissionOk = false;
                }
            }

            if (isPermissionOk)
            {

            }
            else if (android.os.Build.VERSION.SDK_INT < 25 &&
                    !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
            {
                //启动系统权限设置界面
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
        }
    }
}
