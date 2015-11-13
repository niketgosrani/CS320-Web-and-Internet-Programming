package lab2;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.runtime.Context;


@WebServlet("/lab2/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		boolean error = false;
		boolean success = false;
		ServletContext context = this.getServletContext();
		context.setAttribute("error", error);
		context.setAttribute("success", success);

		if (context.getAttribute("Homework1Users") == null) {
			ArrayList<Homework1Users> users = new ArrayList<Homework1Users>();
			users.add(new Homework1Users("Niket", "Gosrani", "ng@gmail.com",
					"abcd", null, null));
			users.add(new Homework1Users("Albert", "Cervantes", "a",
					"a1!", null, null));
			users.add(new Homework1Users("Krina", "Shah", "ks@gmail.com",
					"ijkl", null, null));
			getServletContext().setAttribute("Homework1Users", users);
		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean success = false;
		request.getServletContext().setAttribute("success", success);
		/*
		 * Cookie[] c= request.getCookies(); for(Cookie cobj:c) {
		 * if(cobj.getName().equals("lab2hash")) { System.out.println("found");
		 * break; } }
		 */
		//HttpSession session = request.getSession();
		response.setContentType("text/html");

		// Write HTML
		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>LoginServlet</title>");
		out.println("</head>");
		out.println("<body bgcolor=\"black\">");
		out.println("<font color=\"white\">");
		out.println("	<center>");
		out.println("	<form action=\"LoginServlet\" method=\"post\">");
		out.println("	<fieldset><legend><b><font face=\"Courier New\" size=\"+2\"color=\"blue\">Login</b></font></legend>");
		out.print("<br> </br>");
		boolean error = (boolean) request.getServletContext().getAttribute("error");
		if (error) 
		{
			out.println("<font color='red'>Invalid Username and/or Password</font>");
		}
		out.print("<br> </br>");
		out.println("Email Id &nbsp;&nbsp; :");
		out.print("<input type=\"text\" name=\"emailid\" ");
		out.print("<br> </br>");
		out.print("<br> </br>");
		out.println("Password &nbsp;&nbsp; :");
		out.print("<input type=\"password\" name=\"password\" ");
		out.print("<br> </br>");
		out.print("<br> </br>");
		out.print("<input type=\"checkbox\" name=\"rememberMe\" value=\"Remember Me\">Remember Me");
		out.print("<br> </br>");
		out.print("<input type=\"submit\" value=\"Login\" >");
		out.println("	</fieldset>");
		out.println("	</form>");
		out.println("	</center>");
		out.println("	</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Homework1Users> users = (ArrayList<Homework1Users>) this.getServletContext().getAttribute("Homework1Users");
		String firstname;

		String email = request.getParameter("emailid");
		String password = request.getParameter("password");
		boolean rememberMe = request.getParameter("rememberMe") != null;
		byte[] mdbytes;
		for (Homework1Users user : users) {
			if (email != null && email.equals(user.getEmail())
					&& password != null && password.equals(user.getPassword())) 
			{
				if (rememberMe) {
					MessageDigest md;
					try {
						firstname = user.getFirstname();
						md = MessageDigest.getInstance("SHA-256");

						md.update(firstname.getBytes());
						// md.digest will convert into byte array
						mdbytes = md.digest();

						// String hashvalue = new String(mdbytes);
						StringBuffer hexString = new StringBuffer();
						for (int i = 0; i < mdbytes.length; i++) 
						{
							hexString.append(Integer
									.toHexString(0xFF & mdbytes[i]));
						}

						Cookie cookie = new Cookie("lab2hash",hexString.toString());
						cookie.setMaxAge(14 * 24 * 60 * 60);
						//cookie.setMaxAge(0); Test to check cookie delete
						response.addCookie(cookie);

					} catch (NoSuchAlgorithmException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				session.setAttribute("Lab2User", user);
				response.sendRedirect("Welcome");
				boolean success = true;
				request.getServletContext().setAttribute("success", success);
				boolean error = false;
				request.getServletContext().setAttribute("error", error);
				break;
			}

		}// end of for loop
		if (!(boolean) request.getServletContext().getAttribute("success")) {
			boolean error = true;
			request.getServletContext().setAttribute("error", error);
			boolean success = false;
			request.getServletContext().setAttribute("success", success);
			response.sendRedirect("LoginServlet");
		}
	}
}
