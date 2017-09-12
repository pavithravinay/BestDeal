import java.util.*;
import java.util.logging.*;

public class Accessory{

    private int productId;
    private String brandName;
    private String model;
    private double price;
    private double discount;
    private double rebate;
    private int quantity;    
    private String imageName;
    private String type;
    private int parentProductId;

    public Accessory(){

    }

    public Accessory(int productId, int parentProductId, String brandName, String model, double price, double discount, double rebate, int quantity, String imageName, String type) {
        
        this.productId = productId;
        this.brandName = brandName;
        this.model = model;
        this.price = price;
        this.discount = discount;
        this.rebate = rebate;
        this.quantity = quantity;
        this.imageName = imageName;
        this.type = type;
        this.parentProductId = parentProductId;

    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getParentProductId() {
        return parentProductId;
    }
    public void setParentProductId(int parentProductId) {
        this.parentProductId = parentProductId;
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
        String fullPath = "../webapps/csj/xml/ProductCatalog.xml";

        SaxParserUtility utility = new SaxParserUtility(fullPath);

        ArrayList<Product> products = utility.parseDocument();       

        products.add(product);

        SaxParserUpdate.writeToxml(products);
    }

    public boolean updateProduct(Product product){
        String fullPath = "../webapps/csj/xml/ProductCatalog.xml";

        SaxParserUtility utility = new SaxParserUtility(fullPath);

        ArrayList<Product> products = utility.parseDocument();

        ArrayList<Product> newProducts = new ArrayList<Product>();

        for(Product productOld:products){
            if(productOld.getProductId()!=product.getProductId())
            {
                newProducts.add(productOld);
            }
        }

        newProducts.add(product);

        SaxParserUpdate.writeToxml(newProducts);
        return true;
    }

    public boolean deleteProduct(Product product){
        String fullPath = "../webapps/csj/xml/ProductCatalog.xml";

        SaxParserUtility utility = new SaxParserUtility(fullPath);

        ArrayList<Product> products = utility.parseDocument();

        ArrayList<Product> newProducts = new ArrayList<Product>();

        for(Product productOld:products){
            if(productOld.getProductId()!=product.getProductId())
            {
                newProducts.add(productOld);
            }
        }

        SaxParserUpdate.writeToxml(newProducts);
        return true;
    }

}

