package app.main;

import app.ui.Estilo;
import app.ui.VentanaPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/** Punto de entrada de la aplicacion. */
public class Main {

    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Se usa la apariencia por defecto de Java.");
        }

        SwingUtilities.invokeLater(() -> {
            Estilo.aplicar();
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
