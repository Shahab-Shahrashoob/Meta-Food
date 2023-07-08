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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class adminLoginControl {

  public Label adminError;
  public Button adminLogin;
  public PasswordField adminPassword;
  public AnchorPane backGround;
  public Hyperlink loginBack;

  public void adminLoginButtonClicked()
    throws UnknownHostException, IOException, ClassNotFoundException {
    boolean check = true;
    String password = adminPassword.getText();
    Socket socket = new Socket("localhost", 8000);
    System.out.println("Socket made");
    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
    System.out.println("Stream made");
    out.writeObject("adminlogin");
    out.writeObject(password);
    check = (boolean) in.readObject();
    System.out.println("Message recieved");
    if (check) {
      adminError.setStyle("-fx-text-fill : green;");
      adminError.setText("Correct Password");
      Parent rootadmin = FXMLLoader.load(
        getClass().getResource("adminPage.fxml")
      );
      Scene scene = new Scene(rootadmin, 600, 400);
      App.window.getIcons().add(new Image("1830351.png"));
      App.window.setTitle("Admin");
      App.window.setScene(scene);
      App.window.show();
    } else {
      adminError.setStyle("-fx-text-fill : red;");
      adminError.setText("Wrong Password");
    }
    socket.close();
  }

  public void loginBackClicked() throws IOException {
    Parent rootlogin = FXMLLoader.load(
      getClass().getResource("loginControl.fxml")
    );
    Scene scene = new Scene(rootlogin, 600, 400);
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setTitle("Login");
    App.window.setScene(scene);
    App.window.show();
  }
}
