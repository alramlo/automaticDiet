package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.Dimension;

public class Registrar_usuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton crear;
	private JTextField ciudad;
	private JTextField mail;
	private JTextField pais;
	private JTextField direccion;
	private JTextField apellidos;
	private JTextField nombre;
	private JTextField dni;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Registrar_usuario dialog = new Registrar_usuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Registrar_usuario() {
		setResizable(false);
		setAlwaysOnTop(true);
		setMaximumSize(new Dimension(600, 800));
		setMinimumSize(new Dimension(300, 400));
		setFont(new Font("Arial", Font.PLAIN, 14));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Nuevo Usuario");
		setBounds(100, 100, 300, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			crear = new JButton("Registrar");
			crear.setFont(new Font("Arial", Font.PLAIN, 16));
			crear.setActionCommand("OK");
			getRootPane().setDefaultButton(crear);
		}
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblPoblacin = new JLabel("Poblaci\u00F3n:");
		lblPoblacin.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		
		ciudad = new JTextField();
		ciudad.setFont(new Font("Arial", Font.PLAIN, 14));
		ciudad.setColumns(10);
		
		mail = new JTextField();
		mail.setFont(new Font("Arial", Font.PLAIN, 14));
		mail.setColumns(10);
		
		pais = new JTextField();
		pais.setFont(new Font("Arial", Font.PLAIN, 14));
		pais.setColumns(10);
		
		direccion = new JTextField();
		direccion.setFont(new Font("Arial", Font.PLAIN, 14));
		direccion.setColumns(10);
		
		apellidos = new JTextField();
		apellidos.setFont(new Font("Arial", Font.PLAIN, 14));
		apellidos.setColumns(10);
		
		nombre = new JTextField();
		nombre.setFont(new Font("Arial", Font.PLAIN, 14));
		nombre.setColumns(10);
		
		dni = new JTextField();
		dni.setFont(new Font("Arial", Font.PLAIN, 14));
		dni.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblApellidos, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(apellidos, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblDireccin, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(direccion, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPoblacin, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPais, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(pais, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
								.addComponent(ciudad, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
								.addComponent(mail, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDni, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(dni, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
								.addComponent(nombre, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))
						.addComponent(crear, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDni, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(dni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellidos, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(apellidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDireccin, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(direccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPais, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(pais, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPoblacin, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(ciudad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(mail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
					.addComponent(crear, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
