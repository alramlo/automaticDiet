package presentacion;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.MatteBorder;
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
	private JComboBox<String> comboBoxCiudad;
	private JComboBox<String> comboBoxPais;
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
	
	private final char[] caracEspe={'!','·','$','\"','%','&','/','(',')','=','?','¿','¡'
			,'º','ª','\\','|','@','#','~','¬','€','*','+','[',']','^','{','}',';',':',','
			,'-','<','>'};

	/**
	 * Create the panel.
	 */
	public BuscadorGrupo() {
		this.setSize(800, 600);
		
		try {
			control=Controlador.dameControlador();
		} catch (DominioExcepcion e3) {
			e3.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Ciudad:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Hábitos:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblIntereses = new JLabel("Intereses:");
		lblIntereses.setFont(new Font("Arial", Font.BOLD, 16));
		
		Box boxHabitos = Box.createVerticalBox();
		boxHabitos.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		boxHabitos.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		
				
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
		
		chckbxGastronomia = new JCheckBox("GASTRONOMÍA");
		boxHabitos.add(chckbxGastronomia);
		chckbxGastronomia.setFont(new Font("Arial", Font.PLAIN, 14));
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		
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
		btnBuscar.setIcon(new ImageIcon(BuscadorGrupo.class.getResource("/iconos/buscar.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(nombreCorrecto(textFieldNombre.getText())){
					List<Caracteristica> caracteristicas = queCaracteristicas();
					List<Interes> intereses = queIntereses();
					Grupo grupo = new Grupo();
					if(!textFieldNombre.getText().equals(""))
						grupo.setNombre(textFieldNombre.getText());
					if(!(comboBoxPais.getSelectedItem()+"").equals(""))
						grupo.setPais(comboBoxPais.getSelectedItem()+"");
					if(!(comboBoxCiudad.getSelectedItem()+"").equals(""))
						grupo.setCiudad(comboBoxCiudad.getSelectedItem()+"");
					if(caracteristicas.size()!=0)
						grupo.setCaracteristicas(caracteristicas);
					if(intereses.size()!=0)
						grupo.setIntereses(intereses);
					
					Grupo[] grupoVuelta = control.getGrupos(grupo);
					if(grupoVuelta.length!=0){
						
					table_1 = new JTable(grupoVuelta.length+1,5);
					table_1.setBounds(10, 312, 780, 277);
					table_1.getColumnModel().getColumn(0).setPreferredWidth(180);
					table_1.getColumnModel().getColumn(1).setPreferredWidth(180);
					table_1.getColumnModel().getColumn(2).setPreferredWidth(180);
					table_1.getColumnModel().getColumn(3).setPreferredWidth(180);
					table_1.setBounds(10, 320, 750, 250);
					add(table_1);
					
					Object[][] o=new Object[grupoVuelta.length+1][5];
					
					o[0][0]="Privado";
					o[0][1]="Nombre";
					o[0][2]="Pais";
					o[0][3]="Ciudad";
					o[0][4]="Participantes";
					
					for(int i=1;i<=grupoVuelta.length;i++){
						o[i][0]=grupoVuelta[i-1].getPrivado();
						o[i][1]=grupoVuelta[i-1].getNombre();
						o[i][2]=grupoVuelta[i-1].getPais();
						o[i][3]=grupoVuelta[i-1].getCiudad();
						o[i][4]=control.getNumParticipantes(grupoVuelta[i-1]);
					}
					
					
					
					table_1.setModel(new DefaultTableModel(
							o,
							new String[] {
								"Privado", "Nombre", "Pais", "Ciudad", "Número participantes"
							}
						));
					
					}
					else
						JOptionPane.showMessageDialog(null, "No existe el grupo", "Error", JOptionPane.ERROR_MESSAGE);
			}else
				JOptionPane.showMessageDialog(null, "No se permiten introducir caracteres especiales", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		});
		btnBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setFont(new Font("Arial", Font.BOLD, 16));
		
		
		String[] paises= control.getPaises();
		comboBoxPais = new JComboBox<String>(paises);	
		comboBoxPais.setFont(new Font("Arial", Font.BOLD, 14));
		comboBoxPais.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBoxCiudad.removeAllItems();
				String[] ciudades = control.getCiudades(comboBoxPais.getSelectedItem()+"");
				for(int i=0;i<ciudades.length;i++){
					comboBoxCiudad.addItem(ciudades[i]);
				}
			}
		});
		
		String[] ciudades = control.getCiudades(paises[0]);
		comboBoxCiudad = new JComboBox<String>(ciudades);
		comboBoxCiudad.setFont(new Font("Arial", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(4)
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(boxHabitos, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
									.addGap(3)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(30)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPais, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBoxPais, 0, 279, Short.MAX_VALUE)
										.addComponent(comboBoxCiudad, 0, 279, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(26)
									.addComponent(lblIntereses, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(verticalBox, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)))))
					.addGap(35))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(9)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(2)
									.addComponent(comboBoxPais, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 79, Short.MAX_VALUE)
										.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblPais, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(51)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxCiudad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 22, Short.MAX_VALUE))
							.addGap(21)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(boxHabitos, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addComponent(verticalBox, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 157, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(lblIntereses, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(293))
		);
		setLayout(groupLayout);
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
	
	private boolean nombreCorrecto(String s){
		char[] c= new char[s.length()];
		for(int i=0; i<s.length();i++){
			c[i]=s.charAt(i);
			for(int j=0;j<caracEspe.length;j++){
				if(c[i]==caracEspe[j]){
					return false;
				}
			}
		}
		return true;
	}
}
