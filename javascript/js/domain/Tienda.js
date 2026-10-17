/** Centraliza clientes, catalogo, carrito y ordenes. Todo queda en memoria. */
class Tienda {

    // ---- atributos ----
    static MAX_INTENTOS = 3;

    #clientes = [];
    #productos = Catalogo.cargar();
    #ordenes = [];
    #carrito = new CarritoCompra();
    #clienteActivo = null;
    #intentosFallidos = 0;

   
    constructor() {
        // cliente de prueba para la sustentacion
        this.#clientes.push(new Cliente("Santiago Rodríguez", "santiago@magicelixir.co",
            "Cra 48 # 10-45, El Poblado, Medellín", "santiago", "1234"));
    }

    
    getProductos() {
        return this.#productos;
    }

    getCarrito() {
        return this.#carrito;
    }

    getClienteActivo() {
        return this.#clienteActivo;
    }

    getOrdenes() {
        return this.#ordenes;
    }

    categorias() {
        return Catalogo.categorias();
    }

    intentosRestantes() {
        return Tienda.MAX_INTENTOS - this.#intentosFallidos;
    }

    bloqueado() {
        return this.#intentosFallidos >= Tienda.MAX_INTENTOS;
    }

   
    registrar(nuevo) {
        if (!nuevo.registrar()) {
            return "Revisa los datos: correo válido y contraseña de 4 caracteres o más.";
        }
        for (const c of this.#clientes) {
            if (c.getUsuario().toLowerCase() === nuevo.getUsuario().toLowerCase()) {
                return "Ese usuario ya está registrado.";
            }
        }
        this.#clientes.push(nuevo);
        this.#clienteActivo = nuevo;
        this.#intentosFallidos = 0;
        return null;
    }

    iniciarSesion(usuario, contrasena) {
        for (const c of this.#clientes) {
            if (c.iniciarSesion(usuario, contrasena)) {
                this.#clienteActivo = c;
                this.#intentosFallidos = 0;
                return true;
            }
        }
        this.#intentosFallidos++;
        return false;
    }

    cerrarSesion() {
        this.#clienteActivo = null;
        this.#carrito.vaciar();
    }

    buscar(texto, categoria) {
        return Catalogo.filtrar(this.#productos, texto, categoria);
    }

    confirmarCompra() {
        const orden = new Orden(this.#clienteActivo, this.#carrito);
        orden.generarOrden();
        this.#ordenes.push(orden);
        this.#carrito.vaciar();
        return orden;
    }
}
