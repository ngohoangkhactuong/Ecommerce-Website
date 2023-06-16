package vn.iotstar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import vn.iotstar.entity.Product;
import vn.iotstar.utils.*;

public class ProductDAO {

	private EntityManager em;

	public ProductDAO() {
		this.em = JpaUtils.getEntityManager();
	}

	public List<Product> findAll() {
		TypedQuery<Product> query = this.em.createNamedQuery("Product.findAll", Product.class);
		return query.getResultList();
	}

	public List<Product> findBySellId(String sellId) {
		String jpql = "SELECT p FROM Product p WHERE p.seller.id = " + sellId;
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class);
		return query.getResultList();
	}

	public List<Product> Search(String sellId, String namePro) {
		String jpql = "SELECT p FROM Product p WHERE p.seller.id = " + sellId + " and p.product.name like %" + namePro
				+ "%";
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class);
		return query.getResultList();
	}

	public List<Product> pagination(int index, int n) {
		String jpql = "SELECT obj FROM Product obj WHERE obj.stock >0 ORDER BY obj.id";
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class).setMaxResults(n)
				.setFirstResult((index - 1) * n); // 1 trang se co toi da n san pham
		return query.getResultList();
	}

	public List<Product> paginationseller(int index, int n, int sellerId) {
		String jpql = "SELECT p FROM Product p WHERE  p.seller.id =" + sellerId + "ORDER BY p.id";
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class).setMaxResults(n)
				.setFirstResult((index - 1) * n); // 1 trang se co toi da n san pham
		return query.getResultList();
	}

	public List<Product> paginationsellerByStock(int index, int n, int sellerId) {
		String jpql = "SELECT p FROM Product p WHERE p.stock>0 and p.seller.id =" + sellerId + "ORDER BY p.id";
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class).setMaxResults(n)
				.setFirstResult((index - 1) * n); // 1 trang se co toi da n san pham
		return query.getResultList();
	}
	public List<Product> searchProByName(int index, int n, String namePro) {
		String jpql = "SELECT p FROM Product p WHERE p.name like concat('%',:name,'%') " + " ORDER BY p.id";
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class).setMaxResults(n)
				.setFirstResult((index - 1) * n);
		query.setParameter("name", namePro);
		return query.getResultList();
	}

	public List<Product> paginationsellerByStock(int index, int n, int sellerId, String namePro) {
		String jpql = "SELECT p FROM Product p WHERE p.stock>0 and p.seller.id =" + sellerId
				+ " and p.name like concat('%',:name,'%') " + " ORDER BY p.id";
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class).setMaxResults(n)
				.setFirstResult((index - 1) * n);
		query.setParameter("name", namePro);
		return query.getResultList();
	}
	public List<Product> paginationseller(int index, int n, int sellerId, String namePro) {
		String jpql = "SELECT p FROM Product p WHERE  p.seller.id =" + sellerId
				+ " and p.name like concat('%',:name,'%') " + " ORDER BY p.id";
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class).setMaxResults(n)
				.setFirstResult((index - 1) * n);
		query.setParameter("name", namePro);
		return query.getResultList();
	}

	public List<Product> paginationHome(int index, int n, int ctId) {
		String jpql = "SELECT p FROM Product p JOIN ProductsCategory pc ON pc.product.id = p.id JOIN Category c ON c.id=pc.category.id WHERE p.stock >0 and c.id="
				+ ctId;
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class).setMaxResults(n)
				.setFirstResult((index - 1) * n); // 1 trang se co toi da n san pham
		return query.getResultList();
	}

	public List<Product> paginationHomeSeller(int index, int n, int ctId, int sellID) {
		String jpql = "SELECT p FROM Product p JOIN ProductsCategory pc ON pc.product.id = p.id JOIN Category c ON c.id=pc.category.id WHERE c.id="
				+ ctId + "and p.seller.id =" + sellID;
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class).setMaxResults(n)
				.setFirstResult((index - 1) * n); // 1 trang se co toi da n san pham
		return query.getResultList();
	}
	public List<Product> paginationHomeSellerByStock(int index, int n, int ctId, int sellID) {
		String jpql = "SELECT p FROM Product p JOIN ProductsCategory pc ON pc.product.id = p.id JOIN Category c ON c.id=pc.category.id WHERE p.stock>0 and c.id="
				+ ctId + "and p.seller.id =" + sellID;
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class).setMaxResults(n)
				.setFirstResult((index - 1) * n); // 1 trang se co toi da n san pham
		return query.getResultList();
	}
	public long getTotalProduct() {
		String jpql = "SELECT count(obj.id) FROM Product obj";
		TypedQuery<Long> query = this.em.createQuery(jpql, Long.class);
		return query.getSingleResult();
	}

	public long getTotalProductSeller(int sellerId) {
		String jpql = "SELECT count(p.id) FROM Product p WHERE p.seller.id = " + sellerId;
		TypedQuery<Long> query = this.em.createQuery(jpql, Long.class);
		return query.getSingleResult();
	}
	public long getTotalProductSellerByStock(int sellerId) {
		String jpql = "SELECT count(p.id) FROM Product p WHERE p.stock>0 and p.seller.id = " + sellerId;
		TypedQuery<Long> query = this.em.createQuery(jpql, Long.class);
		return query.getSingleResult();
	}

	public long getTotalProductSeller(int sellerId, String namePro) {
		String jpql = "SELECT count(p.id) FROM Product p WHERE p.seller.id = " + sellerId
				+ " and p.name like concat('%',:name,'%') ";
		TypedQuery<Long> query = this.em.createQuery(jpql, Long.class);
		query.setParameter("name", namePro);
		return query.getSingleResult();
	}
	public long getTotalProductSellerByStock(int sellerId, String namePro) {
		String jpql = "SELECT count(p.id) FROM Product p WHERE p.stock>0 and p.seller.id = " + sellerId
				+ " and p.name like concat('%',:name,'%') ";
		TypedQuery<Long> query = this.em.createQuery(jpql, Long.class);
		query.setParameter("name", namePro);
		return query.getSingleResult();
	}
	public long getTotalProductByCateId(int ctId) {
		String jpql = "SELECT count(*) FROM Product p JOIN ProductsCategory pc ON pc.product.id = p.id JOIN Category c ON c.id=pc.category.id WHERE c.id="
				+ ctId;
		TypedQuery<Long> query = this.em.createQuery(jpql, Long.class);
		return query.getSingleResult();
	}

	public long getTotalProductByCateIdSellId(int ctId, int sellId) {
		String jpql = "SELECT count(*) FROM Product p JOIN ProductsCategory pc ON pc.product.id = p.id JOIN Category c ON c.id=pc.category.id WHERE c.id="
				+ ctId + "and p.seller.id =" + sellId;
		TypedQuery<Long> query = this.em.createQuery(jpql, Long.class);
		return query.getSingleResult();
	}

	public long getTotalProductByCateIdSellIdByStock(int ctId, int sellId) {
		String jpql = "SELECT count(*) FROM Product p JOIN ProductsCategory pc ON pc.product.id = p.id JOIN Category c ON c.id=pc.category.id WHERE p.stock>0 and c.id="
				+ ctId + "and p.seller.id =" + sellId;
		TypedQuery<Long> query = this.em.createQuery(jpql, Long.class);
		return query.getSingleResult();
	}

	public Product findById(int id) {
		return this.em.find(Product.class, id);
	}

	public Product create(Product entity) throws Exception {
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

	public Product delete(Product entity) throws Exception {
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

	public Product update(Product entity) throws Exception {
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

	public List<Product> TopPro() {
		String jpql = "SELECT p FROM Product p  ORDER BY p.amount";
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class).setMaxResults(3);
		return query.getResultList();
	}

	public List<Product> TopProSeller(int sellId) {
		String jpql = "SELECT p FROM Product p WHERE p.seller.id = " + sellId + " ORDER BY p.amount DESC";
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class).setMaxResults(3);
		return query.getResultList();
	}
	
	
	//Home search
	public List<Product> pagination(int index,int n,String str) {
		String jpql = "SELECT obj FROM Product obj  WHERE obj.stock > 0 and obj.name like concat('%',:str,'%') ORDER BY obj.id";
		TypedQuery<Product> query = this.em.createQuery(jpql, Product.class).setMaxResults(n).setFirstResult((index-1)*n); // 1 trang se co toi da n san pham
		query.setParameter("str", str);
		return query.getResultList(); 
	}
	public long getTotalProduct(String str) {
		String jpql = "SELECT count(obj.id) FROM Product obj WHERE obj.stock > 0 and  obj.name like concat('%',:str,'%') ";
		TypedQuery<Long> query = this.em.createQuery(jpql, Long.class);
		query.setParameter("str", str);
		return query.getSingleResult();
	}
}
