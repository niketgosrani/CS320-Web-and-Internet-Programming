package midterm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/midterm/QuotationAdminServlet")
public class QuotationAdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public QuotationAdminServlet() {
        super();
    }

    public void init() throws ServletException {

        ServletContext context = this.getServletContext();
        if (context.getAttribute("quotationList") == null) {
            ArrayList<Quotation> quotesList = new ArrayList<Quotation>();

            quotesList.add(new Quotation("You have to learn rules of the game. And then  you have to play better than anyone else.", "Albert Einstein"));
            quotesList.add(new Quotation("where there is love there is life", "Mahatma Gandhi"));
            quotesList.add(new Quotation("the butterfly counts not months but moments, and thus have enough time", "Rabindranth Tagore"));
            quotesList.add(new Quotation("Design is not just how it looks like and feels like. Design is how it works", "Steve jobs"));

            context.setAttribute("quotationList", quotesList);
            addQuotationToFile(quotesList);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // Write HTML
        out.println("<!doctype html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>CS320 Midterm – Quotation Generator</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        out.println("<font color=\"black\">");
        out.println("	<center>");

        try
        {
        String action = request.getParameter("action");
        if(action != null && action.trim().length() > 0)
        {
            // Add quotation if action = add        
            if(action.equals("add"))
            {
                ArrayList<Quotation> quotesList = getQuotationList(request);
                String quotation = request.getParameter("quotation");
                String author = request.getParameter("author");

                if (quotation != null && author != null)
                {
                    if (quotation.equals("") || author.equals(""))
                    {
                        out.println("<font color='red'>Quotation or Author Blank</font>");
                    }
                    else
                    {
                        quotesList.add(new Quotation(quotation, author));
                        setQuotationList(request, quotesList);
                        addQuotationToFile(quotesList);
                    }
                }
            }
            // Else if remove quotation if action = remove
            else if(action.equals("remove"))
            {
                String publicId = request.getParameter("publicId");
                if (publicId != null) 
                {
                    int id = Integer.parseInt(publicId);
                    ArrayList<Quotation> quotesList = getQuotationList(request);
                    for (int i = 0; i < quotesList.size(); i++) 
                    {
                        if (id == quotesList.get(i).getPublicId()) 
                        {
                            quotesList.remove(i);
                            setQuotationList(request, quotesList);
                            addQuotationToFile(quotesList);
                        }
                    }
                }
                
            }
        }
        
        out.println("	<form action=\"QuotationAdminServlet\" method=\"post\">");
        out.println("	<fieldset><legend><b><font face=\"Courier New\" size=\"+2\"color=\"blue\">Quotes Display</b></font></legend>");
        out.print("<br> </br>");
        out.print("<br> </br>");
        out.println("<table border=2>");
        out.println("<tr>");
        out.println("<th>Actual Quote</th>");
        out.println("<th>Author</th>");
        out.println("<th>Remove</th>");
        out.println("</tr>");

        ArrayList<Quotation> quotesList = getQuotationList(request);
        for (int i = 0; i < quotesList.size(); i++)
        {
            if(quotesList.get(i).getQuote() != null && quotesList.get(i).getAuthor() != null)
            {
                out.println("<tr><td>" + quotesList.get(i).getQuote() + "</td>");
                out.println("<td>" + quotesList.get(i).getAuthor() + "</td> ");
                out.println("<td> <a href=QuotationAdminServlet?action=remove&publicId=" + quotesList.get(i).getPublicId() + "> Remove</a> </td> ");
                out.println("</tr>");
            }

        }
        out.println("</table>");
        out.print("<br> </br>");
        out.print("<br> </br>");
        out.println("</form>");
        out.println("<form action=\"QuotationAdminServlet?action=add\" method=\"post\">");
        out.println(" Quotation:<input type=\"text\" name=\"quotation\"  />");
        out.print("<br> </br>");
        out.println(" Author:<input type=\"text\"  name=\"author\"/>");
        out.print("<br> </br>");
        out.println("<input type=\"submit\"   value=\"submit\" />");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        }
        finally
        {
            out.close();
        }
    }

    private void addQuotationToFile(ArrayList<Quotation> quotesList)
    {
	FileOutputStream fileout = null;
        ObjectOutputStream objout = null;
	try {
		ServletContext context = this.getServletContext();
		String filename=context.getRealPath("quotesList.ser");
		fileout = new FileOutputStream(filename, false);
                objout = new ObjectOutputStream(fileout);
                objout.writeObject(quotesList);
                objout.close();
                fileout.close();	
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }

    }

    private ArrayList<Quotation> getQuotationList(HttpServletRequest request)
    {
        return getQuotationObject(request);
    }

     @SuppressWarnings("unchecked")
	private ArrayList<Quotation> getQuotationObject(HttpServletRequest request)
    {
        FileInputStream filein;
        ObjectInputStream objin;

        try
        {
        	ServletContext context = this.getServletContext();
    		String filename=context.getRealPath("quotesList.ser");
            filein = new FileInputStream(filename);
            objin = new ObjectInputStream(filein);
            ArrayList<Quotation> myList = (ArrayList<Quotation>) objin.readObject();
            objin.close();
            filein.close();
            return myList;

        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }

        return null;
    }


    private void setQuotationList(HttpServletRequest request, ArrayList<Quotation> quotesList)
    {
        request.getServletContext().setAttribute("quotationList", quotesList);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}