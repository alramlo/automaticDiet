package servicio;

import modelo.Caracteristica;
import modelo.Plato;
import persistencia.DAL;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;



public class Controlador {

	private static Controlador control = null;
	private static DAL dal;
//	private Caracteristica caracteristica;
//	private Usuario usuarioActual;



	//**********************************************************************
	// Creación del controlador
	// Un único controlador para todas las WUs
	//**********************************************************************
	private Controlador() throws DominioExcepcion{
		try {
			// Objeto para comunicarse con la capa de acceso a datos

			dal = DAL.dameDAL();
//			usuarioActual = null;

		}catch (DAOExcepcion e){
			throw new DominioExcepcion(e);
		}
	}
	
	public static Controlador dameControlador() throws DominioExcepcion
	{
		if (control == null)
			control = new Controlador();
		return control;
	}

	public void crearCaracteristica(Caracteristica caracteristica)throws DAOExcepcion
	{
		dal.crearCaracteristica(caracteristica);
	}
	
	public Plato consultarPlato(String nombre)throws DAOExcepcion
	{
		return dal.consultarPlato(nombre);
	}
	
	//public List<Ingredientes> IngredientesPorPlato(String plato)throws DAOExcepcion
	//{
		//return dal.IngredientesPorPlato(plato);

	//}
	
	//grupo
	
	//public Grupo buscarGrupoPorNombre(String nombre){
		//dal.buscarGrupoPorNombre(nombre);
	//}
}