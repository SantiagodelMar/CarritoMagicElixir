package app.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/** Compra confirmada. Guarda copia de los productos para que no cambie despues. */
public class Orden {

    // ---- atributos ----
    private static int consecutivo = 1;

    private final String numeroOrden;
    private final Cliente cliente;
    private final List<ItemCarrito> listaProductos = new ArrayList<>();
    private final double subtotal;
    private final double envio;
    private final double total;
    private final LocalDateTime fecha;
    private String estado;

    // ---- constructor ----
    public Orden(Cliente cliente, CarritoCompra carrito) {
        this.numeroOrden = String.format("ME-%04d", consecutivo++);
        this.cliente = cliente;
        this.subtotal = carrito.calcularTotal();
        this.envio = carrito.costoEnvio();
        this.total = carrito.totalAPagar();
        this.fecha = LocalDateTime.now();
        this.estado = "En preparación";
        for (ItemCarrito item : carrito.getListaProductos()) {
            listaProductos.add(new ItemCarrito(item.getProducto(), item.getCantidad()));
        }
    }

    // ---- getters ----
    public String getNumeroOrden() {
        return numeroOrden;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemCarrito> getListaProductos() {
        return listaProductos;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getEnvio() {
        return envio;
    }

    public double getTotal() {
        return total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String fechaFormateada() {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy  hh:mm a"));
    }

    // ---- metodos de negocio ----
    /** Descuenta el inventario de cada producto comprado. */
    public void generarOrden() {
        for (ItemCarrito item : listaProductos) {
            item.getProducto().actualizarStock(item.getCantidad());
        }
        estado = "Confirmada";
    }

    public String mostrarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("Orden ").append(numeroOrden).append("\n");
        resumen.append(cliente.getNombre()).append(" - ").append(fechaFormateada()).append("\n");
        for (ItemCarrito item : listaProductos) {
            resumen.append(item.getCantidad()).append(" x ")
                    .append(item.getProducto().getNombre()).append("   ")
                    .append(item.subtotalFormateado()).append("\n");
        }
        resumen.append("Total: ").append(Dinero.pesos(total));
        return resumen.toString();
    }
}
