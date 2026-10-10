package app.ui;

import app.domain.Cliente;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/** Formulario para crear una cuenta nueva de cliente. */
public class FormRegistro extends JPanel {

    private final VentanaPrincipal ventana;
    private final JTextField nombre = new JTextField();
    private final JTextField correo = new JTextField();
    private final JTextField direccion = new JTextField();
    private final JTextField usuario = new JTextField();
    private final JPasswordField contrasena = new JPasswordField();
    private final JLabel aviso = new JLabel(" ");

    public FormRegistro(VentanaPrincipal ventana, Runnable volverAlLogin) {
        this.ventana = ventana;
        setLayout(new java.awt.BorderLayout());
        setBackground(Estilo.BLANCO);
        aviso.setFont(Estilo.sans(12f));
        aviso.setForeground(new Color(0x8C, 0x2F, 0x2F));

        JPanel panel = Caja.columna();
        panel.add(Estilo.micro("Nuevo cliente"));
        panel.add(Caja.aire(12));
        panel.add(Estilo.titulo("Crea tu cuenta", 32f));
        panel.add(Caja.aire(24));
        panel.add(Caja.campo("Nombre completo", nombre));
        panel.add(Caja.campo("Correo", correo));
        panel.add(Caja.campo("Dirección de envío", direccion));
        panel.add(Caja.campo("Usuario", usuario));
        panel.add(Caja.campo("Contraseña", contrasena));
        panel.add(aviso);
        panel.add(Caja.aire(12));

        Boton crear = new Boton("Crear cuenta", true);
        Caja.topeAlto(crear, 46);
        crear.addActionListener(e -> crearCuenta());
        panel.add(crear);
        panel.add(Caja.aire(16));
        panel.add(volver(volverAlLogin));
        add(Caja.columnaIzquierda(panel), java.awt.BorderLayout.NORTH);
    }

    private void crearCuenta() {
        Cliente nuevo = new Cliente(nombre.getText(), correo.getText(), direccion.getText(),
                usuario.getText(), new String(contrasena.getPassword()));
        String error = ventana.getTienda().registrar(nuevo);
        if (error == null) {
            ventana.mostrar("catalogo");
        } else {
            aviso.setText(error);
        }
    }

    private JButton volver(Runnable accion) {
        JButton boton = new JButton("Ya tengo cuenta, ingresar");
        boton.setFont(Estilo.sans(12.5f));
        boton.setForeground(Estilo.GRIS);
        boton.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addActionListener(e -> accion.run());
        return boton;
    }
}
