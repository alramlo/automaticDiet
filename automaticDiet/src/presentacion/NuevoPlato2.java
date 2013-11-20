package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextPane;
import javax.swing.JSeparator;

import persistencia.DAL;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;
import servicio.Controlador;
import modelo.Ingrediente;
import modelo.Plato;
import modelo.PlatoIngrediente;
import modelo.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.Toolkit;

import javax.swing.ListSelectionModel;

import java.awt.Color;

import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class NuevoPlato2 extends JFrame {

	private JPanel contentPane;
	private JTextField tNombre;
	private Plato plato;
	private Usuario usuario;
	private static Controlador control;
	private JList<Ingrediente> listIngredientes = new JList<Ingrediente>();
	private JTextField tCalorias;
	private JTextField tPrecio;
	private List<Ingrediente> ingredientes;
	private List<PlatoIngrediente> platoIngredienteNuevos;
	private List<Ingrediente> ingredientesEliminar;
	private PlatoIngrediente piAux;
	private Boolean esNuevo;
	private Integer contadorCalorias;
	private BigDecimal contadorPrecio;


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
		setTitle("A\u00F1adir plato");
		
		usuario=u;
		//Comprobamos si el plato es nuevo
		if(p==null){
			esNuevo=true;
			plato = new Plato();
		}
		else{
			//Nos guardamos el plato pasado por parametro
			plato=p;
			esNuevo=false;
		}
		
		contadorCalorias=0;
		contadorPrecio=new BigDecimal(0);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoPlato2.class.getResource("/iconos/platos.png")));
		platoIngredienteNuevos = new ArrayList<PlatoIngrediente>();
		ingredientesEliminar = new ArrayList<Ingrediente>();
		
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
		lNombre.setBounds(37, 50, 140, 19);
		getContentPane().add(lNombre);
		
		tNombre = new JTextField();
		tNombre.setBounds(176, 40, 186, 29);
		getContentPane().add(tNombre);
		tNombre.setColumns(10);
		
		JLabel lAutor = new JLabel("Autor: ");
		lAutor.setFont(new Font("Arial", Font.BOLD, 16));
		lAutor.setBounds(37, 16, 66, 14);
		getContentPane().add(lAutor);
		
		JLabel lAutor2 = new JLabel("Autor Leido...");
		lAutor2.setFont(new Font("Arial", Font.PLAIN, 16));
		lAutor2.setForeground(Color.BLUE);
		lAutor2.setBounds(91, 15, 122, 19);
		getContentPane().add(lAutor2);
		
		JLabel lIngredientes = new JLabel("Ingredientes:");
		lIngredientes.setFont(new Font("Arial", Font.BOLD, 16));
		lIngredientes.setBounds(37, 102, 140, 20);
		getContentPane().add(lIngredientes);
		
		final DefaultListModel<Ingrediente> modelo = new DefaultListModel<Ingrediente>();
		listIngredientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listIngredientes.setModel(modelo);
		listIngredientes.setBounds(37, 133, 282, 155);
		getContentPane().add(listIngredientes);
		
		//Bot�n eliminar ingrediente
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//si hay un ingrediente seleccionados
				if(listIngredientes.getSelectedValue()!=null){
					PlatoIngrediente piEliminar = null;
					for(PlatoIngrediente aux : platoIngredienteNuevos)
						if(aux.getIngrediente().getId()==listIngredientes.getSelectedValue().getId())
							piEliminar=aux;
					//eliminamos el platoIngredinte de la lista platoIngredienteNuevo
					platoIngredienteNuevos.remove(piEliminar);
					
					//Si el plato no es nuevo, a�adimos el ingrediente a la lista de borrar
					if(esNuevo==false){
						ingredientesEliminar.add(listIngredientes.getSelectedValue());
					}
					
					//Si el ingrediente ya hab�a sido introducido anteriormente, recuperamos el PlatoIngrediente de la base de datos
					if(piEliminar==null)
						piEliminar=control.findPlatoIngrediente(plato.getId(), listIngredientes.getSelectedValue().getId());
					
					//Decrementamos las calorias
					contadorCalorias=contadorCalorias-(piEliminar.getCantidad()*piEliminar.getIngrediente().getCalorias());
					tCalorias.setText(contadorCalorias.toString());
					
					//eliminamos el ingrediente seleccionado de la lista de ingredientes
					modelo.removeElementAt(listIngredientes.getSelectedIndex());
					
				}
			}
		});
		btnEliminar.setBounds(329, 164, 89, 29);
		getContentPane().add(btnEliminar);
		
		//Bot�n a�adir ingrediente
		JButton btnBuscar = new JButton("A\u00F1adir");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AnadirIngrediente2 ventana = new AnadirIngrediente2(plato);
					ventana.setModal(true);
					ventana.setVisible(true);
					piAux=control.getPi();
					if(piAux!=null){
						modelo.addElement(piAux.getIngrediente());
						piAux.setPlato(plato);
						platoIngredienteNuevos.add(piAux);
						contadorCalorias=contadorCalorias+(piAux.getIngrediente().getCalorias()*piAux.getCantidad());
						tCalorias.setText(contadorCalorias.toString());
						control.setPi(null);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnBuscar.setBounds(329, 130, 89, 29);
		getContentPane().add(btnBuscar);
		
		JLabel lElaboracion = new JLabel("Elaboraci\u00F3n:");
		lElaboracion.setFont(new Font("Arial", Font.BOLD, 16));
		lElaboracion.setBounds(37, 324, 110, 20);
		contentPane.add(lElaboracion);
		
		final JTextPane tElaboracion = new JTextPane();
		tElaboracion.setBounds(37, 344, 282, 129);
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
				
				//actualizamos el plato
				plato.setNombre(tNombre.getText());
				plato.setElaboracion(tElaboracion.getText());
				plato.setUsuario(usuario);
				//plato.setTiempo(null);
				//plato.setImagen(null);
				
				if(esNuevo){
					//Insertamos el nuevo plato
					control.insertarPlato(plato);
					
				}
				else{
					
					//eliminamos los ingredientes suprimidos que ya estaban persistidos anteriormente 
					for(Ingrediente i : ingredientesEliminar){
						PlatoIngrediente platoIngredienteAux = control.findPlatoIngrediente(plato.getId(), i.getId());
						control.eliminarPlatoIngrediente(platoIngredienteAux);
					}
					control.updatePlato(plato);
				}
				//A�adimos los ingredientes
				for(PlatoIngrediente pi : platoIngredienteNuevos)
					control.insertarPlatoIngrediente(pi);
				dispose();
			}
		});
		btnGuardar.setBounds(532, 505, 89, 29);
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
		btnCancelar.setBounds(433, 505, 89, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblCalorias = new JLabel("Calorias:");
		lblCalorias.setFont(new Font("Arial", Font.BOLD, 16));
		lblCalorias.setBounds(443, 180, 69, 20);
		contentPane.add(lblCalorias);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Arial", Font.BOLD, 16));
		lblPrecio.setBounds(443, 222, 66, 20);
		contentPane.add(lblPrecio);
		
		JLabel lblTiempo = new JLabel("Tiempo:");
		lblTiempo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTiempo.setBounds(443, 259, 66, 20);
		contentPane.add(lblTiempo);
		
		tCalorias = new JTextField();
		tCalorias.setEditable(false);
		tCalorias.setColumns(10);
		tCalorias.setBounds(520, 180, 79, 29);
		contentPane.add(tCalorias);
		
		JLabel lContImg = new JLabel("");
		lContImg.setBounds(393, 355, 110, 97);
		contentPane.add(lContImg);
		
		tPrecio = new JTextField();
		tPrecio.setEditable(false);
		tPrecio.setColumns(10);
		tPrecio.setBounds(520, 220, 79, 26);
		contentPane.add(tPrecio);
		
		JLabel label = new JLabel("\u20AC");
		label.setBounds(609, 227, 12, 14);
		contentPane.add(label);
		
		MaskFormatter mascara = new MaskFormatter("##:##");
		mascara.setValueContainsLiteralCharacters(false);
		JFormattedTextField tTiempo = new JFormattedTextField(mascara);
		tTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		tTiempo.setBounds(520, 257, 52, 29);
		contentPane.add(tTiempo);
		
		JButton btnAadirImagen = new JButton("Modificar");
		btnAadirImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser c = new JFileChooser();
				// Demonstrate "Open" dialog:
			      int rVal = c.showOpenDialog(NuevoPlato2.this);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			        //filename.setText(c.getSelectedFile().getName());
			        //dir.setText(c.getCurrentDirectory().toString());
			      }
			      if (rVal == JFileChooser.CANCEL_OPTION) {
			        //filename.setText("You pressed cancel");
			        //dir.setText("");
			      }
			}
		});
		btnAadirImagen.setBounds(520, 423, 89, 29);
		contentPane.add(btnAadirImagen);
		
		//Si el usuario es diferente de null se carga
		if(u!=null)
			lAutor2.setText(u.getNombre());
		
		//Si el plato pasado por parametro es diferente a null lo cargamos
		if(esNuevo==false){
			
			tNombre.setText(plato.getNombre());
			//Sacamos las horas y los minutos de elaboraci�n
			if(p.getTiempo()!=null){
				System.out.println("Tiempo: "+p.getTiempo());
				DateFormat f = new SimpleDateFormat("kk:mm");
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
			cargarIngredientes(modelo);
			
			//Cargamos la elaboraci�n
			if(p.getElaboracion()!=null)
				tElaboracion.setText(p.getElaboracion());
			
			//Calculamos las calorias
			contadorCalorias=control.calcularCalorias(plato.getId()).intValue();
			tCalorias.setText(contadorCalorias.toString());
			
			//Calculamos el precio			
			
			
			
		}
		
	}

	protected static void DISPOSE_ON_CLOSE() {
		// TODO Auto-generated method stub
		
	}
	
	public void cargarIngredientes(DefaultListModel<Ingrediente> modelo) throws Exception{
		
		if(esNuevo==false){
			ingredientes=control.ingredientesPorPlato2(plato.getNombre());
			System.out.println("Numero de ingredientes: "+ingredientes.size());
			if(ingredientes!=null){
				for(Ingrediente i: ingredientes)
					modelo.addElement(i);
					
			
			}
		}
	}
}
