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
import javax.swing.JPasswordField;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

public class Registrar_usuario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5075395504482939331L;
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
	private JPasswordField pass;
	private JLabel lblPass;
	private JPasswordField rpass;
	private JLabel lblRepetir;
	
	private String file;
	private String dir;
	private Image image;
	
	private Controlador control;
	private JPanel panel_1;
	private JPanel panel_2;

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
		setMaximumSize(new Dimension(600, 800));
		setMinimumSize(new Dimension(300, 400));
		setFont(new Font("Arial", Font.PLAIN, 14));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("AutomaticDiet - Nuevo Usuario");
		setBounds(100, 100, 570, 555);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			crear = new JButton("Registrar");
			crear.setBounds(189, 474, 186, 39);
			crear.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println( pass.getPassword() );
					try {
						if (  	   nombre.getText().isEmpty()
								|| apellidos.getText().isEmpty()
								|| direccion.getText().isEmpty()
								|| pais.getText().isEmpty()
								|| ciudad.getText().isEmpty()
								|| mail.getText().isEmpty()
								|| usuario.getText().isEmpty() )
						{
							JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
						else if ( control.getUsuarioPorUsername(usuario.getText()).size()>0)
						{
							JOptionPane.showMessageDialog(null, "Ya existe un usuario con el nombre de usuario: " + usuario.getText(), "Info", JOptionPane.INFORMATION_MESSAGE);
						}
						else if ( dni.getText().length() != 9)
						{
							JOptionPane.showMessageDialog(null, "El DNI no es correcto, formato 12345678A", "Info", JOptionPane.INFORMATION_MESSAGE);
						} 
						else if ( pass.getPassword().length < 6 )
						{
							JOptionPane.showMessageDialog(null, "Contraseña demasiado debil, introduzca al menos 6 caracteres", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
						else if ( !pass.getPassword().equals(rpass.getPassword()) )
						{
							JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Info", JOptionPane.INFORMATION_MESSAGE);
						}
						else if ( image ==null)
						{
							JOptionPane.showMessageDialog(null, "Seleccione una imagen para su usuario", "Info", JOptionPane.INFORMATION_MESSAGE);
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
							user.setRol("Usuario");
							user.setUsername(usuario.getText());
							user.setPassword(new String(pass.getPassword()));
			
							File fichero=null; 
							if(dir!=null&&file!=null){
								fichero = new File(dir+"\\"+file);
								if(fichero!=null)
									user.setImagen(FileToByte(fichero));
							}
							control.addUsuario(user);
							control.setUsuarioActual(user);
							AutomaticDiet ventanaUsuario = new AutomaticDiet();
							ventanaUsuario.getAutomatic_diet().setLocationRelativeTo(null);
							ventanaUsuario.getAutomatic_diet().setVisible(true);
							
							Bienvenida bienvenida = new Bienvenida();
							bienvenida.start();
							
							try {
								finalize();
								dispose();
							} catch (Throwable e2) {
								e2.printStackTrace();
							}
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
		contentPanel.setLayout(null);
		contentPanel.add(crear);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(189, 14, 365, 169);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Nombre de usuario:");
		lblUsuario.setBounds(27, 23, 134, 26);
		panel.add(lblUsuario);
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
		
		lblPass = new JLabel("Contrase\u00F1a:");
		lblPass.setBounds(27, 71, 134, 28);
		panel.add(lblPass);
		lblPass.setFont(new Font("Arial", Font.PLAIN, 14));
		
		usuario = new JTextField();
		usuario.setBounds(155, 23, 184, 28);
		panel.add(usuario);
		usuario.setFont(new Font("Arial", Font.PLAIN, 14));
		usuario.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(155, 72, 184, 28);
		panel.add(pass);
		pass.setFont(new Font("Arial", Font.PLAIN, 14));
		pass.setColumns(10);
		
		lblRepetir = new JLabel("Repetir contrase\u00F1a:");
		lblRepetir.setBounds(27, 121, 134, 26);
		panel.add(lblRepetir);
		lblRepetir.setFont(new Font("Arial", Font.PLAIN, 14));
		
		rpass = new JPasswordField();
		rpass.setBounds(155, 121, 184, 28);
		panel.add(rpass);
		rpass.setFont(new Font("Arial", Font.PLAIN, 14));
		rpass.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Informaci\u00F3n:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(15, 194, 539, 269);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(21, 23, 71, 28);
		panel_1.add(lblEmail);
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		
		mail = new JTextField();
		mail.setBounds(102, 23, 412, 28);
		panel_1.add(mail);
		mail.setFont(new Font("Arial", Font.PLAIN, 14));
		mail.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(21, 72, 71, 28);
		panel_1.add(lblDni);
		lblDni.setFont(new Font("Arial", Font.PLAIN, 14));
		
		dni = new JTextField();
		dni.setBounds(102, 72, 125, 28);
		panel_1.add(dni);
		dni.setFont(new Font("Arial", Font.PLAIN, 14));
		dni.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(254, 72, 71, 28);
		panel_1.add(lblNombre);
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		
		nombre = new JTextField();
		nombre.setBounds(327, 72, 187, 28);
		panel_1.add(nombre);
		nombre.setFont(new Font("Arial", Font.PLAIN, 14));
		nombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(21, 121, 71, 28);
		panel_1.add(lblApellidos);
		lblApellidos.setFont(new Font("Arial", Font.PLAIN, 14));
		
		apellidos = new JTextField();
		apellidos.setBounds(102, 121, 412, 28);
		panel_1.add(apellidos);
		apellidos.setFont(new Font("Arial", Font.PLAIN, 14));
		apellidos.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(21, 170, 71, 28);
		panel_1.add(lblDireccin);
		lblDireccin.setFont(new Font("Arial", Font.PLAIN, 14));
		
		direccion = new JTextField();
		direccion.setBounds(102, 170, 412, 28);
		panel_1.add(direccion);
		direccion.setFont(new Font("Arial", Font.PLAIN, 14));
		direccion.setColumns(10);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setBounds(21, 219, 71, 28);
		panel_1.add(lblPais);
		lblPais.setFont(new Font("Arial", Font.PLAIN, 14));
		
		pais = new JTextField();
		pais.setBounds(102, 219, 125, 28);
		panel_1.add(pais);
		pais.setFont(new Font("Arial", Font.PLAIN, 14));
		pais.setColumns(10);
		
		JLabel lblPoblacin = new JLabel("Poblaci\u00F3n:");
		lblPoblacin.setBounds(254, 219, 71, 28);
		panel_1.add(lblPoblacin);
		lblPoblacin.setFont(new Font("Arial", Font.PLAIN, 14));
		
		ciudad = new JTextField();
		ciudad.setBounds(327, 219, 187, 28);
		panel_1.add(ciudad);
		ciudad.setFont(new Font("Arial", Font.PLAIN, 14));
		ciudad.setColumns(10);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Avatar:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(15, 14, 164, 169);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		btn_imagen = new JButton("IM\u00C1GEN\r\n");
		btn_imagen.setToolTipText("Carga una im\u00E1gen");
		btn_imagen.setBounds(14, 18, 140, 140);
		panel_2.add(btn_imagen);
		btn_imagen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				JFileChooser c = new JFileChooser();
				int rVal = c.showOpenDialog(Registrar_usuario.this);
				
				if (rVal == JFileChooser.APPROVE_OPTION)
				{
					file = c.getSelectedFile().getName();
					dir  = c.getCurrentDirectory().toString();

					File fichero = new File(dir+"\\"+file);

					ImageIcon im = null;
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

					image=im.getImage();
					Image newImage;
					if(width!=140 || height!=140)
					{
						newImage= image.getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH);
					}
					else
					{
						newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
					}
					
					btn_imagen.setIcon(new ImageIcon(newImage));
					btn_imagen.setText(null);
				}
			}
		});
		btn_imagen.setFont(new Font("Arial", Font.PLAIN, 14));
	}
	
	private byte[] FileToByte(File f){
		FileInputStream fis;
		byte[] zipped=null;
		try {
			fis = new FileInputStream(f);
			zipped = new byte[ (int) f.length()];
			fis.read(zipped);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}	
		return zipped;		
	}
}
