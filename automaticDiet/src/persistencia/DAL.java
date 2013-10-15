package persistencia;

import modelo.Caracteristica;
import modelo.Plato;
import excepciones.DAOExcepcion;


public class DAL {

	//****************************************************************************
	// Data Access Layer. Capa de indirecci�n para gestionar el acceso a datos
	//****************************************************************************

	private static DAL dal;
	
	private CaracteristicaDAO caracteristicaDAO;
	private PlatoDAO platoDAO;


	private DAL() throws DAOExcepcion {
		// Objectos para comunicarse con la capa de acceso a datos
		caracteristicaDAO = new CaracteristicaDAO();
	}
	
	// Patr�n Singleton
	public static DAL dameDAL() throws DAOExcepcion {
		if(dal==null)
			dal = new DAL();
		return dal;
	}
	
	// Caracter�sticas
	public void crearCaracteristica(Caracteristica c) throws DAOExcepcion{
		caracteristicaDAO.crearCaracteristica(c);
	}
	
	//plato
	public Plato consultarPlato(Plato p) throws DAOExcepcion{
		return platoDAO.platoByNombre(p.getNombre());
	}
	
	//public List<Ingredientes> IngredientesPorPlato(Plato p) throws DAOExcepcion{
		//return platoDAO.IngredientesPorPlato(p.getNombre());
	//}
	
}
