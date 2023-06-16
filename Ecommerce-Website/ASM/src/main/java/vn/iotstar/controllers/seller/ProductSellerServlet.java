package vn.iotstar.controllers.seller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import vn.iotstar.dao.*;
import vn.iotstar.entity.*;

@WebServlet({ "/seller/products/index", "/seller/products/create", "/seller/products/update", "/seller/products/delete",
		"/seller/products/search" })

public class ProductSellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private CategoryDAO cateDao;
	private CategoryDetailDao procDao;
	HttpSession session = null;


	public ProductSellerServlet() {
		super();
		this.productDAO = new ProductDAO();
		this.cateDao = new CategoryDAO();
		this.procDao = new CategoryDetailDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("index")) {
			this.index(request, response);
		} else if (uri.contains("search")) {
			this.search(request, response);
		}else if (uri.contains("delete")) {
			this.delete(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String proname =request.getParameter("proname");

		session = request.getSession();
		session.setAttribute("proname",proname);
		if (uri.contains("create")) {
			this.create(request, response);
		} else if (uri.contains("update")) {
			this.update(request, response);
		} else if (uri.contains("search")) {
			this.search(request, response);
		} else {
			request.getRequestDispatcher("/views/404.jsp").forward(request, response);
		}
	}

	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indexStr = request.getParameter("index");
		HttpSession session = request.getSession();
		int sellId =(int) session.getAttribute("sellerid");
		if (indexStr == null) {
			indexStr = "1";
		}
		try {
			int index = Integer.parseInt(indexStr);
			long count = this.productDAO.getTotalProductSeller(sellId);
			long endPage = count / 5;
			if (count % 5 != 0) {
				endPage++;
			}
			List<Category> categories = cateDao.findAll();
			request.setAttribute("cateList", categories);
			request.setAttribute("index", index);
			request.setAttribute("endPage", endPage);
			request.setAttribute("total", count);
			request.setAttribute("listPagination",
					this.productDAO.paginationseller(index, 5, sellId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/seller/product.jsp").forward(request, response);
	}

	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		String indexStr = request.getParameter("index");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int sellid = user.getSeller().getSellId();
		String proname = (String)session.getAttribute("proname");

		if (indexStr == null) {
			indexStr = "1";
		}
		try {
			int index = Integer.parseInt(indexStr);
			long count = this.productDAO.getTotalProductSeller(sellid, proname);
			long endPage = count / 5;
			if (count % 5 != 0) {
				endPage++;
			}
			List<Category> categories = cateDao.findAll();
			request.setAttribute("cateList", categories);
			request.setAttribute("index", index);
			request.setAttribute("endPage", endPage);
			request.setAttribute("total", count);
			request.setAttribute("listPagination", this.productDAO.paginationseller(index, 5, sellid, proname));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/seller/searchmanagerpro.jsp").forward(request, response);
	}

	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {
			User user = (User) session.getAttribute("user");
			String cateName = request.getParameter("catename");
			Category cate = cateDao.findById(Integer.parseInt(cateName));
			Product product = new Product();
			BeanUtils.populate(product, request.getParameterMap());

			product.setStatus(1);
			product.setSeller(user.getSeller());
			session.setAttribute("messageupdateSuccess", "Your product has been created !");
			session.setAttribute("display", "show");
			this.productDAO.create(product);
			ProductsCategory productsCategory = new ProductsCategory();
			productsCategory.setCategory(cate);
			productsCategory.setProduct(product);
			procDao.create(productsCategory);
			response.sendRedirect("/ASM/seller/products/index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("messageupdateSuccess", "Your product doesnt have been created !");
			session.setAttribute("display", "show");
			response.sendRedirect("/ASM/seller/products/index");
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		HttpSession session = request.getSession();
		try {
			int id = Integer.parseInt(idStr);
			Product entity = this.productDAO.findById(id);
			session.setAttribute("messageupdateSuccess", "Your product has been deleted !");
			session.setAttribute("display", "show");
			this.productDAO.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("messageupdateSuccess", "Your product doesnt have been deleted !");
			session.setAttribute("display", "show");
		}
		response.sendRedirect("/ASM/seller/products/index");
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		try {
			Product newEntity = new Product();
			BeanUtils.populate(newEntity, request.getParameterMap());
			User user = (User) session.getAttribute("user");
			newEntity.setSeller(user.getSeller());

			session.setAttribute("messageupdateSuccess", "Your product has been updated !");
			session.setAttribute("display", "show");
			this.productDAO.update(newEntity);
			response.sendRedirect("/ASM/seller/products/index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("messageupdateSuccess", "Your product doesnt have been updated !");
			session.setAttribute("display", "show");
			response.sendRedirect("/ASM/seller/products/index");
		}
	}
}
