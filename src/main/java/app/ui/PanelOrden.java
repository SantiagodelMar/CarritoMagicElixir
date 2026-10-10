package app.ui;

import app.domain.Dinero;
import app.domain.ItemCarrito;
import app.domain.Orden;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Resumen de la orden que se genera al confirmar la compra. */
public class PanelOrden extends JPanel {

    private final VentanaPrincipal ventana;
    private final JPanel contenido = Caja.columna();

    public PanelOrden(VentanaPrincipal ventana) {
        super(new GridBagLayout());
        this.ventana = ventana;
        setBackground(Estilo.BLANCO);
        PanelRedondo caja = new PanelRedondo(new BorderLayout(), 18);
        caja.setPreferredSize(new Dimension(660, 620));
        caja.setBorder(BorderFactory.createEmptyBorder(38, 40, 34, 40));
        contenido.setOpaque(false);
        caja.add(contenido, BorderLayout.NORTH);
        add(caja);
    }

    /** Arma el detalle con los datos de la orden recien creada. */
    public void mostrarOrden(Orden orden) {
        contenido.removeAll();
        contenido.add(Estilo.micro("Orden " + orden.getNumeroOrden()));
        contenido.add(Caja.aire(10));
        contenido.add(Estilo.titulo("Gracias por tu compra", 34f));
        contenido.add(Caja.aire(8));
        contenido.add(Estilo.texto("Te escribimos por WhatsApp para coordinar la entrega.", 13.5f, Estilo.GRIS));
        contenido.add(Caja.aire(24));

        contenido.add(renglon("Fecha", orden.fechaFormateada()));
        contenido.add(renglon("Cliente", orden.getCliente().getNombre()));
        contenido.add(renglon("Envío a", orden.getCliente().getDireccionEnvio()));
        contenido.add(renglon("Estado", orden.getEstado()));
        contenido.add(Caja.aire(18));
        contenido.add(Estilo.micro("Productos"));
        contenido.add(Caja.aire(10));

        for (ItemCarrito item : orden.getListaProductos()) {
            contenido.add(renglon(item.getCantidad() + " x " + item.getProducto().getNombre(),
                    item.subtotalFormateado()));
        }
        contenido.add(Caja.aire(16));
        contenido.add(renglon("Subtotal", Dinero.pesos(orden.getSubtotal())));
        contenido.add(renglon("Envío", orden.getEnvio() == 0 ? "Gratis" : Dinero.pesos(orden.getEnvio())));
        contenido.add(Caja.aire(14));
        contenido.add(Estilo.micro("Total pagado"));
        contenido.add(Caja.aire(4));
        contenido.add(Estilo.titulo(Dinero.pesos(orden.getTotal()), 30f));
        contenido.add(Caja.aire(26));

        Boton seguir = new Boton("Volver al catálogo", true);
        Caja.topeAlto(seguir, 46);
        seguir.addActionListener(e -> ventana.mostrar("catalogo"));
        contenido.add(seguir);
        Caja.columnaIzquierda(contenido);
        contenido.revalidate();
        contenido.repaint();
    }

    private JPanel renglon(String etiqueta, String valor) {
        JPanel fila = new JPanel(new BorderLayout());
        fila.setOpaque(false);
        fila.add(Estilo.texto(etiqueta, 13f, Estilo.GRIS), BorderLayout.WEST);
        JLabel derecha = Estilo.texto(valor, 13f, Estilo.TINTA);
        fila.add(derecha, BorderLayout.EAST);
        fila.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        Caja.topeAlto(fila, 30);
        return fila;
    }
}
