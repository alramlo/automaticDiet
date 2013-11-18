package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class VerAnuncioEnForo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int anuncio;
	

	/**
	 * Create the dialog.
	 */
	public VerAnuncioEnForo(int i) {
		this.anuncio = i;
		
		setTitle("Anuncio");
		setBounds(100, 100, 683, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblCreadorDelPost = new JLabel("Creado por:");
				panel.add(lblCreadorDelPost);
			}
			{
				JLabel lblFecha = new JLabel("AutomaticDIET");
				panel.add(lblFecha);
			}
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setBorder(new LineBorder(new Color(64, 64, 64), 1, true));
			textArea.setEditable(false);
			if (anuncio == 0)
				textArea.setText("NORMAS");
			else if (anuncio == 1)
					textArea.setText("SUGERENCIAS");
				else 
					textArea.setText("CÓMO PREGUNTAR");
			contentPanel.add(textArea, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				buttonPane.add(btnCerrar);
			}
		}
	}

}
