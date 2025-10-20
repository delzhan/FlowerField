package cs.vsu.ru_taranovskaya_ps;

import cs.vsu.ru_taranovskaya_ps.View.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
    }
}