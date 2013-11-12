package presentacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import servicio.Controlador;
import excepciones.DominioExcepcion;

import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.BoxLayout;

public class AutomaticDiet
{
	
	private JFrame automatic_diet;
	JPanel panel_central = new JPanel();
	private Controlador control;
	private JLabel iconCabecera;
	private static String [] rolUsuarioAdmin = {"Administrador","Usuario Registrado"};
	

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
					AutomaticDiet window = new AutomaticDiet();
					window.automatic_diet.setLocationRelativeTo(null);
					window.automatic_diet.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor de la aplicación.
	 **/
	public AutomaticDiet()
	{
		try {
			control=Controlador.dameControlador();
		} catch (DominioExcepcion e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
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
		automatic_diet.setMaximumSize(new Dimension(1024, 760));
		automatic_diet.setMinimumSize(new Dimension(1024, 760));
		automatic_diet.setSize(new Dimension(1024, 760));
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
		panel_perfil.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 2, true), "Scarlett Johansson", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AutomaticDiet.class.getResource("/iconos/perfil_scarlet.jpg")));
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
		tabsMenuUsuario.setBounds(0, 44, 190, 208);
		tabsMenuUsuario.setVisible(false);
		panel_izquierda.add(tabsMenuUsuario);
		tabsMenuUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
		tabsMenuUsuario.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		tabsMenuUsuario.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabsMenuUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		final JTabbedPane tabsMenuAdministrador = new JTabbedPane(JTabbedPane.TOP);
		tabsMenuAdministrador.setBounds(0, 44, 190, 208);
		tabsMenuAdministrador.setVisible(false);
		panel_izquierda.add(tabsMenuAdministrador);
		
		
		final JComboBox comboBox = new JComboBox(rolUsuarioAdmin);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println(comboBox.getSelectedIndex()+" Arg0: "+arg0);
				
				if(comboBox.getSelectedIndex()==0)
				{
					tabsMenuUsuario.setVisible(false);
					tabsMenuAdministrador.setVisible(true);
					_limpiarMenuCentral();
				}
				
				if(comboBox.getSelectedIndex()==1)
				{
					tabsMenuAdministrador.setVisible(false);
					tabsMenuUsuario.setVisible(true);
					_limpiarMenuCentral();
				}
			}
		});
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(0, 0, 190, 26);
		panel_izquierda.add(comboBox);
		
		
		
		JPanel menu_usuario = new JPanel();
		menu_usuario.setOpaque(false);
		menu_usuario.setFont(new Font("Arial", Font.PLAIN, 14));
		menu_usuario.setAlignmentY(Component.TOP_ALIGNMENT);
		menu_usuario.setAlignmentX(Component.LEFT_ALIGNMENT);
		tabsMenuUsuario.addTab("Usuario", new ImageIcon(AutomaticDiet.class.getResource("/iconos/usuario.png")), menu_usuario, null);

		JButton menu_indicadores = new JButton("<html><p>Indicadores</p><p>personales</p></html>");
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
		menu_indicadores.setFont(new Font("Arial", Font.PLAIN, 14));
		menu_indicadores.setBorderPainted(false);
		menu_indicadores.setBorder(null);
		GroupLayout gl_menu_usuario = new GroupLayout(menu_usuario);
		gl_menu_usuario.setHorizontalGroup(
			gl_menu_usuario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu_usuario.createSequentialGroup()
					.addGap(1)
					.addComponent(menu_indicadores, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
		);
		gl_menu_usuario.setVerticalGroup(
			gl_menu_usuario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu_usuario.createSequentialGroup()
					.addGap(5)
					.addComponent(menu_indicadores, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
		);
		menu_usuario.setLayout(gl_menu_usuario);
		
		JPanel menu_admin = new JPanel();
		menu_admin.setOpaque(false);
		menu_admin.setFont(new Font("Arial", Font.PLAIN, 14));
		menu_admin.setAlignmentY(Component.TOP_ALIGNMENT);
		menu_admin.setAlignmentX(Component.LEFT_ALIGNMENT);
		tabsMenuAdministrador.addTab("Administrador", new ImageIcon(AutomaticDiet.class.getResource("/iconos/usuario.png")), menu_admin, null);
		
		JButton menu_gestionGrupos = new JButton("<html><p>Gestión</p><p>grupos</p></html>");
		menu_gestionGrupos.setSelected(true);
		menu_gestionGrupos.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)), "Gestion de Platos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				cambiaPanel(new GestionPlatos());
			}
		});
		menu_gestionGrupos.setOpaque(false);
		menu_gestionGrupos.setMargin(new Insets(2, 2, 2, 2));
		menu_gestionGrupos.setHorizontalTextPosition(SwingConstants.CENTER);
		menu_gestionGrupos.setFont(new Font("Arial", Font.PLAIN, 14));
		menu_gestionGrupos.setBorderPainted(false);
		menu_gestionGrupos.setBorder(null);
		GroupLayout gl_menu_admin = new GroupLayout(menu_admin);
		gl_menu_admin.setHorizontalGroup(
			gl_menu_admin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu_admin.createSequentialGroup()
					.addGap(1)
					.addComponent(menu_gestionGrupos, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
		);
		gl_menu_admin.setVerticalGroup(
			gl_menu_admin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu_admin.createSequentialGroup()
					.addGap(5)
					.addComponent(menu_gestionGrupos, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
		);
		menu_admin.setLayout(gl_menu_admin);
			
	
		JPanel menu_dietas = new JPanel();
		menu_dietas.setOpaque(false);
		menu_dietas.setFont(new Font("Arial", Font.PLAIN, 14));
		tabsMenuUsuario.addTab("Dietas", new ImageIcon(AutomaticDiet.class.getResource("/iconos/dietas.png")), menu_dietas, null);		
		
		JButton menu_dieta_asignada = new JButton("<html><p>Dieta</p><p>asignada</p></html>");
		menu_dieta_asignada.setSelected(true);
		menu_dieta_asignada.setOpaque(false);
		menu_dieta_asignada.setBorderPainted(false);
		menu_dieta_asignada.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)), "Dieta Asignada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				cambiaPanel(new Consultar_dieta_asignada(control));
			}
		}
		);
		menu_dieta_asignada.setBorder(null);
		menu_dieta_asignada.setHorizontalTextPosition(SwingConstants.CENTER);
		menu_dieta_asignada.setMargin(new Insets(2, 2, 2, 2));
		menu_dieta_asignada.setFont(new Font("Arial", Font.PLAIN, 14));
		GroupLayout gl_menu_dietas = new GroupLayout(menu_dietas);
		gl_menu_dietas.setHorizontalGroup(
			gl_menu_dietas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu_dietas.createSequentialGroup()
					.addGap(1)
					.addComponent(menu_dieta_asignada, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
		);
		gl_menu_dietas.setVerticalGroup(
			gl_menu_dietas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu_dietas.createSequentialGroup()
					.addGap(5)
					.addComponent(menu_dieta_asignada, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
		);
		menu_dietas.setLayout(gl_menu_dietas);
		
		
//		JPanel menu_gestionPlatos = new JPanel();
//		menu_gestionPlatos.setOpaque(false);
//		menu_gestionPlatos.setFont(new Font("Arial", Font.PLAIN, 14));
//		tabsMenuAdministrador.addTab("Gestión Platos", new ImageIcon(AutomaticDiet.class.getResource("/iconos/dietas.png")), menu_gestionPlatos, null);
		
		
		JPanel menu_platos = new JPanel();
		menu_platos.setOpaque(false);
		menu_platos.setFont(new Font("Arial", Font.PLAIN, 14));
		tabsMenuUsuario.addTab("Platos", new ImageIcon(AutomaticDiet.class.getResource("/iconos/platos.png")), menu_platos, null);
		
		JButton menu_elaboracion = new JButton("Elaboración");
		menu_elaboracion.setSelected(true);
		menu_elaboracion.setOpaque(false);
		menu_elaboracion.setBorderPainted(false);
		menu_elaboracion.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)), "Elaboracion de Platos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				cambiaPanel(new ElaboracionPlato());
			}
		}
		);
		menu_elaboracion.setBorder(null);
		menu_elaboracion.setHorizontalTextPosition(SwingConstants.CENTER);
		menu_elaboracion.setFont(new Font("Arial", Font.PLAIN, 14));
		GroupLayout gl_menu_platos = new GroupLayout(menu_platos);
		gl_menu_platos.setHorizontalGroup(
			gl_menu_platos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu_platos.createSequentialGroup()
					.addGap(1)
					.addComponent(menu_elaboracion, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
		);
		gl_menu_platos.setVerticalGroup(
			gl_menu_platos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu_platos.createSequentialGroup()
					.addGap(5)
					.addComponent(menu_elaboracion, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
		);
		menu_platos.setLayout(gl_menu_platos);
		
		
		JPanel menu_foro = new JPanel();
		menu_foro.setOpaque(false);
		menu_foro.setFont(new Font("Arial", Font.PLAIN, 14));
		tabsMenuUsuario.addTab("Foro", new ImageIcon(AutomaticDiet.class.getResource("/iconos/foro.png")), menu_foro, null);
		
		JButton menu_buscador_grupos = new JButton("<html><p>Buscador</p><p>de grupos</p></html>");
		menu_buscador_grupos.setSelected(true);
		menu_buscador_grupos.setOpaque(false);
		menu_buscador_grupos.setMargin(new Insets(2, 2, 2, 2));
		menu_buscador_grupos.setHorizontalTextPosition(SwingConstants.CENTER);
		menu_buscador_grupos.setFont(new Font("Arial", Font.PLAIN, 14));
		menu_buscador_grupos.setBorderPainted(false);
		menu_buscador_grupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 128, 128)), "Buscador de Grupos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				cambiaPanel(new BuscadorGrupo());
			}
		});
		menu_buscador_grupos.setBorder(null);
		GroupLayout gl_menu_foro = new GroupLayout(menu_foro);
		gl_menu_foro.setHorizontalGroup(
			gl_menu_foro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu_foro.createSequentialGroup()
					.addGap(1)
					.addComponent(menu_buscador_grupos, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
		);
		gl_menu_foro.setVerticalGroup(
			gl_menu_foro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu_foro.createSequentialGroup()
					.addGap(5)
					.addComponent(menu_buscador_grupos, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
		);
		menu_foro.setLayout(gl_menu_foro);
		automatic_diet.getContentPane().setLayout(groupLayout);
		automatic_diet.setBounds(100, 100, 487, 331);
		automatic_diet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
