package presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import modelo.Ingrediente;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;
import servicio.Controlador;

public class Ingredientes extends JFrame {
	

        /**
         * 
         */
        private static final long serialVersionUID = 7838174345100775020L;
        private JPanel contentPane;
        JTabbedPane lista_ingredientes = new JTabbedPane(JTabbedPane.LEFT);
        private Controlador control;
        private Ingrediente[] ing;

        /**
         * Launch the application.
         */
        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                try {
                                        Ingredientes frame = new Ingredientes(null);
                                        frame.setVisible(true);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
        }

        /**
         * Create the frame.
         */
        public Ingredientes(String plato) {
                
        	try {
    			control=Controlador.dameControlador();
    		} catch (DominioExcepcion e3) {
    			// TODO Auto-generated catch block
    			e3.printStackTrace();
    		}
                
                if(plato!=null){
                        try {
							ing = control.ingredientesPorPlato(plato);
							//muestra_ingredientes(ingredientes);
						} catch (DAOExcepcion e) {
							e.printStackTrace();
						}
                }
                
                setResizable(false);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setBounds(100, 100, 500, 410);
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);
                
                JLabel lblNombre = new JLabel("NOMBRE INGREDIENTE");
                lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
                
                JButton btnInfo = new JButton("Info nutricional");
                btnInfo.setEnabled(false);
                btnInfo.setFont(new Font("Arial", Font.BOLD, 14));
                
                JButton btnCompra = new JButton("A\u00F1adir lista compra");
                btnCompra.setEnabled(false);
                btnCompra.setFont(new Font("Arial", Font.BOLD, 14));
                
                JButton btnOrigen = new JButton("Origen");
                btnOrigen.setFont(new Font("Arial", Font.BOLD, 14));
                
                
                lista_ingredientes.setFont(new Font("Arial", Font.PLAIN, 14));
                GroupLayout gl_contentPane = new GroupLayout(contentPane);
                gl_contentPane.setHorizontalGroup(
                	gl_contentPane.createParallelGroup(Alignment.LEADING)
                		.addGroup(gl_contentPane.createSequentialGroup()
                			.addContainerGap()
                			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                				.addGroup(gl_contentPane.createSequentialGroup()
                					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                						.addGroup(gl_contentPane.createSequentialGroup()
                							.addComponent(btnOrigen)
                							.addPreferredGap(ComponentPlacement.RELATED, 125, Short.MAX_VALUE))
                						.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                					.addPreferredGap(ComponentPlacement.RELATED)
                					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                						.addComponent(btnInfo)
                						.addComponent(btnCompra, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
                				.addComponent(lista_ingredientes, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE))
                			.addContainerGap())
                );
                gl_contentPane.setVerticalGroup(
                	gl_contentPane.createParallelGroup(Alignment.LEADING)
                		.addGroup(gl_contentPane.createSequentialGroup()
                			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                				.addComponent(lblNombre)
                				.addComponent(btnInfo))
                			.addPreferredGap(ComponentPlacement.RELATED)
                			.addComponent(lista_ingredientes, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                			.addPreferredGap(ComponentPlacement.RELATED)
                			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                				.addComponent(btnOrigen)
                				.addComponent(btnCompra)))
                );
                contentPane.setLayout(gl_contentPane);
                
                int num_ingredientes = ing.length;
            	
            	for(int i = 0; i< num_ingredientes; i++)
            	{   
                    JLabel valor_nutricional = new JLabel();
                    valor_nutricional.setText("<html>"
                    		+ "<p><b>Calorias:</b> "+ing[i].getCalorias()+"</p>"
                    		+ "<p><b>Grasas:</b> "+ing[i].getGrasa()+"</p>"
                    		+ "<p><b>Colesterol:</b> "+ing[i].getColesterol()+"</p>"
                    		+ "<p><b>Sodio:</b> "+ing[i].getSodio()+"</p>"
                    		+ "<p><b>Carbohidratos:</b> "+ing[i].getCarbohidratos()+"</p>"
                    		+ "<p><b>Fibra:</b> "+ing[i].getFibra()+"</p>"
                    		+ "<p><b>Proteinas:</b> "+ing[i].getProteinas()+"</p>"
                    		+ "<p><b>Vitaminas:</b> "+ing[i].getVitaminas()+"</p>"
                    				+ "</html>");
                    valor_nutricional.setFont(new Font("Arial", Font.PLAIN, 20));
                    
                    lista_ingredientes.addTab(ing[i].getNombre(), null, valor_nutricional, null);
            	}
        }
}