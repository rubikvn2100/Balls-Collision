// Class ball field
#ifndef BALLFIELD.H
#define BALLFIELD.H

struct holdBall
{
    double px, py, r;
};

class ballField
{
public:
    ballField( int, int, int, int, int, double );
    ~ballField();
    void draw( double );
    double getNextCollision();
    void collision();
public:
    double time;
    double nextCollisionTime;
    bool updated;
private:
    void upToDate( double );
    bool isBallSeparate( ball*, int );

    // Array of pointer to list of ball
    ball *bList[200];

    // Array of pointer to list of pair
    ballPair *pList[200][200];

    // Hold the old value of ball list.
    holdBall holdList[200];
    // pointer to the wall
    wall *fWall;

    // field Size
    int fieldSize;

    // type of next collision.
    ball *ballToCollision;
    ballPair *pairToCollision;
};

// Class construction
ballField::ballField( int iN, int iLeftX, int iTopY, int iRightX, int iBottomY, double iT )
{
    cout << "object ballField was created" << endl;
    fieldSize = iN;
    // initialize the status of the field
    updated = true;

    // create wall.
    fWall = new wall( iLeftX, iTopY, iRightX, iBottomY );

    // create N balls.
    for( int i = 0; i < fieldSize; i++ )
    {
        // bList[i] = new ball( iPx, iPy, iVx, iVy, iR, iT );
        // not Random yet.
        bool checkIsOK = false;
        bList[i] = new ball();
        while( checkIsOK == false )
        {
            // randomize a new ball.
            int randNumber = rand() * rand();
            int positionX = iLeftX + randNumber % ( iRightX - iLeftX );
            int positionY = iTopY  + (randNumber / ( iRightX - iLeftX )) % (iBottomY - iTopY);

            // update value.
            bList[i]->updateValue( positionX, positionY, 50, 50, 5, iT );

            // check the ball
            checkIsOK = fWall->inWall( bList[i] ) && isBallSeparate( bList[i], i );

            if( checkIsOK == true )
            {
                cout << "ball[" << i << "] is created" << endl;
            }
        }
    }

    // create list of pairs.
    for( int i = 0; i < fieldSize - 1; i++ )
    {
        for( int j = i + 1; j < fieldSize; j++ )
        {
            pList[i][j] = new ballPair( bList[i], bList[j]/*, iT*/ );
            pList[j][i] = pList[i][j];
        }
    }
}
// Class destruction
ballField::~ballField()
{
}

// draw all the ball
void ballField::draw( double t )
{
    // update all before draw
    upToDate( t );

    //cleardevice();
    // draw
    int px, py, radius;
    //int c = 0;
    for( int i = 0; i < fieldSize; i++ )
    {
        // check is all the ball in wall.

        //if( fWall->inWall( bList[i] ) )
        //if( absDouble(bList[i]->px) < 450 && absDouble(bList[i]->py) < 350 )
        //{
        //    c++;
        //}


        // clear old ball.
        px = holdList[i].px;
        py = holdList[i].py;
        radius = holdList[i].r;

        setcolor( BLACK );
        circle( px, py, radius );

        // draw the ball "bList[i]->draw();"
        px = bList[i]->px;
        py = bList[i]->py;
        radius = bList[i]->r;
        setcolor ( i % getmaxcolor() + 1);
        circle( px, py, radius );
        /*
        if( i % 20 == 0 )
        {
            setcolor( YELLOW );
            circle( px, py, radius );
        }
        else
        {
            setcolor( WHITE );
            circle( px, py, radius );
        }
        */
        // save all balls
        holdList[i].px = px;
        holdList[i].py = py;
        holdList[i].r  = radius;
    }
    //cout << "balls remind in wall: " << c << endl;
}

