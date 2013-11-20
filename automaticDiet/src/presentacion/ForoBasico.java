package presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import modelo.Foro;
import persistencia.ForoDAO;

public class ForoBasico extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableTablonAnuncios;
	private JTable tableForo;
	private TablaConsultaModelForos modelForos;
	private TablaConsultaModelAnuncios modelAnuncios;
	private ForoDAO foroDAO;
	private List<Foro> listadoDeForos;
//	private List<Foro> listadoDeAnuncios;

	/**
	 * Create the panel.
	 */
	public ForoBasico() {
		foroDAO = new ForoDAO();

		setBounds(new Rectangle(0, 0, 800, 600));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelInf = new JPanel();
		FlowLayout fl_panelInf = (FlowLayout) panelInf.getLayout();
		fl_panelInf.setAlignment(FlowLayout.RIGHT);
		add(panelInf, BorderLayout.SOUTH);
		
		JButton btnNuevoTema = new JButton("Nuevo Tema");
		btnNuevoTema.setIcon(new ImageIcon(ForoBasico.class.getResource("/iconos/edit_add.png")));
		btnNuevoTema.setEnabled(false);
		panelInf.add(btnNuevoTema);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelInf.add(horizontalStrut);
		
		JPanel panelForo = new JPanel();
		add(panelForo, BorderLayout.CENTER);
		panelForo.setLayout(null);
		
		JScrollPane scrollPaneTablonAnuncios = new JScrollPane();
		scrollPaneTablonAnuncios.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tabl\u00F3n de anuncios:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneTablonAnuncios.setBounds(10, 11, 780, 207);
		panelForo.add(scrollPaneTablonAnuncios);
		
		modelAnuncios = new TablaConsultaModelAnuncios();
		tableTablonAnuncios = new JTable();
		tableTablonAnuncios.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			int fila = tableForo.rowAtPoint(e.getPoint());
//			String cod = String.valueOf(tableForo.getValueAt(fila, 0));
//			int idforo = Integer.valueOf(cod);
			VerAnuncioEnForo anuncio = new VerAnuncioEnForo(fila);
			anuncio.setModal(true);
			anuncio.setLocationRelativeTo(null);
			ocultarColumnasJTable(tableTablonAnuncios, new int[]{0});
			anuncio.setVisible(true);
		}
	});

		tableTablonAnuncios.setRowHeight(50);
		tableTablonAnuncios.setModel(modelAnuncios);
		ocultarColumnasJTable(tableTablonAnuncios, new int[]{0});
		DefaultTableCellRenderer centerRendererAnuncios = new DefaultTableCellRenderer();
		centerRendererAnuncios.setHorizontalAlignment( JLabel.LEFT );
		for(int i=0; i<3; i++)
		{
			tableTablonAnuncios.getColumnModel().getColumn(i).setCellRenderer( centerRendererAnuncios );
		}
		scrollPaneTablonAnuncios.setViewportView(tableTablonAnuncios);
		
		JScrollPane scrollPaneForo = new JScrollPane();
		scrollPaneForo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Foro:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneForo.setBounds(10, 229, 780, 288);
		panelForo.add(scrollPaneForo);
		
		modelForos = new TablaConsultaModelForos();
		tableForo = new JTable();
//		tableForo.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int fila = tableForo.rowAtPoint(e.getPoint());
//				String cod = String.valueOf(tableForo.getValueAt(fila, 0));
//				int idforo = Integer.valueOf(cod);
//				ListaMensajesPorforo listaMensajes = new ListaMensajesPorforo(idforo);
//				listaMensajes.cargaMensajes();
//				listaMensajes.setModal(true);
//				listaMensajes.setVisible(true);
//			}
//		});

		tableForo.setRowHeight(50);
		tableForo.setModel(modelForos);
		ocultarColumnasJTable(tableForo, new int[]{0});
		DefaultTableCellRenderer centerRendererForos = new DefaultTableCellRenderer();
		centerRendererForos.setHorizontalAlignment( JLabel.LEFT );
		for(int i=0; i<3; i++)
		{
			tableForo.getColumnModel().getColumn(i).setCellRenderer( centerRendererForos );
		}
		scrollPaneForo.setViewportView(tableForo);
	}
	
	class TablaConsultaModelForos extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		// Columnas de la tabla
		private String[] columnas = {"ID TEMA", "TEMA", "FECHA"};
