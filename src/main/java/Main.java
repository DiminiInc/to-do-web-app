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
        TodoList list = new TodoList();
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("\nTo-Do List v.1.1\nEnter \"1\" to add new task to your To-Do List.\nEnter \"2\" to delete task from your To-Do List.\nEnter \"3\" to view all To-Do list.");
            String cmd = s.nextLine();
            switch (cmd) {
                case "1":
                    System.out.println("Enter new task:");
                    String action = s.nextLine();
                    list.add(action);
                    System.out.println("Task was successfully added to your To-Do list!");
                    break;
                case "2":
                    System.out.println("Enter task id to delete");
                    int number = Integer.parseInt(s.nextLine());
                    if (list.delete(number) == true)
                        System.out.println("Task was successfully deleted from your To-Do list!");
                    else
                        System.out.println("Task with id " + number + " does not exist!");
                    break;
                case "3":
                    System.out.println("Your To-Do list:");
                    for (Task taskObject : list.view()) {
                        System.out.println("id: " + taskObject.getId() + "; task: " + taskObject.getTaskName());
                    }
                    break;
            }
        }
    }
}