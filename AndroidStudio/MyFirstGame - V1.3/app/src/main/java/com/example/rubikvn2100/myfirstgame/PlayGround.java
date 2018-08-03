package com.example.rubikvn2100.myfirstgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.LinkedList;
import java.util.Random;

public class PlayGround
{
    private int left;
    private int top;
    private int right;
    private int bottom;

    private double epsilon;
    private double time;

    private Paint color;

    private LinkedList<Ball> ballList;
    private Collidable collidable;

    private Ball ghostBall;

    PlayGround( int left, int top, int right, int bottom )
    {
        this.left   = left;
        this.top    = top;
        this.right  = right;
        this.bottom = bottom;

        epsilon = 0.0;
        time = 0;

        color = new Paint();
        color.setColor( Color.rgb( 255, 0, 0 ) );

        ballList = new LinkedList<Ball>();
        collidable = new Collidable();

        ghostBall = null;
    }
//
    public boolean addBall( Ball ball )
    {
        System.out.println("In addBall(): " + ball.toString());

        if( isBallAddable( ball ) )
        {
            System.out.println("ball is added");
            ballList.add( ball );
            collidable.increment();
            return true;
        }

        return false;
    }

    public boolean addBall( int radius )
    {
        Ball ball = null;
        int numberOfTry = 10000;
        Random r = new Random();
        int px, py, vx, vy;
        do
        {
            px = r.nextInt(1000000000 ) % ( right - left - 2 * radius );
            py = r.nextInt(1000000000 ) % ( bottom - top - 2 * radius );
            vx = r.nextInt(1000000000) % 20 - 10;
            vy = r.nextInt(1000000000) % 20 - 10;
            ball = new Ball( left + radius + px, top + radius + py, vx, vy, radius );

            if( isBallAddable( ball ) )
            {
                ballList.add( ball );
                collidable.increment();
                return true;
            }
            System.out.println( numberOfTry );

            numberOfTry--;
        } while( numberOfTry > 0 );

        return false;
    }

    public boolean addBall( double px, double py, double vx, double vy, int radius )
    {
        Ball ball = new Ball( px, py, vx,vy, radius );

        if( isBallAddable( ball ) )
        {
            ballList.add( ball );
            collidable.increment();
            return true;
        }

        return false;
    }

    public boolean addBall( double vx, double vy, int radius )
    {
        Ball ball = null;
        int numberOfTry = 10000;
        Random r = new Random();
        int px, py;
        do
        {
            px = r.nextInt(1000000000 ) % ( right - left - 2 * radius );
            py = r.nextInt(1000000000 ) % ( bottom - top - 2 * radius );
            ball = new Ball( left + radius + px, top + radius + py, vx, vy, radius );

            if( isBallAddable( ball ) )
            {
                ballList.add( ball );
                collidable.increment();
                return true;
            }
            System.out.println( numberOfTry );

            numberOfTry--;
        } while( numberOfTry > 0  );

        return false;
    }

    public boolean isBallAddable( Ball ball )
    {
        if( isCollideLeftWall( ball ) || isCollideRightWall( ball ) )
        {
            return false;
        }

        if( isCollideTopWall( ball ) || isCollideBottomWall( ball ) )
        {
            return false;
        }

        for( int i = 0; i < ballList.size(); i++ )
        {
            if( isCollideBalls( ballList.get(i), ball ) )
            {
                return false;
            }
        }

        return true;
    }

    public void setEpsilon( double epsilon )
    {
        this.epsilon = epsilon;
    }

    public void addMarkerBall( double px, double py )
    {
        ghostBall = new Ball( px, py, 0, 0, 5);
    }

    public void incrementRadiusMarkerBall( int i )
    {
        if( ghostBall != null )
        {
            ghostBall.setRadius( ghostBall.getRadius() + i );
        }
    }

    public Ball removeMarkerBall()
    {
        Ball temp = ghostBall;
        ghostBall = null;
        return temp;
    }

    public Ball getMarkerBall()
    {
        return ghostBall;
    }

    private boolean isCollideLeftWall( Ball ball )
    {
        return ball.getPx() - left < ball.getRadius() + epsilon;
    }

    private boolean isCollideTopWall( Ball ball )
    {
        return ball.getPy() - top < ball.getRadius() + epsilon;
    }

    private boolean isCollideRightWall( Ball ball )
    {
        return right - ball.getPx() < ball.getRadius() + epsilon;
    }

    private boolean isCollideBottomWall( Ball ball )
    {
        return bottom - ball.getPy() < ball.getRadius() + epsilon;
    }

    public void setColor( int red, int green, int blue )
    {
        color.setColor( Color.rgb( red, green ,blue ) );
    }

