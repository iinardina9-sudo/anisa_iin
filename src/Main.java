import ui.SuratForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SuratForm().setVisible(true);
        });
    }
}
