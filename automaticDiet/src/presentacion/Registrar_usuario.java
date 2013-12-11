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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	private Image image;
	
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
		setBounds(100, 100, 500, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			crear = new JButton("Registrar");
			crear.setBounds(384, 391, 100, 30);
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
							user.setUsername(usuario.getText());
							user.setPassword(pass.getText());
			
							File fichero=null; 
							if(dir!=null&&file!=null){
								fichero = new File(dir+"\\"+file);
								if(fichero!=null)
									user.setImagen(FileToByte(fichero));
							}
							
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
		lblDni.setBounds(15, 204, 48, 20);
		lblDni.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(207, 202, 62, 25);
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(15, 238, 62, 20);
		lblApellidos.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(15, 273, 63, 20);
		lblDireccin.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setBounds(15, 308, 80, 20);
		lblPais.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblPoblacin = new JLabel("Poblaci\u00F3n:");
		lblPoblacin.setBounds(207, 308, 80, 20);
		lblPoblacin.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(15, 149, 80, 20);
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		
		ciudad = new JTextField();
		ciudad.setBounds(279, 307, 205, 23);
		ciudad.setFont(new Font("Arial", Font.PLAIN, 14));
		ciudad.setColumns(10);
		
		mail = new JTextField();
		mail.setBounds(87, 148, 397, 23);
		mail.setFont(new Font("Arial", Font.PLAIN, 14));
		mail.setColumns(10);
		
		pais = new JTextField();
		pais.setBounds(87, 306, 110, 23);
		pais.setFont(new Font("Arial", Font.PLAIN, 14));
		pais.setColumns(10);
		
		direccion = new JTextField();
		direccion.setBounds(87, 273, 397, 23);
		direccion.setFont(new Font("Arial", Font.PLAIN, 14));
		direccion.setColumns(10);
		
		apellidos = new JTextField();
		apellidos.setBounds(87, 238, 397, 23);
		apellidos.setFont(new Font("Arial", Font.PLAIN, 14));
		apellidos.setColumns(10);
		
		nombre = new JTextField();
		nombre.setBounds(279, 203, 205, 23);
		nombre.setFont(new Font("Arial", Font.PLAIN, 14));
		nombre.setColumns(10);
		
		dni = new JTextField();
		dni.setBounds(87, 203, 110, 23);
		dni.setFont(new Font("Arial", Font.PLAIN, 14));
		dni.setColumns(10);
		
		btn_imagen = new JButton("IMAGEN");
		btn_imagen.setBounds(15, 14, 100, 100);
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

					image=im.getImage();
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
					btn_imagen.setText("");
				}
			}
		});
		btn_imagen.setFont(new Font("Arial", Font.PLAIN, 14));
		
		usuario = new JTextField();
		usuario.setFont(new Font("Arial", Font.PLAIN, 14));
		usuario.setBounds(259, 21, 225, 23);
		usuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Nombre de usuario:");
		lblUsuario.setBounds(125, 22, 124, 20);
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
		
		pass = new JTextField();
		pass.setFont(new Font("Arial", Font.PLAIN, 14));
		pass.setBounds(259, 53, 225, 23);
		pass.setColumns(10);
		
		lblPass = new JLabel("Contrase\u00F1a:");
		lblPass.setBounds(125, 54, 134, 20);
		lblPass.setFont(new Font("Arial", Font.PLAIN, 14));
		
		rpass = new JTextField();
		rpass.setFont(new Font("Arial", Font.PLAIN, 14));
		rpass.setBounds(259, 85, 225, 23);
		rpass.setColumns(10);
		
		lblRepetir = new JLabel("Repetir contrase\u00F1a:");
		lblRepetir.setBounds(125, 86, 134, 20);
		lblRepetir.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPanel.setLayout(null);
		contentPanel.add(lblApellidos);
		contentPanel.add(apellidos);
		contentPanel.add(lblDireccin);
		contentPanel.add(direccion);
		contentPanel.add(lblPoblacin);
		contentPanel.add(lblEmail);
		contentPanel.add(lblPais);
		contentPanel.add(pais);
		contentPanel.add(ciudad);
		contentPanel.add(mail);
		contentPanel.add(lblNombre);
		contentPanel.add(lblDni);
		contentPanel.add(dni);
		contentPanel.add(nombre);
		contentPanel.add(lblUsuario);
		contentPanel.add(lblPass);
		contentPanel.add(usuario);
		contentPanel.add(pass);
		contentPanel.add(btn_imagen);
		contentPanel.add(lblRepetir);
		contentPanel.add(rpass);
		contentPanel.add(crear);
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
