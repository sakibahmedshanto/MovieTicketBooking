package org.example.cinehub;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    @FXML
    private Button addMovieBtn;

    @FXML
    private Button addMovies_clearBtn;

    @FXML
    private TableColumn<moviesData,String> addMovies_col_date;

    @FXML
    private TableColumn<moviesData,String> addMovies_col_duration;

    @FXML
    private TableColumn<moviesData,String> addMovies_col_genre;

    @FXML
    private TableColumn<moviesData,String> addMovies_col_movieTitle;/////////

    @FXML
    private Button addMovies_deleteBtn;

    @FXML
    private TextField addMovies_duration;

    @FXML
    private DatePicker addMovies_Date;

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
    private TableColumn<?, ?> availableMovie_col_genre;

    @FXML
    private TableColumn<?, ?> availableMovie_col_movieTitle;

    @FXML
    private TableColumn<?, ?> availableMovie_col_showingDate;

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
    private TableColumn<moviesData, String> editScreening_col_current;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_duration;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_genre;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_movieTitle;

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
    private TableView<moviesData> editScreening_tableView;/// addScreening table view

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

    private Image image;

    private double x=0;
    private double y=0;

    //database connection tools
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private String[] currentList={"Showing","End Showing"};

    public void comboBox(){
        List<String> listCurrent=new ArrayList<>();

        for(String data: currentList){
            listCurrent.add(data);
        }
        ObservableList listC=FXCollections.observableArrayList(listCurrent);
        editScreening_current.setItems(listC);
    }

    public void updateEditScreening(){
        String sql="UPDATE movie SET current='"
                    +editScreening_current.getSelectionModel().getSelectedItem()
                    + "' WHERE movieTitle ='"+editScreening_title.getText()+"'";
        connect=database.connectDb();
        try{

            Alert alert;
            statement=connect.createStatement();
            if(editScreening_title.getText().isEmpty()
                    ||editScreening_imageView.getImage()==null
                    ||editScreening_current.getSelectionModel().isEmpty()){
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();
            }else{
                statement.executeUpdate(sql);

                alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully update !");
                alert.showAndWait();
                showEditScreening();
                clearEditScreening();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void clearEditScreening(){
        editScreening_title.setText("");
        editScreening_imageView.setImage(null);
        //editScreening_current.setSelectionModel();
    }

    public void searchEditScreening(){
        FilteredList<moviesData> filter = new FilteredList<>(editScreeningL, e -> true);
        editScreening_search.textProperty().addListener((observable, oldValue, newValue) ->{
            filter.setPredicate(predicateMoviesData -> {

                if(newValue.isEmpty() || newValue == null) {
                    clearEditScreening();
                    return true;
                }
                String keySearch = newValue.toLowerCase();

                if(predicateMoviesData.getTitle().toLowerCase().contains(keySearch)){
                    return true;
                }else if(predicateMoviesData.getGenre().toLowerCase().contains(keySearch)){
                    return true;
                }else if(predicateMoviesData.getDuration().toLowerCase().contains(keySearch)) {
                    return true;
                }else if(predicateMoviesData.getCurrent().toLowerCase().contains(keySearch)){
                        return true;
                }

                return false;
            });
            SortedList<moviesData>sortData= new SortedList<>(filter);
            sortData.comparatorProperty().bind(editScreening_tableView.comparatorProperty());
            editScreening_tableView.setItems(sortData);
        } );
    }
    public void selectEditScreening(){
        moviesData movD=editScreening_tableView.getSelectionModel().getSelectedItem();
        int num=editScreening_tableView.getSelectionModel().getFocusedIndex();

        if((num -1)<-1){
            return;
        }

        String uri="file:"+movD.getImage();
        image=new Image(uri,138,183,false,true);
        editScreening_imageView.setImage(image);

        editScreening_title.setText(movD.getTitle());

    }
    public ObservableList<moviesData> editScreeningList(){
        ObservableList<moviesData> editSList= FXCollections.observableArrayList();
        String sql="SELECT * FROM movie";
        connect=database.connectDb();
        try{
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();
            moviesData movD;
            while(result.next()){
                movD=new moviesData(result.getInt("ID")
                        ,result.getString("movieTitle")
                        ,result.getString("genre")
                        ,result.getString("duration")
                        ,result.getString("image")
                        ,result.getDate("date")
                        ,result.getString("current"));
                editSList.add(movD);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return editSList;
    }

    private ObservableList<moviesData> editScreeningL;
    public void showEditScreening(){
        editScreeningL=editScreeningList();

        editScreening_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        editScreening_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        editScreening_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        editScreening_col_current.setCellValueFactory(new PropertyValueFactory<>("current"));

        editScreening_tableView.setItems(editScreeningL);
    }

    public void searchAddMovies(){

        FilteredList<moviesData> filter = new FilteredList<>(listAddMovies, e -> true);
        addMovies_search.textProperty().addListener((observable, oldValue, newValue) ->{
            filter.setPredicate(predicateMoviesData -> {

                if(newValue.isEmpty() || newValue == null) {
                    clearAddMoviesList();
                    return true;
                }
                String keySearch = newValue.toLowerCase();

                if(predicateMoviesData.getTitle().toLowerCase().contains(keySearch)){
                    return true;
                }else if(predicateMoviesData.getGenre().toLowerCase().contains(keySearch)){
                    return true;
                }else if(predicateMoviesData.getDuration().toLowerCase().contains(keySearch)){
                    return true;
                }else if(predicateMoviesData.getDate().toString().contains(keySearch)){
                    return true;
                }

                return false;
            });
            SortedList<moviesData>sortData= new SortedList<>(filter);
            sortData.comparatorProperty().bind(addMovies_tableView.comparatorProperty());
            addMovies_tableView.setItems(sortData);
        } );
    }

    public void importImage(){
        FileChooser open= new FileChooser();

        open.setTitle("Open Image file");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File","*png","*jpg","*jpeg"));

        Stage stage= (Stage) addMovies_form.getScene().getWindow();
        File file= open.showOpenDialog(stage);

        if(file!=null){
            image =new Image(file.toURI().toString(),127,167,false,true);
            addMovies_imageView.setImage(image);

            //take image path
            getData.path=file.getAbsolutePath();///getPath
        }
    }

    private int countId;

    public void movieId(){
        String sql="SELECT count(ID) FROM movie";
        connect =database.connectDb();
        try{
            prepare=connect.prepareStatement(sql);
            result= prepare.executeQuery();

            if(result.next()) {
                getData.movieId =result.getInt("count(id)");
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public void insertAddMovies(){
        String sql1="SELECT * FROM movie WHERE movieTitle='" +addMovies_movieTitle.getText()+ "'";

        connect=database.connectDb();
        Alert alert;
        try{
            statement=connect.createStatement();
            result=statement.executeQuery(sql1);

            if(result.next()){
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(addMovies_movieTitle.getText()+" was already existing!");
                alert.showAndWait();
            }else{
                if(addMovies_movieTitle.getText().isEmpty() ||
                        addMovies_genre.getText().isEmpty() ||
                        addMovies_duration.getText().isEmpty() ||
                        addMovies_imageView.getImage()==null ||
                        addMovies_Date.getValue()==null){
                    alert =new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields!");
                    alert.showAndWait();

                }else{
                    String sql="INSERT INTO movie (ID,movieTitle,genre,duration,image,date) VALUES (?,?,?,?,?,?)";

                    String uri=getData.path;
                    uri=uri.replace("\\","\\\\");

                    movieId();
                    String mID=String.valueOf(getData.movieId+1);

                    prepare=connect.prepareStatement(sql);
                    prepare.setString(1,mID);
                    prepare.setString(2,addMovies_movieTitle.getText());
                    prepare.setString(3,addMovies_genre.getText());
                    prepare.setString(4,addMovies_duration.getText());
                    prepare.setString(5,uri);
                    prepare.setString(6,String.valueOf(addMovies_Date.getValue()));

                    prepare.execute();

                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully add new movie!");
                    alert.showAndWait();

                    clearAddMoviesList();
                    showAddMoviesList();
                }
            }
            //insert same name movie in DB that exist before
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateAddMovies(){
        String uri=getData.path;
        uri=uri.replace("\\","\\\\");

        String sql="UPDATE movie SET movieTitle= '" +addMovies_movieTitle.getText()
                + " ',genre= '"+addMovies_genre.getText()
                + " ',duration='"+ addMovies_duration.getText()
                + " ',image = '"+uri
                + " ',date= '" + addMovies_Date.getValue()
                + " ' WHERE ID= '" +String.valueOf(getData.movieId)+ "' ";

        connect= database.connectDb();

        try{
            statement=connect.createStatement();
            Alert alert;

            if(addMovies_movieTitle.getText().isEmpty()
                    ||addMovies_genre.getText().isEmpty()
                    ||addMovies_duration.getText().isEmpty()
                    ||addMovies_imageView.getImage()==null
                    ||addMovies_Date.getValue()==null)
            {
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();
            }else{
                statement.executeUpdate(sql);

                alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully update "+addMovies_movieTitle.getText());
                alert.showAndWait();
                showAddMoviesList();
                clearAddMoviesList();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteAddMovies(){
        String sql= "DELETE FROM movie WHERE movieTitle='" +addMovies_movieTitle.getText() +"'";
        Alert alert;

        connect=database.connectDb();
        try{
            statement=connect.createStatement();
            if(addMovies_movieTitle.getText().isEmpty()
                    ||addMovies_genre.getText().isEmpty()
                    ||addMovies_duration.getText().isEmpty()
                    ||addMovies_Date.getValue()==null
                    ||addMovies_imageView.getImage()==null){
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();
            }else{
                alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete "+addMovies_movieTitle.getText()+" ?");

                Optional<ButtonType> option = alert.showAndWait();

                if(ButtonType.OK.equals(option.get())){

                    statement.executeUpdate(sql);

                    showAddMoviesList();
                    clearAddMoviesList();

                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                }else{
                    return;
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void clearAddMoviesList(){
        addMovies_movieTitle.setText("");
        addMovies_genre.setText("");
        addMovies_duration.setText("");
        addMovies_imageView.setImage(null);
        addMovies_Date.setValue(null);
    }

    public ObservableList<moviesData> addMoviesList(){
        ObservableList<moviesData>listData= FXCollections.observableArrayList();
        String sql ="SELECT * FROM movie";

        connect=database.connectDb();

        try{
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();

            moviesData movD;
            while(result.next()){
                movD =new moviesData(result.getInt("id"),
                        result.getString("movieTitle"),
                        result.getString("genre"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current"));
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

        addMovies_tableView.setItems(listAddMovies);

    }

    public void selectAddMoviesList(){
        moviesData movD=addMovies_tableView.getSelectionModel().getSelectedItem();
        int num=addMovies_tableView.getSelectionModel().getSelectedIndex();

        if((num-1)<-1){
            return;
        }

        getData.path=movD.getImage();
        getData.movieId=movD.getId();

        addMovies_movieTitle.setText(movD.getTitle());
        addMovies_genre.setText(movD.getGenre());
        addMovies_duration.setText(movD.getDuration());

        String getDate= String.valueOf(movD.getDate());

        String uri="file:"+movD.getImage();

        image=new Image(uri,127,167,false,true);
        addMovies_imageView.setImage(image);

        //addMovies_Date.setValue(LocalDate.of());
        addMovies_Date.setValue(movD.getDate().toLocalDate());



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
            displayUsername();
            showAddMoviesList();
            showEditScreening();
            comboBox();
    }
}