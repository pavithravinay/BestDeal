import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class CancelOrderServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		
                
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
        PrintWriter out = response.getWriter();
        String orderIdString = request.getParameter("OrderId");
        int orderId = Integer.parseInt(orderIdString);

        // SerializeDataStore.updateCartDataStore(orderId);

        IMySqlDataStoreUtilities sdsu = new MySqlDataStoreUtilities();
        Boolean deleteSuccess = sdsu.deleteOrder(orderId);       

        out.println("<div id='body'>");
        out.println("<section id='content'>");          
        if(deleteSuccess){
            out.println("<p>You have successfully deleted the order.</p>");    
        }
        else{
            out.println("<p>MySql server is not up and running.</p>");    
        }
                         
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);
    }


}