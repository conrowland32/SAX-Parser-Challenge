/*  Created by Connor Rowland
    Feb 22, 2018
*/

package saxparserchallenge;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.control.TextArea;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DOMObjectXMLLoader {

    public static DOMObject load(File xmlFile, TextArea target) throws Exception {
        DOMObject dom = new DOMObject();
        ArrayList<DomElement> elementList = new ArrayList<>();
        try {
            
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler() {
                DomElement currentElement;
                int order = 0;
                int level = 0;
                
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    currentElement = new DomElement(order, level);
                    level++;
                    order++;
                    currentElement.setName(qName);
                    elementList.add(currentElement);
                    for(int j = 0; j < attributes.getLength(); j++) {
                        currentElement.addAttribute(attributes.getQName(j), attributes.getValue(j));
                    }
                }
                
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    dom.addElement(elementList.get(elementList.size()-1).getOrder(), elementList.get(elementList.size()-1));
                    elementList.remove(elementList.size()-1);
                    level--;
                }
                
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    elementList.get(elementList.size()-1).setValue((new String(ch, start, length)).replace("\r\n", " ").replace("\n", " "));
                }
            };
            
            saxParser.parse(xmlFile.getAbsoluteFile(), handler);
        } catch (Exception e) {
            throw e;
        }
        
        return dom;
    }
    
}
