import java.util.*;
import java.io.*;
import java.util.logging.*;

public class SerializeDataStore {

	private String fileName;

	public SerializeDataStore() {

	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	static void writeCartDataStore(String filePath, ArrayList<Cart> cartList) {

		try {
			String filePathx = "../webapps/csj/Orders/Orders.txt";
			ArrayList<Cart> oldCartList = SerializeDataStore.readCartDataStore(filePath);

			for (Cart cart : cartList) {
				oldCartList.add(cart);
			}

			File outputFile = new File(filePath);
			FileOutputStream fos = new FileOutputStream(outputFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(oldCartList);
			oos.flush();
			oos.close();
			fos.close();

		} catch (Exception e) {
			Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, e.toString());
		}
	}

	static ArrayList<Cart> readCartDataStore(String filePath) {
		ArrayList<Cart> cartList = new ArrayList<Cart>();

		try {
			File cartDataStore = new File(filePath);
			FileInputStream fis = new FileInputStream(cartDataStore);
			ObjectInputStream ois = new ObjectInputStream(fis);

			// HashMap<String,Console>
			// mapInFile=(HashMap<String,Console>)ois.readObject();
			cartList = (ArrayList<Cart>) ois.readObject();
			ois.close();
			fis.close();

		} catch (Exception e) {
		}
		return cartList;
	}

	static void updateCartDataStore(int orderId){

		try {
			String filePath = "../webapps/csj/Orders/Orders.txt";
			ArrayList<Cart> oldCartList = SerializeDataStore.readCartDataStore(filePath);
			ArrayList<Cart> newCartList = new ArrayList<Cart>();

			for (Cart cart : oldCartList) {
				if(cart.getOrderId()==orderId)
				{
					Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "removing from cart");					
				}
				else
				{
					newCartList.add(cart);
				}
			}

			File outputFile = new File(filePath);
			FileOutputStream fos = new FileOutputStream(outputFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(newCartList);
			oos.flush();
			oos.close();
			fos.close();

		} catch (Exception e) {
			Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, e.toString());
		}

	}

	static void writePersonDataStore(ArrayList<Person> personList) {

		try {
			String personFilePath = "../webapps/csj/Files/Person.txt";
			ArrayList<Person> currentPersonList = SerializeDataStore.readPersonDataStore();

			for (Person person : personList) {
				currentPersonList.add(person);
			}

			File outputFile = new File(personFilePath);
			FileOutputStream fos = new FileOutputStream(outputFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(currentPersonList);
			oos.flush();
			oos.close();
			fos.close();

		} catch (Exception e) {
			Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, e.toString());
		}
	}

	static ArrayList<Person> readPersonDataStore() {
		ArrayList<Person> personList = new ArrayList<Person>();
		String personFilePath = "../webapps/csj/Files/Person.txt";

		try {
			File personDataStore = new File(personFilePath);
			FileInputStream fis = new FileInputStream(personDataStore);
			ObjectInputStream ois = new ObjectInputStream(fis);

			personList = (ArrayList<Person>) ois.readObject();
			ois.close();
			fis.close();

		} catch (Exception e) {
		}
		return personList;
	}

	static int generateNewOrderId() {
		int orderId = 0;
		try {
			String filePath = "../webapps/csj/Orders/Orders.txt";
			ArrayList<Cart> cartList = SerializeDataStore.readCartDataStore(filePath);
			for (Cart myCart : cartList) {
				orderId = myCart.getOrderId();
			}
		} catch (Exception e) {
			Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, e.toString());
		}
		Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "newly genetated order Ids" + orderId);

		return orderId + 1;
	}
}
