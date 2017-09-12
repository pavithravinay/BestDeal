import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.HashMap;

public class SmartPhoneServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String model = request.getParameter("model");
        String brandName = request.getParameter("brandName");
        String type=request.getParameter("type");
        SmartPhone phone1 = new SmartPhone();
        HashMap<String,Product> smartPhones = phone1.buildBasicSmartPhoneList(); 
        int index=0;

        response.setContentType("text/html");   
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);      
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");


        out.println("<table>");

        HttpSession session = request.getSession(true);
        
        //Get logged in user info if available
        String userInfo = new String("UserInfo");
         Person person = new Person();
        if (null != session.getAttribute(userInfo)) {

             person = (Person) session.getAttribute(userInfo);
        }


        for(HashMap.Entry<String, Product> myKey: smartPhones.entrySet()){

            String key = myKey.getKey();
            Product ph1 = myKey.getValue();

            if((ph1.getBrandName().equals(brandName) || brandName==null) && ph1.getType().equalsIgnoreCase(type)){
                if(index%3==0){
                    out.println("<tr>");
                }
                out.println("<td>");
                out.println("<p><a href='DisplayAccessories'>");
                out.println(ph1.getModel());
                out.println("</a></p");
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
                out.println("<p>");
                out.println("<form action='WriteReviewsServlet' method='get'>");
                out.println("<button type='submit'>Write Reviews</button>");
                
                out.println("<input type='hidden' name='model' value='" + ph1.getModel() + "'/>");
                out.println("<input type='hidden' name='category' value='" + ph1.getType() + "'/>");
                out.println("<input type='hidden' name='price' value='" + ph1.getPrice() + "'/>");
                out.println("<input type='hidden' name='manufacturer' value='" + ph1.getBrandName() + "'/>");
                out.println("<input type='hidden' name='rebate' value='" + ph1.getRebate() + "'/>");
                out.println("<input type='hidden' name='userId' value='" + person.getUserId() + "'/>");
                out.println("</form>");                
                out.println("</p>");
                out.println("<p>");
                out.println("<form action='ViewReviewsServlet' method='post'>");
                out.println("<button type='submit'>View Reviews</button>");
                 out.println("<input type='hidden' name='model' value='" + ph1.getModel() + "'/>");
                out.println("<input type='hidden' name='category' value='" + ph1.getType() + "'/>");                
                out.println("<input type='hidden' name='manufacturer' value='" + ph1.getBrandName() + "'/>");                
                out.println("</form>");        
                out.println("</p>");
                out.println("</td>");
                index++;
                if(index%3==0){  
                    out.println("<tr>");
                    index=0;
                }

            }           
        }


        out.println("</table>");
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);
    }
}
