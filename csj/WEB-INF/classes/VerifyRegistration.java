import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class VerifyRegistration extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String filePath = "../webapps/csj/Files/Person.txt";
        IMySqlDataStoreUtilities dsutils = new MySqlDataStoreUtilities();
        //ArrayList<Person> users = SerializeDataStore.readPersonDataStore();
        ArrayList<Person> users = dsutils.readPerson();

        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");
        String contactNumber = request.getParameter("contact");
        String userName = request.getParameter("uname");
        String password = request.getParameter("password");
        String role =  request.getParameter("role");

        Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "VerifyRegistration role " + role);

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html", response);
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");
        out.println("<center>");
        out.println("<form method='post' action='LoginServlet'>");

        boolean personExists = false;
        for (Person user : users) {
            if (user.getEmail().equals(email)) {
                personExists = true;
                break;
            }
        }

        if (personExists) {
            out.println("<h4>This email id already exists, please try again by <a href='UserRegistrationServlet'>clicking here</a>.</h4>");
        } else {
            Person person = new Person(userName, password, firstName, lastName, email, contactNumber, role);
            
            Boolean addSuccess = false;
            addSuccess = dsutils.addPerson(person);
//            users.add(person);
//            SerializeDataStore.writePersonDataStore(users);

        HttpSession sessionOld = request.getSession(true);
        String userInfoOld = new String("UserInfo");

        if (null == sessionOld.getAttribute(userInfoOld)) {        

            HttpSession session = request.getSession(true);
            String userInfo = new String("UserInfo"); 
            session.setAttribute(userInfo, person);
        }

            out.println("<h2>Congrats!!! User Registration successful!!!</h2>");
        }

        out.println("</form>");
        out.println("</center>");
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html", response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html", response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
