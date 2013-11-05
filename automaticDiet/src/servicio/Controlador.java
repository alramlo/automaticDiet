package servicio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import modelo.Caracteristica;
import modelo.Grupo;
import modelo.Ingrediente;
import modelo.Interes;
import modelo.Plato;
import modelo.Usuario;
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
	
	//grupo
	
	//public Grupo buscarGrupoPorNombre(String nombre){
		//dal.buscarGrupoPorNombre(nombre);
	//}
	
	public Plato[] dietaSemanal(int idUsuario, Date fecha){
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fecha);
		Calendar[] fechas=getFechaIniFin(cal.get(Calendar.DAY_OF_WEEK),cal);
		
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
		}
		if(grupo.getNombre()!=null){
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
		}
		Grupo[] grupos = new Grupo[posibles.size()];
		Iterator<Grupo> itGrupo= posibles.iterator();
		for(int i=0; itGrupo.hasNext(); i++){
			
			grupos[i]=itGrupo.next();
		}
		return grupos;
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
}

