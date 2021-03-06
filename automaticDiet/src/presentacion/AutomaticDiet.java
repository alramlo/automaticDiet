package presentacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import servicio.Controlador;
import excepciones.DominioExcepcion;

public class AutomaticDiet
{
	
	private JFrame automatic_diet;

	JPanel panel_central = new JPanel();
	private Controlador control;
	private JLabel iconCabecera;
	private String rolUsuarioActual;
//	private static String [] rolUsuarioAdmin = {"Administrador","Usuario Registrado"};
	

//	/**
//	 * M�todo para lanzar la aplicaci�n
//	 **/
//	public static void main(String[] args)
//	{
//		EventQueue.invokeLater(new Runnable() 
//		{
//			public void run()
//			{
//				try
//				{
//					UIManager.setLookAndFeel(new NimbusLookAndFeel());
//					AutomaticDiet window = new AutomaticDiet();
//					window.automatic_diet.setLocationRelativeTo(null);
//					window.automatic_diet.setVisible(true);
//				}
//				catch (Exception e)
//				{
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Constructor de la aplicaci�n.
	 **/
	public AutomaticDiet()
	{
		try {
			control=Controlador.dameControlador();
		} catch (DominioExcepcion e3) {
			e3.printStackTrace();
		}
		rolUsuarioActual = control.getUsuarioActual().getRol();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		automatic_diet = new JFrame();
		automatic_diet.setIconImage(Toolkit.getDefaultToolkit().getImage(AutomaticDiet.class.getResource("/iconos/logo_icon.png")));
		automatic_diet.setTitle("Automatic Diet       --- free version ---");
		automatic_diet.setMaximumSize(new Dimension(1032, 768));
		automatic_diet.setMinimumSize(new Dimension(1032, 768));
		automatic_diet.setSize(new Dimension(1032, 768));
		automatic_diet.getContentPane().setSize(new Dimension(1024, 760));
		
		panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)), "Bienvenido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_central.setSize(800, 600);
		
		JPanel panel_superior = new JPanel();
		panel_superior.setOpaque(false);
		panel_superior.setPreferredSize(new Dimension(1020, 160));
		panel_superior.setSize(1024, 168);
		
		iconCabecera = new JLabel("");
		iconCabecera.setSize(new Dimension(110, 110));
		iconCabecera.setHorizontalTextPosition(SwingConstants.CENTER);
		iconCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon imgIcon = new ImageIcon(AutomaticDiet.class.getResource("/iconos/logo_icon.png"));
		Image imgEscalada = imgIcon.getImage().getScaledInstance(iconCabecera.getWidth(),iconCabecera.getHeight(), Image.SCALE_SMOOTH);
	    Icon iconoEscalado = new ImageIcon(imgEscalada);
	    iconCabecera.setIcon(iconoEscalado);
		
		JLabel lblAutomaticDiet = new JLabel("Automatic Diet");
		lblAutomaticDiet.setFont(new Font("Arial Black", Font.PLAIN, 74));
		lblAutomaticDiet.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAutomaticDiet.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout gl_panel_superior = new GroupLayout(panel_superior);
		gl_panel_superior.setHorizontalGroup(
			gl_panel_superior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_superior.createSequentialGroup()
					.addContainerGap()
					.addComponent(iconCabecera, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblAutomaticDiet, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE))
		);
		gl_panel_superior.setVerticalGroup(
			gl_panel_superior.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_superior.createSequentialGroup()
					.addGroup(gl_panel_superior.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_superior.createSequentialGroup()
							.addGap(11)
							.addComponent(lblAutomaticDiet)
							.addGap(0, 0, Short.MAX_VALUE))
						.addComponent(iconCabecera, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
					.addGap(0))
		);
		panel_superior.setLayout(gl_panel_superior);
		
		JPanel panel_perfil = new JPanel();
		panel_perfil.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 2, true), control.getUsuarioActual().getUsername(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label = new JLabel("");
		//label.setIcon(new ImageIcon(AutomaticDiet.class.getResource("/iconos/userIcon.jpg")));
		try{
		label.setIcon(new ImageIcon(control.getUsuarioActual().getImagen()));
		}
		catch(NullPointerException e){
			System.err.println(e);
		}
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_panel_perfil = new GroupLayout(panel_perfil);
		gl_panel_perfil.setHorizontalGroup(
			gl_panel_perfil.createParallelGroup(Alignment.TRAILING)
				.addComponent(label, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
		);
		gl_panel_perfil.setVerticalGroup(
			gl_panel_perfil.createParallelGroup(Alignment.LEADING)
				.addComponent(label, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
		);
		panel_perfil.setLayout(gl_panel_perfil);
		
		JPanel panel_izquierda = new JPanel();
		GroupLayout groupLayout = new GroupLayout(automatic_diet.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_izquierda, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_central, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(panel_superior, GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_perfil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(1)))
					.addGap(2))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_perfil, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_superior, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(panel_central, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
							.addGap(4))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_izquierda, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
							.addContainerGap())))
		);
		panel_central.setLayout(new BoxLayout(panel_central, BoxLayout.X_AXIS));
		panel_izquierda.setLayout(null);
		

		
		final JTabbedPane tabsMenuUsuario = new JTabbedPane(JTabbedPane.LEFT);
		tabsMenuUsuario.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabsMenuUsuario.setBounds(0, 44, 190, 237);
		tabsMenuUsuario.setVisible(false);
		panel_izquierda.add(tabsMenuUsuario);
		tabsMenuUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		tabsMenuUsuario.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		tabsMenuUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		final JTabbedPane tabsMenuAdministrador = new JTabbedPane(JTabbedPane.LEFT);
		tabsMenuAdministrador.setBounds(0, 44, 190, 208);
		tabsMenuAdministrador.setVisible(false);
		panel_izquierda.add(tabsMenuAdministrador);
		tabsMenuAdministrador.setFont(new Font("Arial", Font.PLAIN, 12));
		tabsMenuAdministrador.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		tabsMenuAdministrador.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabsMenuAdministrador.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		
		
		
