import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

/**
 * Clase Ventana_Interfaz que extiende de JFrame. 
 * Se encarga de generar la Interfaz Gráfica que usaremos para obtener 
 * la URL que el Usuario introduzca en la aplicación.
 *
 * @author Odei
 * @version 28.03.2016
 */
public class Ventana_Interfaz extends JFrame {
    /**
     * Variable usada para almacenar la URL del Recurso introducido.
     */
    protected JTextField tfRec;
        
    /**
     * Constructor de la Interfaz Gráfica implementada para la Ventana.
     * Genera e inicializa la Interfaz y los elementos utilizados
     * para visualizar de forma interactiva la captura de la URL a analizar.
     */
    public Ventana_Interfaz() {
        JPanel panel = new JPanel();                                            // Creamos un panel para dibujar la interfaz gráfica
        panel.setBorder(BorderFactory.createTitledBorder(null,"Escribe la URL "+
            "de un Recurso para analizarlo:",TitledBorder.DEFAULT_JUSTIFICATION, 
            TitledBorder.DEFAULT_POSITION,new Font("Tahoma",0,11),Color.BLACK));
        tfRec = new JTextField(
            "http://www.lamoncloa.gob.es/documents/constitucion_es1.pdf");      // Agregamos etiquetas, botones, y demás elementos a la Interfaz
        tfRec.setForeground(Color.BLUE);
        
        JButton btn = new JButton("Analizar");
        btn.addActionListener((ActionEvent evt) -> {
            btnActionPerformed(evt, Ventana_Interfaz.this);                     // Añadimos un escuchador para capturar la pulsación del botón
        });

        GroupLayout pl = new GroupLayout(panel);                                // Estilamos elementos para mejorar apariencia de pantalla
        panel.setLayout(pl);
        pl.setHorizontalGroup(
            pl.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, pl.createSequentialGroup()
            .addGap(0, 380, Short.MAX_VALUE)
            .addComponent(btn))
            .addGroup(pl.createSequentialGroup()
            .addContainerGap()
            .addComponent(tfRec, GroupLayout.DEFAULT_SIZE,443, Short.MAX_VALUE))
        );
        pl.setVerticalGroup(
            pl.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pl.createSequentialGroup()
            .addComponent(tfRec, GroupLayout.PREFERRED_SIZE, 
                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 
                    24, Short.MAX_VALUE)
            .addComponent(btn)
            .addContainerGap())
        );
        GroupLayout l = new GroupLayout(getContentPane());
        getContentPane().setLayout(l);
        l.setHorizontalGroup(
            l.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(l.createSequentialGroup()
            .addContainerGap()
            .addGroup(l.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, 
                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(l.createSequentialGroup()
            .addGap(0, 347, Short.MAX_VALUE)))
            .addContainerGap())
        );
        l.setVerticalGroup(
            l.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(l.createSequentialGroup()
            .addContainerGap(12, Short.MAX_VALUE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(panel,GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().createImage(
                Ventana_Interfaz.class.getResource("recursos/client.png")));    // Le ponemos una imágen de icono a la ventana
        setResizable(false);
        setTitle("Análisis de Cabecera de Recurso");
        setLocationRelativeTo(null);                                            // Centramos ventana en mitad de la pantalla
    }

    /**
     * Método usado capturar el evento producido al pulsar el Botón Analizar.
     * Cierra el frame actual y crea un nuevo hilo de la Clase Análisis.
     * 
     * @param evt ActionEvent: evento lanzado por el Usuario
     * @param frame JFrame: frame actual que cerraremos
     */
    protected final void btnActionPerformed(ActionEvent evt, JFrame frame) {  
        frame.dispose();                                                        // Cerramos ventana actual
        new Analizador(tfRec.getText()).start();                                  // Iniciamos un hilo de la Clase análisis mandándole el contenido de un TextField
    }

    /**
     * Método Principal de la Clase Ventana_Interfaz.
     * Lanza la Interfaz Gráfica para capturar la URL a analizar.
     * 
     * @param args String[]: argumentos de la línea de comandos
     */
    public static void main(String args[]) {
        try {
            for(UIManager.LookAndFeelInfo i:UIManager.getInstalledLookAndFeels()){
                if ("Nimbus".equals(i.getName())) {                             // Cambiamos el aspecto visual de los elementos de la Interfaz
                    UIManager.setLookAndFeel(i.getClassName());
                    break;
                }
            }
        } catch (IllegalAccessException | UnsupportedLookAndFeelException | 
                 ClassNotFoundException | InstantiationException ex) {}
        new Ventana_Interfaz().setVisible(true);                                // Lanzamos una nueva Ventana 
    }
}