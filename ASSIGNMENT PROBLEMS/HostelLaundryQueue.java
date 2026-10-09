
abstract class Wash {
    abstract int duration();
    abstract int charge();
}

class Quick extends Wash {
    int duration() { return 30; }
    int charge() { return 20; }
}

class Normal extends Wash {
    int duration() { return 45; }
    int charge() { return 30; }
}

class Heavy extends Wash {
    int duration() { return 60; }
    int charge() { return 45; }
}

class Machine {
    String name;
    boolean busy = false;

    Machine(String name) {
        this.name = name;
    }

    void start(String student, Wash w) {
        if (busy) {
            System.out.println(name + " is currently busy.");
            return;
        }
        busy = true;
        System.out.println(w.getClass().getSimpleName()
                + " wash started on " + name + " for " + student
                + " (" + w.duration() + " min).");
        System.out.printf("Charge: ₹%.2f%n", (double) w.charge());
    }

    void complete() {
        busy = false;
        System.out.println(name + " cycle completed. " + name + " is now free.");
    }
}

public class HostelLaundryQueue {
    public static void main(String[] args) {
        Machine m1 = new Machine("M1");
        Machine m2 = new Machine("M2");

        m1.start("Asha", new Quick());
        m1.start("Ravi", new Heavy());
        m2.start("Ravi", new Heavy());
        m1.complete();
        m1.start("Neha", new Normal());
    }
}