package persistencia;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import modelo.Caracteristica;
import modelo.Ciudad;
import modelo.Dieta;
import modelo.Grupo;
import modelo.Ingrediente;
import modelo.Interes;
import modelo.Pais;
import modelo.Plato;
import modelo.PlatoDieta;
import modelo.PlatoIngrediente;
import modelo.Seguimiento;
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
	private SeguimientoDAO seguimientoDAO;
	private IngredienteDAO ingredienteDAO;
	private PlatoIngredienteDAO platoIngredienteDAO;
	private DietaDAO dietaDAO;
	private PlatoDietaDAO platoDietaDAO;


	private DAL() throws DAOExcepcion {
		// Objectos para comunicarse con la capa de acceso a datos
		caracteristicaDAO = new CaracteristicaDAO();
		platoDAO = new PlatoDAO(); 
		usuarioDAO = new UsuarioDAO();
		interesDAO = new InteresDAO();
		grupoDAO = new GrupoDAO();
		paisDAO = new PaisDAO();
		ciudadDAO = new CiudadDAO();
		ingredienteDAO = new IngredienteDAO();
		seguimientoDAO = new SeguimientoDAO();
		platoIngredienteDAO = new PlatoIngredienteDAO();
		dietaDAO = new DietaDAO();
		platoDietaDAO = new PlatoDietaDAO();
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
		return usuarioDAO.dietaSemanal(idUsuario, fechaIni, fechaFin);
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
	
	//Seguimiento
	public List<Seguimiento> getSegUsuario(int idUsuario){
		return seguimientoDAO.getSegUsuario(idUsuario);
	}
	
	public void setSeguimiento(Seguimiento s) {
		seguimientoDAO.setSeguimiento(s);
	}
	
	public boolean existeSeg(Seguimiento s) {
		return seguimientoDAO.existeSeg(s);
	}
	
	public void updateSeg(Seguimiento s) {
		seguimientoDAO.updateSeg(s);
	}
	
	public List<Plato> buscarPlatosPorAutor(Usuario u){
		return platoDAO.buscarPlatosPorAutor(u);
	}

	public List<Usuario> getUsuarioPorNombre(String nombre) {
		return usuarioDAO.getUsuarioPorNombre(nombre);
	}

	public List<Usuario> getUsuarioPorApellidos(String apellidos) {
		return usuarioDAO.getUsuarioPorApellios(apellidos);
	}

	public List<Usuario> getUsuarios() {
		return usuarioDAO.getUsuarios();
	}
	
	public List<Usuario> getUsuarioPorUsername(String username){
		return usuarioDAO.getUsuarioPorUsername(username);
	}
	
	public void addUsuario(Usuario usuario){
		usuarioDAO.addUsuario(usuario);
	}

	public boolean getPlatosEnDieta(int id) {
		return platoDAO.getPlatosEnDieta(id);
	}

	public void eliminarPlato(Plato platoVuelta) {
		platoDAO.eliminarPlato(platoVuelta);
	}
	
	public List<Ingrediente> getIngredientes(){
		return ingredienteDAO.getIngredientes();
	}
	
	public Long contarCalorias(int codigo){
		return platoDAO.caluclarCalorias(codigo);
	}
	
	public PlatoIngrediente findPlatoIngrediente(int platoId, int ingredienteId){
		return platoIngredienteDAO.findPlatoIngrediente(platoId, ingredienteId);
	}
	
	public void insertarPlato(Plato p){
		platoDAO.insertarPlato(p);
	}
	
	public void insertarPlatoIngrediente(PlatoIngrediente pi){
		platoIngredienteDAO.insertarPlatoIngrediente(pi);
	}
	
	public void eliminarPlatoIngrediente(PlatoIngrediente pi){
		
		platoIngredienteDAO.eliminarPlatoIngrediente(pi);
	}
	
	public void updatePlato(Plato p){
		platoDAO.updatePlato(p);
	}

	public void eliminarPlatoIngredientePorPlato(PlatoIngrediente platoVuelta) {
		platoIngredienteDAO.eliminarPlatoIngredientePorPlato(platoVuelta);
	}

	public List<PlatoIngrediente> getPlatoIntegredientes(Plato platoVuelta) {
		// TODO Auto-generated method stub
		return platoIngredienteDAO.getPlatoIngrediente(platoVuelta);
	}

	public Usuario getUsuarioPorId(int i) {
		return usuarioDAO.getUsuarioPorId(i);
	}

	public Usuario getUsuarioPorDNI(String dni) {
		// TODO Auto-generated method stub
		return usuarioDAO.getUsuarioPorDNI(dni);
	}
	
	public Double calcularPrecio(int codigo){
		return platoDAO.calcularPrecio(codigo);
	}

	public long getSemanasDieta(Date date, Usuario user) {
		return dietaDAO.getSemanasDieta(date,user);
		
	}

	public List<Dieta> getDietas(Usuario user) {
		return dietaDAO.getDietasASC(user);
	}

	public Dieta getDietaPorNombre(String nombre) {
		return dietaDAO.getDietaPorNombre(nombre);
	} 
	
	public List<Plato> getPlatosDieta(int id){
		return platoDAO.getPlatosDieta(id);
	}

	public List<Grupo> getGrupos(Usuario user) {
		return grupoDAO.getGrupos(user);
	}
	
	public Ingrediente findIngrediente(int id){
		return ingredienteDAO.findIngrediente(id);
	}
	
	public List<Dieta> getDietasLibres(){
		return dietaDAO.getDietasLibres();
	}
	
	public long getSemanasDieta(Integer cod) {
		return dietaDAO.getSemanasDieta(cod);
	}
	
	public void incribirseEnDieta(Integer codigo, Date fechaIni, Date fechaFin, Usuario user){
		dietaDAO.incribirseEnDieta(codigo, fechaIni, fechaFin, user);
	}
	
	public void desincribirseEnDieta(Dieta dieta){
		dietaDAO.desincribirseEnDieta(dieta);
	}

	public void setPlatosDietas(List<Integer> codigosPlatoDieta, Integer codigo, GregorianCalendar fechaIni, int numPlatos) {
		platoDietaDAO.setPlatosDietas(codigosPlatoDieta,codigo,fechaIni, numPlatos);
		
	}

	public List<Integer> getIdsPlatoDietaByIdDieta(Integer codigo) {
		return platoDietaDAO.getIdsPlatoDietaByIdDieta(codigo);
	}

	public List<PlatoDieta> getPlatoDietaAmodicar(Integer codigo) {
		// TODO Auto-generated method stub
		return platoDietaDAO.getPlatoDietaAmodicar(codigo);
	}

	public void setPlatoDietaOriginal(PlatoDieta platoDieta) {
		platoDietaDAO.setPlatoDietaOriginal(platoDieta);
	}

	public Dieta getDietaPorId(Integer codigo) {
		return dietaDAO.getDietaPorId(codigo);
	}

	public void altaDieta(Dieta nueva) {
		dietaDAO.altaDieta(nueva);
		
	}

	public int lastIdDietas() {
		return dietaDAO.lastIdDietas();
	}

	public Dieta getDietaPorNombreLibre(String nombre) {
		return dietaDAO.getDietaPorNombreLibre(nombre);
	}

	public Dieta getDietaPorNombreYusuario(String nombre, Usuario usuarioActual2) {
		return dietaDAO.getDietaPorNombreYusuario(nombre,usuarioActual2);
	}

	public void eliminarPlatoDieta(PlatoDieta dieta) {
		platoDietaDAO.eliminarPlatoDieta(dieta);
		
	}
	
	public List<PlatoDieta>  getPlatoDietaPorIdDieta(Integer codigo){
		return platoDietaDAO. getPlatoDietaPorIdDieta(codigo);
	}
}
