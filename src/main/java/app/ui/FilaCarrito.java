package app.ui;

import app.domain.CarritoCompra;
import app.domain.ItemCarrito;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/** Una linea del carrito: producto, cantidad, subtotal y boton para quitarlo. */
public class FilaCarrito extends JPanel {

    private final ItemCarrito item;
    private final CarritoCompra carrito;
    private final Runnable alCambiar;

    public FilaCarrito(ItemCarrito item, VentanaPrincipal ventana, Runnable alCambiar) {
        super(new BorderLayout(18, 0));
        this.item = item;
        this.carrito = ventana.getTienda().getCarrito();
        this.alCambiar = alCambiar;
        setBackground(Estilo.BLANCO);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Estilo.LINEA),
                BorderFactory.createEmptyBorder(16, 0, 16, 14)));
        Caja.topeAlto(this, 124);
        add(miniatura(), BorderLayout.WEST);
        add(detalle(), BorderLayout.CENTER);
        add(controles(), BorderLayout.EAST);
    }

    private JPanel miniatura() {
        JPanel marco = new JPanel(new BorderLayout());
        marco.setBackground(Estilo.LIENZO);
        marco.setPreferredSize(new Dimension(74, 92));
        JLabel foto = new JLabel(Recursos.imagen(item.getProducto().getImagen(), 74, 92));
        foto.setHorizontalAlignment(SwingConstants.CENTER);
        marco.add(foto);
        return marco;
    }

    private JPanel detalle() {
        JPanel bloque = Caja.columna();
        bloque.add(Estilo.micro(item.getProducto().categoria()));
        bloque.add(Caja.aire(6));
        bloque.add(Estilo.titulo(item.getProducto().getNombre(), 19f));
        bloque.add(Caja.aire(5));
        bloque.add(Estilo.texto(item.getProducto().mostrarInfo(), 12f, Estilo.GRIS));
        bloque.add(Caja.aire(4));
        bloque.add(Estilo.texto(item.getProducto().precioFormateado() + " c/u", 12f, Estilo.GRIS));
        return Caja.columnaIzquierda(bloque);
    }

    private JPanel controles() {
        JPanel bloque = Caja.columna();
        bloque.add(Estilo.titulo(item.subtotalFormateado(), 19f));
        bloque.add(Caja.aire(12));

        JPanel fila = Caja.fila(6);
        JLabel cantidad = Estilo.texto(String.valueOf(item.getCantidad()), 14f, Estilo.TINTA);
        cantidad.setHorizontalAlignment(SwingConstants.CENTER);
        cantidad.setPreferredSize(new Dimension(30, 30));
        fila.add(cuadro("-", -1));
        fila.add(cantidad);
        fila.add(cuadro("+", 1));
        Boton quitar = new Boton("Quitar", false);
        quitar.compacto();
        quitar.addActionListener(e -> {
            carrito.eliminarProducto(item.getProducto().getIdProducto());
            alCambiar.run();
        });
        fila.add(quitar);
        bloque.add(fila);
        return Caja.columnaIzquierda(bloque);
    }

    private Boton cuadro(String signo, int paso) {
        Boton boton = new Boton(signo, false);
        boton.setFont(Estilo.sansBold(15f));
        boton.setBorder(BorderFactory.createEmptyBorder(4, 4, 6, 4));
        boton.setPreferredSize(new Dimension(32, 30));
        boton.addActionListener(e -> {
            carrito.actualizarCantidad(item.getProducto().getIdProducto(), item.getCantidad() + paso);
            alCambiar.run();
        });
        return boton;
    }
}
