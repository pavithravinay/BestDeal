import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class OrderDetailsServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
        PrintWriter out = response.getWriter(); 
        int productId = 0;
        HttpSession session = request.getSession(true);

        try{
             
        String sessionProdId =new String("sessionProdId");
        productId = (Integer)session.getAttribute(sessionProdId);
        session.setAttribute(sessionProdId,productId);
        }    
        catch(Exception e){
            out.println(e.toString());
        }
        
        Cart newCart = new Cart();
        ArrayList<Cart> cartList = new ArrayList<Cart>();

        String cartInSession = new String("Cart"); 
        if(null!=session.getAttribute(cartInSession)){
             cartList = (ArrayList<Cart>)session.getAttribute(cartInSession);
        }
        
        
        String userName = request.getParameter("userid");
        String password = request.getParameter("password");
        Person p1 = new Person();
        Person p2 = p1.getDefaultCustomerLoginCredentials();       

           
            out.println("<div id='body'>");
            out.println("<section id='content'>");
            out.println("<form method='post' action='CheckOutServlet'>");
            out.println("<p>Check your order and enter below details:</p>");        
            out.println("<table>");
            for (Cart c1: cartList){
                    
                    
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
                    
                    out.println("</tr>");                   

                }
            out.println("</table>");

            out.println("<p><label for='name:'>Name:</label>");
            out.println("<input name='name' id='name' value='' type='text'/></p>"); 

            out.println("<p><label for='address:'>Address:</label></p>");

            out.println("<label for='street:'>Street:</label>");
            out.println("<input name='street' id='street' value='' type='text'/>"); 

            out.println("<label for='city:'>City:</label>");
            out.println("<input name='city' id='city' value='' type='text'/>"); 

            out.println("<label for='state:'>State:</label>");
            out.println("<input name='state' id='state' value='' type='text'/>"); 

            out.println("<p><label for='zip:'>Zip:</label>");
            out.println("<input name='zip' id='zip' value='' type='text'/></p>"); 

            out.println("<p><label for='ccNumber:'>Credit Card Number:</label>");
            out.println("<input name='ccNumber' id='ccNumber' value='' type='text'/></p>"); 

            out.println("<center><input type='submit' value='Submit'/></center>");            
            out.println("</form>");        
            out.println("</section>");
            utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
            utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);

       


    }
}
