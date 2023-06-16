package vn.iotstar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import vn.iotstar.entity.OrderDetail;
import vn.iotstar.utils.JpaUtils;

public class OrderDetailDAO {

	private EntityManager em;
	public OrderDetailDAO() {
		this.em = JpaUtils.getEntityManager();
	}
	
	public List<OrderDetail> findAll() throws Exception {
		try {
			this.em.getTransaction().begin();
			String jpql = "SELECT obj FROM OrderDetail obj";
			TypedQuery<OrderDetail> query = this.em.createQuery(jpql, OrderDetail.class);
			this.em.getTransaction().commit();
			return query.getResultList();	
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	
	public List<OrderDetail> findBySellId(int sellId) throws Exception {
		try {
			this.em.getTransaction().begin();
			String jpql = "SELECT odt FROM OrderDetail odt WHERE odt.product.seller.id ="+sellId;
			TypedQuery<OrderDetail> query = this.em.createQuery(jpql, OrderDetail.class);
			this.em.getTransaction().commit();
			return query.getResultList();	
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}

	}


	public double getTotalPrice(String month, int sellerID) {
		String jpql = "SELECT sum(odt.quantity * odt.product.price) FROM OrderDetail odt WHERE odt.product.seller.id ="+sellerID+" and SUBSTRING(odt.order.orderDate, 4, 7) = :month";
		TypedQuery<Double> query = this.em.createQuery(jpql, Double.class);
		query.setParameter("month", month);

		return query.getSingleResult();
	}
	public double getTotalPrice(int sellerID) {
		String jpql = "SELECT sum(odt.quantity * odt.product.price) FROM OrderDetail odt WHERE odt.product.seller.id ="+sellerID;
		TypedQuery<Double> query = this.em.createQuery(jpql, Double.class);

		return query.getSingleResult();
	}
	public OrderDetail findById(int id) {
		return this.em.find(OrderDetail.class, id);
	}
	
	public OrderDetail create(OrderDetail entity) throws Exception {
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
	
	public OrderDetail delete(OrderDetail entity) throws Exception {
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
	
	public OrderDetail update(OrderDetail entity) throws Exception {
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
	
}