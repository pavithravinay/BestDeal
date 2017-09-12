
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.*;
import java.util.*;
import java.sql.ResultSet;

public class MySqlDataStoreUtilities implements IMySqlDataStoreUtilities {

	private Connection conn = null;
	private PreparedStatement pst = null;

	public void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDeal", "root", "root");
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}
	}

	public Boolean addPerson(Person person) {
		Boolean addSuccess = false;
		try {
			getConnection();

			String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(firstName,lastName,email,contactNumber,userName,password,role) "
					+ "VALUES (?,?,?,?,?,?,?);";
			pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
			pst.setString(1, person.getFName());
			pst.setString(2, person.getlName());
			pst.setString(3, person.getEmail());
			pst.setString(4, person.getContactNumber());
			pst.setString(5, person.getUserId());
			pst.setString(6, person.getPassword());
			pst.setString(7, person.getRole());
			addSuccess = pst.execute();

			pst.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return addSuccess;
	}

	public ArrayList<Person> readPerson() {
		ArrayList<Person> personList = null;
		java.sql.Statement stm = null;
		try {
			getConnection();

			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String getpersonData = "SELECT userName, password, firstName, lastName, email, contactNumber, role FROM Registration";

			ResultSet rst;
			rst = stm.executeQuery(getpersonData);
			personList = new ArrayList<>();
			while (rst.next()) {
				Person person = new Person(rst.getString("userName"), rst.getString("password"),
						rst.getString("firstName"), rst.getString("lastName"), rst.getString("email"),
						rst.getString("contactNumber"), rst.getString("role"));
				Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE,
						person.toString() + "read from db");
				personList.add(person);
			}

			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return personList;
	}

	public Boolean insertOrder(ArrayList<Cart> cartList) {
		Boolean insertSuccess = false;
		try {
			getConnection();

			String insertIntoCustomerOrdersQuery = "INSERT INTO CustomerOrders(orderId,brandName,model,price,userId,buyWarranty,deliveryDateTime, productId) "
					+ "VALUES (?,?,?,?,?,?,?,?);";

			pst = conn.prepareStatement(insertIntoCustomerOrdersQuery);
			for (Cart cart : cartList) {
				pst.setInt(1, cart.getOrderId());
				pst.setString(2, cart.getBrandName());
				pst.setString(3, cart.getModel());
				pst.setDouble(4, cart.getPrice());
				pst.setString(5, cart.getUserId());
				pst.setBoolean(6, cart.isBuyWarranty());
				pst.setDate(7, new java.sql.Date(cart.getDeliveryDate().getTime()));
				pst.setInt(8, cart.getProductId());
				pst.execute();
				insertSuccess = true;
			}

			pst.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return insertSuccess;

	}

	public int generateNewOrderId() {
		java.sql.Statement stm = null;
		int orderId;
		int newOrderId = 0;
		try {
			getConnection();

			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String getMaxOrderId = "SELECT MAX(orderId) AS newId FROM CustomerOrders;";

			ResultSet rst;
			rst = stm.executeQuery(getMaxOrderId);
			if (!rst.next()) {
				orderId = 0;
			} else {
				orderId = rst.getInt("newId");
			}

			newOrderId = orderId + 1;

			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}
		Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, newOrderId + " returned value");

		return newOrderId;

	}

	public Boolean deleteOrder(int order_id) {
		Boolean deleteSuccess = true;

		java.sql.Statement stm = null;
		try {
			getConnection();

			String deleteOrderQuery = "Delete from CustomerOrders where orderId =" + order_id;

			stm = conn.createStatement();
			 stm.execute(deleteOrderQuery);
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE,
			deleteSuccess+" deleted from db");
			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
			deleteSuccess=false;
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return deleteSuccess;
	}

	public ArrayList<Cart> readAllOrders() {
		ArrayList<Cart> cartList = null;
		java.sql.Statement stm = null;
		try {
			getConnection();

			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String getOrderData = "SELECT orderId, brandName, model, price, userId, buyWarranty, deliveryDateTime,productId FROM CustomerOrders";

			ResultSet rst;
			rst = stm.executeQuery(getOrderData);
			cartList = new ArrayList<>();
			while (rst.next()) {
				Cart cart = new Cart(rst.getString("brandName"), rst.getString("model"), rst.getDouble("price"),
						rst.getInt("orderId"), rst.getString("userId"), rst.getBoolean("buyWarranty"),
						rst.getDate("deliveryDateTime"), rst.getInt("productId"));
				Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE,
						cart.toString() + "read from db");
				cartList.add(cart);
			}

			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return cartList;
	}

	public void updateOrder(int order_id, int product_id) {

		java.sql.Statement stm = null;
		try {
			getConnection();

			String updateOrderQuery = "Delete from CustomerOrders where orderId =" + order_id + " and productId ="
					+ product_id ;

			stm = conn.createStatement();
			stm.execute(updateOrderQuery);
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE,
					" deleted item from an order - db");
			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

	}

	public Boolean insertProducts(HashMap<String, Product> productList){

		Boolean insertSuccess = false;
		try {
			getConnection();

			String insertIntoProductsQuery = "INSERT INTO Products(productId, brandName, model, price, discount, rebate, quantity, imageName, category)"
					+ "VALUES (?,?,?,?,?,?,?,?,?);";

			pst = conn.prepareStatement(insertIntoProductsQuery);

			for(HashMap.Entry<String, Product> myKey: productList.entrySet()){
	            String key = myKey.getKey();
	            Product product = myKey.getValue();
	            pst.setInt(1, product.getProductId());
	            pst.setString(2, product.getBrandName());
	            pst.setString(3, product.getModel());
	            pst.setDouble(4, product.getPrice());
	            pst.setDouble(5, product.getDiscount());
	            pst.setDouble(6, product.getRebate());
	            pst.setInt(7, product.getQuantity());
	            pst.setString(8, product.getImageName());
	            pst.setString(9, product.getType());
	            pst.execute();
				insertSuccess = true;
				Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.INFO, "insert successful: "+key);
	        }  

			pst.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return insertSuccess;
	}


	public HashMap<String,Product> readProducts(){
		HashMap<String, Product> productList = new HashMap<>();
		java.sql.Statement stm = null;
		try {
			getConnection();

			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String getProductData = "SELECT productId, brandName, model, price, discount, rebate, quantity,imageName, category FROM Products";

			ResultSet rst;
			rst = stm.executeQuery(getProductData);
			productList = new HashMap<>();
			while (rst.next()) {
				Product product = new Product(rst.getInt("productId"),rst.getString("brandName"), rst.getString("model"), rst.getDouble("price"), rst.getDouble("discount"), rst.getDouble("rebate"), rst.getInt("quantity"), 
												rst.getString("imageName"), rst.getString("category"));
				Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE,
						product.toString() + "read from db");
				productList.put(product.getModel(), product);
			}

			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage()+"error while reading from db",
					e.getStackTrace());
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return productList;
	}


	// public int generateNewProductId() {
	// 	java.sql.Statement stm = null;
	// 	int productId;
	// 	int newProductId = 0;
	// 	try {
	// 		getConnection();

	// 		stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	// 		String getMaxProductId = "SELECT MAX(productId) AS newId FROM Products;";

	// 		ResultSet rst;
	// 		rst = stm.executeQuery(getMaxProductId);
	// 		if (!rst.next()) {
	// 			productId = 0;
	// 		} else {
	// 			productId = rst.getInt("newId");
	// 		}

	// 		newProductId = productId + 1;

	// 		stm.close();
	// 		conn.close();
	// 	} catch (Exception e) {
	// 		Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
	// 				e.getStackTrace());
	// 	}

	// 	finally {
	// 		if (stm != null) {
	// 			try {
	// 				stm.close();
	// 			} catch (SQLException se) {
	// 				Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
	// 						se.getStackTrace());
	// 			}
	// 		}

	// 		if (conn != null) {
	// 			try {
	// 				conn.close();
	// 			} catch (SQLException se) {
	// 				Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
	// 						se.getStackTrace());
	// 			}
	// 		}
	// 	}
	// 	Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, newProductId + " returned value");

	// 	return newProductId;
	// }


	public Boolean addNewProduct(Product product) {
		Boolean addSuccess = false;
		try {
			getConnection();

			String insertIntoProductsQuery = "INSERT INTO Products(productId, brandName, model, price, discount, rebate, quantity, imageName, category) "
					+ "VALUES (?,?,?,?,?,?,?,?,?);";
			pst = conn.prepareStatement(insertIntoProductsQuery);
			pst.setInt(1, product.getProductId());
			pst.setString(2, product.getBrandName());
			pst.setString(3, product.getModel());
			pst.setDouble(4, product.getPrice());
			pst.setDouble(5, product.getDiscount());
			pst.setDouble(6, product.getRebate());
			pst.setInt(7, product.getQuantity());
			pst.setString(8, product.getImageName());
			pst.setString(9, product.getType());

			addSuccess = pst.execute();

			pst.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return addSuccess;
	}


	public Boolean updateProduct(Product product) {
		Boolean updateSuccess = false;
		try {
			getConnection();

			String updateProductsQuery = "UPDATE Products SET model = '"+product.getModel()+"', price = "+
			product.getPrice()+", discount = " +product.getDiscount()+", rebate = "+product.getRebate()+", quantity = "+
			product.getQuantity()+", imageName= '"+product.getImageName()+"', category = '"+
			product.getType()+"' WHERE productId = "+product.getProductId() ;
			java.sql.Statement stmt = null;
			stmt = conn.createStatement();
			stmt.executeUpdate(updateProductsQuery);			
			updateSuccess = true;
			stmt.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
		}

		finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return updateSuccess;
	}

	public Boolean deleteProduct(Product product) {
		Boolean deleteSuccess = true;

		java.sql.Statement stm = null;
		try {
			getConnection();

			String deleteProductQuery = "Delete from Products where productId =" + product.getProductId();

			stm = conn.createStatement();
			 stm.execute(deleteProductQuery);
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE,
			deleteSuccess+" deleted from db");
			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
			deleteSuccess=false;
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return deleteSuccess;
	}

	public Boolean resetProducts() {
		Boolean resetSuccess = true;

		java.sql.Statement stm = null;
		try {
			getConnection();

			String resetProductQuery = "Delete from Products";

			stm = conn.createStatement();
			stm.execute(resetProductQuery);
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE,
			resetSuccess+" deleted from db");
			stm.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, e.getMessage(),
					e.getStackTrace());
			resetSuccess=false;
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					Logger.getLogger(MySqlDataStoreUtilities.class.getName()).log(Level.SEVERE, se.getMessage(),
							se.getStackTrace());
				}
			}
		}

		return resetSuccess;
	}




}
