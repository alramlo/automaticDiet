package presentacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import modelo.Dieta;
import modelo.Seguimiento;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import servicio.Controlador;

import com.toedter.calendar.JCalendar;

import excepciones.DominioExcepcion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Indicadores_personales extends JPanel {

	/**
	 * 
	 */
	private Date ini_dieta;
	private Date fin_dieta;
	private Dieta dieta_select;
	private JComboBox<String> dietas;
	private JComboBox<String> comboBox;
	
	private Controlador control;
	private JPanel registro = new JPanel();
	private JPanel contenidoGraf;
	
	private JCalendar calendario = new JCalendar();
	
	private JPanel cumplimiento = new JPanel();
	private JLabel lblHasCumplido = new JLabel("\u00BF Has cumplido con la dieta ?");
	private JButton btnSi = new JButton("S\u00ED");
	private JButton btnNo = new JButton("No");
	
	private JPanel pesaje = new JPanel();
	private JLabel lblCuantoKg = new JLabel("\u00BF Cu\u00E1ntos Kg pesas ?");
	private JSpinner peso = new JSpinner();
	private JButton btnReg = new JButton("Registrar");
	
	private Seguimiento[] datos_usuario;
	private Date today = calendario.getDate();
	private Date aReg = null;
	private Date currentDay = null;
	private int mes, year;
	private int mes_ini, year_ini, mes_fin, year_fin;
	private SimpleDateFormat sdf;
	
	ChartPanel grafica_panel;
	
	
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private final JLabel lblFechaDeFin = new JLabel("Fecha de fin");
	private JTextField fini;
	private JTextField ffin;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Constructor del panel Indicadores personales
	 */
	public Indicadores_personales()
	{
	
		try {
			control=Controlador.dameControlador();
		} catch (DominioExcepcion e3) {
			e3.printStackTrace();
		}
		
		datos_usuario = control.getSegUsuario(control.getUsuarioActual().getId());
//		datos_usuario = control.getSegUsuario(11);
		
		String[] dietas_usuario = control.getDietas(control.getUsuarioActual());
//		String[] dietas_usuario = control.getDietas(control.getUsuarioPorId(11));
		
//		dietas = new JComboBox();
		dietas = new JComboBox<String>(dietas_usuario);
		
		mes = calendario.getCalendar().getInstance().MONTH;
		year = calendario.getYearChooser().getYear();
		dieta_select = control.getDietaPorNombre((String) dietas.getItemAt(dietas.getSelectedIndex()));
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 16));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addComponent(tabbedPane)
					.addGap(1))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(tabbedPane)
					.addGap(1))
		);
		
		
		
		//Panel calendario de seguimiento
		
		
		
		tabbedPane.addTab("Calendario de seguimiento", new ImageIcon(Indicadores_personales.class.getResource("/iconos/dietas.png")), registro, null);
		
		cumplimiento.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		pesaje.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		calendario.getMonthChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0)
			{
				mes = calendario.getMonthChooser().getMonth()+1;
				pintaDias(mes, year);
				diaSeleccionado(null);
				poderRegistrar(false);
			}
		});
		calendario.getYearChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0)
			{
				year = calendario.getYearChooser().getYear();
				pintaDias(mes, year);
				diaSeleccionado(null);
				poderRegistrar(false);
			}
		});
		calendario.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0)
			{
				if(datos_usuario.length!=0){
				pintaDias(mes, year);
				aReg = calendario.getDate();
				diaSeleccionado(aReg);
				if(aReg.after(today))
				{
					poderRegistrar(false);
				}
				else if(aReg.before(ini_dieta) || aReg.after(fin_dieta))
				{
					poderRegistrar(false);
					JOptionPane.showMessageDialog(null, "Esta fecha no tiene dieta asignada", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					poderRegistrar(true);
				}
			}
			}
		});
		
		calendario.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		calendario.getMonthChooser().getSpinner().setFont(new Font("Arial", Font.PLAIN, 16));
		calendario.getMonthChooser().getComboBox().setFont(new Font("Arial", Font.PLAIN, 16));
		calendario.getMonthChooser().setFont(new Font("Arial", Font.PLAIN, 16));
		GroupLayout groupLayout_1 = new GroupLayout(calendario.getMonthChooser());
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addComponent(calendario.getMonthChooser().getSpinner(), GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
					.addGap(1))
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addComponent(calendario.getMonthChooser().getSpinner(), GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(1))
		);
		calendario.getMonthChooser().setLayout(groupLayout_1);
		calendario.setWeekOfYearVisible(false);
		calendario.setWeekdayForeground(new Color(0, 0, 0));
		calendario.setFont(new Font("Arial", Font.PLAIN, 20));
		calendario.setDecorationBackgroundColor(new Color(255, 153, 102));
		
		dietas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dieta_select = control.getDietaPorNombre((String) dietas.getItemAt(dietas.getSelectedIndex()));
				comboBox.setSelectedIndex(dietas.getSelectedIndex());
				if(datos_usuario.length!=0)
				diasDieta();
				pintaDias(mes_ini, year_ini);
			}
		});
		
		dietas.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio");
		lblFechaDeInicio.setFont(new Font("Arial", Font.PLAIN, 14));
		
		fini = new JTextField();
		fini.setFont(new Font("Arial", Font.PLAIN, 14));
		fini.setColumns(10);
		
		ffin = new JTextField();
		ffin.setFont(new Font("Arial", Font.PLAIN, 14));
		ffin.setColumns(10);
		GroupLayout gl_registro = new GroupLayout(registro);
		gl_registro.setHorizontalGroup(
			gl_registro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_registro.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_registro.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_registro.createSequentialGroup()
							.addComponent(calendario, GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_registro.createSequentialGroup()
							.addComponent(cumplimiento, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
							.addGap(20)
							.addComponent(pesaje, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(gl_registro.createSequentialGroup()
							.addComponent(dietas, 0, 381, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_registro.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblFechaDeFin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(lblFechaDeInicio, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 87, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_registro.createParallelGroup(Alignment.LEADING)
								.addComponent(fini, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
								.addComponent(ffin, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_registro.setVerticalGroup(
			gl_registro.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_registro.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_registro.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_registro.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblFechaDeInicio, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addComponent(fini, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(dietas, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_registro.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaDeFin, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
						.addGroup(gl_registro.createSequentialGroup()
							.addGap(8)
							.addComponent(ffin)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(calendario, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(gl_registro.createParallelGroup(Alignment.LEADING)
						.addComponent(cumplimiento, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
						.addComponent(pesaje, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
					.addGap(10))
		);
		lblFechaDeFin.setFont(new Font("Arial", Font.PLAIN, 14));
		
		
		lblCuantoKg.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuantoKg.setFont(new Font("Arial", Font.PLAIN, 18));
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				
				Seguimiento s = new Seguimiento();
				
				s.setUsuario2(control.getUsuarioPorId(1));
				s.setFecha(aReg);
				s.setPeso(new BigDecimal((double)peso.getValue()));
				registrar(s);
			}
		});
		
		
		btnReg.setFont(new Font("Arial", Font.PLAIN, 16));
		
		
		peso.setModel(new SpinnerNumberModel(70.0, 40.0, 200.0, 0.1));
		peso.setFont(new Font("Arial", Font.PLAIN, 16));
		GroupLayout gl_pesaje = new GroupLayout(pesaje);
		gl_pesaje.setHorizontalGroup(
			gl_pesaje.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pesaje.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pesaje.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCuantoKg, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
						.addGroup(gl_pesaje.createSequentialGroup()
							.addComponent(peso, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
							.addComponent(btnReg, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_pesaje.setVerticalGroup(
			gl_pesaje.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pesaje.createSequentialGroup()
					.addGap(10)
					.addComponent(lblCuantoKg, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
					.addGap(24)
					.addGroup(gl_pesaje.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnReg, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(peso, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(10))
		);
		pesaje.setLayout(gl_pesaje);
		
		
		lblHasCumplido.setHorizontalAlignment(SwingConstants.CENTER);
		lblHasCumplido.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Seguimiento s = new Seguimiento();
					
				s.setUsuario2(control.getUsuarioPorId(1));
				s.setFecha(aReg);
				s.setCumplido("SI");
				registrar(s);
			}
		});
		
		
		btnSi.setFont(new Font("Arial", Font.PLAIN, 16));
		btnSi.setBorder(new LineBorder(new Color(0, 204, 0), 2));
		
		
		btnNo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Seguimiento s = new Seguimiento();
				
				s.setUsuario2(control.getUsuarioPorId(1));
				s.setFecha(aReg);
				s.setCumplido("NO");
				registrar(s);
			}
		});
		
		btnNo.setBorder(new LineBorder(new Color(255, 0, 0), 2));
		btnNo.setFont(new Font("Arial", Font.PLAIN, 16));
		GroupLayout gl_cumplimiento = new GroupLayout(cumplimiento);
		gl_cumplimiento.setHorizontalGroup(
			gl_cumplimiento.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cumplimiento.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_cumplimiento.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHasCumplido, GroupLayout.PREFERRED_SIZE, 353, Short.MAX_VALUE)
						.addGroup(gl_cumplimiento.createSequentialGroup()
							.addComponent(btnSi, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
							.addComponent(btnNo, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_cumplimiento.setVerticalGroup(
			gl_cumplimiento.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cumplimiento.createSequentialGroup()
					.addGap(10)
					.addComponent(lblHasCumplido, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
					.addGap(24)
					.addGroup(gl_cumplimiento.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(10))
		);
		cumplimiento.setLayout(gl_cumplimiento);
		registro.setLayout(gl_registro);
		
		
		//PANEL DE GRAFICA DE CONTROL DE PESO
		
		tabbedPane.addTab("Grafica de Control de peso", new ImageIcon(Indicadores_personales.class.getResource("/iconos/grafica.png")), panel, null);
		
		contenidoGraf = new JPanel();
		
//		comboBox = new JComboBox();
		comboBox = new JComboBox<String>(dietas_usuario);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
		dieta_select = control.getDietaPorNombre((String) comboBox.getItemAt(comboBox.getSelectedIndex()));
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dieta_select = control.getDietaPorNombre((String) comboBox.getItemAt(comboBox.getSelectedIndex()));
				dietas.setSelectedIndex(comboBox.getSelectedIndex());
				if(datos_usuario.length!=0)
				diasDieta();
				pintaGrafica(mes_ini, year_ini);
			}
		});
		
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		textField.setColumns(10);
		
		JLabel label = new JLabel("Fecha de inicio");
		label.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel label_1 = new JLabel("Fecha de fin");
		label_1.setFont(new Font("Arial", Font.PLAIN, 14));
		
		textField_1 = new JTextField();
		textField_1.setText((String) null);
		textField_1.setFont(new Font("Arial", Font.PLAIN, 14));
		textField_1.setColumns(10);
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(contenidoGraf, GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(391)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(contenidoGraf, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
					.addContainerGap())
		);
		contenidoGraf.setLayout(new BoxLayout(contenidoGraf, BoxLayout.X_AXIS));
		
		grafica_panel = new ChartPanel(grafica(mes_ini, year_ini));
		contenidoGraf.add(grafica_panel);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		if(datos_usuario.length!=0)
		diasDieta();
		pintaGrafica(mes_ini, year_ini);
		pintaDias(mes_ini, year_ini);
	}
	
	private JFreeChart grafica(int m, int y)
	{
		JFreeChart grafica = ChartFactory.createXYLineChart("", "Dia", "Peso (en Kg)", obtenerDatos(m, y), PlotOrientation.VERTICAL, false, false, false);
		XYLineAndShapeRenderer  renderer  = (XYLineAndShapeRenderer) ((XYPlot) grafica.getPlot()).getRenderer();   
		grafica.getXYPlot().getDomainAxis().setRange(1, 31);
		grafica.getXYPlot().getRangeAxis().setRange(10, 150);
        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled (true);
        renderer.setSeriesPaint(0, Color.RED);
        
        return grafica;
	}
	
	private XYSeriesCollection obtenerDatos(int m, int y)
	{
        XYSeries series1 = new XYSeries("");
        for( int i = 0; i < datos_usuario.length; i++)
        {
        	sdf = new SimpleDateFormat("MM");
			int mes = Integer.parseInt(sdf.format(datos_usuario[i].getFecha()));
			sdf = new SimpleDateFormat("yyyy");
			int year = Integer.parseInt(sdf.format(datos_usuario[i].getFecha()));
			
        	if(mes == m && year == y && datos_usuario[i].getPeso()!=null)
        	{
        		sdf = new SimpleDateFormat("dd");
        		int dia = Integer.parseInt(sdf.format(datos_usuario[i].getFecha()));
        		series1.add(dia, datos_usuario[i].getPeso());
        	}
        }

        XYSeriesCollection colection = new XYSeriesCollection();
        colection.addSeries(series1);
                
        return colection;
        
    }
	
	private void pintaGrafica(int m, int y)
	{
		contenidoGraf.removeAll();
		grafica_panel = new ChartPanel(grafica(m, y));
		contenidoGraf.add(grafica_panel);
		contenidoGraf.revalidate();
		contenidoGraf.repaint();
	}
	
	private void diaSeleccionado(Date d)
	{
		Calendar cal = calendario.getCalendar().getInstance();
		cal.setTime(calendario.getDate());
		datos_usuario = control.getSegUsuario(control.getUsuarioActual().getId());
		cal.set(Calendar.DAY_OF_MONTH,1);
		Calendar.getInstance();
		int inicio = cal.get(Calendar.DAY_OF_WEEK)-1;
		Component dias[] = calendario.getDayChooser().getDayPanel().getComponents();
		
		if(d!=null)
		{
			if(inicio ==0)
			{
				inicio = 7;
			}
			
			sdf = new SimpleDateFormat("dd");
			int dia = Integer.parseInt(sdf.format(d));
			dias[5 + inicio + dia].setFont(new Font("Arial", Font.BOLD, 24));
			dias[5 + inicio + dia].setBackground(Color.BLUE);
			
			if(currentDay!=null)
			{
				dia = Integer.parseInt(sdf.format(currentDay));
				dias[5 + inicio + dia].setFont(new Font("Arial", Font.PLAIN, 20));
			}
			
			currentDay = d;
		}
		else if (currentDay !=null)
		{
			sdf = new SimpleDateFormat("dd");
			int dia = Integer.parseInt(sdf.format(currentDay));
			dias[5 + inicio + dia].setFont(new Font("Arial", Font.PLAIN, 20));
		}
	}
	
	private void diasDieta()
	{
		
		ini_dieta = dieta_select.getFechaInicial();
		fin_dieta = dieta_select.getFechaFinal();
		
		sdf = new SimpleDateFormat("dd / MMMM / yyyy");
		System.out.println(sdf.format(ini_dieta));
		fini.setText(sdf.format(ini_dieta));
		textField.setText(sdf.format(ini_dieta));
		ffin.setText(sdf.format(fin_dieta));
		textField_1.setText(sdf.format(fin_dieta));
		
		sdf = new SimpleDateFormat("MM");
		mes_ini = Integer.parseInt(sdf.format(ini_dieta));
		mes_fin = Integer.parseInt(sdf.format(fin_dieta));
		calendario.getMonthChooser().setMonth(mes_ini-1);
		
		sdf = new SimpleDateFormat("yyyy");
		year_ini = Integer.parseInt(sdf.format(ini_dieta));
		year_fin = Integer.parseInt(sdf.format(fin_dieta));
		calendario.getYearChooser().setYear(year_ini);
		
	}
	
	private void registrar(Seguimiento s)
	{
		control.setSeguimiento(s);
		pintaDias(mes, year);
	}
	private void poderRegistrar(boolean b)
	{
		btnNo.setEnabled(b);
		btnSi.setEnabled(b);
		btnReg.setEnabled(b);
		peso.setEnabled(b);
		lblCuantoKg.setEnabled(b);
		lblHasCumplido.setEnabled(b);
	}
	
	private void pintaDias(int m, int y)
	{
		Calendar cal = calendario.getCalendar().getInstance();
		cal.setTime(calendario.getDate());
		datos_usuario = control.getSegUsuario(1);
		cal.set(Calendar.DAY_OF_MONTH,1);
		Calendar.getInstance();
		int inicio = cal.get(Calendar.DAY_OF_WEEK)-1;
		Component dias[] = calendario.getDayChooser().getDayPanel().getComponents();
		
		for (int i = 0; i< datos_usuario.length; i++)
		{
			calendario.getCalendar();
			Calendar.getInstance().setTime(datos_usuario[i].getFecha());
			sdf = new SimpleDateFormat("MM");
			int mes = Integer.parseInt(sdf.format(datos_usuario[i].getFecha()));
			sdf = new SimpleDateFormat("yyyy");
			int year = Integer.parseInt(sdf.format(datos_usuario[i].getFecha()));
			
			if (mes == m && year == y)
			{
				sdf = new SimpleDateFormat("dd");
				int dia = Integer.parseInt(sdf.format(datos_usuario[i].getFecha()));
				
				if (datos_usuario[i].getCumplido()!= null)
				{
					if(datos_usuario[i].getCumplido().compareToIgnoreCase("si")==0)
					{
						dias[5 + inicio + dia].setBackground(Color.GREEN);
					}
					else
						dias[5 + inicio + dia].setBackground(Color.RED);
				}
			}
			
		}
	}
}
