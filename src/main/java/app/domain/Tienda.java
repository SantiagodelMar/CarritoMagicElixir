package app.domain;

import java.util.ArrayList;
import java.util.List;

/** Centraliza clientes, catalogo, carrito y ordenes. Todo queda en memoria. */
public class Tienda {

    // ---- atributos ----
    public static final int MAX_INTENTOS = 3;

    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Producto> productos = Catalogo.cargar();
    private final List<Orden> ordenes = new ArrayList<>();
    private final CarritoCompra carrito = new CarritoCompra();
    private Cliente clienteActivo;
    private int intentosFallidos;

    // ---- constructor ----
    public Tienda() {
        // cliente de prueba para la sustentacion
        clientes.add(new Cliente("Santiago Rodríguez", "santiago@magicelixir.co",
                "Cra 48 # 10-45, El Poblado, Medellín", "santiago", "1234"));
    }

    // ---- getters ----
    public List<Producto> getProductos() {
        return productos;
    }

    public CarritoCompra getCarrito() {
        return carrito;
    }

    public Cliente getClienteActivo() {
        return clienteActivo;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public List<String> categorias() {
        return Catalogo.categorias();
    }

    public int intentosRestantes() {
        return MAX_INTENTOS - intentosFallidos;
    }

    public boolean bloqueado() {
        return intentosFallidos >= MAX_INTENTOS;
    }

    // ---- metodos de negocio ----
    /** Devuelve null si el registro fue correcto, o el mensaje del error. */
    public String registrar(Cliente nuevo) {
        if (!nuevo.registrar()) {
            return "Revisa los datos: correo válido y contraseña de 4 caracteres o más.";
        }
        for (Cliente c : clientes) {
            if (c.getUsuario().equalsIgnoreCase(nuevo.getUsuario())) {
                return "Ese usuario ya está registrado.";
            }
        }
        clientes.add(nuevo);
        clienteActivo = nuevo;
        intentosFallidos = 0;
        return null;
    }

    public boolean iniciarSesion(String usuario, String contrasena) {
        for (Cliente c : clientes) {
            if (c.iniciarSesion(usuario, contrasena)) {
                clienteActivo = c;
                intentosFallidos = 0;
                return true;
            }
        }
        intentosFallidos++;
        return false;
    }

    public void cerrarSesion() {
        clienteActivo = null;
        carrito.vaciar();
    }

    public List<Producto> buscar(String texto, String categoria) {
        return Catalogo.filtrar(productos, texto, categoria);
    }

    public Orden confirmarCompra() {
        Orden orden = new Orden(clienteActivo, carrito);
        orden.generarOrden();
        ordenes.add(orden);
        carrito.vaciar();
        return orden;
    }
}
