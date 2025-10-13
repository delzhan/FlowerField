package cs.vsu.ru_taranovskaya_ps;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final FlowerField panel;

    public MainWindow() throws HeadlessException {
        setTitle("Цветочное поле с птичками и облаками");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new FlowerField();
        this.add(panel);

        pack();
        setLocationRelativeTo(null);
    }
}