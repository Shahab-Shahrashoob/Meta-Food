import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class paymentGatewayControl {

  public PasswordField cardPassword;

  public TextField cardnum1;
  public TextField cardnum2;
  public TextField cardnum3;
  public TextField cardnum4;
  public TextField cvv2;
  public TextField edMonth;
  public TextField edYear;
  public ToggleGroup paymentMethod;
  public Button transactionButton;
  public RadioButton paypal;
  public RadioButton visa;
  public RadioButton etc;
  public Button unlock;
  public TextField amount;
  public Label confirm;

  public void num1() {
    if (cardnum1.getText().length() > 3) {
      cardnum1.setDisable(true);
    }
  }

  public void num2() {
    if (cardnum2.getText().length() > 3) {
      cardnum2.setDisable(true);
    }
  }

  public void num3() {
    if (cardnum3.getText().length() > 3) {
      cardnum3.setDisable(true);
    }
  }

  public void num4() {
    if (cardnum4.getText().length() > 3) {
      cardnum4.setDisable(true);
      unlock.setVisible(true);
    }
  }

  public void transactionButtonClicked()
    throws IOException, ClassNotFoundException {
    double Amount = Double.parseDouble(amount.getText());
    char code;
    if (paypal.isSelected()) code = 'p'; else if (visa.isSelected()) code =
      'v'; else code = 'e';
    System.out.println(code);
    Socket socket = new Socket("localhost", 8000);
    System.out.println("Socket made");
    ObjectOutputStream output = new ObjectOutputStream(
      socket.getOutputStream()
    );
    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
    System.out.println("Stream Channel made");
    output.writeObject("addbalance");
    output.writeObject(code);
    output.writeObject(Amount);
    System.out.println("Sent to Server");
    boolean check = (boolean) input.readObject();
    System.out.println("Recieved message from server");
    if (!check) {
      confirm.setStyle("-fx-text-fill : red;");
      confirm.setText("Transaction Failed");
    } else {
      confirm.setStyle("-fx-text-fill : green;");
      confirm.setText("Transaction Successful");
    }
    socket.close();
  }

  public void unlockClicked() {
    cardnum1.setText("");
    cardnum2.setText("");
    cardnum3.setText("");
    cardnum4.setText("");
    cardnum1.setDisable(false);
    cardnum2.setDisable(false);
    cardnum3.setDisable(false);
    cardnum4.setDisable(false);
  }
}
