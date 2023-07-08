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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class choosefood implements Initializable {

  public ComboBox<String> foodchoose;
  public Label reslabel;
  public ImageView userpage;
  public Label resaddress;
  public Label start;
  public Label end;
  public Label table;
  public ImageView respic;

  public void select()
    throws UnknownHostException, IOException, ClassNotFoundException {
    Socket socket = new Socket("localhost", 8000);
    System.out.println("Socket made in food");
    String chosenRestaurant = foodchoose
      .getSelectionModel()
      .getSelectedItem()
      .toString();
    System.out.println("Item chosen in food");
    ObjectOutputStream output = new ObjectOutputStream(
      socket.getOutputStream()
    );
    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
    System.out.println("Channel made in food");
    output.writeObject("choosefood");
    output.writeObject(chosenRestaurant);
    System.out.println("Items sent");
    boolean check = (boolean) input.readObject();
    System.out.println("Message recieved : " + check);
    socket.close();
    Stage windows = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("fooddetail.fxml"));
    Scene scene = new Scene(root);
    windows.getIcons().add(new Image("1830351.png"));
    windows.setTitle("Order");
    windows.setScene(scene);
    windows.show();
  }

  public void logout() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("userGUI.fxml"));
    Scene scene = new Scene(root, 600, 400);
    App.window.getIcons().add(new Image("1830351.png"));
    App.window.setTitle("User");
    App.window.setScene(scene);
    App.window.show();
  }

  public void initialize(URL arg0, ResourceBundle arg1) {
    try {
      System.out.println("Inside food");
      Socket socket = new Socket("localhost", 8000);
      ObjectOutputStream output = new ObjectOutputStream(
        socket.getOutputStream()
      );
      ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
      System.out.println("Channel made in food");
      output.writeObject("showfood");
      System.out.println("Command sent");
      String[] list = (String[]) input.readObject();
      ObservableList<String> a = FXCollections.observableArrayList(list);
      foodchoose.setItems(a);
      System.out.println("combo box set");
      boolean check = (boolean) input.readObject();
      socket.close();
      System.out.println("Socket closed");
      socket = new Socket("localhost", 8000);
      ObjectOutputStream output2 = new ObjectOutputStream(
        socket.getOutputStream()
      );
      ObjectInputStream input2 = new ObjectInputStream(socket.getInputStream());
      System.out.println("new socket set");
      output2.writeObject("restaurantID");
      System.out.println("command sent");
      reslabel.setText((String) input2.readObject());
      resaddress.setText((String) input2.readObject());
      System.out.println("name and address");
      start.setText((String) input2.readObject());
      end.setText((String) input2.readObject());
      System.out.println("time sent");
      table.setText((String) input2.readObject());
      System.out.println("type");
      String image = (String) input2.readObject();
      System.out.println("image address");
      System.out.println(image);
      Image x = new Image(image);
      System.out.println("imagecreated");
      System.out.println("Items recieved for res");
      respic.setImage(x);
      check = (boolean) input2.readObject();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
