package client.view;


import client.view.Admin.AdminMoviePageController;
import client.view.Admin.AdminUsersPageController;
import client.view.cinemaHall.CinemaHallController;
import client.view.frontPage.FrontPageController;
import client.view.login.LoginViewController;
import client.view.registration.RegisterController;
import client.view.user.UserProfileController;
import client.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shared.NewRegisteredUser;
import shared.User;

import java.io.IOException;

public class ViewHandler
{

  private Stage mainStage;
  private ViewModelFactory vmf;

  public ViewHandler(ViewModelFactory vmf)
  {
    this.vmf = vmf;
    mainStage = new Stage();
  }

  public void start()
  {
    showFrontPage(null);
    mainStage.show();
    mainStage.setResizable(false);
  }

  public void openLoginView()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/Login.fxml"));
    try
    {
      Parent root = loader.load();
      LoginViewController ctrl = loader.getController();
      ctrl.init(vmf.getLoginViewModel(), this);
      mainStage.setTitle("Log in");
      Scene loginScene = new Scene(root);
      mainStage.setScene(loginScene);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

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

  //    public void showFrontPage() {
  //        FXMLLoader loader = new FXMLLoader();
  //        loader.setLocation(getClass().getResource("../fxml/userFrontPage.fxml"));
  //        try
  //        {
  //            Parent root = loader.load();
  //            FrontPageController ctrl = loader.getController();
  //            ctrl.init(vmf.getFrontPage(),this, null);
  //            mainStage.setTitle("Front page");
  //            Scene frontPageScene = new Scene(root);
  //            mainStage.setScene(frontPageScene);
  //        }
  //        catch (IOException e)
  //        {
  //            e.printStackTrace();
  //        }
  //    }

  public void showFrontPage(NewRegisteredUser userLoggedIn)
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

  public void showAdminMoviePage()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/adminMovies.fxml"));
    try
    {
      Parent root = loader.load();
      AdminMoviePageController ctrl = loader.getController();
      ctrl.init(vmf.getAdminMoviePage(), this);
      mainStage.setTitle("Movie management");
      Scene scene = new Scene(root);
      mainStage.setScene(scene);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void showAdminUserPage()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/adminUsers.fxml"));
    try
    {
      Parent root = loader.load();
      AdminUsersPageController ctrl = loader.getController();
      ctrl.init(vmf.getAdminViewModelUsers(), this);
      mainStage.setTitle("User management");
      Scene scene = new Scene(root);
      mainStage.setScene(scene);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void showCinemaHallPage()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/cinemaHall.fxml"));
    try
    {
      Parent root = loader.load();
      CinemaHallController ctrl = loader.getController();
      ctrl.init(vmf.getCinemaHallViewModel(),this);
      mainStage.setTitle("Cinema Hall");
      Scene scene = new Scene(root);
      mainStage.setScene(scene);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void showUserProfile(NewRegisteredUser userLoggedIn)
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../fxml/userProfile.fxml"));
    try
    {
      Parent root = loader.load();
     UserProfileController ctrl = loader.getController();
      ctrl.init(vmf.getUserProfileVM(),this,userLoggedIn);
      mainStage.setTitle("Cinema Hall");
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
    System.out.println("Me close");
    mainStage.close();
  }
}

