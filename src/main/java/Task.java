/**
 * Created by admin on 14.04.2017.
 */
public class Task {
    private final int id;
    private final String taskName;

    public Task(int id, String taskName) {
        this.id = id;
        this.taskName = taskName;
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName == null ? "" : taskName;
    }
}