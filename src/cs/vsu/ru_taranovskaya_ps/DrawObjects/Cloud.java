package cs.vsu.ru_taranovskaya_ps.DrawObjects;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Cloud implements IDrawing {
    private float x, y;
    private float speed;
    private float size;
    private Color color;
    private Random random;
    private JPanel parentPanel;

    public Cloud(float x, float y, float speed, float size, JPanel parentPanel) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
        this.color = Color.WHITE;
        this.random = new Random();
        this.parentPanel = parentPanel;
    }

    public void update() {
        x += speed;

        if (x > parentPanel.getWidth() + 150) {
            x = -150;
            y = random.nextInt(200) + 30;
        }
    }

    @Override
    public void draw(Graphics gr) {
        Graphics2D g2d = (Graphics2D) gr;
        g2d.setColor(color);

        int baseSize = (int)(40 * size);
        int smallSize = (int)(baseSize * 0.7);
        int bigSize = baseSize;

        // Облака из кружочков
        g2d.fillOval((int)x, (int)y + (bigSize - smallSize)/2, smallSize, smallSize);
        g2d.fillOval((int)x + smallSize - 10, (int)y, bigSize, bigSize);
        g2d.fillOval((int)x + smallSize + bigSize - 20, (int)y + (bigSize - smallSize)/2, smallSize, smallSize);

        // Тень
        g2d.setColor(new Color(245, 245, 245));
        g2d.fillOval((int)x + 2, (int)y + (bigSize - smallSize)/2 + 2, smallSize - 4, smallSize - 4);
        g2d.fillOval((int)x + smallSize - 8, (int)y + 2, bigSize - 4, bigSize - 4);
        g2d.fillOval((int)x + smallSize + bigSize - 18, (int)y + (bigSize - smallSize)/2 + 2, smallSize - 4, smallSize - 4);
    }

    public float getX() { return x; }
    public float getY() { return y; }
}