package entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

@Entity
@Table(name = "logs")
@NamedQuery(name = "log.getAll", query = "SELECT c from Log c where c.task.taskname = :insertTask")
public class Log {

    private final IntegerProperty logtime = new SimpleIntegerProperty(this, "logtime");

    private final StringProperty logcoment = new SimpleStringProperty(this, "logcoment");

    private long id;

    private Task task;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    @Column(name = "logtime", length = 32)
    public Integer getLogtime() {
        return logtime.get();
    }

    @Column(name = "logcoment", length = 32)
    public String getLogcoment() {
        return logcoment.get();
    }

    @ManyToOne(targetEntity = Task.class)
    @JoinColumn(name = "taskname", nullable = false)
    public Task getTask() {
        return task;
    }

    public Log() {
    }

    public IntegerProperty logtimeProperty() {
        return logtime;
    }

    public void setLogtime(Integer logtime) {
        this.logtime.set(logtime);
    }

    public StringProperty logcomentProperty() {
        return logcoment;
    }

    public void setLogcoment(String logcoment) {
        this.logcoment.set(logcoment);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
