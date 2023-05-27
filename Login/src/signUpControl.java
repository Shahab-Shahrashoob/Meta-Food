import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class signUpControl {

  public TextField username;
  public TextField password;
  public TextField confirmPassword;
  public Label signUpCheck;
  public Button signUpButton;

  public void signUpButtonClicked() {
    signUpCheck.setText("Sign Up Successful");
  }
}
