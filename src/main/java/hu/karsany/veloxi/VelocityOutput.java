package hu.karsany.veloxi;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.StringWriter;

/**
 * User: fkarsany
 * Date: 2013.01.03.
 * Time: 14:42
 */
public class VelocityOutput {

    private VelocityEngine velocityEngine = new VelocityEngine();
    private VelocityContext context = new VelocityContext();
    private Template template;
    private File templateFile;

    public VelocityOutput(File templateFile) {
        this.templateFile = templateFile;
        velocityEngine.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
        try {
            velocityEngine.setProperty("resource.loader", "file");
            velocityEngine.setProperty("file.resource.loader.path", "");
            velocityEngine.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
            velocityEngine.init();
            template = velocityEngine.getTemplate(templateFile.getPath());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("------------\n" + ex.getLocalizedMessage() + "\n-------------\n");
            System.exit(1);
        }
    }

    public void put(String k, Object v) {
        context.put(k, v);
    }

    public String generate() {
        StringWriter writer = new StringWriter();
        try {
            template.merge(context, writer);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("------------\n" + ex.getLocalizedMessage() + "-------------\n");
            System.exit(1);
        }

        return writer.toString();
    }

}
