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
import javax.swing.JButton;
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
import javax.swing.JLabel;
import javax.swing.ImageIcon;

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
	private Mensaje mens;
	

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
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelBotonesGrupos.add(horizontalStrut);
		
		JButton btnNuevoGrupo = new JButton("Nuevo Grupo");
		btnNuevoGrupo.setIcon(new ImageIcon(GestionGruposPorAdmin.class.getResource("/iconos/add-icon1x16.gif")));
		panelBotonesGrupos.add(btnNuevoGrupo);
		
		JScrollPane scrollPane = new JScrollPane();
		panelGrupos.add(scrollPane, BorderLayout.CENTER);
		
		modelGrupos = new TablaConsultaModelGrupos();
		tableGrupos = new JTable();
		tableGrupos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tableGrupos.rowAtPoint(e.getPoint());
				String cod = String.valueOf(tableGrupos.getValueAt(fila, 0));
				int idGrupo = Integer.valueOf(cod);
				ListaMensajesPorGrupo listaMensajes = new ListaMensajesPorGrupo(idGrupo);
				listaMensajes.cargaMensajes();
				listaMensajes.setModal(true);
				listaMensajes.setVisible(true);
			}
		});
		tableGrupos.setRowHeight(40);
		tableGrupos.setModel(modelGrupos);
		scrollPane.setViewportView(tableGrupos);
		
		JPanel panelPostsDenunciados = new JPanel();
		panelPostsDenunciados.setBorder(new TitledBorder(null, "Posts denunciados:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		panelPostsDenunciados.setBounds(10, 314, 780, 275);
		panelContenedor.add(panelPostsDenunciados);
		panelPostsDenunciados.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotonesPostsDenunciados = new JPanel();
		FlowLayout fl_panelBotonesPostsDenunciados = (FlowLayout) panelBotonesPostsDenunciados.getLayout();
		fl_panelBotonesPostsDenunciados.setAlignment(FlowLayout.RIGHT);
		panelPostsDenunciados.add(panelBotonesPostsDenunciados, BorderLayout.SOUTH);
		
		JLabel label = new JLabel("Buscar:");
		panelBotonesPostsDenunciados.add(label);
		
		tfBuscadorPostsDenunciados = new JTextField();
		tfBuscadorPostsDenunciados.setText("");
		panelBotonesPostsDenunciados.add(tfBuscadorPostsDenunciados);
		tfBuscadorPostsDenunciados.setColumns(10);
		
		JScrollPane scrollPanePostsDenunciados = new JScrollPane();
		panelPostsDenunciados.add(scrollPanePostsDenunciados, BorderLayout.CENTER);
		
		modelPostsDenunciados = new TablaConsultaModelPostsDenunciados();
		tablePostsDenunciados = new JTable();
		tablePostsDenunciados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablePostsDenunciados.rowAtPoint(e.getPoint());
				String cod = String.valueOf(tablePostsDenunciados.getValueAt(fila, 6));
				int idMensaje = Integer.valueOf(cod);
				mens = mensajeDAO.getMensajeById(idMensaje);
				VerMensajeDenunciado mensajeDenunciado = new VerMensajeDenunciado(mens);
				mensajeDenunciado.setModal(true);
				mensajeDenunciado.setLocationRelativeTo(null);
				mensajeDenunciado.setVisible(true);
				cargaDenuncias();
				tablePostsDenunciados.revalidate();
				tablePostsDenunciados.repaint();
			}
		});
		tablePostsDenunciados.setRowHeight(40);
		tablePostsDenunciados.setModel(modelPostsDenunciados);
		scrollPanePostsDenunciados.setViewportView(tablePostsDenunciados);

	}
	
	class TablaConsultaModelGrupos extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		// Columnas de la tabla
		private String[] columnas = {"CÓDIGO", "NOMBRE DEL GRUPO", "PRIVADO", "CREADOR", "CIUDAD", "PAIS"};
		// Datos que muestra la tabla
		private ArrayList<Grupo> data = new ArrayList<Grupo>();

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
			case 2: return g.getPrivado();
			case 3: return g.getUsuario().getNombre();
			case 4: return g.getCiudad();
			case 5: return g.getPais();
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
		public void addRow(Grupo row) {
			data.add(row);
			this.fireTableDataChanged();
		}
		public void delRow(int row) {
			data.remove(row);
			this.fireTableDataChanged();
		}
	}
	
	class TablaConsultaModelPostsDenunciados extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		// Columnas de la tabla
		private String[] columnas = {"ID DENUNCIA", "MOTIVO", "DESCRIPCION", "FECHA", "USUARIO", "MENSAJE", "ID MENSAJE"};
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
			case 0: return postDenunciado.getId();
			case 1: return postDenunciado.getMotivo();
			case 2: return postDenunciado.getExplicacion();
			case 3: return postDenunciado.getFecha();
			case 4: return postDenunciado.getUsuario().getNombre()+" "+postDenunciado.getUsuario().getApellidos();
			case 5: return postDenunciado.getMensaje().getContenido();
			case 6: return postDenunciado.getMensaje().getId();
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
	
	public void cargaDenuncias(){
		try{
				
				listadoDeDenuncias = denunciaDAO.getDenuncias();
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
}
