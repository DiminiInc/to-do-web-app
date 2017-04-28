import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 14.04.2017.
 */
public class TodoList {
    private int id = -1;
    private final ArrayList<Task> todoList = new ArrayList();

    public void add(String taskName) throws SQLException {
        try(Connection c = DriverManager.getConnection("jdbc:h2:~/test")){
            try(PreparedStatement ps = c.prepareStatement("insert into todo (text) values (?)")){
                ps.setString(1,taskName);
                ps.executeUpdate();
            }
        }
//        id++;
//        todoList.add(new Task(id, taskName));
//        return id;
    }

    public boolean delete(int id) {
        for (Task taskObject : todoList) {
            if (taskObject.getId() == id) {
                todoList.remove(taskObject);
                return true;
            }
        }
        return false;
    }

    public List<Task> view() throws SQLException {
        List<Task> list =new ArrayList<>();
        try(Connection c = DriverManager.getConnection("jdbc:h2:~/test")){
            try(PreparedStatement ps = c.prepareStatement("select id,text from todo order by id")){
               try(ResultSet rs = ps.executeQuery()){
                   while(rs.next()){
                       int id=rs.getInt(1);
                       String text = rs.getString(2);
                       todoList.add(new Task(id,text));
                   }
               }
            }
        }
        return todoList;
    }
}