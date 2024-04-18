package org.example.cinehub;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

        private  PreparedStatement prepare2;
        private Statement statement;
        private ResultSet result;


        public void signup(){
                String sql= "INSERT INTO admin (email,username,password) VALUES (?,?,?)";
                connect = database.connectDb();
                try {
                        prepare= connect.prepareStatement(sql);
                        prepare.setString(1,signUp_email.getText());
                        prepare.setString(2,signUp_username.getText());
                        prepare.setString(3,signUp_password.getText());
                        //alert making
                        Alert alert;
                        if(signUp_username.getText().isEmpty()||signUp_password.getText().isEmpty()||signUp_email.getText().isEmpty()){
                                alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Please Fill All Blank Field");
                                alert.showAndWait();
                        }
                        else if (signUp_password.getText().length() < 8) {
                                alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Invalid Password");
                                alert.showAndWait();
                        }
                        // here to implement a email vallidator 1:15 - 1:29
                      else{

                              //executing the prepared query
                                prepare.execute();


                                alert = new Alert (Alert.AlertType.INFORMATION);
                                alert.setTitle("Information Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Succesfully Created a new account");
                                alert.showAndWait();
                               //clearing the text fields

                                signUp_email.setText("");
                                signUp_username.setText("");
                                signUp_password.setText("");

                }

                }catch (Exception e){
                        e.printStackTrace();
                }
        }


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

                                        //to hide the login form
                                        signin_loginBtn.getScene().getWindow().hide();

                                        //to go to dashboard
                                        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                                        Stage stage=new Stage();
                                        Scene scene=new Scene(root);
                                        stage.setScene(scene);

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