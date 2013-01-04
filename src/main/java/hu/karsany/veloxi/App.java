package hu.karsany.veloxi;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        File xmlFile = new File(args[0]);

        XMLParser parser = new XMLParser(xmlFile);

        Map<String, Object> map = parser.parse();
        VelocityOutput output = new VelocityOutput(new File(new File(args[1]).getAbsolutePath()));


        // new MapOutput(map).print();


        for (Map.Entry e : map.entrySet()) {
            output.put((String) e.getKey(), e.getValue());
        }

        System.out.println(output.generate());


    }
}
