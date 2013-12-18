package presentacion;

import java.awt.Dimension;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JPanel;

import modelo.Dieta;
import modelo.Plato;
import modelo.Usuario;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;
import servicio.Controlador;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionDietas extends JPanel {

	/**
	 * Create the panel.
	 */

	private Controlador control;
	private Usuario userConected;
	private JButton buttonAnadir;
	private JButton buttonModificar;
	private final JButton btnEliminar = new JButton("Eliminar");
	private JTable table;
	private List<Dieta> listaDietas;
	private TableModel tableModel;
	

	public GestionDietas() {

		setSize(new Dimension(800, 684));
		this.setSize(800, 600);
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		buttonAnadir = new JButton("A\u00F1adir");
		buttonAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SuscripcionDieta ventana = new SuscripcionDieta(null);
				ventana.setVisible(true);
				//piAux=control.getPi();
				//if(piAux!=null){
			}
		});
		panel.add(buttonAnadir);

		buttonModificar = new JButton("Modificar");
		buttonModificar.setEnabled(false);
		panel.add(buttonModificar);
		btnEliminar.setEnabled(false);
		panel.add(btnEliminar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				System.out.println("Modificamos la tabla");
				//System.out.println((Date)table.getValueAt(table.getSelectedRow(), 2));
				Dieta dietaAux =  control.getDietaPorNombre(table.getValueAt(table.getSelectedRow(), 0).toString());
				Date fechaActual = new Date();
				if(fechaActual.compareTo(dietaAux.getFechaInicial())<0){
					buttonModificar.setEnabled(true);
					btnEliminar.setEnabled(true);
				}
				else{
					buttonModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
					
				}
				
			}
		});
		scrollPane.setViewportView(table);
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//table.setFillsViewportHeight(true);
		//table.setEnabled(false);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		ListSelectionModel lsm = table.getSelectionModel();
		
		

		try {
			control = Controlador.dameControlador();
			userConected = control.getUsuarioActual();
			poblar();
			tableModel=table.getModel();
			
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		
	}

	void poblar() {
		try {
			DateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
			Date formattedDate;
//			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
//			tcr.setHorizontalAlignment(SwingConstants.CENTER);
//			table.getColumnModel().getColumn(0).setCellRenderer(tcr);
//			table.getColumnModel().getColumn(1).setCellRenderer(tcr);
//			table.getColumnModel().getColumn(2).setCellRenderer(tcr);
//			table.getColumnModel().getColumn(3).setCellRenderer(tcr);
			listaDietas = control.getListDietas(userConected);
			Object[][] o = new Object[listaDietas.size()][5];

			for (int i = 0; i < listaDietas.size(); i++) {
				table.setRowHeight(i, 100);
				Dieta dieta = listaDietas.get(i);

				
				o[i][0] = dieta.getNombre();
				o[i][1] = dieta.getDescripcion();
				o[i][2] = dateFormat.format(dieta.getFechaInicial());
				o[i][3] = dateFormat.format(dieta.getFechaFinal());

			}
			table.setModel(
					new DefaultTableModel(o, new String[] { "NOMBRE","DESCRIPCIÓN", "FECHA INICIO", "FECHA FIN" }){
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
					    public boolean isCellEditable(int row, int column) {
					       //all cells false
					       return false;
					    }
					}
			);
			for (int i = 0; i < listaDietas.size(); i++)
				table.setRowHeight(i, 75);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
