import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLreader {
	
	//I wrote some of this
	//https://stackoverflow.com/questions/3300839/get-a-nodes-inner-xml-as-string-in-java-dom
	//https://stackoverflow.com/questions/13295621/recursive-xml-parser
	//http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
	public static Tag realXMLrun(String pathName) {
	    try {

	    	File fXmlFile = new File(pathName);
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	    			
	    	//optional, but recommended
	    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	    	doc.getDocumentElement().normalize();

	    	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	    	Element m = doc.getDocumentElement();
	    	//System.out.println(m.getChildNodes().item(1).getNodeName());
	    	//return Tag
	    	return parse(doc, m);
	    	//elementReader(m);
	    	
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return null;
	}
	
	
	private static Tag parse(final Document doc, final Element e) {
		String temp2=null;
		if(!e.getFirstChild().getTextContent().trim().equals("")) {
			temp2 = e.getFirstChild().getTextContent().trim();
		}
		Tag temp = new Tag(e.getNodeName(),temp2);
		
        final NodeList children = e.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
            	
            		temp.addChild(                parse(doc, (Element) n));
            
            	
        		//System.out.println("M:"+n.getNodeName()+"\n"+n.getFirstChild().getTextContent().trim());

            }
        }
        return temp;
    }
	
	
}
