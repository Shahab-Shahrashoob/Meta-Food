import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class adminPage {

  public ImageView addrestaurantpic;
  public ImageView editrestaurant;
  public ImageView adminpage;

  public void editclick() throws IOException {
    Parent root = FXMLLoader.load(
      getClass().getResource("editRestaurant.fxml")
    );
    Scene scene = new Scene(root, 600, 400);
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setTitle("Edit");
    App.window.setScene(scene);
    App.window.show();
  }

  public void adminclick() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("loginControl.fxml"));
    Scene scene = new Scene(root, 600, 400);
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setTitle("Login");
    App.window.setScene(scene);
    App.window.show();
  }

  public void addclick() throws IOException {
    Parent root = FXMLLoader.load(
      getClass().getResource("addrestaurantpage.fxml")
    );
    Scene scene = new Scene(root, 600, 400);
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setTitle("Add Restaurant");
    App.window.setScene(scene);
    App.window.show();
  }
}
