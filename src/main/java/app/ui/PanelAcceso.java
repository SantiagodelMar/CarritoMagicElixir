package app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Pantalla de inicio: la marca a la izquierda y el formulario a la derecha. */
public class PanelAcceso extends JPanel {

    private static final Color CREMA = new Color(0xF6, 0xF4, 0xF0);
    private static final Color CREMA_TENUE = new Color(0x9A, 0x97, 0x92);

    public PanelAcceso(VentanaPrincipal ventana) {
        super(new BorderLayout());
        setBackground(Estilo.BLANCO);
        add(portada(), BorderLayout.WEST);
        add(new FormAcceso(ventana), BorderLayout.CENTER);
    }

    private JPanel portada() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Estilo.TINTA);
        panel.setPreferredSize(new Dimension(505, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(66, 54, 46, 54));

        JLabel logo = new JLabel(Recursos.imagen("logo", 72, 72));
        panel.add(logo);
        panel.add(Caja.aire(34));
        panel.add(linea("El aroma", Estilo.serif(44f), CREMA));
        panel.add(linea("que te recuerdan", Estilo.serif(44f), CREMA));
        panel.add(Caja.aire(22));
        panel.add(linea("Perfumería original, celulares y accesorios.", Estilo.sans(14f), CREMA_TENUE));
        panel.add(linea("Lo que elijas sale el mismo día del local.", Estilo.sans(14f), CREMA_TENUE));
        panel.add(Box.createVerticalGlue());
        panel.add(rotulo("Envíos a todo el país"));
        panel.add(Caja.aire(10));
        panel.add(rotulo("Garantía de 30 días"));
        panel.add(Caja.aire(10));
        panel.add(rotulo("C.C. Monterrey  ·  El Poblado, Medellín"));
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);
        return panel;
    }

    private JLabel linea(String texto, Font fuente, Color color) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(fuente);
        etiqueta.setForeground(color);
        etiqueta.setAlignmentX(Component.LEFT_ALIGNMENT);
        return etiqueta;
    }

    private JLabel rotulo(String texto) {
        JLabel etiqueta = linea(texto.toUpperCase(), Estilo.espaciada(Estilo.sansBold(10f), 0.18f), CREMA_TENUE);
        etiqueta.setIcon(null);
        return etiqueta;
    }
}
