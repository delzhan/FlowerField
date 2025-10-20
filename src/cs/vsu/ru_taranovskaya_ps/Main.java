package cs.vsu.ru_taranovskaya_ps;

import cs.vsu.ru_taranovskaya_ps.View.MainWindow;
import javax.swing.*;

class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}