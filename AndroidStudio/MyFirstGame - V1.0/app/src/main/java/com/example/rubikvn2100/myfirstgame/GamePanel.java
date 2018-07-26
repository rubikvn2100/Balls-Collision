package com.example.rubikvn2100.myfirstgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Helper;
import android.support.constraint.solver.widgets.WidgetContainer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    public static final int MOVESPEED = -5;

    private PlayGround playGround;
    private MainThread thread;

    public GamePanel( Context context )
    {
        super( context );

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback( this );

        thread = new MainThread( getHolder(), this );

        //make gamePanel focusable so it can handle events
        setFocusable( true );
    }

    @Override
    public void surfaceChanged( SurfaceHolder holder, int format, int width, int height )
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        while(retry)
        {
            try
            {
                thread.setRunning( false );
                thread.join();

            } catch( InterruptedException e )
            {
                e.printStackTrace();
            }

            retry = false;
        }

    }

    @Override
    public void surfaceCreated( SurfaceHolder holder )
    {
        /*
        bg = new Background( BitmapFactory.decodeResource( getResources(), R.drawable.grassbg1 ) );
        player = new Player( BitmapFactory.decodeResource( getResources(), R.drawable.helicopter ), 66, 30, 3 );
        */
        playGround = new PlayGround( 0,0, getWidth() - 0, getHeight() - 0 );
        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public boolean onTouchEvent( MotionEvent event )
    {
        /*
        if( event.getAction() ==  MotionEvent.ACTION_DOWN )
        {
            if( !player.getPlaying() )
            {
                player.setPlaying( true );
            }
            else
            {
                player.setUp( true );
            }

            return true;
        }

        if( event.getAction() == MotionEvent.ACTION_UP )
        {
            player.setUp( false );
            return true;
        }
        */
        return super.onTouchEvent( event );
    }

    public void update()
    {
        /*
        if( player.getPlaying() )
        {
            bg.update();
            player.update();
        }
        */
        playGround.update( 1 );
    }

    @Override
    public void draw( Canvas canvas )
    {
        /*
        final float scaleFactorX = 1.0f * getWidth() / WIDTH;
        final float scaleFactorY = 1.0f * getHeight() / HEIGHT;
        */
        if( canvas!= null )
        {
            /*
            canvas.save();
            canvas.scale( scaleFactorX, scaleFactorY );
            bg.draw( canvas );
            player.draw( canvas );
            canvas.restore();
            */

            playGround.draw( canvas );
        }
    }
}
