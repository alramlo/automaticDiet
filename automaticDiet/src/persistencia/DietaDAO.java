package persistencia;

import java.util.Date;

import javax.persistence.Query;

import modelo.Usuario;

public class DietaDAO extends AbstractDAO{

	public long getSemanasDieta(Date date, Usuario user) {
		try{
			Query q;
			q = entityManager.createQuery("SELECT COUNT(pd) "
					+ "FROM PlatoDieta pd, Dieta d, Usuario u "
					+ "WHERE d.fechaInicial<=:fecha "
					+ "AND d.usuario.id=:ident "
					+ "AND d.fechaFinal>=:fecha "
					+ "AND pd.dieta.id=d.id");
			q.setParameter("fecha", date);
			q.setParameter("ident", user.getId());
			return (long) q.getSingleResult();
			
			
		}catch(Exception e){
			System.out.println("Error:"+e);
			return -1;
			
		}
		
	}

}
