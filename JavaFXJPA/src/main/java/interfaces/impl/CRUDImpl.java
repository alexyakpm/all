package interfaces.impl;

import entity.Log;
import entity.Task;
import entity.User;
import interfaces.CRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CRUDImpl implements CRUD{

    public EntityManager em = Persistence.createEntityManagerFactory("ALEX").createEntityManager();

    private ObservableList<User> userList = FXCollections.observableArrayList();

    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    private ObservableList<Log> logList = FXCollections.observableArrayList();

    @Override
    public void add(User user) {

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

    }

    @Override
    public void update(User user) {

        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();

    }

    @Override
    public void delete(User user) {

        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();

    }

    @Override
    public void addTask(Task task) {

        em.getTransaction().begin();
        em.persist(task);
        em.getTransaction().commit();

    }

    @Override
    public void updateTask(Task task) {

        em.getTransaction().begin();
        em.merge(task);
        em.getTransaction().commit();

    }

    @Override
    public void deleteTask(Task task) {

        em.getTransaction().begin();
        em.remove(task);
        em.getTransaction().commit();

    }

    @Override
    public void addLog(Log log) {

        em.getTransaction().begin();
        em.persist(log);
        em.getTransaction().commit();

    }

    @Override
    public void updateLog(Log log) {

        em.getTransaction().begin();
        em.merge(log);
        em.getTransaction().commit();

    }

    @Override
    public void deleteLog(Log log) {

        em.getTransaction().begin();
        em.remove(log);
        em.getTransaction().commit();

    }

    @Override
    public List<User> getAll() {

        TypedQuery<User> namedQuery = em.createNamedQuery("user.getAll", User.class);
        return namedQuery.getResultList();

    }

    @Override
    public List<Log> getLog() {

        TypedQuery<Log> namedQuery = em.createNamedQuery("log.getAll", Log.class);
        return namedQuery.getResultList();

    }

    @Override
    public ObservableList<User> getContactList() {

        if(!userList.isEmpty())
            userList.clear();
        userList = FXCollections.observableList((List<User>)getAll());
        return userList;

    }

    @Override
    public List<Task> getTask() {

        TypedQuery<Task> namedQuery = em.createNamedQuery("task.getAll", Task.class);
        return namedQuery.getResultList();

    }

    @Override
    public ObservableList<Task> getTaskList(String a) {

        TypedQuery<Task> query = em.createNamedQuery("task.getAll", Task.class) ;
        query.setParameter("insertUser", a);

        if(!taskList.isEmpty())
            taskList.clear();
        taskList = FXCollections.observableList(query.getResultList());
        return taskList;

    }

    @Override
    public ObservableList<Log> getLogList(String a) {

        TypedQuery<Log> query = em.createNamedQuery("log.getAll", Log.class) ;
        query.setParameter("insertTask", a);

        if(!logList.isEmpty())
            logList.clear();
        logList = FXCollections.observableList(query.getResultList());
        return logList;

    }

}
