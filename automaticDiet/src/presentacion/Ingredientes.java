package presentacion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JTextPane;

public class Ingredientes extends JPanel {

	/**
	 * Create the panel.
	 */
	public Ingredientes() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOMBRE INGREDIENTE");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(31, 22, 161, 23);
		add(lblNewLabel);
		
		JButton btnInfoNutricional = new JButton("Info nutricional");
		btnInfoNutricional.setFont(new Font("Arial", Font.BOLD, 11));
		btnInfoNutricional.setBounds(207, 23, 120, 23);
		add(btnInfoNutricional);
		
		JButton btnElaboracion = new JButton("Elaboracion del plato");
		btnElaboracion.setFont(new Font("Arial", Font.BOLD, 11));
		btnElaboracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnElaboracion.setBounds(329, 22, 145, 23);
		add(btnElaboracion);
		
		JList list = new JList();
		list.setBounds(31, 284, 161, -201);
		add(list);
		
		JTextPane textPaneInfoAlim = new JTextPane();
		textPaneInfoAlim.setBounds(207, 78, 267, 215);
		add(textPaneInfoAlim);
		
		JButton btnOrigenAli = new JButton("Origen del alimento");
		btnOrigenAli.setFont(new Font("Arial", Font.BOLD, 11));
		btnOrigenAli.setBounds(49, 375, 143, 23);
		add(btnOrigenAli);
		
		JButton btnAnyadirLista = new JButton("A\u00F1adir a la lista de la compra");
		btnAnyadirLista.setFont(new Font("Arial", Font.BOLD, 11));
		btnAnyadirLista.setBounds(207, 375, 189, 23);
		add(btnAnyadirLista);
		

	}
}
