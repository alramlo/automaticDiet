package persistencia;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import modelo.Plato;
import modelo.PlatoIngrediente;

public class PlatoIngredienteDAO extends AbstractDAO {
	
	public PlatoIngrediente findPlatoIngrediente(int platoId, int ingredienteId){
		try{
		Query q = entityManager.createQuery("SELECT pi "
				+ "FROM PlatoIngrediente pi "
				+ "WHERE pi.plato.id=:platoId "
				+ "AND pi.ingrediente.id=:ingredienteId");
		q.setParameter("platoId", platoId);
		q.setParameter("ingredienteId", ingredienteId);
		return (PlatoIngrediente)q.getSingleResult();
		}catch(Exception e){
			System.out.println("Error en findPlatoIngrediente --> "+e);
			return null;
		}
	}
	
	public void insertarPlatoIngrediente(PlatoIngrediente pi){
		
		EntityTransaction trx = entityManager.getTransaction();
		try{
			trx.begin();
			entityManager.persist(pi);
			entityManager.flush();
			trx.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(trx.isActive())
				trx.rollback();
		}
	}
	
	public void eliminarPlatoIngrediente(PlatoIngrediente pi){
		
		EntityTransaction trx = entityManager.getTransaction();
		try{
			trx.begin();
			entityManager.remove(pi);
			entityManager.flush();
			trx.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(trx.isActive())
				trx.rollback();
		}
		
	}

	public void eliminarPlatoIngredientePorPlato(PlatoIngrediente platoVuelta) {
		EntityTransaction trx = entityManager.getTransaction();
		try{
			trx.begin();
			entityManager.remove(platoVuelta);
			entityManager.flush();
			trx.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(trx.isActive())
				trx.rollback();
		}
	}

	public List<PlatoIngrediente> getPlatoIngrediente(Plato platoVuelta) {
		try{
			Query q = entityManager.createQuery("SELECT pi "
					+ "FROM PlatoIngrediente pi "
					+ "WHERE pi.plato.id=:platoId ");
			q.setParameter("platoId", platoVuelta.getId());
			return (List<PlatoIngrediente>)q.getResultList();
			}catch(Exception e){
				System.out.println("Error en findPlatoIngrediente --> "+e);
				return null;
			}
	}
	
}
