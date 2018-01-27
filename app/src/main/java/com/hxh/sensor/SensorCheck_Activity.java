package com.hxh.sensor;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class SensorCheck_Activity extends AppCompatActivity
{
//    1-TYPE_ACCELEROMETER(加速度传感器)
//    2-TYPE_MAGNETIC_FIELD(磁场传感器)
//    3-TYPE_ORIENTATION(方向传感器)
//    4-TYPE_GYROSCOPE(陀螺仪传感器)
//    5-TYPE_LIGHT(光线传感器)
//    6-TYPE_PRESSURE(压力传感器)
//    7-TYPE_TEMPERATURE(温度传感器)
//    8-TYPE_PROXIMITY(临近传感器)
//    9-TYPE_GRAVITY(重力传感器)
//    10-TYPE_LINEAR_ACCELERATION(线性加速传感器)
//    11-TYPE_ROTATION_VECTOR(旋转矢量传感器)
//    12-TYPE_RELATIVE_HUMIDITY(湿度传感器)
//    13-TYPE_AMBIENT_TEMPERATURE(温度传感器)从Android4.0（APILevel=14）开始被TYPE_AMBIENT_TEMPERATURE取代

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sensorcheck);

        TextView tv = (TextView) findViewById(R.id.tv);

        //获取传感器SensorManager对象
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> list_sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        tv.append("该手机有的传感器列表：\n");

        for (int i = 0; i < list_sensors.size(); i++)
        {
            Sensor sensor = list_sensors.get(i);

            tv.append(i + 1 + ":type=" + sensor.getType() + "\n");

            switch (sensor.getType())
            {
                case Sensor.TYPE_ACCELEROMETER:
                {
                    tv.append(sensor.getVendor() + "\n" + sensor.getName() + "\n1-TYPE_ACCELEROMETER(加速度传感器)\n");

                    break;
                }
                case Sensor.TYPE_MAGNETIC_FIELD:
                {
                    tv.append(sensor.getVendor() + "\n" + sensor.getName() + "\n2-TYPE_MAGNETIC_FIELD(磁场传感器)\n");

                    break;
                }
                case Sensor.TYPE_ORIENTATION:
                {
                    tv.append(sensor.getVendor() + "\n" + sensor.getName() + "\n3-TYPE_ORIENTATION(方向传感器)\n");

                    break;
                }
                case Sensor.TYPE_GYROSCOPE:
                {
                    tv.append(sensor.getVendor() + "\n" + sensor.getName() + "\n4-TYPE_GYROSCOPE(陀螺仪传感器)\n");

                    break;
                }
                case Sensor.TYPE_LIGHT:
                {
                    tv.append(sensor.getVendor() + "\n" + sensor.getName() + "\n5-TYPE_LIGHT(光线传感器)\n");

                    break;
                }
                case Sensor.TYPE_PRESSURE:
                {
                    tv.append(sensor.getVendor() + "\n" + sensor.getName() + "\n6-TYPE_PRESSURE(压力传感器)\n");

                    break;
                }
                case Sensor.TYPE_TEMPERATURE:
                {
                    tv.append(sensor.getVendor() + "\n" + sensor.getName() + "\n7-TYPE_TEMPERATURE(温度传感器)\n");

                    break;
                }
                case Sensor.TYPE_PROXIMITY:
                {
                    tv.append(sensor.getVendor() + "\n" + sensor.getName() + "\n8-TYPE_PROXIMITY(临近传感器)\n");

                    break;
                }
                case Sensor.TYPE_GRAVITY:
                {
                    tv.append(sensor.getVendor() + "\n" + sensor.getName() + "\n9-TYPE_GRAVITY(重力传感器)\n");

                    break;
                }
                case Sensor.TYPE_LINEAR_ACCELERATION:
                {
                    tv.append(sensor.getVendor() + "\n" + sensor.getName() + "\n10-TYPE_LINEAR_ACCELERATION(线性加速传感器)\n");

                    break;
                }
                case Sensor.TYPE_ROTATION_VECTOR:
                {
                    tv.append(sensor.getVendor() + "\n" + sensor.getName() + "\n11-TYPE_ROTATION_VECTOR(旋转矢量传感器)\n");

                    break;
                }
                case Sensor.TYPE_RELATIVE_HUMIDITY:
                {
                    tv.append(sensor.getVendor() + "\n" + sensor.getName() + "\n12-TYPE_RELATIVE_HUMIDITY(湿度传感器)\n");

                    break;
                }
                case Sensor.TYPE_AMBIENT_TEMPERATURE:
                {
                    tv.append(sensor.getVendor() + "\n" + sensor.getName() + "\n13-TYPE_AMBIENT_TEMPERATURE(温度传感器)\n");

                    break;
                }
                default:
                {
                    break;
                }
            }
        }
    }

    public void senser(View v)
    {
        startActivity(new Intent(this, SensorShow_Activity.class));
    }
}
