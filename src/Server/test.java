import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class test {

  public static void main(String[] args) throws IOException {
    takeAway a = new takeAway(
      "Yasaman",
      "Emamat blv.",
      9,
      21,
      "D:\\Project\\Server\\yasaman.png",
      8
    );
    a.addFood(
      new Food("hotdog", 81, "fastfood", "D:\\Project\\Server\\hotdog.jpg", 300)
    );
    ArrayList<Restaurant> list = new ArrayList<>();
    list.add(a);
    File file = new File("Restaurants.dat");
    FileOutputStream out = new FileOutputStream(file, false);
    ObjectOutputStream output = new ObjectOutputStream(out);
    output.writeObject(list);
    output.close();
  }
}
