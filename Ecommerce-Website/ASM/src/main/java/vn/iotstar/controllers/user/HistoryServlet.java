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

import vn.iotstar.dao.*;
import vn.iotstar.entity.*;

@WebServlet(urlPatterns = { "/history", "/history/detail" })
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HistoryDAO hisDAO = new HistoryDAO();

	public HistoryServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
				findall(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/history.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void findall(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<History> histories = hisDAO.history(user.getEmail());
		request.setAttribute("lhistory", histories);
	}
}
