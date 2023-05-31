import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

  public static Stage window;

  public static void main(String[] args) throws Exception {
    launch(args);
  }

  public void start(Stage primaryStage) throws Exception {
    window = primaryStage;
    Parent root = FXMLLoader.load(getClass().getResource("loginControl.fxml"));
    Scene scene = new Scene(root, 600, 400);
    window.getIcons().add(new Image("1830351.png"));
    window.setTitle("Login");
    window.setScene(scene);
    window.show();
  }
}
