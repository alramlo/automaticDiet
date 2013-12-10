package presentacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modelo.Dieta;
import modelo.Plato;
import modelo.Usuario;
import servicio.Controlador;

import com.toedter.calendar.JDateChooser;

public class Consultar_dieta_asignada extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JTable tabla_dieta;
	private int cont;
	private JComboBox<String> comboBoxDietas;
	private JLabel lblPaginacion;
	private final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
	private JButton btnAnterior,buttonSiguiente;
	private int en=0,contSemanas=1;
	private long semanas;
	private Dieta dieta;
	JButton btn_lista_compra = new JButton("Lista de la compra");
	JButton btn_modificar = new JButton("Modificar dieta");
	JButton btn_detalle = new JButton("Ver detalles");
	Plato[] platos;
	
	
	/**
	 * Create the panel.
	 */
	public Consultar_dieta_asignada(final Controlador c)
	{
		this.setSize(768, 580);
		
		
		tabla_dieta = new JTable(/*new Tabla_dieta_semanal(c)*/);
		tabla_dieta.setEnabled(false);
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
//		tabla_dieta.setRowHeight(60);
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
		
		JDateChooser dia_final = new JDateChooser();
		dia_final.getCalendarButton().setEnabled(false);
		dia_final.setDateFormatString("dd-MMMM-yyyy");
		dia_final.setVisible(false);
		
		JDateChooser dia_inicio = new JDateChooser();
		dia_inicio.getCalendarButton().setEnabled(false);
		dia_inicio.setDateFormatString("dd-MMMM-yyyy");
		dia_inicio.setVisible(false);
		
		JButton btn_consultar = new JButton("Consultar");
		btn_consultar.setEnabled(false);
		btn_consultar.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_consultar.setVisible(false);
		
		JLabel lblFechaInicial = new JLabel("fecha inicial");
		lblFechaInicial.setVisible(false);
		
		JLabel lblFechaFinal = new JLabel("fecha final");
		lblFechaFinal.setVisible(false);
		
		JLabel lblConsultar = new JLabel("Consultar");
		lblConsultar.setForeground(Color.BLUE);
		lblConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultar.setFont(new Font("Arial", Font.BOLD, 14));
		lblConsultar.setVisible(false);
		
		JLabel lblHistrico = new JLabel(" hist\u00F3rico");
		lblHistrico.setForeground(Color.BLUE);
		lblHistrico.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistrico.setFont(new Font("Arial", Font.BOLD, 14));
		lblHistrico.setVisible(false);
		
		cont=0;
		
		btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Calendar fecha = new GregorianCalendar();
				fecha.setTime(dieta.getFechaInicial());
				fecha.add(fecha.DATE, (7*contSemanas-1));
				Plato[] pl = c.dietaSemanal(dieta.getUsuario().getId(), fecha.getTime());
				if(pl.length!=0){
						cont=0;
						tabla_dieta.setRowHeight(75);
						for(int i=0;i<7;i++){
							for(int j=0;j<4;j++){
								String nom_plato = pl[cont].getNombre();
								int indice = nom_plato.lastIndexOf(" ");
								cont++;
								if(indice > 0){
									String s1 = nom_plato.substring(0, nom_plato.lastIndexOf(" "));
									String s2 = nom_plato.substring(nom_plato.lastIndexOf(" "), nom_plato.length());
									nom_plato = "<html><p>"+s1+"</p><p>"+s2+"</p></html>";
									tabla_dieta.setValueAt(nom_plato,j+1 , i+1);
								}else{
									tabla_dieta.setValueAt(nom_plato,j+1 , i+1);
								}
							}
						}
						contSemanas--;
						lblPaginacion.setText(contSemanas+"/"+semanas);
						if(contSemanas==1){
							btnAnterior.setEnabled(false);
						}
						else btnAnterior.setEnabled(true);
						if(contSemanas==semanas){
							buttonSiguiente.setEnabled(false);
						}
						else buttonSiguiente.setEnabled(true);
						
				}
			}
		});
		btnAnterior.setIcon(new ImageIcon(Consultar_dieta_asignada.class.getResource("/iconos/anterior.gif")));
		
		buttonSiguiente = new JButton("");
		buttonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Calendar fecha = new GregorianCalendar();
				fecha.setTime(dieta.getFechaInicial());
				fecha.add(fecha.DATE, (7*contSemanas-1));
				Plato[] pl = c.dietaSemanal(dieta.getUsuario().getId(), fecha.getTime());
				if(pl.length!=0){
						cont=0;
						tabla_dieta.setRowHeight(75);
						for(int i=0;i<7;i++){
							for(int j=0;j<4;j++){
								String nom_plato = pl[cont].getNombre();
								int indice = nom_plato.lastIndexOf(" ");
								cont++;
								if(indice > 0){
									String s1 = nom_plato.substring(0, nom_plato.lastIndexOf(" "));
									String s2 = nom_plato.substring(nom_plato.lastIndexOf(" "), nom_plato.length());
									nom_plato = "<html><p>"+s1+"</p><p>"+s2+"</p></html>";
									tabla_dieta.setValueAt(nom_plato,j+1 , i+1);
								}else{
									tabla_dieta.setValueAt(nom_plato,j+1 , i+1);
								}
							}
						}
						contSemanas++;
						lblPaginacion.setText(contSemanas+"/"+semanas);
						btnAnterior.setEnabled(false);
						if(contSemanas==semanas){
							buttonSiguiente.setEnabled(false);
						}
						else buttonSiguiente.setEnabled(true);
						if(contSemanas==1)
							btnAnterior.setEnabled(false);
						else
							btnAnterior.setEnabled(true);
				}
			}
		});
		buttonSiguiente.setIcon(new ImageIcon(Consultar_dieta_asignada.class.getResource("/iconos/siguiente.gif")));
		
		
		lblPaginacion = new JLabel("");
		lblPaginacion.setFont(new Font("Arial", Font.BOLD, 16));
		String[] dietas = c.getDietas(c.getUsuarioActual());
		
		comboBoxDietas = new JComboBox<String>(dietas);
		
		if(comboBoxDietas.getItemCount()==0){
			btnAnterior.setEnabled(false);
			buttonSiguiente.setEnabled(false);
			comboBoxDietas.setEnabled(false);
		}
		
		comboBoxDietas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evento) {
				inicializar(c);
				tabla_dieta.setDefaultRenderer (Object.class, new MiRender());
			}
		});
		
		JLabel lblNewLabel = new JLabel("DIETAS:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		//lblPaginacion.setText(paginar(new Date())+"");
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
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
									.addComponent(btn_consultar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblPaginacion, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(comboBoxDietas, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(btn_detalle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btn_modificar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btn_lista_compra, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tabla_dieta, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
							.addGap(8))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(138)
					.addComponent(btnAnterior, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 365, Short.MAX_VALUE)
					.addComponent(buttonSiguiente, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(143))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBoxDietas, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btn_modificar, 0, 0, Short.MAX_VALUE)
								.addComponent(btn_detalle, 0, 0, Short.MAX_VALUE))
							.addComponent(btn_lista_compra, GroupLayout.PREFERRED_SIZE, 34, Short.MAX_VALUE)))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblPaginacion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAnterior, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 37, Short.MAX_VALUE)
						.addComponent(buttonSiguiente, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 37, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(tabla_dieta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 309, Short.MAX_VALUE)
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
		if(comboBoxDietas.getItemCount()>0)
		inicializar(c);
		else
			JOptionPane.showMessageDialog(null, c.getUsuarioActual().getNombre()+" no tienes dietas asociadas", "Info", JOptionPane.INFORMATION_MESSAGE);
		tabla_dieta.setDefaultRenderer (Object.class, new MiRender());
	}
	
