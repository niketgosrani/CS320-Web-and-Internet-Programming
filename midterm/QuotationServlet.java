package midterm;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/midterm/QuotationServlet")
public class QuotationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
            out.println("<html><head><title>QuotationServlet</title></head><body><form name=\"generate\" method=\"get\" action=\"QuotationServlet\">");
            out.println("<h1>test Quote : " + getRandomQuote(request).getQuote()+"</h1>");
            out.println("Author : " + getRandomQuote(request).getAuthor());
            out.println("<input type=\"submit\" value=\"next\"/>");
            out.println("<a href=\"QuotationAdminServlet\">Admin Servlet</a>");
            
            out.println("</form></body></html>");
         
            out.close();
        

    }

    private Quotation getRandomQuote(HttpServletRequest request) {
        ArrayList<Quotation> quotesList = getQuotationList(request);
        int randomId;
        if (quotesList != null) {
            randomId = (int) (0 + Math.random() * quotesList.size());
            System.out.println(quotesList.get(randomId).getAuthor());
            return quotesList.get(randomId);
        }
        return null;
    }

    private ArrayList<Quotation> getQuotationList(HttpServletRequest request) {
        FileInputStream filein;
        ObjectInputStream objin;

        try {
        	ServletContext context = this.getServletContext();
    		String filename=context.getRealPath("quotesList.ser");
            filein = new FileInputStream(filename);
            objin = new ObjectInputStream(filein);
            ArrayList<Quotation> myList = (ArrayList<Quotation>) objin.readObject();
            objin.close();
            filein.close();
            return myList;

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doGet(request,response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}