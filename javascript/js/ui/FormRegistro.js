/** Formulario para crear una cuenta nueva de cliente. */
class FormRegistro {

    #ventana;
    #nombre = Caja.entrada("text", "name");
    #correo = Caja.entrada("email", "email");
    #direccion = Caja.entrada("text", "street-address");
    #usuario = Caja.entrada("text", "username");
    #contrasena = Caja.entrada("password", "new-password");
    #aviso = Caja.aviso();
    elemento = document.createElement("form");

    constructor(ventana, volverAlLogin) {
        this.#ventana = ventana;
        this.elemento.className = "columna";
        this.elemento.noValidate = true;
        this.elemento.addEventListener("submit", (e) => {
            e.preventDefault();
            this.#crearCuenta();
        });

        const crear = new Boton("Crear cuenta", true);
        crear.elemento.type = "submit";

        this.elemento.append(Estilo.micro("Nuevo cliente"), Caja.aire(12),
            Estilo.titulo("Crea tu cuenta", 32), Caja.aire(24),
            Caja.campo("Nombre completo", this.#nombre),
            Caja.campo("Correo", this.#correo),
            Caja.campo("Dirección de envío", this.#direccion),
            Caja.campo("Usuario", this.#usuario),
            Caja.campo("Contraseña", this.#contrasena),
            this.#aviso, Caja.aire(12),
            crear.elemento, Caja.aire(16),
            this.#volver(volverAlLogin));
    }

    #crearCuenta() {
        const nuevo = new Cliente(this.#nombre.value, this.#correo.value, this.#direccion.value,
            this.#usuario.value, this.#contrasena.value);
        const error = this.#ventana.getTienda().registrar(nuevo);
        if (error === null) {
            this.#aviso.textContent = "";
            this.#ventana.mostrar("catalogo");
        } else {
            this.#aviso.textContent = error;
        }
    }

    #volver(accion) {
        const boton = document.createElement("button");
        boton.type = "button";
        boton.className = "enlace gris";
        boton.textContent = "Ya tengo cuenta, ingresar";
        boton.addEventListener("click", accion);
        return boton;
    }
}
