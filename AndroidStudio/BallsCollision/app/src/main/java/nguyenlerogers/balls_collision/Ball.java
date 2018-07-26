package nguyenlerogers.balls_collision;

public class Ball {
    /**** Data Fields ****/
    private int id; // index
    private int posX, posY; // px, py
    private int velX, velY; // vx, vy
    private int numBounces;

    private double radius, m; // Todo: What is m? Mass?
    private double ballTime;
    private double ballCollisionTime;

    /**** Constructors ****/
    public Ball() {
        // Todo: Code no-arg Ball() constructor
    }
    public Ball(int foo) {
        // Todo: Code Ball(int) constructor
    }
    public Ball(double foo, double bar, double baa, double ram, double yew, double dietCoke) {
        // Todo: Code fully loaded Ball(asdfjkl;) constructor
    }

    /**** Getter Methods ****/
    public int getID() {
        return id;
    }
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public int getVelX() {
        return velX;
    }
    public int getVelY() {
        return velY;
    }
    public int getNumBounces() {
        return numBounces;
    }
    public double getM() {
        return m;
    }
    public double getRadius() {
        return radius;
    }
    public double getBallTime() {
        return ballTime;
    }
    public double getBallCollisionTime() {
        return ballCollisionTime;
    }

    /**** Setter Methods ****/
    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    public void setVel(int velX, int velY) {
        this.velX = velX;
        this.velY = velY;
    }
    public void setVelX(int velX) {
        this.velX = velX;
    }
    public void setVelY(int velY) {
        this.velY = velY;
    }
    public void setNumBounces(int numBounces) {
        this.numBounces = numBounces;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public void setM(double m) {
        this.m = m;
    }
    public void setBallTime(double ballTime) {
        this.ballTime = ballTime;
    }
    public void setBallCollisionTime(double ballCollisionTime) {
        this.ballCollisionTime = ballCollisionTime;
    }

    /** Other Methods **/
    public void updateValue(double foo, double bar, double baa, double ram, double yew, double dietCoke) {
        // Todo: Code Ball.updateValue -- Is this now setPosX, setPosY, setVelX, setVelY?
    }
    public void update(double time) {
        // Todo: Code Ball.update
    }
    public void addBounce(int num) {
        this.numBounces += num;
    }
    public void draw() {
        // Todo: Code Ball.draw -- Will this be necessary in Android? Up to y'all.
    }
}
