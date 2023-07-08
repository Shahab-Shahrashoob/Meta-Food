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

  public void signUpButtonClicked()
    throws InterruptedException, UnknownHostException, IOException, ClassNotFoundException {
    String Username = username.getText();
    String Password = password.getText();
    String Confirmpassword = confirmPassword.getText();
    String Phonenumber = phoneNumber.getText();
    String Email = email.getText();
    if (!Password.equals(Confirmpassword)) {
      signUpCheck.setStyle("-fx-text-fill : red;");
      signUpCheck.setText("Passwords don't match");
      return;
    }
    try {
      for (int i = 0; i < Phonenumber.length(); i++) if (
        Phonenumber.charAt(i) < '0' || Phonenumber.charAt(i) > '9'
      ) throw new LettersInNumbersException();
    } catch (LettersInNumbersException e) {
      signUpCheck.setStyle("-fx-text-fill : red;");
      signUpCheck.setText("Phone number cannot have letters or symbols");
      return;
    }
    System.out.println("Items done");
    Socket socket = new Socket("localhost", 8000);
    ObjectOutputStream output = new ObjectOutputStream(
      socket.getOutputStream()
    );
    System.out.println("Socket done");
    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
    System.out.println("Output channel made");
    output.writeObject("newuser");
    output.writeObject(Username);
    output.writeObject(Password);
    output.writeObject(Phonenumber);
    output.writeObject(Email);
    System.out.println("Items sent");
    boolean check = (boolean) input.readObject();
    System.out.println("Message recieved : " + check);
    if (check) {
      signUpCheck.setStyle("-fx-text-fill : green;");
      signUpCheck.setText("Sign Up Successful");
    } else {
      signUpCheck.setStyle("-fx-text-fill : red;");
      signUpCheck.setText("This Account Already Exists");
    }
    socket.close();
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
