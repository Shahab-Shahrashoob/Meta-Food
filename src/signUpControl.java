import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class signUpControl {

  public TextField username;
  public TextField password;
  public TextField confirmPassword;
  public TextField email;
  public TextField phoneNumber;
  public Label signUpCheck;
  public Button signUpButton;
  public Hyperlink loginBack;

  public void signUpButtonClicked() throws InterruptedException {
    signUpCheck.setStyle("-fx-text-fill : green;");
    signUpCheck.setText("Sign Up Successful");
  }

  public void loginBackClicked() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("loginControl.fxml"));
    Scene scene = new Scene(root, 600, 400);
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setTitle("Login");
    App.window.setScene(scene);
    App.window.show();
  }
}
