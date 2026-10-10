package app.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

/** Paleta y tipografias de la tienda: serif para vender, sans para leer. */
public final class Estilo {

    public static final Color BLANCO = Color.WHITE;
    public static final Color LIENZO = new Color(0xF4, 0xF2, 0xEE);
    public static final Color TINTA = new Color(0x0C, 0x0C, 0x0D);
    public static final Color GRIS = new Color(0x74, 0x72, 0x6E);
    public static final Color LINEA = new Color(0xE3, 0xE0, 0xDA);

    private static Font serif;
    private static Font serifBold;
    private static Font sans;
    private static Font sansBold;

    private Estilo() {
    }

    public static void aplicar() {
        serif = Recursos.fuente("PlayfairDisplay_400Regular.ttf", Font.SERIF);
        serifBold = Recursos.fuente("PlayfairDisplay_700Bold.ttf", Font.SERIF);
        sans = Recursos.fuente("Inter_400Regular.ttf", Font.SANS_SERIF);
        sansBold = Recursos.fuente("Inter_600SemiBold.ttf", Font.SANS_SERIF);

        String[] claves = {"Label.font", "Button.font", "TextField.font", "PasswordField.font",
                "ComboBox.font", "TextArea.font", "OptionPane.messageFont"};
        for (String clave : claves) {
            UIManager.put(clave, sans(13f));
        }
        UIManager.put("Panel.background", BLANCO);
    }

    public static Font serif(float tamano) {
        return serif.deriveFont(tamano);
    }

    public static Font serifBold(float tamano) {
        return serifBold.deriveFont(tamano);
    }

    public static Font sans(float tamano) {
        return sans.deriveFont(tamano);
    }

    public static Font sansBold(float tamano) {
        return sansBold.deriveFont(tamano);
    }

    /** Separa las letras; se usa en los rotulos pequenos en mayuscula. */
    public static Font espaciada(Font fuente, float separacion) {
        Map<TextAttribute, Object> atributos = new HashMap<>();
        atributos.put(TextAttribute.TRACKING, separacion);
        return fuente.deriveFont(atributos);
    }

    public static JLabel micro(String texto) {
        JLabel etiqueta = new JLabel(texto.toUpperCase());
        etiqueta.setFont(espaciada(sansBold(10f), 0.18f));
        etiqueta.setForeground(GRIS);
        return etiqueta;
    }

    public static JLabel titulo(String texto, float tamano) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(serifBold(tamano));
        etiqueta.setForeground(TINTA);
        return etiqueta;
    }

    public static JLabel texto(String contenido, float tamano, Color color) {
        JLabel etiqueta = new JLabel(contenido);
        etiqueta.setFont(sans(tamano));
        etiqueta.setForeground(color);
        return etiqueta;
    }

    /** Deja el campo con una sola linea abajo, como los formularios de tienda. */
    public static void campo(JTextComponent control) {
        control.setFont(sans(14f));
        control.setForeground(TINTA);
        control.setBackground(BLANCO);
        control.setCaretColor(TINTA);
        control.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, LINEA),
                BorderFactory.createEmptyBorder(8, 2, 8, 2)));
    }
}