    private boolean collideWall( int i )
    {
        Ball ball = ballList.get(i);
        boolean flag = false;

        if( isCollideLeftWall( ball ) )
        {
            if( ball.getVx() < 0 )
            {
                ball.negateVx();
                flag = true;
            }

        }

        if( isCollideRightWall( ball ) )
        {
            if( ball.getVx() > 0 )
            {
                ball.negateVx();
                flag = true;
            }
        }

        if( isCollideTopWall( ball ) )
        {
            if( ball.getVy() < 0 )
            {
                ball.negateVy();
                flag = true;
            }
        }

        if( isCollideBottomWall( ball ) )
        {
            if( ball.getVy() > 0 )
            {
                ball.negateVy();
                flag = true;
            }
        }

        return flag;
        /*
        Ball ball = ballList.get(i);
        boolean flag = false;
        if( isCollideLeftWall( ball ) )
        {
            if( collidable.isCollidableLeftWall(i) )
            {
                ball.negateVx();
                collidable.inverseValueLeftWall(i);
                flag = true;
            }
            else if( ball.getVx() < 0 )
            {
                ball.negateVx();
                flag = true;
            }

        }
        else
        {
            if( !collidable.isCollidableLeftWall(i) )
            {
                collidable.inverseValueLeftWall(i);
            }
        }


        if( isCollideRightWall( ball ) )
        {
            if( collidable.isCollidableRightWall(i) )
            {
                ball.negateVx();
                collidable.inverseValueRightWall(i);
                flag = true;
            }
            else if( ball.getVx() > 0 )
            {
                ball.negateVx();
                flag = true;
            }
        }
        else
        {
            if( !collidable.isCollidableRightWall(i) )
            {
                collidable.inverseValueRightWall(i);
            }
        }

        if( isCollideTopWall( ball ) )
        {
            if( collidable.isCollidableTopWall(i) )
            {
                ball.negateVy();
                collidable.inverseValueTopWall(i);
                flag = true;
            }
            else if( ball.getVy() < 0 )
            {
                ball.negateVy();
                flag = true;
            }
        }
        else
        {
            if( !collidable.isCollidableTopWall(i) )
            {
                collidable.inverseValueTopWall(i);
            }
        }

        if( isCollideBottomWall( ball ) )
        {
            if( collidable.isCollidableBottomWall(i) )
            {
                ball.negateVy();
                collidable.inverseValueBottomWall(i);
                flag = true;
            }
            else if( ball.getVy() > 0 )
            {
                ball.negateVy();
                flag = true;
            }
        }
        else
        {
            if( !collidable.isCollidableBottomWall(i) )
            {
                collidable.inverseValueBottomWall(i);
            }
        }

        return flag;
        */
        /*
        Ball ball = ballList.get(i);
        boolean flag = false;
        if( isCollideLeftWall( ball ) )
        {
            if( collidable.isCollidableLeftWall(i) )
            {
                ball.negateVx();
                collidable.inverseValueLeftWall(i);
                collidable.setDistanceLeftWall( i, ( ball.getPx() - left ) );
                flag = true;
            }
            else if( ball.getPx() - left < collidable.getDistanceLeftWall( i ) )
            {
                ball.negateVx();
                collidable.setDistanceLeftWall( i, ( ball.getPx() - left ) );
                flag = true;
            }

        }
        else
        {
            if( !collidable.isCollidableLeftWall(i) )
            {
                collidable.inverseValueLeftWall(i);
            }
        }


        if( isCollideRightWall( ball ) )
        {
            if( collidable.isCollidableRightWall(i) )
            {
                ball.negateVx();
                collidable.inverseValueRightWall(i);
                collidable.setDistanceRightWall( i, right - ball.getPx() );
                flag = true;
            }
            else if( right - ball.getPx() < collidable.getDistanceRightWall( i ) )
            {
                ball.negateVx();
                collidable.setDistanceRightWall( i, right - ball.getPx() );
                flag = true;
            }
        }
        else
        {
            if( !collidable.isCollidableRightWall(i) )
            {
                collidable.inverseValueRightWall(i);
            }
        }

        if( isCollideTopWall( ball ) )
        {
            if( collidable.isCollidableTopWall(i) )
            {
                ball.negateVy();
                collidable.inverseValueTopWall(i);
                collidable.setDistanceTopWall( i, ball.getPy() - top );
                flag = true;
            }
            else if( ball.getPy() - top < collidable.getDistanceTopWall( i ) )
            {
                ball.negateVy();
                collidable.setDistanceTopWall( i, ball.getPy() - top );
                flag = true;
            }
        }
        else
        {
            if( !collidable.isCollidableTopWall(i) )
            {
                collidable.inverseValueTopWall(i);
            }
        }

        if( isCollideBottomWall( ball ) )
        {
            if( collidable.isCollidableBottomWall(i) )
            {
                ball.negateVy();
                collidable.inverseValueBottomWall(i);
                collidable.setDistanceBottomWall( i, bottom - ball.getPx() );
                flag = true;
            }
            else if( bottom - ball.getPx() < collidable.getDistanceBottomWall( i ) )
            {
                ball.negateVx();
                collidable.setDistanceBottomWall( i, bottom - ball.getPx() );
                flag = true;
            }
        }
        else
        {
            if( !collidable.isCollidableBottomWall(i) )
            {
                collidable.inverseValueBottomWall(i);
            }
        }

        return flag;
        */
    }

