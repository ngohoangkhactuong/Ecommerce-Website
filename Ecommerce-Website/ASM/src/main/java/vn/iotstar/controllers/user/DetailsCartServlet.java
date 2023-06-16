package vn.iotstar.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.ProductDAO;
import vn.iotstar.entity.Product;

@WebServlet("/cart")
public class DetailsCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;   
    public DetailsCartServlet() {
        super();
        this.productDAO = new ProductDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int productId = Integer.parseInt(idStr);
		HttpSession session = request.getSession();
		Product pro =  productDAO.findById(productId);
		session.setAttribute("product",pro);
		session.setAttribute("sellerid",pro.getSeller().getSellId() );
		request.getRequestDispatcher("/views/detailsCart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/detailsCart.jsp").forward(request, response);
	}

}
