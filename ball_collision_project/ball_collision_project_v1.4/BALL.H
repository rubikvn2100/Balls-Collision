// ball class
#include "graphics.h"

#ifndef BALL.H
#define BALL.H

class ball
{
public:
    ball( int );
    ball( double, double, double, double, double, double );
    ~ball();
    void updateValue( double, double, double, double, double, double );
    void update( double );
    void draw();
public:
    int index;
    // position and velociy of ball.
    double px, py;
    double vx, vy;
    // radius and mass of ball.
    double r;
    double m;
    // where the ball in the time line.
    double ballTime;
    double ballCollisionTime;
};


// Class construction.
ball::ball( int iIndex )
{
    index = iIndex;
}

// Class construction.
ball::ball( double iPx, double iPy, double iVx, double iVy, double iR, double iT )
{
    // cout << "A new ball is created" << endl;
    px = iPx;
    py = iPy;
    vx = iVx;
    vy = iVy;
    ballTime = iT;
    r = iR;
    m = iR * iR * 3.141592653589793;
}

// class destruction.
ball::~ball()
{
    // cout << "A ball is deleted" << endl;
}

// update the ballTime and position of the ball up to time "t".
// the velocity will not change here.
// assume that the time "t" enter is correct.

// Get value for the ball.
void ball::updateValue( double iPx, double iPy, double iVx, double iVy, double iR, double iT )
{
    // cout << "A new ball is created" << endl;
    px = iPx;
    py = iPy;
    vx = iVx;
    vy = iVy;
    ballTime = iT;
    r = iR;
    m = iR * iR * 3.141592653589793;
}
void ball::update( double t )
{
    double deltaTime = t - ballTime;
    // update the position of the ball.
    px += vx * deltaTime;
    py += vy * deltaTime;
    // update ballTime.
    ballTime = t;
}

// Draw the ball.
void ball::draw()
{
    fillellipse( px, py, r, r );
}

#endif
