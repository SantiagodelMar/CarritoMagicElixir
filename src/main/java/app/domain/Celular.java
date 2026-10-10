package app.domain;

/** La tienda tambien vende celulares, por eso hereda de Producto. */
public class Celular extends Producto {

    // ---- atributos ----
    private final String sistemaOperativo;
    private final double tamanoPantalla;
    private final int camara;              // megapixeles

    // ---- constructor ----
    public Celular(String id, String nombre, String descripcion, double precio, int stock,
                   String imagen, String sistemaOperativo, double tamanoPantalla, int camara) {
        super(id, nombre, descripcion, precio, stock, imagen);
        this.sistemaOperativo = sistemaOperativo;
        this.tamanoPantalla = tamanoPantalla;
        this.camara = camara;
    }

    // ---- getters ----
    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public double getTamanoPantalla() {
        return tamanoPantalla;
    }

    public int getCamara() {
        return camara;
    }

    // ---- metodos de negocio ----
    @Override
    public String mostrarInfo() {
        return sistemaOperativo + " · " + tamanoPantalla + "\" · " + camara + " MP";
    }

    @Override
    public String categoria() {
        return "Celulares";
    }
}
