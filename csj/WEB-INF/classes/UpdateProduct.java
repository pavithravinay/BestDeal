import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class UpdateProduct extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
        PrintWriter out = response.getWriter();

        out.println("<div id='body'>");
        out.println("<section id='content'>");  
        out.println("<fieldset>"); 

        out.println("<form method='post' action='UpdateProduct'>");
        out.println("<p><label for='productId'>Enter Product ID:</label>");
        out.println("<input name='prodId' id='prodId' type='text'/></p>");
        out.println("<input type='submit' value='Update'/>");
        out.println("</form>");
        out.println("<fieldset>"); 
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
        PrintWriter out = response.getWriter();
        String productIdString = request.getParameter("prodId");
        int productId = Integer.parseInt(productIdString);

        HashMap<String,Product> smartPhones = SmartPhone.buildBasicSmartPhoneList();

        out.println("<div id='body'>");
        out.println("<section id='content'>");  
        out.println("<fieldset>"); 


        out.println("<form method='post' action='SaveProducts?action=2'>");

        for(HashMap.Entry<String, Product> myKey: smartPhones.entrySet()){

            String key = myKey.getKey();
            Product ph1 = myKey.getValue();

            if(ph1.getProductId() == productId){

               
                out.println("<p><label for='productId'>Product ID:</label>");
                out.println("<input name='prodId' id='prodId' value='"+ ph1.getProductId()+"'readonly type='text'/></p>");

                out.println("<p><label for='productcategory'>Product Category:</label>");
                out.println("<input name='prodCategory' id='prodCategory' value='"+ph1.getType()+"'readonly type='text'/></p>");

                out.println("<p><label for='brandName'>Brand Name:</label>");
                out.println("<input name='brandName' id='brandName' value='" + ph1.getBrandName()+"'readonly type='text'/></p>");

                out.println("<p><label for='model'>Model:</label>");
                out.println("<input name='model' id='model' value='"+ph1.getModel()+"'type='text'/></p>");

                out.println("<p><label for='price'>Price:</label>");
                out.println("<input name='price' id='price' value='"+ph1.getPrice()+"'type='text'/></p>");

                out.println("<p><label for='discount'>Discount:</label>");
                out.println("<input name='discount' id='discount' value='"+ph1.getDiscount()+"'type='text'/></p>");

                out.println("<p><label for='rebate'>Rebate:</label>");
                out.println("<input name='rebate' id='rebate' value='"+ph1.getRebate()+"'type='text' /></p>");

                out.println("<p><label for='quantity'>Quantity:</label>");
                out.println("<input name='quantity' id='quantity' value='"+ph1.getQuantity()+"'type='text' /></p>");

                out.println("<p><label for='imagePath'>Image Path:</label>");
                out.println("<input name='imagePath' id='imagePath' value='"+ph1.getImageName()+"'type='text' /></p>");
            }
        }

        out.println("<input type='submit' value='Update'/>");
        out.println("</form>");
        out.println("<fieldset>"); 
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);
    }

}