/** Clase base del catalogo. Perfume, Celular y Accesorio heredan de aqui. */
class Producto {

    // ---- atributos ----
    #idProducto;
    #nombre;
    #descripcion;
    #precio;
    #stock;
    #imagen;

    
    constructor(idProducto, nombre, descripcion, precio, stock, imagen) {
        if (new.target === Producto) {
            throw new Error("Producto es abstracta: usa Perfume, Celular o Accesorio.");
        }
        this.#idProducto = idProducto;
        this.#nombre = nombre;
        this.#descripcion = descripcion;
        this.#precio = precio;
        this.#stock = stock;
        this.#imagen = imagen;
    }

    getIdProducto() {
        return this.#idProducto;
    }

    getNombre() {
        return this.#nombre;
    }

    setNombre(nombre) {
        this.#nombre = nombre;
    }

    getDescripcion() {
        return this.#descripcion;
    }

    setDescripcion(descripcion) {
        this.#descripcion = descripcion;
    }

    getPrecio() {
        return this.#precio;
    }

    setPrecio(precio) {
        this.#precio = precio;
    }

    getStock() {
        return this.#stock;
    }

    setStock(stock) {
        this.#stock = stock;
    }

    getImagen() {
        return this.#imagen;
    }

   
    hayStock(cantidad) {
        return cantidad > 0 && cantidad <= this.#stock;
    }

    
    actualizarStock(unidades) {
        this.#stock = Math.max(0, this.#stock - unidades);
    }

    precioFormateado() {
        return Dinero.pesos(this.#precio);
    }

    /** Cada subclase muestra sus propios datos (polimorfismo). */
    mostrarInfo() {
        throw new Error("Cada subclase debe implementar mostrarInfo()");
    }

    
    categoria() {
        throw new Error("Cada subclase debe implementar categoria()");
    }

    toString() {
        return this.#nombre + " - " + this.precioFormateado();
    }
}
