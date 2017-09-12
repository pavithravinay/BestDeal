
import java.util.*;

public interface IMySqlDataStoreUtilities {

	public Boolean addPerson(Person person);
	public ArrayList<Person> readPerson();
	public int generateNewOrderId();
	public Boolean insertOrder(ArrayList<Cart> cart);
	public void updateOrder(int order_id, int product_id);
	public Boolean deleteOrder(int order_Id);	
	public ArrayList<Cart> readAllOrders();
	public Boolean insertProducts(HashMap<String, Product> productList);
	public HashMap<String,Product> readProducts();
	// public int generateNewProductId();
	public Boolean addNewProduct(Product product);
	public Boolean updateProduct(Product product);
	public Boolean deleteProduct(Product product);  
	public Boolean resetProducts();

}
