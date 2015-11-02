package interfaces;

import entity.Log;
import entity.Task;
import entity.User;
import javafx.collections.ObservableList;

import java.util.List;

public interface CRUD {

   void add(User user);

   void update (User user);

   void delete (User user);

   void addTask(Task task);

   void updateTask (Task task);

   void deleteTask (Task task);

   void addLog(Log log);

   void updateLog (Log log);

   void deleteLog (Log log);

   List<User> getAll ();

   ObservableList<User> getContactList();

   List<Task> getTask();

   ObservableList<Task> getTaskList(String a);

   List<Log> getLog();

   ObservableList<Log> getLogList(String a);

}
