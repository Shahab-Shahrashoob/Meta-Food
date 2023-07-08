import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class userGUI implements Initializable {

  public ImageView home;
  public ComboBox<String> reschoose;
  public Label userbalance;
  public Label username;
  public Label reslabel;

  public void select()
    throws UnknownHostException, IOException, ClassNotFoundException {
    Socket socket = new Socket("localhost", 8000);
    System.out.println("Socket made");
    String chosenRestaurant = reschoose
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
    Parent root = FXMLLoader.load(getClass().getResource("choosefood.fxml"));
    System.out.println("root made");
    Scene scene = new Scene(root, 600, 400);
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setTitle("Choose Food");
    App.window.setScene(scene);
    App.window.show();
  }

  public void logout() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("loginControl.fxml"));
    Scene scene = new Scene(root, 600, 400);
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setTitle("Login");
    App.window.setScene(scene);
    App.window.show();
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
      System.out.println("Restaurants recieved");
      ObservableList<String> a = FXCollections.observableArrayList(list);
      reschoose.setItems(a);
      boolean check = (boolean) input.readObject();
      System.out.println(check);
      socket.close();
      System.out.println("Socket closed");
      System.out.println("Starting new socket");
      socket = new Socket("localhost", 8000);
      ObjectOutputStream output2 = new ObjectOutputStream(
        socket.getOutputStream()
      );
      ObjectInputStream input2 = new ObjectInputStream(socket.getInputStream());
      System.out.println("Channel made");
      output2.writeObject("userID");
      String name = (String) input2.readObject();
      String amount = (String) input2.readObject();
      System.out.println("Items recieved");
      userbalance.setText(amount);
      username.setText(name);
      check = (boolean) input2.readObject();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  void select(ActionEvent event) {
    String s = reschoose.getSelectionModel().getSelectedItem().toString();
    reslabel.setText(s);
  }
}
