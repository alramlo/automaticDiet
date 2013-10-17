package presentacion;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Ingredientes2 extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7838174345100775020L;
	private JPanel contentPane;
	private JTextField textFieldInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ingredientes2 frame = new Ingredientes2(null);
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
	public Ingredientes2(String plato) {
		
		
		if(plato!=null){
			//List<Ingredientes> lista = Controlador.dameControlador().IngredientesPorPlato(plato);
			//recorrer la lista y rellenar los campos
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
		
		textFieldInfo = new JTextField();
		textFieldInfo.setBounds(214, 83, 210, 169);
		contentPane.add(textFieldInfo);
		textFieldInfo.setColumns(10);
		
		JList<String> listLista = new JList<String>();
		listLista.setBounds(10, 253, 188, -167);
		contentPane.add(listLista);
		
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
	}

}
