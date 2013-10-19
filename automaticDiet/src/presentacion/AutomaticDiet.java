package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Component;
import javax.swing.JTabbedPane;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AutomaticDiet
{
	
	private JFrame automatic_diet;
	JPanel panel_central = new JPanel();

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		automatic_diet = new JFrame();
		automatic_diet.setAlwaysOnTop(true);
		automatic_diet.setBackground(new Color(50, 205, 50));
		automatic_diet.setVisible(true);
		automatic_diet.setTitle("Automatic Diet       --- free version ---");
		automatic_diet.setExtendedState(Frame.MAXIMIZED_BOTH);
		automatic_diet.setMaximumSize(new Dimension(1024, 760));
		automatic_diet.setMinimumSize(new Dimension(1024, 760));
		automatic_diet.setResizable(false);
		automatic_diet.setSize(new Dimension(1024, 760));
		automatic_diet.getContentPane().setSize(new Dimension(1024, 760));
		panel_central.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), "Bienvenido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		panel_central.setSize(800, 600);
		
		JPanel panel_superior = new JPanel();
		panel_superior.setOpaque(false);
		panel_superior.setPreferredSize(new Dimension(1020, 160));
		panel_superior.setSize(1024, 168);
		
		JLabel foto_perfil = new JLabel("");
		foto_perfil.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		foto_perfil.setIcon(new ImageIcon(AutomaticDiet.class.getResource("/iconos/no_avatar.png")));
		
		JButton btn_perfil = new JButton("Patricio Letelier Torres");
		btn_perfil.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		btn_perfil.setHorizontalAlignment(SwingConstants.LEFT);
		btn_perfil.setContentAreaFilled(false);
		btn_perfil.setBorderPainted(false);
		
		JLabel app_titulo = new JLabel("Automatic Diet");
		app_titulo.setHorizontalAlignment(SwingConstants.CENTER);
		app_titulo.setForeground(Color.BLUE);
		app_titulo.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 30));
		GroupLayout gl_panel_superior = new GroupLayout(panel_superior);
		gl_panel_superior.setHorizontalGroup(
			gl_panel_superior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_superior.createSequentialGroup()
					.addContainerGap()
					.addComponent(foto_perfil, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(btn_perfil, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(app_titulo, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_superior.setVerticalGroup(
			gl_panel_superior.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_superior.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_superior.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_superior.createParallelGroup(Alignment.TRAILING)
							.addComponent(btn_perfil, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel_superior.createSequentialGroup()
								.addComponent(app_titulo, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 10, GroupLayout.PREFERRED_SIZE)))
						.addComponent(foto_perfil, GroupLayout.PREFERRED_SIZE, 64, Short.MAX_VALUE))
					.addGap(10))
		);
		panel_superior.setLayout(gl_panel_superior);
		
		JTabbedPane menu = new JTabbedPane(JTabbedPane.LEFT);
		menu.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		menu.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		menu.setAlignmentX(Component.LEFT_ALIGNMENT);
		GroupLayout groupLayout = new GroupLayout(automatic_diet.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(0)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(menu, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(panel_central, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
						.addComponent(panel_superior, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(2))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_superior, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(menu, GroupLayout.PREFERRED_SIZE, 590, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_central, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)))
		);
		panel_central.setLayout(null);
		
		JPanel menu_usuario = new JPanel();
		menu_usuario.setOpaque(false);
		menu_usuario.setFont(new Font("Arial", Font.PLAIN, 14));
		menu_usuario.setAlignmentY(Component.TOP_ALIGNMENT);
		menu_usuario.setAlignmentX(Component.LEFT_ALIGNMENT);
		menu.addTab("Usuario", new ImageIcon(AutomaticDiet.class.getResource("/iconos/usuario.png")), menu_usuario, null);
		
		JPanel menu_dietas = new JPanel();
		menu_dietas.setOpaque(false);
		menu_dietas.setFont(new Font("Arial", Font.PLAIN, 14));
		menu.addTab("Dietas", new ImageIcon(AutomaticDiet.class.getResource("/iconos/dietas.png")), menu_dietas, null);
		
		JButton menu_dieta_asignada = new JButton("Dieta asignada");
		menu_dieta_asignada.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				cambiaPanel(new Consultar_dieta_asignada());
			}
		}
		);
		menu_dieta_asignada.setBorder(null);
		menu_dieta_asignada.setHorizontalTextPosition(SwingConstants.CENTER);
		menu_dieta_asignada.setMargin(new Insets(2, 2, 2, 2));
		menu_dieta_asignada.setContentAreaFilled(false);
		menu_dieta_asignada.setBorderPainted(false);
		menu_dieta_asignada.setFont(new Font("Arial", Font.PLAIN, 14));
		menu_dietas.add(menu_dieta_asignada);
		
		JPanel menu_platos = new JPanel();
		menu_platos.setOpaque(false);
		menu_platos.setFont(new Font("Arial", Font.PLAIN, 14));
		menu.addTab("Platos", new ImageIcon(AutomaticDiet.class.getResource("/iconos/platos.png")), menu_platos, null);
		
		JButton menu_elaboracion = new JButton("Elaboraci\u00F3n");
		menu_elaboracion.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				cambiaPanel(new ElaboracionPlato(null));
			}
		}
		);
		menu_elaboracion.setBorder(null);
		menu_elaboracion.setBorderPainted(false);
		menu_elaboracion.setContentAreaFilled(false);
		menu_elaboracion.setHorizontalTextPosition(SwingConstants.CENTER);
		menu_elaboracion.setFont(new Font("Arial", Font.PLAIN, 14));
		menu_platos.add(menu_elaboracion);
		
		JButton menu_ingredientes = new JButton("Ingredientes");
		menu_ingredientes.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Ingredientes(null);
			}
		}
		);
		menu_ingredientes.setBorder(null);
		menu_ingredientes.setBorderPainted(false);
		menu_ingredientes.setContentAreaFilled(false);
		menu_ingredientes.setHorizontalTextPosition(SwingConstants.CENTER);
		menu_ingredientes.setFont(new Font("Arial", Font.PLAIN, 14));
		menu_platos.add(menu_ingredientes);
		
		JPanel menu_foro = new JPanel();
		menu_foro.setOpaque(false);
		menu_foro.setFont(new Font("Arial", Font.PLAIN, 14));
		menu.addTab("Foro", new ImageIcon(AutomaticDiet.class.getResource("/iconos/foro.png")), menu_foro, null);
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
}
