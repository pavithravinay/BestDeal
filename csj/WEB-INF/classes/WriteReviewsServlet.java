import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class WriteReviewsServlet extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String userName = request.getParameter("userid");
        Reviews review = new Reviews();

        review.setProductModelName(request.getParameter("productModelName").trim());
        review.setProductCategory(request.getParameter("productCategory").trim());
        review.setProductPrice(Double.parseDouble(request.getParameter("productPrice").trim()));
        review.setRetaileName(request.getParameter("retailerName").trim());
        review.setRetailerZip(request.getParameter("retailerZip").trim());
        review.setRetailerCity(request.getParameter("retailerCity").trim());
        review.setRetailerState(request.getParameter("retailerState").trim());
        review.setProductOnSale(request.getParameter("productOnSale").trim());
        review.setManufacturerName(request.getParameter("manufacturerName").trim());
        review.setManufacturerRebate(request.getParameter("manufacturerRebate").trim());
        review.setUserId(request.getParameter("userID").trim());
        review.setUserAge(Integer.parseInt(request.getParameter("userAge")));
        review.setUserGender(request.getParameter("userGender").trim());
        review.setOccupation(request.getParameter("userOccupation").trim());
        review.setRating(Integer.parseInt(request.getParameter("reviewRating").trim()));
        review.setReviewDate(request.getParameter("reviewDate").trim());
        review.setReviewText(request.getParameter("review").trim());

        Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO, "Logging from WriteReviewsServlet class" + review.toString());
        //MongoDBDataStoreUtilities mongoUtils = new MongoDBDataStoreUtilities();
        Boolean success = MongoDBDataStoreUtilities.storeReview(review);

        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
        PrintWriter out = response.getWriter();
        out.println("<div id='body'>");
        out.println("<section id='content'>");  
        out.println("<fieldset>"); 
        if(success){
            out.println("<h2>Thanks for taking time to write review, it has been added successfully.</h2>");  
        }
        else {
            out.println("<h2>MongoDB is not up and running.</h2>");  
        }
        
        out.println("<fieldset>"); 
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response); 
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        /**********************/
        response.setContentType("text/html");
        Utilities utility = new Utilities();
        utility.printHtml("/Library/Tomcat/webapps/csj/Header.html",response);
        PrintWriter out = response.getWriter();
        // Calendar today = Calendar.getInstance();
        // today.set(Calendar.HOUR_OF_DAY, 0);

        Calendar today = Calendar.getInstance();
        today.clear(Calendar.HOUR); today.clear(Calendar.MINUTE); today.clear(Calendar.SECOND);
        Date todayDate = today.getTime();

        out.println("<div id='body'>");
        out.println("<section id='content'>");  
        out.println("<fieldset>"); 
        out.println("<h2>Please fill the below form to submit your review</h2>");     
        out.println("<form method='post' action='WriteReviewsServlet'>");        
        out.println("<p><label for='productModelName'>ProductModelName</label>");
        out.println("<input name='productModelName' id='productModelName'  readonly  value=' "+  request.getParameter("model")+ "' type='text'/></p>");
        
        out.println("<p><label for='productCategory'>ProductCategory</label>");
        out.println("<input name='productCategory' id='productCategory'  readonly  value=' "+  request.getParameter("category")+ "' type='text'/></p>");

        // out.println("<p><label for='ProductCategory'>Product Category</label>");
        // out.println("<select id='category' name='category'>");
        // out.println("<option value='smartPhones'>Smart Phones</option>"); 
        // out.println("<option value='tablets'>Tablets</option>"); 
        // out.println("<option value='laptop'>Laptop</option>"); 
        // out.println("<option value='tv'>TV</option>");   
        // out.println("</select>");
        out.println("<p><label for='productPrice'>Product Price</label>");
        out.println("<input name='productPrice' id='productPrice'  readonly  value='"+  request.getParameter("price")+ "' type='text'/></p>");
        out.println("<p><label for='retailerName'>Retailer Name</label>");
        out.println("<input name='retailerName' id='retailerName' value='Best Deal' readonly  type='text'/></p>");
        out.println("<p><label for='retailerZip'>Retailer Zip</label>");
        out.println("<input name='retailerZip' id='retailerZip' value='' type='number' /></p>");
        out.println("<p><label for='retailerCity'>Retailer City</label>");
        out.println("<input name='retailerCity' id='retailerCity' value='' type='text' /></p>");        
        out.println("<p><label for='retailerState'>RetailerState</label>");
        out.println("<input name='retailerState' id='retailerState' value='' type='text' /></p>");
       
        out.println("<p><label for='productOnSale'>productOnSale</label>");
        out.println("<select id='productOnSale' name='productOnSale'>");
        out.println("<option value='Yes'>Yes</option>"); 
        out.println("<option value='No'>No</option>");   
        out.println("</select>");

        out.println("<p><label for='manufacturerName'>Manufacturer Name</label>");
        out.println("<input name='manufacturerName' id='manufacturerName'  readonly  value='"+  request.getParameter("manufacturer")+ "' type='text' /></p>");
        out.println("<p><label for='manufacturerRebate'>Manufacturer Rebate</label>");
        out.println("<input name='manufacturerRebate' id='manufacturerRebate'  readonly  value='"+  request.getParameter("rebate")+ "' type='text' /></p>");
        /* Make it auto populate from session */
        out.println("<p><label for='userID'>User ID</label>");
        out.println("<input name='userID' id='userID'  readonly  value='"+  request.getParameter("userId")+ "' type='text' /></p>");
        out.println("<p><label for='userAge'>User Age</label>");
        out.println("<input name='userAge' id='userAge' value='' type='number' min='1' max='100' step='1' /></p>");

        out.println("<p><label for='userGender'>Gender</label>");
        out.println("<select id='userGender' name='userGender'>");
        out.println("<option value='Male'>Male</option>"); 
        out.println("<option value='Female'>Female</option>");   
        out.println("</select>");
        out.println("<p><label for='userOccupation'>User Occupation</label>");
        out.println("<input name='userOccupation' id='userOccupation' value='' type='text' /></p>");
        // out.println("<p><label for='reviewRating'>Review Rating</label>");
        // out.println("<input name='reviewRating' id='reviewRating' value='' type='number' min='1' max='5' step='1' /></p>");

        out.println("<p><label for='reviewRating'>reviewRating</label>");
        out.println("<select id='reviewRating' name='reviewRating'>");
        out.println("<option value='1'>1</option>"); 
        out.println("<option value='2'>2</option>");
        out.println("<option value='3'>3</option>"); 
        out.println("<option value='4'>4</option>");    
        out.println("<option value='5'>5</option>");   
        out.println("</select>");


        /* Make it auto populate with current date */
        out.println("<p><label for='reviewDate'>Review Date</label>");
        out.println("<input name='reviewDate' id='reviewDate' value='" + todayDate+ " '  readonly  type='text' /></p>");
        out.println("<p><label for='reviewText'>Review Text</label>");
        out.println("<textarea rows='4' cols='50' name='review'>");
        out.println("</textarea>");
        out.println("<input type='submit' value='Submit'/>");
        out.println("</form>");
        out.println("<fieldset>"); 
        out.println("</section>");
        utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html",response);
        utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html",response);
    }


}