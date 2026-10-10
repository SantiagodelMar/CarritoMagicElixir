# Carrito de compras — Magic Elixir Parfum

Aplicación de escritorio en Java (Swing) que simula la tienda en línea de Magic Elixir Parfum:
catálogo, carrito y orden de compra. Todo queda en memoria, sin base de datos y **sin pasarela
de pago** (la compra se confirma y se genera la orden, como se pidió para la exposición).

## Cómo abrirlo

1. IntelliJ IDEA → **File → Open** → seleccionar la carpeta `CarritoMagicElixir`.
2. Esperar a que indexe (trae `pom.xml`, así que IntelliJ configura solo el proyecto).
3. Abrir `src/main/java/app/main/Main.java` y darle **Run**.
4. Requiere JDK 17 o superior.

Usuario de prueba ya cargado: **santiago** / **1234**.
También se puede crear una cuenta nueva desde la pantalla de inicio.

## Estructura

```
src/main/java/app/
├── domain/   clases del negocio (POO)
├── ui/       pantallas en Swing
└── main/     Main.java
src/main/resources/
├── img/      20 imágenes de producto + logo
└── fuentes/  Playfair Display e Inter (se cargan en tiempo de ejecución)
```

## Diagrama de clases

```
                     Producto  (abstracta)
                     idProducto, nombre, descripcion, precio, stock
                     mostrarInfo()      <-- se sobrescribe: POLIMORFISMO
                     actualizarStock()
                             ▲
          ┌──────────────────┼──────────────────┐
       Perfume            Celular            Accesorio
   linea, familia,    sistemaOperativo,     tipoAccesorio
   concentracion,     tamanoPantalla,
   mililitros         camara

   Cliente  1 ──── 1  CarritoCompra  1 ──── *  ItemCarrito  * ──── 1  Producto
   registrar()        agregarProducto()        cantidad
   iniciarSesion()    eliminarProducto()       subtotal()
   modificarDatos()   actualizarCantidad()
                      calcularTotal()
                             │  al confirmar
                             ▼
                          Orden
                 numeroOrden, cliente, listaProductos,
                 total, fecha, estado
                 generarOrden(), mostrarResumen()

   Tienda  →  reúne clientes, catálogo, carrito y órdenes (datos en memoria)
```

La herencia evita repetir atributos: `Perfume`, `Celular` y `Accesorio` comparten lo que ya
está en `Producto`. El polimorfismo se ve en `mostrarInfo()`: la tarjeta del catálogo llama
siempre al mismo método y cada tipo responde con sus propios datos
("Eau de Parfum · 90 ml · Ámbar floral" frente a "Android 15 · 6.7" · 50 MP").

## Lista de chequeo del profesor

| Punto | Dónde está |
|---|---|
| Registro de usuarios | `FormRegistro` + `Tienda.registrar()` |
| Inicio de sesión y máximo 3 intentos | `FormAcceso.entrar()` + `Tienda.bloqueado()` |
| Modificar datos personales | `PanelPerfil` + `Cliente.modificarDatos()` |
| Catálogo con nombre, imagen, descripción y precio | `PanelCatalogo` + `TarjetaProducto` |
| Agregar al carrito | `CarritoCompra.agregarProducto()` |
| Modificar cantidades y eliminar | `FilaCarrito` + `actualizarCantidad()` / `eliminarProducto()` |
| Total en tiempo real | `ResumenPedido.actualizar()` |
| Confirmar compra y generar orden | `Tienda.confirmarCompra()` + `PanelOrden` |
| Herencia | `Perfume`, `Celular`, `Accesorio` extienden `Producto` |
| Polimorfismo | `mostrarInfo()` sobrescrito en las tres subclases |
| Datos en memoria | `Catalogo.cargar()` y las listas de `Tienda` |

## Por qué la tipografía y el color están así (para sustentar)

- **Dos familias, dos trabajos.** Playfair Display (serif de alto contraste) para nombres y
  precios: los remates y el contraste fino/grueso se asocian a categoría premium, y la
  tipografía coherente con el producto aumenta la preferencia de marca. Inter (sans humanista)
  para la interfaz: máxima legibilidad en tamaños chicos y menos carga cognitiva al leer.
- **Jerarquía en tres niveles.** Foto → nombre (19 pt serif) → precio (22 pt serif). El ojo
  recorre siempre el mismo camino, y la ficha técnica va en 10 pt para no competir.
- **Rótulos en mayúscula con tracking de 0.18 em.** Convención del lujo y, además, separan
  visualmente la "etiqueta" del dato.
- **Precio sin decimales y sin color de alarma.** Un formato simple se percibe más bajo; evitar
  el rojo y el tachado reduce la sensación de "dolor de pagar".
- **Blanco y negro puro.** Contraste máximo (negro #0C0C0D sobre blanco) para que el único
  elemento con relleno sólido sea el botón de compra: la atención cae donde queremos.
- **Fondo crema (#F4F2EE) detrás de la foto.** El frasco negro resalta más que sobre blanco puro.
- **Botón de ancho completo y 42–48 px de alto.** Objetivo grande = clic más rápido y seguro
  (ley de Fitts).
- **Dos empujones de venta honestos:** "Quedan N unidades" cuando el inventario es bajo
  (escasez real, tomada del atributo `stock`) y "Envío gratis desde $250.000", que sube el
  valor del pedido.

## Guion corto para exponer (3 minutos)

1. **Inicio** — "La tienda pide usuario y contraseña; el sistema solo da tres intentos".
   Fallar una vez a propósito para mostrar el contador.
2. **Catálogo** — filtrar por *Árabe masculina*, buscar "oud". "Cada tarjeta se dibuja con los
   datos del objeto; la línea técnica la devuelve `mostrarInfo()`, que es distinto en perfumes,
   celulares y accesorios: ahí está el polimorfismo".
3. **Carrito** — agregar dos productos, subir una cantidad, quitar otro. "El total se recalcula
   en `calcularTotal()` cada vez que cambia el carrito".
4. **Orden** — confirmar. "Se crea un objeto `Orden` con número, fecha y estado, y se descuenta
   el stock de cada producto".
5. **Perfil** — cambiar la dirección y guardar, para cerrar con `modificarDatos()`.

## Nota

La guía del módulo pedía HTML5, CSS3 y JavaScript. Esta entrega está en Java para IntelliJ,
como se acordó; el diseño de clases y la lista de requisitos funcionales se respetan igual.
