import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class SaveProducts extends HttpServlet {

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {

                // ArrayList<Product> product = new ArrayList<Product>();
                response.setContentType("text/html");
                Utilities utility = new Utilities();
                PrintWriter out = response.getWriter();

                String productId = request.getParameter("prodId");
                String category = request.getParameter("prodCategory");
                String brand = request.getParameter("brandName");
                String model = request.getParameter("model");
                String price = request.getParameter("price");
                String discount = request.getParameter("discount");
                String rebate = request.getParameter("rebate");
                String quantity = request.getParameter("quantity");
                String imagePath = request.getParameter("imagePath");
                String action = request.getParameter("action");


                Product product = new Product();
                SalesManager manager = new SalesManager();

                if (!action.equals("3")) {
                        Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "entering action 3");

                        if(productId.isEmpty() || productId==null){
                            Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "entering empty");
                            response.sendRedirect("AddProducts");
                            return;

                        }
                        else{
                        Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "entering not empty");
                    }

                        product = new Product(Integer.parseInt(productId), brand, model, Double.parseDouble(price),
                                        Double.parseDouble(discount), Double.parseDouble(rebate), Integer.parseInt(quantity), imagePath,
                                        category);

                        // out.println(Integer.parseInt(productId)+brand+model+Double.parseDouble(price)+
                        //                 Double.parseDouble(discount)+Double.parseDouble(rebate)+Integer.parseInt(quantity)+ imagePath+
                        //                 category);
                        
                }
                else{
                        int productIdNew = Integer.parseInt(productId);
                        HashMap<String,Product> products = SmartPhone.buildBasicSmartPhoneList();
                        for(HashMap.Entry<String, Product> myKey: products.entrySet()){

                            String key = myKey.getKey();
                            Product ph1 = myKey.getValue();

                            if(ph1.getProductId()==productIdNew){
                                product=ph1;
                            }
                    }
                }

                if (action.equals("1")) {
                        manager.addProduct(product);
                } else if (action.equals("2")) {
                        manager.updateProduct(product);
                } else {
                        manager.deleteProduct(product);
                }

                utility.printHtml("/Library/Tomcat/webapps/csj/Header.html", response);
                out.println("<div id='body'>");
                out.println("<section id='content'>");

                if(!action.equals("3")){


                out.println("<br><p>You have successfully added/updated below product:</p>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>");
                out.println("Product Id");
                out.println("</th>");
                out.println("<th>");
                out.println("Category");
                out.println("</th>");
                out.println("<th>");
                out.println("Brand");
                out.println("</th>");
                out.println("<th>");
                out.println("Model");
                out.println("</th>");
                out.println("<th>");
                out.println("Price");
                out.println("</th>");
                out.println("<th>");
                out.println("Discount");
                out.println("</th>");
                out.println("<th>");
                out.println("Rebate");
                out.println("</th>");
                out.println("<th>");
                out.println("Quantity");
                out.println("</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>");
                out.println(productId);
                out.println("</td>");
                out.println("<td>");
                out.println(category);
                out.println("</td>");
                out.println("<td>");
                out.println(brand);
                out.println("</td>");
                out.println("<td>");
                out.println(model);
                out.println("</td>");
                out.println("<td>");
                out.println(price);
                out.println("</td>");
                out.println("<td>");
                out.println(discount);
                out.println("</td>");
                out.println("<td>");
                out.println(rebate);
                out.println("</td>");
                out.println("<td>");
                out.println(quantity);
                out.println("</td>");
                out.println("</tr>");
                out.println("</table>");
        }
        else{
                out.println("<br><p>You have successfully deleted the product:</p>");
        }
                // out.println(productId+category+brand+model+price+discount+rebate+quantity+imagePath);
                out.println("</section>");
                utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html", response);
                utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html", response);
        }
}