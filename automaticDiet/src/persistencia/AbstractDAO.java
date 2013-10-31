package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDAO {
	
	EntityManager entityManager = null;
	
	public AbstractDAO (){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("automaticDiet");
		this.entityManager = emf.createEntityManager();
		
	}

}
