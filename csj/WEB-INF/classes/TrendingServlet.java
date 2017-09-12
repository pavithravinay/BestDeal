import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class TrendingServlet extends HttpServlet{	

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


        // MongoDBDataStoreUtilities mongoUtils = new MongoDBDataStoreUtilities();
        HashMap<String, ArrayList<Reviews>> reviews = new HashMap<String, ArrayList<Reviews>>();

        LinkedHashMap<String,Float> mostLikedProducts = MongoDBDataStoreUtilities.mostLikedProducts();

        LinkedHashMap<String,Integer> top5ZipCodes = MongoDBDataStoreUtilities.top5zipcodes();

        LinkedHashMap<String,Integer> mostReviewedProducts = MongoDBDataStoreUtilities.mostReviewedProducts();

        /**********************/
        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");  
        out.println("<fieldset>"); 
        out.println("<h2>Top 5 most liked products: </h2>"); 
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<td>Product Name</td>");
        out.println("<td>Average rating </td>");        
        out.println("</tr");
        out.println("</thead>");
        out.println("<tbody>");


        for (Map.Entry<String, Float> myKey : mostLikedProducts.entrySet()) {
            String key = myKey.getKey();
            Float value = myKey.getValue();
        out.println("<tr>");
        out.println("<td>" +  key +" </td>");
        out.println("<td>" + value + " </td>");
          out.println("</tr>");
    }
        
      
        out.println("</tbody>");
        out.println("</table>");
        out.println("<h2>Top five zip-codes where maximum number of products sold: </h2>"); 
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<td>zip-codes</td>");
        out.println("<td>Number of products</td>");        
        out.println("</tr");
        out.println("</thead>");
        out.println("<tbody>");

        for (Map.Entry<String, Integer> myKey : top5ZipCodes.entrySet()) {
            String key = myKey.getKey();
            Integer value = myKey.getValue();
        out.println("<tr>");
        out.println("<td>" + key + " </td>");
        out.println("<td>" +  value+" </td>");
        out.println("</tr>");
        }
        out.println("</tbody>");
        out.println("</table>");
        out.println("<h2>Top five most reviewed products regardless of the rating: </h2>"); 
        out.println("<table>");
         out.println("<thead>");
        out.println("<tr>");
        out.println("<td>Product Name</td>");
        out.println("<td>Number of Reviews</td>");        
        out.println("</tr");
        out.println("</thead>");
        out.println("<tbody>");
        for (Map.Entry<String, Integer> myKey : mostReviewedProducts.entrySet()) {
            String key = myKey.getKey();
            Integer value = myKey.getValue();
        out.println("<tr>");
        out.println("<td>" + key + " </td>");
        out.println("<td>" +  value+" </td>");
        out.println("</tr>");
        }
        out.println("</tbody>");
        out.println("</table>");
        
        out.println("<fieldset>"); 
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        
    }


}