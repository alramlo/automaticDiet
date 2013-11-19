package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JSeparator;

import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;
import servicio.Controlador;
import modelo.Ingrediente;
import modelo.Plato;
import modelo.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.Toolkit;

public class NuevoPlato2 extends JFrame {

	private JPanel contentPane;
	private JTextField tNombre;
	private Plato plato;
	private static Controlador control;
	private JTextField tCalorias;
	private JTextField tPrecio;
	private JTextField tTiempo;
	private List<Ingrediente> ingredientes;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoPlato2 frame = new NuevoPlato2(null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public NuevoPlato2(Plato p, Usuario u) throws Exception {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoPlato2.class.getResource("/iconos/platos.png")));
		
		//Añadimos el plato pasado por parametro
		plato=p;
		
		//Pedimos el controlador
		control=Controlador.dameControlador();
				
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lNombre = new JLabel("Nombre del plato: ");
		lNombre.setFont(new Font("Arial", Font.BOLD, 16));
		lNombre.setBounds(37, 43, 140, 19);
		getContentPane().add(lNombre);
		
		tNombre = new JTextField();
		tNombre.setBounds(176, 44, 186, 20);
		getContentPane().add(tNombre);
		tNombre.setColumns(10);
		
		JLabel lAutor = new JLabel("Autor: ");
		lAutor.setFont(new Font("Arial", Font.BOLD, 16));
		lAutor.setBounds(37, 16, 66, 14);
		getContentPane().add(lAutor);
		
		JLabel lAutor2 = new JLabel("Autor Leido...");
		lAutor2.setBounds(95, 18, 122, 14);
		getContentPane().add(lAutor2);
		
		JLabel lIngredientes = new JLabel("Ingredientes:");
		lIngredientes.setFont(new Font("Arial", Font.BOLD, 16));
		lIngredientes.setBounds(37, 102, 140, 20);
		getContentPane().add(lIngredientes);
		
		final DefaultListModel<Ingrediente> modelo = new DefaultListModel<Ingrediente>();
		final JList<Ingrediente> listIngredientes = new JList<Ingrediente>();
		listIngredientes.setModel(modelo);
		listIngredientes.setBounds(37, 133, 282, 155);
		getContentPane().add(listIngredientes);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(listIngredientes.getSelectedValue()!=null)
					modelo.removeElementAt(listIngredientes.getSelectedIndex());
				
			}
		});
		btnEliminar.setBounds(329, 164, 89, 23);
		getContentPane().add(btnEliminar);
		
		JButton btnBuscar = new JButton("A\u00F1adir");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AnadirIngrediente2 ventana = new AnadirIngrediente2(plato);
					ventana.setModal(true);
					ventana.setVisible(true);
					control.getPi();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnBuscar.setBounds(329, 130, 89, 23);
		getContentPane().add(btnBuscar);
		
		JLabel lElaboracion = new JLabel("Elaboraci\u00F3n:");
		lElaboracion.setFont(new Font("Arial", Font.BOLD, 16));
		lElaboracion.setBounds(37, 324, 110, 20);
		contentPane.add(lElaboracion);
		
		JTextPane tElaboracion = new JTextPane();
		tElaboracion.setBounds(37, 344, 282, 108);
		contentPane.add(tElaboracion);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(37, 92, 584, 14);
		contentPane.add(separator1);
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(27, 299, 594, 14);
		contentPane.add(separator2);
		
		JLabel lImagen = new JLabel("Imagen:");
		lImagen.setFont(new Font("Arial", Font.BOLD, 16));
		lImagen.setBounds(396, 324, 110, 20);
		contentPane.add(lImagen);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(34, 484, 587, 19);
		contentPane.add(separator);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnGuardar.setBounds(532, 511, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dispose(); 
				
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCancelar.setBounds(433, 511, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblCalorias = new JLabel("Calorias:");
		lblCalorias.setFont(new Font("Arial", Font.BOLD, 16));
		lblCalorias.setBounds(422, 206, 79, 20);
		contentPane.add(lblCalorias);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Arial", Font.BOLD, 16));
		lblPrecio.setBounds(422, 237, 79, 20);
		contentPane.add(lblPrecio);
		
		JLabel lblTiempo = new JLabel("Tiempo:");
		lblTiempo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTiempo.setBounds(422, 268, 79, 20);
		contentPane.add(lblTiempo);
		
		tCalorias = new JTextField();
		tCalorias.setEditable(false);
		tCalorias.setColumns(10);
		tCalorias.setBounds(511, 206, 79, 20);
		contentPane.add(tCalorias);
		
		JLabel lContImg = new JLabel("");
		lContImg.setBounds(393, 355, 110, 97);
		contentPane.add(lContImg);
		
		tPrecio = new JTextField();
		tPrecio.setEditable(false);
		tPrecio.setColumns(10);
		tPrecio.setBounds(509, 237, 79, 20);
		contentPane.add(tPrecio);
		
		tTiempo = new JTextField();
		tTiempo.setEditable(false);
		tTiempo.setColumns(10);
		tTiempo.setBounds(509, 268, 79, 20);
		contentPane.add(tTiempo);
		
		JLabel label = new JLabel("\u20AC");
		label.setBounds(593, 242, 46, 14);
		contentPane.add(label);
		
		//Si el usuario es diferente de null se carga
		if(u!=null)
			lAutor2.setText(u.getNombre());
		
		//Si el plato pasado por parametro es diferente a null lo cargamos
		if(p!=null){
			
			
			//Sacamos las horas y los minutos de elaboración
			if(p.getTiempo()!=null){
				System.out.println("Tiempo: "+p.getTiempo());
				DateFormat f = new SimpleDateFormat("k:mm");
				try{
					Date fecha = (Date) f.parse(p.getTiempo().toString());
					tTiempo.setText(fecha.toString());
				}catch(ParseException e){
					System.out.println("Error: "+e);
					e.printStackTrace();
				}
			}
			
			//cargar imagen
			if(p.getImagen()!=null){
				File file=null;
				try {
					file = new File("automaticDiet");
					FileOutputStream fos = new FileOutputStream (file);
					fos.write(p.getImagen());
					fos.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ImageIcon im=null;
	    		BufferedImage buffer;
				try {
					buffer = ImageIO.read(file);
					im = new ImageIcon(buffer);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		Image image=im.getImage();
	    		Image newImage = image.getScaledInstance(408, 402, java.awt.Image.SCALE_SMOOTH);
	    		lContImg.setIcon(new ImageIcon(newImage));
			}
    		
    		//Cargamos el Autor
			if(p.getUsuario()!=null){
				lAutor2.setText(p.getUsuario().getNombre());
			}
    		
    		//Cargamos los ingredientes
			if(p.getNombre()!=null){
				ingredientes=control.ingredientesPorPlato2(p.getNombre());
				System.out.println("Numero de ingredientes: "+ingredientes.size());
				if(ingredientes!=null){
					for(Ingrediente i: ingredientes)
						{
						modelo.addElement(i);
						}
					//listIngredientes.setModel(modelo);
					System.out.println("La lista es visible= "+listIngredientes.isVisible());
				}
			}
			
			//Cargamos la elaboración
			if(p.getElaboracion()!=null)
				tElaboracion.setText(p.getElaboracion());
			
			
		}
		
	}

	protected static void DISPOSE_ON_CLOSE() {
		// TODO Auto-generated method stub
		
	}
}
