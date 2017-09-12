import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class UserRegistrationServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");  
        out.println("<fieldset>"); 
        out.println("<h2>Register here:</h2>");     
        out.println("<form method='post' action='VerifyRegistration'>");        
        out.println("<p><label for='fname'>First Name:</label>");
        out.println("<input name='fname' id='fname' value='' type='text'/></p>");
        out.println("<p><label for='fname'>Last Name:</label>");
        out.println("<input name='lname' id='lname' value='' type='text'/></p>");
        out.println("<p><label for='email'>Email:</label>");
        out.println("<input name='email' id='email' value='' type='text'/></p>");
        out.println("<p><label for='contact'>Contact Number:</label>");
        out.println("<input name='contact' id='contact' value='' type='text'/></p>");
        out.println("<p><label for='userName'>Desired User Name:</label>");
        out.println("<input name='uname' id='uname' value='' type='text' /></p>");
        out.println("<p><label for='password'>Desired Password:</label>");
        out.println("<input name='password' id='password' value='' type='password' /></p>");
        out.println("<table>");
         out.println("<tr>");
        out.println("<td>Role</td>");
        out.println("<td><select id='role' name='role'>");
        out.println("<option value='customer'>Customer</option>"); 
        out.println("<option value='storeManager'>Store Manager</option>"); 
        out.println("<option value='salesman'>Sales Person</option>");   
        out.println("</select></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<input type='submit' value='Submit'/>");
        out.println("</form>");
        out.println("<fieldset>"); 
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    }


}