package presentacion;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Caracteristica;
import modelo.Grupo;
import modelo.Interes;
import servicio.Controlador;
import excepciones.DominioExcepcion;

public class BuscadorGrupo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4625943961168080147L;
	private JTextField textFieldNombre;
	private JComboBox<String> comboBoxLocalidad;
	private JTable table_1;
	private static Controlador control;
	private JCheckBox chckbxBasquet;
	private JCheckBox chckbxCorrer;
	private JCheckBox chckbxEstudios;
	private JCheckBox chckbxFutbol;
	private JCheckBox chckbxGastronomia;
	private JCheckBox chckbxBajar;
	private JCheckBox chckbxConocer;
	private JCheckBox chckbxDefinir;
	private JCheckBox chckbxMuscular; 
	private JCheckBox chckbxSubir;

	/**
	 * Create the panel.
	 */
	public BuscadorGrupo() {
		setSize(new Dimension(800, 600));
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 2, true), "Buscador de grupo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.setSize(800, 600);
		
		try {
			control=Controlador.dameControlador();
		} catch (DominioExcepcion e3) {
			e3.printStackTrace();
		}
		
		setLayout(null);		
		
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
		
		JLabel lblNewLabel_2 = new JLabel("H�bitos:");
		lblNewLabel_2.setBounds(10, 95, 78, 14);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblIntereses = new JLabel("Intereses:");
		lblIntereses.setBounds(391, 95, 88, 14);
		lblIntereses.setFont(new Font("Arial", Font.BOLD, 16));
		
		Box boxHabitos = Box.createVerticalBox();
		boxHabitos.setBounds(83, 105, 278, 150);
		boxHabitos.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		boxHabitos.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		
		//obtener todas las caracteristicas
				//Caracteristica[] caracteristica=control.getCaracteristicas();
				
				//for(int i=0;i<caracteristica.length;i++){
				
					chckbxBasquet = new JCheckBox("BALONCESTO");
					boxHabitos.add(chckbxBasquet);
					chckbxBasquet.setFont(new Font("Arial", Font.PLAIN, 14));
					
					chckbxCorrer = new JCheckBox("CORRER");
					boxHabitos.add(chckbxCorrer);
					chckbxCorrer.setFont(new Font("Arial", Font.PLAIN, 14));
					
					chckbxEstudios = new JCheckBox("ESTUDIOS");
					boxHabitos.add(chckbxEstudios);
					chckbxEstudios.setFont(new Font("Arial", Font.PLAIN, 14));
					
					chckbxFutbol = new JCheckBox("FUTBOL");
					boxHabitos.add(chckbxFutbol);
					chckbxFutbol.setFont(new Font("Arial", Font.PLAIN, 14));
					
					chckbxGastronomia = new JCheckBox("GASTRONOM�A");
					boxHabitos.add(chckbxGastronomia);
					chckbxGastronomia.setFont(new Font("Arial", Font.PLAIN, 14));
				//}
					
					Box verticalBox = Box.createVerticalBox();
					verticalBox.setBounds(469, 105, 280, 150);
					verticalBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
					
					//obtener todos los intereses
