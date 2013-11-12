package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NuevoPlato2 extends JFrame {

	private JPanel contentPane;
	private JTextField tNombre;
	private JTextField tNuevo;
	private JTextField tCantidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoPlato2 frame = new NuevoPlato2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NuevoPlato2() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lNombre = new JLabel("Nombre del plato: ");
		lNombre.setFont(new Font("Arial", Font.BOLD, 16));
		lNombre.setBounds(37, 60, 140, 19);
		getContentPane().add(lNombre);
		
		tNombre = new JTextField();
		tNombre.setBounds(187, 59, 186, 20);
		getContentPane().add(tNombre);
		tNombre.setColumns(10);
		
		JLabel lAutor = new JLabel("Autor: ");
		lAutor.setFont(new Font("Arial", Font.BOLD, 16));
		lAutor.setBounds(396, 62, 66, 14);
		getContentPane().add(lAutor);
		
		JLabel lAutor2 = new JLabel("Autor Leido...");
		lAutor2.setBounds(453, 64, 122, 14);
		getContentPane().add(lAutor2);
		
		JLabel lIngredientes = new JLabel("Ingredientes:");
		lIngredientes.setFont(new Font("Arial", Font.BOLD, 16));
		lIngredientes.setBounds(37, 124, 140, 20);
		getContentPane().add(lIngredientes);
		
		JList listIngredientes = new JList();
		listIngredientes.setBounds(191, 287, 182, -156);
		getContentPane().add(listIngredientes);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(396, 125, 89, 23);
		getContentPane().add(btnEliminar);
		
		JLabel lNuevo = new JLabel("Nuevo Ingrediente: ");
		lNuevo.setFont(new Font("Arial", Font.BOLD, 16));
		lNuevo.setBounds(37, 305, 159, 20);
		getContentPane().add(lNuevo);
		
		tNuevo = new JTextField();
		tNuevo.setColumns(10);
		tNuevo.setBounds(187, 307, 186, 20);
		getContentPane().add(tNuevo);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(396, 306, 89, 23);
		getContentPane().add(btnBuscar);
		
		JLabel lCantidad = new JLabel("Cantidad:");
		lCantidad.setFont(new Font("Arial", Font.BOLD, 16));
		lCantidad.setBounds(37, 336, 159, 20);
		getContentPane().add(lCantidad);
		
		tCantidad = new JTextField();
		tCantidad.setBounds(5, 5, 658, 384);
		getContentPane().add(tCantidad);
		tCantidad.setColumns(10);
	}

}
