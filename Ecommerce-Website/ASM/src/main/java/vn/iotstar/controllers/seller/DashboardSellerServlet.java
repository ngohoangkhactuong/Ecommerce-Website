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
import vn.iotstar.dao.HistoryDAO;
import vn.iotstar.dao.OrderDAO;
import vn.iotstar.dao.ProductDAO;
import vn.iotstar.dao.UserDAO;
import vn.iotstar.entity.History;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.User;

@WebServlet("/seller/dashboard")
public class DashboardSellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private CategoryDAO categoryDAO;
	private UserDAO userDAO;
	private OrderDAO orderDAO;
	HistoryDAO hisDAO = new HistoryDAO();
	HttpSession session = null;


	public DashboardSellerServlet() {
		super();
		this.productDAO = new ProductDAO();
		this.categoryDAO = new CategoryDAO();
		this.userDAO = new UserDAO();
		this.orderDAO = new OrderDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.TopProduct(request, response);
		this.TotalProduct(request, response);
		this.TotalOrder(request, response);
		this.TotalUser(request, response);
		try {
			findall(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		request.setAttribute("viewAdmin", "/views/seller/dashboard.jsp");
		request.getRequestDispatcher("/views/seller/seller.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/seller/seller.jsp").forward(request, response);
	}

	protected void TopProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Product> lstProducts = productDAO.TopProSeller(user.getSeller().getSellId());
		if (lstProducts != null)
		{
			request.setAttribute("pro1", lstProducts.get(0));
			request.setAttribute("pro2", lstProducts.get(1));
			request.setAttribute("pro3", lstProducts.get(2));
		}

	}

	
	protected void TotalProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		Long totalPro = productDAO.getTotalProductSeller(user.getSeller().getSellId());
		request.setAttribute("totalPro", totalPro);
	}
	
	protected void TotalUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long count = this.userDAO.getTotalUser();
		request.setAttribute("totalUser", count);
	}
	
	protected void TotalOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long count = this.orderDAO.getTotalOrder();
		request.setAttribute("totalOrder", count);
	}
	
	protected void findall(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<History> histories = hisDAO.history(user.getEmail());
		request.setAttribute("lhistory", histories);
	}
}
