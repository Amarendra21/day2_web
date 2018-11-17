package pages_session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookShopDaoImpl;
import pojos.Customer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/sess_logout")
public class LogOutServlet extends HttpServlet {
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
			pw.print("from logout page....<br>");
			//HS
			HttpSession hs=request.getSession();
			System.out.println("from logout page "+hs.isNew());
			System.out.println("sess id "+hs.getId());
			Customer c=(Customer)hs.getAttribute("customer_dtls");
			if(c != null)
				pw.print("Hello ,"+c.getName()+"<br>");
			else
				pw.print("Session tracking failed.....");
	
			pw.print("<br>You have logged out successfully...");
			//discard HS
			hs.invalidate();
			// add a link to index page
			pw.print("<h4><a href=index.html>Visit Again</a></h4>");

		} catch (Exception e) {
			throw new ServletException("err in do-get", e);
		}

	}

}
