package app.domain;

/** Usuario de la tienda. Se registra, inicia sesion y puede editar sus datos. */
public class Cliente {

    // ---- atributos ----
    private String nombre;
    private String correo;
    private String direccionEnvio;
    private final String usuario;
    private String contrasena;

    // ---- constructor ----
    public Cliente(String nombre, String correo, String direccionEnvio,
                   String usuario, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.direccionEnvio = direccionEnvio;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // ---- getters y setters ----
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // ---- metodos de negocio ----
    /** Valida los datos antes de guardar el cliente en la tienda. */
    public boolean registrar() {
        if (vacio(nombre) || vacio(usuario) || vacio(contrasena) || vacio(correo)) {
            return false;
        }
        return correo.contains("@") && correo.contains(".") && contrasena.length() >= 4;
    }

    public boolean iniciarSesion(String usuario, String contrasena) {
        return this.usuario.equalsIgnoreCase(usuario) && this.contrasena.equals(contrasena);
    }

    public void modificarDatos(String nombre, String correo, String direccionEnvio) {
        if (!vacio(nombre)) {
            this.nombre = nombre;
        }
        if (!vacio(correo)) {
            this.correo = correo;
        }
        this.direccionEnvio = direccionEnvio;
    }

    public String primerNombre() {
        return nombre.split(" ")[0];
    }

    private boolean vacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
