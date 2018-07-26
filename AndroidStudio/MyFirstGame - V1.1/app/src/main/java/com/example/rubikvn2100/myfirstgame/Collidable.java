package com.example.rubikvn2100.myfirstgame;

public class Collidable
{
    private boolean[][] arrayBall;
    private boolean[]   arrayLeftWall;
    private boolean[]   arrayTopWall;
    private boolean[]   arrayRightWall;
    private boolean[]   arrayBottomWall;
    private double[][]  distanceBall;
    private double[]    distanceLeftWall;
    private double[]    distanceTopWall;
    private double[]    distanceRightWall;
    private double[]    distanceBottomWall;
    private int numberOfBalls;

    Collidable()
    {
        numberOfBalls = 0;
        arrayBall = null;
        arrayLeftWall = null;
        arrayTopWall = null;
        arrayRightWall = null;
        arrayBottomWall = null;
        distanceLeftWall = null;
        distanceTopWall = null;
        distanceRightWall = null;
        distanceBottomWall = null;
    }

    public void increment()
    {
        boolean[][] newArrayBall = new boolean[numberOfBalls+1][numberOfBalls+1];
        boolean[]   newArrayLeftWall = new boolean[numberOfBalls+1];
        boolean[]   newArrayTopWall = new boolean[numberOfBalls+1];
        boolean[]   newArrayRightWall = new boolean[numberOfBalls+1];
        boolean[]   newArrayBottomWall = new boolean[numberOfBalls+1];
        double[][]  newDistanceBall = new double[numberOfBalls+1][numberOfBalls+1];
        double[]    newDistanceLeftWall = new double[numberOfBalls+1];
        double[]    newDistanceTopWall = new double[numberOfBalls+1];
        double[]    newDistanceRightWall = new double[numberOfBalls+1];
        double[]    newDistanceBottomWall = new double[numberOfBalls+1];

        for( int i = 0; i < numberOfBalls; i++ )
        {
            for( int j = 0; j < numberOfBalls; j++ )
            {
                newArrayBall[i][j] = arrayBall[i][j];
                newDistanceBall[i][j] = distanceBall[i][j];
            }
        }

        for( int i = 0; i < numberOfBalls; i++ )
        {
            newArrayLeftWall[i] = arrayLeftWall[i];
            newArrayTopWall[i] = arrayTopWall[i];
            newArrayRightWall[i] = arrayRightWall[i];
            newArrayBottomWall[i] = arrayBottomWall[i];
            newDistanceLeftWall[i] = distanceLeftWall[i];
            newDistanceTopWall[i] = distanceTopWall[i];
            newDistanceRightWall[i] = distanceRightWall[i];
            newDistanceBottomWall[i] = distanceBottomWall[i];
        }

        arrayBall = newArrayBall;
        arrayLeftWall = newArrayLeftWall;
        arrayTopWall = newArrayTopWall;
        arrayRightWall = newArrayRightWall;
        arrayBottomWall = newArrayBottomWall;
        distanceBall = newDistanceBall;
        distanceLeftWall = newDistanceLeftWall;
        distanceTopWall = newDistanceTopWall;
        distanceRightWall = newDistanceRightWall;
        distanceBottomWall = newDistanceBottomWall;

        numberOfBalls++;
    }

    public boolean isCollidableBall( int i, int j )
    {
        return arrayBall[i][j];
    }

    public boolean isCollidableLeftWall( int i )
    {
        return arrayLeftWall[i];
    }

    public boolean isCollidableTopWall( int i )
    {
        return arrayTopWall[i];
    }

    public boolean isCollidableRightWall( int i )
    {
        return arrayRightWall[i];
    }

    public boolean isCollidableBottomWall( int i )
    {
        return arrayBottomWall[i];
    }

    public void inverseValueBall( int i, int j )
    {
        arrayBall[i][j] = !arrayBall[i][j];
    }

    public void inverseValueLeftWall( int i )
    {
        arrayLeftWall[i] = !arrayLeftWall[i];
    }

    public void inverseValueTopWall( int i )
    {
        arrayTopWall[i] = !arrayTopWall[i];
    }

    public void inverseValueRightWall( int i )
    {
        arrayRightWall[i] = !arrayRightWall[i];
    }

    public void inverseValueBottomWall( int i )
    {
        arrayBottomWall[i] = !arrayBottomWall[i];
    }

    public double getDistanceBall( int i, int j )
    {
        return distanceBall[i][j];
    }

    public double getDistanceLeftWall( int i )
    {
        return distanceLeftWall[i];
    }

    public double getDistanceTopWall( int i )
    {
        return distanceTopWall[i];
    }

    public double getDistanceRightWall( int i )
    {
        return distanceRightWall[i];
    }

    public double getDistanceBottomWall( int i )
    {
        return distanceBottomWall[i];
    }

    public void setDistanceBall( int i, int j, double val )
    {
        distanceBall[i][j] = val;
    }

    public void setDistanceLeftWall( int i, double val )
    {
        distanceLeftWall[i] = val;
    }

    public void setDistanceTopWall( int i, double val )
    {
        distanceTopWall[i] = val;
    }

    public void setDistanceRightWall( int i, double val )
    {
        distanceRightWall[i] = val;
    }

    public void setDistanceBottomWall( int i, double val )
    {
        distanceBottomWall[i] = val;
    }
}
