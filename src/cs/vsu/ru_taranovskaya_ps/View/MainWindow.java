package cs.vsu.ru_taranovskaya_ps.View;

import cs.vsu.ru_taranovskaya_ps.DrawObjects.DrawPanel;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Цветочное поле");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        DrawPanel drawPanel = new DrawPanel();
        add(drawPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}