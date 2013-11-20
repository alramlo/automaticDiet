package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.List;

import javax.swing.JList;
import javax.swing.JTextField;

import modelo.Ingrediente;
import modelo.Plato;
import modelo.PlatoIngrediente;
import excepciones.DominioExcepcion;
import servicio.Controlador;

import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class AnadirIngrediente2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Controlador control;
	private PlatoIngrediente platoIngrediente;
	private Plato plato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AnadirIngrediente2 dialog = new AnadirIngrediente2(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws DominioExcepcion 
	 */
	public AnadirIngrediente2(final Plato p) throws Exception {
		
		
		//Obtener el controlador
		control=Controlador.dameControlador();
		
		plato=p;
		
		setTitle("A\u00F1adir Ingrediente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnadirIngrediente2.class.getResource("/iconos/add-icon1x16.gif")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblIngredientes = new JLabel("Ingredientes");
		lblIngredientes.setBounds(32, 29, 107, 30);
		lblIngredientes.setFont(new Font("Arial", Font.BOLD, 16));
		contentPanel.add(lblIngredientes);
		
		final JList<Ingrediente> listIngredientes = new JList<Ingrediente>();
		listIngredientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<Ingrediente> modelo = new DefaultListModel<Ingrediente>();
		listIngredientes.setMaximumSize(new Dimension(800, 600));
		listIngredientes.setMinimumSize(new Dimension(50, 50));
		listIngredientes.setBounds(32, 59, 269, 137);
		
		List<Ingrediente> listaIngrediente = control.getIngredientes();
		for(Ingrediente i : listaIngrediente)
			modelo.addElement(i);
		
		listIngredientes.setModel(modelo);	
		//contentPanel.add(listIngredientes);
		
			JLabel lblCantidad = new JLabel("Cantidad");
			lblCantidad.setBounds(327, 29, 94, 30);
			lblCantidad.setFont(new Font("Arial", Font.BOLD, 16));
			contentPanel.add(lblCantidad);
		
		
		JScrollPane scrollPane = new JScrollPane(listIngredientes);
		scrollPane.setBounds(30, 57, 271, 139);
		contentPanel.add(scrollPane);
		
		final JList<Integer> listCantidad = new JList<Integer>();
		listCantidad.setBounds(327, 59, 69, 120);
		listCantidad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<Integer> modeloC = new DefaultListModel<Integer>();
		for(int i=1;i<7;i++)
			modeloC.addElement(i);
		listCantidad.setModel(modeloC);
		contentPanel.add(listCantidad);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						control.setPi(null);
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					if(listIngredientes.getSelectedValue()!=null && listCantidad.getSelectedValue()!=null){
						PlatoIngrediente pi = new PlatoIngrediente();
						pi.setPlato(p);
						pi.setIngrediente(listIngredientes.getSelectedValue());
						pi.setCantidad(listCantidad.getSelectedValue());
						control.setPi(pi);
					}
					setVisible(false);
					dispose();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
