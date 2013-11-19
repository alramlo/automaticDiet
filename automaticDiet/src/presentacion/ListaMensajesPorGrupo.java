package presentacion;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import modelo.Mensaje;
import persistencia.MensajeDAO;

public class ListaMensajesPorGrupo extends JDialog {

private static final long serialVersionUID = 1L;
	
	private JTable tablaMensajesPorGrupo;
	private TableModel tablaMensajesPorGrupoModel;
	private List<Mensaje> listadoMensajes;
	private MensajeDAO mensajeDAO;
	private int idGrupo;


	public ListaMensajesPorGrupo(int id) {
		this.idGrupo = id;
		listadoMensajes = null;
		mensajeDAO = new MensajeDAO();
		
		setResizable(false);
		setPreferredSize(new Dimension(600, 500));
		setBounds(100, 100, 835, 475);
		setLocationRelativeTo(null);
		{
			tablaMensajesPorGrupoModel = new TablaMensajesPorGrupoModel();
			tablaMensajesPorGrupo = new JTable();
			
			tablaMensajesPorGrupo.setBounds(10, 11, 799, 415);
			tablaMensajesPorGrupo.setModel(tablaMensajesPorGrupoModel);
			tablaMensajesPorGrupo.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			tablaMensajesPorGrupo.setRowHeight(38);	

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.LEFT );
			for(int i=0; i<4; i++)
			{
				tablaMensajesPorGrupo.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
			}
			
		}
		
		getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane(tablaMensajesPorGrupo);
//		scrollPane.add(tablaMensajesPorGrupo);
		scrollPane.setBounds(10, 10, 799, 417);
		getContentPane().add(scrollPane);
		
		setTitle("Posts:");
		
		
	}
	
	class TablaMensajesPorGrupoModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		// Columnas de la tabla
		private String[] columnas = {"ID MENSAJE", "CONTENIDO", "FECHA", "CREADOR"};
		// Datos que muestra la tabla
		private ArrayList<Mensaje> data = new ArrayList<Mensaje>();

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
			Mensaje men = data.get(row);
			switch(col){
			case 0: return men.getId();
			case 1: return men.getContenido();
			case 2: return men.getFecha();
			case 3: return men.getUsuario().getNombre()+" "+men.getUsuario().getApellidos();
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
		public void addRow(Mensaje row) {
			data.add(row);
			this.fireTableDataChanged();
		}
		public void delRow(int row) {
			data.remove(row);
			this.fireTableDataChanged();
		}
	}
	
	public void cargaMensajes(){
		try{
				
				listadoMensajes = mensajeDAO.getMensajesByIdGrupo(idGrupo);
				Iterator<Mensaje> it = listadoMensajes.iterator();
			
//				List<Mensaje> lm = new ArrayList<Mensaje>();
//				Mensaje m = new Mensaje();
//				Grupo g = new Grupo();
//				g.setId(5);
//				g.setNombre("Grupo cinco");
//				m.setContenido("Hola esto es el contenido");
//				m.setId(999);	
//				m.setGrupo(g);
//				lm.add(m);
//				Iterator<Mensaje> it = lm.iterator();
				
				TablaMensajesPorGrupoModel model = (TablaMensajesPorGrupoModel)tablaMensajesPorGrupo.getModel();
				model.clear();
				
				Mensaje men = null;
				while (it.hasNext()){
					men = it.next();
					model.addRow(men);
				}
			} catch (Exception e){
				JOptionPane.showMessageDialog(this,e.getMessage(),"ERROR AL CARGAR MENSAJES",
						JOptionPane.ERROR_MESSAGE);
			}
	}

}
