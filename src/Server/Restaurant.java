import java.io.Serializable;
import java.util.ArrayList;

public class Restaurant implements Serializable {

  String name;
  String address;
  int begingTime;
  int endTime;
  String image;
  ArrayList<Food> menu;
  boolean visible;

  public ArrayList<Food> getMenu() {
    return menu;
  }

  public Restaurant(
    String name,
    String address,
    int begingTime,
    int endTime,
    String image
  ) {
    this.name = name;
    this.address = address;
    this.begingTime = begingTime;
    this.endTime = endTime;
    this.image = image;
    visible = true;
    menu = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getBegingTime() {
    return begingTime;
  }

  public void setBegingTime(int begingTime) {
    this.begingTime = begingTime;
  }

  public int getEndTime() {
    return endTime;
  }

  public void setEndTime(int endTime) {
    this.endTime = endTime;
  }

  public void addFood(Food food) {
    menu.add(food);
  }

  public void removeFood(Food food) {
    menu.remove(food);
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }
}
