class Payment {

    void pay(double amount) {
        System.out.println(
            "Paid (cash): Rs " + amount
        );
    }
}

class CardPayment extends Payment {

    void payWithProcessingFee(double amount) {
        double total = amount + (amount * 0.02);

        System.out.println(
            "Charged (card, incl. fee): Rs " + total
        );
    }
}

public class CanteenPaymentDispatch {

    static double processTransaction(
        Payment payment,
        double amount
    ) {

        if (payment instanceof CardPayment) {

            CardPayment cardPayment =
                (CardPayment) payment;

            cardPayment.payWithProcessingFee(amount);

            return amount + (amount * 0.02);

        } else {

            payment.pay(amount);

            return amount;
        }
    }

    public static void main(String[] args) {

        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = {
            100,
            50,
            200,
            75,
            120
        };

        double totalCollected = 0;

        for (int i = 0; i < payments.length; i++) {

            double charged =
                processTransaction(
                    payments[i],
                    amounts[i]
                );

            totalCollected =
                totalCollected + charged;
        }

        System.out.println(
            "Total Collected: Rs " + totalCollected
        );
    }
}