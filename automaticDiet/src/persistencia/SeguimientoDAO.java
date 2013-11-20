package persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityTransaction;
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
	
	public void setSeguimiento(Seguimiento s)
	{
		EntityTransaction trx = entityManager.getTransaction();
		
		try
		{	
			trx.begin();
			entityManager.persist(s);
	        trx.commit();
		}
		catch (Exception e)
		{
	        e.printStackTrace();
	    }
		finally
		{
	         if (trx.isActive())
	             trx.rollback();
	    }
	}
	
	public boolean existeSeg(Seguimiento s)
	{
		try
		{
			Query q;
			q = entityManager.createQuery("SELECT s FROM Seguimiento s WHERE s.fecha = :f  AND s.usuario2.id = :u");
			q.setParameter("f", s.getFecha());
			q.setParameter("u", s.getUsuario2().getId());
			
			return (!q.getResultList().isEmpty());
		}
		catch(Exception e)
		{
			System.out.println("Error:"+e);
			return false;
		}
	}
	
	public void updateSeg(Seguimiento s)
	{
		EntityTransaction trx = entityManager.getTransaction();
		
		try
		{	
			trx.begin();
			Seguimiento seg = getSeguimiento(s.getUsuario2().getId(), s.getFecha());
			if (s.getCumplido()!=null)
			{
				seg.setCumplido(s.getCumplido());
			}
			if (s.getPeso()!=null)
			{
				seg.setPeso(s.getPeso());
			}
			
	        trx.commit();
		}
		catch (Exception e)
		{
	        e.printStackTrace();
	    }
		finally
		{
	         if (trx.isActive())
	             trx.rollback();
	    }
	}

	private Seguimiento getSeguimiento(int id, Date fecha) {
		try
		{
			Query q;
			q = entityManager.createQuery("SELECT s FROM Seguimiento s WHERE s.fecha = :f  AND s.usuario2.id = :u");
			q.setParameter("f", fecha);
			q.setParameter("u", id);
			
			return (Seguimiento) q.getSingleResult();
		}
		catch(Exception e)
		{
			System.out.println("Error:"+e);
		}
		return null;
	}
}
