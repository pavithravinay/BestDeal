

import java.util.HashMap;
import java.util.*;
import java.util.logging.*;

public class SmartPhone extends Product implements java.io.Serializable{

	private HashMap<Integer,Product> products = new HashMap<Integer,Product>();

	public SmartPhone(){

	}

	public SmartPhone(int productId, String brandName, String model, double price, double discount, double rebate, int quantity, String imageName, String type) {
		super(productId, brandName, model, price, discount, rebate, quantity, imageName, type);	
	}


	public boolean addProduct(Product product){
		super.addNewProduct(product);
		return true;		
	}

	public boolean updateProduct(Product product){
		super.updateProduct(product);
		return true;

	}

	public boolean deleteProduct(Product product){
		super.deleteProduct(product);
		return true;
	}


	public static HashMap<String,Product> buildBasicSmartPhoneList(){
		
		IMySqlDataStoreUtilities dataUtil = new MySqlDataStoreUtilities();
		HashMap<String,Product> productMap = new HashMap<>();
		productMap = dataUtil.readProducts();
		Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.INFO, "read successful "+productMap);
		String fullPath = "../webapps/jsp/xml/ProductCatalog.xml";
		SaxParserUtility utility = new SaxParserUtility(fullPath);
		ArrayList<Product> productList = new ArrayList<Product>();
		boolean val = productMap!=null?productMap.isEmpty():false;
		Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.INFO, "is HahMap empty: "+val);

		if(val){
			productList = utility.parseDocument();
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.INFO, "parsed into AL");			
			for(Product product:productList){
				productMap.put(product.getModel(), product);
				Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.INFO, "copied to HM "+product.toString());
			}
			dataUtil.insertProducts(productMap);
			productMap.clear();
			productMap = dataUtil.readProducts();
			return productMap;
		}
		else{
			return productMap;
		}
		
	}

	public static HashMap<String,Accessory> buildBasicAccessoryList(){

		String fullPath = "../webapps/jsp/xml/Accessory.xml";
		AccessoryParserUtility utility = new AccessoryParserUtility(fullPath);
		ArrayList<Accessory> smartPhoneList = new ArrayList<Accessory>();
		smartPhoneList = utility.parseDocument();

		HashMap<String,Accessory> smartPhones = new HashMap<String,Accessory>();

		for(Accessory product:smartPhoneList){
			smartPhones.put(product.getModel(), product);
		}

		return smartPhones;

		
	}
}
