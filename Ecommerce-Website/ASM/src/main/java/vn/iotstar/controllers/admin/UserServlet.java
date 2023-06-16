package vn.iotstar.controllers.admin;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import vn.iotstar.dao.*;
import vn.iotstar.entity.*;
import vn.iotstar.utils.*;

@WebServlet({ "/admin/users/index", "/admin/users/create", "/admin/users/createseller", "/admin/users/update",
		"/admin/users/delete", "/admin/users/search", "/admin/users/status" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private SellerDAO sellerDAO;

	public UserServlet() {
		super();
		this.userDAO = new UserDAO();
		this.sellerDAO = new SellerDAO();
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
		if (uri.contains("seller")) {
			this.createseller(request, response);
		} else if (uri.contains("create")) {
			this.create(request, response);
		} else if (uri.contains("search")) {
			this.search(request, response);
		} else if (uri.contains("status")) {
			this.status(request, response);
		} 
		else {
			request.getRequestDispatcher("/views/404.jsp").forward(request, response);
		}
	}

	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String indexStr = request.getParameter("index");
		if (indexStr == null) {
			indexStr = "1";
		}
		try {
			int index = Integer.parseInt(indexStr);
			long count = this.userDAO.getTotalUser();
			long endPage = count / 5;
			if (count % 5 != 0) {
				endPage++;
			}
			request.setAttribute("index", index);
			request.setAttribute("endPage", endPage);
			request.setAttribute("total", count);
			request.setAttribute("listPagination", this.userDAO.pagination(index, 5));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("viewAdmin", "/views/admin/user.jsp");
		request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		// Start check email
		String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"; // check regex email
		boolean matchFound = Pattern.matches(regex, request.getParameter("email"));
		if (!matchFound) {
			session.setAttribute("isEmail", "Nhập sai định dạng email");
			response.sendRedirect("/ASM/login");
			return;
		}
		try {
			String email = request.getParameter("email");
			User user = userDAO.findByEmail(email);
			if (user == null) {
				User entity = new User();
				BeanUtils.populate(entity, request.getParameterMap());
				entity.setPassword(request.getParameter("password"));
				entity.setStatus(1);
				session.setAttribute("messageupdateSuccess", "Your account have been created !");
				session.setAttribute("display", "show");
				this.userDAO.create(entity);
			} else {
				session.setAttribute("messageupdateSuccess", "Email is existed!");
				session.setAttribute("display", "show");
			}
			response.sendRedirect("/ASM/admin/users/index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("messageupdateSuccess", "Your account doesnt have been created !");
			session.setAttribute("display", "show");
			response.sendRedirect("/ASM/admin/users/index");
		}
	}

	private void createseller(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		// Start check email
		String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"; // check regex email
		boolean matchFound = Pattern.matches(regex, request.getParameter("email"));
		if (!matchFound) {
			session.setAttribute("isEmail", "Nhập sai định dạng email");
			response.sendRedirect("/ASM/login");
			return;
		}
		try {
			String email = request.getParameter("email");
			User user = userDAO.findByEmail(email);
			if (user == null) {
				Seller seller = new Seller();

				seller.setName(request.getParameter("nameseller"));
				seller.setStatus(1);
				sellerDAO.create(seller);

				User entity = new User();
				BeanUtils.populate(entity, request.getParameterMap());
				entity.setPassword(request.getParameter("password"));
				entity.setRole(0);
				entity.setSeller(seller);
				entity.setStatus(1);
				session.setAttribute("messageupdateSuccess", "Your account has been created !");
				session.setAttribute("display", "show");
				this.userDAO.create(entity);
				response.sendRedirect("/ASM/admin/users/index");
			} else {
				session.setAttribute("messageupdateSuccess", "Email is existed!");
				session.setAttribute("display", "show");
				response.sendRedirect("/ASM/admin/users/index");

			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("messageupdateSuccess", "Your account doesnt have been created !");
			session.setAttribute("display", "show");
			response.sendRedirect("/ASM/admin/users/index");
		}
	}


	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indexStr = request.getParameter("index");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String proname = request.getParameter("username");

		if (indexStr == null) {
			indexStr = "1";
		}
		try {
			int index = Integer.parseInt(indexStr);
			long count = this.userDAO.getTotalUser();
			long endPage = count / 5;
			if (count % 5 != 0) {
				endPage++;
			}

			request.setAttribute("index", index);
			request.setAttribute("endPage", endPage);
			request.setAttribute("total", count);
			request.setAttribute("listPagination", this.userDAO.searchUserByName(index, 5, proname));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/admin/user.jsp").forward(request, response);
	}
	public void status(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("id");
		String act = request.getParameter("act");
		
		HttpSession session = request.getSession();
		
		User user = userDAO.findById(Integer.parseInt(userId));
		
		try {
			if (act.equals("unblock"))
			{
				user.setStatus(1);
				userDAO.update(user);
				
				
			}else if (act.equals("block"))
			{
				user.setStatus(0);
				userDAO.update(user);
				
			}
		} catch (Exception e) {
		}
		response.sendRedirect("/ASM/admin/users/index");

	}
}
