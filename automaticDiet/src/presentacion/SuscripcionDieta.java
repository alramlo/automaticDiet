package presentacion;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.util.List;

import javax.swing.JList;
import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

import servicio.Controlador;
import modelo.Dieta;
import javax.swing.JScrollPane;


public class SuscripcionDieta extends JFrame {

	private JPanel contentPane;
	private Controlador control;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuscripcionDieta frame = new SuscripcionDieta(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SuscripcionDieta(Dieta dietaModificar) {
		
		try{
		control = Controlador.dameControlador();
		
		setResizable(false);
		setTitle("A\u00F1adir / modificar Suscripci\u00F3n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SuscripcionDieta.class.getResource("/iconos/platos.png")));
		setBounds(100, 100, 391, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		DefaultListModel<Dieta> modelo = new DefaultListModel<Dieta>();
		List<Dieta> listaDietas = control.getDietasLibres();
		System.out.println("Dietas libres: "+listaDietas.size());
		for(Dieta d : listaDietas)
			modelo.addElement(d);
		
		JLabel lblNewLabel = new JLabel("Dieta");
		lblNewLabel.setBounds(32, 23, 46, 14);
		contentPane.add(lblNewLabel);
		
		JDateChooser fecha = new JDateChooser();
		fecha.setBounds(206, 48, 143, 28);
		contentPane.add(fecha);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setBounds(206, 23, 67, 14);
		contentPane.add(lblFechaInicio);
		
		JLabel lblDuracinEnSemanas = new JLabel("Duraci\u00F3n en Semanas");
		lblDuracinEnSemanas.setBounds(206, 97, 110, 14);
		contentPane.add(lblDuracinEnSemanas);
		
		JSpinner duracion = new JSpinner();
		duracion.setBounds(206, 118, 55, 28);
		contentPane.add(duracion);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(32, 188, 67, 14);
		contentPane.add(lblDescripcin);
		
		JTextPane descripcion = new JTextPane();
		descripcion.setBounds(32, 213, 336, 112);
		contentPane.add(descripcion);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(32, 173, 336, 14);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(32, 350, 336, 14);
		contentPane.add(separator_1);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(174, 375, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(273, 375, 89, 23);
		contentPane.add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 47, 143, 101);
		contentPane.add(scrollPane);
		
		JList<Dieta> listDietas = new JList();
		listDietas.setBounds(32, 77, 160, -25);
		listDietas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDietas.setModel(modelo);
		scrollPane.setViewportView(listDietas);
		//contentPane.add(listDietas);
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
