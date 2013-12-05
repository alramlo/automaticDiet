package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import modelo.Usuario;
import servicio.Controlador;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;

import java.awt.Color;

public class Login extends JDialog
{

	private static final long serialVersionUID = 1L;
	private static Controlador control;
	
	private final JPanel contentPanel = new JPanel();

	private JTextField tfUser;
	private JPasswordField tfPass;
	private JButton buttonHospital;
	
	private List<Usuario> listaUsuarios;


	/**
	 * Método para lanzar la aplicación
	 **/
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					Login login = new Login();
					login.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					login.setLocationRelativeTo(null);
					login.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the dialog.
	 */
	public Login()
	{	
		initDominio();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/iconos/logo_icon.png")));
//			Icon hospital = new ImageIcon(getClass().getResource( "/recursos/hospital-red-2-icon.png" ) );
			setTitle("AutomaticDiet - Login");
			setBounds(100,100,448,325);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5,5,5,5));
			getContentPane().add(contentPanel,BorderLayout.CENTER);
			contentPanel.setLayout(null);
			{
				JLabel lblUser = new JLabel("Nombre de usuario:");
				lblUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblUser.setVerticalAlignment(SwingConstants.BOTTOM);
				lblUser.setHorizontalAlignment(SwingConstants.LEFT);
				lblUser.setBounds(28, 32, 193, 28);
				contentPanel.add(lblUser);
			}
			{
				tfUser = new JTextField();
				tfUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfUser.setBounds(28, 61, 193, 34);
				contentPanel.add(tfUser);
				tfUser.setColumns(10);

			}
			{
				JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
				lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblContrasea.setVerticalAlignment(SwingConstants.BOTTOM);
				lblContrasea.setHorizontalAlignment(SwingConstants.LEFT);
				lblContrasea.setBounds(28, 106, 193, 28);
				contentPanel.add(lblContrasea);
			}
			{
				tfPass = new JPasswordField();
				tfPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfPass.setBounds(28, 135, 193, 35);
				contentPanel.add(tfPass);
				tfPass.setColumns(10);

			}
			
			buttonHospital = new JButton("Registrarse");
			buttonHospital.setBackground(new Color(0, 139, 139));
			buttonHospital.setFont(new Font("Tahoma", Font.PLAIN, 18));
			buttonHospital.setIcon(null);
			buttonHospital.addMouseListener(new BtnCargarBbddMouseListener());
			buttonHospital.setBounds(247, 221, 150, 34);
			contentPanel.add(buttonHospital);
			{
				JButton okButton=new JButton("");
				okButton.setBounds(247, 32, 150, 150);
				contentPanel.add(okButton);
				okButton.setMinimumSize(new Dimension(80, 28));
				okButton.setMaximumSize(new Dimension(100, 30));
				okButton.setIcon(new ImageIcon(Login.class.getResource("/iconos/logo_icon.png")));
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				okButton.setHorizontalTextPosition(SwingConstants.RIGHT);
				okButton.setPreferredSize(new Dimension(80, 30));
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
				
				okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							control.logout();
							String user, pass;
							Usuario us=null;
							user = new String(tfUser.getText());
							pass = new String(tfPass.getPassword());
							tfPass.setText("");
							tfUser.setText("");
							
							try {								
								listaUsuarios=control.getUsuarioPorUsername(user);
								us=listaUsuarios.get(0);
							} catch (DAOExcepcion e) { e.printStackTrace();	}
							
							if(us==null){
								JOptionPane.showMessageDialog(null, String.format("ERROR: USUARIO INCORRECTO", JOptionPane.ERROR_MESSAGE));
							}
							else if(us.getPassword().equals(pass))
									{
											control.setUsuarioActual(us);
											
											JOptionPane.showMessageDialog(null, String.format("LOGIN CORRECTO", JOptionPane.INFORMATION_MESSAGE));
											
											if(control.getUsuarioActual().getRol().equals("Usuario"))
											{
												

											}
											else if(control.getUsuarioActual().getRol().equals("Administrador"))
											{
												

											}
											else if(control.getUsuarioActual().getRol().equals("Colaborador"))									
											{
//												AppJefe ventanaJefe = new AppJefe(control,  getContentPane());
//												ventanaJefe.setVisible(true);
											}
											else
											{
												// Dato incorrecto en el atributo Rol del usuario
											}
									}
								else
								{
									JOptionPane.showMessageDialog(null, String.format("ERROR: CONTRASEÑA INCORRECTA", JOptionPane.ERROR_MESSAGE));
								}
				}});
			}
			
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	private void initDominio(){
		try{
			control = Controlador.dameControlador();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void crearDatos(Controlador c){
		try{

			Calendar calendario = Calendar.getInstance();
			String dia = Integer.toString(calendario.get(Calendar.DATE));
			String mes = Integer.toString(calendario.get(Calendar.MONTH+1));
			String anyo = Integer.toString(calendario.get(Calendar.YEAR));
			String hora = Integer.toString(calendario.get(Calendar.HOUR));
			String min = Integer.toString(calendario.get(Calendar.MINUTE));
			String seg = Integer.toString(calendario.get(Calendar.SECOND));
			String fecha = dia+"/"+mes+"/"+anyo+" "+hora+":"+min+":"+seg;

			Usuario u1 = new Usuario();
			u1.setApellidos("Sancho Monrabal");
			u1.setCorreo("josanmon@inf.upv.es");
			u1.setDireccion("Camino de Vera S/N");
			u1.setDni("22334569E");
			u1.setNombre("Pepe");
			u1.setPais("España");
			u1.setPassword("psm");
			u1.setPoblacion("Valencia");
			u1.setRol("Usuario");
			u1.setUsername("psm");
			
			if(c.addUsuario(u1))
				JOptionPane.showMessageDialog( null, "Se ha añadido el usuario a la BBDD", "Insertar usurario", JOptionPane.INFORMATION_MESSAGE );
			else
				JOptionPane.showMessageDialog( null, "No se ha podido añadir el usuario", "Inicialización de la BBDD", JOptionPane.INFORMATION_MESSAGE );
			
			
		}catch (DAOExcepcion e){
			
//			System.out.print("Dominio Excepcion: "+e);
			JOptionPane.showMessageDialog( null, "La base de datos ya está inicializada", "Advertencia", JOptionPane.WARNING_MESSAGE );
		}
	}
	private class BtnCargarBbddMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			crearDatos(control);
		}
	}
}
