package onlineQuiz;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/onlineQuiz/SaveResponses")
public class SaveResponses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int counter = 0;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		counter = 0;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		// int currCount = Integer.parseInt(request.getParameter("count"));
		int qid = Integer.parseInt(request.getParameter("qid"));
		// int answer = Integer.parseInt(request.getParameter("answer"));
		String answer = request.getParameter("answer");

		if (answer.equals("TRUE")) {
			System.out.println("Correct Answer for Qid" + qid);
			this.counter++;
		}

		session.setAttribute("score", counter);
	}

	public boolean isValidAnswer(int qid, String answer) {

		boolean iscorrect = false;

		String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs320stu154";
		String username = "cs320stu154";
		String password = "JqHa!#eC";

		try {
			Connection c = DriverManager.getConnection(url, username, password);

			String query = "Select answer from questionPg WHERE ID=?";
			PreparedStatement pstmt = c.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String correctAnswer = rs.getString("answer");

				if (correctAnswer.equalsIgnoreCase(answer))
					iscorrect = true;
			}
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return iscorrect;
	}

}
