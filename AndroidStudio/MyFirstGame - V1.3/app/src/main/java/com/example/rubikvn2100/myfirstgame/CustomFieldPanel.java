package com.example.rubikvn2100.myfirstgame;

import android.content.Context;
import android.view.SurfaceHolder;

public class CustomFieldPanel extends GamePanel
{
    private static final int N = 4;
    private int[] radius = new int[N];
    private int[] amounts = new int[N];

    public CustomFieldPanel( Context context, int radius[], int amounts[] )
    {
        super( context );

        for( int i = 0; i < N; i++ )
        {
            this.radius[i] = radius[i];
            this.amounts[i] = amounts[i];
        }

    }

    @Override
    public void surfaceCreated( SurfaceHolder holder )
    {
        playGround = new PlayGround( 0,0, getWidth() - 0, getHeight() - 0 );
        playGround.setColor( 0, 0, 0 );

        for( int k = 0; k < N; k++ )
        {
            for (int i = 0; i < amounts[k]; i++ )
            {
                playGround.addBall( radius[k] );
            }
        }

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }
}