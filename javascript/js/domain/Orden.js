/** Compra confirmada. Guarda copia de los productos para que no cambie despues. */
class Orden {

    // ---- atributos ----
    static #consecutivo = 1;

    #numeroOrden;
    #cliente;
    #listaProductos = [];
    #subtotal;
    #envio;
    #total;
    #fecha;
    #estado;

    constructor(cliente, carrito) {
        this.#numeroOrden = "ME-" + String(Orden.#consecutivo++).padStart(4, "0");
        this.#cliente = cliente;
        this.#subtotal = carrito.calcularTotal();
        this.#envio = carrito.costoEnvio();
        this.#total = carrito.totalAPagar();
        this.#fecha = new Date();
        this.#estado = "En preparación";
        for (const item of carrito.getListaProductos()) {
            this.#listaProductos.push(new ItemCarrito(item.getProducto(), item.getCantidad()));
        }
    }

    getNumeroOrden() {
        return this.#numeroOrden;
    }

    getCliente() {
        return this.#cliente;
    }

    getListaProductos() {
        return this.#listaProductos;
    }

    getSubtotal() {
        return this.#subtotal;
    }

    getEnvio() {
        return this.#envio;
    }

    getTotal() {
        return this.#total;
    }

    getEstado() {
        return this.#estado;
    }

    setEstado(estado) {
        this.#estado = estado;
    }

    /** Mismo formato que en Java: dd/MM/yyyy  hh:mm a */
    fechaFormateada() {
        const f = this.#fecha;
        const dos = (n) => String(n).padStart(2, "0");
        const horas = f.getHours() % 12 === 0 ? 12 : f.getHours() % 12;
        const ampm = f.getHours() < 12 ? "a. m." : "p. m.";
        return dos(f.getDate()) + "/" + dos(f.getMonth() + 1) + "/" + f.getFullYear()
            + "  " + dos(horas) + ":" + dos(f.getMinutes()) + " " + ampm;
    }

    // ---- metodos de negocio ----
    /** Descuenta el inventario de cada producto comprado. */
    generarOrden() {
        for (const item of this.#listaProductos) {
            item.getProducto().actualizarStock(item.getCantidad());
        }
        this.#estado = "Confirmada";
    }

    mostrarResumen() {
        let resumen = "Orden " + this.#numeroOrden + "\n";
        resumen += this.#cliente.getNombre() + " - " + this.fechaFormateada() + "\n";
        for (const item of this.#listaProductos) {
            resumen += item.getCantidad() + " x " + item.getProducto().getNombre() + "   "
                + item.subtotalFormateado() + "\n";
        }
        resumen += "Total: " + Dinero.pesos(this.#total);
        return resumen;
    }
}
