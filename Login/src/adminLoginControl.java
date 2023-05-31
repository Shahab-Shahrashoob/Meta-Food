import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class adminLoginControl {

  public Label adminError;
  public Button adminLogin;
  public PasswordField adminPassword;
  public AnchorPane backGround;
  public Hyperlink loginBack;

  public void adminLoginButtonClicked() {
    if (adminPassword.getText().equals("8118")) {
      adminError.setStyle("-fx-text-fill : green;");
      adminError.setText("Correct Password");
    } else {
      adminError.setStyle("-fx-text-fill : red;");
      adminError.setText("Wrong Password");
    }
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