//					Interes[] interes = control.getIntereses();
//					
//					for(int i=0; i<interes.length;i++){
//					
//						JCheckBox chckbxInteres1 = new JCheckBox(interes[i].getNombre());
//						verticalBox.add(chckbxInteres1);
//						chckbxInteres1.setFont(new Font("Arial", Font.PLAIN, 14));
//					}
					
					chckbxBajar = new JCheckBox("BAJAR PESO");
					verticalBox.add(chckbxBajar);
					chckbxBajar.setFont(new Font("Arial", Font.PLAIN, 14));

					chckbxConocer = new JCheckBox("CONOCER GENTE");
					verticalBox.add(chckbxConocer);
					chckbxConocer.setFont(new Font("Arial", Font.PLAIN, 14));
						
					chckbxDefinir = new JCheckBox("DEFINIR");
					verticalBox.add(chckbxDefinir);
					chckbxDefinir.setFont(new Font("Arial", Font.PLAIN, 14));
					
					chckbxMuscular = new JCheckBox("MUSCULAR");
					verticalBox.add(chckbxMuscular);
					chckbxMuscular.setFont(new Font("Arial", Font.PLAIN, 14));
					
					chckbxSubir = new JCheckBox("SUBIR PESO");
					verticalBox.add(chckbxSubir);
					chckbxSubir.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JButton btnBuscar = new JButton("   Buscar");
		btnBuscar.setBounds(589, 266, 160, 35);
		btnBuscar.setIcon(new ImageIcon(BuscadorGrupo.class.getResource("/iconos/buscar.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Caracteristica> caracteristicas = queCaracteristicas();
				List<Interes> intereses = queIntereses();
				Grupo grupo = new Grupo();
				if(!textFieldNombre.getText().equals(""))
					grupo.setNombre(textFieldNombre.getText());
				if(caracteristicas.size()!=0)
					grupo.setCaracteristicas(caracteristicas);
				//a�adir al dto al atributo List<Interes>
				Grupo[] grupoVuelta = control.getGrupos(grupo);
				if(grupoVuelta.length!=0){
					
				table_1 = new JTable(grupoVuelta.length+10,4);
				table_1.setBounds(10, 312, 780, 277);
				table_1.getColumnModel().getColumn(0).setPreferredWidth(180);
				table_1.getColumnModel().getColumn(1).setPreferredWidth(180);
				table_1.getColumnModel().getColumn(2).setPreferredWidth(180);
				table_1.getColumnModel().getColumn(3).setPreferredWidth(180);
				add(table_1);
				
				table_1.setModel(new DefaultTableModel(
						new Object[][] {
							{"Privado", "Nombre", "Poblaci�n", "N�mero participantes"},
							{null, null, null, null, null, null, null},
						},
						new String[] {
							"Privado", "Nombre", "Poblaci�n", "N�mero participantes"
						}
					));
				
				for(int i=0;i<grupoVuelta.length;i++){
					table_1.setValueAt(grupoVuelta[i].getPrivado(), i+1, 0);
					table_1.setValueAt(grupoVuelta[i].getNombre(), i+1, 1);
					table_1.setValueAt(grupoVuelta[i].getPoblacion(), i+1, 2);
					table_1.setValueAt("Prueba", i+1, 3);
				}
				}
				else
					JOptionPane.showMessageDialog(null, "No existe el grupo", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		});
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
		
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
	
	private List<Caracteristica> queCaracteristicas(){
		List<Caracteristica> lista = new ArrayList<Caracteristica>();
		if(chckbxBasquet.isSelected()){
			Caracteristica basket = new Caracteristica();
			basket.setNombre("BALONCESTO");
			lista.add(basket);
		}
		if(chckbxCorrer.isSelected()){
			Caracteristica correr = new Caracteristica();
			correr.setNombre("CORRER");
			lista.add(correr);
		}
		if(chckbxEstudios.isSelected()){
			Caracteristica estudios = new Caracteristica();
			estudios.setNombre("ESTUDIOS");
			lista.add(estudios);
		}
		if(chckbxFutbol.isSelected()){
			Caracteristica futbol = new Caracteristica();
			futbol.setNombre("FUTBOL");
			lista.add(futbol);
		}
		if(chckbxGastronomia.isSelected()){
			Caracteristica gastronomia = new Caracteristica();
			gastronomia.setNombre("GASTRONOMIA");
			lista.add(gastronomia);
		}
		return lista;
	}
	
	private List<Interes> queIntereses(){
		List<Interes> lista = new ArrayList<Interes>();
		if(chckbxBajar.isSelected()){
			Interes bajar = new Interes();
			bajar.setNombre("BAJAR PESO");
			lista.add(bajar);
		}
		if(chckbxConocer.isSelected()){
			Interes conocer = new Interes();
			conocer.setNombre("CONOCER GENTE");
			lista.add(conocer);
		}
		if(chckbxDefinir.isSelected()){
			Interes definir = new Interes();
			definir.setNombre("DEFINIR");
			lista.add(definir);
		}
		if(chckbxMuscular.isSelected()){
			Interes muscular = new Interes();
			muscular.setNombre("MUSCULAR");
			lista.add(muscular);
		}
		if(chckbxSubir.isSelected()){
			Interes subir = new Interes();
			subir.setNombre("SUBIR PESO");
			lista.add(subir);
		}
		return lista;
	}
}
