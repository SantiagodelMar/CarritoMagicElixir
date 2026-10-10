package app.domain;

/** Perfume de las cuatro lineas que maneja la tienda. */
public class Perfume extends Producto {

    // ---- atributos ----
    private final String linea;            // Femenina, Masculina, Arabe femenina, Arabe masculina
    private final String familiaOlfativa;
    private final String concentracion;    // EDT, EDP, Extrait
    private final int mililitros;

    // ---- constructor ----
    public Perfume(String id, String nombre, String descripcion, double precio, int stock,
                   String imagen, String linea, String familiaOlfativa,
                   String concentracion, int mililitros) {
        super(id, nombre, descripcion, precio, stock, imagen);
        this.linea = linea;
        this.familiaOlfativa = familiaOlfativa;
        this.concentracion = concentracion;
        this.mililitros = mililitros;
    }

    // ---- getters ----
    public String getLinea() {
        return linea;
    }

    public String getFamiliaOlfativa() {
        return familiaOlfativa;
    }

    public String getConcentracion() {
        return concentracion;
    }

    public int getMililitros() {
        return mililitros;
    }

    // ---- metodos de negocio ----
    @Override
    public String mostrarInfo() {
        return concentracion + " · " + mililitros + " ml · " + familiaOlfativa;
    }

    @Override
    public String categoria() {
        return linea;
    }
}
