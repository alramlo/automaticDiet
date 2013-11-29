package persistencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import modelo.Ingrediente;
import modelo.Plato;
import modelo.Usuario;

public class PlatoDAO extends AbstractDAO {

	public Plato platoByNombre(String nombre) {
		try {
			Query q;
			q = entityManager
					.createQuery("SELECT p FROM Plato p WHERE p.nombre=:nom");
			q.setParameter("nom", nombre);
			return (Plato) q.getSingleResult();
		} catch (Exception e) {
			System.out.println("Error: "+e);
			return null;

		}

	}
	
	public List<Plato> todosPlatos (){
		try{
		Query q;
		q = entityManager.createQuery("SELECT p FROM Plato p");
		return (List<Plato>)q.getResultList();
		}catch(Exception e){
			System.out.println("Error: "+e);
			return null;
			
		}
		
	}
	

	public List<Ingrediente> IngredientesPorPlato(String nombre) {

		try {
			List result;
			List<Ingrediente> resultIn = new ArrayList<Ingrediente>();
			Query q;
			Plato p = this.platoByNombre(nombre);
			if (p != null) {
				System.out.println("Plato: " + p.getNombre());
				q = entityManager.createQuery("SELECT i "
						+ "FROM Ingrediente i, PlatoIngrediente pi "
						+ "WHERE pi.plato.id=:idP AND "
						+ "pi.ingrediente.id=i.id");
				q.setParameter("idP", p.getId());
				result = q.getResultList();
				for (Object i : result) {
					resultIn.add((Ingrediente) i);
				}
				return resultIn;

			} else {
				// System.out.println("No se encuentra el plato");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Error: "+e);
			return null;

		}

	}
	
	public List<Plato> buscarPlatos(String p, Usuario usuario){
		try{
		Query q;
		q = entityManager.createQuery("SELECT p FROM Plato p WHERE p.nombre=:nom"
				+ " AND p.usuario.id=:id");
		q.setParameter("nom", p);
		q.setParameter("id", usuario.getId());
		return (List<Plato>)q.getResultList();
		}catch(Exception e){
			System.out.println("Error: "+e);
			return null;
			
		}
		
	}
	
	public List<Plato> buscarPlatos(String p){
		try{
		Query q;
		q = entityManager.createQuery("SELECT p FROM Plato p WHERE p.nombre=:nom");
		q.setParameter("nom", p);
		return (List<Plato>)q.getResultList();
		}catch(Exception e){
			System.out.println("Error: "+e);
			return null;
			
		}
		
	}
	
	public List<Plato> buscarPlatosPorAutor(Usuario u){
		try{
			Query q;
			q = entityManager.createQuery("SELECT p FROM Plato p WHERE p.usuario.id=:nom");
			q.setParameter("nom", u.getId());
			return (List<Plato>)q.getResultList();
			}catch(Exception e){
				System.out.println("Error: "+e);
				return null;
				
			}
	}

	public boolean getPlatosEnDieta(int id) {
		try {
			List<Plato> result=null;
			Query q;
				q = entityManager.createQuery("SELECT p "
						+ "FROM Plato p, PlatoDieta pd "
						+ "WHERE p.id=pd.plato.id AND "
						+ "p.id=:ident");
				q.setParameter("ident", id);
				result = (List<Plato>)q.getResultList();
				if(result.size()==0)
				return false;
				else return true;
		} catch (Exception e) {
			System.out.println("Error: "+e);
			return true;

		}
	}
	
	public List<Plato> getPlatosDieta(int id) {
		try {
			List<Plato> result=null;
			Query q;
				q = entityManager.createQuery("SELECT p "
						+ "FROM Plato p, PlatoDieta pd "
						+ "WHERE p.id=pd.plato.id AND "
						+ "p.id=:ident");
				q.setParameter("ident", id);
				return (List<Plato>)q.getResultList();
		} catch (Exception e) {
			System.out.println("Error: "+e);
		}
		return null;
	}

	public void eliminarPlato(Plato platoVuelta) {
		//Query q;
//		q = entityManager.createQuery("DELETE FROM Plato p "
//				+ "WHERE p.id=:ident");
//		q.setParameter("ident", platoVuelta.getId());
		EntityTransaction trx = entityManager.getTransaction();
		try{	
			trx.begin();
			entityManager.remove(platoVuelta);
	        entityManager.flush();
	        trx.commit();
		}catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	         if (trx.isActive())
	             trx.rollback();
	     }
	}
	
	public void insertarPlato(Plato p){
		EntityTransaction trx = entityManager.getTransaction();
		try{
			trx.begin();
			entityManager.persist(p);
			entityManager.flush();
			trx.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(trx.isActive())
				trx.rollback();
		}
	}
	
	public void updatePlato(Plato p){
		EntityTransaction trx = entityManager.getTransaction();
		Plato platoAux; 
		try{
			trx.begin();
			platoAux=entityManager.find(Plato.class, p.getId());
			platoAux.setNombre(p.getNombre());
			platoAux.setTiempo(p.getTiempo());
			platoAux.setImagen(p.getImagen());
			platoAux.setElaboracion(p.getElaboracion());
			trx.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(trx.isActive())
				trx.rollback();
		}
	}
	
	public Long caluclarCalorias(int codigo){
		
		try{
		Query q;
		q=entityManager.createQuery("SELECT SUM(pi.cantidad*i.calorias) "
				+ "FROM PlatoIngrediente pi, Ingrediente i "
				+ "WHERE pi.plato.id=:cod "
				+ "AND i.id=pi.ingrediente.id");
		q.setParameter("cod", codigo);
		return (Long)q.getSingleResult();
		}catch(Exception e){
			System.out.println("Error en calcular Calorias --> "+e);
			return null;
		}
	} 
	
	public Double calcularPrecio(int codigo){
		
		try{
		Query q;
		q=entityManager.createQuery("SELECT SUM(pi.cantidad*i.precio) "
				+ "FROM PlatoIngrediente pi, Ingrediente i "
				+ "WHERE pi.plato.id=:cod "
				+ "AND i.id=pi.ingrediente.id");
		q.setParameter("cod", codigo);
		return (Double)q.getSingleResult();
		}catch(Exception e){
			System.out.println("Error en calcular Calorias --> "+e);
			return null;
		}
	} 

}
