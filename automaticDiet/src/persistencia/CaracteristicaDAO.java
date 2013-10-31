package persistencia;

import java.util.List;

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
	
	public List<Caracteristica> getCaracteristicas(){
		return null;
	}

}
