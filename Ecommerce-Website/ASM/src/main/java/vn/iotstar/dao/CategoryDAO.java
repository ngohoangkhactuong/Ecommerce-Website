package vn.iotstar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.utils.*;

public class CategoryDAO {

	private EntityManager em;

	public CategoryDAO() {
		this.em = JpaUtils.getEntityManager();
	}

	public List<Category> findAll() throws Exception {
		try {
			this.em.getTransaction().begin();
			String jpql = "SELECT obj FROM Category obj";
			TypedQuery<Category> query = this.em.createQuery(jpql, Category.class);
			this.em.getTransaction().commit();
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}

	public List<Category> pagination(int index, int n) {
		String jpql = "SELECT obj FROM Category obj ORDER BY obj.id";
		TypedQuery<Category> query = this.em.createQuery(jpql, Category.class).setMaxResults(n)
				.setFirstResult((index - 1) * n); 
		return query.getResultList();
	}

	public long getTotalCategory() {
		String jpql = "SELECT count(obj.id) FROM Category obj";
		TypedQuery<Long> query = this.em.createQuery(jpql, Long.class);
		return query.getSingleResult();
	}
	public List<Category> findByName(String name) {
		
		try {
			this.em.getTransaction().begin();
			String jpql = "SELECT c FROM Category c WHERE c.name ="+name;
			TypedQuery<Category> query = this.em.createQuery(jpql, Category.class);
			this.em.getTransaction().commit();
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
		
	}
	public List<Category> searchCateByName(int index, int n, String namePro) {
		String jpql = "SELECT c FROM Category c WHERE c.name like concat('%',:name,'%') " + " ORDER BY c.id";
		TypedQuery<Category> query = this.em.createQuery(jpql, Category.class).setMaxResults(n)
				.setFirstResult((index - 1) * n);
		query.setParameter("name", namePro);
		return query.getResultList();
	}
	
	public Category findById(int id) {
		return this.em.find(Category.class, id);
	}

	public Category create(Category entity) throws Exception {
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

	public Category delete(Category entity) throws Exception {
		try {
			this.em.getTransaction().begin();
			this.em.remove(entity);
			this.em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}

	public Category update(Category entity) throws Exception {
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
	public long count() {
		String jpql = "SELECT count(obj.id) FROM Category obj";
		TypedQuery<Long> query = this.em.createQuery(jpql, Long.class);
		return query.getSingleResult();
	}
}
