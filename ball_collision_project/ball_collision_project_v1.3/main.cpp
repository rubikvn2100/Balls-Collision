#include <conio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <iostream>
#include <iomanip>
using namespace std;

#include "graphics.h"
#include "BALL.H"
#include "PAIR.H"
#include "WALL.H"
#include "BALLFIELD.H"

#define TIME_STEP 0.025

// return the value of time in seconds with type Double
double getTime()
{
    double t = clock();
    return t/1000;
}

double absDouble( double n )
{
    return (n < 0)?(-n):(n);
}

int main()
{
    int n;
    cout << "How many ball you want to simulate <1..160>: ";
    cin >> n;

    int winW = 400+400;
    int winH = 300+400;

    int gw = initwindow(winW, winH, "ball collision project");


    // create new field
    cout << "start time: " << getTime() << endl;
    ballField field( n, 200, 200, winW-200, winH-200, getTime() );

    double nextRunTime = getTime() + TIME_STEP;

    // loop, on the going to test.
    int c = 1;
    while( getTime() > 0 )
    {
        /*
        if ( c = clock() )
        {
            cout << showpoint << fixed;
            cout << setw(6) << setprecision(3) << nextRunTime << " - "
                 << setw(6) << setprecision(3) << getTime() << " - "
                 << setw(6) << setprecision(3) << field.nextCollisionTime << endl;
            c++;
        }
        */

        if( field.updated )
        {
            //cout << field.nextCollisionTime << endl;
            //cout << "Right Now: " << getTime() << endl;
            field.getNextCollision();
            //cout << "get Next Collision Time" << endl;
        }
        else if( field.nextCollisionTime <= nextRunTime )
            {
                //cout << field.nextCollisionTime << endl;
                // Give more time if to much collision occur
                if( getTime() > nextRunTime )
                {
                    nextRunTime += 0.001;
                    //cout << "Too much collision. nextRunTime += 0.001; " << endl;
                }
                field.collision();
            }
            else
            {
                // Delay until rumTime.
                double deltaTime = TIME_STEP;
                while( getTime() <= nextRunTime )
                {
                    if( deltaTime > 0.010)
                    {
                        nextRunTime -= 0.001;
                        deltaTime   -= 0.001;
                    }
                    //cout << "Nothing to do. nextRunTime -= 0.001; " << endl;
                }

                // Draw it.
                //cout << "Draw device --------------------------------" << endl << endl;
                field.draw( getTime() );
                nextRunTime += TIME_STEP;
            }

    }
    // stop device until hit any key.
    getch();

    return 0;
}
