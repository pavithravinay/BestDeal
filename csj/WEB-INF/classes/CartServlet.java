
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class CartServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		HttpSession session = request.getSession(true);
		Boolean userLoggedIn = false;


		String prodIdString = request.getParameter("productId");
		String removeProdIds = request.getParameter("removeProduct");	
		Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id string" + removeProdIds);	
		String sessionProdId = new String("sessionProdId") ;		
		int prodId =0;
		int removeProdId=0;
		int index=0;

		if(prodIdString!=null){
			prodId = Integer.parseInt(prodIdString);
			session.setAttribute(sessionProdId, prodId);
		}

		if(removeProdIds!=null){
			removeProdId = Integer.parseInt(removeProdIds);
			Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id" + removeProdId);
		}

		Person person = new Person();

		String userInfo = new String("UserInfo");  
		if(null!=session.getAttribute(userInfo)){
			userLoggedIn = true;
			person = (Person)session.getAttribute(userInfo);
		}


		SmartPhone phone1 = new SmartPhone();
		HashMap<String,Product> smartPhones = phone1.buildBasicSmartPhoneList(); 

		response.setContentType("text/html");
		Utilities utility = new Utilities();
		utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
		PrintWriter out = response.getWriter();
		out.println("<div id='body'>");
		out.println("<section id='content'>");
		out.println("<h2>Cart</h2>");
		out.println("<table>");
		out.println("<thead>");
		out.println("<tr>");
				
		out.println("<td>Brand</td>");
		out.println("<td>Model</td>");
		out.println("<td>price</td>");
		out.println("<td>Buy Warranty</td>");
		out.println("<td>Remove Item</td>");		
		
		out.println("</tr>");
		out.println("</thead>");

		ArrayList<Cart> myCart = new ArrayList<Cart>();
		for(HashMap.Entry<String, Product> myKey: smartPhones.entrySet()){

			String key = myKey.getKey();
			Product ph1 = myKey.getValue();
			if(ph1.getProductId() == prodId){
				Date dt = new Date();
				Calendar c = Calendar.getInstance(); 
				c.setTime(dt); 
				c.add(Calendar.DATE, 14);
				 dt = c.getTime();


				 Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "datetime first insert into cart" + dt);
				Cart cart = new Cart(ph1.getBrandName(), ph1.getModel(), (ph1.getPrice()-ph1.getDiscount()), 
						SerializeDataStore.generateNewOrderId(), person.getUserId(),false,dt,prodId);	

				myCart.add(cart); 
			
			}
			else if(ph1.getProductId() == removeProdId){
				Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id at index" + index);
				Cart cart = new Cart(ph1.getBrandName(), ph1.getModel(), ph1.getPrice(), 
						SerializeDataStore.generateNewOrderId(), person.getUserId(),false,null,removeProdId);
				// out.println("<h1>"+cart+"</h1>");
				myCart.remove(index);
			}
			index++;
		}

		String cartInSession = new String("Cart"); 
		if(null!=session.getAttribute(cartInSession)){
			ArrayList<Cart> priorCart = (ArrayList<Cart>)session.getAttribute(cartInSession);
            Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "Cart In Session" + priorCart.toString());

			myCart.addAll(priorCart);
		}

		session.setAttribute(cartInSession, myCart);


		for (Cart c1: myCart){


			out.println("<tr>");

			out.println("<td width='350' style='border: 1px solid black ;'>");
			out.println(c1.getBrandName());
			out.println("</td>");

			out.println("<td width='350' style='border: 1px solid black ;'>");
			out.println(c1.getModel());
			out.println("</td>");

			out.println("<td width='350' style='border: 1px solid black ;'>");
			out.println("$" + c1.getPrice());
			out.println("</td>");

			out.println("<td width='350' style='border: 1px solid black ;'>");
			out.println("<input type='checkbox' name='buyWarranty' value='buyWarranty'><br>");
			out.println("</td>");

			out.println("<td width='350' style='border: 1px solid black ;'>");
			out.println("<form action='CartServlet?removeProduct=" + c1.getProductId() + " 'method='post' >");
			out.println("<button type='submit'>Remove Item</button>");
			out.println("</form>");
			out.println("</td>");
			Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "rc1.getProductId()" + c1.getProductId());
			out.println("</tr>");	    			

		}


		out.println("</table>");
		// out.println("<a href='LoginServlet'> <input type='button' value='Check
		// Out'/>");

		if(!userLoggedIn){
			out.println("<form action='LoginServlet' >");
		}
		else{
			out.println("<form action='OrderDetailsServlet' method='post'>");
		}

		out.println("<button type='submit'>Check Out</button>");
		out.println("</form>");
		out.println("</a>");out.println("</section>");
		utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
		utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		HttpSession session = request.getSession(true);
		Boolean userLoggedIn = false;


		String prodIdString = request.getParameter("productId");
		String removeProdIds = request.getParameter("removeProduct");	
		Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id string" + removeProdIds);	
		String sessionProdId = new String("sessionProdId") ;		
		int prodId =0;
		int removeProdId=0;
		int index=0;

		if(prodIdString!=null){
			prodId = Integer.parseInt(prodIdString);
			session.setAttribute(sessionProdId, prodId);
		}

		if(removeProdIds!=null){
			removeProdId = Integer.parseInt(removeProdIds);
			Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id" + removeProdId);
		}

		Person person = new Person();

		String userInfo = new String("UserInfo");  
		if(null!=session.getAttribute(userInfo)){
			userLoggedIn = true;
			person = (Person)session.getAttribute(userInfo);
		}


		SmartPhone phone1 = new SmartPhone();
		HashMap<String,Product> smartPhones = phone1.buildBasicSmartPhoneList(); 

		response.setContentType("text/html");
		Utilities utility = new Utilities();
		utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
		PrintWriter out = response.getWriter();
		out.println("<div id='body'>");
		out.println("<section id='content'>");
		out.println("<h2>Cart</h2>");
		out.println("<table >");
		out.println("<thead>");
		
		out.println("<tr>");
		out.println("<td>Brand</td>");
		out.println("<td>Model</td>");
		out.println("<td>price</td>");
		out.println("<td>Buy Warranty</td>");
		out.println("<td>Remove Item</td>");
		out.println("</tr>");
		
		out.println("</thead>");

		ArrayList<Cart> myCart = new ArrayList<Cart>();
		String cartInSession = new String("Cart"); 
		if(null!=session.getAttribute(cartInSession)){
			ArrayList<Cart> priorCart = (ArrayList<Cart>)session.getAttribute(cartInSession);

			myCart.addAll(priorCart);
		}
		for(HashMap.Entry<String, Product> myKey: smartPhones.entrySet()){

			String key = myKey.getKey();
			Product ph1 = myKey.getValue();
			if(ph1.getProductId() == prodId){
				Date dt = new Date();
				Calendar c = Calendar.getInstance(); 
				c.setTime(dt); 
				c.add(Calendar.DATE, 14);
				 dt = c.getTime();


				 Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "datetime first insert into cart" + dt);
				Cart cart = new Cart(ph1.getBrandName(), ph1.getModel(), ph1.getPrice(), 
						SerializeDataStore.generateNewOrderId(), person.getUserId(),false,dt,prodId);	

				myCart.add(cart); 
			}
			else if(ph1.getProductId() == removeProdId){
				Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id at index" + index);

				Iterator<Cart> it = myCart.iterator();
				while(it.hasNext()){
					Cart cartx=it.next();
					if(cartx.getProductId()==removeProdId){
						it.remove();
						break;
					}
				}
			}
			index++;
		}

		

		session.setAttribute(cartInSession, myCart);

		for (Cart c1: myCart){


			out.println("<tr>");

			out.println("<td width='350' style='border: 1px solid black ;'>");
			out.println(c1.getBrandName());
			out.println("</td>");

			out.println("<td width='350' style='border: 1px solid black ;'>");
			out.println(c1.getModel());
			out.println("</td>");

			out.println("<td width='350' style='border: 1px solid black ;'>");
			out.println("$" + c1.getPrice());
			out.println("</td>");

			out.println("<td width='350' style='border: 1px solid black ;'>");
			out.println("<input type='checkbox' name='buyWarranty' value='buyWarranty'><br>");
			out.println("</td>");

			out.println("<td width='350' style='border: 1px solid black ;'>");
			out.println("<form action='CartServlet?removeProduct=" + c1.getProductId() + " 'method='post' >");
			out.println("<button type='submit'>Remove Item</button>");
			out.println("</form>");
			out.println("</td>");
			Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "rc1.getProductId()" + c1.getProductId());
			out.println("</tr>");	    			

		}


		out.println("</table>");
		// out.println("<a href='LoginServlet'> <input type='button' value='Check
		// Out'/>");

		if(!userLoggedIn){
			out.println("<form action='LoginServlet' >");
		}
		else{
			out.println("<form action='OrderDetailsServlet' method='post'>");
		}

		out.println("<button type='submit'>Check Out</button>");
		out.println("</form>");
		out.println("</a>");out.println("</section>");
		utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
		utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);
	}

}
