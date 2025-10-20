package cs.vsu.ru_taranovskaya_ps.DrawObjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grass implements IDrawing {
    private List<GrassBlade> grassBlades;
    private float windAngle = 0;
    private Random random;

    public Grass(int width, int height, int grassHeight) {
        grassBlades = new ArrayList<>();
        random = new Random();
        createGrassBlades(width, height, grassHeight);
    }

    private void createGrassBlades(int width, int height, int grassHeight) {
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width - 20) + 10;
            int y = random.nextInt(grassHeight - 30) + (height - grassHeight);
            int bladeHeight = random.nextInt(15) + 10;
            float phase = random.nextFloat() * (float) Math.PI * 2;
            grassBlades.add(new GrassBlade(x, y, bladeHeight, phase));
        }
    }

    public void update() {
        windAngle += 0.005f;
        if (windAngle > 2 * Math.PI) {
            windAngle = 0;
        }
    }

    @Override
    public void draw(Graphics gr) {
        Graphics2D g2d = (Graphics2D) gr;

        // Рисуем основную траву
        g2d.setColor(new Color(60, 179, 113));
        g2d.fillRect(0, g2d.getClipBounds().height - 250, g2d.getClipBounds().width, 250);

        // Рисуем статичную траву на границе
        for (int i = 0; i < g2d.getClipBounds().width; i += 4) {
            int height = 10 + Math.abs((i % 20) - 10);
            g2d.drawLine(i, g2d.getClipBounds().height - 250, i, g2d.getClipBounds().height - 250 - height);
        }

        // Рисуем травинки
        for (GrassBlade grassBlade : grassBlades) {
            grassBlade.draw(g2d, windAngle);
        }
    }

    private static class GrassBlade {
        private int x, y, height;
        private Color color;
        private float phase;

        public GrassBlade(int x, int y, int height, float phase) {
            this.x = x;
            this.y = y;
            this.height = height;
            this.color = new Color(0, 100, 0);
            this.phase = phase;
        }

        public void draw(Graphics2D g2d, float windAngle) {
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            float sway = (float) Math.sin(windAngle + phase) * 3;
            int curve = (int) sway;
            g2d.drawLine(x, y, x + curve, y - height);
        }
    }
}
