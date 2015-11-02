package entity;

import javafx.beans.property.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "alltask")
@NamedQuery(name = "task.getAll", query = "SELECT c from Task c WHERE c.users.name = :insertUser")
public class Task {

    private final StringProperty taskname = new SimpleStringProperty(this, "taskname");

    private final StringProperty taskstatus = new SimpleStringProperty(this, "taskstatus");

    private long id;

    private Set<Log> logs;

    private User users;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    @Column(name = "taskname", length = 32)
    public String getTaskname() {
        return taskname.get();
    }

    @Column(name = "taskstatus", length = 32)
    public String getTaskstatus() {
        return taskstatus.get();
    }

    @OneToMany(orphanRemoval=true, cascade= CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "task",targetEntity = Log.class)
    public Set<Log> getLogs() {
        return logs;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "name", nullable = false)
    public User getUsers() {
        return users;
    }

    public Task() {
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public StringProperty taskstatusProperty() {
        return taskstatus;
    }

    public void setTaskstatus(String taskstatus) {
        this.taskstatus.set(taskstatus);
    }

    public StringProperty tasknameProperty() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname.set(taskname);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

}
