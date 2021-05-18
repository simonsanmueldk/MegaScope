package client.view;

import client.view.Admin.AdminUsersPageController;
import client.view.cinemaHall.CinemaHallController;
import client.view.frontPage.FrontPageController;
import client.view.login.LoginViewController;
import client.view.movieManagement.AddMovieController;
import client.view.movieManagement.EditMovieController;
import client.view.registration.RegisterController;
import client.view.user.UserProfileController;
import client.viewmodel.ViewModelFactory;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shared.Show;
import shared.User;

import java.io.IOException;

public class ViewHandler {

  private Stage mainStage;
  private ViewModelFactory vmf;

  public ViewHandler(ViewModelFactory vmf) {
    this.vmf = vmf;
    mainStage = new Stage();
//    Image image = new Image("file:logo.png");
//    mainStage.getIcons().add(image);
  }

  public void start() {
    showFrontPage(null);
    mainStage.show();
    mainStage.setResizable(false);
  }

  public void openLoginView(User userLoggedIn) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/Login.fxml"));
    try {
      Parent root = loader.load();
      LoginViewController ctrl = loader.getController();
      ctrl.init(vmf.getLoginViewModel(), this, userLoggedIn);
      mainStage.setTitle("Log in");
      Scene loginScene = new Scene(root);
      mainStage.setScene(loginScene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openAddMovieView(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/addMovie.fxml"));
    try {
      Parent root = loader.load();
      AddMovieController ctrl = loader.getController();
      ctrl.init(vmf.getAddMovieViewModel(), this);
      Stage stage = new Stage();
      stage.initOwner(mainStage);
      stage.setTitle("Add new movie");
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openEditMovie(Show show){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/editMovie.fxml"));
    try {
      Parent root = loader.load();
      EditMovieController ctrl = loader.getController();
      ctrl.init(vmf.getEditMovieViewModel(), this, show);
      Stage stage = new Stage();
      stage.initOwner(mainStage);
      stage.setTitle("Edit movie");
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void openAdminUsersPage(User userLoggedIn) {

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/adminUsers.fxml"));
    try {
      Parent root = loader.load();
      AdminUsersPageController ctrl = loader.getController();
      ctrl.init(vmf.getUsersVM(), this, userLoggedIn);
      mainStage.setTitle("Manage Users");
      Scene loginScene = new Scene(root);
      mainStage.setScene(loginScene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


/*  public Alert openRemoveConfiramtion()

  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Dialog");
    alert.setHeaderText("Look, a Confirmation Dialog");
    alert.setContentText("Are you ok with this?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){


    } else {
      alert.close();
    }
  }*/

  public Dialog<Show> openAddMovieWindow(){
    Dialog<Show> dialog = new Dialog<>();
    ButtonType buttonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(buttonType, ButtonType.CANCEL);

    VBox sceneContainer = new VBox(5);
    HBox movieTitleContainer          = new HBox();
    HBox movieDateOfReleaseContainer  = new HBox();
    HBox movieMainActorsContainer     = new HBox();
    HBox movieDescriptionContainer    = new HBox();
    HBox movieTimeOfShowContainer     = new HBox();
    HBox movieDateOfShowContainer     = new HBox();

    Label movieTitleLabel             = new Label("Movie title");
    Label movieDateOfReleaseLabel     = new Label("Date of release");
    Label movieMainActorsLabel        = new Label("Movie actors");
    Label movieDescriptionLabel       = new Label("Movie description");
    Label movieTimeOfShowLabel        = new Label("Time of show");
    Label movieDateOfShow             = new Label("Date of show");

    movieTitleLabel.setPrefWidth(100);
    movieDateOfReleaseLabel.setPrefWidth(100);
    movieMainActorsLabel.setPrefWidth(100);
    movieDescriptionLabel.setPrefWidth(100);
    movieTimeOfShowLabel.setPrefWidth(100);
    movieDateOfShow.setPrefWidth(100);

    TextField movieTitleTextField         = new TextField();
    TextField movieDateOfReleaseTextField = new TextField();
    TextField movieMainActorsTextField    = new TextField();
    TextField movieDescriptionTextField   = new TextField();
    TextField movieTimeOfShowTextField    = new TextField();
    TextField movieDateOfShowTextField    = new TextField();

    movieTitleContainer.getChildren().addAll(movieTitleLabel,movieTitleTextField);
    movieDateOfReleaseContainer.getChildren().addAll(movieDateOfReleaseLabel,movieDateOfReleaseTextField);
    movieMainActorsContainer.getChildren().addAll(movieMainActorsLabel,movieMainActorsTextField);
    movieDescriptionContainer.getChildren().addAll(movieDescriptionLabel,movieDescriptionTextField);
    movieTimeOfShowContainer.getChildren().addAll(movieTimeOfShowLabel,movieTimeOfShowTextField);
    movieDateOfShowContainer.getChildren().addAll(movieDateOfShow,movieDateOfShowTextField);

    sceneContainer.getChildren().addAll(movieTitleContainer,movieDateOfReleaseContainer,
            movieMainActorsContainer,movieDescriptionContainer,movieTimeOfShowContainer,
            movieDateOfShowContainer);

    Node addButton = dialog.getDialogPane().lookupButton(buttonType);
    addButton.setDisable(false);

    Platform.runLater(movieTitleTextField::requestFocus);

    dialog.getDialogPane().setContent(sceneContainer);

    dialog.setResultConverter(dialogButton -> {
      if(dialogButton == buttonType){

        return new Show(movieTitleTextField.getText(), movieDateOfReleaseTextField.getText(),
                movieMainActorsTextField.getText(), movieDescriptionTextField.getText(),
                movieTimeOfShowTextField.getText(), movieDateOfShowTextField.getText());
      }
      return null;
    });
    return dialog;

  }
/*
  public Dialog<Show> openEditMovieWindow(Show show){
    Dialog<Show> dialog = new Dialog<>();
    ButtonType buttonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(buttonType, ButtonType.CANCEL);

    VBox sceneContainer = new VBox(5);
    HBox movieTitleContainer          = new HBox();
    HBox movieDateOfReleaseContainer  = new HBox();
    HBox movieMainActorsContainer     = new HBox();
    HBox movieDescriptionContainer    = new HBox();
    HBox movieTimeOfShowContainer     = new HBox();
    HBox movieDateOfShowContainer     = new HBox();

    Label movieTitleLabel             = new Label("Movie title");
    Label movieDateOfReleaseLabel     = new Label("Date of release");
    Label movieMainActorsLabel        = new Label("Movie actors");
    Label movieDescriptionLabel       = new Label("Movie description");
    Label movieTimeOfShowLabel        = new Label("Time of show");
    Label movieDateOfShow             = new Label("Date of show");

    movieTitleLabel.setPrefWidth(100);
    movieDateOfReleaseLabel.setPrefWidth(100);
    movieMainActorsLabel.setPrefWidth(100);
    movieDescriptionLabel.setPrefWidth(100);
    movieTimeOfShowLabel.setPrefWidth(100);
    movieDateOfShow.setPrefWidth(100);

    TextField movieTitleTextField         = new TextField();
    TextField movieDateOfReleaseTextField = new TextField();
    TextField movieMainActorsTextField    = new TextField();
    TextField movieDescriptionTextField   = new TextField();
    TextField movieTimeOfShowTextField    = new TextField();
    TextField movieDateOfShowTextField    = new TextField();

    movieTitleTextField.setText(show.getName());
    movieDateOfReleaseTextField.setText(show.getDateOfRelease());
    movieMainActorsTextField.setText(show.getMainActors());
    movieDescriptionTextField.setText(show.getDescription());
    movieTimeOfShowTextField.setText(show.getTimeOfShow());
    movieDateOfReleaseTextField.setText(show.getDateOfRelease());

    movieTitleContainer.getChildren().addAll(movieTitleLabel,movieTitleTextField);
    movieDateOfReleaseContainer.getChildren().addAll(movieDateOfReleaseLabel,movieDateOfReleaseTextField);
    movieMainActorsContainer.getChildren().addAll(movieMainActorsLabel,movieMainActorsTextField);
    movieDescriptionContainer.getChildren().addAll(movieDescriptionLabel,movieDescriptionTextField);
    movieTimeOfShowContainer.getChildren().addAll(movieTimeOfShowLabel,movieTimeOfShowTextField);
    movieDateOfShowContainer.getChildren().addAll(movieDateOfShow,movieDateOfShowTextField);

    sceneContainer.getChildren().addAll(movieTitleContainer,movieDateOfReleaseContainer,
            movieMainActorsContainer,movieDescriptionContainer,movieTimeOfShowContainer,
            movieDateOfShowContainer);

    Node addButton = dialog.getDialogPane().lookupButton(buttonType);
    addButton.setDisable(false);

    Platform.runLater(movieTitleTextField::requestFocus);

    dialog.getDialogPane().setContent(sceneContainer);

    dialog.setResultConverter(dialogButton -> {
      if(dialogButton == buttonType){

        return new Show(show.getMovie_id(),movieTitleTextField.getText(), movieDateOfReleaseTextField.getText(),
                movieMainActorsTextField.getText(), movieDescriptionTextField.getText(),
                movieTimeOfShowTextField.getText(), movieDateOfShowTextField.getText());
      }
      return null;
    });
    return dialog;
  }

 */

  public void openRegisterView()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/Register.fxml"));
    try
    {
      Parent root = loader.load();
      RegisterController ctrl = loader.getController();
      ctrl.init(vmf.getRegisterVM(), this);
      mainStage.setTitle("Register");
      Scene registerScene = new Scene(root);
      mainStage.setScene(registerScene);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }


  public void showFrontPage(User userLoggedIn)
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/userFrontPage.fxml"));
    try
    {
      Parent root = loader.load();
      FrontPageController ctrl = loader.getController();
      ctrl.init(vmf.getFrontPage(), this, userLoggedIn);
      mainStage.setTitle("Front page");
      Scene frontPageScene = new Scene(root);
      mainStage.setScene(frontPageScene);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openCinemaHallPage(User userLoggedIn, Show show)
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/cinemaHall.fxml"));
    try
    {
      Parent root = loader.load();
      CinemaHallController ctrl = loader.getController();
      ctrl.init(vmf.getCinemaHallPage(),this, userLoggedIn, show);
      mainStage.setTitle("Cinema Hall");
      Scene cinemaHallScene = new Scene(root);
      mainStage.setScene(cinemaHallScene);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }


  public void openUserProfile(User userLoggedIn)
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/userProfile.fxml"));
    try
    {
      Parent root = loader.load();
     UserProfileController ctrl = loader.getController();
      ctrl.init(vmf.getUserProfileVM(),this,userLoggedIn);
      mainStage.setTitle("Manage profile Page");
      Scene scene = new Scene(root);
      mainStage.setScene(scene);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void close()
  {
    System.out.println("Closing the client");
    mainStage.close();
  }
}

