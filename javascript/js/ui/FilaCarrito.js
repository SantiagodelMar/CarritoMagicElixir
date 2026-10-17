/** Una linea del carrito: producto, cantidad, subtotal y boton para quitarlo. */
class FilaCarrito {

    #item;
    #carrito;
    #alCambiar;
    elemento = document.createElement("div");

    constructor(item, ventana, alCambiar) {
        this.#item = item;
        this.#carrito = ventana.getTienda().getCarrito();
        this.#alCambiar = alCambiar;
        this.elemento.className = "fila-carrito";
        this.elemento.append(this.#miniatura(), this.#detalle(), this.#controles());
    }

    #miniatura() {
        const marco = document.createElement("div");
        marco.className = "miniatura";
        marco.append(Recursos.imagen(this.#item.getProducto().getImagen(), 74, 92));
        return marco;
    }

    #detalle() {
        const producto = this.#item.getProducto();
        const bloque = Caja.columna("detalle");
        bloque.append(Estilo.micro(producto.categoria()), Caja.aire(6),
            Estilo.titulo(producto.getNombre(), 19), Caja.aire(5),
            Estilo.texto(producto.mostrarInfo(), 12, "gris"), Caja.aire(4),
            Estilo.texto(producto.precioFormateado() + " c/u", 12, "gris"));
        return bloque;
    }

    #controles() {
        const bloque = Caja.columna("controles");
        bloque.append(Estilo.titulo(this.#item.subtotalFormateado(), 19), Caja.aire(12));

        const fila = Caja.fila(6);
        const cantidad = Estilo.texto(String(this.#item.getCantidad()), 14, "tinta");
        cantidad.classList.add("cantidad");
        const quitar = new Boton("Quitar", false);
        quitar.compacto();
        quitar.alClic(() => {
            this.#carrito.eliminarProducto(this.#item.getProducto().getIdProducto());
            this.#alCambiar();
        });
        fila.append(this.#cuadro("-", -1), cantidad, this.#cuadro("+", 1), quitar.elemento);
        bloque.append(fila);
        return bloque;
    }

    #cuadro(signo, paso) {
        const boton = new Boton(signo, false);
        boton.elemento.classList.add("cuadro");
        boton.alClic(() => {
            this.#carrito.actualizarCantidad(this.#item.getProducto().getIdProducto(),
                this.#item.getCantidad() + paso);
            this.#alCambiar();
        });
        return boton.elemento;
    }
}
