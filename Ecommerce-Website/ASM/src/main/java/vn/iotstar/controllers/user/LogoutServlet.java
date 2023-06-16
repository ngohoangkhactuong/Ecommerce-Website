package vn.iotstar.controllers.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.OrderDAO;
import vn.iotstar.dao.OrderDetailDAO;
import vn.iotstar.dao.UserDAO;
import vn.iotstar.entity.Order;
import vn.iotstar.entity.OrderDetail;
import vn.iotstar.entity.User;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDAO = new UserDAO();
	OrderDAO orderDAO = new OrderDAO();
	OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		try {
			if (order.getOrderStatus() == 3) {
				User user = (User) session.getAttribute("user");
				order.setUser(user);
				orderDAO.create(order);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		session.removeAttribute("user");
		session.removeAttribute("sellerId");
		session.removeAttribute("message");
		session.removeAttribute("color");
		session.removeAttribute("order");
		session.setAttribute("avatarLg",
				"https://www.caribbeangamezone.com/wp-content/uploads/2018/03/avatar-placeholder.png");
		response.sendRedirect("/ASM/home?index=1&ctid=0");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
