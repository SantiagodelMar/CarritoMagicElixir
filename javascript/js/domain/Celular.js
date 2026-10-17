/** La tienda tambien vende celulares, por eso hereda de Producto. */
class Celular extends Producto {

    
    #sistemaOperativo;
    #tamanoPantalla;
    #camara;             

    
    constructor(id, nombre, descripcion, precio, stock, imagen,
                sistemaOperativo, tamanoPantalla, camara) {
        super(id, nombre, descripcion, precio, stock, imagen);
        this.#sistemaOperativo = sistemaOperativo;
        this.#tamanoPantalla = tamanoPantalla;
        this.#camara = camara;
    }

    
    getSistemaOperativo() {
        return this.#sistemaOperativo;
    }

    getTamanoPantalla() {
        return this.#tamanoPantalla;
    }

    getCamara() {
        return this.#camara;
    }

    
    mostrarInfo() {
        return this.#sistemaOperativo + " · " + this.#tamanoPantalla + "\" · " + this.#camara + " MP";
    }

    categoria() {
        return "Celulares";
    }
}
