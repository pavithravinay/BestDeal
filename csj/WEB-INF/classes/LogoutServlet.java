import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class LogoutServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        HttpSession session = request.getSession(true);
        session.invalidate();
        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
        PrintWriter out = response.getWriter();

        out.println("<div id='body'>");
        out.println("<section id='content'>");
        out.println("<center>");
    
        out.println("<h4>Logout Successful!!!</h4>");
       
        out.println("</form>");
        out.println("</center>");
        
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    }


}
