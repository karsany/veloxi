package hu.karsany.veloxi;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * User: fkarsany
 * Date: 2013.01.03.
 * Time: 13:34
 */
public class XMLParser {

    private File xmlFile;

    public XMLParser(File xmlFile) {
        this.xmlFile = xmlFile;
    }

    private boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        int strLen = str.trim().length();
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public Map<String, Object> parse() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        Map<String, Object> retv = new HashMap<String, Object>();
        retv.put(doc.getDocumentElement().getNodeName(), parseXml(doc.getDocumentElement()));
        return retv;
    }

    private Map<String, Object> parseXml(Element documentElement) {
        Set<String> tags = new HashSet<String>();
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            tags.add(childNodes.item(i).getNodeName());
        }
        Map<String, Object> retv = new HashMap<String, Object>();
        retv.putAll(parseAttributes(documentElement));
        for (String tag : tags) {
            NodeList elements = documentElement.getElementsByTagName(tag);
            if (elements.getLength() == 1 && !tag.toLowerCase().endsWith("s")) {
                if (isBlank(elements.item(0).getTextContent())) {
                    retv.put(tag, parseXml((Element) elements.item(0)));
                } else {
                    retv.put(tag, elements.item(0).getTextContent());
                }
            } else {
                String cTag = tag;
                if (cTag.toLowerCase().endsWith("s")) {
                    int len = cTag.length();
                    cTag = cTag.substring(0, len - 1);
                }
                retv.put(cTag, parseList((elements)));
            }
        }
        return retv;
    }

    private List<Object> parseList(NodeList elements) {
        List<Object> retv = new ArrayList<Object>();
        int size = elements.getLength();
        for (int i = 0; i < size; i++) {
            retv.add(parseXml((Element) elements.item(i)));
        }
        return retv;
    }

    private Map<String, String> parseAttributes(Element documentElement) {
        Map<String, String> retv = new HashMap<String, String>();
        NamedNodeMap attr = documentElement.getAttributes();
        int attrLength = attr.getLength();
        for (int i = 0; i < attrLength; i++) {
            retv.put(attr.item(i).getNodeName(), documentElement.getAttribute(attr.item(i).getNodeName()));
        }
        return retv;
    }

}
