import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.logging.*;
// import java.util.*;

public class AutoCompleteServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String model = request.getParameter("searchId");
		String action = request.getParameter("action");	

	    try {
			StringBuffer sb = new StringBuffer(); 
			boolean namesAdded = false;
			if (action.equals("complete")) 
			{
				if (!model.equals(""))
				{
					AjaxUtility util = new AjaxUtility();
					Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "before calling readData: ");
					sb = util.readData(model);
					Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "after calling readData: "+sb);


					if(sb!=null || !sb.equals(""))
					{
						namesAdded=true;
					}
					if (namesAdded)
					{
						response.setContentType("text/xml");
						response.getWriter().write("<products>" + sb.toString() + "</products >"); 
					}
				}
		        
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
