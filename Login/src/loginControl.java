import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class loginControl {

  public TextField username;
  public PasswordField password;
  public Button login;
  public Label errorLabel;
  public Hyperlink signUpLink;
  public Hyperlink adminLink;

  public void loginButtonClicked() throws UnknownHostException, IOException {
    Socket socket = new Socket("127.0.0.1", 5000);
    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
    ObjectOutputStream output = new ObjectOutputStream(
      socket.getOutputStream()
    );
    output.writeObject(username.getText());
    // String name = username.getText();
    // String pass = password.getText();
    // if (name.equals("shahab") && pass.equals("8118")) {
    // errorLabel.setStyle("-fx-text-fill : green;");
    // errorLabel.setText("Correct Username & Password");
    // } else {
    // errorLabel.setStyle("-fx-text-fill : red;");
    // errorLabel.setText("Wrong Username or Password");}
  }

  public void signUpLinkClicked() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("signUpControl.fxml"));
    Scene signupScene = new Scene(root, 600, 400);
    App.window.setTitle("Sign Up");
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setScene(signupScene);
    App.window.show();
  }

  public void adminLinkClicked() throws IOException {
    Parent root = FXMLLoader.load(
      getClass().getResource("adminLoginControl.fxml")
    );
    Scene adminScene = new Scene(root, 600, 400);
    App.window.setTitle("Admin");
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setScene(adminScene);
    App.window.show();
  }
}
