/** Ingreso de clientes. El acceso se bloquea despues de tres intentos fallidos. */
class FormAcceso {

    #ventana;
    #usuario = Caja.entrada("text", "username");
    #clave = Caja.entrada("password", "current-password");
    #aviso = Caja.aviso();
    #ingresar = new Boton("Ingresar", true);
    #login;
    #registro;
    elemento = document.createElement("div");

    constructor(ventana) {
        this.#ventana = ventana;
        this.#login = this.#crearLogin();
        this.#registro = new FormRegistro(ventana, () => this.#mostrarForma("login")).elemento;

        const contenido = document.createElement("div");
        contenido.className = "forma";
        contenido.append(this.#login, this.#registro);
        this.elemento.className = "acceso-form";
        this.elemento.append(contenido);
        this.#mostrarForma("login");
    }

    #mostrarForma(nombre) {
        this.#login.hidden = nombre !== "login";
        this.#registro.hidden = nombre !== "registro";
    }

    #crearLogin() {
        const panel = document.createElement("form");
        panel.className = "columna";
        panel.noValidate = true;
        this.#ingresar.elemento.type = "submit";
        panel.addEventListener("submit", (e) => {
            e.preventDefault();
            this.#entrar();
        });

        panel.append(Estilo.micro("Cuenta de cliente"), Caja.aire(12),
            Estilo.titulo("Ingresa a tu cuenta", 32), Caja.aire(28),
            Caja.campo("Usuario", this.#usuario),
            Caja.campo("Contraseña", this.#clave),
            this.#aviso, Caja.aire(14),
            this.#ingresar.elemento, Caja.aire(18),
            this.#enlace("Crear una cuenta nueva"), Caja.aire(30),
            Estilo.micro("Prueba:  usuario santiago  ·  clave 1234"));
        return panel;
    }

    #entrar() {
        const tienda = this.#ventana.getTienda();
        if (tienda.bloqueado()) {
            return;
        }
        if (tienda.iniciarSesion(this.#usuario.value, this.#clave.value)) {
            this.#aviso.textContent = "";
            this.#clave.value = "";
            this.#ventana.mostrar("catalogo");
        } else if (tienda.bloqueado()) {
            this.#aviso.textContent = "Tres intentos fallidos: acceso bloqueado.";
            this.#ingresar.setEnabled(false);
        } else {
            this.#aviso.textContent = "Datos incorrectos. Te quedan " + tienda.intentosRestantes() + " intentos.";
        }
    }

    #enlace(texto) {
        const boton = document.createElement("button");
        boton.type = "button";
        boton.className = "enlace gris";
        boton.textContent = texto;
        boton.addEventListener("click", () => this.#mostrarForma("registro"));
        return boton;
    }
}
