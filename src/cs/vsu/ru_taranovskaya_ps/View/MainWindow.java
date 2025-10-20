package cs.vsu.ru_taranovskaya_ps.View;

import cs.vsu.ru_taranovskaya_ps.DrawObjects.DrawPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final DrawPanel panel;

    public MainWindow() throws HeadlessException {
        setTitle("Цветочное поле с птичками и облаками");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new DrawPanel();
        this.add(panel);

        pack();
        setLocationRelativeTo(null);
    }
}