# Carrito de compras — Magic Elixir Parfum / JavaScript

Versión web (HTML + CSS + JavaScript) de la aplicación Java Swing de Magic Elixir Parfum:
catálogo, carrito y orden de compra. Todo queda en memoria, sin base de datos y **sin pasarela
de pago** (la compra se confirma y se genera la orden).

Usuario de prueba ya cargado: **santiago** / **1234**

## Cómo abrirla

No hay que instalar nada:

- Doble clic en `index.html`, o
- En VS Code: clic derecho sobre `index.html` → *Open with Live Server* (extensión Live Server).

## Estructura

Cada clase de la versión Java tiene su archivo `.js` con el mismo nombre:

```
index.html            página principal (carga los scripts en orden)
css/estilos.css       paleta y tipografías (equivale a Estilo.java)
js/domain/            lógica del negocio: Producto, Perfume, Celular, Accesorio,
                      ItemCarrito, CarritoCompra, Cliente, Orden, Catalogo, Tienda, Dinero
js/ui/                pantallas: VentanaPrincipal, Cabecera, PanelAcceso, FormAcceso,
                      FormRegistro, PanelCatalogo, TarjetaProducto, PanelCarrito,
                      FilaCarrito, ResumenPedido, PanelOrden, PanelPerfil
js/main/Main.js       punto de entrada
img/, fuentes/        imágenes y tipografías
```

Se conservan los conceptos de POO de la versión Java: herencia (`Perfume extends Producto`),
clase abstracta (`Producto`), polimorfismo (`mostrarInfo()` y `categoria()`) y encapsulamiento
con atributos privados (`#nombre`) y getters/setters.

**Equipo**
Juan Manuel Mira Janne,
Aaron Mateo Ruiz,
Harry Uran Molina,
Santiago Rodríguez Marín.
