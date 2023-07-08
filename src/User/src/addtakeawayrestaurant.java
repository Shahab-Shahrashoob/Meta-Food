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

public class addtakeawayrestaurant {

  public ImageView adminpage;
  public TextField addname;
  public TextField addaddress;
  public TextField addstart;
  public TextField addclose;
  public TextField addnumberofdelivery;
  public Button addrestaurantbutton;
  public TextField imageaddress;
  public Label confirm;

  public void adminpage() throws IOException {
    Parent root = FXMLLoader.load(
      getClass().getResource("addrestaurantpage.fxml")
    );
    Scene scene = new Scene(root, 600, 400);
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setTitle("Add Restaurant");
    App.window.setScene(scene);
    App.window.show();
  }

  public void addclick() throws IOException, ClassNotFoundException {
    String name = addname.getText();
    String address = addaddress.getText();
    int start = Integer.parseInt(addstart.getText());
    int close = Integer.parseInt(addclose.getText());
    int delivery = Integer.parseInt(addnumberofdelivery.getText());
    String image = imageaddress.getText();
    System.out.println("Items Recieved");
    Socket socket = new Socket("localhost", 8000);
    ObjectOutputStream output = new ObjectOutputStream(
      socket.getOutputStream()
    );
    System.out.println("Socket done");
    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
    System.out.println("Output channel made");
    output.writeObject("newDrestaurant");
    output.writeObject(name);
    output.writeObject(address);
    output.writeObject(start);
    output.writeObject(close);
    output.writeObject(delivery);
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
