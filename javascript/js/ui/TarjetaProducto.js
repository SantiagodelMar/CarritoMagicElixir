/** Tarjeta del catalogo: foto, nombre, descripcion, precio y boton de compra. */
class TarjetaProducto {

    elemento = PanelRedondo.crear("tarjeta");

    constructor(producto, ventana) {
        this.elemento.append(this.#foto(producto), this.#datos(producto), this.#compra(producto, ventana));
    }

    #foto(producto) {
        const marco = document.createElement("div");
        marco.className = "foto";
        const imagen = Recursos.imagen(producto.getImagen(), 212, 232);
        imagen.alt = producto.getNombre();
        marco.append(imagen);
        return marco;
    }

    #datos(producto) {
        const bloque = Caja.columna("datos");
        const descripcion = Estilo.texto(producto.getDescripcion(), 11.5, "gris");
        descripcion.classList.add("descripcion");
        bloque.append(Estilo.micro(producto.categoria()), Caja.aire(7),
            Estilo.titulo(producto.getNombre(), 19), Caja.aire(6),
            descripcion, Caja.aire(9));
        // la ficha tecnica se parte en dos lineas para que quepa en la tarjeta
        const partes = producto.mostrarInfo().split(" · ");
        bloque.append(Estilo.micro(partes.length > 1 ? partes[0] + " · " + partes[1] : partes[0]));
        if (partes.length > 2) {
            bloque.append(Caja.aire(4), Estilo.micro(partes[2]));
        }
        return bloque;
    }

    #compra(producto, ventana) {
        const bloque = Caja.columna("compra");
        bloque.append(Estilo.titulo(producto.precioFormateado(), 22));
        if (producto.getStock() <= 8) {
            bloque.append(Caja.aire(4), Estilo.micro("Quedan " + producto.getStock() + " unidades"));
        }
        bloque.append(Caja.aire(12));
        const agregar = new Boton("Agregar al carrito", true);
        agregar.alClic(() => {
            ventana.getTienda().getCarrito().agregarProducto(producto, 1);
            ventana.actualizarCabecera();
            this.#confirmar(agregar);
        });
        bloque.append(agregar.elemento);
        return bloque;
    }

    /** Pequeno aviso en el mismo boton para que se note que si quedo agregado. */
    #confirmar(boton) {
        boton.setTexto("Agregado");
        setTimeout(() => boton.setTexto("Agregar al carrito"), 1200);
    }
}
