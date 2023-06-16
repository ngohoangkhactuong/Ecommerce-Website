package vn.iotstar.controllers.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.mapping.PrimaryKey;

import vn.iotstar.dao.*;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.User;

@WebServlet( urlPatterns = { "/home","/home/search" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private CategoryDAO categoryDAO;
	HttpSession session = null;

	public HomeServlet() {
		super();
		this.productDAO = new ProductDAO();
		this.categoryDAO = new CategoryDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		this.TopProduct(request, response);
		if (uri.contains("search"))
		{
			this.search(request, response);

		}else {
			this.pagination(request, response);
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String str =request.getParameter("str");
		session = request.getSession();
		session.setAttribute("str",str);
		 if (uri.contains("search")) {
				this.search(request, response);
				

		 }
	}

	protected void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("listCategory", this.categoryDAO.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String str = (String)session.getAttribute("str");

		String indexStr = request.getParameter("index");
		String cateId = request.getParameter("ctid");
		request.setAttribute("tagactive", cateId);

		if (indexStr == null) {
			indexStr = "1";
		}
		int index = Integer.parseInt(indexStr);
		if ("0".equals(cateId)) {
			try {
				long count = this.productDAO.getTotalProduct(str);
				long endPage = count / 8;
				if (count % 8 != 0) {
					endPage++;
				}
				request.setAttribute("endPage", endPage);
				request.setAttribute("listPagination", this.productDAO.pagination(index, 8,str));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				int cateid = Integer.parseInt(cateId);
				long count = this.productDAO.getTotalProductByCateId(cateid);

				long endPage = count / 8;
				if (count % 8 != 0) {
					endPage++;
				}
				request.setAttribute("endPage", endPage);
				request.setAttribute("listPagination", this.productDAO.paginationHome(index, 8, cateid));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("index", index);
		request.getRequestDispatcher("/views/searchproduct.jsp").forward(request, response);

	}


	protected void pagination(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("listCategory", this.categoryDAO.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String indexStr = request.getParameter("index");
		String cateId = request.getParameter("ctid");
		request.setAttribute("tagactive", cateId);

		if (indexStr == null) {
			indexStr = "1";
		}
		int index = Integer.parseInt(indexStr);
		if ("0".equals(cateId)) {
			try {
				long count = this.productDAO.getTotalProduct();
				long endPage = count / 8;
				if (count % 8 != 0) {
					endPage++;
				}
				request.setAttribute("endPage", endPage);
				request.setAttribute("listPagination", this.productDAO.pagination(index, 8));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				int cateid = Integer.parseInt(cateId);
				long count = this.productDAO.getTotalProductByCateId(cateid);

				long endPage = count / 8;
				if (count % 8 != 0) {
					endPage++;
				}
				request.setAttribute("endPage", endPage);
				request.setAttribute("listPagination", this.productDAO.paginationHome(index, 8, cateid));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("index", index);
	}

	protected void TopProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> lstProducts = productDAO.TopPro();
		request.setAttribute("pro1", lstProducts.get(0));
		request.setAttribute("pro2", lstProducts.get(1));
		request.setAttribute("pro3", lstProducts.get(2));
	}
}