//		@SuppressWarnings({ "unchecked", "rawtypes" })
//		final JComboBox comboBox = new JComboBox(rolUsuarioAdmin);
//		comboBox.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent arg0) {
//				System.out.println(comboBox.getSelectedIndex()+" Arg0: "+arg0);
//				
//				if(comboBox.getSelectedIndex()==0)
//				{
//					tabsMenuUsuario.setVisible(false);
//					tabsMenuAdministrador.setVisible(true);
//					_limpiarMenuCentral();
//				}
//				
//				if(comboBox.getSelectedIndex()==1)
//				{
//					tabsMenuAdministrador.setVisible(false);
//					tabsMenuUsuario.setVisible(true);
//					//_limpiarMenuCentral();
//					 cambiaPanel(new Indicadores_personales());
//				}
//			}
//		});
//		comboBox.setSelectedIndex(-1);
//		comboBox.setBounds(0, 0, 190, 26);
//		panel_izquierda.add(comboBox);
		
		
		
		

		if(rolUsuarioActual.equals("Administrador") || rolUsuarioActual.equals("Colaborador"))
		{
			tabsMenuUsuario.setVisible(false);
			tabsMenuAdministrador.setVisible(true);
			//_limpiarMenuCentral();
			GestionPlatos gp = new GestionPlatos(control.getUsuarioActual());
			gp.poblar();
			cambiaPanel(gp);
		}		
		else if(rolUsuarioActual.equals("Usuario"))
		{
			tabsMenuAdministrador.setVisible(false);
			tabsMenuUsuario.setVisible(true);
			//_limpiarMenuCentral();
			 cambiaPanel(new Indicadores_personales());
		}
		else
		{
			System.out.println("En Pantalla Principal");
			JOptionPane.showMessageDialog(null, String.format("ERROR: ROL DEL USUARIO DESCONOCIDO", JOptionPane.ERROR_MESSAGE));
		}

		
		
		
		
		
		
		
		JPanel menu_admin = new JPanel();
		menu_admin.setOpaque(false);
		menu_admin.setFont(new Font("Arial", Font.PLAIN, 14));
		menu_admin.setAlignmentY(Component.TOP_ALIGNMENT);
		menu_admin.setAlignmentX(Component.LEFT_ALIGNMENT);
		tabsMenuAdministrador.addTab("Administrador", null, menu_admin, null);
		
		JButton menu_gestionPlatos = new JButton("<html><p>Gesti�n</p><p>de platos</p></html>");
		menu_gestionPlatos.setSelected(true);
		menu_gestionPlatos.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)), "Gestion de Platos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GestionPlatos gp = new GestionPlatos(control.getUsuarioActual());
				gp.poblar();
				cambiaPanel(gp);
			}
		});
		menu_gestionPlatos.setOpaque(false);
		menu_gestionPlatos.setMargin(new Insets(2, 2, 2, 2));
		menu_gestionPlatos.setHorizontalTextPosition(SwingConstants.CENTER);
		menu_gestionPlatos.setFont(new Font("Arial", Font.PLAIN, 14));
		menu_gestionPlatos.setBorderPainted(false);
		menu_gestionPlatos.setBorder(null);
		
