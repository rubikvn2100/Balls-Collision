// wall class
#ifndef WALL.H
#define WALL.H

// define absDouble();
double absDouble( double );

class wall
{
public:
    wall( int, int, int, int );
    ~wall();
    double nextCollisionTime ( ball* );
    void collisiion( ball*, double );
    bool inWall( ball* );
private:
    int leftX;
    int rightX;
    int topY;
    int bottomY;
};

// class construction.
wall::wall( int iLeftX, int iTopY, int iRightX, int iBottomY )
{
    cout << "object wall created" << endl;
    leftX = iLeftX;
    rightX = iRightX;
    topY = iTopY;
    bottomY = iBottomY;
}

// class destruction.
wall::~wall()
{
}

double wall::nextCollisionTime( ball *b )
{
    // initialize time collision.
    // of course 1 billion seconds is too big (^_^)
    double timeCollisionX = 1000000000;
    double timeCollisionY = 1000000000;

    // do not forger the radius of the ball will affect the next time collision.

    // working with x component.
    if( b->vx <0 )
    {
        double d = leftX + b->r - b->px;
        if( d > 0 )
        {
            return b->ballTime;
        }
        // time to hit the left wall.
        timeCollisionX = d / b->vx;
    }
    else if( b->vx > 0 )
        {
            double d = rightX - b->r - b->px;
            if( d < 0 )
            {
                return b->ballTime;
            }
            // time to hit the right wall.
            timeCollisionX = d / b->vx;
        }

    // working with y component.
    if( b->vy < 0 )
    {
        double d = topY + b->r - b->py;
        if( d > 0 )
        {
            return b->ballTime;
        }
        // time to hit the top wall.
        timeCollisionY = d / b->vy;
    }
    else if( b->vy > 0)
        {
            double d = bottomY - b->r - b->py;
            if( d < 0 )
            {
                return b->ballTime;
            }
            //time to hit the bottom wall.
            timeCollisionY = d / b->vy;
        }

    // we need the next time collision, not the delta time.
    return b->ballTime + ( (timeCollisionX < timeCollisionY)?(timeCollisionX):(timeCollisionY) );
}

void wall::collisiion( ball* b, double time )
{
    // update the ball position up to now.
    //cout << b->px << " - " << b->py << " - ballTime: " << b->ballTime << endl;
    b->update( time );

    /*
    cout << "A ball collision is happening at: " << time << endl;
    cout << endl;
    */

    double radius = b->r;
    double deltaLeftX   = absDouble( b->px - leftX   - radius );
    double deltaRightX  = absDouble( b->px - rightX  + radius );
    double deltaTopY    = absDouble( b->py - topY    - radius );
    double deltaBottomY = absDouble( b->py - bottomY + radius );

    /*
    cout << b->px << " - " << b->py << endl;
    cout << leftX << " \ " << rightX << " \ " << " \ " << topY << " \ " << bottomY << endl;
    cout << deltaLeftX << " \ " << deltaRightX << " \ " << deltaTopY << " \ " << deltaBottomY << endl;
    */

    // find which direction should we change.
    int option = 0;
    double smallest = 1000000000;

    // find the one that nearest the wall.

    if ( smallest > deltaLeftX )
    {
        option = 1;
        smallest = deltaLeftX;
    }

    if ( smallest > deltaRightX )
    {
        option = 2;
        smallest = deltaRightX;
    }

    if ( smallest > deltaTopY )
    {
        option = 3;
        smallest = deltaTopY;
    }

    if (smallest > absDouble(deltaBottomY) )
    {
        option = 4;
        smallest = deltaBottomY;
    }

    /*
    cout << "Option: " << option << endl;
    if (smallest > 0.01 )
    {
        cout << "what???" << endl;
        getch();
    }
    */

    // change the direction of of the smallest
    if ( smallest == deltaLeftX || smallest == deltaRightX )
    {
        b->vx = -(b->vx);
        // cout << "Reverse Vx" << endl;
    }

    if ( smallest == deltaTopY || smallest == deltaBottomY )
    {
        b->vy = -(b->vy);
        // cout << "Reverse Vy" << endl;
    }
    // debug
    //getch();
}

bool wall::inWall( ball *b )
{
    bool check = true;

    if ( b->px - b->r < leftX )
    {
        check = false;
    }

    if ( b->px + b->r > rightX )
    {
        check = false;
    }

    if ( b->py - b->r < topY )
    {
        check = false;
    }

    if ( b->py + b->r > bottomY )
    {
        check = false;
    }

    return check;
}
#endif
