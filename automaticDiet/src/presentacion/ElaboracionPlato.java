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
import javax.swing.JOptionPane;
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

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
	private JButton buttonPanelIngredientes;
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
		
		buttonPanelIngredientes = new JButton("Panel Ingredientes");
		buttonPanelIngredientes.setMinimumSize(new Dimension(123, 36));
		buttonPanelIngredientes.setIcon(new ImageIcon(ElaboracionPlato.class.getResource("/iconos/ingredients-icon.png")));
		buttonPanelIngredientes.setFont(new Font("Arial", Font.BOLD, 16));
		buttonPanelIngredientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Ingredientes(comboBoxPlatos.getSelectedItem()+"").setVisible(true);
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
		
		
		Usuario u = new Usuario();
		u.setId(1);
		String[] nomDietas = control.getDietas(u);
		comboBoxDietas = new JComboBox<String>(nomDietas);
		comboBoxDietas.setSelectedIndex(nomDietas.length-1);
		
		comboBoxDietas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evento) {
				if ( evento.getStateChange() == ItemEvent.SELECTED )
				{
					Dieta dieta = control.getDietaPorNombre(comboBoxDietas.getSelectedItem()+"");
					String[] nomPlatos = control.getPlatosDieta(dieta.getId());
					if(nomPlatos.length!=0){
						comboBoxPlatos.setEnabled(true);
						imagenPlato.setVisible(true);
						textAreaElaboracion.setVisible(true);
						buttonPanelIngredientes.setVisible(true);
						for(int i=0;i<nomPlatos.length;i++)
							comboBoxPlatos.addItem(nomPlatos[i]);
					}
					else{
						JOptionPane.showMessageDialog(null, "Esta dieta no contiene platos asociados", "Info", JOptionPane.INFORMATION_MESSAGE);
						comboBoxPlatos.setEnabled(false);
						imagenPlato.setVisible(false);
						textAreaElaboracion.setVisible(false);
						buttonPanelIngredientes.setVisible(false);
					}
				}
			}
		});
		
		comboBoxPlatos = new JComboBox<String>(/*control.todosPlatos()*/);
//		comboBox = new JComboBox<String>();
		//comboBoxPlatos.setSelectedIndex(1);
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
							textAreaElaboracion.setVisible(true);
							imagenPlato.setVisible(true);
							buttonPanelIngredientes.setVisible(true);
							textAreaElaboracion.setText(platoVuelta.getElaboracion());
							comboBoxPlatos.setEnabled(true);
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
			        		System.out.println("w  "+width);
			        		System.out.println("h  "+height);
			        		if(width>390 || height> 330){
			        			imagenPlato.setSize(3*width/5, 3*height/5);
			        			newImage = image.getScaledInstance(3*width/4, 3*height/4, java.awt.Image.SCALE_SMOOTH);
			        		}else{
			        			imagenPlato.setSize(width, height);
			        			newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
			        		}
			        		imagenPlato.setIcon(new ImageIcon(newImage));
						}
						else{
							JOptionPane.showMessageDialog(null, "Esta dieta no contiene platos asociados", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
						} catch (DAOExcepcion e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
				}
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Receta elaborada por AutomaticDiet.\r\n Todos los derechos reservados.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel lblNewLabel_1 = new JLabel("RECETA:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblPlatos = new JLabel("PLATOS:");
		lblPlatos.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblDietas = new JLabel("DIETAS:");
		lblDietas.setFont(new Font("Arial", Font.BOLD, 16));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(lblDietas, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(292)
					.addComponent(lblPlatos, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(399)
					.addComponent(lblNewLabel_1))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(buttonPanelIngredientes, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(416)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addComponent(imagenPlato, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
						.addGap(40)
						.addComponent(textAreaElaboracion))
					.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addGap(29)
						.addComponent(comboBoxDietas, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
						.addGap(40)
						.addComponent(comboBoxPlatos, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDietas)
						.addComponent(lblPlatos))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBoxDietas, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxPlatos, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(lblNewLabel_1)
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(imagenPlato, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
							.addGap(39)
							.addComponent(buttonPanelIngredientes, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addComponent(textAreaElaboracion, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);
		
	}
	
	private void estadoInicial(){
		Dieta dieta=null;
		try {
			if(comboBoxDietas.getModel().getSize()>0){
				dieta = control.getDietaPorNombre(comboBoxDietas.getSelectedItem()+"");
				comboBoxPlatos.setEnabled(true); 
				String[] nomPlatos = control.getPlatosDieta(dieta.getId());
				for(int i=0;i<nomPlatos.length;i++)
					comboBoxPlatos.addItem(nomPlatos[i]);
				if(comboBoxPlatos.getModel().getSize()!=0){	
					platoVuelta = control.consultarPlato(comboBoxPlatos.getItemAt(1).toString());
					if(platoVuelta!=null){
						textAreaElaboracion.setText(platoVuelta.getElaboracion());
						String[] vector = new String[1];
						vector[0]=((Plato)platoVuelta).getNombre();
	//					comboBox=new JComboBox<String>(vector);
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
		    		if(width>320 || height> 230){
		    			newImage = image.getScaledInstance(3*width/4, 3*height/4, java.awt.Image.SCALE_SMOOTH);
		    			//imagenPlato.setBorder(null);
		    		}else{
		    			newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		    		}
		    		imagenPlato.setIcon(new ImageIcon(newImage));
					}
				}
				else{
					//NO HAY PLATOS
					JOptionPane.showMessageDialog(null, "Esta dieta no contiene platos asociados", "Info", JOptionPane.INFORMATION_MESSAGE);
					comboBoxPlatos.setEnabled(false);
					imagenPlato.setVisible(false);
					textAreaElaboracion.setVisible(false);
					buttonPanelIngredientes.setVisible(false);
				}
			}
			} catch (DAOExcepcion e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		 
	}
}
