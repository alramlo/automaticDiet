package persistencia;

import java.util.Date;
import java.util.List;

import modelo.Caracteristica;
import modelo.Grupo;
import modelo.Ingrediente;
import modelo.Interes;
import modelo.Plato;
import excepciones.DAOExcepcion;


public class DAL {

	//****************************************************************************
	// Data Access Layer. Capa de indirección para gestionar el acceso a datos
	//****************************************************************************

	private static DAL dal;
	
	private CaracteristicaDAO caracteristicaDAO;
	private PlatoDAO platoDAO;
	private UsuarioDAO usuarioDAO;
	private InteresDAO interesDAO;
	private GrupoDAO grupoDAO;


	private DAL() throws DAOExcepcion {
		// Objectos para comunicarse con la capa de acceso a datos
		caracteristicaDAO = new CaracteristicaDAO();
		platoDAO = new PlatoDAO(); 
		usuarioDAO = new UsuarioDAO();
		interesDAO = new InteresDAO();
		grupoDAO = new GrupoDAO();
	}
	
	// Patrón Singleton
	public static DAL dameDAL() throws DAOExcepcion {
		if(dal==null)
			dal = new DAL();
		return dal;
	}
	
	// Características
	public void crearCaracteristica(Caracteristica c) throws DAOExcepcion{
		caracteristicaDAO.crearCaracteristica(c);
	}
	
	//plato
	public Plato consultarPlato(String n) throws DAOExcepcion{
		return platoDAO.platoByNombre(n);
	}
	
	public List<Plato> todosPlatos (){
		return platoDAO.todosPlatos();
	}
	
	public List<Ingrediente> ingredientesPorPlato(String n) throws DAOExcepcion{
		return platoDAO.IngredientesPorPlato(n);
	}
	
	//grupo
	
	//public Grupo buscarGrupoPorNombre(String n) throws DAOExcepcion{
		//return grupoDAO.buscarGrupoPorNombre(n);
	//}
	
	public List<Plato> dietaSemanal(int idUsuario, Date fechaIni, Date fechaFin){
		return usuarioDAO.dietaSemanal(1, fechaIni, fechaFin);
	}
	
	public List<Caracteristica> getCaracteristicas(){
		return caracteristicaDAO.getCaracteristicas();
	}
	
	public List<Interes> getIntereses(){
		return interesDAO.getIntereses();
	}

	public List<Grupo> getGrupos(Grupo grupo) {
		return grupoDAO.getGrupos(grupo);
	}
}
