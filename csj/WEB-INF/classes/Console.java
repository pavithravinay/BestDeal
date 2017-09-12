

import java.util.ArrayList;
import java.util.List;


public class Console {
    String retailer;
    String name;
    String id;
    String image;
    String condition;
    int price;
    List<String> accessories;
    public Console(){
        accessories=new ArrayList<String>();
    }

void setId(String id) {
    this.id = id;
}

void setRetailer(String retailer) {
    this.retailer = retailer;
}


void setImage(String image) {
    this.image = image;
}

void setCondition(String condition) {
    this.condition = condition;
}

void setPrice(int price) {
    this.price = price;
}

List getAccessories() {
    return accessories;
}


void setName(String name) {
    this.name = name;
}





}
