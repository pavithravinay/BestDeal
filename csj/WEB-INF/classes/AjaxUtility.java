import java.util.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.*;

public class AjaxUtility{

	public static HashMap<String,Product> getData(){
		HashMap<String,Product> productsMap = new HashMap<String,Product>();
		try
			{ 
				MySqlDataStoreUtilities utility = new MySqlDataStoreUtilities();				
				productsMap = utility.readProducts();
				Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "productMap count " + productsMap.size());
			}
			catch(Exception e){
				e.printStackTrace();
				Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "exception: "+ e.toString());

			}
		return productsMap;
		}

	public StringBuffer readData(String model) {
		HashMap<String,Product> data; 
		
		data = getData();
		StringBuffer sb = new StringBuffer();
		Iterator it = data.entrySet().iterator(); 
		while (it.hasNext())
		{
			Map.Entry entry = (Map.Entry)it.next();
			Product product = (Product)entry.getValue();
			Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "actual model : "+ product.getModel());
			if (product.getModel().toLowerCase().startsWith(model)) {
				sb.append("<product>");
				sb.append("<id>" + product.getProductId() + "</id>"); 
				sb.append("<brandName>" + product.getBrandName() + "</brandName>"); 
				sb.append("<model>" + product.getModel() + "</model>"); 
				sb.append("</product>");
			} 
		}
		return sb; 
	}
}