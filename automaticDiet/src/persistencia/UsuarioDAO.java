package persistencia;


import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import modelo.Plato;

public class UsuarioDAO extends AbstractDAO{
	
	/**
	 * @param idUsuario id del usuario
	 * @param fecha fecha en formato string
	 * @return
	 */
	public List<Plato> dietaSemanal(int idUsuario, Date fechaIni, Date fechaFin){
		
		try{
			Query q;
			q = entityManager.createQuery("SELECT p "
					+ "FROM Plato p, PlatoDieta pd, Dieta d, Usuario u "
					+ "WHERE pd.dia>=:fechaI "
					+ "AND pd.dia<=:fechaF "
					+ "AND pd.plato.id=p.id");
			q.setParameter("fechaI", fechaIni);
			q.setParameter("fechaF", fechaFin);
			return (List<Plato>) q.getResultList();
			
			
		}catch(Exception e){
			System.out.println("Error:"+e);
			return null;
			
		}
		
	}
	
}