//		private String[] columnas = {"ID TEMA", "TEMA", "CREADO POR", "FECHA"};
		// Datos que muestra la tabla
		private ArrayList<Foro> data = new ArrayList<Foro>();

		public int getColumnCount() {
			return columnas.length;
		}
		public int getRowCount() {
			return data.size();
		}
		public String getColumnName(int col) {
			return columnas[col];
		}
		// Este método se dispara cada vez que la tabla necesita el valor de un campo
		public Object getValueAt(int row, int col) {
			Foro g = data.get(row);
			switch(col){
			case 0: return g.getId();
			case 1: return g.getTema();
//			case 2: return g.getVisitar();
			case 2: return new SimpleDateFormat("dd / MM / yyyy").format(g.getFecha());
			default: return null;
			}
		}
		public void clear(){
			data.clear();
		}
		/*
		 * JTable uses this method to determine the default renderer/
		 * editor for each cell. If we didn't implement this method,
		 * then the last column would contain text ("true"/"false"),
		 * rather than a check box.
		 */
		public Class<? extends Object>getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}
		public void addRow(Foro row) {
			data.add(row);
			this.fireTableDataChanged();
		}
		public void delRow(int row) {
			data.remove(row);
			this.fireTableDataChanged();
		}
	}
	
	public void cargaForos(){
		try{
				listadoDeForos = foroDAO.getForos();
				Iterator<Foro> it = listadoDeForos.iterator();

				TablaConsultaModelForos model = (TablaConsultaModelForos)tableForo.getModel();
				model.clear();
				
				Foro f = null;
				while (it.hasNext()){
					f = it.next();
					model.addRow(f);
				}
			} catch (Exception e){
				JOptionPane.showMessageDialog(this,e.getMessage(),"ERROR AL CARGAR LOS FOROS",
						JOptionPane.ERROR_MESSAGE);
			}
	}
	
	class TablaConsultaModelAnuncios extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		// Columnas de la tabla
		private String[] columnas = {"ID ANUNCIO", "TÍTULO", "FECHA DE CREACIÓN"};
		// Datos que muestra la tabla
		private ArrayList<Foro> data = new ArrayList<Foro>();

		public int getColumnCount() {
			return columnas.length;
		}
		public int getRowCount() {
			return data.size();
		}
		public String getColumnName(int col) {
			return columnas[col];
		}
		// Este método se dispara cada vez que la tabla necesita el valor de un campo
		public Object getValueAt(int row, int col) {
			Foro g = data.get(row);
			switch(col){
			case 0: return g.getId();
			case 1: return g.getTema();
			case 2: return new SimpleDateFormat("dd / MM / yyyy").format(g.getFecha());
			default: return null;
			}
		}
		public void clear(){
			data.clear();
		}
		/*
		 * JTable uses this method to determine the default renderer/
		 * editor for each cell. If we didn't implement this method,
		 * then the last column would contain text ("true"/"false"),
		 * rather than a check box.
		 */
		public Class<? extends Object>getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}
		public void addRow(Foro row) {
			data.add(row);
			this.fireTableDataChanged();
		}
		public void delRow(int row) {
			data.remove(row);
			this.fireTableDataChanged();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void cargaAnuncios(){
		try{
//				listadoDeAnuncios = foroDAO.getForos();
//				Iterator<Foro> it = listadoDeForos.iterator();

				TablaConsultaModelAnuncios model = (TablaConsultaModelAnuncios)tableTablonAnuncios.getModel();
				model.clear();
				
				Foro f1 = new Foro();
				f1.setId(1);
				f1.setTema("NORMAS");
				f1.setFecha(new Date("9/9/2012"));	
				model.addRow(f1);
				Foro f2 = new Foro();
				f2.setId(2);
				f2.setTema("RECOMENDACIONES");
				f2.setFecha(new Date("18/2/2012"));	
				model.addRow(f2);
				Foro f3 = new Foro();
				f3.setId(1);
				f3.setTema("FUNCIONAMIENTO");
				f3.setFecha(new Date("28/12/2011"));	
				model.addRow(f3);

			} catch (Exception e){
				JOptionPane.showMessageDialog(this,e.getMessage(),"ERROR AL CARGAR LOS ANUNCIOS",
						JOptionPane.ERROR_MESSAGE);
			}
	}
	
	private void ocultarColumnasJTable(JTable tbl, int columna[])
    {
        for(int i=0;i<columna.length;i++)
        {
             tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
             tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
             tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
             tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
        }
    }
}
