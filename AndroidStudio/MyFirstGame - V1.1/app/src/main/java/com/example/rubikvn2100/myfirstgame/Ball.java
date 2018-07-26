package com.example.rubikvn2100.myfirstgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball
{
    Point center;
    Vector velocity;

    private int radius;
    private double mass;

    //private double epsilon;
    private double time;

    Paint color;

    public double getPx()
    {
        return center.getX();
    }

    public double getPy()
    {
        return center.getY();
    }

    public double getVx()
    {
        return velocity.getX();
    }

    public double getVy()
    {
        return velocity.getY();
    }

    public void setVx( double vx )
    {
        velocity.setX( vx );
    }

    public void setVy( double vy )
    {
        velocity.setY( vy );
    }

    public void negateVx()
    {
        velocity.setX( -velocity.getX() );
    }

    public void negateVy()
    {
        velocity.setY( -velocity.getY() );
    }

    public Point getCenter()
    {
        return center;
    }

    public Vector getVelocity()
    {
        return velocity;
    }

    public void setVelocity( Vector velocity )
    {
        this.velocity = velocity;
    }

    public double getMass()
    {
        return mass;
    }

    public double getRadius()
    {
        return radius;
    }

    Ball( double px, double py, double vx, double vy, int radius )
    {
        center   = new Point( px, py );
        velocity = new Vector( vx, vy );

        this.radius = radius;
        mass = Math.PI * radius * radius;

        //epsilon = 0.5;
        time = 0;

        color = new Paint();
        color.setColor( Color.rgb( 0, 0, 255 ) );
    }

    public void update( float timePass )
    {
        center.setX( center.getX() + velocity.getX() * timePass );
        center.setY( center.getY() + velocity.getY() * timePass );
        time += timePass;
    }

    public void draw( Canvas canvas )
    {
        canvas.drawOval( (float)(center.getX() - radius), (float)(center.getY() + radius),
                         (float)(center.getX() + radius), (float)(center.getY() - radius), color );
    }

    public String toString()
    {
        String info = "";
        info += "A ball with radius: " + getRadius() + "\n";
        info += "            center " + center.toString() + "\n";
        info += "            velocity " + velocity.toString() + "\n";
        return info;
    }
}
