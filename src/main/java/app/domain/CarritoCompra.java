package app.domain;

import java.util.ArrayList;
import java.util.List;

/** Carrito del cliente: agrega, elimina, cambia cantidades y calcula el total. */
public class CarritoCompra {

    // ---- atributos ----
    public static final double ENVIO = 15000;
    public static final double MINIMO_ENVIO_GRATIS = 250000;

    private final List<ItemCarrito> listaProductos = new ArrayList<>();

    // ---- getters ----
    public List<ItemCarrito> getListaProductos() {
        return listaProductos;
    }

    // ---- metodos de negocio ----
    public void agregarProducto(Producto producto, int cantidad) {
        ItemCarrito item = buscar(producto.getIdProducto());
        if (item != null) {
            item.sumar(cantidad);
        } else {
            listaProductos.add(new ItemCarrito(producto, cantidad));
        }
    }

    public void eliminarProducto(String idProducto) {
        ItemCarrito item = buscar(idProducto);
        if (item != null) {
            listaProductos.remove(item);
        }
    }

    /** Si la cantidad baja a cero la linea se quita del carrito. */
    public void actualizarCantidad(String idProducto, int cantidad) {
        ItemCarrito item = buscar(idProducto);
        if (item == null) {
            return;
        }
        if (cantidad <= 0) {
            listaProductos.remove(item);
        } else {
            item.setCantidad(Math.min(cantidad, item.getProducto().getStock()));
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito item : listaProductos) {
            total += item.subtotal();
        }
        return total;
    }

    public double costoEnvio() {
        double subtotal = calcularTotal();
        if (subtotal == 0 || subtotal >= MINIMO_ENVIO_GRATIS) {
            return 0;
        }
        return ENVIO;
    }

    public double totalAPagar() {
        return calcularTotal() + costoEnvio();
    }

    public int cantidadArticulos() {
        int unidades = 0;
        for (ItemCarrito item : listaProductos) {
            unidades += item.getCantidad();
        }
        return unidades;
    }

    public boolean estaVacio() {
        return listaProductos.isEmpty();
    }

    public void vaciar() {
        listaProductos.clear();
    }

    private ItemCarrito buscar(String idProducto) {
        for (ItemCarrito item : listaProductos) {
            if (item.getProducto().getIdProducto().equals(idProducto)) {
                return item;
            }
        }
        return null;
    }
}
