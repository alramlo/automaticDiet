package servicio;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;
import modelo.Caracteristica;
import servicio.Controlador;

public class EjecutarPorConsola {

	private static Controlador control;

	public static void main(String[] args)
	{	
		try {
			control = Controlador.dameControlador();
		} catch (DominioExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		Caracteristica caract1 = new Caracteristica();
		caract1.setId(1);
		caract1.setNombre("Caracteristica Uno");
		
		try {
			control.crearCaracteristica(caract1);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
