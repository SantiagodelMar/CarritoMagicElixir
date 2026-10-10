package app.ui;

import app.domain.Orden;
import app.domain.Tienda;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** Ventana unica de la aplicacion. Cambia de vista con un CardLayout. */
public class VentanaPrincipal extends JFrame {

    private final Tienda tienda = new Tienda();
    private final CardLayout vistas = new CardLayout();
    private final JPanel contenido = new JPanel(vistas);

    private final Cabecera cabecera;
    private final PanelCatalogo panelCatalogo;
    private final PanelCarrito panelCarrito;
    private final PanelOrden panelOrden;
    private final PanelPerfil panelPerfil;

    public VentanaPrincipal() {
        setTitle("Magic Elixir Parfum  |  Carrito de compras");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 820);
        setMinimumSize(new Dimension(1060, 700));
        setLocationRelativeTo(null);
        setIconImage(Recursos.imagen("logo", 64, 64).getImage());

        cabecera = new Cabecera(this);
        panelCatalogo = new PanelCatalogo(this);
        panelCarrito = new PanelCarrito(this);
        panelOrden = new PanelOrden(this);
        panelPerfil = new PanelPerfil(this);

        contenido.add(new PanelAcceso(this), "acceso");
        contenido.add(panelCatalogo, "catalogo");
        contenido.add(panelCarrito, "carrito");
        contenido.add(panelOrden, "orden");
        contenido.add(panelPerfil, "perfil");

        setLayout(new BorderLayout());
        add(cabecera, BorderLayout.NORTH);
        add(contenido, BorderLayout.CENTER);
        cabecera.setVisible(false);
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void mostrar(String vista) {
        cabecera.setVisible(!vista.equals("acceso"));
        cabecera.refrescar();
        if (vista.equals("catalogo")) {
            panelCatalogo.refrescar();
        } else if (vista.equals("carrito")) {
            panelCarrito.refrescar();
        } else if (vista.equals("perfil")) {
            panelPerfil.refrescar();
        }
        vistas.show(contenido, vista);
        revalidate();
        repaint();
    }

    public void mostrarOrden(Orden orden) {
        panelOrden.mostrarOrden(orden);
        mostrar("orden");
    }

    public void actualizarCabecera() {
        cabecera.refrescar();
    }
}
