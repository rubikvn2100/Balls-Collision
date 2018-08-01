package com.example.rubikvn2100.myfirstgame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class EmptyFieldPanel extends GamePanel
{
    public EmptyFieldPanel( Context context )
    {
        super( context );
    }

    public void surfaceCreated( SurfaceHolder holder )
    {
        playGround = new PlayGround( 0,0, getWidth() - 0, getHeight() - 0 );
        playGround.setColor( 0, 0, 0 );

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }
}
