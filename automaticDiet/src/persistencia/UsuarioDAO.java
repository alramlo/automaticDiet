package persistencia;


import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import modelo.Grupo;
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
			q = entityManager.createQuery("SELECT u "
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
public List<Usuario> getNumParticipantes(Grupo g){
	try{
		Query q;
		q = entityManager.createQuery("SELECT u "
				+ "FROM Usuario u, UsuarioGrupo ug, Grupo g "
				+ "WHERE g.nombre=:nom "
				+ "AND g.pais=:p "
				+ "AND g.ciudad=:c "
				+ "AND u.id=ug.usuario.id "
				+ "AND ug.grupo.id=g.id");
		q.setParameter("nom", g.getNombre());
		q.setParameter("p", g.getPais());
		q.setParameter("c", g.getCiudad());
		return (List<Usuario>) q.getResultList();
	}catch(Exception e){
		System.out.println("Error:"+e);
		return null;
		
	}
}

public List<Usuario> getUsuarioPorNombre(String nombre) {
	try{
		Query q;
		q = entityManager.createQuery("SELECT u "
				+ "FROM Usuario u "
				+ "WHERE u.nombre=:nom");
		q.setParameter("nom", nombre);
		return (List<Usuario>) q.getResultList();
	}catch(Exception e){
		System.out.println("Error:"+e);
		return null;
		
	}
}

public List<Usuario> getUsuarioPorApellios(String apellidos) {
	try{
		Query q;
		q = entityManager.createQuery("SELECT u "
				+ "FROM Usuario u "
				+ "WHERE u.apellidos=:apell");
		q.setParameter("apell", apellidos);
		return (List<Usuario>) q.getResultList();
	}catch(Exception e){
		System.out.println("Error:"+e);
		return null;
		
	}
}

public List<Usuario> getUsuarios() {
	try{
		Query q;
		q = entityManager.createQuery("SELECT u "
				+ "FROM Usuario u ");
		return (List<Usuario>) q.getResultList();
	}catch(Exception e){
		System.out.println("Error:"+e);
		return null;
		
	}
}

public Usuario getUsuarioPorId(int i) {
	try{
		Query q;
		q = entityManager.createQuery("SELECT u "
				+ "FROM Usuario u "
				+ "WHERE u.id=:id");
		q.setParameter("id", i);
		return (Usuario) q.getSingleResult();
	}catch(Exception e){
		System.out.println("Error:"+e);
		return null;
		
	}
}

public Usuario getUsuarioPorDNI(String dni) {
	try{
		Query q;
		q = entityManager.createQuery("SELECT u "
				+ "FROM Usuario u "
				+ "WHERE u.dni=:dni");
		q.setParameter("dni", dni);
		return (Usuario) q.getSingleResult();
	}catch(Exception e){
		System.out.println("Error:"+e);
		return null;
		
	}
}
	
}
