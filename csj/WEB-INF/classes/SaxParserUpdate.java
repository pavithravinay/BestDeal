

import java.io.File;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.*;
import java.io.FileNotFoundException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.logging.*;

public class SaxParserUpdate{

   public static void writeToxml(ArrayList<Product> products){

      Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "Entering write method");
         
      try {

        String fullPath = "../webapps/csj/xml/ProductCatalog.xml";

        //Empty the file
        PrintWriter writer = new PrintWriter(new File(fullPath));
        writer.print("");
        writer.close();

        Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "Emptied xml file");

         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
          Document doc = docBuilder.newDocument();
           // write the content into xml file
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         DOMSource source = new DOMSource(doc);
         
         StreamResult result = new StreamResult(new File(fullPath));

         Element rootElement = doc.createElement("ProductCatalog");
         doc.appendChild(rootElement);

         for(Product product:products){
         // root elements
        Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, "writing product to new xml" + product.getModel());
         

         // staff elements
         Element staff = doc.createElement("product");
         rootElement.appendChild(staff);

         // set attribute to staff element
         Attr attr = doc.createAttribute("productId");
         attr.setValue(Integer.toString(product.getProductId()));
         staff.setAttributeNode(attr);

         // shorten way
         // staff.setAttribute("id", "1");

         // firstname elements
         Element firstname = doc.createElement("brandName");
         firstname.appendChild(doc.createTextNode(product.getBrandName()));
         staff.appendChild(firstname);

         // lastname elements
         Element lastname = doc.createElement("model");
         lastname.appendChild(doc.createTextNode(product.getModel()));
         staff.appendChild(lastname);

         // nickname elements
         Element nickname = doc.createElement("price");
         nickname.appendChild(doc.createTextNode(Double.toString(product.getPrice())));
         staff.appendChild(nickname);

         // salary elements
         Element salary = doc.createElement("discount");
         salary.appendChild(doc.createTextNode(Double.toString(product.getDiscount())));
         staff.appendChild(salary);

          // salary elements
         Element rebate = doc.createElement("rebate");
         rebate.appendChild(doc.createTextNode(Double.toString(product.getRebate())));
         staff.appendChild(rebate);

          // salary elements
         Element quantity = doc.createElement("quantity");
         quantity.appendChild(doc.createTextNode(Integer.toString(product.getQuantity())));
         staff.appendChild(quantity);

          // salary elements
         Element imageName = doc.createElement("imageName");
         imageName.appendChild(doc.createTextNode(product.getImageName()));
         staff.appendChild(imageName);

          // salary elements
         Element type = doc.createElement("type");
         type.appendChild(doc.createTextNode(product.getType()));
         staff.appendChild(type);


         transformer.transform(source, result);
         }
        

       
         

         // Output to console for testing
         // StreamResult result = new StreamResult(System.out);


         

      } catch (ParserConfigurationException pce) {
         pce.printStackTrace();
      } catch (TransformerException tfe) {
         tfe.printStackTrace();
      }
      catch (FileNotFoundException fe){
        fe.printStackTrace();
      }
      catch(Exception e){
        e.printStackTrace();
      }
   }
}
