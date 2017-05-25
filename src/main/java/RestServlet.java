import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Yaskovich Dmitry on 14/04/2017.
 */
public class RestServlet extends HttpServlet {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private TodoList list = new TodoList();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        String uri = req.getRequestURI();
        if (uri.startsWith("/rest/add")) {
            String text=req.getParameter("text");
            try {
                list.add(text);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                gson.toJson(list.view(),resp.getWriter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (uri.startsWith("/rest/delete")) {
            int id=Integer.parseInt(req.getParameter("id"));
            try {
                list.delete(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (uri.startsWith("/rest/view")){
            try {
                gson.toJson(list.view(),resp.getWriter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}