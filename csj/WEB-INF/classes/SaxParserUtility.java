
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a �de facto� standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 



The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/





import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.util.logging.*;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserUtility extends DefaultHandler {
    Product product;
    // Console console;
    ArrayList<Product> products;
    // List<Console> consoles;
    String productXmlFileName;
    String elementValueRead;


    
    public SaxParserUtility(String productXmlFileName) {
        this.productXmlFileName = productXmlFileName;
        products = new ArrayList<Product>();
        
        // prettyPrint();
    }


    public ArrayList<Product> parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(productXmlFileName, this);

        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
            System.out.println(e);
            Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, e.toString());
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
            System.out.println(e);
             Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, e.toString());
        } catch (IOException e) {
            System.out.println("IO error");
            System.out.println(e);
             Logger.getLogger(SaxParserUtility.class.getName()).log(Level.INFO, e.toString());
        }

        return products;
    }


    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("product")) {
            product = new Product();
            product.setProductId(Integer.parseInt(attributes.getValue("productId")));            
        }

    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("product")) {
            products.add(product);
	    return;
        }
        if (element.equalsIgnoreCase("brandName")) {
            product.setBrandName(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("model")) {
            product.setModel(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("price")) {
            product.setPrice(Double.parseDouble(elementValueRead));
        return;
        }
        
        if(element.equalsIgnoreCase("discount")){
            product.setDiscount(Double.parseDouble(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("rebate")){
            product.setRebate(Double.parseDouble(elementValueRead));
        return;
        }
        if(element.equalsIgnoreCase("quantity")){
            product.setQuantity(Integer.parseInt(elementValueRead));
        return;
        }
        if(element.equalsIgnoreCase("imageName")){
            product.setImageName(elementValueRead);
        return;
        }
        if(element.equalsIgnoreCase("type")){
            product.setType(elementValueRead);
        return;
        }

    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }

}
