import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class addRestaurantPage {

  public ImageView adminpage;
  public ImageView deliverypic;
  public ImageView withtablechoose;

  public void takeAwayClicked() throws IOException {
    System.out.println("Take away image clicked");
    Parent root = FXMLLoader.load(
      getClass().getResource("addtakeawayrestaurant.fxml")
    );
    System.out.println("FXML loaded");
    Scene scene = new Scene(root, 600, 400);
    System.out.println("Scene Set");
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setTitle("Add Take Away Restaurant");
    App.window.setScene(scene);
    App.window.show();
  }

  public void withTableClicked() throws IOException {
    Parent root = FXMLLoader.load(
      getClass().getResource("addwithtablerestaurant.fxml")
    );
    Scene scene = new Scene(root, 600, 400);
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setTitle("Add Restaurant With Tables");
    App.window.setScene(scene);
    App.window.show();
  }

  public void adminclick() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
    Scene scene = new Scene(root, 600, 400);
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setTitle("Admin");
    App.window.setScene(scene);
    App.window.show();
  }
}
