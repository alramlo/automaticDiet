package presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Plato;
import modelo.Usuario;
import servicio.Controlador;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;

public class GestionPlatos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8334806379840766024L;
	private JTextField textFieldPlato;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTable table;
	private Controlador control;

	/**
	 * Create the panel.
	 */
	public GestionPlatos() {
		
		setSize(new Dimension(800, 600));
		this.setSize(800, 600);
		
		try {
			control=Controlador.dameControlador();
		} catch (DominioExcepcion e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		add(table);
		
		JButton btnAadir = new JButton("A\u00D1ADIR");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAadir.setIcon(new ImageIcon(GestionPlatos.class.getResource("/iconos/edit_add.png")));
		btnAadir.setFont(new Font("Arial", Font.BOLD, 16));
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setIcon(new ImageIcon(GestionPlatos.class.getResource("/iconos/edit.png")));
		btnModificar.setFont(new Font("Arial", Font.BOLD, 16));
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setIcon(new ImageIcon(GestionPlatos.class.getResource("/iconos/eliminar-icono-4912-32.png")));
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 16));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(table, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(80)
							.addComponent(btnAadir, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
							.addGap(84)
							.addComponent(btnModificar)
							.addGap(85)
							.addComponent(btnEliminar))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAadir, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JLabel lblNewLabel = new JLabel("Plato:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		textFieldPlato = new JTextField();
		textFieldPlato.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Autor:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Apellidos:");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldApellidos = new JTextField();
		textFieldApellidos.setColumns(10);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setIcon(new ImageIcon(GestionPlatos.class.getResource("/iconos/buscar.png")));
		btnBuscar.setFont(new Font("Arial", Font.BOLD, 16));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//FILTRO NOMBRE PLATO
				if(!textFieldPlato.getText().equals("") && textFieldNombre.getText().equals("") && textFieldApellidos.getText().equals("")){
					try {
						Plato plato = control.consultarPlato(textFieldPlato.getText());
						if(plato!=null){
							Object[][] o  = new Object[2][5];
							o[0][0]="Plato";
							o[0][1]="Autor";
							o[0][2]="Calorias";
							o[0][3]="Precio";
							o[0][4]="Valoración";
							o[1][0]=plato.getNombre();
							o[1][1]=plato.getNombre();
							o[1][2]=plato.getNombre();
							o[1][3]=plato.getNombre();
							o[1][4]=plato.getNombre();
							table.setModel(new DefaultTableModel(
							o,
							new String[] {
								"Plato", "Autor", "Calorias", "Precio", "Valoración"
							}
						));
						
						}
						else
							JOptionPane.showMessageDialog(null, "No existe el plato", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (DAOExcepcion e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}//FIN BUSCAR POR NOMBRE PLATO
				//BUSCAR POR AUTOR COMPLETO
				else if(textFieldPlato.getText().equals("") && !textFieldNombre.getText().equals("") && !textFieldApellidos.getText().equals("")){
					Usuario autor = control.getIdUsuario(textFieldNombre.getText(), textFieldApellidos.getText());
					List<Plato> l = control.buscarPlatosPorAutor(autor);
					if(l!=null){
						Object[][] o  = new Object[l.size()+1][5];
						o[0][0]="Plato";
						o[0][1]="Autor";
						o[0][2]="Calorias";
						o[0][3]="Precio";
						o[0][4]="Valoración";
						for(int i=1;i<=l.size();i++){
							o[i][0]=l.get(i-1).getNombre();
							o[i][1]=l.get(i-1).getNombre();
							o[i][2]=l.get(i-1).getNombre();
							o[i][3]=l.get(i-1).getNombre();
							o[i][4]=l.get(i-1).getNombre();
						}
						table.setModel(new DefaultTableModel(
						o,
						new String[] {
							"Plato", "Autor", "Calorias", "Precio", "Valoración"
						}
					));
					
					}
					else
						JOptionPane.showMessageDialog(null, "No existen platos para ese autor", "Error", JOptionPane.ERROR_MESSAGE);
				}//FIN BUSCAR POR AUTOR COMPLETO
				//BUSCAR SOLO POR NOMBRE AUTOR
				else if(textFieldPlato.getText().equals("") && textFieldApellidos.getText().equals("") && !textFieldNombre.getText().equals("") ){
					List<Usuario> usuarios = control.getUsuarioPorNombre(textFieldNombre.getText());
					if(usuarios!=null){
						List<Plato> platos = new ArrayList<Plato>();
						for(int i=0;i<usuarios.size();i++){
							platos.addAll(control.buscarPlatosPorAutor(usuarios.get(i)));
						}
						if(platos.size()!=0){
							Object[][] o  = new Object[platos.size()+1][5];
							o[0][0]="Plato";
							o[0][1]="Autor";
							o[0][2]="Calorias";
							o[0][3]="Precio";
							o[0][4]="Valoración";
							for(int i=1;i<=platos.size();i++){
								o[i][0]=platos.get(i-1).getNombre();
								o[i][1]=platos.get(i-1).getNombre();
								o[i][2]=platos.get(i-1).getNombre();
								o[i][3]=platos.get(i-1).getNombre();
								o[i][4]=platos.get(i-1).getNombre();
							}
							table.setModel(new DefaultTableModel(
							o,
							new String[] {
								"Plato", "Autor", "Calorias", "Precio", "Valoración"
							}
						));
						}else
							JOptionPane.showMessageDialog(null, "No existen platos para autores con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(null, "No existe un autor con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
				}//FIN BUSCAR POR AUTOR
				//BUSQUEDA POR APELLIDO
				else if(textFieldPlato.getText().equals("") && textFieldNombre.getText().equals("") && !textFieldApellidos.getText().equals("")){
					List<Usuario> usuarios = control.getUsuarioPorApellidos(textFieldApellidos.getText());
					if(usuarios!=null){
						List<Plato> platos = new ArrayList<Plato>();
						for(int i=0;i<usuarios.size();i++){
							platos.addAll(control.buscarPlatosPorAutor(usuarios.get(i)));
						}
						if(platos.size()!=0){
							Object[][] o  = new Object[platos.size()+1][5];
							o[0][0]="Plato";
							o[0][1]="Autor";
							o[0][2]="Calorias";
							o[0][3]="Precio";
							o[0][4]="Valoración";
							for(int i=1;i<=platos.size();i++){
								o[i][0]=platos.get(i-1).getNombre();
								o[i][1]=platos.get(i-1).getNombre();
								o[i][2]=platos.get(i-1).getNombre();
								o[i][3]=platos.get(i-1).getNombre();
								o[i][4]=platos.get(i-1).getNombre();
							}
							table.setModel(new DefaultTableModel(
							o,
							new String[] {
								"Plato", "Autor", "Calorias", "Precio", "Valoración"
							}
						));
						}else
							JOptionPane.showMessageDialog(null, "No existen platos para autores con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(null, "No existe un autor con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
				}//FIN BUSQUEDA POR APELLIDOS
				//BUSQUEDA COMBINANDO PLATO Y AUTOR
				else{
					List<Usuario> usuarios=null;
					if(!textFieldPlato.getText().equals("") && !textFieldNombre.getText().equals("") && textFieldApellidos.getText().equals(""))
						usuarios= control.getUsuarioPorNombre(textFieldNombre.getText());
					else if(!textFieldPlato.getText().equals("") && !textFieldApellidos.getText().equals("") && textFieldNombre.getText().equals(""))
						usuarios= control.getUsuarioPorApellidos(textFieldApellidos.getText());
					else{
						usuarios = new ArrayList<Usuario>();
						usuarios.add(control.getIdUsuario(textFieldNombre.getText(), textFieldApellidos.getText()));
					}
					
					if(usuarios!=null && usuarios.get(0)!=null){
						List<Plato> platos = new ArrayList<Plato>();
						for(int i=0;i<usuarios.size();i++){
							platos.addAll(control.buscarPlatos(textFieldPlato.getText(), usuarios.get(i)));
						}
						if(platos.size()!=0){
							Object[][] o  = new Object[platos.size()+1][5];
							o[0][0]="Plato";
							o[0][1]="Autor";
							o[0][2]="Calorias";
							o[0][3]="Precio";
							o[0][4]="Valoración";
							for(int i=1;i<=platos.size();i++){
								o[i][0]=platos.get(i-1).getNombre();
								o[i][1]=platos.get(i-1).getNombre();
								o[i][2]=platos.get(i-1).getNombre();
								o[i][3]=platos.get(i-1).getNombre();
								o[i][4]=platos.get(i-1).getNombre();
							}
							table.setModel(new DefaultTableModel(
							o,
							new String[] {
								"Plato", "Autor", "Calorias", "Precio", "Valoración"
							}
						));
						}else
							JOptionPane.showMessageDialog(null, "No existen platos para autores con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(null, "No existe un autor con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
				}//FIN BUSQUEDA COMBINANDO PLATO Y AUTOR
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnBuscar)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_3)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldPlato, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
									.addGap(119)
									.addComponent(lblNewLabel_1)
									.addGap(48)
									.addComponent(lblNewLabel_2)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldApellidos)
								.addComponent(textFieldNombre, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldPlato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textFieldApellidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addComponent(btnBuscar)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
	
	private List<String> getFullString(String s, int tipo){
		List<String> res=new ArrayList<String>();
		switch(tipo){
			case 0://nombre plato
				String[] platos = control.todosPlatos();
				for(int i=0; i<platos.length;i++){
					if(platos[i].substring(0, s.length()).equals(s)){
						res.add(platos[i]);
					}
				}
				break;
			case 1: //nombre usuario
				break;
			case 2://apellidos usuario
				break;
		}
		return res;
	}
}
