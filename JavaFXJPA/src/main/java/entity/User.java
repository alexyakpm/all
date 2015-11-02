package entity;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import org.hibernate.property.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "alluser")
@NamedQuery(name = "user.getAll", query = "SELECT c from User c")
public class User {

    private final StringProperty name = new SimpleStringProperty(this, "name");

    private final StringProperty login = new SimpleStringProperty(this, "login");

    private final StringProperty password = new SimpleStringProperty(this, "password");

    private long id;

    private Set<Task> tasks;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    @Column(name = "name", length = 32)
    public String getName() {
        return name.get();
    }

    @Column(name = "login", length = 32)
    public String getLogin() {
        return login.get();
    }

    @Column(name = "password", length = 32)
    public String getPassword() {
        return password.get();
    }

    @OneToMany(orphanRemoval=true,cascade=CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "users",targetEntity = Task.class)
    public Set<Task> getTasks() {
        return tasks;
    }

    public User() {
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

}
