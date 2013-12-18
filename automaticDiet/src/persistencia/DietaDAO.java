package persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import modelo.Dieta;
import modelo.Plato;
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
	
	public long getSemanasDieta(Integer cod) {
		try{
			Query q;
			q = entityManager.createQuery("SELECT COUNT(pd)/4 "
					+ "FROM PlatoDieta pd "
					+ "WHERE pd.dieta.id=:cod ");
			q.setParameter("cod", cod);
			return (long) q.getSingleResult();
			
			
		}catch(Exception e){
			System.out.println("Error:"+e);
			return -1;
			
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Dieta> getDietas(Usuario user) {
		try{
			Query q;
			q = entityManager.createQuery("SELECT d "
					+ "FROM Dieta d "
					+ "WHERE d.usuario.id=:ident "
					+ "ORDER BY d.fechaInicial DESC");
			q.setParameter("ident", user.getId());;
			return (List<Dieta>) q.getResultList();
		
		
		}catch(Exception e){
			System.out.println("Error:"+e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Dieta> getDietasASC(Usuario user) {
		try{
			Query q;
			q = entityManager.createQuery("SELECT d "
					+ "FROM Dieta d "
					+ "WHERE d.usuario.id=:ident "
					+ "ORDER BY d.fechaInicial");
			q.setParameter("ident", user.getId());;
			return (List<Dieta>) q.getResultList();
		
		
		}catch(Exception e){
			System.out.println("Error:"+e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Dieta> getDietasLibres(){
		try{
			Query q;
			q = entityManager.createQuery("SELECT d "
					+ "FROM Dieta d "
					+ "WHERE d.usuario = null");
			return (List<Dieta>) q.getResultList();
		
		}catch(Exception e){
			System.out.println("Error:"+e);
			return null;
		}
		
	}

	public Dieta getDietaPorNombre(String nombre) {
		try{
			Query q;
			q = entityManager.createQuery("SELECT d "
					+ "FROM Dieta d "
					+ "WHERE d.nombre=:nom ");
			q.setParameter("nom", nombre);;
			return (Dieta) q.getSingleResult();
		
		
		}catch(Exception e){
			System.out.println("Error:"+e);
			return null;
		}
	}
	
	public void incribirseEnDieta(Integer codigo, Date fecha, Usuario user){
		EntityTransaction trx = entityManager.getTransaction();
		Dieta dietaAux; 
		try{
			trx.begin();
			dietaAux=entityManager.find(Dieta.class, codigo);
			dietaAux.setFechaInicial(fecha);
			dietaAux.setUsuario(user);
			trx.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(trx.isActive())
				trx.rollback();
		}
	}
	
	public void desincribirseEnDieta(Integer codigo){
		EntityTransaction trx = entityManager.getTransaction();
		Dieta dietaAux; 
		try{
			trx.begin();
			dietaAux=entityManager.find(Dieta.class, codigo);
			dietaAux.setFechaInicial(null);
			dietaAux.setUsuario(null);
			trx.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(trx.isActive())
				trx.rollback();
		}
	}

}
