package com.example.rubikvn2100.myfirstgame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.Random;

class ChaosFieldPanel extends GamePanel
{
    public ChaosFieldPanel( Context context )
    {
        super( context );
    }

    @Override
    public void surfaceCreated( SurfaceHolder holder )
    {
        playGround = new PlayGround( 0,0, getWidth() - 0, getHeight() - 0 );
        playGround.setColor( 0, 0, 0 );
        Random rando = new Random();

        for( int i = 0; i < 250; i++ )
        {
<<<<<<< HEAD
            playGround.addBall( 5 );
=======
            double vx = rando.nextDouble()*10;
            double vy = rando.nextDouble()*10;
            if(rando.nextBoolean()) vx *= -1;
            if(rando.nextBoolean()) vy *= -1;
            playGround.addBall( vx, vy, 5 );
>>>>>>> 48c03defe5bf0ec01917ce0da63c641c59e7aa93
        }

        for( int i = 0; i < 125; i++ )
        {
<<<<<<< HEAD
            playGround.addBall( 10 );
=======
            double vx = rando.nextDouble()*10;
            double vy = rando.nextDouble()*10;
            if(rando.nextBoolean()) vx *= -1;
            if(rando.nextBoolean()) vy *= -1;
            playGround.addBall( vx, vy, 10 );
>>>>>>> 48c03defe5bf0ec01917ce0da63c641c59e7aa93
        }

        for( int i = 0; i < 25; i++ )
        {
<<<<<<< HEAD
            playGround.addBall( 40 );
=======
            double vx = rando.nextDouble()*10;
            double vy = rando.nextDouble()*10;
            if(rando.nextBoolean()) vx *= -1;
            if(rando.nextBoolean()) vy *= -1;
            playGround.addBall( vx, vy, 40 );
        }
        /*
        for( int i = 0; i < 5; i++ )
        {
            playGround.addBall( 5, 10, 160 );
>>>>>>> 48c03defe5bf0ec01917ce0da63c641c59e7aa93
        }

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }
}
