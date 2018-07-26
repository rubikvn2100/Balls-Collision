package nguyenlerogers.balls_collision;

public class Ball {
    /**** Data Fields ****/
    private int id; // index
    private int posX, posY; // px, py
    private int velX, velY; // vx, vy
    private int numBounces;

    private double radius, mass;
    private double ballTime;
    private double ballCollisionTime;

    /**** Constructors ****/
    public Ball() {
        // Todo: Code no-arg Ball() constructor
    }
    public Ball(int foo) {
        // Todo: Code Ball(int) constructor
    }
    public Ball(double posX, double posY, double velX, double velY, double radius, double ballTime) {
        this.setPos(posX, posY);
        this.setVel(velX, velY);
        this.setRadius(radius);
        this.setBallTime(ballTime);
        this.setMass(radius*radius*Math.PI);
    }

    /**** Getter Methods ****/
    public int getID() {
        return this.id;
    }
    public int getPosX() {
        return this.posX;
    }
    public int getPosY() {
        return this.posY;
    }
    public int getVelX() {
        return this.velX;
    }
    public int getVelY() {
        return this.velY;
    }
    public int getNumBounces() {
        return this.numBounces;
    }
    public double getMass() {
        return this.mass;
    }
    public double getRadius() {
        return this.radius;
    }
    public double getBallTime() {
        return this.ballTime;
    }
    public double getBallCollisionTime() {
        return this.ballCollisionTime;
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
    public void setMass(double mass) {
        this.mass = mass;
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
        double dt = time - this.getBallTime();
        // Update the position of the ball.
        this.incrementPos()
    }
    public void addBounce(int num) {
        this.numBounces += num;
    }
    public void draw() {
        // Todo: Code Ball.draw -- Will this be necessary in Android? Up to y'all.
    }
}
