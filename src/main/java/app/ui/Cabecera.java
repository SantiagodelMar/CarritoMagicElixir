package app.ui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Barra superior: marca a la izquierda, navegacion y carrito a la derecha. */
public class Cabecera extends JPanel {

    private final VentanaPrincipal ventana;
    private final Boton carrito = new Boton("Carrito  ·  0", true);
    private final JLabel saludo = Estilo.micro("");

    public Cabecera(VentanaPrincipal ventana) {
        super(new BorderLayout());
        this.ventana = ventana;
        setBackground(Estilo.BLANCO);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Estilo.LINEA),
                BorderFactory.createEmptyBorder(14, 34, 14, 34)));
        add(marca(), BorderLayout.WEST);
        add(acciones(), BorderLayout.EAST);
    }

    private JPanel marca() {
        JPanel bloque = Caja.fila(12);
        JLabel icono = new JLabel(Recursos.imagen("logo", 38, 38));
        JPanel textos = Caja.columna();
        JLabel nombre = Estilo.titulo("Magic Elixir", 20f);
        textos.add(nombre);
        textos.add(Estilo.micro("Parfum  ·  C.C. Monterrey, Medellín"));
        bloque.add(icono);
        bloque.add(textos);
        bloque.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bloque.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ventana.mostrar("catalogo");
            }
        });
        return bloque;
    }

    private JPanel acciones() {
        JPanel bloque = Caja.fila(18);
        bloque.add(saludo);
        bloque.add(enlace("Catálogo", "catalogo"));
        bloque.add(enlace("Mi perfil", "perfil"));
        carrito.compacto();
        carrito.addActionListener(e -> ventana.mostrar("carrito"));
        bloque.add(carrito);
        return bloque;
    }

    private JButton enlace(String texto, String vista) {
        JButton boton = new JButton(texto);
        boton.setFont(Estilo.sans(13f));
        boton.setForeground(Estilo.TINTA);
        boton.setBorder(BorderFactory.createEmptyBorder(6, 4, 6, 4));
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addActionListener(e -> ventana.mostrar(vista));
        return boton;
    }

    /** Actualiza el contador del carrito y el nombre de quien esta comprando. */
    public void refrescar() {
        int unidades = ventana.getTienda().getCarrito().cantidadArticulos();
        carrito.setText("CARRITO  ·  " + unidades);
        if (ventana.getTienda().getClienteActivo() != null) {
            saludo.setText("HOLA, " + ventana.getTienda().getClienteActivo().primerNombre().toUpperCase());
        }
    }
}
