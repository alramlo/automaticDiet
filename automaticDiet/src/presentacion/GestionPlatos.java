package presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Plato;
import modelo.Usuario;
import servicio.Controlador;
import excepciones.DominioExcepcion;

public class GestionPlatos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6087570348856138948L;
	private JTextField textFieldNombrePlato;
	private JTextField textFieldApellidosAutor;
	private JTextField textFieldNombreAutor;
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
		
		setLayout(null);
		
		
		
		JButton btnNewButtonEliminar = new JButton("ELIMINAR");
		btnNewButtonEliminar.setIcon(new ImageIcon(GestionPlatos.class.getResource("/iconos/eliminar-icono-4912-32.png")));
		btnNewButtonEliminar.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButtonEliminar.setBounds(101, 532, 172, 41);
		add(btnNewButtonEliminar);
		
		JButton btnNewButtonAnadir = new JButton("A\u00D1ADIR");
		btnNewButtonAnadir.setIcon(new ImageIcon(GestionPlatos.class.getResource("/iconos/edit_add.png")));
		btnNewButtonAnadir.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButtonAnadir.setBounds(305, 532, 145, 41);
		add(btnNewButtonAnadir);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.setIcon(new ImageIcon(GestionPlatos.class.getResource("/iconos/edit.png")));
		btnModificar.setFont(new Font("Arial", Font.BOLD, 16));
		btnModificar.setBounds(483, 532, 182, 41);
		add(btnModificar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(29, 11, 744, 173);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(44, 23, 81, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		textFieldNombrePlato = new JTextField();
		textFieldNombrePlato.setBounds(120, 22, 185, 20);
		panel.add(textFieldNombrePlato);
		textFieldNombrePlato.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Autor:");
		lblNewLabel_1.setBounds(353, 26, 69, 14);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(428, 27, 81, 14);
		panel.add(lblNombre);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblNewLabel_2 = new JLabel("Apellidos:");
		lblNewLabel_2.setBounds(422, 58, 81, 14);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		
		textFieldApellidosAutor = new JTextField();
		textFieldApellidosAutor.setBounds(506, 53, 193, 20);
		panel.add(textFieldApellidosAutor);
		textFieldApellidosAutor.setColumns(10);
		
		textFieldNombreAutor = new JTextField();
		textFieldNombreAutor.setBounds(506, 24, 193, 20);
		panel.add(textFieldNombreAutor);
		textFieldNombreAutor.setColumns(10);
		
		JButton btnNewButtonBuscar = new JButton("BUSCAR");
		btnNewButtonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuario;
				List<Plato> platos=null;
				//FILTRO POR AUTOR
				if(!textFieldNombreAutor.getText().equals("") && !textFieldApellidosAutor.getText().equals("") && textFieldNombrePlato.getText().equals("")){
					usuario = getIdUsuario(textFieldNombreAutor.getText(),textFieldApellidosAutor.getText());
					if(usuario==null){
						JOptionPane.showMessageDialog(null, "No existe el usuario", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else{
						platos=control.buscarPlatosPorAutor(usuario);
						if(platos!=null)
							System.out.println(platos.get(0).getNombre());
						else
							JOptionPane.showMessageDialog(null, "No existe el plato", "Error", JOptionPane.ERROR_MESSAGE);
					}
				//FALTA NOMBRE O APELLIDO
				}else if((!textFieldNombreAutor.getText().equals("") && textFieldApellidosAutor.getText().equals("")) ||
						(textFieldNombreAutor.getText().equals("") && !textFieldApellidosAutor.getText().equals("")))
							JOptionPane.showMessageDialog(null, "Complete el nombre y apellidos del usuario", "Error", JOptionPane.ERROR_MESSAGE);
				//NO SE QUIERE FILTRAR POR AUTOR
				else{
					if(!textFieldNombrePlato.getText().equals("")){
						platos=control.buscarPlatos(textFieldNombrePlato.getText(),null);
						if(platos!=null)
							mostrar(platos);
						else
							JOptionPane.showMessageDialog(null, "No existe el plato", "Error", JOptionPane.ERROR_MESSAGE);

					}
					else{
						JOptionPane.showMessageDialog(null, "No existen platos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnNewButtonBuscar.setBounds(553, 110, 145, 41);
		panel.add(btnNewButtonBuscar);
		btnNewButtonBuscar.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButtonBuscar.setIcon(new ImageIcon(GestionPlatos.class.getResource("/iconos/buscar.png")));
		
//		table_1 = new JTable();
//		table_1.setBounds(29, 504, 744, -282);
//		add(table_1);

	}
	
	private Usuario getIdUsuario(String n, String a){
		return control.getIdUsuario(n, a);
	}
	
	private List<Plato> buscarPlatos(String plato, Usuario idUsuario){
			return control.buscarPlatos(plato,idUsuario);
	}
	
	private void mostrar(List<Plato> l){

	}
}
