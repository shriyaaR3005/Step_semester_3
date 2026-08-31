import java.util.Scanner;

class HallTicket {
    String studentName;
    int seatNumber;

    HallTicket(String studentName, int seatNumber) {
        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial seat number: ");
        int seat = sc.nextInt();

        HallTicket priya = new HallTicket(name, seat);

        // Reference copy
        HallTicket copy = priya;

        System.out.print("Enter new seat number through copy: ");
        copy.seatNumber = sc.nextInt();

        // Separate object
        HallTicket separate = new HallTicket(name, copy.seatNumber);

        System.out.println("\nOutput:");

        System.out.println(name + "'s seatNumber (via first variable): "
                + priya.seatNumber);

        System.out.println("copy == priya: " + (copy == priya));

        System.out.println("separate == priya: " + (separate == priya));

        sc.close();
    }
}