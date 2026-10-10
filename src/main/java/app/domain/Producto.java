package app.domain;

/** Clase base del catalogo. Perfume, Celular y Accesorio heredan de aqui. */
public abstract class Producto {

    // ---- atributos ----
    private final String idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private final String imagen;

    // ---- constructor ----
    protected Producto(String idProducto, String nombre, String descripcion,
                       double precio, int stock, String imagen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
    }

    // ---- getters y setters ----
    public String getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    // ---- metodos de negocio ----
    public boolean hayStock(int cantidad) {
        return cantidad > 0 && cantidad <= stock;
    }

    /** Descuenta unidades cuando se confirma una compra. */
    public void actualizarStock(int unidades) {
        stock = Math.max(0, stock - unidades);
    }

    public String precioFormateado() {
        return Dinero.pesos(precio);
    }

    /** Cada subclase muestra sus propios datos (polimorfismo). */
    public abstract String mostrarInfo();

    /** Nombre de la linea con la que se filtra en el catalogo. */
    public abstract String categoria();

    @Override
    public String toString() {
        return nombre + " - " + precioFormateado();
    }
}
