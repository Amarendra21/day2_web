package pages_cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookShopDaoImpl;
import pojos.Customer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set cont type
		response.setContentType("text/html");
		// pw -- buffered char o/p strm connected from
		// server---> clnt , to send resp
		try (PrintWriter pw = response.getWriter()) {
			pw.print("from category page....<br>");
		/*	String email = request.getParameter("email");
			String pass123 = request.getParameter("pass");
			pw.print("email "+email);
	*/		
			//get cookies from request hdr
			Cookie[] cookies=request.getCookies();
			if(cookies != null)
			{
				//session tracking works
				for(Cookie c : cookies)
					if(c.getName().equals("cust_dtls"))
						pw.print("Validated custmer details "+c.getValue());
			} else
				pw.print("No Cookies : Session Tracking fails....");
			//add logout link
			pw.print("<h4><a href=logout>Log Me Out</a></h4>");
		
		} catch (Exception e) {
			throw new ServletException("err in do-get", e);
		}

	}

}
