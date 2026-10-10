package app.ui;

import app.domain.Cliente;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Datos del cliente que inicio sesion; aqui puede corregirlos. */
public class PanelPerfil extends JPanel {

    private final VentanaPrincipal ventana;
    private final JTextField nombre = new JTextField();
    private final JTextField correo = new JTextField();
    private final JTextField direccion = new JTextField();
    private final JLabel aviso = Estilo.texto(" ", 12f, Estilo.GRIS);

    public PanelPerfil(VentanaPrincipal ventana) {
        super(new GridBagLayout());
        this.ventana = ventana;
        setBackground(Estilo.BLANCO);
        PanelRedondo caja = new PanelRedondo(new BorderLayout(), 18);
        caja.setPreferredSize(new Dimension(520, 540));
        caja.setBorder(BorderFactory.createEmptyBorder(36, 38, 30, 38));
        caja.add(formulario(), BorderLayout.NORTH);
        add(caja);
    }

    private JPanel formulario() {
        JPanel panel = Caja.columna();
        panel.setOpaque(false);
        panel.add(Estilo.micro("Mi cuenta"));
        panel.add(Caja.aire(10));
        panel.add(Estilo.titulo("Mis datos", 32f));
        panel.add(Caja.aire(24));
        panel.add(Caja.campo("Nombre completo", nombre));
        panel.add(Caja.campo("Correo", correo));
        panel.add(Caja.campo("Dirección de envío", direccion));
        panel.add(aviso);
        panel.add(Caja.aire(14));

        Boton guardar = new Boton("Guardar cambios", true);
        Caja.topeAlto(guardar, 46);
        guardar.addActionListener(e -> guardar());
        panel.add(guardar);
        panel.add(Caja.aire(10));
        Boton salir = new Boton("Cerrar sesión", false);
        Caja.topeAlto(salir, 44);
        salir.addActionListener(e -> {
            ventana.getTienda().cerrarSesion();
            ventana.mostrar("acceso");
        });
        panel.add(salir);
        return Caja.columnaIzquierda(panel);
    }

    private void guardar() {
        Cliente cliente = ventana.getTienda().getClienteActivo();
        cliente.modificarDatos(nombre.getText(), correo.getText(), direccion.getText());
        aviso.setText("Datos actualizados.");
        ventana.actualizarCabecera();
    }

    /** Carga en los campos la informacion del cliente activo. */
    public void refrescar() {
        Cliente cliente = ventana.getTienda().getClienteActivo();
        if (cliente == null) {
            return;
        }
        nombre.setText(cliente.getNombre());
        correo.setText(cliente.getCorreo());
        direccion.setText(cliente.getDireccionEnvio());
        aviso.setText(" ");
    }
}
