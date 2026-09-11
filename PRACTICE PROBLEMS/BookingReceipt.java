import java.util.Scanner;

public class BookingReceipt {

    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {

        this.bookingId = bookingId;

        // Defensive copy while receiving the array
        this.seatNumbers = seatNumbers.clone();
    }

    public String[] getSeatNumbers() {

        // Defensive copy while returning the array
        return seatNumbers.clone();
    }

    public BookingReceipt withUpdatedSeat(
            int index, String newSeat) {

        String[] updatedSeats = seatNumbers.clone();

        if (index >= 0 && index < updatedSeats.length) {
            updatedSeats[index] = newSeat;
        }

        return new BookingReceipt(
                bookingId,
                updatedSeats
        );
    }

    public static String processNightlySettlement(
            BookingReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (BookingReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof GroupBookingReceipt) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + group + " group | "
                + individual + " individual";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of receipts: ");
        int n = sc.nextInt();
        sc.nextLine();

        BookingReceipt[] receipts =
                new BookingReceipt[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nReceipt " + (i + 1));

            System.out.print(
                "Enter type (group/individual/null): "
            );

            String type = sc.nextLine();

            if (type.equalsIgnoreCase("null")) {
                receipts[i] = null;
                continue;
            }

            System.out.print("Enter booking ID: ");
            String bookingId = sc.nextLine();

            System.out.print("Enter number of seats: ");
            int seatCount = sc.nextInt();
            sc.nextLine();

            String[] seats = new String[seatCount];

            for (int j = 0; j < seatCount; j++) {

                System.out.print(
                    "Enter seat " + (j + 1) + ": "
                );

                seats[j] = sc.nextLine();
            }

            if (type.equalsIgnoreCase("group")) {

                System.out.print("Enter group size: ");
                int groupSize = sc.nextInt();
                sc.nextLine();

                receipts[i] =
                    new GroupBookingReceipt(
                        bookingId,
                        seats,
                        groupSize
                    );

            } else {

                receipts[i] =
                    new BookingReceipt(
                        bookingId,
                        seats
                    );
            }
        }

        System.out.println("\nSettlement:");
        System.out.println(
            processNightlySettlement(receipts)
        );

        sc.close();
    }
}


// Group booking variant
class GroupBookingReceipt extends BookingReceipt {

    private final int groupSize;

    public GroupBookingReceipt(
            String bookingId,
            String[] seatNumbers,
            int groupSize) {

        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}