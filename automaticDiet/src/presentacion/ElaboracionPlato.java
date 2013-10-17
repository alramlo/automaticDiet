package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import modelo.Plato;
import servicio.Controlador;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;

public class ElaboracionPlato extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -35301839624147280L;
	private JTextField textFieldBuscar;
	private JComboBox<String> comboBox;
	private JTextPane textPaneElaboracion;
	private JLabel imagenPlato;

	/**
	 * Create the panel.
	 */
	public ElaboracionPlato(Object platoVuelta) {
		
		setLayout(null);
		
		JButton buttonPanelIngredientes = new JButton("Panel Ingredientes");
		buttonPanelIngredientes.setFont(new Font("Arial", Font.BOLD, 16));
		buttonPanelIngredientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Ingredientes2(comboBox.getSelectedItem()+"").setVisible(true);
			}
		});
		buttonPanelIngredientes.setBounds(366, 39, 186, 39);
		add(buttonPanelIngredientes);
		
		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(10, 110, 256, 29);
		add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Buscar plato:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 79, 256, 20);
		add(lblNewLabel);
		
		
		comboBox = new JComboBox<String>();
		
		//Extraer todos los platos del usuario
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Plato plato = new Plato();
				plato.setNombre(comboBox.getSelectedItem()+"");
				//Controlador
			}
		});
		comboBox.setBounds(366, 223, 424, 30);
		add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Plato:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(366, 198, 46, 14);
		add(lblNewLabel_1);
		
		imagenPlato = new JLabel("");
		imagenPlato.setBounds(10, 264, 346, 325);
		add(imagenPlato);
		
		textPaneElaboracion = new JTextPane();
		textPaneElaboracion.setBounds(366, 264, 424, 325);
		add(textPaneElaboracion);
		
		JLabel lblNewLabel_2 = new JLabel("AutomaticDiet");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 21));
		lblNewLabel_2.setBounds(366, 108, 162, 24);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cumple tus objetivos facilmente!");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_3.setBounds(366, 143, 424, 29);
		add(lblNewLabel_3);
		
		JButton buttonBuscar = new JButton("Buscar");
		buttonBuscar.setFont(new Font("Arial", Font.PLAIN, 14));
		buttonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Plato platoVuelta = Controlador.dameControlador().consultarPlato(textFieldBuscar.getText());
					if(platoVuelta!=null){
						textFieldBuscar.setText(platoVuelta.getNombre());
						textPaneElaboracion.setText(platoVuelta.getElaboracion());
						String[] vector = new String[1];
						vector[0]=((Plato)platoVuelta).getNombre();
						comboBox=new JComboBox<String>(vector);
						File file=null;
						try {
							file = new File("automaticDiet");
							FileOutputStream fos = new FileOutputStream (file);
							fos.write(((Plato) platoVuelta).getImagen());
							fos.close();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ImageIcon im=null;
		        		BufferedImage buffer;
						try {
							buffer = ImageIO.read(file);
							im = new ImageIcon(buffer);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		        		Image image=im.getImage();
		        		Image newImage = image.getScaledInstance(346, 325, java.awt.Image.SCALE_SMOOTH);
		        		imagenPlato.setIcon(new ImageIcon(newImage));
					}
				} catch (DAOExcepcion e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DominioExcepcion e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buttonBuscar.setBackground(Color.RED);
		buttonBuscar.setBounds(177, 148, 89, 23);
		add(buttonBuscar);

	}
}
