package app.domain;

/** Accesorios de la tienda: atomizadores, estuches, audifonos, cargadores. */
public class Accesorio extends Producto {

    // ---- atributos ----
    private final String tipoAccesorio;

    // ---- constructor ----
    public Accesorio(String id, String nombre, String descripcion, double precio, int stock,
                     String imagen, String tipoAccesorio) {
        super(id, nombre, descripcion, precio, stock, imagen);
        this.tipoAccesorio = tipoAccesorio;
    }

    // ---- getters ----
    public String getTipoAccesorio() {
        return tipoAccesorio;
    }

    // ---- metodos de negocio ----
    @Override
    public String mostrarInfo() {
        return tipoAccesorio + " · Compatible con toda la línea";
    }

    @Override
    public String categoria() {
        return "Accesorios";
    }
}
