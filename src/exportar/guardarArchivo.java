package exportar;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class guardarArchivo {

    public static void guardaFicheroTXT(String salida, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(salida);
            writer.close();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
}
