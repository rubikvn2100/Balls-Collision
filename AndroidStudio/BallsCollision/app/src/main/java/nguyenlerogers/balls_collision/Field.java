package nguyenlerogers.balls_collision;

import java.time.temporal.TemporalAdjusters;

public class Field {
    /** Data Fields, sans getters (private) **/
    private Ball ballList[]; // Array of pointer to list of Balls / Todo: Should represent a POINTER to a BallList
    private BallPair pairList[][];// Array of pointer to list of pair Todo: Should represent a POINTER to a BallPair
    private HoldBall holdList[]; // Hold the old value of ball list
    private boolean justCollision[][]; // Check list of just collision ball
    private Wall fieldWall; // Pointer to the wall / Todo: Should represent a POINTER to a Wall
    private int fieldSize; // The size of our field
    private Ball ballToCollision; // Type of next collision / Todo: Should represent a POINTER to a Ball
    private BallPair pairToCollision; // Type of next collision / Todo: Should represent a POINTER to a BallPair

    /** Data Fields, with getters (public) **/
    private double fieldTime;
    private double nextCollisionTime;
    private boolean updated;

    /** Constructors **/
    Field(int fieldSize, int leftX, int topY, int rightX, int bottomY, double t) {
        System.out.println("Field object was created");
        this.fieldSize = fieldSize;
        this.updated = true;


        // Todo: Code Field(asdfjkl); constructor

    }
    /** Getter Methods **/
    public double getFieldTime() {
        return this.fieldTime;
    }
    public double getNextCollisionTime() {
        return this.nextCollisionTime;
    }
    public boolean isUpdated() {
        return this.updated;
    }

    /** Setter Methods **/
    public void setFieldTime(double fieldTime) {
        this.fieldTime = fieldTime; // Todo: Is this method... necessary? Is it correct?
    }
    public void setNextCollisionTime(double nextCollisionTime) {
        this.nextCollisionTime = nextCollisionTime; // Todo: I'm just making setter methods. Remove this if necessary.
    }
    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    /** Private Methods **/
    private void upToDate(double foo) {
        // Todo: Code Field.upToDate method
    }
    private boolean isBallSeparate(Ball foo/*Todo: POINTER*/, int bar) {
        // Todo: Code Field.isBallSeparate method
        return false;
    }
}
