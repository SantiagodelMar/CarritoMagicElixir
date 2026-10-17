/** Catalogo con buscador, filtros por linea y la grilla de productos. */
class PanelCatalogo {

    #ventana;
    #busqueda = Caja.entrada("search");
    #grilla = document.createElement("div");
    #chips = [];
    #categoria = "Todos";
    elemento = document.createElement("section");

    constructor(ventana) {
        this.#ventana = ventana;
        this.#grilla.className = "grilla";
        this.elemento.className = "vista";
        this.elemento.append(this.#encabezado(), this.#grilla);
    }

    #encabezado() {
        const panel = Caja.columna("catalogo-encabezado");
        panel.append(Estilo.micro("Colección disponible"), Caja.aire(8),
            Estilo.titulo("Elige el tuyo", 36), Caja.aire(6),
            Estilo.texto("Veinte referencias con entrega el mismo día en Medellín.", 13.5, "gris"),
            Caja.aire(16));

        this.#busqueda.addEventListener("input", () => this.refrescar());
        const barra = Caja.campo("Buscar por nombre o nota olfativa", this.#busqueda);
        barra.classList.add("buscador");
        panel.append(barra);

        const filtros = Caja.fila(8);
        for (const nombre of this.#ventana.getTienda().categorias()) {
            const chip = new Boton(nombre, nombre === this.#categoria);
            chip.compacto();
            chip.alClic(() => this.#cambiarCategoria(nombre));
            this.#chips.push(chip);
            filtros.append(chip.elemento);
        }
        panel.append(filtros);
        return panel;
    }

    #cambiarCategoria(nombre) {
        this.#categoria = nombre;
        for (const chip of this.#chips) {
            chip.marcar(chip.getTexto() === nombre.toUpperCase());
        }
        this.refrescar();
    }

   
    refrescar() {
        this.#grilla.replaceChildren();
        const encontrados = this.#ventana.getTienda().buscar(this.#busqueda.value, this.#categoria);
        for (const producto of encontrados) {
            this.#grilla.append(new TarjetaProducto(producto, this.#ventana).elemento);
        }
        if (encontrados.length === 0) {
            this.#grilla.append(Estilo.texto("No hay productos con ese filtro.", 14, "gris"));
        }
    }
}
