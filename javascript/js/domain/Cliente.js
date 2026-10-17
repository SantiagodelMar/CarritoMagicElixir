/** Usuario de la tienda. Se registra, inicia sesion y puede editar sus datos. */
class Cliente {

    
    #nombre;
    #correo;
    #direccionEnvio;
    #usuario;
    #contrasena;

    
    constructor(nombre, correo, direccionEnvio, usuario, contrasena) {
        this.#nombre = nombre;
        this.#correo = correo;
        this.#direccionEnvio = direccionEnvio;
        this.#usuario = usuario;
        this.#contrasena = contrasena;
    }

    
    getNombre() {
        return this.#nombre;
    }

    getCorreo() {
        return this.#correo;
    }

    getDireccionEnvio() {
        return this.#direccionEnvio;
    }

    getUsuario() {
        return this.#usuario;
    }

    setContrasena(contrasena) {
        this.#contrasena = contrasena;
    }

  
    registrar() {
        if (this.#vacio(this.#nombre) || this.#vacio(this.#usuario)
                || this.#vacio(this.#contrasena) || this.#vacio(this.#correo)) {
            return false;
        }
        return this.#correo.includes("@") && this.#correo.includes(".") && this.#contrasena.length >= 4;
    }

    iniciarSesion(usuario, contrasena) {
        return this.#usuario.toLowerCase() === usuario.toLowerCase() && this.#contrasena === contrasena;
    }

    modificarDatos(nombre, correo, direccionEnvio) {
        if (!this.#vacio(nombre)) {
            this.#nombre = nombre;
        }
        if (!this.#vacio(correo)) {
            this.#correo = correo;
        }
        this.#direccionEnvio = direccionEnvio;
    }

    primerNombre() {
        return this.#nombre.split(" ")[0];
    }

    #vacio(texto) {
        return texto === null || texto === undefined || texto.trim() === "";
    }
}
