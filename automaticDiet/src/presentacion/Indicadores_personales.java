package presentacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

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

public class Indicadores_personales extends JPanel {

	/**
	 * 
	 */
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
	private Date aReg = today;
	private Date currentDay = today;
	private int mes, year;
	private SimpleDateFormat sdf;
	
	ChartPanel grafica_panel;
	JMonthChooser monthChooser;
	JYearChooser yearChooser;
	
	
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();

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
		
		datos_usuario = control.getSegUsuario(1);
		
		mes = calendario.getCalendar().getInstance().MONTH;
		year = calendario.getYearChooser().getYear();
		
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
				pintaDias(mes, year);
				aReg = calendario.getDate();
				diaSeleccionado(aReg);
				if(aReg.after(today))
				{
					poderRegistrar(false);
				}
				else
				{
					poderRegistrar(true);
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
					.addComponent(calendario.getMonthChooser().getSpinner(), GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
					.addGap(1))
		);
		calendario.getMonthChooser().setLayout(groupLayout_1);
		calendario.setWeekOfYearVisible(false);
		calendario.setWeekdayForeground(new Color(0, 0, 0));
		calendario.setFont(new Font("Arial", Font.PLAIN, 20));
		calendario.setDecorationBackgroundColor(new Color(255, 153, 102));
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
							.addComponent(pesaje, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
							.addGap(10))))
		);
		gl_registro.setVerticalGroup(
			gl_registro.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_registro.createSequentialGroup()
					.addGap(10)
					.addComponent(calendario, GroupLayout.PREFERRED_SIZE, 236, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(gl_registro.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cumplimiento, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addComponent(pesaje, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
					.addGap(10))
		);
		
		
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
					.addGroup(gl_pesaje.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCuantoKg, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_pesaje.createSequentialGroup()
							.addComponent(peso, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
							.addComponent(btnReg, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_pesaje.setVerticalGroup(
			gl_pesaje.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pesaje.createSequentialGroup()
					.addGap(10)
					.addComponent(lblCuantoKg, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
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
						.addComponent(lblHasCumplido, GroupLayout.PREFERRED_SIZE, 220, Short.MAX_VALUE)
						.addGroup(gl_cumplimiento.createSequentialGroup()
							.addComponent(btnSi, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
							.addComponent(btnNo, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_cumplimiento.setVerticalGroup(
			gl_cumplimiento.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cumplimiento.createSequentialGroup()
					.addGap(10)
					.addComponent(lblHasCumplido, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(gl_cumplimiento.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(10))
		);
		cumplimiento.setLayout(gl_cumplimiento);
		registro.setLayout(gl_registro);
		diaSeleccionado(today);
		
		
		//PANEL DE GRAFICA DE CONTROL DE PESO
		
		tabbedPane.addTab("Grafica de Control de peso", new ImageIcon(Indicadores_personales.class.getResource("/iconos/grafica.png")), panel, null);
		
		monthChooser = new JMonthChooser();
		monthChooser.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent arg0)
			{
				pintaGrafica(monthChooser.getMonth()+1, yearChooser.getYear());
			}
		});
		monthChooser.getComboBox().setFont(new Font("Arial", Font.PLAIN, 18));
		monthChooser.getSpinner().setFont(new Font("Arial", Font.PLAIN, 16));
		
		yearChooser = new JYearChooser();
		yearChooser.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent arg0)
			{
				pintaGrafica(monthChooser.getMonth()+1, yearChooser.getYear());
			}
		});
		yearChooser.setFont(new Font("Arial", Font.PLAIN, 18));
		
		contenidoGraf = new JPanel();
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(contenidoGraf, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(monthChooser, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(yearChooser, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(yearChooser, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
						.addComponent(monthChooser, GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(contenidoGraf, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contenidoGraf.setLayout(new BoxLayout(contenidoGraf, BoxLayout.X_AXIS));
		
		grafica_panel = new ChartPanel(grafica(mes, year));
		contenidoGraf.add(grafica_panel);
		GroupLayout gl_yearChooser = new GroupLayout(yearChooser);
		gl_yearChooser.setHorizontalGroup(
			gl_yearChooser.createParallelGroup(Alignment.LEADING)
				.addComponent(yearChooser.getSpinner(), GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
		);
		gl_yearChooser.setVerticalGroup(
			gl_yearChooser.createParallelGroup(Alignment.LEADING)
				.addComponent(yearChooser.getSpinner(), GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
		);
		yearChooser.setLayout(gl_yearChooser);
		GroupLayout gl_monthChooser = new GroupLayout(monthChooser);
		gl_monthChooser.setHorizontalGroup(
			gl_monthChooser.createParallelGroup(Alignment.LEADING)
				.addComponent(monthChooser.getSpinner(), GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
		);
		gl_monthChooser.setVerticalGroup(
			gl_monthChooser.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_monthChooser.createSequentialGroup()
					.addComponent(monthChooser.getSpinner(), GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		monthChooser.setLayout(gl_monthChooser);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		pintaDias(mes, year);
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
		if(d!=null)
		{
			Calendar cal = calendario.getCalendar().getInstance();
			cal.setTime(calendario.getDate());
			cal.set(Calendar.DAY_OF_MONTH,1);
			Calendar.getInstance();
			int inicio = cal.get(Calendar.DAY_OF_WEEK)-1;
			Component dias[] = calendario.getDayChooser().getDayPanel().getComponents();
			sdf = new SimpleDateFormat("dd");
			int dia = Integer.parseInt(sdf.format(d));
			dias[5 + inicio + dia].setFont(new Font("Arial", Font.BOLD, 48));
			
			dia = Integer.parseInt(sdf.format(currentDay));
			dias[5 + inicio + dia].setFont(new Font("Arial", Font.PLAIN, 20));
			
			currentDay = d;
		}
		else
		{
			Calendar cal = calendario.getCalendar().getInstance();
			cal.setTime(calendario.getDate());
			cal.set(Calendar.DAY_OF_MONTH,1);
			Calendar.getInstance();
			int inicio = cal.get(Calendar.DAY_OF_WEEK)-1;
			Component dias[] = calendario.getDayChooser().getDayPanel().getComponents();
			sdf = new SimpleDateFormat("dd");
			int dia = Integer.parseInt(sdf.format(currentDay));
			dias[5 + inicio + dia].setFont(new Font("Arial", Font.PLAIN, 20));
		}
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
