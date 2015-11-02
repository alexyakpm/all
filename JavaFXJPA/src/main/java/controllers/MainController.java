package controllers;


import entity.Log;
import entity.Task;
import entity.User;
import interfaces.impl.CRUDImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class MainController {

    private CRUDImpl crud = new CRUDImpl();

    private Object fxmlEdit;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private Object fxmlEditTask;

    private FXMLLoader fxmlLoaderTask = new FXMLLoader();

    private Object fxmlEditLog;

    private FXMLLoader fxmlLoaderLog = new FXMLLoader();

    private Stage mainStage;

    private Stage editDialogStage;

    private Stage taskDialogStage;

    private Stage logDialogStage;

    private EditController editController;

    private LogController logController;

    private TaskController taskController;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button dellButton;

    @FXML
    private Button addTask;
    @FXML
    private Button editTask;
    @FXML
    private Button dellTask;

    @FXML
    private Button addLog;
    @FXML
    private Button editLog;
    @FXML
    private Button dellLog;

    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, String> columnFIO;
    @FXML
    private TableColumn<User, String> login;
    @FXML
    private TableColumn<User, String> password;

    @FXML
    private TableView<Task> tableTask;
    @FXML
    private TableColumn<Task, String> taskName;
    @FXML
    private TableColumn<Task, String> taskStatus;

    @FXML
    private TableView<Log> tableLog;
    @FXML
    private TableColumn<Log, String> logComents;
    @FXML
    private TableColumn<Log, Integer> logTime;

    @FXML
    private void initialize() {

        showUser();
        taskAll();
        initLoader();
        initLoaderTask();
        initLoaderLog();

    }

    private void showUser(){

        columnFIO.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        login.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        tableUser.setItems(crud.getContactList());

    }

    private void showTask(){
        taskName.setCellValueFactory(new PropertyValueFactory<Task, String>("taskname"));
        taskStatus.setCellValueFactory(new PropertyValueFactory<Task, String>("taskstatus"));
        User a = tableUser.getSelectionModel().getSelectedItem();
        tableTask.setItems(crud.getTaskList(a.getName()));
        logAll();
    }

    private void showLog(){

        logComents.setCellValueFactory(new PropertyValueFactory<Log, String>("logcoment"));
        logTime.setCellValueFactory(new PropertyValueFactory<Log, Integer>("logtime"));
        Task a = tableTask.getSelectionModel().getSelectedItem();
        tableLog.setItems(crud.getLogList(a.getTaskname()));
        logBattonAll();
    }

    private void taskAll(){

        tableUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {

                    addButton.setDisable(false);
                    editButton.setDisable(false);
                    dellButton.setDisable(false);
                    tableTask.setDisable(false);
                    addTask.setDisable(false);
                    editTask.setDisable(true);
                    dellTask.setDisable(true);
                    tableLog.setDisable(true);
                    addLog.setDisable(true);
                    editLog.setDisable(true);
                    dellLog.setDisable(true);
                    tableLog.setItems(null);
                    showTask();

                }
            }
        });
    }

    private void logAll(){

        tableTask.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {

                    addButton.setDisable(true);
                    editButton.setDisable(true);
                    dellButton.setDisable(true);
                    tableTask.setDisable(false);
                    addTask.setDisable(false);
                    editTask.setDisable(false);
                    dellTask.setDisable(false);
                    tableLog.setDisable(false);
                    addLog.setDisable(false);
                    editLog.setDisable(true);
                    dellLog.setDisable(true);
                    showLog();

                }
            }
        });

    }

    private void logBattonAll(){
        tableLog.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    addButton.setDisable(true);
                    editButton.setDisable(true);
                    dellButton.setDisable(true);
                    tableTask.setDisable(false);
                    addTask.setDisable(true);
                    editTask.setDisable(true);
                    dellTask.setDisable(true);
                    tableLog.setDisable(false);
                    addLog.setDisable(true);
                    editLog.setDisable(false);
                    dellLog.setDisable(false);

                }
            }
        });
    }

    public void clickButton(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            case "addButton":

                editController.setUser(new User());
                showDialog();
                crud.add(editController.getUser());
                showUser();

                break;

            case "editButton":

                editController.setUser(tableUser.getSelectionModel().getSelectedItem());
                showDialog();
                crud.update(editController.getUser());
                showUser();

                break;

            case "dellButton":

                crud.delete(tableUser.getSelectionModel().getSelectedItem());
                showUser();
                tableTask.setItems(null);
                tableLog.setItems(null);

                break;

            case "addTask":

                taskController.setTask(new Task(),tableUser.getSelectionModel().getSelectedItem());
                showDialogTask();
                crud.addTask(taskController.getTask());
                showTask();

                break;

            case "editTask":

                taskController.setTask(tableTask.getSelectionModel().getSelectedItem(),tableUser.getSelectionModel().getSelectedItem());
                showDialogTask();
                crud.updateTask(taskController.getTask());
                showTask();
                break;

            case "dellTask":

                crud.deleteTask(tableTask.getSelectionModel().getSelectedItem());
                showTask();
                tableLog.setItems(null);
                break;

            case "addLog":

                logController.setLog(new Log(),tableTask.getSelectionModel().getSelectedItem());
                showDialogLog();
                crud.addLog(logController.getLog());
                showLog();

                break;

            case "editLog":

                logController.setLog(tableLog.getSelectionModel().getSelectedItem(),tableTask.getSelectionModel().getSelectedItem());
                showDialogLog();
                crud.updateLog(logController.getLog());
                showLog();

                break;

            case "dellLog":

                crud.deleteLog(tableLog.getSelectionModel().getSelectedItem());
                showLog();
                break;

    }}

    private void initLoader() {

        try {
        fxmlLoader.setLocation(getClass().getResource("/fxml/edit.fxml"));
        fxmlEdit = fxmlLoader.load();
        editController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initLoaderTask(){

        try {
            fxmlLoaderTask.setLocation(getClass().getResource("/fxml/task.fxml"));
            fxmlEditTask = fxmlLoaderTask.load();
            taskController = fxmlLoaderTask.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initLoaderLog(){

        try {
            fxmlLoaderLog.setLocation(getClass().getResource("/fxml/log.fxml"));
            fxmlEditLog = fxmlLoaderLog.load();
            logController = fxmlLoaderLog.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDialogTask(){

        if (taskDialogStage==null) {
            taskDialogStage = new Stage();
            taskDialogStage.setTitle("Task");
            taskDialogStage.setMinHeight(130);
            taskDialogStage.setMinWidth(650);
            taskDialogStage.setHeight(130);
            taskDialogStage.setWidth(650);
            taskDialogStage.setResizable(false);
            taskDialogStage.setScene(new Scene((Parent) fxmlEditTask));
            taskDialogStage.initModality(Modality.WINDOW_MODAL);
            taskDialogStage.initOwner(mainStage);
        }

        taskDialogStage.showAndWait();
    }
    private void showDialogLog(){

        if (logDialogStage==null) {
            logDialogStage = new Stage();
            logDialogStage.setTitle("Log");
            logDialogStage.setMinHeight(130);
            logDialogStage.setMinWidth(650);
            logDialogStage.setHeight(130);
            logDialogStage.setWidth(650);
            logDialogStage.setResizable(false);
            logDialogStage.setScene(new Scene((Parent) fxmlEditLog));
            logDialogStage.initModality(Modality.WINDOW_MODAL);
            logDialogStage.initOwner(mainStage);
        }

        logDialogStage.showAndWait();
    }
    private void showDialog() {

        if (editDialogStage==null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("User");
            editDialogStage.setMinHeight(130);
            editDialogStage.setMinWidth(650);
            editDialogStage.setHeight(130);
            editDialogStage.setWidth(650);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene((Parent) fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }

        editDialogStage.showAndWait();

    }

}
