
abstract class MembershipPlan {
    abstract int months();
    abstract double discount();

    double calculateFee() {
        return 1000 * months() * (1 - discount());
    }
}

class Monthly extends MembershipPlan {
    int months() { return 1; }
    double discount() { return 0; }
}

class Quarterly extends MembershipPlan {
    int months() { return 3; }
    double discount() { return 0.10; }
}

class Annual extends MembershipPlan {
    int months() { return 12; }
    double discount() { return 0.25; }
}

class Membership {
    String name, status = "Active";
    MembershipPlan plan;

    Membership(String name, MembershipPlan plan) {
        this.name = name;
        this.plan = plan;
        System.out.println(plan.getClass().getSimpleName()
                + " membership created for " + name + ".");
        System.out.printf("Fee: ₹%.2f%n", plan.calculateFee());
        System.out.println("Status: " + status);
    }

    void checkIn() {
        if (status.equals("Active"))
            System.out.println(name + " checked in successfully.");
        else
            System.out.println("Check-in denied: " + name
                    + "'s membership is " + status + ".");
    }

    void freeze() {
        if (status.equals("Active")) {
            status = "Frozen";
            System.out.println(name + "'s membership frozen.");
            System.out.println("Status: " + status);
        } else {
            System.out.println("Cannot freeze an " + status + " membership.");
        }
    }

    void unfreeze() {
        if (status.equals("Frozen")) {
            status = "Active";
            System.out.println(name + "'s membership unfrozen.");
        } else {
            System.out.println("Cannot unfreeze an " + status + " membership.");
        }
    }

    void expire() {
        status = "Expired";
        System.out.println(name + "'s membership expired.");
        System.out.println("Status: " + status);
    }
}

public class FitZoneMembershipDesk {
    public static void main(String[] args) {
        Membership a = new Membership("Asha", new Quarterly());
        Membership r = new Membership("Ravi", new Monthly());

        a.checkIn();
        a.freeze();
        a.checkIn();

        r.expire();
        r.freeze();
    }
}