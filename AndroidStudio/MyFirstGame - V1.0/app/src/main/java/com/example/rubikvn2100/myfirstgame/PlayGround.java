package com.example.rubikvn2100.myfirstgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class PlayGround
{
    private int left;
    private int top;
    private int right;
    private int bottom;

    private double epsilon;
    private double time;

    Paint color;

    private Ball ball;

    PlayGround( int left, int top, int right, int bottom )
    {
        this.left   = left;
        this.top    = top;
        this.right  = right;
        this.bottom = bottom;

        epsilon = 0.5;
        time = 0;

        color = new Paint();
        color.setColor( Color.rgb( 255, 0, 0 ) );

        ball = new Ball( 100, 100, 5,5, 50 );
    }

    public void setEpsilon( double epsilon )
    {
        this.epsilon = epsilon;
    }

    private boolean isCollideLeftWall( Ball ball )
    {
        return ball.getPx() - left < ball.getRadius() + epsilon;
    }

    private boolean isCollideTopWall( Ball ball )
    {
        return ball.getPy() - top < ball.getRadius() + epsilon;
    }

    private boolean isCollideRightWall( Ball ball )
    {
        return right - ball.getPx() < ball.getRadius() + epsilon;
    }

    private boolean isCollideBottomWall( Ball ball )
    {
        return bottom - ball.getPy() < ball.getRadius() + epsilon;
    }

    public void setColor( int red, int green, int blue )
    {
        color.setColor( Color.rgb( red, green ,blue ) );
    }

    public void update( float timePass )
    {
        ball.update( timePass );

        if( isCollideLeftWall( ball ) || isCollideRightWall( ball ) )
        {
            ball.setVx( -ball.getVx() );
        }

        if( isCollideTopWall( ball ) || isCollideBottomWall( ball ) )
        {
            ball.setVy( -ball.getVy() );
        }
    }

    public void draw( Canvas canvas )
    {
        // clear canvas with white rectangle
        canvas.drawRect( left, top, right, bottom, color );

        ball.draw( canvas );
    }
}
