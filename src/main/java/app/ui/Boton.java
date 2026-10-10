package app.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/** Boton plano. Solido (negro) para la accion principal, con borde para las demas. */
public class Boton extends JButton {

    private boolean solido;
    private boolean encima;

    public Boton(String texto, boolean solido) {
        super(texto.toUpperCase());
        this.solido = solido;
        setFont(Estilo.espaciada(Estilo.sansBold(11f), 0.14f));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder(11, 20, 11, 20));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        pintarTexto();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                encima = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                encima = false;
                repaint();
            }
        });
    }

    public void marcar(boolean activo) {
        solido = activo;
        pintarTexto();
        repaint();
    }

    public void compacto() {
        setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        setFont(Estilo.espaciada(Estilo.sansBold(10.5f), 0.12f));
    }

    private void pintarTexto() {
        setForeground(solido ? Color.WHITE : Estilo.TINTA);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (solido) {
            g2.setColor(encima ? new Color(0x2B, 0x2B, 0x2E) : Estilo.TINTA);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
        } else {
            g2.setColor(encima ? Estilo.LIENZO : Estilo.BLANCO);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
            g2.setColor(Estilo.LINEA);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
        }
        g2.dispose();
        super.paintComponent(g);
    }
}
