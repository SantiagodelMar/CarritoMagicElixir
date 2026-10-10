package app.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

/** Atajos para armar filas y columnas sin repetir el mismo codigo de layout. */
public final class Caja {

    private Caja() {
    }

    public static JPanel columna() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Estilo.BLANCO);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return panel;
    }

    public static JPanel fila(int separacion) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, separacion, 0));
        panel.setBackground(Estilo.BLANCO);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return panel;
    }

    public static Component aire(int alto) {
        return Box.createVerticalStrut(alto);
    }

    public static void alinear(JComponent... componentes) {
        for (JComponent componente : componentes) {
            componente.setAlignmentX(Component.LEFT_ALIGNMENT);
        }
    }

    public static void topeAlto(JComponent componente, int alto) {
        componente.setMaximumSize(new Dimension(Integer.MAX_VALUE, alto));
    }

    /** Deja todos los hijos pegados a la izquierda dentro de una columna. */
    public static JPanel columnaIzquierda(JPanel panel) {
        for (Component hijo : panel.getComponents()) {
            if (hijo instanceof JComponent) {
                ((JComponent) hijo).setAlignmentX(Component.LEFT_ALIGNMENT);
            }
        }
        return panel;
    }

    /** Rotulo pequeno arriba y campo de texto abajo, como en los formularios. */
    public static JPanel campo(String etiqueta, JTextComponent control) {
        JPanel bloque = columna();
        Estilo.campo(control);
        topeAlto(control, 40);
        JComponent rotulo = Estilo.micro(etiqueta);
        alinear(rotulo, control);
        bloque.add(rotulo);
        bloque.add(aire(6));
        bloque.add(control);
        bloque.add(aire(18));
        topeAlto(bloque, 92);
        return bloque;
    }
}
