import org.json.simple.parser.ParseException;
import pa.edu.uip.fisc.NumeralSystem;

import java.io.IOException;
import java.util.Scanner;

/**
 * Proyecto Final
 *
 * Problema #8
 * Se tiene un numero de 2 cifras, convertir a romano
 *
 * @description El sistema de numeración romana es un sistema de numeración no posicional que se desarrolló en la Antigua Roma
 * y se utilizó en todo el Imperio romano.
 *
 * Su conversión es relativamente sencilla, toma un número del sistema decimal y se relaciona con caracteres ya definidos por el sistema romano.
 * Puede ser implementado completamente estático, sería armar una tabla inmensa con cada símbolo de cada número decimal, o dinámicamente, siendo esta la que empleamos.
 *
 * Una base de datos con símbolos de interés, lo llamamos “base”, de tipo de dato JSON (key/value pair) contiene los símbolos de 1-10
 * y 10 en 10 hasta llegar a 90.
 * La clase abstracta NumeralSystem es nuestro plano para todo las implementaciones de sistema de numeración no posicional.
 * Contiene propiedades, métodos abstractos y método que inicializa retornando una subclase por defecto, RomanNumeral.
 *
 * La clase RomanNumeral, por otro lado, mantiene la lógica necesaria para convertir un numero decimal a Romano.
 * Esta clase depende de una librería externa, json-simple, para facilitar manejo de archivo JSON como una colección de objeto.
 *
 * @author Zahir Gudino
 * @created 27/08/2015
 * @licenses MIT
 */
public class Main {
    public static void main(String[] args) {
        NumeralSystem roman = NumeralSystem.getInstance();
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        String r = null;

        System.out.println("Ingrese un número entero de no más de 2 dígitos...");
        try {
            n = scanner.nextInt();
            r = roman.get(n);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        finally {
            scanner.close();
        }

        System.out.printf("Número %s en Romano es \'%s\'", n, r);
    }
}
