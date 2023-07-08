import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class removeFood {

  public TextField name;
  public TextField price;
  public TextField type;
  public TextField image;
  public TextField weight;
  public Button remove;
  public Label textLabel;

  public void removeclicked() throws IOException, ClassNotFoundException {
    String Name = name.getText();
    String Type = type.getText();
    String Image = image.getText();
    double Price = Double.parseDouble(price.getText());
    double Weight = Double.parseDouble(weight.getText());
    Socket socket = new Socket("localhost", 8000);
    ObjectOutputStream output = new ObjectOutputStream(
      socket.getOutputStream()
    );
    System.out.println("Socket done");
    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
    System.out.println("Output channel made");
    output.writeObject("removefood");
    output.writeObject(Name);
    output.writeObject(Type);
    output.writeObject(Image);
    output.writeObject(Price);
    output.writeObject(Weight);
    System.out.println("Items sent");
    boolean check = (boolean) input.readObject();
    if (check) {
      textLabel.setStyle("-fx-text-fill: red");
      textLabel.setText("Remove Seccussful");
    }
    socket.close();
  }
}