// Get the time of next collision
double ballField::getNextCollision()
{
    double time = nextCollisionTime + 0.000001;
    // update all balls before find next collision time.
    upToDate( time );

    //cout << "Right Now is: " << time << endl;
    // set nothing for 2 pointer.
    ballToCollision = NULL;
    pairToCollision = NULL;

    // get time.
    double minBallTime = 1000000000;
    double minPairTime = 1000000000;
    double tmp;

    // check all balls
    for( int i = 0; i < fieldSize; i++ )
    {
        tmp = fWall->nextCollisionTime( bList[i] );
        //cout << "ball[" << i <<"]: " << bList[i]->px << " - " << bList[i]->py << " === " << bList[i]->vx << " - " << bList[i]->vy << " time: " << tmp << endl;
        if ( tmp < minBallTime )
        {
            minBallTime = tmp;
            ballToCollision = bList[i];
        }
    }

    // check all pairs
    for( int i = 0; i < fieldSize - 1; i++ )
    {
        for( int j = i + 1; j < fieldSize; j++)
        {
            // pList.nextCollisionTime() only return the delta time from now to the collision.
            tmp = time + pList[i][j]->nextCollisionTime();
            //cout << "[" << i << "][" << j << "] = " << tmp << endl;
            if ( tmp < minPairTime )
            {
                /*
                cout << "second ball: " << bList[i]->px << " - " << bList[i]->py << endl;
                cout << "First ball: " << bList[j]->px << " - " << bList[j]->py << endl;
                cout << endl;
                cout << "V second ball: " << bList[i]->vx << " - " << bList[i]->vy << endl;
                cout << "V First ball: " << bList[j]->vx << " - " << bList[j]->vy << endl;
                */
                //cout << "----------------------------------------------------------------" << endl;
                minPairTime = tmp;
                pairToCollision = pList[i][j];
            }
        }
    }

    // only keep the one that we need.
    //cout << "min ball time: " << minBallTime << endl;
    //cout << "min pair time: " << minPairTime << endl;
    if ( minBallTime < minPairTime )
    {
        // clear the pair. We don't need it.
        pairToCollision = NULL;
        //cout << "Ball is next" << endl;
        // return min time.
        nextCollisionTime = minBallTime;
    }
    else
    {
        // clear the ball. We don't need it.
        ballToCollision = NULL;
        //cout << "Pair is next" << endl;
        // return min time.
        nextCollisionTime = minPairTime;
    }

    // Set the status of the filed.
    // It will let the collision happen.
    updated = false;
}

// make collision
void ballField::collision()
{
    static int countCollision = 0;
    // update all.
    upToDate( nextCollisionTime );

    // check and make the collision.
    //cout << "is Field collision ------------" << endl;
    cout << fixed;
    if ( ballToCollision != NULL )
    {
        /*
        countCollision++;
        cout << setw(5) << countCollision << " - "
             << "call Wall.collision(" << setprecision(6) << nextCollisionTime << ");" << endl;
        */
        fWall->collisiion( ballToCollision, nextCollisionTime );
    }

    // check and make the collision.
    if ( pairToCollision != NULL )
    {
        /*
        countCollision++;
        cout << setw(5) << countCollision << " - "
             << "call Pair.collision(" << setprecision(6) << nextCollisionTime << ");" << endl;
        */
        pairToCollision->collision( nextCollisionTime );
    }

    // Set the status of the field.
    // It will not let the collision happen.
    updated = true;
    //cout << "Collision is finished ---------" << endl;
    //cout << endl;
}

void ballField::upToDate( double time )
{
    for( int i = 0; i < fieldSize; i++ )
    {
        bList[i]->update( time );
    }
}

bool ballField::isBallSeparate( ball *b1, int n )
{
    bool check = true;

    ball *b2;
    double dCenter;
    double delta, deltaPx, deltaPy;
    for( int i = 0; i < n; i++ )
    {
        b2 = bList[i];
        dCenter = b1->r + b2->r;
        deltaPx = b1->px - b2->px;
        deltaPy = b1->py - b2->py;
        delta = sqrt( deltaPx*deltaPx + deltaPy*deltaPy );
        if( delta < dCenter + 2 )
        {
            check = false;
        }
    }
    return check;
}
#endif // BALLFIELD
