

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return false;
    }
}

class Order {
    String name, status = "Pending";
    double total = 0;

    Order(String name) {
        this.name = name;
        System.out.println("Order created for " + name);
    }

    void addProduct(double price, int quantity) {
        total += price * quantity;
    }

    void pay(PaymentMethod method, String paymentName) {
        if (total == 0) {
            System.out.println("Cannot pay for an empty order.");
            return;
        }

        System.out.println("Payment via " + paymentName);
        if (method.processPayment(total)) {
            status = "Paid";
            System.out.println("Payment successful.");
        } else {
            System.out.println("Payment failed.");
        }
        System.out.println("Order status: " + status);
    }
}

public class PaymentProcessingSystem {
    public static void main(String[] args) {
        Order a = new Order("Customer X");
        a.addProduct(100, 2);
        a.addProduct(50, 1);
        a.pay(new CreditCardPayment(), "Credit Card");

        Order b = new Order("Customer Y");
        b.pay(new CreditCardPayment(), "Credit Card");

        Order c = new Order("Customer Z");
        c.addProduct(200, 1);
        c.pay(new PayPalPayment(), "PayPal");
    }
}