import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class ViewReviewsServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//MongoDBDataStoreUtilities mongoUtils = new MongoDBDataStoreUtilities();
		HashMap<String, ArrayList<Reviews>> reviews = new HashMap<String, ArrayList<Reviews>>();

		String productIn = request.getParameter("model");

		Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO,
				"Logging from ViewReviewsServlet class" + productIn);

		reviews = MongoDBDataStoreUtilities.selectReview();

		// for (HashMap.Entry<String, ArrayList<Reviews>> myKey : reviews.entrySet()) {

		// 	String key = myKey.getKey();
		// 	// Product ph1 = myKey.getValue();

		// 	Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO,
		// 			"Logging from ViewReviewsServlet class keys available" + key);
		// }
		ArrayList<Reviews> reviewList = new ArrayList<Reviews>();

		boolean reviewFound = false;

		if (reviews.containsKey(productIn)) {
			Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO, "Success");
			reviewFound = true;

		}

		if (reviewFound) {
			for (HashMap.Entry<String, ArrayList<Reviews>> myKey : reviews.entrySet()) {

				String key = myKey.getKey();

				if (key != null && key.trim().equals(productIn.trim())) {
					reviewList = myKey.getValue();

					Logger.getLogger(WriteReviewsServlet.class.getName()).log(Level.INFO,
							"number of reviews = " + reviewList.size());
					break;
				}

			}
		}

		response.setContentType("text/html");
		Utilities utility = new Utilities();
		utility.printHtml("/Library/Tomcat/webapps/csj/Header.html", response);
		PrintWriter out = response.getWriter();
		out.println("<div id='body'>");
		out.println("<section id='content'>");
		out.println("<fieldset>");
		out.println("<h1>Product Reviews</h1>");

		for (Reviews review : reviewList) {
			//out.println("<h2>" + review.getReviewText() + "</h2>");

			out.println("<table id='productReview'  >");
		        out.println("<tbody>");
		            out.println("<tr>");
		                out.println("<td>Prpduct Model Name</td>");
		                out.println("<td>" + review.getProductModelName() + "</td>");
		            out.println("</tr>");
		            out.println("<tr>");
		                out.println("<td>Product Model category</td>");
		                out.println("<td>" + review.getProductCategory() + "</td>");
		            out.println("</tr>");
		            out.println("<tr>");
		                out.println("<td>Price</td>");
		                out.println("<td>" + review.getProductPrice() + "</td>");
		            out.println("</tr>");
		            out.println("<tr>");
		                out.println("<td>Retailer Name</td>");
		                out.println("<td>" + review.getRetaileName() + "</td>");
		            out.println("</tr>");
		             out.println("<tr>");
		                out.println("<td>City</td>");
		                out.println("<td>" + review.getRetailerCity() + "</td>");
		            out.println("</tr>");
		            out.println("<tr>");
		                out.println("<td>State</td>");
		                out.println("<td>" + review.getRetailerState() + "</td>");
		            out.println("</tr>");
		            out.println("<tr>");
		                out.println("<td>Zip code</td>");
		                out.println("<td>" + review.getRetailerZip() + "</td>");
		            out.println("</tr>");
		           
		            out.println("<tr>");
		                out.println("<td>Product On Sale</td>");
		                out.println("<td>" + review.getProductOnSale() + "</td>");
		            out.println("</tr>");
		            out.println("<tr>");
		                out.println("<td>Manufacturer Name</td>");
		                out.println("<td>" + review.getManufacturerName() + "</td>");
		            out.println("</tr>");
		            out.println("<tr>");
		                out.println("<td>Rebate</td>");
		                out.println("<td>" + review.getManufacturerRebate() + "</td>");
		            out.println("</tr>");
		            out.println("<tr>");
		                out.println("<td>UserName</td>");
		                out.println("<td>" + review.getUserId() + "</td>");
		            out.println("</tr>");
		            out.println("<tr>");
		                out.println("<td>User Age</td>");
		                out.println("<td>" + review.getUserAge() + "</td>");
		            out.println("</tr>");
		            out.println("<tr>");
		                out.println("<td>Gender</td>");
		                out.println("<td>" + review.getUserGender() + "</td>");
		            out.println("</tr>");
		            out.println("<tr>");
		                out.println("<td>Occupation</td>");
		                out.println("<td>" + review.getOccupation() + "</td>");
		            out.println("</tr>");
		            out.println("<tr>");
		                out.println("<td>Rating</td>");
		                out.println("<td>" + review.getRating() + "</td>");
		            out.println("</tr>");
		            out.println("<tr>");
		                out.println("<td>Review Date</td>");
		                out.println("<td>" + review.getReviewDate() + "</td>");
		            out.println("</tr>");
		            out.println("<tr>");
		                out.println("<td>Review Text</td>");
		                out.println("<td>" + review.getReviewText() + "</td>");
		            out.println("</tr>");
		            
	            out.println("</tbody>");
    		out.println("</table>");
		}

		out.println("<fieldset>");
		out.println("</section>");
		utility.printHtml("/Library/Tomcat/webapps/csj/LeftNavigationBar.html", response);
		utility.printHtml("/Library/Tomcat/webapps/csj/Footer.html", response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}