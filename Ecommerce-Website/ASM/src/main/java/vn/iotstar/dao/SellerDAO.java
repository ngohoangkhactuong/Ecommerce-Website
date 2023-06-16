package vn.iotstar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import vn.iotstar.entity.Seller;
import vn.iotstar.utils.JpaUtils;

public class SellerDAO {
	private EntityManager em;

	public SellerDAO() {
		this.em = JpaUtils.getEntityManager();
	}

	public Seller findById(int id) {
		return this.em.find(Seller.class, id);
	}

	public List<Seller> findAll() {
		TypedQuery<Seller> query = this.em.createNamedQuery("Seller.findAll", Seller.class);
		return query.getResultList();
	}
	public Seller create(Seller entity) throws Exception {
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
	public Seller update(Seller entity) throws Exception{
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
