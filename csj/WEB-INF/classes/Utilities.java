import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Utilities extends HttpServlet {

	//private HttpServletResponse response;

	// public Utilities(HttpServletResponse response){
	// 	this.response = response;
	// }

	public String HtmlToString(String filePath) throws IOException{
		byte[] encoded  =Files.readAllBytes(Paths.get(filePath));
		return new String(encoded);	
	}

	public void printHtml(String file, HttpServletResponse response) throws IOException {

	String result = HtmlToString(file);
	PrintWriter pw = response.getWriter();
	pw.print(result);

	
		}

	public void printHeader(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

		HttpSession session = request.getSession(true);
		String userInfo = new String("UserInfo");
		Person person =  null;
		int cartItemCount=0;

		String fname="";

		if (null != session.getAttribute(userInfo)) {
			person = (Person) session.getAttribute(userInfo);
			fname=person.getFName();
		}

		Cart newCart = new Cart();
        ArrayList<Cart> cartList = new ArrayList<Cart>();

        String cartInSession = new String("Cart"); 
        if(null!=session.getAttribute(cartInSession)){
             cartList = (ArrayList<Cart>)session.getAttribute(cartInSession);
        }

        cartItemCount= cartList.size();
        PrintWriter out = response.getWriter();

		out.println("<div id='body'>");
		out.println("<section id='content'>");

		out.println("<article>");
		

		out.println("<nav>");
		out.println("<ul>");
		out.println("<li><p>Hello" + fname +"</p></li>");
		out.println("<li><p><a href='CartServlet'>Cart(" + cartItemCount+ ")</a></p></li>");
		out.println("</ul>");
		out.println("</nav>");

		out.println("<h1>Welcome to BestDeal website. Please pick a category to start shopping!</h1>");
		out.println("</article>");
		out.println("</section>");

		
	}
	}
