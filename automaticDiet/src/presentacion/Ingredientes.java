package presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Ingrediente;
import servicio.Controlador;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;

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
        	setIconImage(Toolkit.getDefaultToolkit().getImage(Ingredientes.class.getResource("/iconos/logo_icon.png")));
                
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
                
                JLabel lblNombre = new JLabel(plato);
                lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
                
                JButton btnCompra = new JButton("A\u00F1adir lista compra");
                btnCompra.setIcon(new ImageIcon(Ingredientes.class.getResource("/iconos/carrito.png")));
                btnCompra.setEnabled(false);
                btnCompra.setFont(new Font("Arial", Font.BOLD, 14));
                
                JButton btnOrigen = new JButton("  Origen");
                btnOrigen.setEnabled(false);
                btnOrigen.setHorizontalAlignment(SwingConstants.LEFT);
                btnOrigen.setIcon(new ImageIcon(Ingredientes.class.getResource("/iconos/origen.png")));
                btnOrigen.setFont(new Font("Arial", Font.PLAIN, 16));
                
                
                lista_ingredientes.setFont(new Font("Arial", Font.PLAIN, 14));
                
                JLabel lblNewLabel = new JLabel("INFO NUTRICIONAL");
                lblNewLabel.setIcon(new ImageIcon(Ingredientes.class.getResource("/iconos/icono_informacion.png")));
                lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
                GroupLayout gl_contentPane = new GroupLayout(contentPane);
                gl_contentPane.setHorizontalGroup(
                	gl_contentPane.createParallelGroup(Alignment.LEADING)
                		.addGroup(gl_contentPane.createSequentialGroup()
                			.addContainerGap()
                			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                				.addGroup(gl_contentPane.createSequentialGroup()
                					.addComponent(btnOrigen, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                					.addPreferredGap(ComponentPlacement.RELATED)
                					.addComponent(btnCompra, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                				.addComponent(lista_ingredientes, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                				.addGroup(gl_contentPane.createSequentialGroup()
                					.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                					.addGap(35)
                					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)))
                			.addContainerGap())
                );
                gl_contentPane.setVerticalGroup(
                	gl_contentPane.createParallelGroup(Alignment.LEADING)
                		.addGroup(gl_contentPane.createSequentialGroup()
                			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                				.addComponent(lblNombre)
                				.addComponent(lblNewLabel))
                			.addPreferredGap(ComponentPlacement.RELATED)
                			.addComponent(lista_ingredientes, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                			.addPreferredGap(ComponentPlacement.RELATED)
                			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                				.addComponent(btnOrigen, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                				.addComponent(btnCompra, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
                );
                contentPane.setLayout(gl_contentPane);
                
                int num_ingredientes = ing.length;
            	
            	for(int i = 0; i< num_ingredientes; i++)
            	{   
                    JLabel valor_nutricional = new JLabel();
                    valor_nutricional.setText("<html>"
                    		+ "<p><b>Calorias:</b> "+ing[i].getCalorias()+" kcal</p>"
                    		+ "<p><b>Grasas:</b> "+ing[i].getGrasa()+" g</p>"
                    		+ "<p><b>Colesterol:</b> "+ing[i].getColesterol()+" g</p>"
                    		+ "<p><b>Sodio:</b> "+ing[i].getSodio()+" g</p>"
                    		+ "<p><b>Carbohidratos:</b> "+ing[i].getCarbohidratos()+" g</p>"
                    		+ "<p><b>Fibra:</b> "+ing[i].getFibra()+" g</p>"
                    		+ "<p><b>Proteinas:</b> "+ing[i].getProteinas()+" g</p>"
                    		+ "<p><b>Vitaminas:</b> "+ing[i].getVitaminas()+"</p>"
                    				+ "</html>");
                    valor_nutricional.setFont(new Font("Arial", Font.PLAIN, 20));
                    
                    lista_ingredientes.addTab(ing[i].getNombre(), null, valor_nutricional, null);
            	}
        }
}