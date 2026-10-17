/** Pantalla de inicio: la marca a la izquierda y el formulario a la derecha. */
class PanelAcceso {

    elemento = document.createElement("section");

    constructor(ventana) {
        this.elemento.className = "vista acceso";
        this.elemento.append(this.#portada(), new FormAcceso(ventana).elemento);
    }

    #portada() {
        const panel = document.createElement("aside");
        panel.className = "portada";

        const rotulos = document.createElement("div");
        rotulos.className = "rotulos";
        rotulos.append(Estilo.micro("Envíos a todo el país"),
            Estilo.micro("Garantía de 30 días"),
            Estilo.micro("C.C. Monterrey  ·  El Poblado, Medellín"));

        panel.append(Recursos.imagen("logo", 72, 72), Caja.aire(34),
            this.#linea("El aroma", "lema"),
            this.#linea("que te recuerdan", "lema"),
            Caja.aire(22),
            this.#linea("Perfumería original, celulares y accesorios.", "bajada"),
            this.#linea("Lo que elijas sale el mismo día del local.", "bajada"),
            rotulos);
        return panel;
    }

    #linea(texto, clase) {
        const etiqueta = document.createElement("div");
        etiqueta.className = clase;
        etiqueta.textContent = texto;
        return etiqueta;
    }
}
