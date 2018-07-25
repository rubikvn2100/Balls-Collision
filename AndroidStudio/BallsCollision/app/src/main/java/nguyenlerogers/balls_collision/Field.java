package nguyenlerogers.balls_collision;

public class Field {
    /** Data Fields **/
    private Ball ballList[]; // Todo: Should represent a POINTER to a BallList
    private BallPair pairList[][];// Todo: Should represent a POINTER to a BallPair
    private HoldBall holdList[];
    private boolean justCollision[][];
    private Wall fieldWall; // Todo: Should represent a POINTER to a Wall
    private int fieldSize;
    private Ball ballToCollision; // Todo: Should represent a POINTER to a Ball
    private BallPair pairToCollision; // Todo: Should represent a POINTER to a BallPair

    /** Getter Methods **/
    public Ball[] getBallList() {
        return ballList;
    }
    public BallPair[][] getPairList() {
        return pairList;
    }
    public HoldBall[] getHoldList() {
        return holdList;
    }
    public boolean[][] getJustCollision() {
        return justCollision;
    }
    public Wall getFieldWall() {
        return fieldWall;
    }
    public int getFieldSize() {
        return fieldSize;
    }
    public Ball getBallToCollision() {
        return ballToCollision;
    }
    public BallPair getPairToCollision() {
        return pairToCollision;
    }

    /** Setter Methods **/
    public void setBallList(Ball[] ballList) {
        this.ballList = ballList;
    }
    public void setPairList(BallPair[][] pairList) {
        this.pairList = pairList;
    }
    public void setHoldList(HoldBall[] holdList) {
        this.holdList = holdList;
    }
    public void setJustCollision(boolean[][] justCollision) {
        this.justCollision = justCollision;
    }
    public void setFieldWall(Wall fieldWall) {
        this.fieldWall = fieldWall;
    }
    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
    }
    public void setBallToCollision(Ball ballToCollision) {
        this.ballToCollision = ballToCollision;
    }
    public void setPairToCollision(BallPair pairToCollision) {
        this.pairToCollision = pairToCollision;
    }

    /** Other Methods **/
    public void upToDate(double foo) {
        // Todo: Code Field.upToDate method
    }
    public boolean isBallSeparate(Ball foo/*Todo: POINTER*/, int bar) {
        // Todo: Code Field.isBallSeparate method
        return false;
    }
}
