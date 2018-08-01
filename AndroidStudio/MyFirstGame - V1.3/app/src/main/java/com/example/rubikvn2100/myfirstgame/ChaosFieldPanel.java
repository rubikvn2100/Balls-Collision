package com.example.rubikvn2100.myfirstgame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class ChaosFieldPanel extends GamePanel
{
    public ChaosFieldPanel( Context context )
    {
        super( context );
    }

    public void surfaceCreated( SurfaceHolder holder )
    {
        playGround = new PlayGround( 0,0, getWidth() - 0, getHeight() - 0 );
        playGround.setColor( 0, 0, 0 );

        playGround.addBall( 5, 10, 320 );

        for( int i = 0; i < 5; i++ )
        {
            playGround.addBall( 5, 10, 160 );
        }

        for( int i = 0; i < 25; i++ )
        {
            playGround.addBall( 5, 10, 40 );
        }

        for( int i = 0; i < 125; i++ )
        {
            playGround.addBall( 5, 10, 10 );
        }

        for( int i = 0; i < 250; i++ )
        {
            playGround.addBall( 5, 10, 5 );
        }

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }
}
