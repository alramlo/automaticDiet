package persistencia;


import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import modelo.Plato;
import modelo.Usuario;

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
	
public Usuario getIdUsuario(String n, String a){
		
		try{
			Query q;
			q = entityManager.createQuery("SELECT u.id "
					+ "FROM Usuario u "
					+ "WHERE u.nombre=:nom "
					+ "AND u.apellidos=:apell");
			q.setParameter("nom", n);
			q.setParameter("apell", a);
			return (Usuario) q.getSingleResult();
		}catch(Exception e){
			System.out.println("Error:"+e);
			return null;
			
		}
		
	}
	
}
