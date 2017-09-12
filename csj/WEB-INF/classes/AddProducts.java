import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AddProducts extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");  
        out.println("<fieldset>"); 
        out.println("<h2>Enter Product details</h2>");     
        out.println("<form method='post' action='SaveProducts?action=1'>");

        out.println("<p><label for='productId'>Product ID:</label>");
        out.println("<input name='prodId' id='prodId' value='' type='text'/></p>");

        
        out.println("<p>Product Category");
        out.println("<select id='prodCategory' name='prodCategory'>");
        out.println("<option value='phone'>phone</option>"); 
        out.println("<option value='tablet'>tablet</option>"); 
        out.println("<option value='laptop'>laptop</option>"); 
        out.println("<option value='tv'>tv</option>");    
        out.println("</select></p>");

        out.println("<p>Brand Name");
        out.println("<select id='brandName' name='brandName'>");
        out.println("<option value='Samsung'>Samsung</option>"); 
        out.println("<option value='Apple'>Apple</option>"); 
        out.println("<option value='LG'>LG</option>"); 
        out.println("<option value='Sony'>Sony</option>");
        out.println("<option value='Dell'>Dell</option>"); 
        out.println("<option value='Microsoft'>Microsoft</option>"); 
        out.println("<option value='Panasonic'>Panasonic</option>"); 
        out.println("<option value='Toshiba'>Toshiba</option>");           
        out.println("</select></p>");


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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    }


}