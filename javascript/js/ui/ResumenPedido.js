/** Caja de la derecha en el carrito: subtotal, envio, total y botones. */
class ResumenPedido {

    #ventana;
    #subtotal = Estilo.texto("", 13.5, "tinta");
    #envio = Estilo.texto("", 13.5, "tinta");
    #total = Estilo.titulo("", 28);
    elemento = PanelRedondo.crear("resumen");

    constructor(ventana) {
        this.#ventana = ventana;

        const confirmar = new Boton("Confirmar compra", true);
        confirmar.alClic(() => this.#confirmar());
        const seguir = new Boton("Seguir comprando", false);
        seguir.alClic(() => ventana.mostrar("catalogo"));

        const columna = Caja.columna();
        columna.append(Estilo.micro("Resumen del pedido"), Caja.aire(20),
            this.#renglon("Subtotal", this.#subtotal), Caja.aire(10),
            this.#renglon("Envío", this.#envio), Caja.aire(18),
            Estilo.micro("Total a pagar"), Caja.aire(4),
            this.#total, Caja.aire(22),
            confirmar.elemento, Caja.aire(10),
            seguir.elemento, Caja.aire(16),
            Estilo.micro("IVA incluido en el precio"), Caja.aire(6),
            Estilo.micro("Envío gratis desde " + Dinero.pesos(CarritoCompra.MINIMO_ENVIO_GRATIS)));
        this.elemento.append(columna);
    }

    /** Vuelve a escribir los totales cada vez que cambia el carrito. */
    actualizar() {
        const carrito = this.#ventana.getTienda().getCarrito();
        this.#subtotal.textContent = Dinero.pesos(carrito.calcularTotal());
        this.#envio.textContent = carrito.costoEnvio() === 0 ? "Gratis" : Dinero.pesos(carrito.costoEnvio());
        this.#total.textContent = Dinero.pesos(carrito.totalAPagar());
    }

    #confirmar() {
        if (this.#ventana.getTienda().getCarrito().estaVacio()) {
            return;
        }
        this.#ventana.mostrarOrden(this.#ventana.getTienda().confirmarCompra());
        this.#ventana.actualizarCabecera();
    }

    #renglon(etiqueta, valor) {
        const fila = document.createElement("div");
        fila.className = "renglon";
        fila.append(Estilo.texto(etiqueta, 13.5, "gris"), valor);
        return fila;
    }
}
