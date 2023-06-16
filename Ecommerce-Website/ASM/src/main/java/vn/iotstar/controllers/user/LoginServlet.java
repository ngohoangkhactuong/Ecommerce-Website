package vn.iotstar.controllers.user;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.OrderDAO;
import vn.iotstar.dao.UserDAO;
import vn.iotstar.entity.Order;
import vn.iotstar.entity.User;
import vn.iotstar.utils.EncryptUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	OrderDAO orderDAO = new OrderDAO();
	HttpSession session =null;

	public LoginServlet() {
		super();
		this.userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("emailLogin");
		String password = request.getParameter("passwordLogin");
		String remember = request.getParameter("remember");
		HttpSession session = request.getSession();
		String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"; // check regex email
		boolean matchFound = Pattern.matches(regex, email);
		if (!matchFound) {
			session.setAttribute("isEmail", "Nhập sai định dạng email");
			response.sendRedirect("/ASM/login");
			return;
		}
		try {
			User user = this.userDAO.findByEmail(email);
			if (user.getStatus()==0)
			{
				session.setAttribute("messageLg", "Tài khoản đã bị khoá!");
				response.sendRedirect("/ASM/login");
				return;
			}
			else {
				if ((user != null) & (password.equals(user.getPassword()))  ) {
					
					session.setAttribute("user", user);
		
					if (orderDAO.FindByUserID(user.getId()).size() > 0) {
						Order ord = orderDAO.FindByUserID(user.getId()).get(0);
						session.setAttribute("order", ord);

					}
					if (user.getSeller() != null) {
						int sellId =user.getSeller().getSellId();
						session.setAttribute("sellerid", sellId);
						response.sendRedirect("/ASM/home/seller?index=1&ctid=0");
					}
					else {
						response.sendRedirect("/ASM/home?index=1&ctid=0");

					}
				} else {
					session = request.getSession();
					session.setAttribute("messageLg", "Sai tên đăng nhập hoặc mật khẩu !");
					response.sendRedirect("/ASM/login");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
//			session = request.getSession();
			session.setAttribute("messageLg", "Sai tên đăng nhập hoặc mật khẩu !");
			response.sendRedirect("/ASM/login");
		}

	}

}
