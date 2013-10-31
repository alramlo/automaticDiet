package persistencia;

import java.util.List;

import javax.persistence.Query;

import modelo.Caracteristica;

public class CaracteristicaDAO extends AbstractDAO{
	
	
	public void crearCaracteristica(Caracteristica c){
		
		try{
			
			entityManager.getTransaction().begin();
			entityManager.persist(c);
			entityManager.flush();
			
		}catch(Exception e){
			
			entityManager.getTransaction().rollback();
			System.out.println("Error al crear Caracteristica: "+e);	
			
		}
		
		entityManager.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Caracteristica> getCaracteristicas(){
		try{
			Query q;
			q = entityManager.createQuery("SELECT c FROM Caracteristica c");
			return (List<Caracteristica>)q.getResultList();
			}catch(Exception e){
				System.out.println("Error: "+e);
				return null;
				
			}
	}

}
