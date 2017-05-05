import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.security.auth.login.AppConfigurationEntry;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaskovich Dmitry on 14/04/2017.
 */
public class TestServlet extends HttpServlet {
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);

    {
        try {
            cfg.setTemplateLoader(new FileTemplateLoader(new File(".")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private TodoList list = new TodoList();

    {
        //list.add("Example task");
    }
//    {list.add("Example task");
//        try{
//            cfg.setTemplateLoader(new FileTemplateLoader(new File(".")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.endsWith(".css")) {
            Files.copy(Paths.get("." + uri), resp.getOutputStream());
            return;
        }
        //Template t=cfg.getTemplate("todo.html");
//        StringBuilder buf = new StringBuilder();
//        List<Task> tasks = list.view();
//        for (Task t : tasks) {
//            buf.append("<li>" + t.getTaskName() + "</li>\n");
//        }
        resp.setCharacterEncoding("UTF-8");
//        resp.getWriter().write("<html>\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <title>To-Do List</title>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<form method=\"post\" action=\"add\">\n" +
//                "    Add task:<input name=\"task\">\n" +
//                "    <input type=\"submit\" value=\"add\">\n" +
//                "</form>\n" +
//                "<form method=\"post\" action=\"remove\">\n" +
//                "    Remove task:<input name=\"taskID\">\n" +
//                "    <input type=\"submit\" value=\"remove\">\n" +
//                "</form>\n" +
//                "<ol>\n" +
//                buf +
//                "</ol>\n" +
//                "</body>\n" +
//                "</html>");
        try {
            Template t = cfg.getTemplate("todo.html");
            Map<String, Object> map = new HashMap<>();
            try {
                map.put("tasks", list.view());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            t.process(map, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
            resp.sendError(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String what = req.getParameter("task");
        String whatID = req.getParameter("taskID");
        String whatRemove = req.getParameter("taskRemove");
        if (what != null && what.length() > 0)
            try {
                list.add(what);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (whatID != null && whatID.length() > 0)
            try {
                list.delete(Integer.parseInt(whatID));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (whatRemove != null)
        {
            try {
                whatRemove=whatRemove.replace("remove?id=","");
                list.delete(Integer.parseInt(whatRemove));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/");
    }
}