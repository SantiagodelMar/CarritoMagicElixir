package app.ui;

import app.domain.Producto;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/** Catalogo con buscador, filtros por linea y la grilla de productos. */
public class PanelCatalogo extends JPanel {

    private final VentanaPrincipal ventana;
    private final JTextField busqueda = new JTextField();
    private final JPanel grilla = new JPanel(new GridLayout(0, 4, 20, 24));
    private final List<Boton> chips = new ArrayList<>();
    private String categoria = "Todos";

    public PanelCatalogo(VentanaPrincipal ventana) {
        super(new BorderLayout());
        this.ventana = ventana;
        setBackground(Estilo.BLANCO);
        grilla.setBackground(Estilo.BLANCO);

        JPanel envoltura = new JPanel(new BorderLayout());
        envoltura.setBackground(Estilo.BLANCO);
        envoltura.setBorder(BorderFactory.createEmptyBorder(0, 34, 34, 34));
        envoltura.add(grilla, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(envoltura);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(Estilo.BLANCO);
        scroll.getVerticalScrollBar().setUnitIncrement(18);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(11, 0));

        add(encabezado(), BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel encabezado() {
        JPanel panel = Caja.columna();
        panel.setBorder(BorderFactory.createEmptyBorder(22, 34, 14, 34));
        panel.add(Estilo.micro("Colección disponible"));
        panel.add(Caja.aire(8));
        panel.add(Estilo.titulo("Elige el tuyo", 36f));
        panel.add(Caja.aire(6));
        panel.add(Estilo.texto("Veinte referencias con entrega el mismo día en Medellín.", 13.5f, Estilo.GRIS));
        panel.add(Caja.aire(16));

        Estilo.campo(busqueda);
        Caja.topeAlto(busqueda, 38);
        busqueda.addCaretListener(e -> refrescar());
        JPanel barra = Caja.campo("Buscar por nombre o nota olfativa", busqueda);
        barra.setMaximumSize(new Dimension(430, 78));
        panel.add(barra);

        JPanel filtros = Caja.fila(8);
        for (String nombre : ventana.getTienda().categorias()) {
            Boton chip = new Boton(nombre, nombre.equals(categoria));
            chip.compacto();
            chip.addActionListener(e -> cambiarCategoria(nombre));
            chips.add(chip);
            filtros.add(chip);
        }
        Caja.topeAlto(filtros, 44);
        panel.add(filtros);
        return Caja.columnaIzquierda(panel);
    }

    private void cambiarCategoria(String nombre) {
        categoria = nombre;
        for (Boton chip : chips) {
            chip.marcar(chip.getText().equalsIgnoreCase(nombre));
        }
        refrescar();
    }

    /** Vuelve a dibujar las tarjetas segun el texto buscado y la categoria. */
    public void refrescar() {
        grilla.removeAll();
        List<Producto> encontrados = ventana.getTienda().buscar(busqueda.getText(), categoria);
        for (Producto producto : encontrados) {
            grilla.add(new TarjetaProducto(producto, ventana));
        }
        if (encontrados.isEmpty()) {
            grilla.add(Estilo.texto("No hay productos con ese filtro.", 14f, Estilo.GRIS));
        }
        grilla.revalidate();
        grilla.repaint();
    }
}
