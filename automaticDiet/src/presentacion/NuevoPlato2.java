package presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import modelo.Ingrediente;
import modelo.IngredienteLista;
import modelo.Plato;
import modelo.PlatoIngrediente;
import modelo.Usuario;
import servicio.Controlador;

public class NuevoPlato2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7631627484409625280L;
	private JPanel contentPane;
	private JTextField tNombre;
	private Plato plato;
	private Usuario usuario;
	private static Controlador control;
	private JList<IngredienteLista> listIngredientes = new JList<IngredienteLista>();
	private JTextField tCalorias;
	private JTextField tPrecio;
	private List<Ingrediente> ingredientes;
	private List<PlatoIngrediente> platoIngredienteNuevos;
	private List<Ingrediente> ingredientesEliminar;
	private PlatoIngrediente piAux;
	private Boolean esNuevo;
	private Integer contadorCalorias;
	private Double contadorPrecio;
	private GestionPlatos gPlatos;
	private JFormattedTextField tTiempo;
	private String file;
	private String dir;
	private JLabel lContImg;
	private JButton btnEliminar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoPlato2 frame = new NuevoPlato2(null,null,null);
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
	public NuevoPlato2(Plato p, Usuario u, GestionPlatos gestionPlatos) throws Exception {
		setTitle("A\u00F1adir/Modificar plato");
		
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
		gPlatos=gestionPlatos;
		contadorCalorias=0;
		contadorPrecio=new Double(0);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoPlato2.class.getResource("/iconos/platos.png")));
		platoIngredienteNuevos = new ArrayList<PlatoIngrediente>();
		ingredientesEliminar = new ArrayList<Ingrediente>();
		
		//Pedimos el controlador
		control=Controlador.dameControlador();
				
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		tNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				System.out.println("Entramos ha modificar el nombre");
				if(tNombre.getText().contains("0") || tNombre.getText().contains("1") || tNombre.getText().contains("3") || tNombre.getText().contains("4") || tNombre.getText().contains("5") || tNombre.getText().contains("6") || tNombre.getText().contains("7") || tNombre.getText().contains("8") || tNombre.getText().contains("9")|| tNombre.getText().contains("_") || tNombre.getText().contains(".") || tNombre.getText().contains(",") || tNombre.getText().contains(";") || tNombre.getText().contains(":") || tNombre.getText().contains("@") || tNombre.getText().contains("&") || tNombre.getText().contains("#") || tNombre.getText().contains("$") || tNombre.getText().contains("!") || tNombre.getText().contains("\"") || tNombre.getText().contains("º") || tNombre.getText().contains("ª") || tNombre.getText().contains("\\") || tNombre.getText().contains("%") || tNombre.getText().contains("/") || tNombre.getText().contains("(") || tNombre.getText().contains(")") || tNombre.getText().contains("=") || tNombre.getText().contains("^") || tNombre.getText().contains("*") || tNombre.getText().contains("¨") || tNombre.getText().contains("{") || tNombre.getText().contains("}") || tNombre.getText().contains("<") || tNombre.getText().contains(">") || tNombre.getText().contains("+") || tNombre.getText().contains("[") || tNombre.getText().contains("]") || tNombre.getText().contains("'") || tNombre.getText().contains("?") || tNombre.getText().contains("¿")){
					JOptionPane.showMessageDialog(null, "El nombre del plato no puede contener números ni caracteres especiales", "Error", JOptionPane.ERROR_MESSAGE);
					tNombre.setText("");
				}
			}
		});
		
		tNombre.setBounds(176, 40, 311, 29);
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
		
		final DefaultListModel<IngredienteLista> modelo = new DefaultListModel<IngredienteLista>();
		
		listIngredientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(listIngredientes.getSelectedValue()!=null)
					NuevoPlato2.this.btnEliminar.setEnabled(true);
			}
		});
		
		
		
		listIngredientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listIngredientes.setModel(modelo);
		listIngredientes.setBounds(37, 133, 282, 155);
		getContentPane().add(listIngredientes);
		
		//Botón eliminar ingrediente
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
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
					
					//Si el plato no es nuevo, añadimos el ingrediente a la lista de borrar
					if(esNuevo==false){
						ingredientesEliminar.add(control.findIngrediente(listIngredientes.getSelectedValue().getId()));
					}
					
					//Si el ingrediente ya había sido introducido anteriormente, recuperamos el PlatoIngrediente de la base de datos
					if(piEliminar==null)
						piEliminar=control.findPlatoIngrediente(plato.getId(), listIngredientes.getSelectedValue().getId());
					
					//Decrementamos las calorias
					contadorCalorias=contadorCalorias-(piEliminar.getCantidad()*piEliminar.getIngrediente().getCalorias());
					tCalorias.setText(contadorCalorias.toString());
					if(modelo.size()==1)
						contadorPrecio=0.0;
					else
						contadorPrecio=contadorPrecio-(piEliminar.getCantidad()*piEliminar.getIngrediente().getPrecio());
					Float aux = contadorPrecio.floatValue();
					tPrecio.setText(aux.toString()); 
					
					//eliminamos el ingrediente seleccionado de la lista de ingredientes
					modelo.removeElementAt(listIngredientes.getSelectedIndex());
					
				}
			}
		});
		btnEliminar.setBounds(329, 164, 89, 29);
		getContentPane().add(btnEliminar);
		
		//Botón añadir ingrediente
		JButton btnBuscar = new JButton("A\u00F1adir");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AnadirIngrediente2 ventana = new AnadirIngrediente2(plato, platoIngredienteNuevos);
					ventana.setModal(true);
					ventana.setVisible(true);
					piAux=control.getPi();
					if(piAux!=null){
						IngredienteLista il = new IngredienteLista();
						Ingrediente ingredienteAux = control.findIngrediente(piAux.getIngrediente().getId()); 
						il.setId(ingredienteAux.getId());
						il.setNombre(ingredienteAux.getNombre());
						il.setCantidad(piAux.getCantidad());
						modelo.addElement(il);
						piAux.setPlato(plato);
						platoIngredienteNuevos.add(piAux);
						contadorCalorias=contadorCalorias+(piAux.getIngrediente().getCalorias()*piAux.getCantidad());
						tCalorias.setText(contadorCalorias.toString());
						contadorPrecio=contadorPrecio+(piAux.getIngrediente().getPrecio()*piAux.getCantidad());
						Float aux = contadorPrecio.floatValue();
						tPrecio.setText(aux.toString());
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
				
				if(tNombre.getText().equals("") || tElaboracion.getText().equals("") || usuario==null || tTiempo.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Los campos nombre, elaboración y tiempo son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else{
					
					if(control.buscarPlatos(tNombre.getText(), usuario).size()>0 && esNuevo){
						JOptionPane.showMessageDialog(null, "El plato ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else{
						
						//actualizamos el plato
						plato.setNombre(tNombre.getText());
						plato.setElaboracion(tElaboracion.getText());
						plato.setUsuario(usuario);
						
						//Transformamos el tiempo
							SimpleDateFormat f = new SimpleDateFormat("HH:mm");
							Date fecha;
							try {
								fecha = f.parse(tTiempo.getText());
								plato.setTiempo(fecha);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								esNuevo=false;
								JOptionPane.showMessageDialog(null, "Los campos nombre, elaboración y tiempo son obligatorios.", "Info", JOptionPane.INFORMATION_MESSAGE);
							}
						
						//Metemos la imagen
						File fichero=null; 
						if(dir!=null&&file!=null){
							fichero = new File(dir+"\\"+file);
							if(fichero!=null)
								plato.setImagen(NuevoPlato2.this.FileToByte(fichero));
						}
						
						if(esNuevo){
							//Insertamos el nuevo plato
							control.insertarPlato(plato);
							
						}
						else{
							
							//eliminamos los ingredientes suprimidos que ya estaban persistidos anteriormente 
							for(Ingrediente i : ingredientesEliminar){
								PlatoIngrediente platoIngredienteAux = control.findPlatoIngrediente(plato.getId(), i.getId());
								if(platoIngredienteAux!=null)
									control.eliminarPlatoIngrediente(platoIngredienteAux);
							}
							control.updatePlato(plato);
						}
						//Añadimos los ingredientes
							for(PlatoIngrediente pi : platoIngredienteNuevos)
								control.insertarPlatoIngrediente(pi);
							gPlatos.poblar();
							//if(esNuevo){
							JOptionPane.showMessageDialog(null, "Plato guardado correctamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							//}
						}
				}
				
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
		
		lContImg = new JLabel("");
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
		tTiempo = new JFormattedTextField(mascara);
		tTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		tTiempo.setBounds(520, 257, 52, 29);
		contentPane.add(tTiempo);
		
		JButton btnAadirImagen = new JButton("Añadir IMG");
		btnAadirImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser c = new JFileChooser();
				// Demonstrate "Open" dialog:
			      int rVal = c.showOpenDialog(NuevoPlato2.this);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			        file=c.getSelectedFile().getName();
			        dir=c.getCurrentDirectory().toString();
			        
			        File fichero=null;
			        
					fichero = new File(dir+"\\"+file);
					System.out.println("Ruta: "+dir+file);
//						FileOutputStream fos = new FileOutputStream (file);
//						fos.write(p.getImagen());
//						fos.close();
					ImageIcon im=null;
		    		BufferedImage buffer;
		    		int width=0,height=0;
					try {
						buffer = ImageIO.read(fichero);
						im = new ImageIcon(buffer);
						width=buffer.getWidth();
						height=buffer.getHeight();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    		Image image=im.getImage();
		    		Image newImage;
		    		if(width>112 || height>98)
		    			newImage= image.getScaledInstance(width/3, height/3, java.awt.Image.SCALE_SMOOTH);
		    		else
		    			newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		    		lContImg.setIcon(new ImageIcon(newImage));
			        
			      }
			      
			}
		});
		btnAadirImagen.setBounds(532, 422, 89, 29);
		contentPane.add(btnAadirImagen);
		
		JLabel label_oblig = new JLabel("*");
		label_oblig.setHorizontalAlignment(SwingConstants.CENTER);
		label_oblig.setForeground(Color.RED);
		label_oblig.setFont(new Font("Arial", Font.BOLD, 16));
		label_oblig.setBounds(27, 50, 12, 19);
		contentPane.add(label_oblig);
		
		JLabel label_1 = new JLabel("*");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Arial", Font.BOLD, 16));
		label_1.setBounds(27, 324, 12, 19);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Arial", Font.BOLD, 16));
		label_2.setBounds(432, 264, 12, 19);
		contentPane.add(label_2);
		
		//Si el usuario es diferente de null se carga
		if(u!=null)
			lAutor2.setText(u.getNombre());
		
		//Si el plato pasado por parametro es diferente a null lo cargamos
		if(esNuevo==false){
			
			tNombre.setText(plato.getNombre());
			//Sacamos las horas y los minutos de elaboración
			if(p.getTiempo()!=null){
				System.out.println("Tiempo: "+p.getTiempo());
				SimpleDateFormat f = new SimpleDateFormat("HH:mm");
				try{
					String fecha=f.format(p.getTiempo());
					tTiempo.setText(fecha);
					System.out.println("Fecha: "+fecha);
				}catch(Exception e){
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
	    		int width=0,height=0;
				try {
					buffer = ImageIO.read(file);
					im = new ImageIcon(buffer);
					width=buffer.getWidth();
					height=buffer.getHeight();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		Image image=im.getImage();
	    		Image newImage;
	    		if(width>112 || height>98)
	    			newImage= image.getScaledInstance(width/3, height/3, java.awt.Image.SCALE_SMOOTH);
	    		else
	    			newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
	    		lContImg.setIcon(new ImageIcon(newImage));
			}
    		
    		//Cargamos el Autor
			if(p.getUsuario()!=null){
				lAutor2.setText(p.getUsuario().getNombre());
			}
    		
    		//Cargamos los ingredientes
			cargarIngredientes(modelo);
			
			//Cargamos la elaboración
			if(p.getElaboracion()!=null)
				tElaboracion.setText(p.getElaboracion());
			
			//Calculamos las calorias
			Long calorias = control.calcularCalorias(plato.getId());
			if(calorias!=null)
			contadorCalorias=calorias.intValue();
			tCalorias.setText(contadorCalorias.toString());
			
			//Calculamos el precio	
			Double precio = control.calcularPrecio(plato.getId());
			if(precio!=null)
			contadorPrecio=contadorPrecio+precio;
			tPrecio.setText(contadorPrecio.toString());
			
			
			
		}
		
	}

	protected static void DISPOSE_ON_CLOSE() {
		// TODO Auto-generated method stub
		
	}
	
	public void cargarIngredientes(DefaultListModel<IngredienteLista> modelo) throws Exception{
		IngredienteLista il;
		if(esNuevo==false){
			ingredientes=control.ingredientesPorPlato2(plato.getNombre());
			System.out.println("Numero de ingredientes: "+ingredientes.size());
			if(ingredientes!=null){
				for(Ingrediente in: ingredientes){
					il = new IngredienteLista();
					il.setNombre(in.getNombre());
					il.setId(in.getId());
					il.setCantidad(control.findPlatoIngrediente(plato.getId(), in.getId()).getCantidad());
					modelo.addElement(il);
				}
			
			}
		}
	}
	
	public byte[] FileToByte(File f){
		FileInputStream fis;
		byte[] zipped=null;
		try {
			fis = new FileInputStream(f);
			zipped = new byte[ (int) f.length()];
			fis.read(zipped);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}	
		return zipped;		
	}
}
