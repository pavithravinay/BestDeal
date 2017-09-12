
import java.util.*;
import java.util.logging.*;

public class Product{

    private int productId;
    private String brandName;
    private String model;
    private double price;
    private double discount;
    private double rebate;
    private int quantity;    
    private String imageName;
    private String type;

    public Product(){

    }

    public Product(int productId, String brandName, String model, double price, double discount, double rebate, int quantity, String imageName, String type) {
        
        this.productId = productId;
        this.brandName = brandName;
        this.model = model;
        this.price = price;
        this.discount = discount;
        this.rebate = rebate;
        this.quantity = quantity;
        this.imageName = imageName;
        this.type = type;

    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getRebate() {
        return rebate;
    }
    public void setRebate(double rebate) {
        this.rebate = rebate;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public  void addNewProduct(Product product){

        IMySqlDataStoreUtilities dataUtil = new MySqlDataStoreUtilities();
        dataUtil.addNewProduct(product);
   }

    public boolean updateProduct(Product product){

        IMySqlDataStoreUtilities dataUtil = new MySqlDataStoreUtilities();
        dataUtil.updateProduct(product);        
        return true;
    }

    public boolean deleteProduct(Product product){

        IMySqlDataStoreUtilities dataUtil = new MySqlDataStoreUtilities();
        dataUtil.deleteProduct(product);        
        return true;

    }

    public String toString(){
        return "productId: "+productId;
    }

}

