package app.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Catalogo simulado en memoria (sin base de datos, como pide la guia). */
public final class Catalogo {

    private Catalogo() {
    }

    public static List<Producto> cargar() {
        List<Producto> productos = new ArrayList<>();

        productos.add(new Perfume("P01", "Lumiere Noire", "Jazmín de noche sobre vainilla ahumada. Deja rastro en el ascensor.",
                420000, 12, "lumiere-noire", "Femenina", "Ámbar floral", "Eau de Parfum", 90));
        productos.add(new Perfume("P02", "Seda Blanca", "Azahar y almizcle limpio, pensado para el día completo en la oficina.",
                365000, 15, "seda-blanca", "Femenina", "Floral blanco", "Eau de Parfum", 75));
        productos.add(new Perfume("P03", "Rosa Medianoche", "Rosa turca con pimienta rosada. Aguanta hasta la madrugada.",
                530000, 8, "rosa-medianoche", "Femenina", "Rosa especiada", "Extrait", 50));
        productos.add(new Perfume("P04", "Acero Azul", "Cítrico salino muy fresco. El de todos los días, nunca cansa.",
                310000, 20, "acero-azul", "Masculina", "Acuática aromática", "Eau de Toilette", 100));
        productos.add(new Perfume("P05", "Cuero y Vetiver", "Cuero suave con vetiver seco. Reuniones de día y salidas de noche.",
                480000, 10, "cuero-vetiver", "Masculina", "Amaderada cuero", "Eau de Parfum", 100));
        productos.add(new Perfume("P06", "Bruma de Cedro", "Cedro, salvia y un fondo cálido por el que siempre preguntan.",
                395000, 11, "bruma-cedro", "Masculina", "Amaderada aromática", "Eau de Parfum", 90));
        productos.add(new Perfume("P07", "Zahra Oud", "Oud suave con rosa y azafrán. Proyecta durante ocho horas.",
                610000, 9, "zahra-oud", "Árabe femenina", "Oud floral", "Eau de Parfum", 85));
        productos.add(new Perfume("P08", "Amira Musk", "Almizcle blanco y vainilla. Sensación de piel limpia y cálida.",
                540000, 10, "amira-musk", "Árabe femenina", "Almizcle vainilla", "Extrait", 60));
        productos.add(new Perfume("P09", "Layali Ambar", "Ámbar, dátil y miel. Rinde mejor en clima frío.",
                575000, 7, "layali-ambar", "Árabe femenina", "Ámbar dulce", "Eau de Parfum", 100));
        productos.add(new Perfume("P10", "Sultan Oud", "Oud, incienso y cuero. Con dos aplicaciones es suficiente.",
                890000, 6, "sultan-oud", "Árabe masculina", "Oud ahumado", "Extrait", 75));
        productos.add(new Perfume("P11", "Arena Dorada", "Canela, ámbar y madera seca. Acompaña todo el día.",
                520000, 12, "arena-dorada", "Árabe masculina", "Ámbar especiado", "Eau de Parfum", 100));
        productos.add(new Perfume("P12", "Noche de Damasco", "Rosa damascena con azafrán. Elegante y nada dulce.",
                640000, 8, "noche-damasco", "Árabe masculina", "Rosa y azafrán", "Eau de Parfum", 90));

        productos.add(new Celular("C01", "Nova X7", "Pantalla grande y batería que aguanta dos días de uso normal.",
                1290000, 6, "nova-x7", "Android 15", 6.7, 50));
        productos.add(new Celular("C02", "Nova Lite 5", "Para quien solo necesita que funcione bien y dure.",
                790000, 9, "nova-lite5", "Android 14", 6.5, 32));
        productos.add(new Celular("C03", "Orbit Pro 12", "Cámara principal de 108 MP y carga rápida incluida.",
                2150000, 4, "orbit-pro12", "Android 15", 6.8, 108));

        productos.add(new Accesorio("A01", "Atomizador de viaje 10 ml", "Recargable y de bolsillo: lleva tu perfume sin cargar el frasco.",
                38000, 40, "atomizador-10", "Atomizador"));
        productos.add(new Accesorio("A02", "Estuche Trío Magic Elixir", "Guarda y transporta tres frascos sin que se golpeen.",
                125000, 15, "estuche-trio", "Estuche"));
        productos.add(new Accesorio("A03", "Audífonos Aura", "Inalámbricos, con estuche de carga y seis horas por sesión.",
                185000, 12, "aura-buds", "Audífonos"));
        productos.add(new Accesorio("A04", "Cargador rápido 30 W", "Llega al 60% de batería en media hora.",
                95000, 25, "carga-30w", "Cargador"));
        productos.add(new Accesorio("A05", "Set de muestras 5 x 2 ml", "Cinco aromas para probar antes de decidirte por el frasco.",
                65000, 30, "set-muestras", "Muestras"));

        return productos;
    }

    public static List<String> categorias() {
        return Arrays.asList("Todos", "Femenina", "Masculina", "Árabe femenina",
                "Árabe masculina", "Celulares", "Accesorios");
    }

    /** Filtra el catalogo por categoria y por el texto del buscador. */
    public static List<Producto> filtrar(List<Producto> productos, String texto, String categoria) {
        List<Producto> resultado = new ArrayList<>();
        String busqueda = texto == null ? "" : texto.trim().toLowerCase();
        for (Producto producto : productos) {
            boolean porCategoria = categoria == null || categoria.equals("Todos")
                    || producto.categoria().equals(categoria);
            boolean porTexto = busqueda.isEmpty()
                    || producto.getNombre().toLowerCase().contains(busqueda)
                    || producto.mostrarInfo().toLowerCase().contains(busqueda);
            if (porCategoria && porTexto) {
                resultado.add(producto);
            }
        }
        return resultado;
    }
}
