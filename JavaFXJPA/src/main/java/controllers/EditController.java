package controllers;


import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {

    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField fioInput;
    @FXML
    private TextField loginInput;
    @FXML
    private TextField passwordInput;

    private User user;

    public void setUser(User user) {
        if (user == null) {
            return;
        }
        this.user = user;
        fioInput.setText(user.getName());
        loginInput.setText(user.getLogin());
        passwordInput.setText(user.getPassword());
    }

    public User getUser() {
        return user;
    }

    public void actionClose(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();

    }

    public void actionSave(ActionEvent actionEvent) {

        if (fioInput.getText().trim().length()==0 || loginInput.getText().trim().length()==0|| passwordInput.getText().trim().length()==0){
            return;
        }
        user.setName(fioInput.getText());
        user.setLogin(loginInput.getText());
        user.setPassword(passwordInput.getText());
        actionClose(actionEvent);
    }

}
