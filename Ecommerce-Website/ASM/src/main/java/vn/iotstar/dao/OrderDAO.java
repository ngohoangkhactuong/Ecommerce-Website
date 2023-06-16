package vn.iotstar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import vn.iotstar.filter.*;
import vn.iotstar.utils.*;
import vn.iotstar.entity.*;
public class OrderDAO {

	private EntityManager em;
	public OrderDAO() {
		this.em = JpaUtils.getEntityManager();
	}
	
	public List<Order> findAll(int index, int n) throws Exception {
		try {
			this.em.getTransaction().begin();
			String jpql = "SELECT obj FROM Order obj WHERE obj.orderStatus != 3";
			TypedQuery<Order> query = this.em.createQuery(jpql, Order.class).setMaxResults(n).setFirstResult((index-1)*n);
			this.em.getTransaction().commit();
			return query.getResultList();	
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}	
	}
	public Order update(Order entity) throws Exception{
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
	public Order findById(int id) {
		return this.em.find(Order.class, id);
	}
	
	public long getTotalOrder() {
		String jpql = "SELECT count(obj.id) FROM Order obj  WHERE obj.orderStatus != 3";
		TypedQuery<Long> query = this.em.createQuery(jpql, Long.class);
		return query.getSingleResult();
	}
	
	
	public Order create(Order entity) throws Exception {
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
	public List<Order> FindByUserID(int userID) {
		try {
			String jpql = "SELECT od FROM Order od WHERE od.orderStatus = 3 and user.id= "+userID;
			TypedQuery<Order> query = this.em.createQuery(jpql, Order.class);
			return query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}
}
