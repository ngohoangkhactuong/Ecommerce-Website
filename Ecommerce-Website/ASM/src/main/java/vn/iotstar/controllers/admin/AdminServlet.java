package vn.iotstar.controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.iotstar.dao.ProductDAO;
import vn.iotstar.entity.Product;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;

    public AdminServlet() {
        super();
        this.productDAO = new ProductDAO();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("viewAdmin","/views/admin/dashboard.jsp");
		request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
	}
	
	protected void TopProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> lstProducts = productDAO.TopPro();
		request.setAttribute("pro1", lstProducts.get(0));
		request.setAttribute("pro2", lstProducts.get(1));
		request.setAttribute("pro3", lstProducts.get(2));
	}
}
