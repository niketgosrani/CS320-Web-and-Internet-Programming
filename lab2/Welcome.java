package lab2;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/lab2/Welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Welcome() {
		super();

	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String hashName = null;
		ArrayList<Homework1Users> users = (ArrayList<Homework1Users>) this
				.getServletContext().getAttribute("Homework1Users");
		HttpSession session = request.getSession();
		boolean isCookiePresent = false;
		byte[] mdbytes;
		Homework1Users user = null;

		if (session.getAttribute("Lab2User") != null) 
		{
			user = (Homework1Users) session.getAttribute("Lab2User");
			PrintWriter out = response.getWriter();
			out.println("<!doctype html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Welcome</title>");
			out.println("</head>");
			out.println("<body bgcolor=\"black\">");
			out.println("<font color=\"white\">");
			out.println("	<center>");
			out.println("	<form action=\"Welcome\" method=\"post\">");
			out.println("	<fieldset><legend><b><font face=\"Courier New\" size=\"+2\"color=\"blue\">Welcome Page</b></font></legend>");
			out.println("<br> </br>");

			out.println("Welcome " + user.getFirstname());
			out.println("<p><a href='Logout?'>Logout</a></p>");
			out.println("	</fieldset>");
			out.println("	</form>");
			out.println("	</center>");
			out.println("	</body>");
			out.println("</html>");
		} else if (session.getAttribute("Lab2User") == null) {
			// cookie logic
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("lab2hash")) {
						hashName = cookie.getValue();
						break;
					}

				}

				if (hashName != null) {
					// for loop on all users
					for (Homework1Users usr : users) {
						MessageDigest md;
						try {
							String firstname = usr.getFirstname();
							md = MessageDigest.getInstance("SHA-256");

							md.update(firstname.getBytes());
							// md.digest will convert into byte array
							mdbytes = md.digest();

							// String hashvalue = new String(mdbytes);
							StringBuffer hexString = new StringBuffer();
							for (int i = 0; i < mdbytes.length; i++) {
								hexString.append(Integer
										.toHexString(0xFF & mdbytes[i]));
							}

							if (hexString.toString().equals(hashName)) {
								isCookiePresent = true;
								session.setAttribute("Lab2User", usr);
								response.sendRedirect("Welcome");
								break;
								// user data is not in session but its in
								// cookie.

							}

						} catch (NoSuchAlgorithmException e) {

							e.printStackTrace();
						}
					}

					if (!isCookiePresent) {
						// cookie is not present
						response.sendRedirect("LoginServlet");
						System.out.println("Cookie present");
						return;
					}

					else if (hashName != null && isCookiePresent) {
						Cookie cookie = new Cookie("lab2hash", "Cookie deleted");
						cookie.setMaxAge(0);
						response.addCookie(cookie);
						System.out.println("Cookie Deleted");

					}

				}

			} else {
				if (!isCookiePresent) {
					// cookie is not present
					response.sendRedirect("LoginServlet");
					System.out.println("Cookie present");
					return;
				}

			}

		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		// TODO Auto-generated method stub
	}

}