package cs.vsu.ru_taranovskaya_ps.DrawObjects;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Bird implements IDrawing {
    private float x, y;
    private float speed;
    private float wingAngle = 0;
    private Color color;
    private Random random;
    private JPanel parentPanel;

    public Bird(float x, float y, float speed, JPanel parentPanel) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = Color.BLACK;
        this.random = new Random();
        this.parentPanel = parentPanel;
    }

    public void update() {
        x += speed;
        wingAngle += 0.4f;

        if (x > parentPanel.getWidth() + 50) {
            x = -50;
            y = random.nextInt(300) + 50;
        }
    }

    @Override
    public void draw(Graphics gr) {
        Graphics2D g2d = (Graphics2D) gr;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        float wingSpread = (float) Math.sin(wingAngle) * 30;
        int centerX = (int) x;
        int centerY = (int) y;
        int wingLength = 15;

        double leftAngle = Math.toRadians(225 - wingSpread);
        int leftWingX = centerX + (int) (wingLength * Math.cos(leftAngle));
        int leftWingY = centerY + (int) (wingLength * Math.sin(leftAngle));

        double rightAngle = Math.toRadians(315 + wingSpread);
        int rightWingX = centerX + (int) (wingLength * Math.cos(rightAngle));
        int rightWingY = centerY + (int) (wingLength * Math.sin(rightAngle));

        g2d.drawLine(leftWingX, leftWingY, centerX, centerY);
        g2d.drawLine(centerX, centerY, rightWingX, rightWingY);
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
