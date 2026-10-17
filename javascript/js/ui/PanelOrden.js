/** Resumen de la orden que se genera al confirmar la compra. */
class PanelOrden {

    #ventana;
    #contenido = Caja.columna();
    elemento = document.createElement("section");

    constructor(ventana) {
        this.#ventana = ventana;
        this.elemento.className = "vista centrado";
        const caja = PanelRedondo.crear("orden-caja");
        caja.append(this.#contenido);
        this.elemento.append(caja);
    }

   
    mostrarOrden(orden) {
        const c = this.#contenido;
        c.replaceChildren();
        c.append(Estilo.micro("Orden " + orden.getNumeroOrden()), Caja.aire(10),
            Estilo.titulo("Gracias por tu compra", 34), Caja.aire(8),
            Estilo.texto("Te escribimos por WhatsApp para coordinar la entrega.", 13.5, "gris"),
            Caja.aire(24),
            this.#renglon("Fecha", orden.fechaFormateada()),
            this.#renglon("Cliente", orden.getCliente().getNombre()),
            this.#renglon("Envío a", orden.getCliente().getDireccionEnvio()),
            this.#renglon("Estado", orden.getEstado()),
            Caja.aire(18), Estilo.micro("Productos"), Caja.aire(10));

        for (const item of orden.getListaProductos()) {
            c.append(this.#renglon(item.getCantidad() + " x " + item.getProducto().getNombre(),
                item.subtotalFormateado()));
        }

        const seguir = new Boton("Volver al catálogo", true);
        seguir.alClic(() => this.#ventana.mostrar("catalogo"));

        c.append(Caja.aire(16),
            this.#renglon("Subtotal", Dinero.pesos(orden.getSubtotal())),
            this.#renglon("Envío", orden.getEnvio() === 0 ? "Gratis" : Dinero.pesos(orden.getEnvio())),
            Caja.aire(14), Estilo.micro("Total pagado"), Caja.aire(4),
            Estilo.titulo(Dinero.pesos(orden.getTotal()), 30), Caja.aire(26),
            seguir.elemento);
    }

    #renglon(etiqueta, valor) {
        const fila = Caja.renglon(etiqueta, valor, 13);
        fila.classList.add("con-aire");
        return fila;
    }
}
