import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.logging.*;
import java.util.*;

public class CheckOutServlet extends HttpServlet {

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                int orderId = 0;

                Person person = new Person();
               
                String name = request.getParameter("name");
                String street = request.getParameter("street");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String zip = request.getParameter("zip");
                String ccNumber = request.getParameter("ccNumber");


                boolean isValid = true;
                boolean addSuccess = false;

                ArrayList<Cart> cartList = new ArrayList<Cart>();
                IMySqlDataStoreUtilities dbstore = new MySqlDataStoreUtilities();

                if (ccNumber.isEmpty() || name.isEmpty() || street.isEmpty() || city.isEmpty() 
                    || state.isEmpty() || zip.isEmpty()) {
                        isValid = false;
                }

                try {
                        HttpSession session = request.getSession(true);
                        String sessionProdId = new String("sessionProdId");
                        int productId = (Integer) session.getAttribute(sessionProdId);
                        session.setAttribute(sessionProdId, productId);
                        String userInfo = new String("UserInfo");
                        if (null != session.getAttribute(userInfo)) {

                                person = (Person) session.getAttribute(userInfo);
                        }

                        // orderId = SerializeDataStore.generateNewOrderId();
                        orderId = dbstore.generateNewOrderId();

                       
                        String cartInSession = new String("Cart"); 

                        if(null!=session.getAttribute(cartInSession)){
                             cartList = (ArrayList<Cart>)session.getAttribute(cartInSession);
                             for(Cart cart:cartList)
                             {
                                cart.setOrderId(orderId);
                                Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "datetime read inserting cart" + cart.getDeliveryDate());
                             }
                             session.removeAttribute(cartInSession);
                        }
                        
                        Cart cart = null;                      

                        // String fullPath = "../webapps/csj/Orders/Orders.txt";
                        // SerializeDataStore.writeCartDataStore(fullPath, cartList);
                        addSuccess = dbstore.insertOrder(cartList);
                        Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "addsuccess value after insert :" + addSuccess);

                } catch (Exception e) {
                        Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, e.toString());
                        PrintWriter out = response.getWriter();
                          out.println("<p>MySql server is not up and running.</p>");   
                }

                Utilities utility = new Utilities();
                response.setContentType("text/html");
                utility.printHtml("/Library/Tomcat/webapps/csj/Header.html", response);
                PrintWriter out = response.getWriter();
                out.println("<div id='body'>");
                out.println("<section id='content'>");
                if(isValid) {
                    Date dt = new Date();
                Calendar c = Calendar.getInstance(); 
                c.setTime(dt); 
                c.add(Calendar.DATE, 14);
                 dt = c.getTime();
                    if(addSuccess){
                        out.println("<h2>You have successfully placed the order.</h2>");
                        out.println("<h2>OrderId :" + orderId + "</h2>");
                        out.println("<h2>Delivery Date :" + dt + "</h2>");
                    }
                    else{
                        out.println("<h2>MySql server is not up and running.</h2>");
                    }

                }
                else{
                        out.println("<h2>Please enter all the details</h2>");
                }


                out.println("</section>");
                utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html", response);
                utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html", response);
        }
}