package vn.iotstar.controllers.admin;

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

@WebServlet("/admin/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private CategoryDAO categoryDAO;
	private UserDAO userDAO;
	private OrderDAO orderDAO;
	HistoryDAO hisDAO = new HistoryDAO();


	public DashboardServlet() {
		super();
		this.productDAO = new ProductDAO();
		this.categoryDAO = new CategoryDAO();
		this.userDAO = new UserDAO();
		this.orderDAO = new OrderDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.TopProduct(request, response);
		this.TotalCategory(request, response);
		this.TotalProduct(request, response);
		this.TotalOrder(request, response);
		this.TotalUser(request, response);
		try {
			findall(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		request.setAttribute("viewAdmin", "/views/admin/dashboard.jsp");
		request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
	}

	protected void TopProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> lstProducts = productDAO.TopPro();
		request.setAttribute("pro1", lstProducts.get(0));
		request.setAttribute("pro2", lstProducts.get(1));
		request.setAttribute("pro3", lstProducts.get(2));
	}

	protected void TotalCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long topCate = categoryDAO.getTotalCategory();
		request.setAttribute("totalCate", topCate);
	}
	
	protected void TotalProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long totalPro = productDAO.getTotalProduct();
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
