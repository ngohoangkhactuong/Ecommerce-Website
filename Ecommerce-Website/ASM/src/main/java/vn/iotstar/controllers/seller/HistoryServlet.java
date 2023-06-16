package vn.iotstar.controllers.seller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.*;
import vn.iotstar.entity.*;

@WebServlet(urlPatterns = { "/history/seller", "/history/seller/month", "/history/seller/detail"})
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HistoryDAO hisDAO = new HistoryDAO();
	HistoryDetailDAO hisDetailDAO = new HistoryDetailDAO();

	public HistoryServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String uri = request.getRequestURI();
			if (uri.contains("month")) {
			} else
				findall(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/seller/history.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			findallbymonth(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	protected void findall(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		int sellId = (int) (session.getAttribute("sellerid"));

		List<Integer> listHisID = hisDetailDAO.LOrderBySellerId(sellId);
		List<HistoryDetail> lHisDT = new ArrayList<HistoryDetail>();

		List<History> lHis = new ArrayList<History>();
		double totalsale = 0;

		for (int i: listHisID)
		{
			History his = new History();
			his =hisDAO.findById(i); 
			if (his.getStatus() ==1)
				lHis.add(his);
		}
		for (History his: lHis ) {
			for(HistoryDetail hisdt: his.getHistoryDetails())
			{
				if (hisdt.getSellid() == sellId) {
					totalsale=totalsale + hisdt.getQuantity()*hisdt.getPrice();
				}

			}
		}
		
		request.setAttribute("lhistory",lHis);
		request.setAttribute("totalSale", totalsale);

	}

	protected void findallbymonth(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		int sellId = (int) (session.getAttribute("sellerid"));
		String month = request.getParameter("month");

		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM");
		Date date = formater.parse(month);

		SimpleDateFormat formater1 = new SimpleDateFormat("MM-yyyy");
		String m = formater1.format(date);
		
		List<Integer> listHisID = hisDetailDAO.LOrderBySellerId(sellId);
		List<History> lhis = new ArrayList<History>();


		for (int i: listHisID)
		{
			History his = new History();
			 if (hisDAO.findbymonth(m,i).size() !=0) {
				 his = hisDAO.findbymonth(m,i).get(0);
					lhis.add(his);

			 }
		}
		
		double totalsale = 0;
		for (History his: lhis ) {
			for(HistoryDetail hisdt: his.getHistoryDetails())
			{
				if (hisdt.getSellid() == sellId) {
					totalsale=totalsale + hisdt.getQuantity()*hisdt.getPrice();
				}

			}
		}
		request.setAttribute("lhistory", lhis);
		
		request.setAttribute("totalSale", totalsale);


		request.getRequestDispatcher("/views/seller/history.jsp").forward(request, response);

	}
}
