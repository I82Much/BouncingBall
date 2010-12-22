/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package boundingball;

/**
 *
 * @author ndunn
 */
public class Ball {
    private double x;
    private double y;
    private double xVelocity;
    private double yVelocity;

    private int width;
    private int height;

    private static final int DEFAULT_WIDTH = 10;
    private static final int DEFAULT_HEIGHT = 10;

    public Ball(int x, int y, int xVelocity, int yVelocity, int width, int height) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.width = width;
        this.height = height;
    }
    public Ball(int x, int y, int xVelocity, int yVelocity) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
    }

    public void update() {
        this.x += xVelocity;
        this.y += yVelocity;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }




}
