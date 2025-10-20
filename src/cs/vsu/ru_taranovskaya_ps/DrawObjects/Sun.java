package cs.vsu.ru_taranovskaya_ps.DrawObjects;

import java.awt.*;

    public class Sun implements IDrawing {
        private int centerX, centerY;
        private float rayAngle = 0;

        public Sun(int centerX, int centerY) {
            this.centerX = centerX;
            this.centerY = centerY;
        }

        public void update() {
            rayAngle += 0.02f;
            if (rayAngle > 2 * Math.PI) {
                rayAngle = 0;
            }
        }

        @Override
        public void draw(Graphics gr) {
            Graphics2D g2d = (Graphics2D) gr;

            // Основной круг солнца
            RadialGradientPaint sunGradient = new RadialGradientPaint(
                    centerX, centerY, 40,
                    new float[]{0.0f, 1.0f},
                    new Color[]{Color.YELLOW, Color.ORANGE}
            );
            g2d.setPaint(sunGradient);
            g2d.fillOval(centerX - 40, centerY - 40, 80, 80);

            // Лучи солнца
            g2d.setColor(new Color(255, 255, 0, 150));
            g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            for (int i = 0; i < 12; i++) {
                double angle = rayAngle + i * Math.PI / 6;
                int rayLength = 25 + (i % 2 == 0 ? 10 : 0);

                int x1 = centerX + (int)(40 * Math.cos(angle));
                int y1 = centerY + (int)(40 * Math.sin(angle));
                int x2 = centerX + (int)((40 + rayLength) * Math.cos(angle));
                int y2 = centerY + (int)((40 + rayLength) * Math.sin(angle));

                g2d.drawLine(x1, y1, x2, y2);
            }
        }
    }