//	private long paginar(Date date, Usuario u){
//		long semanas=0;
//		try {
//			semanas = Controlador.dameControlador().getSemanasDieta(date,u);
//		} catch (DominioExcepcion e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return semanas;
//	}
	
	private void inicializar(Controlador c){
		String nombre = comboBoxDietas.getSelectedItem()+"";
		Object[][] data  = new Object[5][8];
		dieta = c.getDietaPorNombre(nombre);
		semanas = (((dieta.getFechaFinal().getTime()-dieta.getFechaInicial().getTime())/ MILLSECS_PER_DAY)+1)/7;
		Plato[] pl = c.dietaSemanal(dieta.getUsuario().getId(), dieta.getFechaInicial());
		if(pl.length!=0){
				cont=0;
				tabla_dieta.setRowHeight(75);
				DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
				data[0][1]="Lunes";
				data[0][2]="Martes";
				data[0][3]="Miercoles";
				data[0][4]="Jueves";
				data[0][5]="Viernes";
				data[0][6]="Sabado";
				data[0][7]="Domingo";
				data[1][0]="Desayuno";
				data[2][0]="Comida";
				data[3][0]="Merienda";
				data[4][0]="Cena";
				for(int i=0;i<7;i++){
					for(int j=0;j<4;j++){
						String nom_plato = pl[cont].getNombre();
						int indice = nom_plato.lastIndexOf(" ");
						cont++;
						if(indice > 0){
							String s1 = nom_plato.substring(0, nom_plato.lastIndexOf(" "));
							String s2 = nom_plato.substring(nom_plato.lastIndexOf(" "), nom_plato.length());
							nom_plato = "<html><p>"+s1+"</p><p>"+s2+"</p></html>";
							data[j+1][i+1]=nom_plato;
						}else{
							data[j+1][i+1]=nom_plato;
						}
					}
				}
//				tabla_dieta.repaint();
				tabla_dieta.setModel(new DefaultTableModel(data,new String[] {
						"", "Lunes", "Martes", "Mi\u00E9rcoles", "Jueves", "Viernes", "S\u00E1bado", "Domingo"
					}));
				lblPaginacion.setText(contSemanas+"/"+semanas);
				btnAnterior.setEnabled(false);
				if(semanas<=1) buttonSiguiente.setEnabled(false);
				else buttonSiguiente.setEnabled(true);
		}else{
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
			lblPaginacion.setText("");
			btnAnterior.setEnabled(false);
			buttonSiguiente.setEnabled(false);
			if(en==0){
				JOptionPane.showMessageDialog(null, "La dieta no tiene platos", "Info", JOptionPane.INFORMATION_MESSAGE);
				en++;
			}
		}
	}
	
	
	private File byteToFile(Plato p){
		File file=null;
		try {
			file = new File("automaticDiet");
			FileOutputStream fos = new FileOutputStream (file);
			fos.write(p.getImagen());
			fos.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch(java.lang.NullPointerException e3){
			e3.printStackTrace();
		
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return file;
//		ImageIcon im=null;
//		BufferedImage buffer;
//		//int width=0,height=0;
//		try {
//			buffer = ImageIO.read(file);
//			im = new ImageIcon(buffer);
////			width=buffer.getWidth();
////			height=buffer.getHeight();
//			
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return im;
//		Image image=im.getImage();
//		Image newImage;
//		if(width>366 || height> 373){
//			imagenPlato.setSize(3*width/4, 3*height/4);
//			newImage = image.getScaledInstance(3*width/4, 3*height/4, java.awt.Image.SCALE_SMOOTH);
//		}else{
//			imagenPlato.setSize(width, height);
//			newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
//		} 
//		imagenPlato.setIcon(new ImageIcon(newImage));
	}
}

 class MiRender extends DefaultTableCellRenderer
{
   public Component getTableCellRendererComponent(JTable table,
      Object value,
      boolean isSelected,
      boolean hasFocus,
      int row,
      int column)
   {
      super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
      this.setOpaque(true);
      this.setFont(new Font("Arial Black", Font.PLAIN, 12));
      this.setHorizontalAlignment(SwingConstants.CENTER);
         if(row==0 || column==0 ){
        	 this.setBackground(Color.LIGHT_GRAY);
         }     
         else{
        	 
        	 this.setBackground(Color.WHITE);
         }
        this.setBorder(new LineBorder(Color.BLACK));
      return this;
   }
}
