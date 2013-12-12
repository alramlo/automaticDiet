package presentacion;

import java.awt.Dimension;

import javax.swing.JPanel;

import modelo.Usuario;
import excepciones.DominioExcepcion;
import servicio.Controlador;

public class GestionDietas extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private Controlador control;
	private Usuario userConected;
	
	public GestionDietas() {
		
		setSize(new Dimension(800, 684));
		this.setSize(800, 600);
		
		
		try {
			control=Controlador.dameControlador();
		} catch (DominioExcepcion e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		userConected=control.getUsuarioActual();
		

	}

}
