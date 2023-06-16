package vn.iotstar.controllers.seller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.CategoryDAO;
import vn.iotstar.dao.ProductDAO;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.User;

@WebServlet({ "/home/seller","/home/seller/search" })

public class HomeSellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private CategoryDAO categoryDAO;
	HttpSession session = null;

	public HomeSellerServlet() {
		super();
		this.productDAO = new ProductDAO();
		this.categoryDAO = new CategoryDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		TopProduct(request, response);

		if (uri.contains("search"))
		{
			this.search(request, response);

		}else {
			pagination(request, response);
			TotalProduct(request, response);
			request.getRequestDispatcher("/views/seller/home.jsp").forward(request, response);
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

	protected void pagination(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("listCategory", this.categoryDAO.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		session = request.getSession();
		int sellId =(int) session.getAttribute("sellerid");
		String indexStr = request.getParameter("index");
		String cateId = request.getParameter("ctid");
		request.setAttribute("tagactive", cateId);

		if (indexStr == null) {
			indexStr = "1";
		}
		int index = Integer.parseInt(indexStr);
		if ("0".equals(cateId)) {
			try {
				long count = this.productDAO.getTotalProductSellerByStock(sellId);
				long endPage = count / 8;
				if (count % 8 != 0) {
					endPage++;
				}
				request.setAttribute("endPage", endPage);
				request.setAttribute("listPagination", this.productDAO.paginationsellerByStock(index, 8, sellId));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				int cateid = Integer.parseInt(cateId);
				long count = this.productDAO.getTotalProductByCateIdSellIdByStock(cateid, sellId);

				long endPage = count / 8;
				if (count % 8 != 0) {
					endPage++;
				}
				request.setAttribute("endPage", endPage);
				request.setAttribute("listPagination", this.productDAO.paginationHomeSellerByStock(index, 8, cateid, sellId));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("index", index);
	}
	protected void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("listCategory", this.categoryDAO.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		session = request.getSession();
		String str = (String)session.getAttribute("str");
		int sellId =(int) session.getAttribute("sellerid");
		String indexStr = request.getParameter("index");
		String cateId = request.getParameter("ctid");
		request.setAttribute("tagactive", cateId);

		if (indexStr == null) {
			indexStr = "1";
		}
		int index = Integer.parseInt(indexStr);
		if ("0".equals(cateId)) {
			try {
				long count = this.productDAO.getTotalProductSellerByStock(sellId,str);
				long endPage = count / 8;
				if (count % 8 != 0) {
					endPage++;
				}
				request.setAttribute("endPage", endPage);
				request.setAttribute("listPagination", this.productDAO.paginationsellerByStock(index, 8, sellId,str));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				int cateid = Integer.parseInt(cateId);
				long count = this.productDAO.getTotalProductByCateIdSellId(cateid, sellId);

				long endPage = count / 8;
				if (count % 8 != 0) {
					endPage++;
				}
				request.setAttribute("endPage", endPage);
				request.setAttribute("listPagination", this.productDAO.paginationHomeSeller(index, 8, cateid, sellId));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("index", index);
		request.getRequestDispatcher("/views/seller/searchproduct.jsp").forward(request, response);

	}
	protected void TopProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		int sellId = (int) (session.getAttribute("sellerid"));
		List<Product> lstProducts = productDAO.TopProSeller(sellId);
		request.setAttribute("pro1", lstProducts.get(0));
		request.setAttribute("pro2", lstProducts.get(1));
		request.setAttribute("pro3", lstProducts.get(2));
	}

	protected void TotalProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		int sellId = (int) (session.getAttribute("sellerid"));
		long total = productDAO.getTotalProductSellerByStock(sellId);
		request.setAttribute("totalPro", Long.toString(total));

	}
}
