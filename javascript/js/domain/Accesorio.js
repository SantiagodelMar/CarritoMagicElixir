/** Accesorios de la tienda: atomizadores, estuches, audifonos, cargadores. */
class Accesorio extends Producto {

    
    #tipoAccesorio;

    
    constructor(id, nombre, descripcion, precio, stock, imagen, tipoAccesorio) {
        super(id, nombre, descripcion, precio, stock, imagen);
        this.#tipoAccesorio = tipoAccesorio;
    }


    getTipoAccesorio() {
        return this.#tipoAccesorio;
    }

    
    mostrarInfo() {
        return this.#tipoAccesorio + " · Compatible con toda la línea";
    }

    categoria() {
        return "Accesorios";
    }
}
