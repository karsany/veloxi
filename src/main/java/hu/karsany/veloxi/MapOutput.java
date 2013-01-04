package hu.karsany.veloxi;

import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * User: fkarsany
 * Date: 2013.01.03.
 * Time: 15:48
 */
public class MapOutput {

    private Map<String, Object> map;
    private LinkedList<String> stack;

    public MapOutput(Map<String, Object> map) {
        this.map = map;
        stack = new LinkedList<String>();
        stack.clear();
    }

    private void printValue(Object o) {
        if (o instanceof Map) {
            printMap((Map<String, Object>) o);
        } else if (o instanceof List) {
            printList((List<Object>) o);
        } else if (o instanceof String) {

            LinkedList<String> rev = (LinkedList<String>) stack;
            Collections.reverse(rev);

            System.out.println(StringUtils.join(rev, ".") + " = " + o);
        }
    }

    private void printMap(Map<String, Object> m) {
        for (Map.Entry e : m.entrySet()) {
            stack.push((String) e.getKey());
            printValue(e.getValue());
            stack.pop();
        }
    }

    private void printList(List<Object> value) {
        int i = 0;
        for (Object o : value) {
            stack.push(String.valueOf(i));
            printValue(o);
            stack.pop();
            i++;
        }
    }

    public void print() {
        printMap(map);
    }
}
