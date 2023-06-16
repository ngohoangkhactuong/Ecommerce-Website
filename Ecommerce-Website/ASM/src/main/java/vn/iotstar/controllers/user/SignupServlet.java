package vn.iotstar.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.mindrot.jbcrypt.BCrypt;

import vn.iotstar.dao.SellerDAO;
import vn.iotstar.dao.UserDAO;
import vn.iotstar.entity.Seller;
import vn.iotstar.entity.User;

@WebServlet({ "/signup/index", "/signup/create", "/signup/seller/index", "/signup/seller/create" })
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO DAO;
	private SellerDAO sellerDAO;
	private boolean isSignup;
	HttpSession session = null;

	public SignupServlet() {
		super();
		this.DAO = new UserDAO();
		this.sellerDAO = new SellerDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("seller/index")) {
			request.getRequestDispatcher("/views/signupseller.jsp").forward(request, response);
		} else if (uri.contains("index")) {
			this.index(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("seller/create")) {
			this.createseller(request, response);

		} else if (uri.contains("create")) {
			this.create(request, response);
		}
	}

	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			session = request.getSession();
//			request.setAttribute("listUser", DAO.findAll());
//			if (isSignup) {
//				request.setAttribute("message", "Success !");
//				request.setAttribute("color", "success");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/signup.jsp").forward(request, response);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		session = request.getSession();
		try {
			String email = request.getParameter("email");
			User user = DAO.findByEmail(email);
			if (user == null) {
				User entity = new User();
				BeanUtils.populate(entity, request.getParameterMap());
				entity.setPassword(request.getParameter("password"));
				entity.setRole(0);
				this.DAO.create(entity);
				isSignup = true;

				response.sendRedirect("/ASM/login");
				session.setAttribute("message", "Success !");
				session.setAttribute("color", "success");
			} else {
				session.setAttribute("message", "Email is existed !");
				session.setAttribute("color", "danger");
				response.sendRedirect("/ASM/signup/seller/index");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createseller(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		session = request.getSession();

		try {
			String email = request.getParameter("email");
			User user = DAO.findByEmail(email);
			if (user == null) {

				Seller seller = new Seller();
				seller.setName(request.getParameter("name"));
				seller.setStatus(1);
				sellerDAO.create(seller);

				User entity = new User();
				BeanUtils.populate(entity, request.getParameterMap());
				entity.setPassword(request.getParameter("password"));
				entity.setRole(0);
				entity.setSeller(seller);
				isSignup = true;

				this.DAO.create(entity);
				response.sendRedirect("/ASM/login");
				session.setAttribute("message", "Success !");
				session.setAttribute("color", "success");
				response.sendRedirect("/ASM/signup/index");
			} else {
				session.setAttribute("message", "Email is existed !");
				session.setAttribute("color", "danger");
				response.sendRedirect("/ASM/signup/seller/index");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