    private boolean isCollideBalls( Ball A, Ball B )
    {
        return Point.distance( A.getCenter(), B.getCenter() ) - A.getRadius() - B.getRadius() < epsilon;
    }

    private void ballsCollision( Ball A, Ball B )
    {
        /*
        Vector i      = new Vector( A.getCenter(), B.getCenter() );
        Vector unitA_i = i.getUnitVector();
        Vector unitA_j = unitA_i.getRotate90();
        Vector unitB_i = unitA_j.getRotate90();
        Vector unitB_j = unitB_i.getRotate90();

        double A_vi = Vector.dot( unitA_i, A.getVelocity() );
        double A_vj = Vector.dot( unitA_j, A.getVelocity() );

        double B_vi = Vector.dot( unitB_i, B.getVelocity() );
        double B_vj = Vector.dot( unitB_j, B.getVelocity() );

        double totalMass = A.getMass() + B.getMass();
        //B_vi = - B_vi;
        double A_new_vi = ( A_vi * ( A.getMass() - B.getMass() ) + B_vi * 2 * B.getMass() ) / totalMass;
        double B_new_vi = ( B_vi * ( B.getMass() - A.getMass() ) + A_vi * 2 * A.getMass() ) / totalMass;
        //B_new_vi = - B_new_vi;

        A.setVelocity( Vector.addition( Vector.scalarMul( unitA_i, A_new_vi), Vector.scalarMul( unitA_j, A_vj ) ) );
        B.setVelocity( Vector.addition( Vector.scalarMul( unitB_i, B_new_vi), Vector.scalarMul( unitB_j, B_vj ) ) );
        */
        // A.setVx( A_new_vi * unitA]_i.getX() + A_vj * unit_j.getX() );
        // A.setVy( A_new_vi * unit_i.getY() + A_vj * unit_j.getY() );

        // B.setVx( B_new_vi * unit_i.getX() + B_vj * unit_j.getX() );
        // B.setVy( B_new_vi * unit_i.getY() + B_vj * unit_j.getY() );

        double totalMass = A.getMass() + B.getMass();
        double new_A_vx = ( A.getVx() * ( A.getMass() - B.getMass() ) + B.getVx() * 2 * B.getMass() ) / totalMass;
        double new_A_vy = ( A.getVy() * ( A.getMass() - B.getMass() ) + B.getVy() * 2 * B.getMass() ) / totalMass;
        double new_B_vx = ( B.getVx() * ( B.getMass() - A.getMass() ) + A.getVx() * 2 * A.getMass() ) / totalMass;
        double new_B_vy = ( B.getVy() * ( B.getMass() - A.getMass() ) + A.getVy() * 2 * A.getMass() ) / totalMass;

        A.setVx( new_A_vx );
        A.setVy( new_A_vy );
        B.setVx( new_B_vx );
        B.setVy( new_B_vy );
    }

    private boolean collideBalls( int i, int j )
    {
        /*
        Ball A = ballList.get(i);
        Ball B = ballList.get(j);
        if( isCollideBalls( A, B ) )
        {
            //if( Vector.dot( Vector.subtraction( A.getVelocity(), B.getVelocity() ), new Vector( A.getCenter(), B.getCenter() ) ) < 0 )
            {
                ballsCollision( A, B );
                return true;
            }
        }

        return false;
        */
        Ball A = ballList.get(i);
        Ball B = ballList.get(j);
        if( isCollideBalls( A, B ) )
        {
            if( collidable.isCollidableBall( i, j ) )
            {
                ballsCollision(A, B);
                collidable.inverseValueBall( i, j );
                collidable.setDistanceBall( i, j, Point.distance( A.getCenter(), B.getCenter() ) );
                return true;
            }
            else if( Point.distance( A.getCenter(), B.getCenter() ) < collidable.getDistanceBall( i, j ) )
            {
                ballsCollision(A, B);
                collidable.setDistanceBall( i, j, Point.distance( A.getCenter(), B.getCenter() ) );
                return true;
            }
        }
        else
        {
            if( !collidable.isCollidableBall( i, j ) )
            {
                collidable.inverseValueBall( i, j );
            }
        }

        return false;
    }

    public void update( float timePass )
    {
        for( int i = 0; i < ballList.size(); i++ )
        {
            ballList.get(i).update( timePass );
        }

        for( int i = 0; i < ballList.size(); i++ )
        {
            collideWall( i );
        }

        for( int i = 0; i < ballList.size() - 1; i++ )
        {
            for( int j = i + 1; j < ballList.size(); j++ )
            {
                collideBalls( i, j );
            }
        }
    }

    public void draw( Canvas canvas )
    {
        // clear canvas
        canvas.drawRect( left, top, right, bottom, color );

        for( int i = 0; i < ballList.size(); i++)
        {
            ballList.get(i).draw( canvas );
        }

        if( ghostBall != null )
            ghostBall.draw( canvas );
    }
}
