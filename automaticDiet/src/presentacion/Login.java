package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import modelo.Usuario;
import servicio.Controlador;
import excepciones.DAOExcepcion;

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
			setBounds(100,100,511,364);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5,5,5,5));
			getContentPane().add(contentPanel,BorderLayout.CENTER);
			contentPanel.setLayout(null);
			{
				JLabel lblUser = new JLabel("Nombre de usuario:");
				lblUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblUser.setVerticalAlignment(SwingConstants.BOTTOM);
				lblUser.setHorizontalAlignment(SwingConstants.LEFT);
				lblUser.setBounds(29, 32, 219, 28);
				contentPanel.add(lblUser);
			}
			{
				tfUser = new JTextField();
				tfUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfUser.setBounds(29, 61, 220, 34);
				contentPanel.add(tfUser);
				tfUser.setColumns(10);

			}
			{
				JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
				lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblContrasea.setVerticalAlignment(SwingConstants.BOTTOM);
				lblContrasea.setHorizontalAlignment(SwingConstants.LEFT);
				lblContrasea.setBounds(29, 106, 219, 28);
				contentPanel.add(lblContrasea);
			}
			{
				tfPass = new JPasswordField();
				tfPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfPass.setBounds(29, 135, 220, 34);
				contentPanel.add(tfPass);
				tfPass.setColumns(10);

			}
			{
				JButton okButton=new JButton("");
				okButton.setVerifyInputWhenFocusTarget(false);
				okButton.setRequestFocusEnabled(false);
				okButton.setRolloverEnabled(false);
				okButton.setBorderPainted(false);
				okButton.setFocusable(false);
				okButton.setFocusTraversalKeysEnabled(false);
				okButton.setFocusPainted(false);
				okButton.setBorder(UIManager.getBorder("Button.border"));
				okButton.setBounds(278, 38, 188, 188);
				contentPanel.add(okButton);
				okButton.setMinimumSize(new Dimension(80, 28));
				okButton.setMaximumSize(new Dimension(100, 30));
				okButton.setIcon(new ImageIcon(Login.class.getResource("/iconos/logo_icon.png")));
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				okButton.setHorizontalTextPosition(SwingConstants.CENTER);
				okButton.setPreferredSize(new Dimension(80, 30));
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
				
				JButton btnEntrar = new JButton("Entrar");
				btnEntrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						control.logout();
						String user, pass;
						Usuario us=null;
						user = new String(tfUser.getText());
						pass = new String(tfPass.getPassword());
						tfPass.setText("");
						tfUser.setText("");
						
						try {	
								if(user.equals(""))
								{
									JOptionPane.showMessageDialog(null, String.format("ERROR: Introduzca un nombre de usuario", JOptionPane.ERROR_MESSAGE));
								}
								else if(pass.equals(""))
									{
										JOptionPane.showMessageDialog(null, String.format("ERROR: USUARIO INCORRECTO", JOptionPane.ERROR_MESSAGE));
									}
									else
										{
											listaUsuarios=control.getUsuarioPorUsername(user);
											if(listaUsuarios.size()>=1)
											{
												us=listaUsuarios.get(0);
											}

											if(us==null){
												JOptionPane.showMessageDialog(null, String.format("ERROR: USUARIO INCORRECTO", JOptionPane.ERROR_MESSAGE));
											}
											else if(us.getPassword().equals(pass))
													{
															control.setUsuarioActual(us);
															
//															JOptionPane.showMessageDialog(null, String.format("LOGIN CORRECTO", JOptionPane.INFORMATION_MESSAGE));

															if(control.getUsuarioActual().getRol().equals("Usuario"))
															{			
																AutomaticDiet ventanaUsuario = new AutomaticDiet();
																ventanaUsuario.getAutomatic_diet().setLocationRelativeTo(null);
																ventanaUsuario.getAutomatic_diet().setVisible(true);
																
																Bienvenida bienvenida = new Bienvenida();
																bienvenida.start();
																
																try {
																	finalize();
																	dispose();
																} catch (Throwable e) {
																	e.printStackTrace();
																}
															}
															else if(control.getUsuarioActual().getRol().equals("Administrador"))
															{
																AutomaticDiet ventanaUsuario = new AutomaticDiet();
																ventanaUsuario.getAutomatic_diet().setLocationRelativeTo(null);
																ventanaUsuario.getAutomatic_diet().setVisible(true);
																try {
																	finalize();
																	dispose();
																} catch (Throwable e) {
																	e.printStackTrace();
																}

															}
															else if(control.getUsuarioActual().getRol().equals("Colaborador"))									
															{
																AutomaticDiet ventanaUsuario = new AutomaticDiet();
																ventanaUsuario.getAutomatic_diet().setLocationRelativeTo(null);
																ventanaUsuario.getAutomatic_diet().setVisible(true);
																try {
																	finalize();
																	dispose();
																} catch (Throwable e) {
																	e.printStackTrace();
																}
															}
															else
															{
																System.out.println("En Login");
																JOptionPane.showMessageDialog(null, String.format("ERROR: ROL DEL USUARIO DESCONOCIDO", JOptionPane.ERROR_MESSAGE));
															}
													}
												else
												{
													JOptionPane.showMessageDialog(null, String.format("ERROR: USUARIO INCORRECTO", JOptionPane.ERROR_MESSAGE));
												}
										}							

						} catch (DAOExcepcion e) { e.printStackTrace();	}
					}
				});
				btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
				btnEntrar.setBackground(SystemColor.activeCaption);
				btnEntrar.setBounds(83, 192, 100, 34);
				contentPanel.add(btnEntrar);
				
				JPanel panel = new JPanel();
				panel.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel.setBounds(10, 254, 475, 61);
				contentPanel.add(panel);
				panel.setLayout(null);
				{
					JLabel lblNewLabel = new JLabel("\u00BFTodav\u00EDa no tienes cuenta?");
					lblNewLabel.setBounds(20, 16, 219, 34);
					panel.add(lblNewLabel);
					lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				}
				
				buttonHospital = new JButton("Registrarse");
				buttonHospital.setBounds(266, 16, 188, 34);
				panel.add(buttonHospital);
				buttonHospital.setBackground(SystemColor.inactiveCaption);
				buttonHospital.setFont(new Font("Tahoma", Font.PLAIN, 16));
				buttonHospital.setIcon(null);
				{
					JLabel label = new JLabel("");
					label.setHorizontalTextPosition(SwingConstants.CENTER);
					label.setHorizontalAlignment(SwingConstants.CENTER);
					label.setIcon(new ImageIcon(Login.class.getResource("/iconos/logo_icon.png")));
					label.setBounds(278, 38, 188, 188);
					contentPanel.add(label);
				}
				buttonHospital.addMouseListener(new BtnCargarBbddMouseListener());
				
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
							
					}
				});
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
	
