package com.example.rubikvn2100.myfirstgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball
{
    private Point center;
    private Vector velocity;

    private int radius;
    private double mass;

    //private double epsilon;
    private double time;

    private Paint color;

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

    public void setRadius( int radius )
    {
        this.radius = radius;
        mass = Math.PI * radius * radius;
    }


    public int getRadius()
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

        this.updateColor();
    }

    public void draw( Canvas canvas )
    {
        canvas.drawOval( (float)(center.getX() - radius), (float)(center.getY() + radius),
                (float)(center.getX() + radius), (float)(center.getY() - radius), color );
    }

    public void updateColor()
    {
        if( !GamePanel.isUsingColorTemperature() )
        {
            this.color.setColor(Color.rgb(255, 255, 255));
            return;
        }

        double scalar = 1; // TODO : Color Temperature scalar will need some adjustment
        int temperature = (int)(scalar * this.getVelocity().getLength());
        int red, green, blue;

        /* Calculate Red */
        if( temperature <= 66 )
            red = 255;
        else {
            red = temperature - 60;
            red = 330 * (int)Math.pow(red,-0.1332047592);
        }
        if( red > 255 ) red = 255;

        /* Calculate Green */
        if( temperature <= 66  )
        {
            green = temperature;
            green = 99 * (int)Math.log(green) - 161;
        }
        else
            {
            green = temperature - 60;
            green = 288*(int)Math.pow(green,-0.0755148492);
        }
        if( green > 255 ) green = 255;

        /* Calculate Blue */
        if( temperature >= 66 )
            blue = 255;
        else
        {
            blue = temperature - 10;
            blue = 139*(int)Math.log(blue) - 305;
        }
        if( green > 255 ) green = 255;

        this.color.setColor(Color.rgb(red, green, blue));
    }

    public String toString()
    {
        String info = "";
        info += "A ball with radius: " + getRadius() + "\n";
        info += "            center " + center.toString() + "\n";
        info += "            velocity " + velocity.toString() + "\n";
        info += "            color " + color.toString() + "\n";
        return info;
    }
}
