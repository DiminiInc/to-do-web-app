import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.util.Scanner;

/**
 * Created by admin on 14.04.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(80);
        ServletContextHandler h = new ServletContextHandler();
        h.setResourceBase("web");
        h.addServlet(DefaultServlet.class,"/");
        h.addServlet(TestServlet.class, "");
        h.addServlet(TestServlet.class, "/add");
        h.addServlet(TestServlet.class, "/remove");
        h.addServlet(TestServlet.class, "/view");
        h.addServlet(RestServlet.class,"/rest/*");
        server.setHandler(h);
        server.start();
    }
}