//	public static void crearDatos(Controlador c){
//		try{
//
//			Calendar calendario = Calendar.getInstance();
//			String dia = Integer.toString(calendario.get(Calendar.DATE));
//			String mes = Integer.toString(calendario.get(Calendar.MONTH+1));
//			String anyo = Integer.toString(calendario.get(Calendar.YEAR));
//			String hora = Integer.toString(calendario.get(Calendar.HOUR));
//			String min = Integer.toString(calendario.get(Calendar.MINUTE));
//			String seg = Integer.toString(calendario.get(Calendar.SECOND));
//			String fecha = dia+"/"+mes+"/"+anyo+" "+hora+":"+min+":"+seg;
//
//			Usuario u1 = new Usuario();
//			u1.setApellidos("Sancho Monrabal");
//			u1.setCorreo("josanmon@inf.upv.es");
//			u1.setDireccion("Camino de Vera S/N");
//			u1.setDni("22334569E");
//			u1.setNombre("Pepe");
//			u1.setPais("España");
//			u1.setPassword("psm");
//			u1.setPoblacion("Valencia");
//			u1.setRol("Usuario");
//			u1.setUsername("psm");
//			
//			if(c.addUsuario(u1))
//				JOptionPane.showMessageDialog( null, "Se ha añadido el usuario a la BBDD", "Insertar usurario", JOptionPane.INFORMATION_MESSAGE );
//			else
//				JOptionPane.showMessageDialog( null, "No se ha podido añadir el usuario", "Insertar usurario", JOptionPane.INFORMATION_MESSAGE );
//			
//			
//		}catch (DAOExcepcion e){
//			
////			System.out.print("Dominio Excepcion: "+e);
//			JOptionPane.showMessageDialog( null, "La base de datos ya está inicializada", "Advertencia", JOptionPane.WARNING_MESSAGE );
//		}
//	}
	
	private class BtnCargarBbddMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			crearDatos(control);
		}
	}
	
	public static void crearDatos(Controlador c){
		Registrar_usuario ventanaRegistro = new Registrar_usuario();
		ventanaRegistro.setModal(true);
		ventanaRegistro.setLocationRelativeTo(null);
		ventanaRegistro.setVisible(true);
	}
}
