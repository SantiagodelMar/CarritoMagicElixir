package app.ui;

import app.domain.CarritoCompra;
import app.domain.Dinero;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Caja de la derecha en el carrito: subtotal, envio, total y botones. */
public class ResumenPedido extends PanelRedondo {

    private final VentanaPrincipal ventana;
    private final JLabel subtotal = Estilo.texto("", 13.5f, Estilo.TINTA);
    private final JLabel envio = Estilo.texto("", 13.5f, Estilo.TINTA);
    private final JLabel total = Estilo.titulo("", 28f);

    public ResumenPedido(VentanaPrincipal ventana) {
        super(new BorderLayout(), 16);
        this.ventana = ventana;
        setBackground(Estilo.LIENZO);
        setBorde(null);
        setPreferredSize(new Dimension(340, 0));
        setBorder(BorderFactory.createEmptyBorder(26, 26, 26, 26));

        JPanel columna = Caja.columna();
        columna.setOpaque(false);
        columna.add(Estilo.micro("Resumen del pedido"));
        columna.add(Caja.aire(20));
        columna.add(renglon("Subtotal", subtotal));
        columna.add(Caja.aire(10));
        columna.add(renglon("Envío", envio));
        columna.add(Caja.aire(18));
        columna.add(Estilo.micro("Total a pagar"));
        columna.add(Caja.aire(4));
        columna.add(total);
        columna.add(Caja.aire(22));

        Boton confirmar = new Boton("Confirmar compra", true);
        Caja.topeAlto(confirmar, 48);
        confirmar.addActionListener(e -> confirmar());
        columna.add(confirmar);
        columna.add(Caja.aire(10));
        Boton seguir = new Boton("Seguir comprando", false);
        Caja.topeAlto(seguir, 44);
        seguir.addActionListener(e -> ventana.mostrar("catalogo"));
        columna.add(seguir);
        columna.add(Caja.aire(16));
        columna.add(Estilo.micro("IVA incluido en el precio"));
        columna.add(Caja.aire(6));
        columna.add(Estilo.micro("Envío gratis desde "
                + Dinero.pesos(CarritoCompra.MINIMO_ENVIO_GRATIS)));
        add(Caja.columnaIzquierda(columna), BorderLayout.NORTH);
    }

    /** Vuelve a escribir los totales cada vez que cambia el carrito. */
    public void actualizar() {
        CarritoCompra carrito = ventana.getTienda().getCarrito();
        subtotal.setText(Dinero.pesos(carrito.calcularTotal()));
        envio.setText(carrito.costoEnvio() == 0 ? "Gratis" : Dinero.pesos(carrito.costoEnvio()));
        total.setText(Dinero.pesos(carrito.totalAPagar()));
    }

    private void confirmar() {
        if (ventana.getTienda().getCarrito().estaVacio()) {
            return;
        }
        ventana.mostrarOrden(ventana.getTienda().confirmarCompra());
        ventana.actualizarCabecera();
    }

    private JPanel renglon(String etiqueta, JLabel valor) {
        JPanel fila = new JPanel(new BorderLayout());
        fila.setOpaque(false);
        fila.add(Estilo.texto(etiqueta, 13.5f, Estilo.GRIS), BorderLayout.WEST);
        fila.add(valor, BorderLayout.EAST);
        Caja.topeAlto(fila, 24);
        return fila;
    }
}
