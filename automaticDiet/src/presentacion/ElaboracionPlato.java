package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ElaboracionPlato extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ElaboracionPlato() {
		setLayout(null);
		
		JButton buttonPanelIngredientes = new JButton("Panel Ingredientes");
		buttonPanelIngredientes.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonPanelIngredientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonPanelIngredientes.setBounds(250, 11, 162, 39);
		add(buttonPanelIngredientes);
		
		textField = new JTextField();
		textField.setBounds(41, 76, 174, 29);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Buscar");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(41, 51, 46, 14);
		add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBox.setBounds(250, 142, 174, 20);
		add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Plato:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(250, 117, 46, 14);
		add(lblNewLabel_1);
		
		JLabel imagenPlato = new JLabel("");
		imagenPlato.setBounds(41, 193, 174, 155);
		add(imagenPlato);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(250, 193, 174, 155);
		add(textPane);
		
		JLabel lblNewLabel_2 = new JLabel("AutomaticDiet");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_2.setBounds(250, 61, 162, 24);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cumple tus objetivos facilmente!");
		lblNewLabel_3.setBounds(250, 83, 162, 14);
		add(lblNewLabel_3);
		
		JButton buttonBuscar = new JButton("Buscar");
		buttonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonBuscar.setBackground(Color.RED);
		buttonBuscar.setBounds(126, 114, 89, 23);
		add(buttonBuscar);


	}
}
