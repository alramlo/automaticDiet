package presentacion;

import java.awt.Dimension;

import javax.swing.JPanel;

import modelo.Dieta;
import modelo.Usuario;
import excepciones.DominioExcepcion;
import servicio.Controlador;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JLabel;

public class GestionDietas extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private Controlador control;
	private Usuario userConected;
	private final JButton btnEliminar = new JButton("Eliminar");
	private JTable table;
	private List<Dieta> listaDietas;
	
	public GestionDietas() {
		
		setSize(new Dimension(800, 684));
		this.setSize(800, 600);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton buttonAnadir = new JButton("A\u00F1adir");
		panel.add(buttonAnadir);
		
		JButton buttonModificar = new JButton("Modificar");
		panel.add(buttonModificar);
		panel.add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		try {
			control=Controlador.dameControlador();
		} catch (DominioExcepcion e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		userConected=control.getUsuarioActual();
		listaDietas = control.getListDietas(userConected);
		
		

	}
}
