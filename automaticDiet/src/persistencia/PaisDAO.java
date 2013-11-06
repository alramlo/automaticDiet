package persistencia;

import java.util.List;

import javax.persistence.Query;

import modelo.Pais;

public class PaisDAO extends AbstractDAO{

	
	public List<Pais> getPaises(){
		try{
			Query q;
			q = entityManager.createQuery("SELECT p FROM Pais p");
			return (List<Pais>)q.getResultList();
			}catch(Exception e){
				System.out.println("Error: "+e);
				return null;
				
			}
	}
}
