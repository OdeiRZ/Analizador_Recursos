import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.util.Date;

/**
 * Clase Analizador que extiende de Thread. 
 * Se encarga de obtener el contenido de una URL introducida por el usuario
 * previamente mediante la Interfaz Ventana de la aplicación.
 *
 * @author Odei
 * @version 28.03.2016
 */
public class Analizador extends Thread {
    /**
     * Variable cadena usada para almacenar la URL introducida por el Usuario.
     */
    protected String url;

    /**
     * Constructor de la Clase Analisis.
     * Obtiene la URL enviada por el Usuario y captura los datos más 
     * reelevantes para analizarlos mediante la Interfaz Resultado.
     * 
     * @param url String: url a analizar enviada por el Usuario
     */
    public Analizador(String url) {
        this.url = url;
    }

    /**
     * Método que realiza la funcionalidad de la Aplicación.
     * Mediante un Objeto URLConnection obtenemos los datos más importantes
     * y se los enviamos a la Interfaz Resultado para mostrar su análisis.
     */
    @Override
    public void run() {
        String tip = null;                                                      // Inicializamos algunos valores para evitar problemas si surgen errores
        String fec = null;
        double tam = 0;
        
        try {
            URLConnection cnx = new URL(url).openConnection();                  // Capturamos la conexión para operar con la misma
            cnx.connect();
            tip = cnx.getContentType();                                         // Obtenems cabecera
            fec = new Date(cnx.getLastModified()).toString();                   // última fecha de modificación
            tam = cnx.getContentLength()/1024;                                  // y tamaño en Kb
        } catch (MalformedURLException e) {                                     // capturando cualquier posible error durante el proceso
            url = "URL Mal Formada";
        } catch (IOException e) {
            url = "Error de Lectura/Escritura";
        } finally {
            Resultado_Interfaz r = new Resultado_Interfaz(url, tip, tam, fec);  // y creamos una nueva Interfaz para mostrar el resultado enviando dichos valores
        }
    }
}