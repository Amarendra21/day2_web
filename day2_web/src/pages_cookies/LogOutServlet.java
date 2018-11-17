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
@WebServlet("/logout")
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
			/*
			 * String email = request.getParameter("email"); String pass123 =
			 * request.getParameter("pass"); pw.print("email "+email);
			 */
			// get cookies from request hdr
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				// session tracking works
				for (Cookie c : cookies)
					if (c.getName().equals("cust_dtls")) {
						c.setMaxAge(0);
						response.addCookie(c);
						pw.print("Custmer details : " + c.getValue());
					}
			} else
				pw.print("No Cookies : Session Tracking fails....");
			pw.print("<br>You have logged out successfully...");
			// add a link to index page
			pw.print("<h4><a href=index.html>Visit Again</a></h4>");

		} catch (Exception e) {
			throw new ServletException("err in do-get", e);
		}

	}

}
