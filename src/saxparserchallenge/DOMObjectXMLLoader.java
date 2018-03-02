/*  Created by Connor Rowland
    Feb 22, 2018
*/

package saxparserchallenge;

import java.io.File;
import java.util.Stack;
import javafx.scene.control.TextArea;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DOMObjectXMLLoader {
    static DOMObject root = null;
    public static DOMObject load(File xmlFile) throws Exception {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler() {
                Stack<DOMObject> stack = new Stack<>();
                DOMObject currentElement = null;
                
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    DOMObject newElement = new DOMObject(qName);
                    newElement.setDepth(stack.size());
                    stack.push(newElement);
                    for(int i = 0; i < attributes.getLength(); i++) {
                        newElement.addAttribute(attributes.getQName(i), attributes.getValue(i));
                    }
                    if(currentElement != null) {
                        currentElement.addChild(newElement);
                    }
                    currentElement = newElement;
                }
                
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if(stack != null) {
                        DOMObject finishedElement = stack.pop();
                        if(stack.isEmpty()) {
                            currentElement = null;
                            root = finishedElement;
                        } else {
                            currentElement = stack.lastElement();
                        }
                    }
                }
                
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    if(currentElement.getText() == null) {
                        currentElement.setText(String.copyValueOf(ch, start, length).trim().replace("\r\n", " ").replace("\n", " "));
                    } else {
                        currentElement.setText(currentElement.getText() + String.copyValueOf(ch, start, length).trim().replace("\r\n", " ").replace("\n", " "));
                    }
                }
            };
            
            saxParser.parse(xmlFile.getAbsoluteFile(), handler);
        } catch (Exception e) {
            throw e;
        }
        
        return root;
    }
    
}
