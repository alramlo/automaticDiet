package persistencia;

import java.util.List;

import javax.persistence.Query;

import modelo.Interes;

public class InteresDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Interes> getIntereses(){
		try{
			Query q;
			q = entityManager.createQuery("SELECT i FROM Intereses i");
			return (List<Interes>)q.getResultList();
			}catch(Exception e){
				System.out.println("Error: "+e);
				return null;
				
			}
	}

}
