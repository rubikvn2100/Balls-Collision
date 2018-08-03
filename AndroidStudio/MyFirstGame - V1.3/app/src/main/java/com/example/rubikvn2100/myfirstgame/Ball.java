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

        if( GamePanel.isUsingColorTemperature() )
            updateColor();
        else
            color.setColor(Color.rgb(0, 0, 255));
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
            this.color.setColor(Color.rgb(0, 0, 255));
            return;
        }

        double scalar = 0.9; // TODO : Color Temperature scalar will need some adjustment
        double temperature = scalar * velocity.getLength();
        int red, green, blue;

        /* Calculate Red */
        if( temperature <= 66 )
            red = 255;
        else
            red = (int)(329.70 * Math.pow( temperature-60.0, -0.1332047592 ));

        /* Calculate Green */
        if( temperature <= 66  )
            green = (int)(99.47 * Math.log( temperature ) - 161.12);
        else
            green = (int)(288.12 * Math.pow( temperature-60.0, -0.0755148492 ));

        /* Calculate Blue */
        if( temperature >= 66 )
            blue = 255;
        else {
            if (temperature <= 19)
                blue = 0;
            else
                blue = (int) (138.52 * Math.log(temperature - 10.0) - 305.04);
        }

        /* Double-check and reassign values */
        red =   (red<0)?    0   : red;
        red =   (red>255)?  255 : red;
        green = (green<0)?  0   : green;
        green = (green>255)?255 : green;
        blue =  (blue<0)?   0   : blue;
        blue =  (blue>255)? 255 : blue;

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