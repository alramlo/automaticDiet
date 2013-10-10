package presentacion;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Consultar_dieta_asignada extends JPanel
{
	private JTable tabla_dieta;
	JButton btn_lista_compra = new JButton("Lista de la compra");
	JButton btn_modificar = new JButton("Modificar dieta");
	JButton btn_detalle = new JButton("Ver detalles");
	JLabel lbl_fecha_actual = new JLabel("00 / 00 / 0000");
	
	/**
	 * Create the panel.
	 */
	public Consultar_dieta_asignada(/*Controlador c*/)
	{
		this.setSize(1024, 768);
		
		tabla_dieta = new JTable(/*new Tabla_dieta_semanal(c)*/);
		btn_lista_compra.setIcon(new ImageIcon(Consultar_dieta_asignada.class.getResource("/iconos/carrito.png")));
		btn_lista_compra.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_lista_compra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_modificar.setIcon(new ImageIcon(Consultar_dieta_asignada.class.getResource("/iconos/actualizar.png")));
		btn_modificar.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_detalle.setIconTextGap(10);
		btn_detalle.setIcon(new ImageIcon(Consultar_dieta_asignada.class.getResource("/iconos/lupa.png")));
		btn_detalle.setFont(new Font("Arial", Font.PLAIN, 14));
		
		
		lbl_fecha_actual.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_fecha_actual.setFont(new Font("Arial", Font.PLAIN, 24));
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabla_dieta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbl_fecha_actual, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
							.addComponent(btn_detalle, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_modificar, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_lista_compra, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_fecha_actual, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_lista_compra, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_modificar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_detalle, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(tabla_dieta, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(120, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
