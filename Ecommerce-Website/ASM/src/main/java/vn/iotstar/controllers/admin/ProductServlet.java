package vn.iotstar.controllers.admin;

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

@WebServlet({ "/admin/products/index", "/admin/products/status","/admin/products/search" })

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private CategoryDAO cateDao;
	private SellerDAO sellerDao;

	public ProductServlet() {
		super();
		this.productDAO = new ProductDAO();
		this.cateDao = new CategoryDAO();
		this.sellerDao = new SellerDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("index")) {
			this.index(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("search")) {
			this.search(request, response);
		} else  if (uri.contains("status")) {
				try {
					this.status(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		else {
			request.getRequestDispatcher("/views/404.jsp").forward(request, response);
		}
	}

	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indexStr = request.getParameter("index");

		if (indexStr == null) {
			indexStr = "1";
		}
		try {
			int index = Integer.parseInt(indexStr);
			long count = this.productDAO.getTotalProduct();
			long endPage = count / 8;
			if (count % 8 != 0) {
				endPage++;
			}
			List<Category> categories = cateDao.findAll();

			List<Seller> sellers = sellerDao.findAll();
			request.setAttribute("sellerList", sellers);
			request.setAttribute("cateList", categories);
			request.setAttribute("index", index);
			request.setAttribute("endPage", endPage);
			request.setAttribute("total", count);
			request.setAttribute("listPagination", this.productDAO.pagination(index, 8));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("viewAdmin", "/views/admin/product.jsp");
		request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
	}

	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indexStr = request.getParameter("index");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String proname = request.getParameter("proname");

		if (indexStr == null) {
			indexStr = "1";
		}
		try {
			int index = Integer.parseInt(indexStr);
			long count = this.productDAO.getTotalProduct();
			long endPage = count / 8;
			if (count % 8 != 0) {
				endPage++;
			}
			List<Category> categories = cateDao.findAll();
			request.setAttribute("cateList", categories);
			request.setAttribute("index", index);
			request.setAttribute("endPage", endPage);
			request.setAttribute("total", count);
			request.setAttribute("listPagination", this.productDAO.searchProByName(index, 5 , proname));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/admin/product.jsp").forward(request, response);
	}
	public void status(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String proId = request.getParameter("id");
		String act = request.getParameter("act");
		
		HttpSession session = request.getSession();
		Product product = productDAO.findById(Integer.parseInt(proId));
		try {
			if (act.equals("unblock"))
			{
				product.setStatus(1);
				productDAO.update(product);
				session.setAttribute("messageupdateSuccess", "Unblock Successfull !");
				session.setAttribute("display", "show");
			}
			else if (act.equals("block"))
			{
				product.setStatus(0);
				productDAO.update(product);
				session.setAttribute("messageupdateSuccess", "block Successfull !");
				session.setAttribute("display", "show");
			}
			
		} catch (Exception e) {
		}
		response.sendRedirect("/ASM/admin/products/index");

	}
}
