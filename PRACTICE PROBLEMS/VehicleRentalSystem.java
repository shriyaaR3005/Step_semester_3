

abstract class Vehicle {
    String name;
    boolean available = true;

    Vehicle(String name) {
        this.name = name;
    }

    abstract double charge(int days);
}

class Sedan extends Vehicle {
    Sedan(String name) { super(name); }
    double charge(int days) { return days * 50; }
}

class SUV extends Vehicle {
    SUV(String name) { super(name); }
    double charge(int days) { return days * 80; }
}

public class VehicleRentalSystem {
    static void rent(Vehicle v, String customer, int days) {
        if (!v.available) {
            System.out.println(v.name + " is currently unavailable.");
            return;
        }
        v.available = false;
        System.out.println(v.name + " rented by " + customer);
        System.out.println("Rental charge: $" + v.charge(days));
    }

    static void returnVehicle(Vehicle v, String customer) {
        v.available = true;
        System.out.println(v.name + " returned by " + customer);
    }

    public static void main(String[] args) {
        Vehicle a = new Sedan("Sedan A");
        Vehicle b = new SUV("SUV B");

        rent(a, "Customer 1", 3);
        rent(a, "Customer 2", 2);
        returnVehicle(a, "Customer 1");
        rent(b, "Customer 3", 5);
    }
}