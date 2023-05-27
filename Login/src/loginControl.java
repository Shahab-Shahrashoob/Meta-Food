import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class loginControl {

  public TextField username;
  public PasswordField password;
  public Button login;
  public Label errorLabel;
  public Hyperlink signUpLink;
  public Hyperlink adminLink;

  public void loginButtonClicked() {
    String name = username.getText();
    String pass = password.getText();
    String error = "Wrong Username or Password";
    errorLabel.setText(error);
  }

  public void signUpLinkClicked() throws IOException {
    Stage signup = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("signUpControl.fxml"));
    Scene signupScene = new Scene(root, 600, 400);
    signup.setTitle("Sign Up");
    signup.getIcons().add(new Image("1830351.png"));
    signup.setScene(signupScene);
    signup.show();
  }

  public void adminLinkClicked() {}
}
