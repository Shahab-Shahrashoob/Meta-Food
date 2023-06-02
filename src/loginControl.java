import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
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

  public void loginButtonClicked() throws IOException {
    Socket socket = new Socket("127.0.0.1", 4999);
    InputStreamReader input = new InputStreamReader(socket.getInputStream());
    OutputStreamWriter output = new OutputStreamWriter(
      socket.getOutputStream()
    );
    BufferedReader reader = new BufferedReader(input);
    BufferedWriter writer = new BufferedWriter(output);
    String result = username.getText();
    writer.write(result);
    String reult = reader.readLine();
    errorLabel.setText(reult);
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
