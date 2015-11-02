package controllers;


import entity.Log;
import entity.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogController {

    @FXML
    private Button okButtonLog;
    @FXML
    private Button cancelButtonLog;
    @FXML
    private TextField logTime;
    @FXML
    private TextField logComment;

    private Log log;

    public void setLog(Log log, Task task) {
        if (log == null) {
            return;
        }
        this.log = log;
        logTime.setText(Integer.toString(log.getLogtime()));
        logComment.setText(log.getLogcoment());
        log.setTask(task);

    }

    public Log getLog() {
        return log;
    }

    public void actionClose(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();

    }

    public void actionSave(ActionEvent actionEvent) {

        if (logTime.getText().trim().length()==0 || logComment.getText().trim().length()==0){
            return;
        }
        log.setLogtime(Integer.valueOf(logTime.getText()));
        log.setLogcoment(logComment.getText());

        actionClose(actionEvent);
    }

}
