package presentacion;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Consultar_dieta_asignada extends JPanel
{
	private JTable tabla_dieta;
	JButton btn_lista_compra = new JButton("Lista de la compra");
	JButton btn_modificar = new JButton("Modificar dieta");
	JButton btn_detalle = new JButton("Ver detalles");
	JLabel lbl_fecha_actual = new JLabel("");
	
	/**
	 * Create the panel.
	 */
	public Consultar_dieta_asignada(/*Controlador c*/)
	{
		this.setSize(800, 600);
		
		tabla_dieta = new JTable(/*new Tabla_dieta_semanal(c)*/);
		tabla_dieta.setColumnSelectionAllowed(true);
		tabla_dieta.setModel(new DefaultTableModel(
			new Object[][] {
				{"Desayuno", null, null, null, null, null, null, null},
				{"Almuerzo", null, null, null, null, null, null, null},
				{"Merienda", null, null, null, null, null, null, null},
				{"Cena", null, null, null, null, null, null, null},
			},
			new String[] {
				"", "Lunes", "Martes", "Mi\u00E9rcoles", "Jueves", "Viernes", "S\u00E1bado", "Domingo"
			}
		));
		tabla_dieta.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tabla_dieta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla_dieta.setRowSelectionAllowed(false);
		tabla_dieta.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_lista_compra.setIcon(new ImageIcon(Consultar_dieta_asignada.class.getResource("/iconos/carrito.png")));
		btn_lista_compra.setFont(new Font("Arial", Font.PLAIN, 14));
		
		btn_lista_compra.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				new Advertencia("Esta función no esta diponible en este momento",  "info").setVisible(true);
			}
		});
		
		btn_modificar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
			}
		});
		btn_modificar.setIcon(new ImageIcon(Consultar_dieta_asignada.class.getResource("/iconos/actualizar.png")));
		btn_modificar.setFont(new Font("Arial", Font.PLAIN, 14));
		
		btn_detalle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (!tabla_dieta.isColumnSelected(tabla_dieta.getSelectedColumn()))
				{
					
				}
			}
		});
		btn_detalle.setIconTextGap(10);
		btn_detalle.setIcon(new ImageIcon(Consultar_dieta_asignada.class.getResource("/iconos/lupa.png")));
		btn_detalle.setFont(new Font("Arial", Font.PLAIN, 14));
		
		Date actual = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd / MMMM / yyyy");
		
		String fecha = formatoFecha.format(actual);
		
		lbl_fecha_actual.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_fecha_actual.setFont(new Font("Arial", Font.PLAIN, 24));
		lbl_fecha_actual.setText(fecha);
		
		JDateChooser dia_final = new JDateChooser();
		dia_final.setDateFormatString("dd-MMMM-yyyy");
		
		JDateChooser dia_inicio = new JDateChooser();
		dia_inicio.setDateFormatString("dd-MMMM-yyyy");
		
		JButton btn_consultar = new JButton("Consultar");
		btn_consultar.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblFechaInicial = new JLabel("fecha inicial");
		
		JLabel lblFechaFinal = new JLabel("fecha final");
		
		JLabel lblConsultar = new JLabel("Consultar");
		lblConsultar.setForeground(Color.BLUE);
		lblConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultar.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel lblHistrico = new JLabel(" hist\u00F3rico");
		lblHistrico.setForeground(Color.BLUE);
		lblHistrico.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistrico.setFont(new Font("Arial", Font.BOLD, 14));
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabla_dieta, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbl_fecha_actual, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
							.addComponent(btn_detalle, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_modificar, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_lista_compra, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblHistrico, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblConsultar, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(dia_inicio, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFechaInicial, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFechaFinal)
								.addComponent(dia_final, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btn_consultar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
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
					.addComponent(tabla_dieta, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btn_consultar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(dia_final, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblFechaInicial, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblFechaFinal))
									.addGap(1)
									.addComponent(dia_inicio, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(lblConsultar, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(lblHistrico, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		setLayout(groupLayout);
	}
}
