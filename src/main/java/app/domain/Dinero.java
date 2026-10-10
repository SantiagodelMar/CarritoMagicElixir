package app.domain;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/** Formato de precios en pesos colombianos: $ 350.000 */
public final class Dinero {

    private static final DecimalFormat FORMATO = crearFormato();

    private Dinero() {
    }

    private static DecimalFormat crearFormato() {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');
        return new DecimalFormat("$ #,##0", simbolos);
    }

    public static String pesos(double valor) {
        return FORMATO.format(valor);
    }
}
