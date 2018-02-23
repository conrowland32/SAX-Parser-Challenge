/*  Created by Connor Rowland
    Feb 22, 2018
*/

package saxparserchallenge;

import java.util.HashMap;

public class DomElement {
    
    private String name = "";
    private HashMap<String, String> attributes;
    private String value = "";
    int order;
    int level;
    
    public DomElement(int order, int level) {
        this.attributes = new HashMap();
        this.order = order;
        this.level = level;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public HashMap getAttributes() {
        return this.attributes;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public int getOrder() {
        return this.order;
    }
    
    public int getLevel() {
        return this.level;
    }

}
