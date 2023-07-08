public class withTable extends Restaurant {

  int tables;

  public withTable(
    String name,
    String address,
    int begingTime,
    int endTime,
    String image,
    int tables
  ) {
    super(name, address, begingTime, endTime, image);
    this.tables = tables;
  }

  public int getTables() {
    return tables;
  }

  public void setTables(int tables) {
    this.tables = tables;
  }
}
