package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window.Type;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class AutomaticDiet
{

	private JFrame automatic_diet;

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
		automatic_diet.setMaximumSize(new Dimension(1280, 720));
		automatic_diet.setMinimumSize(new Dimension(1280, 720));
		automatic_diet.setResizable(false);
		automatic_diet.setSize(new Dimension(1366, 768));
		automatic_diet.getContentPane().setSize(new Dimension(1366, 728));
		automatic_diet.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_central = new Consultar_dieta_asignada();
		automatic_diet.getContentPane().add(panel_central, BorderLayout.CENTER);
		
		JPanel panel_izquierda = new JPanel();
		automatic_diet.getContentPane().add(panel_izquierda, BorderLayout.WEST);
		automatic_diet.setBounds(100, 100, 487, 331);
		automatic_diet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
