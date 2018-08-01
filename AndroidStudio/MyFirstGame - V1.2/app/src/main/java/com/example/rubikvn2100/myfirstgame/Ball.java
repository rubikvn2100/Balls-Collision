package com.example.rubikvn2100.myfirstgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball
{
    private Point center;
    private Vector velocity;
    private boolean useColorTemperature = true;

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
    public void updateColor ( ) {
        if(!useColorTemperature) {
            this.color.setColor(Color.rgb(255, 255, 255));
            return;
        }
        double scalar = 0.15; // TODO : Color Temperature scalar will need some adjustment
        int temperature = (int)(scalar * this.getVelocity().getLength() /* this.getRadius()*/);
        int red, green, blue;

        /* Calculate Red */
        if( temperature <= 66 )
            red = 255;
        else {
            red = temperature - 60;
            red = (int)(329.698727446 * Math.pow(red,-0.1332047592));
        }
        red = (red > 255) ? 255 : red;

        /* Calculate Green */
        if( temperature <= 66  ) {
            green = temperature;
            green = (int)(99.4708025861 * Math.log(green) - 161.1195681661);
        }
        else {
            green = temperature - 60;
            green = (int)(288.1221695283 * Math.pow(green,-0.0755148492));
        }
        green = (green > 255) ? 255 : green;

        /* Calculate Blue */
        if( temperature >= 66 )
            blue = 255;
        else {
            blue = temperature - 10;
            blue = (int)(138.5177312231 * Math.log(blue) - 305.0447927307);
        }
        blue = (blue > 255) ? 255 : blue;

        this.color.setColor(Color.rgb(red, green, blue));
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
