package org.example.cinehub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FXMLDocumentcontroller implements Initializable {

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


        private Connection connect;
        private PreparedStatement prepare;
        private Statement statement;
        private ResultSet result;

        public void signin(){
                String sql = "SELECT * FROM admin where username =? and password=?";
                connect= database.connectDb();
                try {
                        prepare= connect.prepareStatement(sql);
                        prepare.setString(1,signin_username.getText());
                        prepare.setString(2,signin_password.getText());

                        result = prepare.executeQuery();

                        Alert alert;
                        if(signin_username.getText().isEmpty()||signin_password.getText().isEmpty()){
                                alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Empty USER/PASSWORD");
                                alert.setHeaderText(null);
                                alert.showAndWait();
                        }
                        else {
                                if(result.next()){
                                        alert=new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Information Message");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Succesful Login");
                                        alert.showAndWait();
                                }else{
                                        alert=new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("ERROR Message");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Wrong Username/Password");
                                        alert.showAndWait();
                                }
                        }
                }
                catch (Exception e){
                        e.printStackTrace();
                }
        }



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