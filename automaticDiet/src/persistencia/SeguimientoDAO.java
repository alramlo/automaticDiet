package persistencia;

import java.util.List;

import javax.persistence.Query;

import modelo.Seguimiento;

public class SeguimientoDAO extends AbstractDAO
{
	public List<Seguimiento> getSegUsuario(int idUsuario)
	{
		try
		{
			Query q;
			q = entityManager.createQuery("SELECT * "
					+ "FROM Seguimiento s"
					+ "WHERE s.id =:idUsuario");
			q.setParameter("idUsuario", idUsuario);
			return (List<Seguimiento>) q.getResultList();
			
			
		}catch(Exception e){
			System.out.println("Error:"+e);
			return null;
			
		}
		
	}
}
