package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.Dieta;
import modelo.Ingrediente;
import modelo.Plato;
import modelo.PlatoIngrediente;
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
	private Usuario userConected;
	private JButton btnModificar;
	private JButton btnEliminar;

	/**
	 * Create the panel.
	 */
	public GestionPlatos(Usuario user) {
		
		setSize(new Dimension(800, 684));
		this.setSize(800, 600);
		
		try {
			control=Controlador.dameControlador();
		} catch (DominioExcepcion e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		userConected=control.getUsuarioPorDNI(user.getDni());
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		
		JPanel panel_tabla = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
						.addComponent(panel_tabla, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel_tabla, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_tabla.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Botones = new JPanel();
		panel_tabla.add(panel_Botones, BorderLayout.SOUTH);
		
		JButton btnAadir = new JButton("A\u00D1ADIR");
		panel_Botones.add(btnAadir);
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NuevoPlato2 ventanaPlatos;
				try {
					ventanaPlatos = new NuevoPlato2(null,userConected,GestionPlatos.this);
					ventanaPlatos.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAadir.setIcon(new ImageIcon(GestionPlatos.class.getResource("/iconos/edit_add.png")));
		btnAadir.setFont(new Font("Arial", Font.BOLD, 16));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_Botones.add(horizontalStrut_2);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_Botones.add(horizontalStrut);
		
		btnModificar = new JButton("MODIFICAR");
		panel_Botones.add(btnModificar);
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int fila = table.getSelectedRow();
					Plato plato = new Plato();
					plato.setNombre(table.getModel().getValueAt(fila, 0)+"");
					Plato platoVuelta = control.consultarPlato(plato.getNombre());
					if(platoVuelta!=null){
						if(platoVuelta.getUsuario().getDni().equals(userConected.getDni()) || userConected.getRol().equals("Administrador")){
							try {
								NuevoPlato2 ventanaPlatos = new NuevoPlato2(platoVuelta,userConected,GestionPlatos.this);
								ventanaPlatos.setVisible(true);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else
							JOptionPane.showMessageDialog(null, "No tiene permisos", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null, "No existe el plato", "Error", JOptionPane.ERROR_MESSAGE);
					
				} catch (DAOExcepcion e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setIcon(new ImageIcon(GestionPlatos.class.getResource("/iconos/edit.png")));
		btnModificar.setFont(new Font("Arial", Font.BOLD, 16));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_Botones.add(horizontalStrut_1);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_Botones.add(horizontalStrut_3);
		
		btnEliminar = new JButton("ELIMINAR");
		panel_Botones.add(btnEliminar);
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//
				Object[] options = {"SI","NO"};
				
				Icon icon = new ImageIcon("src/iconos/eliminar-icono-4912-32.png");
				
				int respuesta = JOptionPane.showOptionDialog(null, "¿Desea eliminar el plato?", "CONFIRMACIÓN", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, icon, options, options[0]);
				if (respuesta == 0 )
				{
					try {
						int fila = table.getSelectedRow();
						Plato plato = new Plato();
						plato.setNombre(table.getModel().getValueAt(fila,0)+"");
						Plato platoVuelta = control.consultarPlato(plato.getNombre());
						if(!control.getPlatosEnDieta(platoVuelta.getId())){
							if(platoVuelta.getUsuario().getDni().equals(userConected.getDni()) || userConected.getRol().equals("Administrador")){
							List<PlatoIngrediente> pi = control.getPlatoIngredientes(platoVuelta);
							control.eliminarPlatoIngredientePorPlato(pi);
							control.eliminarPlato(platoVuelta);
							poblar();
							}else
								JOptionPane.showMessageDialog(null, "No tiene permisos", "Error", JOptionPane.ERROR_MESSAGE);

						}
						else
							JOptionPane.showMessageDialog(null, "El plato pertene a una dieta o no tiene permisos y no se puede borrar.", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (DAOExcepcion e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					btnEliminar.setEnabled(false);
					btnModificar.setEnabled(false);
					JOptionPane.showMessageDialog(null, "El plato se ha eliminado correctamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}else{
						JOptionPane.showMessageDialog(null, "El plato no se ha eliminado.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				//
				
				
				
			}
		});
		btnEliminar.setIcon(new ImageIcon(GestionPlatos.class.getResource("/iconos/eliminar-icono-4912-32.png")));
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_tabla.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(1,5);
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		//add(table);
		
		ListSelectionModel lsm = table.getSelectionModel();
		
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
						List<String> posibles = getFullString(textFieldPlato.getText(),0);
						if(posibles!=null){
						Object[][] o  = new Object[posibles.size()+1][5];
						for(int i=0;i<posibles.size();i++){
							Plato plato = control.consultarPlato(posibles.get(i));
							o[i][0]=plato.getNombre();
							o[i][1]=plato.getUsuario().getNombre()+" "+plato.getUsuario().getApellidos();
							//o[i][2]=(int)getInfoPorPlato(plato)[0]+" KCal";
							//o[i][3]=getInfoPorPlato(plato)[1].toString()+" €";
							o[i][2]=control.calcularCalorias(plato.getId())+" KCal";
							o[i][3]=control.calcularPrecio(plato.getId())+" €";
							o[i][4]=plato.getValoracion();
						}
							table.setModel(new DefaultTableModel(
							o,
							new String[] {
								"Plato", "Autor", "Calorias", "Precio", "Valoración"
							}
						));
							for(int i=0;i<posibles.size();i++)
								table.setRowHeight(i, 75);
						}else
							JOptionPane.showMessageDialog(null, "No existen platos.", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (DAOExcepcion e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}//FIN BUSCAR POR NOMBRE PLATO
				//BUSCAR POR AUTOR COMPLETO
				else if(textFieldPlato.getText().equals("") && !textFieldNombre.getText().equals("") && !textFieldApellidos.getText().equals("")){
					List<String> posiblesNombres = getFullString(textFieldNombre.getText(),1);
					List<String> posiblesApellidos = getFullString(textFieldApellidos.getText(),2);
					if(posiblesNombres!=null && posiblesApellidos!=null){
						List<Usuario> usuarios =new ArrayList<Usuario>();
						for(int i=0;i<posiblesNombres.size();i++){	
							for(int j=0;j<posiblesApellidos.size();j++){	
								usuarios.add(control.getIdUsuario(posiblesNombres.get(i), posiblesApellidos.get(i)));
							}
							//usuarios.addAll(control.getUsuarioPorNombre(posibles.get(i)));
						}
					//Usuario autor = control.getIdUsuario(textFieldNombre.getText(), textFieldApellidos.getText());
						if(usuarios.size()!=0 && sonUsuariosCorrectos(usuarios)){
							List<Plato> l = new ArrayList<Plato>();
							for(int i=0;i<usuarios.size();i++)
								l.addAll(control.buscarPlatosPorAutor(usuarios.get(i)));
							if(l.size()!=0){
								Object[][] o  = new Object[l.size()+1][5];
//								o[0][0]="Plato";
//								o[0][1]="Autor";
//								o[0][2]="Calorias";
//								o[0][3]="Precio";
//								o[0][4]="Valoración";
								for(int i=0;i<l.size();i++){
									o[i][0]=l.get(i).getNombre();
									o[i][1]=l.get(i).getUsuario().getNombre()+" "+l.get(i).getUsuario().getApellidos();
									//o[i][2]=(int)getInfoPorPlato(l.get(i))[0]+" KCal";
									//o[i][3]=getInfoPorPlato(l.get(i))[1].toString()+" €";
									o[i][2]=control.calcularCalorias(l.get(i).getId())+" KCal";
									o[i][3]=control.calcularPrecio(l.get(i).getId())+" €";
									o[i][4]=l.get(i).getValoracion();
								}
								table.setModel(new DefaultTableModel(
								o,
								new String[] {
									"Plato", "Autor", "Calorias", "Precio", "Valoración"
								}
										));
								for(int i=0;i<l.size();i++)
									table.setRowHeight(i, 75);
							}
							else
								JOptionPane.showMessageDialog(null, "No existen platos para ese usuario", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null, "No existen usuarios con esas características", "Error", JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(null, "No existen usuarios con esas características", "Error", JOptionPane.ERROR_MESSAGE);
				}//FIN BUSCAR POR AUTOR COMPLETO
				//BUSCAR SOLO POR NOMBRE AUTOR
				else if(textFieldPlato.getText().equals("") && textFieldApellidos.getText().equals("") && !textFieldNombre.getText().equals("") ){
					List<String> posibles = getFullString(textFieldNombre.getText(),1);
					if(posibles!=null){
						List<Usuario> usuarios =new ArrayList<Usuario>();
						for(int i=0;i<posibles.size();i++){	
							usuarios.addAll(control.getUsuarioPorNombre(posibles.get(i)));
						}
						if(usuarios.size()!=0){
						List<Plato> platos = new ArrayList<Plato>();
						for(int i=0;i<usuarios.size();i++){
							platos.addAll(control.buscarPlatosPorAutor(usuarios.get(i)));
						}
						if(platos.size()!=0){
							Object[][] o  = new Object[platos.size()+1][5];
//							o[0][0]="Plato";
//							o[0][1]="Autor";
//							o[0][2]="Calorias";
//							o[0][3]="Precio";
//							o[0][4]="Valoración";
							for(int i=0;i<platos.size();i++){
								o[i][0]=platos.get(i).getNombre();
								o[i][1]=platos.get(i).getUsuario().getNombre()+" "+platos.get(i).getUsuario().getApellidos();
								//o[i][2]=(int)getInfoPorPlato(platos.get(i))[0]+" KCal";
								//o[i][3]=getInfoPorPlato(platos.get(i))[1].toString()+" €";
								o[i][2]=control.calcularCalorias(platos.get(i).getId())+" KCal";
								o[i][3]=control.calcularPrecio(platos.get(i).getId())+" €";
								o[i][4]=platos.get(i).getValoracion();
							}
							table.setModel(new DefaultTableModel(
							o,
							new String[] {
								"Plato", "Autor", "Calorias", "Precio", "Valoración"
							}
						));
							for(int i=0;i<platos.size();i++)
								table.setRowHeight(i, 75);
						}else
							JOptionPane.showMessageDialog(null, "No existen platos para autores con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(null, "No existe un autor con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}//FIN BUSCAR POR AUTOR
				//BUSQUEDA POR APELLIDO
				else if(textFieldPlato.getText().equals("") && textFieldNombre.getText().equals("") && !textFieldApellidos.getText().equals("")){
					List<String> posibles = getFullString(textFieldApellidos.getText(),2);
					if(posibles!=null){
						List<Usuario> usuarios =new ArrayList<Usuario>();
						for(int i=0;i<posibles.size();i++){	
							usuarios.addAll(control.getUsuarioPorApellidos(posibles.get(i)));
						}
						//List<Usuario> usuarios = control.getUsuarioPorApellidos(textFieldApellidos.getText());
						if(usuarios.size()!=0){
							List<Plato> platos = new ArrayList<Plato>();
							for(int i=0;i<usuarios.size();i++){
								platos.addAll(control.buscarPlatosPorAutor(usuarios.get(i)));
							}
							if(platos.size()!=0){
								Object[][] o  = new Object[platos.size()+1][5];
//								o[0][0]="Plato";
//								o[0][1]="Autor";
//								o[0][2]="Calorias";
//								o[0][3]="Precio";
//								o[0][4]="Valoración";
								for(int i=0;i<platos.size();i++){
									o[i][0]=platos.get(i).getNombre();
									o[i][1]=platos.get(i).getUsuario().getNombre()+" "+platos.get(i).getUsuario().getApellidos();
									//o[i][2]=(int)getInfoPorPlato(platos.get(i))[0]+" KCal";
									//o[i][3]=getInfoPorPlato(platos.get(i))[1].toString()+" €";
									o[i][2]=control.calcularCalorias(platos.get(i).getId())+" KCal";
									o[i][3]=control.calcularPrecio(platos.get(i).getId())+" €";
									o[i][4]=platos.get(i).getValoracion();
								}
								table.setModel(new DefaultTableModel(
								o,
								new String[] {
									"Plato", "Autor", "Calorias", "Precio", "Valoración"
								}
							));
								for(int i=0;i<platos.size();i++)
									table.setRowHeight(i, 75);
							}else
								JOptionPane.showMessageDialog(null, "No existen platos para autores con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
						}else
							JOptionPane.showMessageDialog(null, "No existe un autor con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}//FIN BUSQUEDA POR APELLIDOS
				//SIN BUSQUEDA
				else if(textFieldApellidos.getText().equals("") && textFieldNombre.getText().equals("") && textFieldPlato.getText().equals("")){
					poblar();
				//BUSQUEDA COMBINANDO PLATO Y AUTOR
				}else{
					List<Usuario> usuarios=null;
					List<String> posiblesPlatos = null;
					List<String> posiblesNombres = null;
					List<String> posiblesApellidos = null;
					if(!textFieldPlato.getText().equals("") && !textFieldNombre.getText().equals("") && textFieldApellidos.getText().equals("")){
						posiblesPlatos = getFullString(textFieldPlato.getText(),0);
						posiblesNombres = getFullString(textFieldNombre.getText(),1);
						if(posiblesNombres!=null && posiblesPlatos!=null){
							usuarios =new ArrayList<Usuario>();
							for(int i=0;i<posiblesNombres.size();i++){	
								usuarios.addAll(control.getUsuarioPorNombre(posiblesNombres.get(i)));
							}
						}
						
						//usuarios= control.getUsuarioPorNombre(textFieldNombre.getText());
					}
					else if(!textFieldPlato.getText().equals("") && !textFieldApellidos.getText().equals("") && textFieldNombre.getText().equals("")){
						//usuarios= control.getUsuarioPorApellidos(textFieldApellidos.getText());
						posiblesPlatos = getFullString(textFieldPlato.getText(),0);
						posiblesApellidos = getFullString(textFieldApellidos.getText(),2);
						if(posiblesApellidos!=null && posiblesPlatos!=null){
							usuarios =new ArrayList<Usuario>();
							for(int i=0;i<posiblesApellidos.size();i++){	
								usuarios.addAll(control.getUsuarioPorApellidos(posiblesApellidos.get(i)));
							}
						}
					}else{
						posiblesPlatos = getFullString(textFieldPlato.getText(),0);
						posiblesApellidos = getFullString(textFieldApellidos.getText(),2);
						posiblesNombres = getFullString(textFieldNombre.getText(),1);
						if(posiblesApellidos!=null && posiblesPlatos!=null && posiblesNombres!=null){
							usuarios = new ArrayList<Usuario>();
							for(int i=0; i<posiblesNombres.size();i++)
								for(int j=0;j<posiblesApellidos.size();j++)
									usuarios.add(control.getIdUsuario(posiblesNombres.get(i), posiblesApellidos.get(j)));
						}
					}
					
					if(usuarios.size()!=0 && usuarios.get(0)!=null){
						List<Plato> platos = new ArrayList<Plato>();
						for(int i=0;i<usuarios.size();i++){
							for(int j=0;j<posiblesPlatos.size();j++){
							platos.addAll(control.buscarPlatos(posiblesPlatos.get(j), usuarios.get(i)));
							}
						}
						if(platos.size()!=0){
							Object[][] o  = new Object[platos.size()+1][5];
//							o[0][0]="Plato";
//							o[0][1]="Autor";
//							o[0][2]="Calorias";
//							o[0][3]="Precio";
//							o[0][4]="Valoración";
							for(int i=0;i<platos.size();i++){
								o[i][0]=platos.get(i).getNombre();
								o[i][1]=platos.get(i).getUsuario().getNombre()+" "+platos.get(i).getUsuario().getApellidos();
								//o[i][2]=(int)getInfoPorPlato(platos.get(i))[0]+" KCal";
								o[i][2]=control.calcularCalorias(platos.get(i).getId())+" KCal";
								//o[i][3]=getInfoPorPlato(platos.get(i))[1].toString()+" €";
								o[i][3]=control.calcularPrecio(platos.get(i).getId())+" €";
								o[i][4]=platos.get(i).getValoracion();
							}
							table.setModel(new DefaultTableModel(
							o,
							new String[] {
								"Plato", "Autor", "Calorias", "Precio", "Valoración"
							}
						));
							for(int i=0;i<platos.size();i++)
								table.setRowHeight(i, 75);
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
		lsm.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e){
		         btnModificar.setEnabled(true);
		         btnEliminar.setEnabled(true);
		   }
		});

	}
	
	private List<String> getFullString(String s, int tipo){
		List<String> res=new ArrayList<String>();
		List<Usuario> usuarios = null;
		switch(tipo){
			case 0://nombre plato
				String[] platos = control.todosPlatos();
				for(int i=0; i<platos.length;i++){
					if(platos[i].length() >= s.length() && platos[i].substring(0, s.length()).equals(s)){
						res.add(platos[i]);
					}
				}
				break;
			case 1: //nombre usuario
				usuarios = control.getUsuarios();
				for(int i=0; i<usuarios.size();i++){
					if(usuarios.get(i).getNombre().length()>=s.length() && usuarios.get(i).getNombre().substring(0, s.length()).equals(s)){
						if(i==0)
							res.add(usuarios.get(i).getNombre());
						else if(!existe(res,usuarios.get(i).getNombre()))
							res.add(usuarios.get(i).getNombre());
					}
				}
				break;
			case 2://apellidos usuario
				usuarios = control.getUsuarios();
				for(int i=0; i<usuarios.size();i++){
					if(usuarios.get(i).getApellidos().length()>=s.length() && usuarios.get(i).getApellidos().substring(0, s.length()).equals(s)){
						if(i==0)
							res.add(usuarios.get(i).getApellidos());
						else if(!existe(res,usuarios.get(i).getApellidos()))
							res.add(usuarios.get(i).getApellidos());
					}
				}
				break;
		}
		return res;
	}
	
	private boolean existe(List<String> a, String b){
		for(int i=0;i<a.size();i++){
			if(a.get(i).equals(b))
				return true;
		}
		return false;
	}
	
	private boolean sonUsuariosCorrectos(List<Usuario> l){
		boolean res=true;
		for(int i=0; i<l.size();i++)
			if(l.get(i)==null)
				return false;
		return res;
	}
	
	private Object[] getInfoPorPlato(Plato p){
		Ingrediente[] ing;
		Object ingrs[] = new Object [2]; 
		int cal=0;
		Double precio= new Double(0.0);
		try {
			ing = control.ingredientesPorPlato(p.getNombre());
			for(int i=0; i<ing.length;i++){
				cal+=ing[i].getCalorias();
				precio= precio+ing[i].getPrecio();
			}
			ingrs[0]=cal;
			ingrs[1]=precio;
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ingrs; 
	}
	
	void poblar(){
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(3).setCellRenderer(tcr);
		table.getColumnModel().getColumn(4).setCellRenderer(tcr);
		String[] pl = control.todosPlatos();
		Object[][] o  = new Object[pl.length][5];
//		o[0][0]="Plato";
//		o[0][1]="Autor";
//		o[0][2]="Calorias";
//		o[0][3]="Precio";
//		o[0][4]="Valoración";
		for(int i=0;i<pl.length;i++){
			table.setRowHeight(i, 100);
			Plato plato=null;
			try {
					plato = control.consultarPlato(pl[i]);
					o[i][0]=plato.getNombre();
					o[i][1]=plato.getUsuario().getNombre()+" "+plato.getUsuario().getApellidos();
					//o[i][2]=(int)getInfoPorPlato(plato)[0]+" KCal";
					//o[i][3]=getInfoPorPlato(plato)[1].toString()+" €";
					o[i][2]=control.calcularCalorias(plato.getId())+" KCal";
					o[i][3]=control.calcularPrecio(plato.getId())+" €";
					o[i][4]=plato.getValoracion();				
			} catch (DAOExcepcion e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			table.setModel(new DefaultTableModel(
			o,
			new String[] {
				"Plato", "Autor", "Calorias", "Precio", "Valoración"
			}
		));
			for(int i=0;i<pl.length;i++)
				table.setRowHeight(i, 75);
	}
}
