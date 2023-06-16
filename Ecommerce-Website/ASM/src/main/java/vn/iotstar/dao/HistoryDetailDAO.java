package vn.iotstar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import vn.iotstar.entity.History;
import vn.iotstar.entity.HistoryDetail;
import vn.iotstar.utils.JpaUtils;

public class HistoryDetailDAO {
	private EntityManager em;
	public HistoryDetailDAO() {
		this.em = JpaUtils.getEntityManager();
	}
	public HistoryDetail create(HistoryDetail entity) throws Exception {
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
	public List<Integer> LOrderBySellerId(int sellerId)
	{
		try {
			String jpql = "SELECT distinct h.history.id  FROM HistoryDetail h WHERE h.sellid = "+sellerId;
			TypedQuery<Integer> query = this.em.createQuery(jpql,Integer.class);
			List<Integer> result = query.getResultList();
			return result;	
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}	
	}
	public List<HistoryDetail> LHisDetailByHID(int sellerId,int hisId )
	{
		try {
			String jpql = "SELECT  h  FROM HistoryDetail h WHERE h.sellid = "+sellerId+"and h.history.id ="+hisId;
			TypedQuery<HistoryDetail> query = this.em.createQuery(jpql,HistoryDetail.class);
			List<HistoryDetail> result = query.getResultList();
			return result;	
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}	
	}
}
