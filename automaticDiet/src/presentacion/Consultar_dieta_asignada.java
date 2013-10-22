package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.Plato;
import servicio.Controlador;

import com.toedter.calendar.JDateChooser;
import javax.swing.UIManager;

public class Consultar_dieta_asignada extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JTable tabla_dieta;
	private JDateChooser fecha_actual;
	private int cont;
	JButton btn_lista_compra = new JButton("Lista de la compra");
	JButton btn_modificar = new JButton("Modificar dieta");
	JButton btn_detalle = new JButton("Ver detalles");
	Plato[] platos;
	
	/**
	 * Create the panel.
	 */
	public Consultar_dieta_asignada(final Controlador c)
	{
		setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 2, true), "Dieta asignada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.setSize(800, 600);
		
		tabla_dieta = new JTable(/*new Tabla_dieta_semanal(c)*/);
		tabla_dieta.setBackground(Color.WHITE);
		tabla_dieta.setColumnSelectionAllowed(true);
		tabla_dieta.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"},
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
		tabla_dieta.setRowHeight(100);
//		textAreaElaboracion.setLineWrap(true);
//		textAreaElaboracion.setWrapStyleWord(true);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i=0;i<8;i++){
		tabla_dieta.getColumnModel().getColumn(i).setCellRenderer(tcr);
		tabla_dieta.getColumnModel().getColumn(i).setCellRenderer(tcr);
		}
		
		btn_lista_compra.setEnabled(false);
		btn_lista_compra.setIcon(new ImageIcon(Consultar_dieta_asignada.class.getResource("/iconos/carrito.png")));
		btn_lista_compra.setFont(new Font("Arial", Font.PLAIN, 12));
		
		btn_lista_compra.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				new Advertencia("Esta función no esta diponible", "info").setVisible(true);
			}
		});
		btn_modificar.setEnabled(false);
		
		btn_modificar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				new Advertencia("Esta función no esta diponible","info").setVisible(true);
			}
		});
		btn_modificar.setIcon(new ImageIcon(Consultar_dieta_asignada.class.getResource("/iconos/actualizar.png")));
		btn_modificar.setFont(new Font("Arial", Font.PLAIN, 12));
		btn_detalle.setEnabled(false);
		
		btn_detalle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (!tabla_dieta.isColumnSelected(tabla_dieta.getSelectedColumn()))
				{
					new Advertencia("Esta función no esta diponible",  "warning").setVisible(true);
				}
			}
		});
		btn_detalle.setIconTextGap(10);
		btn_detalle.setIcon(new ImageIcon(Consultar_dieta_asignada.class.getResource("/iconos/lupa.png")));
		btn_detalle.setFont(new Font("Arial", Font.PLAIN, 12));
		
		Date actual = new Date();
		
		JDateChooser dia_final = new JDateChooser();
		dia_final.setDateFormatString("dd-MMMM-yyyy");
		
		JDateChooser dia_inicio = new JDateChooser();
		dia_inicio.setDateFormatString("dd-MMMM-yyyy");
		
		JButton btn_consultar = new JButton("Consultar");
		btn_consultar.setEnabled(false);
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
		
		 cont=0;
		fecha_actual = new JDateChooser(actual);
		fecha_actual.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent e){
			
			try{
			if(fecha_actual.getDate()!=null && cont<28){
				platos=c.dietaSemanal(1, fecha_actual.getDate());
				if(platos.length!=0){
				for(int i=1;i<=7;i++){
					for(int j=1;j<=4;j++){
					tabla_dieta.setValueAt(platos[cont].getNombre(),j , i);
					cont++;
					}
				}
				}else{
					for(int i=1;i<=7;i++){
						for(int j=1;j<=4;j++){
						tabla_dieta.setValueAt("",j , i);
						}
					}
				}
				cont=0;
				}
			}catch(java.lang.IllegalStateException a){}
			}
		});
		fecha_actual.getCalendarButton().setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		fecha_actual.getCalendarButton().setContentAreaFilled(false);
		fecha_actual.setOpaque(false);
		fecha_actual.setFont(new Font("Arial", Font.PLAIN, 22));
		fecha_actual.setDateFormatString(" dd / MMMM / yyyy");
		fecha_actual.setBorder(null);
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(fecha_actual, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btn_detalle, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btn_modificar, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btn_lista_compra, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
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
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tabla_dieta, GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btn_detalle, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(btn_modificar, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(btn_lista_compra, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 34, Short.MAX_VALUE))
							.addGap(12))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(fecha_actual, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(18)
					.addComponent(tabla_dieta, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
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
