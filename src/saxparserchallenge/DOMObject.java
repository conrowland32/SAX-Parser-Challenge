/*  Created by Connor Rowland
    Feb 22, 2018
*/

package saxparserchallenge;

import java.util.ArrayList;
import java.util.HashMap;

public class DOMObject {
    
    private String name;
    private String text = null;
    private int depth;
    private HashMap<String, String> attributes = new HashMap<>();
    private ArrayList<DOMObject> children = new ArrayList<>();
    
    public DOMObject(String name) {
        this.name = name;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void addAttribute(String name, String value) {
        attributes.put(name, value);
    }
    
    public void addChild(DOMObject newChild) {
        this.children.add(newChild);
    }
    
    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getText() {
        return this.text;
    }
    
    public HashMap<String, String> getAttributes() {
        return this.attributes;
    }
    
    public ArrayList<DOMObject> getChildren() {
        return this.children;
    }
    
    public int getDepth() {
        return this.depth;
    }
    
}
