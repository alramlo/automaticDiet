package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.Usuario;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;
import servicio.Controlador;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Window.Type;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
	private JButton btn_imagen;
	private JTextField usuario;
	private JTextField pass;
	private JLabel lblPass;
	private JTextField rpass;
	private JLabel lblRepetir;
	
	private String file;
	private String dir;
	
	private Controlador control;

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
	public Registrar_usuario()
	{
		try {
			control=Controlador.dameControlador();
		} catch (DominioExcepcion e3) {
			e3.printStackTrace();
		}
		
		
		setResizable(false);
		setAlwaysOnTop(true);
		setMaximumSize(new Dimension(600, 800));
		setMinimumSize(new Dimension(300, 400));
		setFont(new Font("Arial", Font.PLAIN, 14));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Nuevo Usuario");
		setBounds(100, 100, 400, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			crear = new JButton("Registrar");
			crear.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					try {
						if ( dni.getText().length() < 8 )
						{
							JOptionPane.showMessageDialog(null, "El DNI no es correcto", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
						else if (  nombre.getText()	   ==null
								|| apellidos.getText() ==null
								|| direccion.getText() ==null
								|| pais.getText()	   ==null
								|| ciudad.getText()	   ==null
								|| mail.getText()	   ==null
								|| usuario.getText()   ==null )
						{
							JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos", "Info", JOptionPane.INFORMATION_MESSAGE);
						} else if ( control.getUsuarioPorUsername(usuario.getText()) != null)
						{
							JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nick", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
						else if ( pass.getText().length() < 6 )
						{
							JOptionPane.showMessageDialog(null, "Contraseña demasiado debil, introduzca al menos 6 caracteres", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
						else if ( pass.getText() != rpass.getText() )
						{
							JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							Usuario user = new Usuario();
							user.setDni(dni.getText());
							user.setNombre(nombre.getText());
							user.setApellidos(apellidos.getText());
							user.setDireccion(direccion.getText());
							user.setPais(pais.getText());
							user.setPoblacion(ciudad.getText());
							user.setCorreo(mail.getText());
							user.setUsername(usuario.getText());
							user.setPassword(pass.getText());
							
							control.addUsuario(user);
						}
						
					}
					catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
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
		
		btn_imagen = new JButton("IMAGEN");
		btn_imagen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				JFileChooser c = new JFileChooser();
				int rVal = c.showOpenDialog(Registrar_usuario.this);
				
				if (rVal == JFileChooser.APPROVE_OPTION)
				{
					file=c.getSelectedFile().getName();
					dir=c.getCurrentDirectory().toString();

					File fichero=null;

					fichero = new File(dir+"\\"+file);

					ImageIcon im=null;
					BufferedImage buffer;
					int width=0,height=0;

					try
					{
						buffer = ImageIO.read(fichero);
						im = new ImageIcon(buffer);
						width=buffer.getWidth();
						height=buffer.getHeight();
					} 
					catch (IOException e1)
					{
						e1.printStackTrace();
					}

					Image image=im.getImage();
					Image newImage;
					if(width>112 || height>98)
					{
						newImage= image.getScaledInstance(width/3, height/3, java.awt.Image.SCALE_SMOOTH);
					}
					else
					{
						newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
					}
					
					btn_imagen.setIcon(new ImageIcon(newImage));
				}
			}
		});
		btn_imagen.setFont(new Font("Arial", Font.PLAIN, 14));
		
		usuario = new JTextField();
		usuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
		
		pass = new JTextField();
		pass.setColumns(10);
		
		lblPass = new JLabel("Contrase\u00F1a:");
		lblPass.setFont(new Font("Arial", Font.PLAIN, 14));
		
		rpass = new JTextField();
		rpass.setColumns(10);
		
		lblRepetir = new JLabel("Repetir:");
		lblRepetir.setFont(new Font("Arial", Font.PLAIN, 14));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(10, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblApellidos, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(apellidos, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblDireccin, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
										.addComponent(direccion, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblPoblacin, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblPais, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
										.addGap(10)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(pais, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
											.addComponent(ciudad, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
											.addComponent(mail, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblDni, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
										.addGap(10)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
											.addComponent(dni, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
											.addComponent(nombre, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblPass, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
										.addGap(10)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
											.addComponent(usuario, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
											.addComponent(pass, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGap(132)
								.addComponent(btn_imagen, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblRepetir, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(rpass)))
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
					.addGap(30)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(usuario, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pass, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPass, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rpass, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRepetir, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addComponent(btn_imagen, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(crear, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
