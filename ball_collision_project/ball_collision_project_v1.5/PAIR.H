// Pair of balls
#include<math.h>

#ifndef PAIR.H
#define PAIR.H

class ballPair
{
public:
    ballPair( ball*, ball*/*, double*/ );
    ~ballPair();
    double nextCollisionTime( double );
    void collision( double );
public:
    ball *b1;
    ball *b2;
public:
    double pairCollisionTime;
};

// Class construction
// Let the pointer b1 and b2, point to iBall1, iBall2
ballPair::ballPair( ball *iBall1, ball *iBall2/*, double iT*/ )
{
    //cout << "an object Pair is created" << endl;
    b1 = iBall1;
    b2 = iBall2;
    //time = iT;
}

// Class destruction
ballPair::~ballPair()
{
}

// return the delta time from now to the next collision.
double ballPair::nextCollisionTime( double time )
{
    // Ball No.2 relative to ball No.1 (ball2 - ball1)
    double dPx = b1->px - b2->px;
    double dPy = b1->py - b2->py;
    double dVx = b1->vx - b2->vx;
    double dVy = b1->vy - b2->vy;
    double radius = b1->r + b2->r;

    // prevent 2 balls stick together.
    double deltaD = sqrt( dPx*dPx + dPy*dPy ) - (radius);
    /*
    if( deltaD < -0 )
    {
        cout << setw(10) << deltaD << " , " << b1->index << " - " << b2->index << endl;
        circle( b1->px, b1->py, 1);
        circle( b2->px, b2->py, 1);
        cout << "ball 1: " << b1->px << " - " << b1->py << endl;
        cout << "ball 2: " << b2->px << " - " << b2->py << endl;
        getch();
    }
    */
    if( deltaD < 0 )
    {
        pairCollisionTime = time;
        return pairCollisionTime;
    }
    // cout << "second ball: " << b1->px << " - " << b1->py << endl;
    // cout << "First ball: " << b2->px << " - " << b2->py << endl;

    // calculate the time.
    double a = (dVx * dVx) + (dVy * dVy);
    double b = (dPx * dVx) + (dPy * dVy);
    double c = (dPx * dPx) + (dPy * dPy) - (radius * radius);

    double delta = b*b - a*c;
    /*
    cout << dPx << " --- " << dPy << endl;
    cout << dVx << " --- " << dVy << endl;
    cout << radius << endl;
    cout << a << " - " << b << " - " << c << endl;
    cout << "delta: " << delta << endl;
    */
    if( delta < 0)
    {
        // If delta <0, so the collision time is not exist.
        // 1 billion seconds is big enought for not affect the programe.
        pairCollisionTime = time + 1000000000;
        return pairCollisionTime;
    }
    else if( delta == 0 )
        {
            // not sure that it will happen or not, just make sure.
            pairCollisionTime = time + (-b) / a;
            return pairCollisionTime;
        }
        else if( delta > 0 )
            {
                // let use sqrt in maht.h
                delta = sqrt( delta );
                double t1 = ( -b + delta ) / a;
                double t2 = ( -b - delta ) / a;

                // return the smallest positive one.
                // just make sure.
                double result = 1000000000;
                //cout << t1 << " - " << t2 << endl;
                if( t1 > 0)
                {
                    //cout << "shit=========== t1" << endl;
                    result = (t1 < result)?(t1):(result);
                }

                if( t2 > 0)
                {
                    //cout << "shit=========== t2" << endl;
                    result = (t2 < result)?(t2):(result);
                }

                pairCollisionTime = time + result;
                return pairCollisionTime;
            }
}

// Make the collision for ball No.1 and ball No.2
void ballPair::collision( double time )
{
    // update the position of the balls.
    // getch();
    b1->update( time );
    b2->update( time );

    /*
    cout << "A pair collision is occur: " << endl;
    cout << endl;
    */

    /*
    cout << "second ball: " << b2->px << " - " << b2->py << endl;
    cout << "First ball: " << b1->px << " - " << b1->py << endl;
    cout << endl;
    cout << "v second ball: " << b2->vx << " - " << b2->vy << endl;
    cout << "v First ball: " << b1->vx << " - " << b1->vy << endl;
    */

    // make ball No.2 relative with ball No.1
    // looking for the vector of (ball2)(ball1)
    double vectorUx = b2->px - b1->px;
    double vectorUy = b2->py - b1->py;
    double lengthU = sqrt( vectorUx * vectorUx + vectorUy * vectorUy);
    // now we have the Unit vector.
    vectorUx /= lengthU;
    vectorUy /= lengthU;


    //cout << vectorUx << " - " << vectorUy << endl;
    /*
    cout << (vectorUx * vectorUx + vectorUy * vectorUy) << endl;

    vectorUx *= vectorUx;
    vectorUy *= vectorUy;
    cout << (vectorUx + vectorUy) << endl;

    getch();
    */

    // let break the vector by using the dot product.
    // the new coordinate.
    // [ Ux, Uy]
    // [-Uy, Ux]
    // [-Ux,-Uy]
    // [ Uy,-Ux]
    double vector12X = b1->vx * ( vectorUx) + b1->vy * ( vectorUy);
    double vector12Y = b1->vx * (-vectorUy) + b1->vy * ( vectorUx);

    double vector21X = b2->vx * ( vectorUx) + b2->vy * ( vectorUy);
    double vector21Y = b2->vx * (-vectorUy) + b2->vy * ( vectorUx);

    // let make the easiest case. 2 ball have the same mass.
    // swap the velocity vector.

    //cout << "v break vector 12: " << vector12X << " - " << vector12Y << endl;
    //cout << "v break vector 21: " << vector21X << " - " << vector21Y << endl;
    double tmp;
    tmp = vector12X;
    vector12X = vector21X;
    vector21X = tmp;


    // return the length value form the coordinate above to the original coordinate.
    // reuse the Unit vector above.
    // [ Ux, Uy]
    // [-Uy, Ux]
    // [-Ux,-Uy]
    // [ Uy,-Ux]
    // multiply these unit vector of the new coordinate with it length will return the old coordinate.
    // 2 steps in 1, be careful when re read it.
    b1->vx = vector12X*( vectorUx) + vector12Y*(-vectorUy);
    b1->vy = vector12X*( vectorUy) + vector12Y*( vectorUx);
    b2->vx = vector21X*( vectorUx) + vector21Y*(-vectorUy);
    b2->vy = vector21X*( vectorUy) + vector21Y*( vectorUx);

    //cout << "second ball: " << b2->px << " - " << b2->py << endl;
    //cout << "First ball: " << b1->px << " - " << b1->py << endl;
    //getch();
}

#endif
