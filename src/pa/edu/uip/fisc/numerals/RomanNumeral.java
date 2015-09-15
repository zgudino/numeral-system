package pa.edu.uip.fisc.numerals;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pa.edu.uip.fisc.NumeralSystem;

import javax.management.InstanceNotFoundException;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by zgudino on 8/27/15.
 */
public class RomanNumeral extends NumeralSystem {
    /**
     * Constructor de la clase RomanNumeral
     * @important La clase RomanNumeral espera que el objeto instaciado sea de clase NumeralSystem de lo contrario,
     * InstanceNotFoundException es retornado
     */
    public RomanNumeral() {
        try {
            if (!(this instanceof NumeralSystem))
                throw new InstanceNotFoundException("Error: Clase RomanNumeral debe ser instancia de NumeralSystem");

            setDataSource(new File("data/db.json"));
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implementacion de metodo setDataSource asigna la fuente de datos de este sistema numerico
     * @param file
     */
    @Override
    protected void setDataSource(File file) {
        try {
            if (!file.exists())
                throw new FileNotFoundException("Error: Fuente de datos \'" + file.getCanonicalPath() + "\' no existe");
            // @note Notar que es una propiedad de la super clase
            dataSource = file.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implementacion de metodo get retorna un entero convertido a Romano
     * @param number
     * @return String
     * @throws IOException
     * @throws ParseException
     */
    @Override
    public String get(Integer number) throws IOException, ParseException {
        int length;
        String roman;
        String unformatted = Integer.toString(number); // Convertir a cadena para el uso de length()

        BufferedReader reader = new BufferedReader(new FileReader(dataSource));;
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(reader); // JSON como una coleccion de objetos

        ArrayList<Integer> formatted = new ArrayList(); // Arreglo generico tipo lista

        // Cuando la cantidad de digitos del entero sea mayor a 1
        if ((length = unformatted.length()) > 1) {
            int[] numbers = new int[length];
            String tmp = "";

            // entonces, leer individualmente cada digito para asignarlo a un arreglo
            for (int i = 0; i < unformatted.length(); i++) {
                if (i == 0)
                    numbers[i] = ((number % 100) / 10) * 10; // Para el primer digito, sacamos la decena
                else
                    // @note API charAt(int index) retorna su valor en ASCII. Restamos '0' para retornar el valor entero
                    numbers[i] = unformatted.charAt(i) - '0';

                if (numbers[i] != 0)
                    // @important Excluimos 0 si es un digito unico
                    formatted.add(numbers[i]);
            }

            // Implementacion de ForEach
            for (Object f: formatted)
                tmp += object.get(Integer.toString((Integer) f));

            roman = tmp;
        }
        // Sino, simplemente retorna su simbolo Romano
        else
            roman = (String) object.get(Integer.toString(number));

        return roman;
    }
}
