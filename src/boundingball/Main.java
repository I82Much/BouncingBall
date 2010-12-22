/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package boundingball;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ndunn
 */
public class Main extends JPanel {

    private static final int FRAMES_PER_SECOND = 30;
    private static final int MS_TO_WAIT = 1000 / FRAMES_PER_SECOND;
    private Ball ball;
    private static final int INITIAL_Y_VELOCITY = 10;
    private static final int INITIAL_X_VELOCITY = 5;

    private static final double ACCELERATION = 1.1;
    // What proportion of the velocity is retained on a bounce?  if 1.0, no energy
    // is lost, and the ball will bounce infinitely.
    private static final double COEFFICIENT_OF_RESTITUTION = 0.8;
    // While the ball is rolling along the bottom of the screen, its x velocity
    // is multiplied by this amount each frame.
    private static final double COEFFICIENT_OF_FRICTION = 0.9;
    private Timer animationTimer;
    private TimerTask animationTask;

    public Main() {
        ball = new Ball(200, 0, INITIAL_X_VELOCITY, INITIAL_Y_VELOCITY);


        animationTimer = new Timer("Ball Animation");
        animationTask = new AnimationUpdator();
    }

    public void start() {
        // Update at FRAMES_PER_SECOND hz
        animationTimer.schedule(animationTask, 0, MS_TO_WAIT);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }




    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.clearRect(0, 0, getWidth(), getHeight());
        g.fillOval((int) (ball.getX() - ball.getWidth()/2), (int) (ball.getY() - ball.getHeight()/2), ball.getWidth(), ball.getHeight());
    }

    private class AnimationUpdator extends TimerTask {

        @Override
        public void run() {

            // a = (v2-v1)/t
            // a*t = (v2-v1)
            // (a*t)+v1 = v2
            double v2 = (ACCELERATION * 1) + ball.getyVelocity();

            ball.setyVelocity(v2);
            ball.update();

            int maxY = getHeight() - (ball.getHeight() / 2);

            // Ball is out of bounds in y dimension
            if (ball.getY() > maxY) {
                ball.setY(maxY);
                ball.setyVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getyVelocity());
            }
            else if (ball.getY() < 0) {
                ball.setY(0);
                ball.setyVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getyVelocity());
            }


            // Ball is out of bounds in x dimension
            if (ball.getX() > getWidth()) {
                ball.setX(getWidth());
                ball.setxVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getxVelocity());
            }
            else if (ball.getX() < 0) {
                ball.setX(0);
                ball.setxVelocity(-COEFFICIENT_OF_RESTITUTION * ball.getxVelocity());
            }

            // ball is rolling along the bottom
            if (ball.getY() == maxY) {
                System.out.println("x velocity: " + ball.getxVelocity());
                ball.setxVelocity(COEFFICIENT_OF_FRICTION * ball.getxVelocity());
                System.out.println("x velocity after: " + ball.getxVelocity());
            }

            repaint();
        }

    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        JFrame frame = new JFrame();
        Main panel = new Main();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.start();
    }

}
