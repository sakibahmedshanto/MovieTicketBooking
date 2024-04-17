package org.example.cinehub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

        @FXML
        private Hyperlink signUp_alreadyHaveAccount;

        @FXML
        private Button signUp_btn;

        @FXML
        private TextField signUp_email;

        @FXML
        private AnchorPane signUp_form;

        @FXML
        private PasswordField signUp_password;

        @FXML
        private TextField signUp_username;

        @FXML
        private Hyperlink signin_createAccount;

        @FXML
        private AnchorPane signIn_form;

        @FXML
        private Button signin_loginBtn;

        @FXML
        private PasswordField signin_password;

        @FXML
        private TextField signin_username;

        @Override
        public void initialize(URL url, ResourceBundle rb){}

        public void switchForm(ActionEvent event){
                if(event.getSource() == signin_createAccount){
                        signIn_form.setVisible(false);
                        signUp_form.setVisible(true);
                }
                else if(event.getSource() == signUp_alreadyHaveAccount){
                        signIn_form.setVisible(true);
                        signUp_form.setVisible(false);
                }
        }



}