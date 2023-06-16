package vn.iotstar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import vn.iotstar.entity.History;
import vn.iotstar.utils.JpaUtils;

public class HistoryDAO {

	private EntityManager em;
	public HistoryDAO() {
		this.em = JpaUtils.getEntityManager();
	}
	
	public History create(History entity) throws Exception {
		try {
			this.em.getTransaction().begin();
			this.em.persist(entity);
			this.em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	public List<History> findAll() throws Exception {
		try {
			this.em.getTransaction().begin();
			String jpql = "SELECT obj FROM History obj WHERE obj.status = 1";
			TypedQuery<History> query = this.em.createQuery(jpql, History.class);
			this.em.getTransaction().commit();
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	public History update(History entity) throws Exception{
		try {
			this.em.getTransaction().begin();
			this.em.merge(entity);
			this.em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	public List<History> history(String useremail) throws Exception {
		try {
			String jpql = "SELECT h FROM History h WHERE h.user_email = :useremail";

			TypedQuery<History> query = this.em.createQuery(jpql,History.class);
			query.setParameter("useremail", useremail);

			List<History> result = query.getResultList();
			return result;	
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}	
	}	
	public List<History> findById(int id, int userid) throws Exception {
		try {
			String jpql = "SELECT h FROM History h WHERE h.status=1 and h.user_id = "+userid+"and h.id="+id;
			TypedQuery<History> query = this.em.createQuery(jpql,History.class);
			List<History> result = query.getResultList();
			return result;	
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}	
	}	
	public History findById(int id) {
		return this.em.find(History.class, id);
	}
	public List<History> findbymonth( String month,int id ) throws Exception {
		try {
			String jpql = "SELECT h FROM History h WHERE SUBSTRING(h.orderDate, 4, 7) = :month and h.id = :id and h.status=1";
			TypedQuery<History> query = this.em.createQuery(jpql,History.class);
			query.setParameter("month", month);
			query.setParameter("id", id);

			List<History> result = query.getResultList();
			return result;	
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}	
	}	
}