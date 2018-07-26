package com.example.rubikvn2100.myfirstgame;

public class Point
{
    private double x;
    private double y;

    Point( double x, double y )
    {
        this.x = x;
        this.y = y;
    }

    public void setX( double x )
    {
        this.x = x;
    }

    public double getX()
    {
        return x;
    }

    public void setY( double y )
    {
        this.y = y;
    }

    public double getY()
    {
        return y;
    }

    public double distance( Point other )
    {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt( dx * dx + dy * dy );
    }

    public static double distance( Point A, Point B )
    {
        double dx = A.x - B.x;
        double dy = A.y - B.y;
        return Math.sqrt( dx * dx + dy * dy );
    }

    public String toString()
    {
        return "Point ( " + x + ", " + y + " )";
    }
}
