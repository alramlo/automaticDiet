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
			q = entityManager.createQuery("SELECT s FROM Seguimiento s WHERE s.usuario2.id = :u ");
			q.setParameter("u", idUsuario);
			return (List<Seguimiento>) q.getResultList();
		}
		catch(Exception e)
		{
			System.out.println("Error:"+e);
			return null;
			
		}
		
	}
}
