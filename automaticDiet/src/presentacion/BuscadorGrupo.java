package presentacion;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import modelo.Caracteristica;
import modelo.Grupo;
import servicio.Controlador;
import excepciones.DominioExcepcion;

public class BuscadorGrupo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4625943961168080147L;
	private JTextField textFieldNombre;
	private JTable table;
	private JComboBox<String> comboBoxLocalidad;
	private JTable table_1;
	private static Controlador control;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public BuscadorGrupo() {
		setSize(new Dimension(800, 600));
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 2, true), "Buscador de grupo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.setSize(800, 600);
		
		try {
			control=Controlador.dameControlador();
		} catch (DominioExcepcion e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		setLayout(null);

		
//				textFieldNombre.setText(((Vector<Grupo>) grupoVuelta).get(0).getNombre());
//				comboBoxLocalidad.setSelectedItem(((Vector<Grupo>) grupoVuelta).get(0).getPoblacion());
//				//Falta checkear caracteristicas e intereses
//				
//				table = new JTable(((Vector<Grupo>)grupoVuelta).size()+1,4);
//				table.setBounds(752, 409, -708, 168);
//				//table.set
//				
//				Object[] cabecera = {"Privado","Nombre","Población","Número Participantes"};
//				Object[][] grupos = new Object[4][((Vector<Grupo>)grupoVuelta).size()];
//				
//				for(int i=0;i<((Vector<Grupo>)grupoVuelta).size();i++){
//					grupos[0][i]=((Vector<Grupo>)grupoVuelta).get(i).getPrivado();
//					grupos[1][i]=((Vector<Grupo>)grupoVuelta).get(i).getNombre();
//					grupos[2][i]=((Vector<Grupo>)grupoVuelta).get(i).getPoblacion();
//					//grupos[3][i]=((Vector<Grupo>)grupoVuelta).get(i).getNumParticipantes();
//				}
//				
//				
//				table = new JTable(grupos,cabecera);
//				table.setBounds(752, 409, -708, 168);
//				add(table);
				
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 28, 78, 14);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Ciudad:");
		lblNewLabel_1.setBounds(391, 63, 88, 14);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(83, 25, 278, 20);
		textFieldNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textFieldNombre.getText().equals("")){
					comboBoxLocalidad.setEnabled(false);
				}
				else
					comboBoxLocalidad.setEnabled(true);
			}
		});
		textFieldNombre.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldNombre.setColumns(10);
		
		comboBoxLocalidad = new JComboBox<String>();
		comboBoxLocalidad.setBounds(469, 60, 280, 20);
		comboBoxLocalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(comboBoxLocalidad.getSelectedItem()+"").equals("")){
					textFieldNombre.setEnabled(false);
				}
				else
					textFieldNombre.setEnabled(true);
			}
		});
		comboBoxLocalidad.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblNewLabel_2 = new JLabel("Hábitos:");
		lblNewLabel_2.setBounds(10, 95, 78, 14);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblIntereses = new JLabel("Intereses:");
		lblIntereses.setBounds(391, 95, 88, 14);
		lblIntereses.setFont(new Font("Arial", Font.BOLD, 16));
		
		JButton btnBuscar = new JButton("   Buscar");
		btnBuscar.setBounds(589, 266, 160, 35);
		btnBuscar.setIcon(new ImageIcon(BuscadorGrupo.class.getResource("/iconos/buscar.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();
				
			}
		});
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
		
		Box boxHabitos = Box.createVerticalBox();
		boxHabitos.setBounds(83, 105, 278, 150);
		boxHabitos.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		boxHabitos.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		
		//obtener todas las caracteristicas
		Caracteristica[] caracteristica=control.getCaracteristicas();
		
		for(int i=0;i<caracteristica.length;i++){
		
			JCheckBox chckbxPrimero = new JCheckBox(caracteristica[i].getNombre());
			boxHabitos.add(chckbxPrimero);
			chckbxPrimero.setFont(new Font("Arial", Font.PLAIN, 14));
		}
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(469, 105, 280, 150);
		verticalBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		
		//obtener todos los intereses
		
		JCheckBox chckbxInteres1 = new JCheckBox("Interes 1");
		verticalBox.add(chckbxInteres1);
		chckbxInteres1.setFont(new Font("Arial", Font.PLAIN, 14));
		
		table_1 = new JTable();
		table_1.setBounds(10, 312, 780, 277);
		add(table_1);
		add(lblNewLabel);
		add(lblNewLabel_2);
		add(lblNewLabel_1);
		add(textFieldNombre);
		add(btnBuscar);
		add(comboBoxLocalidad);
		add(boxHabitos);
		add(lblIntereses);
		add(verticalBox);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setFont(new Font("Arial", Font.BOLD, 16));
		lblPais.setBounds(391, 28, 46, 14);
		add(lblPais);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(469, 27, 280, 20);
		add(comboBox);
		

	}
}
