package app.domain;

/** Una linea del carrito: el producto y cuantas unidades lleva el cliente. */
public class ItemCarrito {

    // ---- atributos ----
    private final Producto producto;
    private int cantidad;

    // ---- constructor ----
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // ---- getters y setters ----
    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // ---- metodos de negocio ----
    public void sumar(int unidades) {
        cantidad += unidades;
    }

    public double subtotal() {
        return producto.getPrecio() * cantidad;
    }

    public String subtotalFormateado() {
        return Dinero.pesos(subtotal());
    }
}
