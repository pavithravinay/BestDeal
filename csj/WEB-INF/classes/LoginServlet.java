import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class LoginServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        HttpSession session = request.getSession(true);
        String sessionProdId =new String("sessionProdId");
        if(null != session.getAttribute(sessionProdId)){
            int prodId = (Integer)session.getAttribute(sessionProdId);
            session.setAttribute(sessionProdId,prodId);
        }


        String userInfo = new String("UserInfo");        

        if(null!=session.getAttribute(userInfo)){
            response.sendRedirect("Home");
        }

         response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
        PrintWriter out = response.getWriter();
        
        out.println("<div id='body'>");
        out.println("<section id='content'>");
        out.println("<center>");
        out.println("<form method='post' action='ValidateCredential'>");
        out.println("<h2>Login to proceed:</h2>");
        // out.println("<h4>"+"Enter your User ID and Password and click Login"+"</h4>");
        out.println("<table cellpadding='2' cellspacing='1'>");
        out.println("<tr>");
        out.println("<td>User ID</td>");
        out.println("<td><input type='TEXT' size='15' name='userid'></input></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Password</td>");
        out.println("<td><input type='PASSWORD' size='15' name='password'/></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Role</td>");
        out.println("<td><select id='role' name='role'>");
        out.println("<option value='customer'>Customer</option>"); 
        out.println("<option value='storeManager'>Store Manager</option>"); 
        out.println("<option value='salesman'>Sales Person</option>");   
        out.println("</select></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td colspan='2'>");
        out.println("<center><input type='submit' value='Login'/></center>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</form>");
        out.println("</center>");
             
        out.println("<a href='UserRegistrationServlet'>New User? Click here to register.</a>");
        
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    }


}
