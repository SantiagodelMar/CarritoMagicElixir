/** Perfume de las cuatro lineas que maneja la tienda. */
class Perfume extends Producto {

    
    #linea;            
    #familiaOlfativa;
    #concentracion;    
    #mililitros;

    constructor(id, nombre, descripcion, precio, stock, imagen,
                linea, familiaOlfativa, concentracion, mililitros) {
        super(id, nombre, descripcion, precio, stock, imagen);
        this.#linea = linea;
        this.#familiaOlfativa = familiaOlfativa;
        this.#concentracion = concentracion;
        this.#mililitros = mililitros;
    }

  
    getLinea() {
        return this.#linea;
    }

    getFamiliaOlfativa() {
        return this.#familiaOlfativa;
    }

    getConcentracion() {
        return this.#concentracion;
    }

    getMililitros() {
        return this.#mililitros;
    }

    mostrarInfo() {
        return this.#concentracion + " · " + this.#mililitros + " ml · " + this.#familiaOlfativa;
    }

    categoria() {
        return this.#linea;
    }
}
