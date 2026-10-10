package app.ui;

import app.domain.Producto;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/** Tarjeta del catalogo: foto, nombre, descripcion, precio y boton de compra. */
public class TarjetaProducto extends PanelRedondo {

    public TarjetaProducto(Producto producto, VentanaPrincipal ventana) {
        super(new BorderLayout(), 16);
        setPreferredSize(new Dimension(252, 518));
        setBorder(BorderFactory.createEmptyBorder(14, 16, 16, 16));
        add(foto(producto), BorderLayout.NORTH);
        add(datos(producto), BorderLayout.CENTER);
        add(compra(producto, ventana), BorderLayout.SOUTH);
        resaltarAlPasar();
    }

    private JPanel foto(Producto producto) {
        JPanel marco = new JPanel(new BorderLayout());
        marco.setBackground(Estilo.LIENZO);
        marco.setPreferredSize(new Dimension(212, 232));
        JLabel imagen = new JLabel(Recursos.imagen(producto.getImagen(), 212, 232));
        imagen.setHorizontalAlignment(SwingConstants.CENTER);
        marco.add(imagen);
        return marco;
    }

    private JPanel datos(Producto producto) {
        JPanel bloque = Caja.columna();
        bloque.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));
        bloque.add(Estilo.micro(producto.categoria()));
        bloque.add(Caja.aire(7));
        bloque.add(Estilo.titulo(producto.getNombre(), 19f));
        bloque.add(Caja.aire(6));
        bloque.add(Estilo.texto("<html><div style='width:195px'>"
                + producto.getDescripcion() + "</div></html>", 11.5f, Estilo.GRIS));
        bloque.add(Caja.aire(9));
        // la ficha tecnica se parte en dos lineas para que quepa en la tarjeta
        String[] partes = producto.mostrarInfo().split(" · ");
        bloque.add(Estilo.micro(partes.length > 1 ? partes[0] + " · " + partes[1] : partes[0]));
        if (partes.length > 2) {
            bloque.add(Caja.aire(4));
            bloque.add(Estilo.micro(partes[2]));
        }
        return Caja.columnaIzquierda(bloque);
    }

    private JPanel compra(Producto producto, VentanaPrincipal ventana) {
        JPanel bloque = Caja.columna();
        JLabel precio = Estilo.titulo(producto.precioFormateado(), 22f);
        bloque.add(precio);
        if (producto.getStock() <= 8) {
            bloque.add(Caja.aire(4));
            bloque.add(Estilo.micro("Quedan " + producto.getStock() + " unidades"));
        }
        bloque.add(Caja.aire(12));
        Boton agregar = new Boton("Agregar al carrito", true);
        Caja.topeAlto(agregar, 42);
        agregar.addActionListener(e -> {
            ventana.getTienda().getCarrito().agregarProducto(producto, 1);
            ventana.actualizarCabecera();
            confirmar(agregar);
        });
        bloque.add(agregar);
        return Caja.columnaIzquierda(bloque);
    }

    /** Pequeno aviso en el mismo boton para que se note que si quedo agregado. */
    private void confirmar(Boton boton) {
        boton.setText("AGREGADO");
        Timer regreso = new Timer(1200, e -> boton.setText("AGREGAR AL CARRITO"));
        regreso.setRepeats(false);
        regreso.start();
    }

    private void resaltarAlPasar() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorde(Estilo.TINTA);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorde(Estilo.LINEA);
            }
        });
    }
}
