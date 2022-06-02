package longtv.listener;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import longtv.utils.XMLHelper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Web application lifecycle listener.
 *
 * @author Admin
 */
public class MyContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying..........."); //To change body of generated methods, choose Tools | Templates.
        //1. Get DB
        ServletContext context = sce.getServletContext();
        String xml_File_Path = context.getInitParameter("STUDENT_DB_FILE");
        String webApp_Path = context.getRealPath("/");
        String xml_File = webApp_Path + xml_File_Path;
        System.out.println("XML File: " + xml_File);
        //XML had already loaded.

        try {
            //2. Parse DOM TREE
            Document document = XMLHelper.buildDOMFromFile(xml_File);

            if(document != null) {
                context.setAttribute("DOM_TREE", document);
            }
            
            //3. Store DOM TREE for application.
        } catch (ParserConfigurationException e) {
            context.log("Parser: " + e.getMessage());
        } catch (SAXException e) {
            context.log("SAX: " + e.getMessage());
        } catch (IOException e) {
            context.log("IOException: " + e.getMessage());
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Undeploying.............");
    }
}
