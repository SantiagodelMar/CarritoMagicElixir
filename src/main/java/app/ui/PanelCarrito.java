package app.ui;

import app.domain.CarritoCompra;
import app.domain.ItemCarrito;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/** Vista del carrito: las lineas a la izquierda y el resumen a la derecha. */
public class PanelCarrito extends JPanel {

    private final VentanaPrincipal ventana;
    private final JPanel lineas = Caja.columna();
    private final ResumenPedido resumen;

    public PanelCarrito(VentanaPrincipal ventana) {
        super(new BorderLayout());
        this.ventana = ventana;
        this.resumen = new ResumenPedido(ventana);
        setBackground(Estilo.BLANCO);
        setBorder(BorderFactory.createEmptyBorder(28, 34, 28, 34));

        JPanel titulo = Caja.columna();
        titulo.add(Estilo.micro("Carrito de compras"));
        titulo.add(Caja.aire(8));
        titulo.add(Estilo.titulo("Tu selección", 34f));
        titulo.add(Caja.aire(22));
        add(Caja.columnaIzquierda(titulo), BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(lineas);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(Estilo.BLANCO);
        scroll.getVerticalScrollBar().setUnitIncrement(18);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(11, 0));
        add(scroll, BorderLayout.CENTER);
        add(resumen, BorderLayout.EAST);
    }

    /** Dibuja de nuevo las lineas y actualiza los totales en vivo. */
    public void refrescar() {
        CarritoCompra carrito = ventana.getTienda().getCarrito();
        lineas.removeAll();
        if (carrito.estaVacio()) {
            lineas.add(Estilo.texto("Tu carrito está vacío por ahora.", 14f, Estilo.GRIS));
        }
        for (ItemCarrito item : carrito.getListaProductos()) {
            lineas.add(new FilaCarrito(item, ventana, this::refrescar));
        }
        Caja.columnaIzquierda(lineas);
        resumen.actualizar();
        ventana.actualizarCabecera();
        lineas.revalidate();
        lineas.repaint();
    }
}
