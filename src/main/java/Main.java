import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.util.Scanner;

/**
 * Created by admin on 14.04.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(80);
        ServletContextHandler h = new ServletContextHandler();
        h.addServlet(TestServlet.class, "/");
        server.setHandler(h);
        server.start();
    }
}