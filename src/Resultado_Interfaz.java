import java.awt.Color;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Clase Resultado_Interfaz que extiende de JFrame. 
 * Se encarga de generar la Interfaz Gráfica que usaremos para analizar los
 * resultados de la URL introducida en la aplicación.
 *
 * @author Odei
 * @version 28.03.2016
 */
public class Resultado_Interfaz extends JFrame {
    /**
     * Constructor de la Interfaz Gráfica implementada para el Resultado.
     * Genera e inicializa la Interfaz y los elementos utilizados
     * para visualizar de forma interactiva los resultados de la URL analizada.
     * 
     * @param url String: URL en forma de cadena introducida por el Usuario
     * @param tip String: cabecera en forma de cadena del Recurso analizado
     * @param tam Double: tamaño en Kb del Recurso analizado
     * @param fec String: fecha de última modificación del Recurso analizado
     */
    public Resultado_Interfaz(String url, String tip, Double tam, String fec) {
        JPanel panel = new JPanel(null);                                        // Creamos un panel para dibujar la interfaz gráfica
        JLabel lb1 = new JLabel("Recurso:");                                    // Agregamos etiquetas, botones, y demás elementos a la Interfaz
        JLabel lbRecurso = new JLabel(url);
        JLabel lb2 = new JLabel("Fecha Últ. Modifi.:");
        JLabel lb3 = new JLabel("Tamaño Recurso:");
        TextField tfFec = new TextField();
        TextField tfTam = new TextField();
        
        ButtonGroup bgTipo = new ButtonGroup();                                 // Agregamos grupo para agrupar los JRadioButton
        JRadioButton rbAud = new JRadioButton("Audio");
        JRadioButton rbImg = new JRadioButton("Imagen");
        JRadioButton rbVid = new JRadioButton("Vídeo");                         // Añadimos elementos JRadioButton
        JRadioButton rbPdf = new JRadioButton("Pdf");
        JRadioButton rbWeb = new JRadioButton("Web");
        JRadioButton rbOtr = new JRadioButton("Otros");
        JButton btnVolver = new JButton("Volver");
        
        bgTipo.add(rbAud);
        bgTipo.add(rbImg);                                                      // Agregamos JRadioButton a sus grupos correspondientes
        bgTipo.add(rbVid);
        bgTipo.add(rbPdf);
        bgTipo.add(rbWeb);
        bgTipo.add(rbOtr);
        
        panel.add(lb1).setBounds(35, 15, 60, 20);
        panel.add(lbRecurso).setBounds(95, 15, 380, 20);                        // Agregamos y posicionamos elementos en el panel
        panel.add(lb2).setBounds(35, 65, 100, 20);
        panel.add(lb3).setBounds(35, 95, 100, 20);
        panel.add(tfFec).setBounds(150, 65, 200, 20);
        panel.add(tfTam).setBounds(150, 95, 200, 20);
        panel.add(rbAud).setBounds(380, 60, 80, 20);                                 
        panel.add(rbImg).setBounds(380, 85, 80, 20);
        panel.add(rbVid).setBounds(380, 110, 80, 20);
        panel.add(rbPdf).setBounds(380, 135, 80, 20);
        panel.add(rbWeb).setBounds(380, 160, 80, 20);
        panel.add(rbOtr).setBounds(380, 185, 80, 20);
        panel.add(btnVolver).setBounds(35, 130, 315, 70);  
        
        if (tip != null) {
            if (tip.startsWith("audio")) {                                      // Dependiendo del tipo de cabecera 
                rbAud.setSelected(true);                                        // marcamos un tipo de recurso concreto
            } else if (tip.startsWith("image")) {
                rbImg.setSelected(true);
            } else if (tip.startsWith("video")) {
                rbVid.setSelected(true);
            } else if (tip.startsWith("application/pdf")) {
                rbPdf.setSelected(true);
            } else if (tip.startsWith("text/html")) {
                rbWeb.setSelected(true);
            } else {
                rbOtr.setSelected(true);
            } 
            tfFec.setText(fec);
            tfTam.setText(tam + " Kb");
            lbRecurso.setForeground(Color.blue);
        } else {
            lbRecurso.setForeground(Color.red);
        }
                    
        JFrame frame = new JFrame("Análisis de Cabecera de Recurso");           // Creamos JFrame y le ponemos título
        frame.add(panel);                                                       // agregando el panel previamente creado
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().createImage(
                Resultado_Interfaz.class.getResource("recursos/client.png")));  // Le ponemos una imágen de icono a la ventana
        frame.setSize(500, 260);                                                // y le asignamos tamaño y demás parámetros
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        btnVolver.addActionListener((ActionEvent evt) -> {
            btnVolverActionPerformed(evt, frame);                               // Añadimos un escuchador para capturar la pulsación del botón volver
        });
    }
    
    /**
     * Método usado capturar el evento producido al pulsar el Botón Volver.
     * Cierra el frame actual y crea una nueva instancia de la Ventana inicial.
     * 
     * @param evt ActionEvent: evento lanzado por el Usuario
     * @param frame JFrame: frame actual que cerraremos
     */
    private void btnVolverActionPerformed(ActionEvent evt, JFrame frame) {
        frame.dispose();                                                        // Cerramos ventana actual
        new Ventana_Interfaz().setVisible(true);                                // Iniciamos una nueva instancia la interfaz de la ventana inicial
    }
}