package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

  public static void main(String[] args)
    throws IOException, ClassNotFoundException {
    ServerSocket server = new ServerSocket(8000);
    ObjectInputStream input;
    ObjectOutputStream output;
    Socket socket;
    String result;
    do {
      socket = server.accept();
      input = new ObjectInputStream(socket.getInputStream());
      output = new ObjectOutputStream(socket.getOutputStream());
      result = (String) input.readObject();
      System.out.println(result);
    } while (!result.equals("EXIT"));
  }
}
