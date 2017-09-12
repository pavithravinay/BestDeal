import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class UpdateCustomerOrders3 extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		// ArrayList<Product> product = new ArrayList<Product>();
	response.setContentType("text/html");
        Utilities utility = new Utilities();
        PrintWriter out = response.getWriter();

	String orderId = request.getParameter("orderId");
	String brand = request.getParameter("brand");	
	String model = request.getParameter("model");
	String price = request.getParameter("price");
	

	Cart cart = new Cart(brand, model, Double.parseDouble(price), Integer.parseInt(orderId));
	

        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
        out.println("<div id='body'>");
        out.println("<section id='content'>");
        out.println("<br><p>You have successfully updated below order:</p>");        
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>");
        out.println("Order Id");
        out.println("</th>");        
        out.println("<th>");
        out.println("Brand");
        out.println("</th>");
        out.println("<th>");
        out.println("Model");
        out.println("</th>");
        out.println("<th>");
        out.println("Price");
        out.println("</th>");        
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>");
        out.println(orderId);
        out.println("</td>");        
        out.println("<td>");
        out.println(brand);
        out.println("</td>");
        out.println("<td>");
        out.println(model);
        out.println("</td>");
        out.println("<td>");
        out.println(price);
        out.println("</td>");               
        out.println("</tr>");
        out.println("</table>");
        // out.println(productId+category+brand+model+price+discount+rebate+quantity+imagePath);
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);
	}
}