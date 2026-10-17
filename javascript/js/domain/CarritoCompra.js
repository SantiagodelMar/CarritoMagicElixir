/** Carrito del cliente: agrega, elimina, cambia cantidades y calcula el total. */
class CarritoCompra {

    
    static ENVIO = 15000;
    static MINIMO_ENVIO_GRATIS = 250000;

    #listaProductos = [];

    
    getListaProductos() {
        return this.#listaProductos;
    }

    
    agregarProducto(producto, cantidad) {
        const item = this.#buscar(producto.getIdProducto());
        if (item !== null) {
            item.sumar(cantidad);
        } else {
            this.#listaProductos.push(new ItemCarrito(producto, cantidad));
        }
    }

    eliminarProducto(idProducto) {
        const item = this.#buscar(idProducto);
        if (item !== null) {
            this.#quitar(item);
        }
    }

   
    actualizarCantidad(idProducto, cantidad) {
        const item = this.#buscar(idProducto);
        if (item === null) {
            return;
        }
        if (cantidad <= 0) {
            this.#quitar(item);
        } else {
            item.setCantidad(Math.min(cantidad, item.getProducto().getStock()));
        }
    }

    calcularTotal() {
        let total = 0;
        for (const item of this.#listaProductos) {
            total += item.subtotal();
        }
        return total;
    }

    costoEnvio() {
        const subtotal = this.calcularTotal();
        if (subtotal === 0 || subtotal >= CarritoCompra.MINIMO_ENVIO_GRATIS) {
            return 0;
        }
        return CarritoCompra.ENVIO;
    }

    totalAPagar() {
        return this.calcularTotal() + this.costoEnvio();
    }

    cantidadArticulos() {
        let unidades = 0;
        for (const item of this.#listaProductos) {
            unidades += item.getCantidad();
        }
        return unidades;
    }

    estaVacio() {
        return this.#listaProductos.length === 0;
    }

    vaciar() {
        this.#listaProductos.length = 0;
    }

    #buscar(idProducto) {
        for (const item of this.#listaProductos) {
            if (item.getProducto().getIdProducto() === idProducto) {
                return item;
            }
        }
        return null;
    }

    #quitar(item) {
        this.#listaProductos.splice(this.#listaProductos.indexOf(item), 1);
    }
}
