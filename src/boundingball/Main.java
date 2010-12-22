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

    private static final double ACCELERATION = 1.1;

    private Timer animationTimer;

    public Main() {
        ball = new Ball(200, 0, 0, INITIAL_Y_VELOCITY);


        animationTimer = new Timer("Ball Animation");
        TimerTask updateBallTask = new TimerTask() {
            @Override
            public void run() {


                // a = (v2-v1)/t
                // a*t = (v2-v1)
                // (a*t)+v1 = v2

                double v2 = (ACCELERATION * 1) + ball.getyVelocity();


                ball.setyVelocity(v2);
                ball.update();


                // If it falls off the stage, reset it to the top of the screen
                if (ball.getY() > getHeight()) {
                    ball.setY(getHeight());
                    ball.setyVelocity((-.9 * ball.getyVelocity()));
                }
                else if (ball.getY() < 0) {
                    ball.setY(0);
                    ball.setyVelocity((-.9 * ball.getyVelocity()));
                }

                repaint();
            }
        };
        // Update at FRAMES_PER_SECOND hz
        animationTimer.schedule(updateBallTask, 0, MS_TO_WAIT);
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



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        JFrame frame = new JFrame();
        JPanel panel = new Main();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}
