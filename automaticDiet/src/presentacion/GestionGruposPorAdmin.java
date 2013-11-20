package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import modelo.Denuncia;
import modelo.Grupo;
import modelo.Mensaje;
import persistencia.DenunciaDAO;
import persistencia.GrupoDAO;
import persistencia.MensajeDAO;

public class GestionGruposPorAdmin extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfBuscadorGrupos;
	private JTextField tfBuscadorPostsDenunciados;
	private JTable tableGrupos;
	private JTable tablePostsDenunciados;
	private TableModel modelGrupos;
	private TableModel modelPostsDenunciados;
	private GrupoDAO grupoDAO;
	private DenunciaDAO denunciaDAO;
	private MensajeDAO mensajeDAO;
	private List<Grupo> listadoDeGrupos;
	private List<Denuncia> listadoDeDenuncias;
	private Mensaje mensActual;;
	

	/**
	 * Create the panel.
	 */
	public GestionGruposPorAdmin() {

		grupoDAO = new GrupoDAO();
		denunciaDAO = new DenunciaDAO();
		mensajeDAO = new MensajeDAO();
		
		setBounds(new Rectangle(0, 0, 800, 600));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelContenedor = new JPanel();
		add(panelContenedor, BorderLayout.CENTER);
		panelContenedor.setLayout(null);
		
		JPanel panelGrupos = new JPanel();
		panelGrupos.setBorder(new TitledBorder(null, "Grupos:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		panelGrupos.setBounds(10, 11, 780, 275);
		panelContenedor.add(panelGrupos);
		panelGrupos.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotonesGrupos = new JPanel();
		FlowLayout fl_panelBotonesGrupos = (FlowLayout) panelBotonesGrupos.getLayout();
		fl_panelBotonesGrupos.setAlignOnBaseline(true);
		fl_panelBotonesGrupos.setAlignment(FlowLayout.RIGHT);
		panelGrupos.add(panelBotonesGrupos, BorderLayout.SOUTH);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		panelBotonesGrupos.add(lblBuscar);
		
		tfBuscadorGrupos = new JTextField();
		panelBotonesGrupos.add(tfBuscadorGrupos);
		tfBuscadorGrupos.setColumns(10);
		
		JPanel panelPostsDenunciados = new JPanel();
		panelPostsDenunciados.setBorder(new TitledBorder(null, "Posts denunciados:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		panelPostsDenunciados.setBounds(10, 314, 780, 275);
		panelContenedor.add(panelPostsDenunciados);
		panelPostsDenunciados.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotonesPostsDenunciados = new JPanel();
		FlowLayout fl_panelBotonesPostsDenunciados = (FlowLayout) panelBotonesPostsDenunciados.getLayout();
		fl_panelBotonesPostsDenunciados.setAlignment(FlowLayout.RIGHT);
		panelPostsDenunciados.add(panelBotonesPostsDenunciados, BorderLayout.SOUTH);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelBotonesGrupos.add(horizontalStrut);
		
		JButton btnNuevoGrupo = new JButton("Nuevo Grupo");
		btnNuevoGrupo.setIcon(new ImageIcon(GestionGruposPorAdmin.class.getResource("/iconos/add-icon1x16.gif")));
		panelBotonesGrupos.add(btnNuevoGrupo);
		
		JScrollPane scrollPane = new JScrollPane();
		panelGrupos.add(scrollPane, BorderLayout.CENTER);
		modelGrupos = new TablaConsultaModelGrupos();
		tableGrupos = new JTable();
		
		JScrollPane scrollPanePostsDenunciados = new JScrollPane();
		panelPostsDenunciados.add(scrollPanePostsDenunciados, BorderLayout.CENTER);
		modelPostsDenunciados = new TablaConsultaModelPostsDenunciados();
		tablePostsDenunciados = new JTable();
		
		tableGrupos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Dentro del evento de la tabla Grupos !!!");
				
				int fila = tableGrupos.rowAtPoint(e.getPoint());
				String cod = String.valueOf(tableGrupos.getValueAt(fila, 0));
				System.out.println(String.valueOf(tableGrupos.getValueAt(fila, 0)));
				int idGrupo = Integer.valueOf(cod);
				
				if(e.getClickCount()==1){
					System.out.println("Un solo click !!!");
					cargaDenunciasPorGrupo(idGrupo);
					tablePostsDenunciados.revalidate();
					tablePostsDenunciados.repaint();
				}
				if(e.getClickCount()>=2){
					System.out.println("Dos o más clicks !!!");
					ListaMensajesPorGrupo listaMensajes = new ListaMensajesPorGrupo(idGrupo);
					listaMensajes.cargaMensajes();
					listaMensajes.setModal(true);
					listaMensajes.setVisible(true);
				}
			}
		});
		tableGrupos.setRowHeight(40);
		tableGrupos.setModel(modelGrupos);
		ocultarColumnasJTable(tableGrupos, new int[]{0});
		scrollPane.setViewportView(tableGrupos);
	
		JLabel label = new JLabel("Buscar:");
		panelBotonesPostsDenunciados.add(label);
		
		tfBuscadorPostsDenunciados = new JTextField();
		tfBuscadorPostsDenunciados.setText("");
		panelBotonesPostsDenunciados.add(tfBuscadorPostsDenunciados);
		tfBuscadorPostsDenunciados.setColumns(10);

		tablePostsDenunciados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Dentro del evento Ver Mensaje Denunciado:");
				
				int fila = tablePostsDenunciados.rowAtPoint(e.getPoint());

				String codMensaje = String.valueOf(tablePostsDenunciados.getValueAt(fila, 0));
				int idMensaje = Integer.valueOf(codMensaje);
				System.out.println("Id Mensaje: "+idMensaje);
				String codGrupo = String.valueOf(tablePostsDenunciados.getValueAt(fila, 1));
				int idGrupo = Integer.valueOf(codGrupo);
				System.out.println("Id Grupo: "+idGrupo);

				mensActual = mensajeDAO.getMensajeById(idMensaje);
				VerMensajeDenunciado mensajeDenunciado = new VerMensajeDenunciado(mensActual);
				mensajeDenunciado.setModal(true);
				mensajeDenunciado.setLocationRelativeTo(null);
				mensajeDenunciado.setVisible(true);
				
				cargaDenunciasPorGrupo(idGrupo);
				tablePostsDenunciados.revalidate();
				tablePostsDenunciados.repaint();

				refrescarTablaGrupos();

			}
		});
		tablePostsDenunciados.setRowHeight(40);
		tablePostsDenunciados.setModel(modelPostsDenunciados);
		ocultarColumnasJTable(tablePostsDenunciados, new int[]{0,1});
		scrollPanePostsDenunciados.setViewportView(tablePostsDenunciados);

	}
	
	class TablaConsultaModelGrupos extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		// Columnas de la tabla
		private String[] columnas = {"CÓDIGO", "NOMBRE DEL GRUPO", "CREADOR", "CIUDAD", "PAIS", "DENUNCIAS"};
		// Datos que muestra la tabla
		private ArrayList<Grupo> data = new ArrayList<Grupo>();
		private ArrayList<Long> numDenuncias = new ArrayList<Long>();
		
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
			Grupo g = data.get(row);
			switch(col){
			case 0: return g.getId();
			case 1: return g.getNombre();
			case 2: return g.getUsuario().getNombre();
			case 3: return g.getCiudad();
			case 4: return g.getPais();
			case 5: return numDenuncias.get(row);
			default: return null;
			}
		}
		public void clear(){
			data.clear();
			numDenuncias.clear();
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
		public void addRow(Grupo row) {
			data.add(row);
			numDenuncias.add(denunciaDAO.getNumDenunciasPorGrupo(row.getId()));
			this.fireTableDataChanged();
		}
		public void delRow(int row) {
			data.remove(row);
			numDenuncias.remove(row);
			this.fireTableDataChanged();
		}
	}
	
	class TablaConsultaModelPostsDenunciados extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		// Columnas de la tabla
		private String[] columnas = {"ID MENSAJE","ID GRUPO", "MOTIVO", "DESCRIPCION", "FECHA", "USUARIO"};
		// Datos que muestra la tabla
		private ArrayList<Denuncia> data = new ArrayList<Denuncia>();

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
			Denuncia postDenunciado = data.get(row);
			switch(col){
			case 0: return postDenunciado.getMensaje().getId();
			case 1: return postDenunciado.getMensaje().getGrupo().getId();
			case 2: return postDenunciado.getMotivo();
			case 3: return postDenunciado.getExplicacion();
			case 4: return postDenunciado.getFecha();
			case 5: return postDenunciado.getUsuario().getNombre()+" "+postDenunciado.getUsuario().getApellidos();
//			case 6: return postDenunciado.getMensaje().getContenido();
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
		public void addRow(Denuncia row) {
			data.add(row);
			this.fireTableDataChanged();
		}
		public void delRow(int row) {
			data.remove(row);
			this.fireTableDataChanged();
		}
	}
	
	public void cargaGrupos(){
		try{
				
				listadoDeGrupos = grupoDAO.getGrupos();
				Iterator<Grupo> it = listadoDeGrupos.iterator();

				TablaConsultaModelGrupos model = (TablaConsultaModelGrupos)tableGrupos.getModel();
//				model.obtenerNumDenuncias();
				model.clear();
				
				Grupo g = null;
				while (it.hasNext()){
					g = it.next();
					model.addRow(g);
				}
			} catch (Exception e){
				JOptionPane.showMessageDialog(this,e.getMessage(),"ERROR AL CARGAR LOS GRUPOS",
						JOptionPane.ERROR_MESSAGE);
			}
	}
	
