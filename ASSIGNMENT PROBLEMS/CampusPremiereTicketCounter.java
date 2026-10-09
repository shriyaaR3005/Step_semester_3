
import java.util.*;

class Seat {
    String id, type;
    int price;

    Seat(String id, String type) {
        this.id = id;
        this.type = type;
        price = type.equals("Regular") ? 150 :
                type.equals("Premium") ? 250 : 400;
    }
}

class Show {
    Set<String> booked = new HashSet<>();
    boolean started = false;
}

class Booking {
    String customer;
    Show show;
    ArrayList<Seat> seats = new ArrayList<>();
    boolean active = true;

    Booking(String c, Show s, Seat[] selected) {
        customer = c;
        show = s;

        if (selected.length == 0 || selected.length > 6) {
            System.out.println("Booking must contain 1 to 6 seats.");
            active = false;
            return;
        }

        for (Seat seat : selected) {
            if (show.booked.contains(seat.id)) {
                System.out.println("Seat " + seat.id
                        + " is already booked for this show.");
                active = false;
                return;
            }
        }

        for (Seat seat : selected) {
            show.booked.add(seat.id);
            seats.add(seat);
        }

        int total = 0;
        for (Seat seat : seats) total += seat.price;

        System.out.println("Booking confirmed for " + customer + ": "
                + String.join(", ", seatNames()) + ".");
        System.out.printf("Total: ₹%.2f%n", (double) total);
    }

    ArrayList<String> seatNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Seat seat : seats) names.add(seat.id);
        return names;
    }

    void cancel() {
        if (!active || show.started) {
            System.out.println("Cannot cancel booking.");
            return;
        }

        for (Seat seat : seats) show.booked.remove(seat.id);
        active = false;
        System.out.println(customer + "'s booking cancelled. Seats "
                + String.join(", ", seatNames()) + " released.");
    }
}

public class CampusPremiereTicketCounter {
    public static void main(String[] args) {
        Show show = new Show();

        Booking a = new Booking("Asha", show, new Seat[]{
            new Seat("A1", "Regular"),
            new Seat("A2", "Regular"),
            new Seat("F5", "Premium")
        });

        new Booking("Ravi", show, new Seat[]{
            new Seat("A2", "Regular")
        });

        new Booking("Ravi", show, new Seat[]{
            new Seat("R1", "Recliner")
        });

        a.cancel();

        new Booking("Neha", show, new Seat[]{
            new Seat("A2", "Regular")
        });
    }
}