import java.io.Serializable;

public class User implements Serializable {

  String username;
  String password;
  String phoneNumber;
  String email;
  double balance;

  public User(
    String username,
    String password,
    String phoneNumber,
    String email
  ) {
    this.username = username;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.balance = 0.0;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public boolean contains(User user) {
    if (
      user.getUsername().equals(username) && user.getPassword().equals(password)
    ) return true; else return false;
  }
}
