import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginControl {

  private TextField username;
  private PasswordField password;
  private Button login;
  private Label errorLabel;
  private Hyperlink signUpLink;

  public void loginButtonClicked() {
    String enteredUsername = username.getText();
    String enteredPassword = password.getText();
    String error = "Wrong Username and Password";
    errorLabel.setText(error);
  }

  public void signUpLinkClicked() {
    String enteredUsername = username.getText();
    String enteredPassword = password.getText();
    String error = "Wrong Username and Password";
    errorLabel.setText(error);
  }
}
