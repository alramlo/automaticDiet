package presentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Ingredientes extends JPanel {

	/**
	 * Create the panel.
	 */
	public Ingredientes(Object ingredientesVuelta) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOMBRE INGREDIENTE");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(21, 60, 197, 23);
		add(lblNewLabel);
		
		JButton btnInfoNutricional = new JButton("Info nutricional");
		btnInfoNutricional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInfoNutricional.setFont(new Font("Arial", Font.BOLD, 16));
		btnInfoNutricional.setBounds(355, 60, 197, 23);
		add(btnInfoNutricional);
		
		JButton btnElaboracion = new JButton("Elaboracion del plato");
		btnElaboracion.setFont(new Font("Arial", Font.BOLD, 16));
		btnElaboracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnElaboracion.setBounds(562, 60, 228, 23);
		add(btnElaboracion);
		
		JTextPane textPaneInfoAlim = new JTextPane();
		textPaneInfoAlim.setBounds(355, 108, 435, 373);
		add(textPaneInfoAlim);
		
		JButton btnOrigenAli = new JButton("Origen del alimento");
		btnOrigenAli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnOrigenAli.setFont(new Font("Arial", Font.BOLD, 16));
		btnOrigenAli.setBounds(470, 550, 214, 23);
		add(btnOrigenAli);
		
		JButton btnAnyadirLista = new JButton("A\u00F1adir a la lista de la compra");
		btnAnyadirLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAnyadirLista.setFont(new Font("Arial", Font.BOLD, 16));
		btnAnyadirLista.setBounds(442, 505, 266, 23);
		add(btnAnyadirLista);
		
		JList list = new JList();
		list.setBounds(21, 480, 305, -371);
		add(list);
		

	}
}
