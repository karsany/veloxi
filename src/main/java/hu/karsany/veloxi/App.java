package hu.karsany.veloxi;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Hello world!
 */
public final class App {

    private App() {
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        if (args.length != 2) {
            System.err.println("usage: veloxi input.xml template.vm > output.ext");
            System.exit(1);
        }

        String xmlFileName = args[0];
        String velocityTemplateFileName = new File(args[1]).getAbsolutePath();

        File xmlFile = new File(xmlFileName);

        XMLParser parser = new XMLParser(xmlFile);

        Map<String, Object> map = parser.parse();
        VelocityOutput output = new VelocityOutput(new File(velocityTemplateFileName));

        //new MapOutput(map).print();

        for (Map.Entry e : map.entrySet()) {
            output.put((String) e.getKey(), e.getValue());
        }

        System.out.println(output.generate());


    }
}
