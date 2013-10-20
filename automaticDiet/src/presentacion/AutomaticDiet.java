package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Dimension;

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

import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;
import servicio.Controlador;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;

public class AutomaticDiet
{
	
	private JFrame automatic_diet;
	JPanel panel_central = new JPanel();
	private Controlador control;

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
		automatic_diet.setTitle("Automatic Diet       --- free version ---");
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
		
		JLabel app_titulo = new JLabel("");
		app_titulo.setIcon(new ImageIcon(AutomaticDiet.class.getResource("/iconos/logo.png")));
		app_titulo.setHorizontalAlignment(SwingConstants.CENTER);
		app_titulo.setForeground(Color.BLUE);
		app_titulo.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 30));
		GroupLayout gl_panel_superior = new GroupLayout(panel_superior);
		gl_panel_superior.setHorizontalGroup(
			gl_panel_superior.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_superior.createSequentialGroup()
					.addContainerGap()
					.addComponent(app_titulo, GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_superior.setVerticalGroup(
			gl_panel_superior.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_superior.createSequentialGroup()
					.addContainerGap(10, Short.MAX_VALUE)
					.addComponent(app_titulo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(0))
		);
		panel_superior.setLayout(gl_panel_superior);
		
		JTabbedPane menu = new JTabbedPane(JTabbedPane.LEFT);
		menu.setFont(new Font("Arial", Font.PLAIN, 12));
		menu.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		menu.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		menu.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JPanel panel_perfil = new JPanel();
		panel_perfil.setBorder(new TitledBorder(new LineBorder(new Color(100, 100, 100), 2, true), "Patricio Letelier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(automatic_diet.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(panel_superior, GroupLayout.PREFERRED_SIZE, 1013, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_perfil, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
								.addComponent(menu, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
							.addGap(4)
							.addComponent(panel_central, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)))
					.addGap(4))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addComponent(panel_superior, GroupLayout.PREFERRED_SIZE, 115, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_perfil, GroupLayout.PREFERRED_SIZE, 184, Short.MAX_VALUE)
							.addGap(1)
							.addComponent(menu, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(panel_central, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE))
					.addGap(10))
		);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AutomaticDiet.class.getResource("/iconos/no_avatar.png")));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_panel_perfil = new GroupLayout(panel_perfil);
		gl_panel_perfil.setHorizontalGroup(
			gl_panel_perfil.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_perfil.createSequentialGroup()
					.addGap(21)
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
					.addGap(19))
		);
		gl_panel_perfil.setVerticalGroup(
			gl_panel_perfil.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_perfil.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(19))
		);
		panel_perfil.setLayout(gl_panel_perfil);
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
		
		JButton menu_dieta_asignada = new JButton("<html><p>Dieta</p><p>asignada</p></html>");
		menu_dieta_asignada.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				cambiaPanel(new Consultar_dieta_asignada(control));
			}
		}
		);
		menu_dieta_asignada.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
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
				cambiaPanel(new ElaboracionPlato());
			}
		}
		);
		menu_elaboracion.setBorder(null);
		menu_elaboracion.setBorderPainted(false);
		menu_elaboracion.setContentAreaFilled(false);
		menu_elaboracion.setHorizontalTextPosition(SwingConstants.CENTER);
		menu_elaboracion.setFont(new Font("Arial", Font.PLAIN, 14));
		menu_platos.add(menu_elaboracion);
		
		
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
