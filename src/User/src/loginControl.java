import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

  public void loginButtonClicked() throws IOException, ClassNotFoundException {
    String Username = username.getText();
    String Password = password.getText();
    Socket socket = new Socket("localhost", 8000);
    System.out.println("Socket made");
    ObjectOutputStream output = new ObjectOutputStream(
      socket.getOutputStream()
    );
    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
    System.out.println("Stream Channel made");
    output.writeObject("loginuser");
    output.writeObject(Username);
    output.writeObject(Password);
    System.out.println("Sent to Server");
    boolean check = (boolean) input.readObject();
    System.out.println("Recieved message from server");
    if (!check) {
      errorLabel.setStyle("-fx-text-fill : red;");
      errorLabel.setText("Wrong Username or Password");
    } else {
      errorLabel.setStyle("-fx-text-fill : green;");
      errorLabel.setText("Correct Username & Password");
      Parent root = FXMLLoader.load(getClass().getResource("userGUI.fxml"));
      Scene signupScene = new Scene(root, 600, 400);
      App.window.setTitle("User");
      App.window.getIcons().add(new Image("1830351.png"));
      App.window.setScene(signupScene);
      App.window.show();
    }
    socket.close();
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

  public void cursorOnSignUp() {
    signUpLink.setStyle("-fx-text-decoration : underline");
  }

  public void cursorOnAdmin() {
    adminLink.setStyle("-fx-text-decoration : underline");
  }
}
