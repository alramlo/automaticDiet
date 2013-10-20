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
					+ "FROM Dieta d, Usuario u, PlatoDieta pd, Plato p "
					+ "WHERE u.id=:usu AND "
					+ "d.usuario.id=u.id AND "
					+ "pd.dieta.id=d.id AND "
					+ "pd.id.dia>=:diaI AND "
					+ "pd.id.dia<=:diaF");
			q.setParameter("usu", idUsuario);
			q.setParameter("diaI", fechaIni);
			q.setParameter("diaF", fechaFin);
			return (List<Plato>) q.getResultList();
			
			
		}catch(Exception e){
			System.out.println("Error:"+e);
			return null;
			
		}
		
	}
	
}
