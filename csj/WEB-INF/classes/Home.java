import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.logging.*;
import java.util.*;

public class Home extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		String userInfo = new String("UserInfo");
		Person person = null;
		int cartItemCount=0;
		String fname="";

		if (null != session.getAttribute(userInfo)) {
			person = (Person) session.getAttribute(userInfo);
			fname = person.getFName();
		}

		Cart newCart = new Cart();
        ArrayList<Cart> cartList = new ArrayList<Cart>();

        String cartInSession = new String("Cart"); 
        if(null!=session.getAttribute(cartInSession)){
             cartList = (ArrayList<Cart>)session.getAttribute(cartInSession);
        }

        cartItemCount= cartList.size();

		response.setContentType("text/html");

		Utilities utility = new Utilities();
		PrintWriter out = response.getWriter();
		utility.printHtml("/Library/Tomcat/webapps/csj/Header.html", response);

		out.println("<nav id='welcome'>");
    	out.println("<ul>");        	
        out.println("<li><a>Welcome <mark>" + fname + "!!!</mark></a><li> ");
        out.println("<li><a href='CartServlet'>Cart(" + cartItemCount+ ")</a><li> ");
        out.println("</ul>");
    	out.println("</nav>");

		utility.printHtml("/Library/Tomcat/webapps/csj/Content.html",response);
		utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html", response);

		
		if (person != null && person.getRole().equalsIgnoreCase("StoreManager")) {
			out.println("<a href='AddProducts'>Add Products</a>");
			out.println("<br/>");
			out.println("<a href='UpdateProduct'>Update Products</a>");
			out.println("<br/>");
			out.println("<a href='DeleteProducts'>Delete Products</a>");
			out.println("<br/>");
			out.println("<a href='DataAnalyticsServlet'>Data Analytics</a>");
		}
		if (person != null && person.getRole().equalsIgnoreCase("SalesMan")) {
			out.println("<a href='UserRegistrationServlet'>Create Customer Account</a><br/>");
			out.println("<a href='SalesmanOperations'>Add Customer Orders</a><br/>");
			out.println("<a href='UpdateCustomerOrders1'>Update Customer Orders</a><br/>");
			out.println("<a href='UpdateCustomerOrders1'>Delete Customer Orders</a>");
		}

		utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html", response);
	}
}
