public class takeAway extends Restaurant {

  int deliverys;

  public takeAway(
    String name,
    String address,
    int begingTime,
    int endTime,
    String image,
    int deliverys
  ) {
    super(name, address, begingTime, endTime, image);
    this.deliverys = deliverys;
  }

  public int getDeliverys() {
    return deliverys;
  }

  public void setDeliverys(int deliverys) {
    this.deliverys = deliverys;
  }
}
