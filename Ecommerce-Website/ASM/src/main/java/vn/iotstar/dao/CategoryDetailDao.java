package vn.iotstar.dao;

import javax.persistence.EntityManager;

import vn.iotstar.entity.ProductsCategory;
import vn.iotstar.utils.JpaUtils;

public class CategoryDetailDao {
	private EntityManager em;
	public CategoryDetailDao() {
		this.em =  JpaUtils.getEntityManager(); 
	}

	public ProductsCategory create(ProductsCategory entity) throws Exception {
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

}
