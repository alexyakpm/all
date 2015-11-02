package controllers;


import entity.Task;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TaskController {

    @FXML
    private Button okButtonTask;
    @FXML
    private Button cancelButtonTask;
    @FXML
    private TextField taskName;
    @FXML
    private TextField taskStatus;

    private Task task;
    private User user;

    public void setTask(Task task,User user) {
        if (task == null) {
            return;
        }
        this.task = task;
        taskName.setText(task.getTaskname());
        taskStatus.setText(task.getTaskstatus());
        task.setUsers(user);

    }

    public Task getTask() {
        return task;
    }

    public void actionClose(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();

    }


    public void actionSave(ActionEvent actionEvent) {

        if (taskName.getText().trim().length()==0 || taskStatus.getText().trim().length()==0){
            return;
        }
        task.setTaskname(taskName.getText());
        task.setTaskstatus(taskStatus.getText());

        actionClose(actionEvent);
    }
}
