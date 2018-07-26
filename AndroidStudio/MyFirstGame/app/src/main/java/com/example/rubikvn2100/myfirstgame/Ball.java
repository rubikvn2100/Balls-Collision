package com.example.rubikvn2100.myfirstgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball
{
    private int radius;
    private double px;
    private double py;
    private double vx;
    private double vy;

    private double time;

    public double getPx()
    {
        return px;
    }

    public double getPy()
    {
        return py;
    }

    public double getVx()
    {
        return vx;
    }

    public double getVy()
    {
        return vy;
    }

    public void setVx( double vx )
    {
        this.vx = vx;
    }

    public void setVy( double vy )
    {
        this.vy = vy;
    }

    public double getRadius()
    {
        return radius;
    }

    Ball( double px, double py, double vx, double vy, int radius )
    {
        this.px     = px;
        this.py     = py;
        this.vx     = vx;
        this.vy     = vy;
        this.radius = radius;

        time = 0;
    }

    public void update( float timePass )
    {
        px += vx * timePass;
        py += vy * timePass;
        time += timePass;
    }

    public void draw( Canvas canvas )
    {
        Paint paint = new Paint();
        paint.setColor( Color.rgb( 0, 0, 255 ) );

        canvas.drawOval( (float)(px - radius), (float)(py + radius), (float)(px + radius), (float)(py - radius), paint );
    }
}
