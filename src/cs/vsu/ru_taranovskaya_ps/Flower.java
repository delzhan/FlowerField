package cs.vsu.ru_taranovskaya_ps;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Flower implements Drawing {
    private int x, y;
    private Color color;
    private boolean dragging;
    private int dragOffsetX, dragOffsetY;
    private Color stemColor;
    private static final Color[] FLOWER_COLORS = {
            Color.RED, Color.PINK, Color.YELLOW,
            Color.ORANGE, Color.MAGENTA, Color.CYAN
    };
    private Random random;

    public Flower(int x, int y) {
        this.x = x;
        this.y = y;
        this.random = new Random();
        this.color = FLOWER_COLORS[random.nextInt(FLOWER_COLORS.length)];
        this.stemColor = new Color(0, 100, 0);
    }

    @Override
    public void draw(Graphics gr) {
        Graphics2D g2d = (Graphics2D) gr;

        // Стебель
        g2d.setColor(stemColor);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(x, y, x, y + 40);

        // Листья
        g2d.fillOval(x - 10, y + 12, 8, 4);
        g2d.fillOval(x + 4, y + 22, 8, 4);

        // Лепестки
        g2d.setColor(color);
        int petalSize = 18;

        for (int i = 0; i < 6; i++) {
            double angle = i * Math.PI / 3;
            int petalX = x + (int)(Math.cos(angle) * 8);
            int petalY = y + (int)(Math.sin(angle) * 8);
            g2d.fillOval(petalX - petalSize/2, petalY - petalSize/2, petalSize, petalSize);
        }

        // Центр цветка
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(x - 6, y - 6, 12, 12);

        g2d.setColor(new Color(255, 165, 0));
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawOval(x - 6, y - 6, 12, 12);

    }

    public boolean contains(Point point) {
        return new Ellipse2D.Float(x - 15, y - 15, 30, 65).contains(point);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public boolean isDragging() { return dragging; }
    public void setDragging(boolean dragging) { this.dragging = dragging; }
    public void setDragOffset(int x, int y) {
        dragOffsetX = x;
        dragOffsetY = y;
    }
    public int getDragOffsetX() { return dragOffsetX; }
    public int getDragOffsetY() { return dragOffsetY; }
}