package presentacion;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Ingrediente;
import servicio.Controlador;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;

public class Ingredientes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7211093247670386312L;
	private JPanel contentPane;
	private static Controlador control;
	private JTabbedPane tabbedPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ingredientes frame = new Ingredientes(null);
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
	public Ingredientes(String plato) {
	
		try {
			control = Controlador.dameControlador();
		} catch (DominioExcepcion e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("NOMBRE INGREDIENTE");
		lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
		lblNombre.setBounds(10, 11, 161, 14);
		contentPane.add(lblNombre);
		
		JButton btnInfo = new JButton("Info nutricional");
		btnInfo.setEnabled(false);
		btnInfo.setFont(new Font("Arial", Font.BOLD, 14));
		btnInfo.setBounds(214, 32, 210, 23);
		contentPane.add(btnInfo);
		
		JButton btnCompra = new JButton("A\u00F1adir lista compra");
		btnCompra.setEnabled(false);
		btnCompra.setFont(new Font("Arial", Font.BOLD, 14));
		btnCompra.setBounds(214, 263, 210, 23);
		contentPane.add(btnCompra);
		
		JButton btnOrigen = new JButton("Origen");
		btnOrigen.setFont(new Font("Arial", Font.BOLD, 14));
		btnOrigen.setBounds(214, 304, 210, 23);
		contentPane.add(btnOrigen);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 83, 414, 159);
		contentPane.add(tabbedPane);
		
		if(plato!=null){
			try {
				Ingrediente[] ingredientes = control.ingredientesPorPlato(plato);
				for(int i=0;i<ingredientes.length;i++){
					JPanel panel = new JPanel();
					tabbedPane.addTab(ingredientes[i].getNombre(), null, panel, null);
					textField = new JTextField();
					textField.setBounds(0, 0, 414, 159);
					panel.add(textField);
					textField.setColumns(10);
					textField.setText(ingredientes[i].toString());
				}

			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
}
