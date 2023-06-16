package vn.iotstar.controllers.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import vn.iotstar.dao.*;
import vn.iotstar.entity.*;

@WebServlet({"/admin/orders/index","/admin/orders/status" , "/admin/orders/search"})
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO; 
    private HistoryDAO historyDAO;
    public OrderServlet() {
        super();
        this.orderDAO = new OrderDAO();
		this.historyDAO = new HistoryDAO();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.contains("index")) {
			this.index(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		 if(uri.contains("status")) {
				this.status(request, response);
		 }
	}
	
	protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indexStr = request.getParameter("index");
		if(indexStr==null) {
			indexStr ="1";
		}
		int index = Integer.parseInt(indexStr);
		long count = this.orderDAO.getTotalOrder();
		long endPage = count/5;
		if(count%5!=0) {
			endPage++;
		}
		request.setAttribute("index", index);
		request.setAttribute("endPage", endPage);
		request.setAttribute("total", count);
		try {
			request.setAttribute("listOrder", this.orderDAO.findAll(index,5));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("viewAdmin","/views/admin/orders.jsp");
		request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
	}
	protected void status(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String idOrder = request.getParameter("id");
		HttpSession session = request.getSession();
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		try {
			int id = Integer.parseInt(idOrder);
			if(request.getParameter("act").equals("verify")) {
				Order oldEntity = this.orderDAO.findById(id);
				Order newEntity = new Order();
				BeanUtils.populate(newEntity, request.getParameterMap());
				newEntity.setOrderDate(formater.format(date));
				newEntity.setOrderStatus(1);
				newEntity.setUser(oldEntity.getUser());
				newEntity.setOrderDetails(oldEntity.getOrderDetails());
				newEntity.setShippingAddress(oldEntity.getShippingAddress());
				this.orderDAO.update(newEntity);
				
				History history = historyDAO.findById(newEntity.getId());
				history.setStatus(1);
				historyDAO.update(history);
				
				
				
			} 	
			if(request.getParameter("act").equals("unverify")) {
				Order oldEntity = this.orderDAO.findById(id);
				Order newEntity = new Order();
				BeanUtils.populate(newEntity, request.getParameterMap());
				newEntity.setOrderDate(formater.format(date));
				newEntity.setOrderStatus(0);
				newEntity.setUser(oldEntity.getUser());
				newEntity.setOrderDetails(oldEntity.getOrderDetails());
				newEntity.setShippingAddress(oldEntity.getShippingAddress());
				this.orderDAO.update(newEntity);
			}
			session.setAttribute("messageSuccess", "Your order has been updated !");
			session.setAttribute("display", "show");
			response.sendRedirect("/ASM/admin/orders/index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("messageSuccess", "Your order doesnt have been updated !");
			session.setAttribute("display", "hide");
			response.sendRedirect("/ASM/admin/orders/index");
		}
	}
	
	
}
