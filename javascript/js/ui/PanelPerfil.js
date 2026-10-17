/** Datos del cliente que inicio sesion; aqui puede corregirlos. */
class PanelPerfil {

    #ventana;
    #nombre = Caja.entrada("text", "name");
    #correo = Caja.entrada("email", "email");
    #direccion = Caja.entrada("text", "street-address");
    #aviso = Estilo.texto("", 12, "gris");
    elemento = document.createElement("section");

    constructor(ventana) {
        this.#ventana = ventana;
        this.elemento.className = "vista centrado";
        const caja = PanelRedondo.crear("perfil-caja");
        caja.append(this.#formulario());
        this.elemento.append(caja);
    }

    #formulario() {
        const panel = document.createElement("form");
        panel.className = "columna";
        panel.noValidate = true;
        panel.addEventListener("submit", (e) => {
            e.preventDefault();
            this.#guardar();
        });

        const guardar = new Boton("Guardar cambios", true);
        guardar.elemento.type = "submit";
        const salir = new Boton("Cerrar sesión", false);
        salir.alClic(() => {
            this.#ventana.getTienda().cerrarSesion();
            this.#ventana.mostrar("acceso");
        });

        this.#aviso.classList.add("aviso-ok");
        panel.append(Estilo.micro("Mi cuenta"), Caja.aire(10),
            Estilo.titulo("Mis datos", 32), Caja.aire(24),
            Caja.campo("Nombre completo", this.#nombre),
            Caja.campo("Correo", this.#correo),
            Caja.campo("Dirección de envío", this.#direccion),
            this.#aviso, Caja.aire(14),
            guardar.elemento, Caja.aire(10),
            salir.elemento);
        return panel;
    }

    #guardar() {
        const cliente = this.#ventana.getTienda().getClienteActivo();
        cliente.modificarDatos(this.#nombre.value, this.#correo.value, this.#direccion.value);
        this.#aviso.textContent = "Datos actualizados.";
        this.#ventana.actualizarCabecera();
    }

    
    refrescar() {
        const cliente = this.#ventana.getTienda().getClienteActivo();
        if (cliente === null) {
            return;
        }
        this.#nombre.value = cliente.getNombre();
        this.#correo.value = cliente.getCorreo();
        this.#direccion.value = cliente.getDireccionEnvio();
        this.#aviso.textContent = "";
    }
}
