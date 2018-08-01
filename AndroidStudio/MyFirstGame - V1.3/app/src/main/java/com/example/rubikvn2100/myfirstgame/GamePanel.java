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

public abstract class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
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
        playGround = new PlayGround( 0,0, getWidth() - 0, getHeight() - 0 );
        playGround.setColor( 0, 0, 0 );
        /*
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
        */
        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }

    private float downX;
    private float downY;
    private float upX;
    private float upY;
    private long downT;
    private long upT;
    @Override
    public boolean onTouchEvent( MotionEvent event )
    {
        System.out.println( MotionEvent.actionToString( event.getAction( ) ) );
        if( event.getAction() ==  MotionEvent.ACTION_DOWN )
        {
            downX = event.getX();
            downY = event.getY();
            downT = System.currentTimeMillis();
            playGround.addMarkerBall( downX, downY );

            return true;
        }

        if( event.getAction() == MotionEvent.ACTION_UP )
        {
            upX = event.getX();
            upY = event.getY();
            upT = System.currentTimeMillis();

            float vx = - ( upX - downX ) / 25f;
            float vy = - ( upY - downY ) / 25f;

            Ball ball = playGround.removeMarkerBall();
            ball.setVelocity( new Vector( vx, vy ) );
            System.out.println( (playGround.addBall( ball ))?"New ball is added":"Cannot add the ball");

            return true;
        }

        if( event.getAction() == MotionEvent.ACTION_MOVE )
        {
            // TODO: Update the arrow to turn it
            System.out.println("Move to x = " + event.getX() + " y = " + event.getY() );
        }

        return super.onTouchEvent( event );
    }

    public void update()
    {
        playGround.update( 1 );
        playGround.incrementRadiusMarkerBall( 1 );
    }

    @Override
    public void draw( Canvas canvas )
    {
        if( canvas!= null )
        {
            playGround.draw( canvas );
        }
    }
}
