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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

import servicio.Controlador;
import modelo.Dieta;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class SuscripcionDieta extends JFrame {

	private JPanel contentPane;
	private Controlador control;
	private JSpinner duracion;
	private JDateChooser fecha;
	private JTextPane descripcion;
	private JList<Dieta> listDietas;
	

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
		
		fecha = new JDateChooser();
		fecha.setBounds(206, 48, 143, 28);
		contentPane.add(fecha);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setBounds(206, 23, 67, 14);
		contentPane.add(lblFechaInicio);
		
		JLabel lblDuracinEnSemanas = new JLabel("Duraci\u00F3n en dias");
		lblDuracinEnSemanas.setBounds(206, 97, 110, 14);
		contentPane.add(lblDuracinEnSemanas);
		
		duracion = new JSpinner();
		duracion.setBounds(206, 118, 55, 28);
		contentPane.add(duracion);
		duracion.setEnabled(false);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(32, 188, 67, 14);
		contentPane.add(lblDescripcin);
		
		descripcion = new JTextPane();
		descripcion.setBounds(32, 213, 336, 112);
		contentPane.add(descripcion);
		descripcion.setEnabled(false);
		
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
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				setVisible(false);
				dispose();
			}
		});
		btnCancelar.setBounds(273, 375, 89, 23);
		contentPane.add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 47, 143, 101);
		contentPane.add(scrollPane);
		
		listDietas = new JList();
		listDietas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Dieta dietaAux = control.getDietaPorNombre(listDietas.getSelectedValue().getNombre());
				descripcion.setText(dietaAux.getDescripcion());
				Long dur = control.getSemanasDieta(dietaAux.getId());
				duracion.setValue(dur.intValue());
				System.out.println("Duración: "+dur.intValue());
				
			}
		});
		listDietas.setBounds(32, 77, 160, 28);
		listDietas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDietas.setModel(modelo);
		scrollPane.setViewportView(listDietas);
		//contentPane.add(listDietas);
		
		if(dietaModificar!=null){
			
			listDietas.setVisible(false);
			scrollPane.setVisible(false);
			JTextField nombreDieta = new JTextField();
			nombreDieta.setBounds(32, 47, 143, 28);
			nombreDieta.setText(dietaModificar.getNombre());
			nombreDieta.setEditable(false);
			contentPane.add(nombreDieta);
			fecha.setDate(dietaModificar.getFechaInicial());
			descripcion.setText(dietaModificar.getDescripcion());
			//lblDuracinEnSemanas.setText(control.);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