//		JButton menu_gestionGrupos = new JButton("<html><p>Gesti�n</p><p>grupos</p></html>");
//		menu_gestionGrupos.setSelected(true);
//		menu_gestionGrupos.setEnabled(false);
//		menu_gestionGrupos.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent arg0)
//			{
//				panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)), "Gestion de Grupos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//				GestionGruposPorAdmin panelGestionGrupos = new GestionGruposPorAdmin();
//				panelGestionGrupos.cargaGrupos(control.getUsuarioActual());
////				panelGestionGrupos.cargaDenuncias();
//				cambiaPanel(panelGestionGrupos);
//			}
//		});
//		menu_gestionGrupos.setOpaque(false);
//		menu_gestionGrupos.setMargin(new Insets(2, 2, 2, 2));
//		menu_gestionGrupos.setHorizontalTextPosition(SwingConstants.CENTER);
//		menu_gestionGrupos.setFont(new Font("Arial", Font.PLAIN, 14));
//		menu_gestionGrupos.setBorderPainted(false);
//		menu_gestionGrupos.setBorder(null);
		
		GroupLayout gl_menu_admin = new GroupLayout(menu_admin);
		gl_menu_admin.setHorizontalGroup(
			gl_menu_admin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu_admin.createSequentialGroup()
					.addGap(0)
					//.addComponent(menu_gestionGrupos, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addComponent(menu_gestionPlatos, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
		));
		gl_menu_admin.setVerticalGroup(
			gl_menu_admin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu_admin.createSequentialGroup()
					.addGap(44)
		//			.addComponent(menu_gestionGrupos, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addComponent(menu_gestionPlatos, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
		));
		menu_admin.setLayout(gl_menu_admin);
		
		
		
		JPanel menu_usuario = new JPanel();
		menu_usuario.setOpaque(false);
		menu_usuario.setFont(new Font("Arial", Font.PLAIN, 14));
		menu_usuario.setAlignmentY(Component.TOP_ALIGNMENT);
		menu_usuario.setAlignmentX(Component.LEFT_ALIGNMENT);
		tabsMenuUsuario.addTab("Mis dietas", new ImageIcon(AutomaticDiet.class.getResource("/iconos/usuario.png")), menu_usuario, null);
		
				JButton menu_indicadores = new JButton("<html><p>Indicadores</p><p>personales</p></html>");
				menu_indicadores.setBounds(0, 5, 73, 44);
				menu_indicadores.setSelected(true);
				menu_indicadores.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)), "Indicadores personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						cambiaPanel(new Indicadores_personales());
					}
				});
				menu_indicadores.setOpaque(false);
				menu_indicadores.setMargin(new Insets(2, 2, 2, 2));
				menu_indicadores.setHorizontalTextPosition(SwingConstants.CENTER);
				menu_indicadores.setFont(new Font("Arial", Font.PLAIN, 12));
				menu_indicadores.setBorderPainted(false);
				menu_indicadores.setBorder(null);
				
				JButton menu_dieta_asignada2 = new JButton("<html>Consultar<br>            dieta</html>");
				menu_dieta_asignada2.setBounds(0, 60, 73, 44);
				menu_dieta_asignada2.setSelected(true);
				menu_dieta_asignada2.setOpaque(false);
				menu_dieta_asignada2.setMargin(new Insets(2, 2, 2, 2));
				menu_dieta_asignada2.setHorizontalTextPosition(SwingConstants.CENTER);
				menu_dieta_asignada2.setFont(new Font("Arial", Font.PLAIN, 14));
				menu_dieta_asignada2.setBorderPainted(false);
				menu_dieta_asignada2.setBorder(null);
				
				JButton menu_elaboracion2 = new JButton("Platos");
				menu_elaboracion2.setBounds(0, 115, 73, 44);
				menu_elaboracion2.setSelected(true);
				menu_elaboracion2.setOpaque(false);
				menu_elaboracion2.setMargin(new Insets(2, 2, 2, 2));
				menu_elaboracion2.setHorizontalTextPosition(SwingConstants.CENTER);
				menu_elaboracion2.setFont(new Font("Arial", Font.PLAIN, 14));
				menu_elaboracion2.setBorderPainted(false);
				menu_elaboracion2.setBorder(null);
				menu_usuario.setLayout(null);
				menu_usuario.add(menu_indicadores);
				menu_usuario.add(menu_dieta_asignada2);
				menu_usuario.add(menu_elaboracion2);
				
				JButton gestionDietas = new JButton("Gesti\u00F3n");
				gestionDietas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)), "Gesti�n de mis dietas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						cambiaPanel(new GestionDietas());
						
					}
				});
				gestionDietas.setSelected(true);
				gestionDietas.setOpaque(false);
				gestionDietas.setMargin(new Insets(2, 2, 2, 2));
				gestionDietas.setHorizontalTextPosition(SwingConstants.CENTER);
				gestionDietas.setFont(new Font("Arial", Font.PLAIN, 14));
				gestionDietas.setBorderPainted(false);
				gestionDietas.setBorder(null);
				gestionDietas.setBounds(0, 170, 73, 44);
				menu_usuario.add(gestionDietas);
				menu_dieta_asignada2.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)), "Dieta Asignada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						cambiaPanel(new Consultar_dieta_asignada(control));
					}
				}
				);
				menu_elaboracion2.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)), "Elaboracion de Platos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						cambiaPanel(new ElaboracionPlato());
					}
				}
				);
		
		JButton logoutButton = new JButton("LOGOUT");
		if(control.getUsuarioActual()==null){
			logoutButton.setEnabled(false);
		}
		else{
			logoutButton.setEnabled(true);
		}
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.logout();
				new Login().setVisible(true);
				automatic_diet.dispose();
			}
		});
		logoutButton.setIcon(new ImageIcon(AutomaticDiet.class.getResource("/iconos/gnome_logout.png")));
		logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
		logoutButton.setBounds(10, 559, 180, 32);
		panel_izquierda.add(logoutButton);
		automatic_diet.getContentPane().setLayout(groupLayout);
		automatic_diet.setBounds(100, 100, 487, 331);
		automatic_diet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 tabsMenuUsuario.addChangeListener(new ChangeListener(){
		      public void stateChanged(ChangeEvent e){
		        switch(tabsMenuUsuario.getSelectedIndex()){
		        case 0://usuario
		        	break;
//		        case 1://dietas
//		        	panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)), "Dieta Asignada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//					cambiaPanel(new Consultar_dieta_asignada(control));
//		        	break;
//		        case 2://platos
//		        	panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)), "Elaboracion de Platos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//					cambiaPanel(new ElaboracionPlato());
//		        	break;
		        case 1://foro
		        	ForoBasico fb = new ForoBasico();
					fb.cargaForos();
					fb.cargaAnuncios();
					panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)), "Temas del foro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
					cambiaPanel(fb);
		        	break;
		        }
		      }
		    });
	}
	
	public JFrame getAutomatic_diet() {
		return automatic_diet;
	}
	
	private void cambiaPanel(JPanel nuevo)
	{
		panel_central.removeAll();
		panel_central.add(nuevo);
		panel_central.revalidate();
		panel_central.repaint();
	}
	
	private void _limpiarMenuCentral()
	{
		panel_central.removeAll();
		panel_central.revalidate();
		panel_central.repaint();
	}
}
