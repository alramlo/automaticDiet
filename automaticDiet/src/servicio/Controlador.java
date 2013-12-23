package servicio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
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
import persistencia.DAL;
import excepciones.DAOExcepcion;
import excepciones.DominioExcepcion;



public class Controlador {

	private static Controlador control = null;
	private static DAL dal;
	private PlatoIngrediente pi;
	private Usuario usuarioActual;
	
//	private Caracteristica caracteristica;




	//**********************************************************************
	// Creación del controlador
	// Un único controlador para todas las WUs
	//**********************************************************************
	private Controlador() throws DominioExcepcion{
		try {
			// Objeto para comunicarse con la capa de acceso a datos

			dal = DAL.dameDAL();
			usuarioActual = null;
			pi=null;

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
	
	public String[] todosPlatos(){
		
		List<Plato> listaPlatos = dal.todosPlatos();
		String [] nombresPlatos = new String[listaPlatos.size()];
		
		Iterator<Plato> itPlatos = listaPlatos.iterator();
		for(int i=0; itPlatos.hasNext(); i++){
			Plato p = itPlatos.next();
			nombresPlatos[i]=p.getNombre();
		}
		return nombresPlatos;
	}
	
	public Ingrediente[] ingredientesPorPlato(String plato)throws DAOExcepcion
	{
		List<Ingrediente> listaIngredientes = dal.ingredientesPorPlato(plato);
		Ingrediente [] ingredientes = new Ingrediente[listaIngredientes.size()];
		
		Iterator<Ingrediente> itIngrediente = listaIngredientes.iterator();
		for(int i=0; itIngrediente.hasNext(); i++){
			
			ingredientes[i]=itIngrediente.next();
		}
		return ingredientes;
	}
	
	public List<Ingrediente> ingredientesPorPlato2(String plato) throws Exception{
		
		return dal.ingredientesPorPlato(plato);
	}
	
	//grupo
	
	//public Grupo buscarGrupoPorNombre(String nombre){
		//dal.buscarGrupoPorNombre(nombre);
	//}
	
	public Plato[] dietaSemanal(int idUsuario, Date fecha){
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fecha);
		Calendar[] fechas=getFechaIniFin(cal.get(Calendar.DAY_OF_WEEK),cal);
		System.out.println("ini: "+fechas[0].getTime());
		System.out.println("fin: "+fechas[1].getTime());
		List<Plato> listaPlatos = dal.dietaSemanal(idUsuario, fechas[0].getTime(), fechas[1].getTime());
		Plato [] platos = new Plato[listaPlatos.size()];
		
		Iterator<Plato> itPlato = listaPlatos.iterator();
		for(int i=0; itPlato.hasNext(); i++){
			
			platos[i]=itPlato.next();
		}
		return platos;
	}
	
	public Caracteristica[] getCaracteristicas(){
		List<Caracteristica> listaCaracteristica = dal.getCaracteristicas();
		Caracteristica[] caracteristica= new Caracteristica[listaCaracteristica.size()];
		
		Iterator<Caracteristica> itCaracteristica= listaCaracteristica.iterator();
		for(int i=0; itCaracteristica.hasNext(); i++){
			
			caracteristica[i]=itCaracteristica.next();
		}
		return caracteristica;
	}
	
	public Interes[] getIntereses(){
		List<Interes> listaInteres = dal.getIntereses();
		Interes[] interes= new Interes[listaInteres.size()];
		
		Iterator<Interes> itInteres= listaInteres.iterator();
		for(int i=0; itInteres.hasNext(); i++){
			
			interes[i]=itInteres.next();
		}
		return interes;
	}
	
	public Grupo[] getGrupos(Grupo grupo) {
		
		List<Grupo> lgrupos = dal.getGrupos();
		List<Grupo> posibles=new ArrayList<Grupo>();
		//Iterator<Grupo> itGrupo= lgrupos.iterator();
		for(int i=0; i<lgrupos.size(); i++){	
			lgrupos.get(i).setCaracteristicas(dal.addCaracteristicas(lgrupos.get(i)));
			lgrupos.get(i).setIntereses(dal.addIntereses(lgrupos.get(i)));
		}
		//falta añadir los intereses
		
		//Primer filtro -> nombre
		if(grupo.getNombre()!=null){
			//Iterator<Grupo> itGrupo2= lgrupos.iterator();
			for(int i=0; i<lgrupos.size(); i++){	
				if(grupo.getNombre().equals(lgrupos.get(i).getNombre())){
					posibles.add(lgrupos.get(i));
				}
			}
			lgrupos.clear();
			int count=posibles.size();
			for(int i=0; i<count; i++){
				lgrupos.add(posibles.get(i));
			}
		}
//		if(grupo.getNombre()!=null){
//			lgrupos.clear();
//			int count=posibles.size();
//			for(int i=0; i<count; i++){
//				lgrupos.add(posibles.get(i));
//			}
//		}
		
		if(grupo.getPais()!=null){
			posibles.clear();
			for(int i=0; i<lgrupos.size(); i++){	
				if(grupo.getPais().equals(lgrupos.get(i).getPais())){
					posibles.add(lgrupos.get(i));
				}
			}
			lgrupos.clear();
			int count=posibles.size();
			for(int i=0; i<count; i++){
				lgrupos.add(posibles.get(i));
			}
		}
	
		if(grupo.getCiudad()!=null){
			posibles.clear();
			for(int i=0; i<lgrupos.size(); i++){	
				if(grupo.getCiudad().equals(lgrupos.get(i).getCiudad())){
					posibles.add(lgrupos.get(i));
				}
			}
			lgrupos.clear();
			int count=posibles.size();
			for(int i=0; i<count; i++){
				lgrupos.add(posibles.get(i));
			}
		}
		
		//Segundo filtro --> caracteristicas
		if(grupo.getCaracteristicas()!=null){
			posibles.clear();
			//Iterator<Grupo> itGrupo3= posibles.iterator();
			int cont=0;
			for(int i=0; i<lgrupos.size(); i++){//grupos posibles
				cont=0;
				for(int j=0;j<lgrupos.get(i).getCaracteristicas().size(); j++){//caracteristicas de cada grupo posible
					for(int k=0;k<grupo.getCaracteristicas().size(); k++){ //caracteristicas del grupo a buscar
						if(grupo.getCaracteristicas().get(k).getNombre().equals(lgrupos.get(i).getCaracteristicas().get(j).getNombre())){
							cont++;
						}
					}
					if(cont==grupo.getCaracteristicas().size()){
						posibles.add(lgrupos.get(i));
						break;
					}
				}
			}
			lgrupos.clear();
			int count=posibles.size();
			for(int i=0; i<count; i++){
				lgrupos.add(posibles.get(i));
			}
		}
		
		if(grupo.getIntereses()!=null){
			posibles.clear();
			//Iterator<Grupo> itGrupo3= posibles.iterator();
			int cont=0;
			for(int i=0; i<lgrupos.size(); i++){//grupos posibles
				cont=0;
				for(int j=0;j<lgrupos.get(i).getIntereses().size(); j++){//caracteristicas de cada grupo posible
					for(int k=0;k<grupo.getIntereses().size(); k++){ //caracteristicas del grupo a buscar
						if(grupo.getIntereses().get(k).getNombre().equals(lgrupos.get(i).getIntereses().get(j).getNombre())){
							cont++;
						}
					}
					if(cont==grupo.getIntereses().size()){
						posibles.add(lgrupos.get(i));
						break;
					}
				}
			}
			lgrupos.clear();
			int count=posibles.size();
			for(int i=0; i<count; i++){
				lgrupos.add(posibles.get(i));
			}
		}
		Grupo[] grupos = new Grupo[posibles.size()];
		Iterator<Grupo> itGrupo= posibles.iterator();
		for(int i=0; itGrupo.hasNext(); i++){
			
			grupos[i]=itGrupo.next();
		}
		return grupos;
	}
	
	public int getNumParticipantes(Grupo g){
		return dal.getNumParticipantes(g).size();
	}
	
	public Usuario getIdUsuario(String n,String a){
		return dal.getIdUsuario(n,a);
	}
	
	public List<Plato> buscarPlatos(String p, Usuario usuario){
		if(usuario!=null)
			return dal.buscarPlatos(p,usuario);
		else
			return dal.buscarPlatos(p);
	}
	
	public String[] getPaises(){
		List<Pais> listaPaises = dal.getPaises();
		String[] paises= new String[listaPaises.size()+1];
		paises[0]="";
		Iterator<Pais> itPais= listaPaises.iterator();
		for(int i=1; itPais.hasNext(); i++){
			
			paises[i]=itPais.next().getNombre();
		}
		
		return paises;
	}
	
	public String[] getCiudades(String p){
		List<Ciudad> listaCiudades = dal.getCiudades(p);
		String[] ciudades= new String[listaCiudades.size()+1];
		ciudades[0]="";
		Iterator<Ciudad> itCiudad= listaCiudades.iterator();
		for(int i=1; itCiudad.hasNext(); i++){
			
			ciudades[i]=itCiudad.next().getNombre();
		}
		
		return ciudades;
	}
	
	@SuppressWarnings("static-access")
	private Calendar[] getFechaIniFin(int nfecha, GregorianCalendar fecha){
		Calendar [] fechas = new Calendar[2];
		GregorianCalendar fechaIni, fechaFin;
		switch(nfecha){
		case 1: //domingo
			fechaFin=(GregorianCalendar)fecha.clone();
			fecha.add(fecha.DATE, -6);
			fechas[0]=fecha;
			fechas[1]=fechaFin;
			break;
		case 2: //lunes
			fechaIni=(GregorianCalendar)fecha.clone();
			fechas[0]=fechaIni;
			fecha.add(fecha.DATE,+6);
			fechas[1]=fecha;
			break;
		case 3: //martes
			fechaFin=(GregorianCalendar)fecha.clone();
			fechaFin.add(fecha.DATE,+5);
			fecha.add(fecha.DATE,-1);
			fechas[0]=fecha;
			fechas[1]=fechaFin;
			break;
		case 4: //miércoles
			fechaFin=(GregorianCalendar)fecha.clone();
			fechaFin.add(fecha.DATE,+4);
			fecha.add(fecha.DATE,-2);
			fechas[0]=fecha;
			fechas[1]=fechaFin;
			break;
		case 5: //jueves
			fechaFin=(GregorianCalendar)fecha.clone();
			fechaFin.add(fecha.DATE,+3);
			fecha.add(fecha.DATE,-3);
			fechas[0]=fecha;
			fechas[1]=fechaFin;
			break;
		case 6: //viernes
			fechaFin=(GregorianCalendar)fecha.clone();
			fechaFin.add(fecha.DATE,+2);
			fecha.add(fecha.DATE,-4);
			fechas[0]=fecha;
			fechas[1]=fechaFin;
			break;
		case 7: //sabado
			fechaFin=(GregorianCalendar)fecha.clone();
			fechaFin.add(fecha.DATE,+1);
			fecha.add(fecha.DATE,-5);
			fechas[0]=fecha;
			fechas[1]=fechaFin;
			break;
	}
		return fechas;
	}
	
	public Seguimiento[] getSegUsuario(int idUsuario){
		List<Seguimiento> lista = dal.getSegUsuario(idUsuario);
		Seguimiento[] datos = new Seguimiento[lista.size()];
		Iterator<Seguimiento> itDatos= lista.iterator();
		for(int i=0; itDatos.hasNext(); i++){			
			datos[i] = itDatos.next();
		}
		return datos;
	}
	
	public void setSeguimiento(Seguimiento s) {
		if(dal.existeSeg(s))
		{
			dal.updateSeg(s);
		}
		else
		{
			dal.setSeguimiento(s);
		}
	}
	
	public List<Plato> buscarPlatosPorAutor(Usuario u){
		return dal.buscarPlatosPorAutor(u);
	}

	public List<Usuario> getUsuarioPorNombre(String nombre) {
		return dal.getUsuarioPorNombre(nombre);
	}

	public List<Usuario> getUsuarioPorApellidos(String apellidos) {
		return dal.getUsuarioPorApellidos(apellidos);
	}

	public List<Usuario> getUsuarios() {
		return dal.getUsuarios();
	}

	public boolean getPlatosEnDieta(int id) {
		return dal.getPlatosEnDieta(id);
	}

	public void eliminarPlato(Plato platoVuelta) {
		dal.eliminarPlato(platoVuelta);
		
	}
	
	public List<Ingrediente> getIngredientes(){
		return dal.getIngredientes();
	}

	public PlatoIngrediente getPi() {
		return pi;
	}

	public void setPi(PlatoIngrediente pi) {
		this.pi = pi;
	}
	
	public Long calcularCalorias(int codigo){
		return dal.contarCalorias(codigo);
	}
	
	public PlatoIngrediente findPlatoIngrediente(int platoId, int ingredienteId){
		return dal.findPlatoIngrediente(platoId, ingredienteId);
	}
	
	public void insertarPlato(Plato p){
		dal.insertarPlato(p);
	}
	
	public void insertarPlatoIngrediente(PlatoIngrediente pi){
		dal.insertarPlatoIngrediente(pi);
	}
	
	public void eliminarPlatoIngrediente(PlatoIngrediente pi){
		
		dal.eliminarPlatoIngrediente(pi);
	}
	
	public void updatePlato(Plato p){
		dal.updatePlato(p);
	}

	public void eliminarPlatoIngredientePorPlato(List<PlatoIngrediente> platoVuelta) {
		for(int i=0;i<platoVuelta.size();i++)
		dal.eliminarPlatoIngredientePorPlato(platoVuelta.get(i));
	}

	public List<PlatoIngrediente> getPlatoIngredientes(Plato platoVuelta) {
		// TODO Auto-generated method stub
		return dal.getPlatoIntegredientes(platoVuelta);
	}

	public Usuario getUsuarioPorId(int i) {
		return dal.getUsuarioPorId(i);
	}

	public Usuario getUsuarioPorDNI(String dni) {
		// TODO Auto-generated method stub
		return dal.getUsuarioPorDNI(dni);
	}
	
	public Double calcularPrecio(int codigo){
		return dal.calcularPrecio(codigo);
	}

	public long getSemanasDieta(Date date, Usuario user) {
		long l = dal.getSemanasDieta(date,user);
		if(l==-1)
			return -1;
		else
		return  ((l/5)/4)/7;
	}

	public String[] getDietas(Usuario user) {
		List<Dieta> dietas = dal.getDietas(user);
		String [] nombresDietas = new String[dietas.size()];
		
		Iterator<Dieta> itDietas = dietas.iterator();
		for(int i=0; itDietas.hasNext(); i++){
			Dieta d = itDietas.next();
			nombresDietas[i]=d.getNombre();
		}
		return nombresDietas;
	}
	
	public List<Dieta> getListDietas(Usuario user) {
		return dal.getDietas(user);
		
	}
	
	public String[] getPlatosDieta(int id) {
		List<Plato> platos = dal.getPlatosDieta(id);
		String [] nombresPlatos = new String[platos.size()];
		
		Iterator<Plato> itPlato = platos.iterator();
		for(int i=0; itPlato.hasNext(); i++){
			Plato d = itPlato.next();
			nombresPlatos[i]=d.getNombre();
		}
		return nombresPlatos;
	}

	public Dieta getDietaPorNombre(String nombre) {
		return dal.getDietaPorNombre(nombre);
	}
	
	// Métodos para el login
	public void logout() {
		usuarioActual = null;
	}	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	public void setUsuarioActual(Usuario us) {
		this.usuarioActual = us;
	}
	public List<Usuario> getUsuarioPorUsername(String u) throws DAOExcepcion{
		return dal.getUsuarioPorUsername(u);
	}
	public boolean addUsuario(Usuario usuario)throws DAOExcepcion{
		List<Usuario> yaExiste = dal.getUsuarioPorUsername(usuario.getUsername());
		if(yaExiste.size()>0)
		{
			return false;
		}
		else
		{
			dal.addUsuario(usuario);
			return true;
		}
	}

	public List<Grupo> getGrupos(Usuario user) {
		return dal.getGrupos(user);
	}
	
	public Ingrediente findIngrediente(int id){
		return dal.findIngrediente(id);
	}
	
	public List<Dieta> getDietasLibres(){
		return dal.getDietasLibres();
	}
	
	public long getSemanasDieta(Integer cod) {
		return dal.getSemanasDieta(cod);
	}
	
	public void incribirseEnDieta(Integer codigo, Date fechaIni, Date fechaFin, Usuario user){
		Dieta dieta = this.getDietaPorId(codigo);
		dal.incribirseEnDieta(codigo, fechaIni, fechaFin, user);
		Dieta nueva = new Dieta();
		nueva.setCaloriasMax(dieta.getCaloriasMax());
		nueva.setCaloriasMin(dieta.getCaloriasMin());
		nueva.setDescripcion(dieta.getDescripcion());
		nueva.setNombre(dieta.getNombre());
		this.altaDieta(nueva);
		int id = lastIdDietas();
		Dieta dietaVuelta = this.getDietaPorId(id);
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(fechaIni);
		String[] numPlatos = this.getPlatosDieta(codigo);
		List<Integer> codigosPlatoDieta = this.getIdsPlatoDietaByIdDieta(codigo);
		List<PlatoDieta> pd = this.getPlatoDietaAmodicar(codigo);
		dal.setPlatosDietas(codigosPlatoDieta,codigo,date, numPlatos.length);
		for(int i=0; i<pd.size();i++){
			//pd.get(i).setId(pd.get(pd.size()-1).getId()+i+1);
			PlatoDieta pd2 = new PlatoDieta();
			pd2.setDia(null);
			pd2.setDieta(dietaVuelta);
			pd2.setPlato(pd.get(i).getPlato());
			this.setPlatoDietaOriginal(pd2);
		}
	}
	
	private int lastIdDietas() {
		return dal.lastIdDietas();
	}

	public void altaDieta(Dieta nueva) {
		dal.altaDieta(nueva);
	}

	public Dieta getDietaPorId(Integer codigo) {
		return dal.getDietaPorId(codigo);
	}

	private void setPlatoDietaOriginal(PlatoDieta platoDieta) {
		dal.setPlatoDietaOriginal(platoDieta);
		
	}

	private List<PlatoDieta> getPlatoDietaAmodicar(Integer codigo) {
		// TODO Auto-generated method stub
		return dal.getPlatoDietaAmodicar(codigo);
	}

	private List<Integer> getIdsPlatoDietaByIdDieta(Integer codigo) {
		return dal.getIdsPlatoDietaByIdDieta(codigo);
	}

	public void desincribirseEnDieta(Dieta d){
		List<PlatoDieta> pd = dal. getPlatoDietaPorIdDieta(d.getId());
		for(int i=0;i<pd.size();i++)
			dal.eliminarPlatoDieta(pd.get(i));
		dal.desincribirseEnDieta(d);
	}
	
	public boolean mismaPassword (char[] pass1, char[] pass2){
		boolean cent = true;
		if(pass1.length!=pass2.length){
			cent = false;
		} else {
			for(int i = 0; i < pass1.length; i++){
				if(pass1[i]!=pass2[i]){
					cent = false;
					i=pass1.length;
				}
			}
		}
		return cent;
	}

	public Dieta getDietaPorNombreLibres(String nombre) {
		return dal.getDietaPorNombreLibre(nombre);
	}

	public Dieta getDietaPorNombreYusuario(String nombre, Usuario usuarioActual2) {
		return dal.getDietaPorNombreYusuario(nombre, usuarioActual2);
	}
}

