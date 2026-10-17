/** Ventana unica de la aplicacion. Cambia de vista mostrando una seccion y ocultando las demas. */
class VentanaPrincipal {

    #tienda = new Tienda();
    #vistas;

    #cabecera;
    #panelCatalogo;
    #panelCarrito;
    #panelOrden;
    #panelPerfil;

    constructor(raiz) {
        document.title = "Magic Elixir Parfum  |  Carrito de compras";

        this.#cabecera = new Cabecera(this);
        this.#panelCatalogo = new PanelCatalogo(this);
        this.#panelCarrito = new PanelCarrito(this);
        this.#panelOrden = new PanelOrden(this);
        this.#panelPerfil = new PanelPerfil(this);

        this.#vistas = {
            acceso: new PanelAcceso(this).elemento,
            catalogo: this.#panelCatalogo.elemento,
            carrito: this.#panelCarrito.elemento,
            orden: this.#panelOrden.elemento,
            perfil: this.#panelPerfil.elemento,
        };

        const contenido = document.createElement("main");
        contenido.append(...Object.values(this.#vistas));
        raiz.append(this.#cabecera.elemento, contenido);
        this.#cabecera.setVisible(false);
    }

    getTienda() {
        return this.#tienda;
    }

    mostrar(vista) {
        this.#cabecera.setVisible(vista !== "acceso");
        this.#cabecera.refrescar();
        if (vista === "catalogo") {
            this.#panelCatalogo.refrescar();
        } else if (vista === "carrito") {
            this.#panelCarrito.refrescar();
        } else if (vista === "perfil") {
            this.#panelPerfil.refrescar();
        }
        for (const [nombre, seccion] of Object.entries(this.#vistas)) {
            seccion.hidden = nombre !== vista;
        }
        window.scrollTo(0, 0);
    }

    mostrarOrden(orden) {
        this.#panelOrden.mostrarOrden(orden);
        this.mostrar("orden");
    }

    actualizarCabecera() {
        this.#cabecera.refrescar();
    }
}
