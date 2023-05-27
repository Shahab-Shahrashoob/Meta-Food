import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginControl {

  public TextField username;
  public PasswordField password;
  public Button login;
  public Label errorLabel;
  public Hyperlink signUpLink;

  public void loginButtonClicked() {
    String error = "Wrong Username or Password";
    errorLabel.setText(error);
  }

  public void signUpLinkClicked() {}
}
