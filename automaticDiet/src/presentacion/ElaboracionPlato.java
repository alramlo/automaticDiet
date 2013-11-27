package presentacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import modelo.Plato;
import servicio.Controlador;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;

public class ElaboracionPlato extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -35301839624147280L;
	private JComboBox<String> comboBox;
	private JLabel imagenPlato;
	private JTextArea textAreaElaboracion;
	private static Controlador control;
	Plato platoVuelta;

	/**
	 * Create the panel.
	 */
	public ElaboracionPlato() {
		this.setSize(754, 598);
		
		try {
			control=Controlador.dameControlador();
			platoVuelta = null;
		} catch (DominioExcepcion e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		JButton buttonPanelIngredientes = new JButton("Panel Ingredientes");
		buttonPanelIngredientes.setMinimumSize(new Dimension(123, 36));
		buttonPanelIngredientes.setIcon(new ImageIcon(ElaboracionPlato.class.getResource("/iconos/ingredients-icon.png")));
		buttonPanelIngredientes.setFont(new Font("Arial", Font.BOLD, 16));
		buttonPanelIngredientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Ingredientes(comboBox.getSelectedItem()+"").setVisible(true);
			}
		});
		
		textAreaElaboracion = new JTextArea();
		textAreaElaboracion.setFont(new Font("Arial", Font.PLAIN, 18));
		textAreaElaboracion.setAutoscrolls(false);
		textAreaElaboracion.setBackground(UIManager.getColor("Button.background"));
		textAreaElaboracion.setEditable(false);
		textAreaElaboracion.setLineWrap(true);
		textAreaElaboracion.setWrapStyleWord(true);
		textAreaElaboracion.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textAreaElaboracion.setText("  Descripci\u00F3n de la elaboraci\u00F3n de los platos");
		
		imagenPlato = new JLabel("");
		imagenPlato.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		comboBox = new JComboBox<String>(control.todosPlatos());
//		comboBox = new JComboBox<String>();
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(414, 145, 330, 36);
		
		estadoInicial();
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evento) {
				// determina si está seleccionada el estado correspondiente
				if ( evento.getStateChange() == ItemEvent.SELECTED )
				{
				
						try {
							platoVuelta = control.consultarPlato(comboBox.getSelectedItem().toString());
						if(platoVuelta!=null){
							textAreaElaboracion.setText(platoVuelta.getElaboracion());
							String[] vector = new String[1];
							vector[0]=((Plato)platoVuelta).getNombre();
//							comboBox=new JComboBox<String>(vector);
							File file=null;
							try {
								file = new File("automaticDiet");
								FileOutputStream fos = new FileOutputStream (file);
								fos.write(((Plato) platoVuelta).getImagen());
								fos.close();
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}catch(java.lang.NullPointerException e3){
								e3.printStackTrace();
							
							}catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ImageIcon im=null;
			        		BufferedImage buffer;
			        		int width=0,height=0;
							try {
								buffer = ImageIO.read(file);
								im = new ImageIcon(buffer);
								width=buffer.getWidth();
								height=buffer.getHeight();
								
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			        		Image image=im.getImage();
			        		Image newImage;
			        		if(width>366 || height> 373){
			        			imagenPlato.setSize(3*width/4, 3*height/4);
			        			newImage = image.getScaledInstance(3*width/4, 3*height/4, java.awt.Image.SCALE_SMOOTH);
			        		}else{
			        			imagenPlato.setSize(width, height);
			        			newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
			        		}
			        		imagenPlato.setIcon(new ImageIcon(newImage));
						}
						} catch (DAOExcepcion e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
				}
				
			}
		});
		add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("AutomaticDiet");
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 48));
		
		JLabel lblNewLabel_3 = new JLabel("\u00A1Cumple tus objetivos facilmente!");
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lblNewLabel = new JLabel("Receta elaborada por AutomaticDiet.\r\n Todos los derechos reservados.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(15)
							.addComponent(imagenPlato, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel, 0, 0, Short.MAX_VALUE)
								.addComponent(textAreaElaboracion, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
							.addGap(19))
						.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE))
					.addGap(4))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(99)
					.addComponent(buttonPanelIngredientes, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(492, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(68)
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(buttonPanelIngredientes, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(imagenPlato, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textAreaElaboracion, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
	
	private void estadoInicial(){
		try {
			platoVuelta = control.consultarPlato(comboBox.getItemAt(1).toString());
		if(platoVuelta!=null){
			textAreaElaboracion.setText(platoVuelta.getElaboracion());
			String[] vector = new String[1];
			vector[0]=((Plato)platoVuelta).getNombre();
//			comboBox=new JComboBox<String>(vector);
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
    		int width=0,height=0;
			try {
				buffer = ImageIO.read(file);
				im = new ImageIcon(buffer);
				width=buffer.getWidth();
				height=buffer.getHeight();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		Image image=im.getImage();
    		Image newImage;
    		if(width>366 || height> 373){
    			newImage = image.getScaledInstance(3*width/4, 3*height/4, java.awt.Image.SCALE_SMOOTH);
    			//imagenPlato.setBorder(null);
    		}else{
    			newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
    		}
    		imagenPlato.setIcon(new ImageIcon(newImage));
		}
		} catch (DAOExcepcion e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
}
