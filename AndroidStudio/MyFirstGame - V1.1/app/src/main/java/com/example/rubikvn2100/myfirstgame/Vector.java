package com.example.rubikvn2100.myfirstgame;

public class Vector
{
    private double x;
    private double y;

    Vector( double x, double y )
    {
        this.x = x;
        this.y = y;
    }

    Vector( Point A, Point B )
    {
        x = B.getX() - A.getX();
        y = B.getY() - A.getX();
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

    public double getLength()
    {
        return Math.sqrt( x * x + y * y );
    }

    public Vector getUnitVector()
    {
        double l = getLength();
        return new Vector( x / l, y / l );
    }

    public double dot( Vector other )
    {
        return x * other.x + y * other.y;
    }

    public static double dot( Vector A, Vector B )
    {
        return A.x * B.x + A.y * B.y;
    }

    public static Vector addition( Vector A, Vector B )
    {
        return new Vector( A.x + B.x, A.y + B.y );
    }

    public static Vector subtraction( Vector A, Vector B )
    {
        return new Vector( A.x - B.x, A.y - B.y );
    }

    public Vector getRotate90()
    {
        return new Vector( -y, x );
    }

    public static Vector scalarMul( Vector A, double c )
    {
        return new Vector( c * A.x, c * A.y );
    }

    public String toString()
    {
        return "Vector ( " + x + ", " + y + " )";
    }
}
