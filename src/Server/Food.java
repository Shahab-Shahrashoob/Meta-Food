import java.io.Serializable;

public class Food implements Serializable {

  String name;
  double price;
  String type;
  String image;
  double weight;

  public Food(
    String name,
    double price,
    String type,
    String image,
    double weight
  ) {
    this.name = name;
    this.price = price;
    this.type = type;
    this.image = image;
    this.weight = weight;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }
}
