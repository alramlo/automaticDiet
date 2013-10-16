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
import javax.swing.Box;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.ComponentOrientation;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class BuscadorGrupo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4625943961168080147L;
	private JTextField textFieldNombre;
	private JTable table;
	private JComboBox<String> comboBoxLocalidad;
	private JTable table_1;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public BuscadorGrupo(Object grupoVuelta) {
		
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
		
		JLabel lblNewLabel_1 = new JLabel("Localidad:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		
		textFieldNombre = new JTextField();
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
		
		JLabel lblNewLabel_2 = new JLabel("H\u00E1bitos:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblIntereses = new JLabel("Intereses:");
		lblIntereses.setFont(new Font("Arial", Font.BOLD, 16));
		
		JButton btnBuscar = new JButton("   Buscar");
		btnBuscar.setIcon(new ImageIcon(BuscadorGrupo.class.getResource("/iconos/buscar.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//Faltan añadir al dto grupo las caracteristicas e intereses
				//Controlador buscar grupo
			}
		});
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
		
		Box boxHabitos = Box.createVerticalBox();
		boxHabitos.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		boxHabitos.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		
		JCheckBox chckbxPrimero = new JCheckBox("Ejemplo 1");
		boxHabitos.add(chckbxPrimero);
		chckbxPrimero.setFont(new Font("Arial", Font.PLAIN, 14));
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		
		JCheckBox chckbxInteres1 = new JCheckBox("Interes 1");
		verticalBox.add(chckbxInteres1);
		chckbxInteres1.setFont(new Font("Arial", Font.PLAIN, 14));
		
		table_1 = new JTable();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(table_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(comboBoxLocalidad, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(textFieldNombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(boxHabitos, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblIntereses, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(verticalBox, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnBuscar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))))
					.addGap(82))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxLocalidad, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIntereses, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(boxHabitos, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addComponent(verticalBox, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		

	}
}
