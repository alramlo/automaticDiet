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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import modelo.Dieta;
import modelo.Plato;
import modelo.Usuario;
import servicio.Controlador;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;

public class ElaboracionPlato extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -35301839624147280L;
	private JComboBox<String> comboBoxPlatos;
	private JLabel imagenPlato;
	private JTextArea textAreaElaboracion;
	private static Controlador control;
	private JComboBox<String> comboBoxDietas;
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
		buttonPanelIngredientes.setBounds(510, 27, 209, 36);
		buttonPanelIngredientes.setMinimumSize(new Dimension(123, 36));
		buttonPanelIngredientes.setIcon(new ImageIcon(ElaboracionPlato.class.getResource("/iconos/ingredients-icon.png")));
		buttonPanelIngredientes.setFont(new Font("Arial", Font.BOLD, 16));
		buttonPanelIngredientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Ingredientes(comboBoxPlatos.getSelectedItem()+"").setVisible(true);
			}
		});
		
		textAreaElaboracion = new JTextArea();
		textAreaElaboracion.setBounds(389, 234, 330, 329);
		textAreaElaboracion.setFont(new Font("Arial", Font.PLAIN, 18));
		textAreaElaboracion.setAutoscrolls(false);
		textAreaElaboracion.setBackground(UIManager.getColor("Button.background"));
		textAreaElaboracion.setEditable(false);
		textAreaElaboracion.setLineWrap(true);
		textAreaElaboracion.setWrapStyleWord(true);
		textAreaElaboracion.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textAreaElaboracion.setText("  Descripci\u00F3n de la elaboraci\u00F3n de los platos");
		
		imagenPlato = new JLabel("");
		imagenPlato.setBounds(34, 234, 325, 329);
		imagenPlato.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		
		Usuario u = new Usuario();
		u.setId(1);
		String[] nomDietas = control.getDietas(u);
		comboBoxDietas = new JComboBox<String>(nomDietas);
		comboBoxDietas.setBounds(29, 136, 330, 36);
		comboBoxDietas.setSelectedIndex(1);
		
		comboBoxDietas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evento) {
				if ( evento.getStateChange() == ItemEvent.SELECTED )
				{
					Dieta dieta = control.getDietaPorNombre(comboBoxDietas.getSelectedItem()+"");
					String[] nomPlatos = control.getPlatosDieta(dieta.getId());
					for(int i=0;i<nomPlatos.length;i++)
						comboBoxPlatos.addItem(nomPlatos[i]);
				}
			}
		});
		
		comboBoxPlatos = new JComboBox<String>(/*control.todosPlatos()*/);
		comboBoxPlatos.setBounds(389, 136, 330, 36);
//		comboBox = new JComboBox<String>();
		comboBoxPlatos.setSelectedIndex(1);
		comboBoxPlatos.setEnabled(false);
		
		estadoInicial();
		
		comboBoxPlatos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evento) {
				// determina si está seleccionada el estado correspondiente
				if ( evento.getStateChange() == ItemEvent.SELECTED )
				{
				
						try {
							platoVuelta = control.consultarPlato(comboBoxPlatos.getSelectedItem().toString());
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
		add(comboBoxPlatos);
		
		JLabel lblNewLabel = new JLabel("Receta elaborada por AutomaticDiet.\r\n Todos los derechos reservados.");
		lblNewLabel.setBounds(416, 573, 0, 18);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel lblNewLabel_1 = new JLabel("RECETA:");
		lblNewLabel_1.setBounds(389, 204, 73, 19);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblPlatos = new JLabel("PLATOS:");
		lblPlatos.setBounds(389, 93, 73, 19);
		lblPlatos.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblDietas = new JLabel("DIETAS:");
		lblDietas.setBounds(29, 93, 73, 19);
		lblDietas.setFont(new Font("Arial", Font.BOLD, 16));
		setLayout(null);
		add(comboBoxPlatos);
		add(lblNewLabel_1);
		add(lblNewLabel);
		add(imagenPlato);
		add(lblDietas);
		add(comboBoxDietas);
		add(textAreaElaboracion);
		add(lblPlatos);
		add(buttonPanelIngredientes);
		
	}
	
	private void estadoInicial(){
		try {
			platoVuelta = control.consultarPlato(comboBoxPlatos.getItemAt(1).toString());
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
