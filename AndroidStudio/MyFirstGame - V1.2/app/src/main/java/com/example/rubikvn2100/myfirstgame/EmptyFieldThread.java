package com.example.rubikvn2100.myfirstgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class EmptyFieldThread extends Thread
{
    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private EmptyFieldPanel emptyFieldPanel;
    private boolean running;
    public static Canvas canvas;

    public EmptyFieldThread(SurfaceHolder surfaceHolder, EmptyFieldPanel emptyFieldPanel )
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.emptyFieldPanel = emptyFieldPanel;
    }

    @Override
    public void run()
    {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / FPS;

        while(running)
        {
            startTime = System.nanoTime();
            canvas = null;

            //try locking the canvas for pixel editing
            try
            {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized( surfaceHolder )
                {
                    this.emptyFieldPanel.update();
                    this.emptyFieldPanel.draw(canvas);
                }
            } catch (Exception e) {}

            finally
            {
                if(canvas!=null)
                {
                    try
                    {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = ( System.nanoTime() - startTime ) / 1000000;
            waitTime = targetTime - timeMillis;

            try
            {
                this.sleep(waitTime);
            } catch(Exception e) {}

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if( frameCount == FPS )
            {
                averageFPS = 1000 / ( (totalTime / 1000000 ) / frameCount );
                frameCount = 0;
                totalTime  = 0;
                System.out.println("Average FPS: " + averageFPS);
            }
        }
    }

    public void setRunning( boolean b )
    {
        running = b;
    }
}