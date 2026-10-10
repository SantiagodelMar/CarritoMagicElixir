package app.ui;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/** Carga imagenes y fuentes desde src/main/resources. */
public final class Recursos {

    private static final Map<String, ImageIcon> CACHE = new HashMap<>();

    private Recursos() {
    }

    /** Primero busca en el classpath; si no esta, lo lee de la carpeta del proyecto. */
    private static InputStream abrir(String ruta) {
        InputStream flujo = Recursos.class.getResourceAsStream("/" + ruta);
        if (flujo != null) {
            return flujo;
        }
        try {
            return new FileInputStream(new File("src/main/resources/" + ruta));
        } catch (Exception e) {
            return null;
        }
    }

    public static Font fuente(String archivo, String respaldo) {
        try (InputStream flujo = abrir("fuentes/" + archivo)) {
            Font fuente = Font.createFont(Font.TRUETYPE_FONT, flujo);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fuente);
            return fuente;
        } catch (Exception e) {
            return new Font(respaldo, Font.PLAIN, 13);
        }
    }

    public static ImageIcon imagen(String nombre, int ancho, int alto) {
        String clave = nombre + "@" + ancho + "x" + alto;
        if (CACHE.containsKey(clave)) {
            return CACHE.get(clave);
        }
        ImageIcon icono;
        try (InputStream flujo = abrir("img/" + nombre + ".png")) {
            Image imagen = ImageIO.read(flujo).getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            icono = new ImageIcon(imagen);
        } catch (Exception e) {
            icono = new ImageIcon(new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB));
        }
        CACHE.put(clave, icono);
        return icono;
    }
}
