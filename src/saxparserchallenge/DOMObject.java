/*  Created by Connor Rowland
    Feb 22, 2018
*/

package saxparserchallenge;

import java.util.HashMap;

public class DOMObject {
    
    private HashMap<Integer, DomElement> elementMap;

    public DOMObject() {
        this.elementMap = new HashMap();
    }
    
    public void addElement (Integer key, DomElement value) {
        this.elementMap.put(key, value);
    }
    
    public HashMap<Integer, DomElement> getList() {
        return this.elementMap;
    }

}
