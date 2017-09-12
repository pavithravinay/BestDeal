import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class UpdateCustomerOrders2 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userInfo = new String("UserInfo");
        HttpSession session = request.getSession(true);
        Person person = new Person();
        if (null != session.getAttribute(userInfo)) {
            person = (Person) session.getAttribute(userInfo);
        }
        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html", response);
        PrintWriter out = response.getWriter();
        String orderIdString = request.getParameter("orderId");
        int orderId = Integer.parseInt(orderIdString);
        String filePath = "../webapps/csj/Orders/Orders.txt";
        // ArrayList<Cart> cartList =
        // SerializeDataStore.readCartDataStore(filePath);
        IMySqlDataStoreUtilities sdsu = new MySqlDataStoreUtilities();
        

        int removeProdId = 0;
        String removeProdIds = request.getParameter("removeProduct");
        if (removeProdIds != null) {
            removeProdId = Integer.parseInt(removeProdIds);
            Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "remove prod id" + removeProdId);
        }

        out.println("<div id='body'>");
        out.println("<section id='content'>");
        out.println("<fieldset>");

        boolean orderFound = false;
        out.println("<table>");        
        sdsu.updateOrder(orderId, removeProdId);
        Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE,
                    " Update: deleted item " + removeProdId + " from order# " +orderId);

        /*
         * Read all the orders from the file into an arrayList, find an order
         * matching 'removeprodId' and put it in a new arrayList and write the
         * remaining orders back to the file. Read orderId from session and
         * remove particular item from that order.
         */
        // if(removeProdId>0){
        // Iterator<Cart> it = cartList.iterator();
        // while(it.hasNext()){
        // Cart cartx=it.next();
        // if(cartx.getProductId()==removeProdId){
        // it.remove();
        // }
        // }
        // SerializeDataStore.updateCartDataStore(orderId);
        // SerializeDataStore.writeCartDataStore(filePath, cartList);
        // cartList = SerializeDataStore.readCartDataStore(filePath);
        // }
        ArrayList<Cart> cartList = sdsu.readAllOrders();

        Date dt = null;
        for (Cart c1 : cartList) {
            if (c1.getOrderId() == orderId
                    && (c1.getUserId().equals(person.getUserId()) || person.getRole().equalsIgnoreCase("SalesMan"))) {

                dt = c1.getDeliveryDate();
                Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO,
                        "datetime read from cart" + c1.getDeliveryDate());

                orderFound = true;

                out.println("<tr>");

                out.println("<td width='350' style='border: 1px solid black ;'>");
                out.println(c1.getBrandName());
                out.println("</td>");

                out.println("<td width='350' style='border: 1px solid black ;'>");
                out.println(c1.getModel());
                out.println("</td>");

                out.println("<td width='350' style='border: 1px solid black ;'>");
                out.println("$" + c1.getPrice());
                out.println("</td>");

                out.println("<td width='350' style='border: 1px solid black ;'>");
                out.println("<form action='UpdateCustomerOrders2?removeProduct=" + c1.getProductId() + "&orderId="
                        + orderId + " 'method='post' >");
                out.println("<button type='submit'>Remove Item</button>");
                // out.println("<meta http-equiv='refresh' content='2' />");
                out.println("</form>");
                out.println("</td>");

                out.println("</tr>");
            }

        }
        out.println("</table>");
        if (dt != null) {
            out.println("<h2>Expected Delivery Date :" + dt + "</h2>");
        }
        out.println("<form method='post' action='CancelOrderServlet?OrderId=" + orderIdString + "'>");
        if (!orderFound) {
            out.println("This order does not exist!");
        } else {
            out.println("<input type='submit' value='Cancel Order'/>");
        }
        out.println("</form>");
        out.println("<fieldset>");
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html", response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html", response);
    }

    protected void doget(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}