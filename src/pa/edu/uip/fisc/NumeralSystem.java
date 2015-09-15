package pa.edu.uip.fisc;

import org.json.simple.parser.ParseException;
import pa.edu.uip.fisc.numerals.RomanNumeral;

import java.io.File;
import java.io.IOException;

/**
 * Created by zgudino on 8/27/15.
 */
public abstract class NumeralSystem {
    /**
     * Fuente de datos
     * @important Toda subclase debe inicializar esta propiedad, de lo contrario retorna una excepcion de IOException
     */
    protected String dataSource;

    /**
     * Inicializa la clase abstracta retornando una nueva instancia de la subclase heredada.
     * @return pa.edu.uip.fisc.NumeralSystem
     */
    public static NumeralSystem getInstance() {
        return createInstance();
    }

    /**
     * Getter retorna el datasource sea esta una instacia de archivo, driver o, adaptador
     * @return dataSource
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * Crear nueva instancia de sistema numeral. Por defecto el sistema numeral Romano
     * @return pa.edu.uip.fisc.NumeralSystem
     */
    private static NumeralSystem createInstance() {
        // @todo Diversidad de sistemas numericos

        /*
        * @important
        *
        * Empleo el patrón de diseño "Singleton"
        * Mas detalle ver https://en.wikipedia.org/wiki/Singleton_pattern
        * */
        NumeralSystem numeralSystem = new RomanNumeral();
        return numeralSystem;
    }

    /**
     * Metodo abstracto asigna el datasource empleado por la subclase
     * @param file
     */
    abstract protected void setDataSource(File file);

    /**
     * Metodo abstracto retorna la conversion de un numero en sistema decimal a aquello instanciado, ejemplo Romano
     * @param number
     * @return String
     * @throws IOException
     * @throws ParseException
     */
    abstract public String get(Integer number) throws IOException, ParseException;
}
