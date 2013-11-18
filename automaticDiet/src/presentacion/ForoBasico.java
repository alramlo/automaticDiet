package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
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
	private ForoDAO foroDAO;
	private List<Foro> listadoDeForos;

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
		panelInf.add(btnNuevoTema);
		
		JPanel panelForo = new JPanel();
		add(panelForo, BorderLayout.CENTER);
		panelForo.setLayout(null);
		
		JScrollPane scrollPaneTablonAnuncios = new JScrollPane();
		scrollPaneTablonAnuncios.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tabl\u00F3n de anuncios:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneTablonAnuncios.setBounds(10, 11, 780, 149);
		panelForo.add(scrollPaneTablonAnuncios);
		
		tableTablonAnuncios = new JTable();
		scrollPaneTablonAnuncios.setViewportView(tableTablonAnuncios);
		
		JScrollPane scrollPaneForo = new JScrollPane();
		scrollPaneForo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Foro:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneForo.setBounds(10, 169, 780, 387);
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
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.LEFT );
		for(int i=0; i<4; i++)
		{
			tableForo.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		}
		scrollPaneForo.setViewportView(tableForo);
	}
	
	class TablaConsultaModelForos extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		// Columnas de la tabla
		private String[] columnas = {"ID TEMA", "TEMA", "VISITAR", "FECHA DE CREACIÓN"};
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
			case 2: return g.getVisitar();
			case 3: return g.getFecha();
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
//				JOptionPane.showMessageDialog(this,e.getMessage(),"ERROR AL CARGAR LOS FOROS",
//						JOptionPane.ERROR_MESSAGE);
			}
	}
}
