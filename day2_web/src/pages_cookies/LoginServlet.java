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
@WebServlet(urlPatterns="/validate",loadOnStartup=1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookShopDaoImpl dao;

	public void init() throws ServletException {
		// create DAO instance
		try {
			dao = new BookShopDaoImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException("err in init", e);
		}

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// clean up dao
		if (dao != null)
			try {
				dao.cleanUp();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("err in destroy", e);
			}
	}

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
			// read em n pasword sent from the clnt
			String email = request.getParameter("email");
			String pass123 = request.getParameter("pass");
			// invoke DAO's CRUD method
			Customer cust = dao.validateCustomer(email, pass123);
			if(cust == null)
			{
				//invalid login
				pw.print("Invalid Login,  Pls<a href=login.html>Retry</a>");
			}
			else {
			/*	//valid login --print user details 
				pw.print("Login Successful<br>");
				pw.print(cust);*/
				//redirect the clnt to next page in the NEXT request
				pw.print("from login page....");
				//un comment next line to check IllegalStateExc
			//	pw.flush();
				//create a cookie to hold customer details fetched from DB
				Cookie c1=new Cookie("cust_dtls",cust.toString());
				//add cookie to resp hdr
				response.addCookie(c1);
				//page navigation --clnt pull
				response.sendRedirect("category");
			}
		} catch (Exception e) {
			throw new ServletException("err in do-get", e);
		}

	}

}
