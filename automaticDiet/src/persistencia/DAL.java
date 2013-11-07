package persistencia;

import java.util.Date;
import java.util.List;

import modelo.Caracteristica;
import modelo.Ciudad;
import modelo.Grupo;
import modelo.Ingrediente;
import modelo.Interes;
import modelo.Pais;
import modelo.Plato;
import modelo.Usuario;
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
	private PaisDAO paisDAO;
	private CiudadDAO ciudadDAO;


	private DAL() throws DAOExcepcion {
		// Objectos para comunicarse con la capa de acceso a datos
		caracteristicaDAO = new CaracteristicaDAO();
		platoDAO = new PlatoDAO(); 
		usuarioDAO = new UsuarioDAO();
		interesDAO = new InteresDAO();
		grupoDAO = new GrupoDAO();
		paisDAO = new PaisDAO();
		ciudadDAO = new CiudadDAO();
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

	public List<Grupo> getGrupos() {
		return grupoDAO.getGrupos();
	}
	
	public List<Caracteristica> addCaracteristicas(Grupo g){
		return grupoDAO.addCaracteristicas(g);
	}
	
	public List<Interes> addIntereses(Grupo g){
		return grupoDAO.addIntereses(g);
	}
	
	public List<Usuario> getNumParticipantes(Grupo g){
		return usuarioDAO.getNumParticipantes(g);
	}
	
	public Usuario getIdUsuario(String n, String a){
		return usuarioDAO.getIdUsuario(n,a);
	}
	
	public List<Plato> buscarPlatos(String p, Usuario usuario){
		return platoDAO.buscarPlatos(p,usuario);
	}
	
	public List<Plato> buscarPlatos(String p){
		return platoDAO.buscarPlatos(p);
	}
	
	public List<Pais> getPaises(){
		return paisDAO.getPaises();
	}
	
	public List<Ciudad> getCiudades(String p){
		return ciudadDAO.getCiudades(p);
	}
}
