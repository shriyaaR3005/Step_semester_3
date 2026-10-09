
abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) { super(name); }
    boolean canTakeLeave(int days) { return days <= 20; }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) { super(name); }
    boolean canTakeLeave(int days) { return days <= 5; }
}

class LeaveRequest {
    String name, status = "Pending";

    LeaveRequest(String name) {
        this.name = name;
    }

    void review(boolean approve) {
        if (!status.equals("Pending")) {
            System.out.println("Request already reviewed.");
            return;
        }
        status = approve ? "Approved" : "Rejected";
        System.out.println(name + "'s leave: " + status);
    }

    void changeStatus(String s) {
        if (!status.equals("Pending"))
            System.out.println("Cannot change " + status + " to " + s);
        else
            status = s;
    }
}

public class EmployeeLeaveSystem {
    public static void main(String[] args) {
        Employee e = new FullTimeEmployee("John");
        LeaveRequest r = new LeaveRequest(e.name);

        if (e.canTakeLeave(5)) {
            System.out.println("Leave request submitted for John");
            r.review(true);
            r.changeStatus("Pending");
        }

        Employee p = new PartTimeEmployee("Jane");
        LeaveRequest r2 = new LeaveRequest(p.name);

        if (p.canTakeLeave(2)) {
            System.out.println("Leave request submitted for Jane");
            r2.review(false);
        }
    }
}