import java.util.Scanner;

public class BookInventory {

    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {

        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {

        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {

        return copiesAvailable;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of copies: ");
        int total = sc.nextInt();

        BookInventory book =
            new BookInventory(total);

        System.out.print("Enter number of check-out operations: ");
        int out = sc.nextInt();

        for (int i = 0; i < out; i++) {
            book.checkOut();
        }

        System.out.println(
            "Available copies after checkout: "
            + book.getCopiesAvailable()
        );

        System.out.print("Enter number of check-in operations: ");
        int in = sc.nextInt();

        for (int i = 0; i < in; i++) {
            book.checkIn();
        }

        System.out.println(
            "Available copies after check-in: "
            + book.getCopiesAvailable()
        );

        sc.close();
    }
}