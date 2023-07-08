public class Paypal extends paymentGateway {

  public static boolean transaction(double amount) {
    if (amount < 100) return false; else return true;
  }
}
