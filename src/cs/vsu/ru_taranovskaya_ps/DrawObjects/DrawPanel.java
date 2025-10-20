package cs.vsu.ru_taranovskaya_ps.DrawObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawPanel extends JPanel implements ActionListener {
    private List<Flower> flowers;
    private List<Cloud> clouds;
    private List<Bird> birds;
    private Sun sun;
    private Grass grass;
    private Random random;
    private Timer animationTimer;

    public DrawPanel() {
        flowers = new ArrayList<>();
        clouds = new ArrayList<>();
        birds = new ArrayList<>();
        random = new Random();

        sun = new Sun(690, 90);
        grass = new Grass(800, 600, 250);
        createInitialFlowers();
        createInitialClouds();
        createInitialBirds();

        addMouseListeners();
        startAnimation();
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(135, 206, 235));
    }

    private void createInitialFlowers() {
        for (int i = 0; i < 15; i++) {
            addFlowerInGrassArea();
        }
    }

    private void addFlowerInGrassArea() {
        int x = random.nextInt(750) + 25;
        int y = random.nextInt(200) + 350;
        flowers.add(new Flower(x, y));
    }

    private void createInitialClouds() {
        clouds.add(new Cloud(100, 80, 0.8f, 1.0f, this));
        clouds.add(new Cloud(300, 120, 0.6f, 0.8f, this));
        clouds.add(new Cloud(500, 60, 1.0f, 1.2f, this));
        clouds.add(new Cloud(50, 150, 0.5f, 0.7f, this));
    }

    private void createInitialBirds() {
        birds.add(new Bird(200, 200, 1.2f, this));
        birds.add(new Bird(400, 250, 1.0f, this));
        birds.add(new Bird(100, 300, 0.8f, this));
    }

    private void startAnimation() {
        animationTimer = new Timer(30, this);
        animationTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Cloud cloud : clouds) {
            cloud.update();
        }

        for (Bird bird : birds) {
            bird.update();
        }

        sun.update();
        grass.update();
        repaint();
    }

    private void addMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DrawPanel outer = DrawPanel.this;

                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (e.getY() < 350) {
                        birds.add(new Bird(e.getX(), e.getY(), random.nextFloat() * 1.0f + 0.5f, outer));
                    } else {
                        flowers.add(new Flower(e.getX(), e.getY()));
                    }
                    repaint();
                }
                else if (SwingUtilities.isRightMouseButton(e)) {
                    flowers.removeIf(flower -> flower.contains(e.getPoint()));
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                for (Flower flower : flowers) {
                    if (flower.contains(e.getPoint())) {
                        flower.setDragging(true);
                        flower.setDragOffset(
                                e.getX() - flower.getX(),
                                e.getY() - flower.getY()
                        );
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                for (Flower flower : flowers) {
                    if (flower.isDragging()) {
                        flower.setDragging(false);
                        if (flower.getY() < 350) {
                            flower.setY(350);
                        }
                        repaint();
                    }
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                for (Flower flower : flowers) {
                    if (flower.isDragging()) {
                        int newY = e.getY() - flower.getDragOffsetY();
                        if (newY < 350) newY = 350;
                        if (newY > 550) newY = 550;

                        flower.setX(e.getX() - flower.getDragOffsetX());
                        flower.setY(newY);
                        repaint();
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        sun.draw(g2d);
        grass.draw(g2d);

        for (Cloud cloud : clouds) {
            cloud.draw(g2d);
        }

        for (Bird bird : birds) {
            bird.draw(g2d);
        }

        for (Flower flower : flowers) {
            flower.draw(g2d);
        }
    }
}