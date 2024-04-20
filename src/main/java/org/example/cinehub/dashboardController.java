package org.example.cinehub;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    @FXML
    private Button addMovieBtn;

    @FXML
    private TextField addMovies_Date;

    @FXML
    private Button addMovies_clearBtn;

    @FXML
    private TableColumn<?, ?> addMovies_col_date;

    @FXML
    private TableColumn<?, ?> addMovies_col_duration;

    @FXML
    private TableColumn<?, ?> addMovies_col_genre;

    @FXML
    private TableColumn<?, ?> addMovies_col_movieTitle;

    @FXML
    private Button addMovies_deleteBtn;

    @FXML
    private TextField addMovies_duration;

    @FXML
    private AnchorPane addMovies_form;

    @FXML
    private TextField addMovies_genre;

    @FXML
    private ImageView addMovies_imageView;

    @FXML
    private Button addMovies_import;

    @FXML
    private Button addMovies_insertBtn;

    @FXML
    private TextField addMovies_movieTitle;

    @FXML
    private TextField addMovies_search;

    @FXML
    private TableView<moviesData> addMovies_tableView;

    @FXML
    private Button addMovies_updateBtn;

    @FXML
    private Button availableMovie_buyBtn;

    @FXML
    private Button availableMovie_clearBtn;

    @FXML
    private TableColumn<moviesData,String> availableMovie_col_genre;

    @FXML
    private TableColumn<moviesData,String> availableMovie_col_movieTitle;

    @FXML
    private TableColumn<moviesData,String> availableMovie_col_showingDate; ////cant find the duration column

    @FXML
    private Label availableMovie_date;

    @FXML
    private Label availableMovie_genre;

    @FXML
    private ImageView availableMovie_imageView;

    @FXML
    private Label availableMovie_movieTitle;

    @FXML
    private Label availableMovie_normalClass_price;

    @FXML
    private Spinner<?> availableMovie_normalClass_quantity;

    @FXML
    private Button availableMovie_receiptBtn;

    @FXML
    private Button availableMovie_selectMovie;

    @FXML
    private Label availableMovie_specialClass_price;

    @FXML
    private Spinner<?> availableMovie_specialClass_quantity;

    @FXML
    private TableView<?> availableMovie_tableView;

    @FXML
    private Label availableMovie_title;

    @FXML
    private Label availableMovie_total;

    @FXML
    private Button availableMoviesBtn;

    @FXML
    private AnchorPane availableMovies_form;

    @FXML
    private Button close;

    @FXML
    private Button customerBtn;

    @FXML
    private AnchorPane customer_form;

    @FXML
    private Button customers_clearBtn;

    @FXML
    private TableColumn<?, ?> customers_col_Genre;

    @FXML
    private TableColumn<?, ?> customers_col_date;

    @FXML
    private TableColumn<?, ?> customers_col_movieTitle;

    @FXML
    private TableColumn<?, ?> customers_col_ticketNumber;

    @FXML
    private TableColumn<?, ?> customers_col_time;

    @FXML
    private Label customers_date;

    @FXML
    private Button customers_deleteBtn;

    @FXML
    private Label customers_genre;

    @FXML
    private Label customers_movieTitle;

    @FXML
    private TextField customers_search;

    @FXML
    private TableView<?> customers_tableView;

    @FXML
    private Label customers_ticketNumber;

    @FXML
    private Label customers_time;

    @FXML
    private Button dashboardBtn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Button editScreeningBtn;

    @FXML
    private TableColumn<?, ?> editScreening_col_current;

    @FXML
    private TableColumn<?, ?> editScreening_col_duration;

    @FXML
    private TableColumn<?, ?> editScreening_col_genre;

    @FXML
    private TableColumn<?, ?> editScreening_col_movieTitle;

    @FXML
    private ComboBox<?> editScreening_current;

    @FXML
    private Button editScreening_deleteBtn;

    @FXML
    private AnchorPane editScreening_form;

    @FXML
    private ImageView editScreening_imageView;

    @FXML
    private TextField editScreening_search;

    @FXML
    private TableView<?> editScreening_tableView;

    @FXML
    private Label editScreening_title;

    @FXML
    private Button editScreening_updateBtn;

    @FXML
    private Button minimize;

    @FXML
    private Button signout;

    @FXML
    private AnchorPane topForm;

    @FXML
    private Label username;

    private double x=0;
    private double y=0;

    //database connection tools
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    public ObservableList<moviesData> addMoviesList(){
        ObservableList<moviesData>listData= FXCollections.observableArrayList();
        String sql ="SELECT * FROM movie";

        connect=database.connectDb();

        try{
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();

            moviesData movD;
            while(result.next()){
                movD =new moviesData(result.getString("movieTitle"),
                        result.getString("genre"),result.getString("duration"),
                        result.getString("image"),result.getDate("date"));
                listData.add(movD);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<moviesData> listAddMovies;
    public void showAddMoviesList(){
        listAddMovies=addMoviesList();

        addMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        addMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addMovies_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        addMovies_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

    public void logout(){
        signout.getScene().getWindow().hide();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) ->{
                x= event.getSceneX();
                y= event.getSceneY();
            });


            root.setOnMouseDragged((MouseEvent event) ->{
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public void switchForm(ActionEvent event){
        if(event.getSource()==dashboardBtn) {
            dashboard_form.setVisible(true);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customer_form.setVisible(false);

            dashboardBtn.setStyle("-fx-background-color:#ae2d3c;");
            addMovieBtn.setStyle("-fx-background-color:transparent;");
            availableMoviesBtn.setStyle("-fx-background-color:transparent;");
            editScreeningBtn.setStyle("-fx-background-color:transparent;");
            customerBtn.setStyle("-fx-background-color:transparent;");
        }

        else if(event.getSource()==addMovieBtn){
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(true);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customer_form.setVisible(false);

            dashboardBtn.setStyle("-fx-background-color:transparent;");
            addMovieBtn.setStyle("-fx-background-color:#ae2d3c;");
            availableMoviesBtn.setStyle("-fx-background-color:transparent;");
            editScreeningBtn.setStyle("-fx-background-color:transparent;");
            customerBtn.setStyle("-fx-background-color:transparent;");
        }
        else if(event.getSource()==availableMoviesBtn){
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(true);
            editScreening_form.setVisible(false);
            customer_form.setVisible(false);

            dashboardBtn.setStyle("-fx-background-color:transparent;");
            addMovieBtn.setStyle("-fx-background-color:transparent;");
            availableMoviesBtn.setStyle("-fx-background-color:#ae2d3c;");
            editScreeningBtn.setStyle("-fx-background-color:transparent;");
            customerBtn.setStyle("-fx-background-color:transparent;");
        }
        else if(event.getSource()==editScreeningBtn){
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(true);
            customer_form.setVisible(false);

            dashboardBtn.setStyle("-fx-background-color:transparent;");
            addMovieBtn.setStyle("-fx-background-color:transparent;");
            availableMoviesBtn.setStyle("-fx-background-color:transparent;");
            editScreeningBtn.setStyle("-fx-background-color:#ae2d3c;");
            customerBtn.setStyle("-fx-background-color:transparent;");
        }
        else if(event.getSource()==customerBtn){
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customer_form.setVisible(true);

            dashboardBtn.setStyle("-fx-background-color:transparent;");
            addMovieBtn.setStyle("-fx-background-color:transparent;");
            availableMoviesBtn.setStyle("-fx-background-color:transparent;");
            editScreeningBtn.setStyle("-fx-background-color:transparent;");
            customerBtn.setStyle("-fx-background-color:#ae2d3c;");
        }
    }
    public void displayUsername(){
        username.setText(getData.username);
    }

    //problem in close button need to remove closeclicked//
    public void close(){
        System.exit(0);
    }

    public void minimize(){
        Stage stage = (Stage)topForm.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}