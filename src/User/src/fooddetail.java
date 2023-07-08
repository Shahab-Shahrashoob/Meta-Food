import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class fooddetail implements Initializable {

  public Label foodname;
  public Label foodprice;
  public Label foodtype;
  public Label foodweight;
  public TextField numberoffood;
  public ImageView foodpic;
  public Label nomoney;
  public Button pay;
  public Button buy;

  public void buyclicked()
    throws UnknownHostException, IOException, ClassNotFoundException {
    int number = Integer.parseInt(numberoffood.getText());
    Socket socket = new Socket("localhost", 8000);
    ObjectOutputStream output = new ObjectOutputStream(
      socket.getOutputStream()
    );
    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
    output.writeObject("order");
    output.writeObject(number);
    boolean check = (boolean) input.readObject();
    if (check) {
      nomoney.setStyle("-fx-text-fill : green");
      nomoney.setText("Order Succeed");
      nomoney.setVisible(true);
    } else {
      nomoney.setVisible(true);
      pay.setVisible(true);
    }
    socket.close();
  }

  public void pay() throws IOException {
    Stage wind = new Stage();
    Parent root = FXMLLoader.load(
      getClass().getResource("paymentGatewayControl.fxml")
    );
    Scene scene = new Scene(root, 600, 400);
    wind.getIcons().add(new Image("1830351.png"));
    wind.setTitle("Payment Gateway");
    wind.setScene(scene);
    wind.show();
  }

  public void initialize(URL arg0, ResourceBundle arg1) {
    try {
      Socket socket = new Socket("localhost", 8000);
      ObjectOutputStream output = new ObjectOutputStream(
        socket.getOutputStream()
      );
      ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
      output.writeObject("foodID");
      foodname.setText((String) input.readObject());
      foodweight.setText((String) input.readObject());
      foodprice.setText((String) input.readObject());
      foodtype.setText((String) input.readObject());
      foodpic.setImage(new Image((String) input.readObject()));
      boolean check = (boolean) input.readObject();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
