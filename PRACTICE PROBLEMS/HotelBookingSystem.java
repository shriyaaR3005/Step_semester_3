
import java.time.LocalDate;
import java.util.*;

abstract class Room {
    int number;
    double price;

    Room(int number, double price) {
        this.number = number;
        this.price = price;
    }

    double calculatePrice(long days) {
        return price * days;
    }
}

class StandardRoom extends Room {
    StandardRoom(int n) { super(n, 100); }
}

class DeluxeRoom extends Room {
    DeluxeRoom(int n) { super(n, 200); }
}

class Reservation {
    String customer;
    Room room;
    LocalDate start, end, deadline;
    boolean active = true;

    Reservation(String c, Room r, LocalDate s, LocalDate e,
                LocalDate d) {
        customer = c;
        room = r;
        start = s;
        end = e;
        deadline = d;
    }

    boolean overlaps(LocalDate s, LocalDate e) {
        return active && s.isBefore(end) && e.isAfter(start);
    }

    void cancel(LocalDate today) {
        if (today.isBefore(deadline) && active) {
            active = false;
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Cancellation deadline passed.");
        }
    }
}

public class HotelBookingSystem {
    static ArrayList<Reservation> bookings = new ArrayList<>();

    static void book(String name, Room room, LocalDate s,
                     LocalDate e, LocalDate deadline) {
        for (Reservation r : bookings) {
            if (r.room.number == room.number && r.overlaps(s, e)) {
                System.out.println("Room " + room.number + " unavailable.");
                return;
            }
        }

        Reservation r = new Reservation(name, room, s, e, deadline);
        bookings.add(r);
        long days = java.time.temporal.ChronoUnit.DAYS.between(s, e);

        System.out.println("Room " + room.number + " booked by " + name);
        System.out.println("Price: $" + room.calculatePrice(days));
    }

    public static void main(String[] args) {
        Room r1 = new StandardRoom(101);
        Room r2 = new DeluxeRoom(201);

        LocalDate s = LocalDate.of(2026, 1, 1);
        LocalDate e = LocalDate.of(2026, 1, 5);

        book("Customer A", r1, s, e, s.minusDays(1));
        book("Customer B", r1,
             LocalDate.of(2026, 1, 3),
             LocalDate.of(2026, 1, 7), s);

        bookings.get(0).cancel(LocalDate.of(2025, 12, 20));

        book("Customer C", r2,
             LocalDate.of(2026, 2, 10),
             LocalDate.of(2026, 2, 12),
             LocalDate.of(2026, 2, 5));
    }
}