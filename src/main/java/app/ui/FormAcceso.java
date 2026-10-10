package app.ui;

import app.domain.Tienda;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/** Ingreso de clientes. El acceso se bloquea despues de tres intentos fallidos. */
public class FormAcceso extends JPanel {

    private final VentanaPrincipal ventana;
    private final CardLayout formas = new CardLayout();
    private final JPanel contenido = new JPanel(formas);

    private final JTextField usuario = new JTextField();
    private final JPasswordField clave = new JPasswordField();
    private final JLabel aviso = new JLabel(" ");
    private final Boton ingresar = new Boton("Ingresar", true);

    public FormAcceso(VentanaPrincipal ventana) {
        super(new GridBagLayout());
        this.ventana = ventana;
        setBackground(Estilo.BLANCO);
        aviso.setFont(Estilo.sans(12f));
        aviso.setForeground(new Color(0x8C, 0x2F, 0x2F));

        contenido.setBackground(Estilo.BLANCO);
        contenido.setPreferredSize(new Dimension(400, 580));
        contenido.add(login(), "login");
        contenido.add(new FormRegistro(ventana, () -> formas.show(contenido, "login")), "registro");
        add(contenido);
    }

    private JPanel login() {
        JPanel panel = Caja.columna();
        panel.add(Estilo.micro("Cuenta de cliente"));
        panel.add(Caja.aire(12));
        panel.add(Estilo.titulo("Ingresa a tu cuenta", 32f));
        panel.add(Caja.aire(28));
        panel.add(Caja.campo("Usuario", usuario));
        panel.add(Caja.campo("Contraseña", clave));
        panel.add(aviso);
        panel.add(Caja.aire(14));

        Caja.topeAlto(ingresar, 46);
        ingresar.addActionListener(e -> entrar());
        clave.addActionListener(e -> entrar());
        panel.add(ingresar);
        panel.add(Caja.aire(18));
        panel.add(enlace("Crear una cuenta nueva"));
        panel.add(Caja.aire(30));
        panel.add(Estilo.micro("Prueba:  usuario santiago  ·  clave 1234"));
        return Caja.columnaIzquierda(panel);
    }

    private void entrar() {
        Tienda tienda = ventana.getTienda();
        if (tienda.iniciarSesion(usuario.getText(), new String(clave.getPassword()))) {
            aviso.setText(" ");
            clave.setText("");
            ventana.mostrar("catalogo");
        } else if (tienda.bloqueado()) {
            aviso.setText("Tres intentos fallidos: acceso bloqueado.");
            ingresar.setEnabled(false);
        } else {
            aviso.setText("Datos incorrectos. Te quedan " + tienda.intentosRestantes() + " intentos.");
        }
    }

    private JButton enlace(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(Estilo.sans(12.5f));
        boton.setForeground(Estilo.GRIS);
        boton.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addActionListener(e -> formas.show(contenido, "registro"));
        return boton;
    }
}
