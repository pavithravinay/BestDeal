import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.HashMap;


public class DisplayAccessories extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


        SmartPhone phone1 = new SmartPhone();
        HashMap<String,Accessory> smartPhones = phone1.buildBasicAccessoryList(); 
        int index=0;

        response.setContentType("text/html");   
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);      
        //utility.printHtml("/Library/Tomcat/webapps/csj/SmartPhones.html",response);
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");
        

        out.println("<table>");


        for(HashMap.Entry<String, Accessory> myKey: smartPhones.entrySet()){

            String key = myKey.getKey();
            Accessory ph1 = myKey.getValue();

            
                if(index%3==0){
                    out.println("<tr>");
                }
                out.println("<td>");
                out.println("<p>");
                out.println(ph1.getModel());
                out.println("</p");
                out.println("<p>");
                out.println("Original Price: $"+ph1.getPrice());
                out.println("<br><label for='discount'>Discount: $"+ ph1.getDiscount()+"</label>");
                double finalPrice = ph1.getPrice()-ph1.getDiscount();
                out.println("<br><label for='afterDiscount'>Price after discount: $"+ finalPrice+"</label>");                  
                out.println("</p>");
                out.println("<img class='header-image' src=Images/" + ph1.getImageName());      
                out.println("/>");
                out.println("<p>");         
                out.println("<a href='CartServlet?productId="+ph1.getProductId()+"'> <input type='button' value='BuyNow'/>");       
                out.println("</a>");
                out.println("</p>");
                out.println("</p>");
                out.println("<button>");
                out.println("Reviews");
                out.println("</button>");
                out.println("</p>");
                out.println("</td>");
                index++;
                if(index%3==0){  
                    out.println("<tr>");
                    index=0;
                }
                
                      
        }


        out.println("</table>");
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);
    }
}