//	public void cargaDenuncias(){
//		try{
//				
//				listadoDeDenuncias = denunciaDAO.getDenuncias();
//				Iterator<Denuncia> it = listadoDeDenuncias.iterator();
//
//				TablaConsultaModelPostsDenunciados model = (TablaConsultaModelPostsDenunciados)tablePostsDenunciados.getModel();
//				model.clear();
//				
//				Denuncia d = null;
//				while (it.hasNext()){
//					d = it.next();
//					model.addRow(d);
//				}
//			} catch (Exception e){
//				JOptionPane.showMessageDialog(this,e.getMessage(),"ERROR AL CARGAR LOS POST DENUNCIADOS",
//						JOptionPane.ERROR_MESSAGE);
//			}
//	}
	
	public void cargaDenunciasPorGrupo(int idGrupo){
		try{
				
				listadoDeDenuncias = denunciaDAO.getDenunciaPorGrupo(idGrupo);
				Iterator<Denuncia> it = listadoDeDenuncias.iterator();

				TablaConsultaModelPostsDenunciados model = (TablaConsultaModelPostsDenunciados)tablePostsDenunciados.getModel();
				model.clear();
				
				Denuncia d = null;
				while (it.hasNext()){
					d = it.next();
					model.addRow(d);
				}
			} catch (Exception e){
				JOptionPane.showMessageDialog(this,e.getMessage(),"ERROR AL CARGAR LOS POST DENUNCIADOS",
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
	
	private void refrescarTablaGrupos()
	{	
		cargaGrupos();	
		tableGrupos.revalidate();
		tableGrupos.repaint();
	}
}
