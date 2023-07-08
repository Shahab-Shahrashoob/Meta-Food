import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class addwithtablerestaurant {

  public ImageView adminpage;
  public TextField name;
  public TextField address;
  public TextField start;
  public TextField close;
  public TextField tables;
  public Button addrestaurantbutton;
  public TextField imageaddress;
  public Label confirm;

  public void adminclick() throws IOException {
    Parent root = FXMLLoader.load(
      getClass().getResource("addrestaurantpage.fxml")
    );
    Scene scene = new Scene(root, 600, 400);
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setTitle("Add Restaurant");
    App.window.setScene(scene);
    App.window.show();
  }

  public void addclicked() throws IOException, ClassNotFoundException {
    System.out.println("In method");
    String Name = name.getText();
    String Address = address.getText();
    int Start = Integer.parseInt(start.getText());
    int Close = Integer.parseInt(close.getText());
    int Tables = Integer.parseInt(tables.getText());
    String image = imageaddress.getText();
    System.out.println("Items Recieved");
    Socket socket = new Socket("localhost", 8000);
    ObjectOutputStream output = new ObjectOutputStream(
      socket.getOutputStream()
    );
    System.out.println("Socket done");
    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
    System.out.println("Output channel made");
    output.writeObject("newTrestaurant");
    output.writeObject(Name);
    output.writeObject(Address);
    output.writeObject(Start);
    output.writeObject(Close);
    output.writeObject(Tables);
    output.writeObject(image);
    System.out.println("Items sent");
    boolean check = (boolean) input.readObject();
    if (check) {
      confirm.setStyle("-fx-text-fill: green");
      confirm.setText("Add Seccussful");
    }
    socket.close();
  }
}
