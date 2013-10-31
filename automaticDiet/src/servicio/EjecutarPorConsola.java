package servicio;
import modelo.Caracteristica;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;

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
		caract1.setNombre("CaracterÝstica uno");
		
		try {
			control.crearCaracteristica(caract1);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
