
import java.io.*;
import java.util.*;

public class SalesManager extends Person{

	ArrayList<Product> productList = new ArrayList<Product>();

	
	public SalesManager(){

	}

	public SalesManager(String userId, String password, String fName, String lName, String email, String contactNumber, String role){
		super(userId, password, fName, lName, email, contactNumber, role);
	}

	public  boolean addProduct(Product product){

		SmartPhone phone = new SmartPhone();
		phone.addProduct(product);
		return true;
	}

	public boolean updateProduct(Product product){
		SmartPhone phone = new SmartPhone();
		phone.updateProduct(product);
		return true;
	}

	public boolean deleteProduct(Product product){

		SmartPhone phone = new SmartPhone();
		phone.deleteProduct(product);
		return true;
		}
		
	}
