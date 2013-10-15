package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window.Type;
import java.awt.BorderLayout;
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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AutomaticDiet
{
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private JFrame automatic_diet;
	JPanel panel_central;

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
		automatic_diet.setType(Type.UTILITY);
		automatic_diet.setVisible(true);
		automatic_diet.setTitle("Automatic Diet       --- free version ---");
		automatic_diet.setExtendedState(Frame.MAXIMIZED_BOTH);
		automatic_diet.setMaximumSize(new Dimension(1024, 768));
		automatic_diet.setMinimumSize(new Dimension(1024, 768));
		automatic_diet.setResizable(false);
		automatic_diet.setSize(new Dimension(1024, 768));
		automatic_diet.getContentPane().setSize(new Dimension(1024, 768));
		automatic_diet.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel_central = new Consultar_dieta_asignada();
		panel_central.setSize(800, 600);
		automatic_diet.getContentPane().add(panel_central, BorderLayout.SOUTH);
		
		JPanel panel_izquierda = new JPanel();
		automatic_diet.getContentPane().add(panel_izquierda, BorderLayout.WEST);
		
		JPanel panel_superior = new JPanel();
		panel_superior.setSize(1024, 168);
		automatic_diet.getContentPane().add(panel_superior, BorderLayout.NORTH);
		
		JLabel foto_perfil = new JLabel("");
		foto_perfil.setIcon(new ImageIcon(AutomaticDiet.class.getResource("/iconos/no_avatar.png")));
		
		JButton btn_perfil = new JButton("Patricio Letelier Torres");
		btn_perfil.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		btn_perfil.setHorizontalAlignment(SwingConstants.LEFT);
		btn_perfil.setContentAreaFilled(false);
		btn_perfil.setBorderPainted(false);
		
		JLabel titulo_panel_central = new JLabel("Dieta asignada");
		titulo_panel_central.setFont(new Font("Arial Black", Font.BOLD, 24));
		
		JLabel app_titulo = new JLabel("Automatic Diet");
		app_titulo.setForeground(Color.BLUE);
		app_titulo.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 30));
		GroupLayout gl_panel_superior = new GroupLayout(panel_superior);
		gl_panel_superior.setHorizontalGroup(
			gl_panel_superior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_superior.createSequentialGroup()
					.addContainerGap()
					.addComponent(foto_perfil, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_perfil, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(app_titulo, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(titulo_panel_central, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_superior.setVerticalGroup(
			gl_panel_superior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_superior.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_superior.createParallelGroup(Alignment.TRAILING)
						.addComponent(btn_perfil, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_superior.createParallelGroup(Alignment.BASELINE)
							.addComponent(titulo_panel_central, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addComponent(app_titulo, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
						.addComponent(foto_perfil, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		panel_superior.setLayout(gl_panel_superior);
		automatic_diet.setBounds(100, 100, 487, 331);
		automatic_diet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		automatic_diet.setJMenuBar(menuBar);
		
		JMenu mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);
		
		JMenuItem mntmIndicadores = new JMenuItem("Indicadores");
		mnUsuario.add(mntmIndicadores);
		
		JMenu mnDieta = new JMenu("Dieta");
		menuBar.add(mnDieta);
		
		JMenuItem mntmConsultarDietaAsignada = new JMenuItem("Consultar dieta asignada");
		mntmConsultarDietaAsignada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				automatic_diet.remove(panel_central);
				panel_central = new Consultar_dieta_asignada();
				automatic_diet.getContentPane().add(panel_central);
				automatic_diet.validate();
				automatic_diet.repaint();
			}
		});
		mnDieta.add(mntmConsultarDietaAsignada);
		
		JMenu mnPlato = new JMenu("Plato");
		menuBar.add(mnPlato);
		
		JMenuItem mntmElaboracinDePlato = new JMenuItem("Elaboraci\u00F3n de plato");
		mntmElaboracinDePlato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				automatic_diet.remove(panel_central);
				panel_central = new ElaboracionPlato(null);
				automatic_diet.getContentPane().add(panel_central);
				automatic_diet.validate();
				automatic_diet.repaint();
			}
		});
		mnPlato.add(mntmElaboracinDePlato);
		
		JMenuItem mntmIngredientes = new JMenuItem("Ingredientes");
		mntmIngredientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				automatic_diet.remove(panel_central);
				panel_central = new Ingredientes(null);
				automatic_diet.getContentPane().add(panel_central);
				automatic_diet.validate();
				automatic_diet.repaint();
			}
		});
		mnPlato.add(mntmIngredientes);
		
		JMenu mnForo = new JMenu("Foro");
		menuBar.add(mnForo);
		
		JMenuItem mntmBuscadorDeGrupos = new JMenuItem("Buscador de grupos");
		mnForo.add(mntmBuscadorDeGrupos);
	}
}
