package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import servicio.Controlador;
import excepciones.DominioExcepcion;

public class Bienvenida extends Thread {

	private static Controlador control;
	private BienvenidaJDialog bienvenida;
	
	
	public void run()
	{	
		
		try {
			bienvenida = new BienvenidaJDialog();
			bienvenida.setLocationRelativeTo(null);
			bienvenida.setVisible(true);
			bienvenida.setModal(true);
			
			Thread.sleep(4000);
			
			bienvenida.setVisible(false);
			bienvenida.dispose();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	class BienvenidaJDialog extends JDialog {
		
		private static final long serialVersionUID = 1L;
		private final JPanel contentPanel = new JPanel();
		

		/**
		 * Create the dialog.
		 * @throws DominioExcepcion 
		 * @throws InterruptedException 
		 */
		public BienvenidaJDialog() throws DominioExcepcion, InterruptedException {
			control = Controlador.dameControlador();

			setIconImage(Toolkit.getDefaultToolkit().getImage(BienvenidaJDialog.class.getResource("/iconos/logo_icon.png")));
			setTitle("AutomaticDiet - Bienvenida");
			control = Controlador.dameControlador();
			setBounds(100, 100, 484, 412);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			
			JLabel lblHola = new JLabel("Hola "+control.getUsuarioActual().getNombre()+" "+control.getUsuarioActual().getApellidos());
			lblHola.setFont(new Font("Arial", Font.PLAIN, 23));
			lblHola.setHorizontalTextPosition(SwingConstants.CENTER);
			lblHola.setHorizontalAlignment(SwingConstants.CENTER);
			lblHola.setBounds(0, 9, 468, 42);
			contentPanel.add(lblHola);
			
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(10, 62, 448, 301);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel label = new JLabel("Bienvenido a");
			label.setHorizontalTextPosition(SwingConstants.CENTER);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("Tahoma", Font.PLAIN, 51));
			label.setBounds(3, 0, 441, 80);
			panel.add(label);
			
			JLabel label_1 = new JLabel("");
			label_1.setIcon(new ImageIcon(BienvenidaJDialog.class.getResource("/iconos/logo_icon.png")));
			label_1.setHorizontalTextPosition(SwingConstants.CENTER);
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 51));
			label_1.setBounds(3, 86, 441, 127);
			panel.add(label_1);
			
			JLabel label_2 = new JLabel("AutomaticDiet");
			label_2.setHorizontalTextPosition(SwingConstants.CENTER);
			label_2.setHorizontalAlignment(SwingConstants.CENTER);
			label_2.setFont(new Font("Tahoma", Font.PLAIN, 51));
			label_2.setBounds(3, 216, 441, 80);
			panel.add(label_2);

		}
		
	}
}
