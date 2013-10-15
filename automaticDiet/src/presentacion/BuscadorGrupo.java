package presentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import modelo.Grupo;

public class BuscadorGrupo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4625943961168080147L;
	private JTextField textFieldNombre;
	private JTable table;
	private JComboBox<String> comboBoxLocalidad;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public BuscadorGrupo(Object grupoVuelta) {
		setLayout(null);
		
		if(grupoVuelta!=null){
			if(grupoVuelta instanceof Vector){
				textFieldNombre.setText(((Vector<Grupo>) grupoVuelta).get(0).getNombre());
				comboBoxLocalidad.setSelectedItem(((Vector<Grupo>) grupoVuelta).get(0).getPoblacion());
				//Falta checkear caracteristicas e intereses
				
				table = new JTable(((Vector<Grupo>)grupoVuelta).size()+1,4);
				table.setBounds(752, 409, -708, 168);
				//table.set
				
				Object[] cabecera = {"Privado","Nombre","Población","Número Participantes"};
				Object[][] grupos = new Object[4][((Vector<Grupo>)grupoVuelta).size()];
				
				for(int i=0;i<((Vector<Grupo>)grupoVuelta).size();i++){
					grupos[0][i]=((Vector<Grupo>)grupoVuelta).get(i).getPrivado();
					grupos[1][i]=((Vector<Grupo>)grupoVuelta).get(i).getNombre();
					grupos[2][i]=((Vector<Grupo>)grupoVuelta).get(i).getPoblacion();
					grupos[3][i]=((Vector<Grupo>)grupoVuelta).get(i).getNumParticipantes();
				}
				
				
				table = new JTable(grupos,cabecera);
				table.setBounds(752, 409, -708, 168);
				add(table);
				
			}
		}
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(33, 28, 78, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Localidad:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(33, 74, 88, 14);
		add(lblNewLabel_1);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldNombre.setBounds(138, 25, 210, 20);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		comboBoxLocalidad = new JComboBox<String>();
		comboBoxLocalidad.setFont(new Font("Arial", Font.BOLD, 16));
		comboBoxLocalidad.setBounds(138, 71, 210, 20);
		add(comboBoxLocalidad);
		
		JLabel lblNewLabel_2 = new JLabel("H\u00E1bitos:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2.setBounds(33, 157, 78, 14);
		add(lblNewLabel_2);
		
		JCheckBox chckbxPrimero = new JCheckBox("Ejemplo 1");
		chckbxPrimero.setFont(new Font("Arial", Font.BOLD, 13));
		chckbxPrimero.setBounds(138, 154, 97, 23);
		add(chckbxPrimero);
		
		JLabel lblIntereses = new JLabel("Intereses:");
		lblIntereses.setFont(new Font("Arial", Font.BOLD, 16));
		lblIntereses.setBounds(385, 157, 88, 14);
		add(lblIntereses);
		
		JCheckBox chckbxInteres1 = new JCheckBox("Interes 1");
		chckbxInteres1.setFont(new Font("Arial", Font.BOLD, 13));
		chckbxInteres1.setBounds(489, 154, 97, 23);
		add(chckbxInteres1);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Grupo grupo = new Grupo();
				grupo.setNombre(textFieldNombre.getText());
				grupo.setPoblacion(comboBoxLocalidad.getSelectedItem()+"");
				//Faltan añadir al dto grupo las caracteristicas e intereses
				//Controlador buscar grupo
			}
		});
		btnBuscar.setFont(new Font("Arial", Font.BOLD, 16));
		btnBuscar.setBounds(663, 217, 89, 23);
		add(btnBuscar);
		

	}
}
