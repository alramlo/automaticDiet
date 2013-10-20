package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
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

import modelo.Plato;
import servicio.Controlador;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;

import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ElaboracionPlato extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -35301839624147280L;
	private JComboBox<String> comboBox;
	private JLabel imagenPlato;
	private static Controlador control;
	private TextArea textAreaElaboracion;

	/**
	 * Create the panel.
	 */
	public ElaboracionPlato() {
		setSize(new Dimension(800, 600));
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Elaboración de plato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.setSize(800, 600);
		
		try {
			control=Controlador.dameControlador();
		} catch (DominioExcepcion e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		setLayout(null);
		
		JButton buttonPanelIngredientes = new JButton("Panel Ingredientes");
		buttonPanelIngredientes.setIcon(new ImageIcon(ElaboracionPlato.class.getResource("/iconos/ingredients-icon.png")));
		buttonPanelIngredientes.setFont(new Font("Arial", Font.BOLD, 16));
		buttonPanelIngredientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Ingredientes(comboBox.getSelectedItem()+"").setVisible(true);
			}
		});
		buttonPanelIngredientes.setBounds(110, 143, 209, 36);
		add(buttonPanelIngredientes);
		
		comboBox = new JComboBox<String>(control.todosPlatos());	
		comboBox.setBounds(425, 145, 365, 36);
		add(comboBox);
//		comboBox = new JComboBox<String>();	
		//Extraer todos los platos del usuario
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Plato platoVuelta;
				//System.out.println(comboBox.getSelectedItem().toString());
				try {
					platoVuelta = control.consultarPlato(comboBox.getSelectedItem()+"");
				if(platoVuelta!=null){
					textAreaElaboracion.setText(platoVuelta.getElaboracion());
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
				} catch (DAOExcepcion e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		imagenPlato = new JLabel("");
		imagenPlato.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		imagenPlato.setBounds(10, 186, 409, 402);
		add(imagenPlato);
		
		JLabel lblNewLabel_2 = new JLabel("AutomaticDiet");
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 48));
		lblNewLabel_2.setBounds(10, 11, 780, 75);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u00A1Cumple tus objetivos facilmente!");
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_3.setBounds(10, 79, 780, 60);
		add(lblNewLabel_3);
		
		textAreaElaboracion = new TextArea();
		textAreaElaboracion.setEditable(false);
		textAreaElaboracion.setBounds(425, 186, 365, 402);
		add(textAreaElaboracion);
		
	}
}
