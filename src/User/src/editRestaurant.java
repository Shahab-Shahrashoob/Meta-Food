import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class editRestaurant implements Initializable {

  public ComboBox restaurantselect;
  public TextField name;
  public TextField address;
  public TextField delivery;
  public TextField start;
  public TextField end;
  public Button addfood;
  public Button removefood;
  public Button submit;
  public CheckBox visible;
  public Hyperlink backlink;
  public AnchorPane pane;

  public void select()
    throws UnknownHostException, IOException, ClassNotFoundException {
    Socket socket = new Socket("localhost", 8000);
    System.out.println("Socket made");
    String chosenRestaurant = restaurantselect
      .getSelectionModel()
      .getSelectedItem()
      .toString();
    System.out.println("Item chosen");
    ObjectOutputStream output = new ObjectOutputStream(
      socket.getOutputStream()
    );
    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
    System.out.println("Channel made");
    output.writeObject("chooserestaurant");
    output.writeObject(chosenRestaurant);
    System.out.println("Items sent");
    boolean check = (boolean) input.readObject();
    System.out.println("Message recieved : " + check);
    socket.close();
    pane.setVisible(true);
  }

  public void submitclicked() throws IOException, ClassNotFoundException {
    Socket socket = new Socket("localhost", 8000);
    String chosenRestaurant = (String) restaurantselect.getValue();
    ObjectOutputStream output = new ObjectOutputStream(
      socket.getOutputStream()
    );
    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
    output.writeObject("editrestaurant");
    output.writeObject(name.getText());
    output.writeObject(address.getText());
    output.writeObject(Integer.parseInt(start.getText()));
    output.writeObject(Integer.parseInt(end.getText()));
    output.writeObject(Integer.parseInt(delivery.getText()));
    output.writeObject(visible.isSelected());
    System.out.println("Items Sent");
    boolean check = (boolean) input.readObject();
    socket.close();
  }

  public void add() throws IOException {
    Stage windows = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("addFood.fxml"));
    Scene scene = new Scene(root, 300, 400);
    windows.getIcons().add(new Image("1830351.png"));
    windows.setTitle("Add Food");
    windows.setScene(scene);
    windows.show();
  }

  public void backlinkclicked() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
    Scene scene = new Scene(root, 300, 400);
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setTitle("Admin");
    App.window.setScene(scene);
    App.window.show();
  }

  public void remove() throws IOException {
    Stage windows = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("removeFood.fxml"));
    Scene scene = new Scene(root, 600, 400);
    windows.getIcons().add(new Image("1830351.png"));
    windows.setTitle("Remove Food");
    windows.setScene(scene);
    windows.show();
  }

  public void initialize(URL arg0, ResourceBundle arg1) {
    try {
      Socket socket = new Socket("localhost", 8000);
      ObjectOutputStream output = new ObjectOutputStream(
        socket.getOutputStream()
      );
      ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
      output.writeObject("showrestaurants");
      String[] list = (String[]) input.readObject();
      ObservableList<String> a = FXCollections.observableArrayList(list);
      restaurantselect.setItems(a);
      boolean check = (boolean) input.readObject();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
