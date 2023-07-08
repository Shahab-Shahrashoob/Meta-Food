import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

  private static Socket socket;
  private static ObjectInputStream input;
  private static ObjectOutputStream output;
  private static FileInputStream inputFile;
  private static FileOutputStream outputFile;
  private static ObjectInputStream reader;
  private static ObjectOutputStream writer;
  private static File users = new File("Users.dat");
  private static File restaurants = new File("Restaurants.dat");
  private static User currentLoggedInUser;
  private static Restaurant chosenRestaurant;
  private static Food currentFood;
  private static ArrayList<Restaurant> list;

  public static void main(String[] args)
    throws IOException, ClassNotFoundException {
    ServerSocket server = new ServerSocket(8000);
    String command = "";
    boolean result = false;
    do {
      socket = server.accept();
      input = new ObjectInputStream(socket.getInputStream());
      output = new ObjectOutputStream(socket.getOutputStream());
      command = (String) input.readObject();
      System.out.println(command);
      try {
        result = headQuarters(command);
      } catch (EOFException e) {
        System.out.println("End Of File");
      }
      output.writeObject(result);
    } while (!command.equals("Exit"));
    server.close();
  }

  public static boolean headQuarters(String command)
    throws IOException, ClassNotFoundException, EOFException {
    switch (command) {
      case "chooserestaurant":
        {
          System.out.println("In HQ");
          String name = (String) input.readObject();
          System.out.println("Name recieved");
          for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
              chosenRestaurant = list.get(i);
              System.out.println("true");
              return true;
            }
          }
          return false;
        }
      case "order":
        {
          System.out.println("in hq with order");
          int number = (int) input.readObject();
          if (
            (currentFood.getPrice() * number) > currentLoggedInUser.getBalance()
          ) return false; else {
            currentLoggedInUser.setBalance(
              currentLoggedInUser.getBalance() -
              (currentFood.getPrice() * number)
            );
            return true;
          }
        }
      case "choosefood":
        {
          System.out.println("in hq");
          String name = (String) input.readObject();
          System.out.println("food name recieved");
          ArrayList<Food> menu = chosenRestaurant.getMenu();
          for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getName().equals(name)) {
              currentFood = menu.get(i);
              System.out.println("true");
              return true;
            }
          }
          return false;
        }
      case "foodID":
        {
          System.out.println("in hq for food");
          String a = "";
          output.writeObject(currentFood.getName());
          output.writeObject(a + currentFood.getWeight());
          output.writeObject(a + currentFood.getPrice());
          output.writeObject(currentFood.getType());
          output.writeObject(currentFood.getImage());
          return true;
        }
      case "showrestaurants":
        {
          System.out.println("In HQ");
          inputFile = new FileInputStream(restaurants);
          reader = new ObjectInputStream(inputFile);
          System.out.println("Stream made");
          list = (ArrayList<Restaurant>) reader.readObject();
          System.out.println("Restaurants recieved");
          String[] names = new String[list.size()];
          int j = 0;
          for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isVisible()) {
              names[j] = list.get(i).getName();
              j++;
            }
          }
          System.out.println("Names recieved");
          output.writeObject(names);
          System.out.println("Items sent to client");
          return true;
        }
      case "showfood":
        {
          System.out.println("in HQ for food");
          ArrayList<Food> menu = chosenRestaurant.getMenu();
          String[] b = new String[menu.size()];
          for (int i = 0; i < menu.size(); i++) {
            b[i] = menu.get(i).getName();
          }
          output.writeObject(b);
          return true;
        }
      case "newuser":
        {
          System.out.println("In the HQ");
          String username = (String) input.readObject();
          String password = (String) input.readObject();
          String phonenumber = (String) input.readObject();
          String email = (String) input.readObject();
          System.out.println("User Created");
          User a = new User(username, password, email, phonenumber);
          ArrayList<User> list = new ArrayList<User>();
          inputFile = new FileInputStream(users);
          System.out.println("File Made");
          reader = new ObjectInputStream(inputFile);
          System.out.println("Input Channel made");
          list = (ArrayList<User>) reader.readObject();
          System.out.println("List created");
          for (int i = 0; i < list.size(); i++) if (
            username.equals(list.get(i).getUsername())
          ) return false;
          list.add(a);
          System.out.println("New User Added");
          outputFile = new FileOutputStream(users, false);
          writer = new ObjectOutputStream(outputFile);
          writer.writeObject(list);
          System.out.println("Written file completed");
          return true;
        }
      case "loginuser":
        {
          int sw = -1;
          String username = (String) input.readObject();
          String password = (String) input.readObject();
          ArrayList<User> list = new ArrayList<User>();
          inputFile = new FileInputStream(users);
          System.out.println("File Made");
          reader = new ObjectInputStream(inputFile);
          System.out.println("Input Channel made");
          list = (ArrayList<User>) reader.readObject();
          System.out.println("Users recieved");
          System.out.println(list);
          for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getUsername());
            System.out.println(list.get(i).getPhoneNumber());
            System.out.println(list.get(i).getEmail());
            System.out.println(list.get(i).getPassword());
          }
          for (int i = 0; i < list.size(); i++) if (
            username.equals(list.get(i).getUsername()) &&
            password.equals(list.get(i).getPassword())
          ) sw = i;
          outputFile = new FileOutputStream(users, false);
          writer = new ObjectOutputStream(outputFile);
          writer.writeObject(list);
          writer.close();
          reader.close();
          if (sw != -1) {
            currentLoggedInUser = list.get(sw);
            return true;
          } else return false;
        }
      case "adminlogin":
        {
          String Password = (String) input.readObject();
          if (Password.equals("admin")) return true; else return false;
        }
      case "newDrestaurant":
        {
          System.out.println("In HQ new res");
          String name = (String) input.readObject();
          String address = (String) input.readObject();
          int start = (int) input.readObject();
          int close = (int) input.readObject();
          int delivery = (int) input.readObject();
          String image = (String) input.readObject();
          System.out.println("Items recieved");
          takeAway a = new takeAway(
            name,
            address,
            start,
            close,
            image,
            delivery
          );
          System.out.println("Restaurant made");
          inputFile = new FileInputStream(restaurants);
          reader = new ObjectInputStream(inputFile);
          ArrayList<Restaurant> restaurantsList = (ArrayList<Restaurant>) reader.readObject();
          restaurantsList.add(a);
          outputFile = new FileOutputStream(restaurants);
          writer = new ObjectOutputStream(outputFile);
          writer.writeObject(restaurantsList);
          System.out.println(restaurantsList);
          return true;
        }
      case "newTrestaurant":
        {
          System.out.println("In HQ new res");
          String name = (String) input.readObject();
          String address = (String) input.readObject();
          int start = (int) input.readObject();
          int close = (int) input.readObject();
          int tables = (int) input.readObject();
          String image = (String) input.readObject();
          System.out.println("Items recieved");
          withTable a = new withTable(
            name,
            address,
            start,
            close,
            image,
            tables
          );
          System.out.println("Restaurant made");
          inputFile = new FileInputStream(restaurants);
          reader = new ObjectInputStream(inputFile);
          ArrayList<Restaurant> restaurantsList = (ArrayList<Restaurant>) reader.readObject();
          restaurantsList.add(a);
          outputFile = new FileOutputStream(restaurants);
          writer = new ObjectOutputStream(outputFile);
          writer.writeObject(restaurantsList);
          System.out.println(restaurantsList);
          return true;
        }
      case "newfood":
        {
          System.out.println("In HQ new food");
          String name = (String) input.readObject();
          String type = (String) input.readObject();
          String image = (String) input.readObject();
          double price = (double) input.readObject();
          double weight = (double) input.readObject();
          Food a = new Food(name, price, type, image, weight);
          System.out.println("Food Created");
          chosenRestaurant.addFood(a);
          for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(chosenRestaurant.getName())) list
              .get(i)
              .addFood(a);
          }
          outputFile = new FileOutputStream(restaurants, false);
          writer = new ObjectOutputStream(outputFile);
          writer.writeObject(list);
          return true;
        }
      case "removefood":
        {
          System.out.println("In HQ remove");
          String name = (String) input.readObject();
          String type = (String) input.readObject();
          String image = (String) input.readObject();
          double price = (double) input.readObject();
          double weight = (double) input.readObject();
          Food a = new Food(name, price, type, image, weight);
          System.out.println("Food Created");
          chosenRestaurant.removeFood(a);
          for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(chosenRestaurant.getName())) list
              .get(i)
              .removeFood(a);
          }
          outputFile = new FileOutputStream(restaurants, false);
          writer = new ObjectOutputStream(outputFile);
          writer.writeObject(list);
          return true;
        }
      case "addbalance":
        {
          System.out.println("In HQ as Add Balance");
          char code = (char) input.readObject();
          double amount = (double) input.readObject();
          switch (code) {
            case 'v':
              {
                if (Visa.transaction(amount)) {
                  currentLoggedInUser.setBalance(
                    currentLoggedInUser.getBalance() + (amount * 0.95)
                  );
                  System.out.println(currentLoggedInUser.getBalance());
                  return true;
                } else return false;
              }
            case 'p':
              {
                if (Paypal.transaction(amount)) {
                  currentLoggedInUser.setBalance(
                    currentLoggedInUser.getBalance() + amount
                  );
                  System.out.println(currentLoggedInUser.getBalance());
                  return true;
                } else return false;
              }
            case 'e':
              {
                if (Etc.transaction(amount)) {
                  currentLoggedInUser.setBalance(
                    currentLoggedInUser.getBalance() + amount
                  );
                  System.out.println(currentLoggedInUser.getBalance());
                  return true;
                } else return false;
              }
          }
        }
      case "editRestaurant":
        {
          System.out.println("In HQ as EDIT");
          String name = (String) input.readObject();
          String address = (String) input.readObject();
          int start = (int) input.readObject();
          int close = (int) input.readObject();
          int tableOrDelivery = (int) input.readObject();
          boolean visible = (boolean) input.readObject();
          String image = chosenRestaurant.getImage();
          inputFile = new FileInputStream(restaurants);
          reader = new ObjectInputStream(inputFile);
          ArrayList<Restaurant> restaurantsList = (ArrayList<Restaurant>) reader.readObject();
          restaurantsList.remove(chosenRestaurant);
          chosenRestaurant =
            new takeAway(name, address, start, close, image, tableOrDelivery);
          chosenRestaurant.setVisible(visible);
          restaurantsList.add(chosenRestaurant);
          return true;
        }
      case "userID":
        {
          System.out.println("In HQ as UserID");
          output.writeObject(currentLoggedInUser.getUsername());
          String balance = "";
          output.writeObject(balance + currentLoggedInUser.getBalance());
          return true;
        }
      case "restaurantID":
        {
          System.out.println("In HQ as RestaurantID");
          output.writeObject(chosenRestaurant.getName());
          output.writeObject(chosenRestaurant.getAddress());
          System.out.println("name and address sent");
          String a = "";
          output.writeObject(a + chosenRestaurant.getBegingTime());
          output.writeObject(a + chosenRestaurant.getEndTime());
          System.out.println("times sent");
          if (chosenRestaurant instanceof takeAway) {
            takeAway b = (takeAway) chosenRestaurant;
            output.writeObject(a + b.getDeliverys());
          } else if (chosenRestaurant instanceof withTable) {
            withTable b = (withTable) chosenRestaurant;
            output.writeObject(a + b.getTables());
          }
          System.out.println("tables/delivery sent");
          output.writeObject(chosenRestaurant.getImage());
          System.out.println("Everything sent");
          return true;
        }
    }
    return false;
  }
}
