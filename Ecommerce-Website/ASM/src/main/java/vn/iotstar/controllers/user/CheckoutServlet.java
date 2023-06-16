package vn.iotstar.controllers.user;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.*;
import vn.iotstar.entity.*;

@WebServlet({ "/checkout/index", "/checkout/payment" })
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO;
	private UserDAO userDAO;
	private ProductDAO proDAO;
	private HistoryDAO historyDAO;
	private HistoryDetailDAO historyDetailDAO;

	private List<OrderDetail> listDetails;

	public CheckoutServlet() {
		super();
		this.userDAO = new UserDAO();
		this.orderDAO = new OrderDAO();
		this.proDAO = new ProductDAO();
		this.historyDAO = new HistoryDAO();
		this.historyDetailDAO = new HistoryDetailDAO();

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
		if (uri.contains("payment")) {
			this.payment(request, response);
		}
	}

	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		session.setAttribute("ttp", request.getParameter("totalp"));
		request.getRequestDispatcher("/views/checkout.jsp").forward(request, response);
	}

	protected void payment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			HttpSession session = request.getSession();
			String address = request.getParameter("address");
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			User userId = (User) session.getAttribute("user");
			Order order = new Order();

			if (userId != null) {
				order = (Order) session.getAttribute("order");
				User user = this.userDAO.findById(userId.getId());
				int amount = 0;

				order.setUser(user);
				order.setOrderStatus(0);
				order.setShippingAddress(address);
				order.setOrderDate(formater.format(date));
				if (orderDAO.findById(order.getId()) == null) {
					this.orderDAO.create(order);
				} else {
					this.orderDAO.update(order);
				}
				listDetails = order.getOrderDetails();
				for (OrderDetail od : listDetails) {
					Product pro = od.getProduct();
					int stock = pro.getStock();
					amount = pro.getAmount();
					int quantity = od.getQuantity();
					stock = stock - quantity;
					amount = amount + quantity;
					pro.setAmount(amount);
					pro.setStock(stock);
					proDAO.update(pro);

				}
				

				History hist = new History();
				List<HistoryDetail> lhistD = new ArrayList<HistoryDetail>();
				hist.setOrderDate(order.getOrderDate());
				hist.setShippingAddress(order.getShippingAddress());
				hist.setPrice(Double.parseDouble(session.getAttribute("ttp").toString()));
				hist.setUser_email(user.getEmail());
				hist.setStatus(0);
				for (OrderDetail od : order.getOrderDetails()) {
					HistoryDetail histD = new HistoryDetail();
					histD.setImage(od.getProduct().getImage());
					histD.setName(od.getProduct().getName());
					histD.setQuantity(od.getQuantity());
					histD.setPrice(od.getProduct().getPrice());
					histD.setSellid(od.getProduct().getSeller().getSellId());
					histD.setHistory(hist);
					
					
					lhistD.add(histD);
					
				}
				
				hist.setHistoryDetails(lhistD);
				historyDAO.create(hist);
				
				
				
				listDetails.clear();
				session.setAttribute("messageSuccess", "Your orded to this cart successful !");
				session.setAttribute("display", "show");
				response.sendRedirect("/ASM/detail/index");
				session.removeAttribute("order"); 
				session.removeAttribute("ttp"); 



			} else {
				response.sendRedirect("/ASM/login");
				session.setAttribute("messageSuccess", "Your orded to this cart faild !");
				session.setAttribute("display", "show");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
