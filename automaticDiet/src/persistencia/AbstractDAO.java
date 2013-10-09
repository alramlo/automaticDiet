package persistencia;

import javax.persistence.*;

public abstract class AbstractDAO {
	
	EntityManager entityManager = null;
	
	public AbstractDAO (){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("automaticDiet");
		this.entityManager = emf.createEntityManager();
		
	}

}
