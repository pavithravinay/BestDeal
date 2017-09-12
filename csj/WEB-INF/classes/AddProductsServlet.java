import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AddProductsServlet extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");  
        out.println("<fieldset>"); 
        out.println("<h2>Enter Product details</h2>");     
        out.println("<form method='post' action='SaveProduct'>");

        out.println("<p><label for='productId'>Product ID:</label>");
        out.println("<input name='prodId' id='prodId' value='' type='text'/></p>");

        out.println("<p><label for='productcategory'>Product Category:</label>");
        out.println("<input name='prodCategory' id='prodCategory' value='' type='text'/></p>");

        out.println("<p><label for='brandName'>Brand Name:</label>");
        out.println("<input name='brandName' id='brandName' value='' type='text'/></p>");

        out.println("<p><label for='model'>Model:</label>");
        out.println("<input name='model' id='model' value='' type='text'/></p>");

        out.println("<p><label for='price'>Price:</label>");
        out.println("<input name='price' id='price' value='' type='text'/></p>");

        out.println("<p><label for='discount'>Discount:</label>");
        out.println("<input name='discount' id='discount' value='' type='text'/></p>");

        out.println("<p><label for='rebate'>Rebate:</label>");
        out.println("<input name='rebate' id='rebate' value='' type='text' /></p>");

        out.println("<p><label for='quantity'>Quantity:</label>");
        out.println("<input name='quantity' id='quantity' value='' type='text' /></p>");

        out.println("<p><label for='imagePath'>Image Path:</label>");
        out.println("<input name='imagePath' id='imagePath' value='' type='text' /></p>");

        out.println("<input type='submit' value='Add'/>");
        out.println("</form>");
        out.println("<fieldset>"); 
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    }


}