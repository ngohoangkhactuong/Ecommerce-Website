package vn.iotstar.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import vn.iotstar.dao.SellerDAO;
import vn.iotstar.dao.UserDAO;
import vn.iotstar.entity.Seller;
import vn.iotstar.entity.User;

@WebServlet({ "/my-account", "/my-account/update" })
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private SellerDAO sellerDAO;

	public AccountServlet() {

		super();
		this.userDAO = new UserDAO();
		this.sellerDAO = new SellerDAO();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/myaccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();

		if (uri.contains("update")) {
			this.update(request, response);
		}
		request.getRequestDispatcher("/views/myaccount.jsp").forward(request, response);
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		Seller seller = new Seller();
		HttpSession session = request.getSession();
		try {
			User oldEntity = (User) session.getAttribute("user");
			User newEntity = new User();
			BeanUtils.populate(newEntity, request.getParameterMap());
			newEntity.setStatus(1);
			newEntity.setPassword(oldEntity.getPassword());
			if (!(oldEntity.getEmail()).equals(email)) {
				User user = userDAO.findByEmail(newEntity.getEmail());
				if (user == null) {
					if (oldEntity.getSeller() != null) {
						seller = oldEntity.getSeller();
						seller.setName(request.getParameter("nameseller"));
						seller.setStatus(1);
						newEntity.setSeller(seller);
						sellerDAO.update(seller);
					}
					session.setAttribute("messageupdateSuccess", "Your account have been updated !");
					session.setAttribute("display", "show");
					this.userDAO.update(newEntity);
					response.sendRedirect("/ASM/admin/users/index");
				} else {
					session.setAttribute("messageupdateSuccess", "Email is existed!");
					session.setAttribute("display", "show");
					request.getRequestDispatcher("/views/myaccount.jsp").forward(request, response);
				}
			} else {
				if (oldEntity.getSeller() != null) {
					seller = oldEntity.getSeller();
					seller.setName(request.getParameter("nameseller"));
					seller.setStatus(1);
					newEntity.setSeller(seller);
					sellerDAO.update(seller);
				}
				session.setAttribute("messageupdateSuccess", "Your account have been updated !");
				session.setAttribute("display", "show");
				this.userDAO.update(newEntity);
				session.setAttribute("user", newEntity);
				request.getRequestDispatcher("/views/myaccount.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("messageupdateSuccess", "Your account doesnt have been updated !");
			session.setAttribute("display", "hide");
			request.getRequestDispatcher("/views/myaccount.jsp").forward(request, response);
		}
	}

}
